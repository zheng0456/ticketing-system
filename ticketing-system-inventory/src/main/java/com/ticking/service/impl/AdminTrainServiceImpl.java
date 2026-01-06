package com.ticking.service.impl;

import com.ticking.entity.TrainCarriageEntity;
import com.etc.trainordersys.utils.SnowflakeIdWorker;
import com.ticking.entity.TrainEntity;
import com.ticking.entity.TrainStationEntity;
import com.ticking.mapper.AdminTrainMapper;
import com.ticking.mapper.TicketMapper;
import com.ticking.service.IAdminTrainService;
import com.ticking.until.RedisUtil;
import com.ticking.until.TimeCalculationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("adminTrainService")
public class AdminTrainServiceImpl implements IAdminTrainService {
    @Autowired
    AdminTrainMapper adminTrainMapper;
    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 查询车次信息
     */
    @Override
    public List<TrainEntity> selectTrainMessages() {
        List<TrainEntity> trains=adminTrainMapper.selectAllTrain();
        for (TrainEntity train:trains){
            String startStationName=adminTrainMapper.selectTrainStationByTrainId(train.getStartStationId());
            String endStationName=adminTrainMapper.selectTrainStationByTrainId(train.getEndStationId());
            train.setStartStationName(startStationName);
            train.setEndStationName(endStationName);
        }
        return trains;
    }

    /**
     * 添加车次信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addTrain(Map<String, Object> train) {
        Boolean result=false;
        Integer businessClassCarriages = (Integer) train.get("businessClassCarriages");// 商务座
        Integer secondClassCarriages = (Integer) train.get("secondClassCarriages");// 二等座
        Integer firstClassCarriages = (Integer) train.get("firstClassCarriages");// 一等座

        if (businessClassCarriages == 0 && secondClassCarriages == 0 && firstClassCarriages == 0) {
            //普通列车 和 动车
            result=addCommonTrain(train);
        } else {
            //高铁
            result=addHighSpeedTrain(train);
        }
        return result;
    }

    @Override
    public Boolean updateTrain(Long id, Map<String, Object> train) {
        Integer status = (Integer) train.get("status");   // 状态
        String departureTime = (String) train.get("departureTime");  // 出发时间
        String arrivalTime = (String) train.get("arrivalTime");   // 到达时间
        Integer startStation = (Integer) train.get("startStationId");  // 出发站
        Integer endStation = (Integer) train.get("endStationId");  // 到达站
        String serviceLife = (String) train.get("serviceLife");// 服役年限
        String remark = (String) train.get("remark");   // 备注
        String lastMaintenanceDate = (String) train.get("lastMaintenanceDate"); // 最后维护时间
        Boolean result=adminTrainMapper.updateTrain(id,departureTime, arrivalTime, startStation, endStation, status, Double.valueOf(serviceLife), remark, lastMaintenanceDate);
        return result;
    }
    @Override
    public Boolean deleteTrain(Long id) {
        Boolean result = adminTrainMapper.deleteTrain(id);
        if (result) {
            List<Long> carriageIDs = adminTrainMapper.selectTrainCarriagesID(id);
            for (Long carriageID : carriageIDs) {
                // 删除车厢对应的座位Redis缓存
                deleteCarriageSeatCache(carriageID);
                // 删除座位信息
                adminTrainMapper.deleteTrainSeats(carriageID);
            }
            // 删除列车对应的Redis缓存
            deleteTrainCache(id);
            result = adminTrainMapper.deleteTrainCarriages(id);
        }
        return result;
    }

    /**
     * 删除车厢座位相关的Redis缓存
     */
    private void deleteCarriageSeatCache(Long carriageID) {
        try {
            // 获取该车厢下所有座位的Redis键
            Set<String> seatKeys = redisUtil.keys("carriageId:" + carriageID + ":seatId:*");
            if (seatKeys != null && !seatKeys.isEmpty()) {
                redisUtil.del(seatKeys.toArray(new String[0]));
            }
            
            // 删除该车厢的座位统计缓存
            Set<String> carriageKeys = redisUtil.keys("train:*:carriage:" + carriageID);
            if (carriageKeys != null && !carriageKeys.isEmpty()) {
                redisUtil.del(carriageKeys.toArray(new String[0]));
            }
        } catch (Exception e) {
            System.err.println("删除车厢Redis缓存失败，carriageID: " + carriageID + ", 错误: " + e.getMessage());
        }
    }

    /**
     * 删除列车相关的Redis缓存
     */
    private void deleteTrainCache(Long trainId) {
        try {
            // 删除该列车相关的所有缓存
            Set<String> trainKeys = redisUtil.keys("train:" + trainId + ":*");
            if (trainKeys != null && !trainKeys.isEmpty()) {
                redisUtil.del(trainKeys.toArray(new String[0]));
            }
        } catch (Exception e) {
            System.err.println("删除列车Redis缓存失败，trainId: " + trainId + ", 错误: " + e.getMessage());
        }
    }

    //添加普通列车和动车
    private Boolean addCommonTrain(Map<String, Object> train) {
        Boolean result = false;
        Boolean result1 = true;
        Boolean result2 = true;
        Boolean result3 = true;
        String departureTime = (String) train.get("departureTime");  // 出发时间
        String arrivalTime = (String) train.get("arrivalTime");   // 到达时间
        Integer startStation = (Integer) train.get("startStation");  // 出发站
        Integer endStation = (Integer) train.get("endStation");  // 到达站
        String trainType = (String) train.get("trainType");// 列车类型
        String trainNumber = (String) train.get("trainNumber");  // 车次号
        Integer status = (Integer) train.get("status");// 状态
        String serviceLife = (String) train.get("serviceLife");// 服役年限
        String remark = (String) train.get("remark");   // 备注
        String manufactureDate = (String) train.get("manufactureDate");   // 制造时间
        String lastMaintenanceDate = (String) train.get("lastMaintenanceDate"); // 最后维护时间
        Integer hardSeatCarriages = (Integer) train.get("hardSeatCarriages");// 硬座
        Integer hardSleeperCarriages = (Integer) train.get("hardSleeperCarriages");  // 硬卧
        Integer softSleeperCarriages = (Integer) train.get("softSleeperCarriages");  // 软卧
        String runDuration = new TimeCalculationExample().calculateTimeDifference(departureTime, arrivalTime);  // 运行时长

        //雪花算法获取id
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker();
        long id = snowflakeIdWorker.nextId();
        //添加车次信息
        int trainEntity = adminTrainMapper.addTrain(id,departureTime, arrivalTime, startStation, endStation,trainType, trainNumber, status, Double.valueOf(serviceLife), remark, manufactureDate, lastMaintenanceDate, runDuration);
        if (trainEntity != 0) {
            //添加车厢信息
            Boolean ticketsAdded1 = false;
            Boolean ticketsAdded2 = false;
            Boolean ticketsAdded3 = false;  // 标志变量，确保只执行一次
            for (int i = 0; i < hardSeatCarriages; i++) {
                long TrainCarriageID = snowflakeIdWorker.nextId();
                trainEntity = adminTrainMapper.addTrainCarriage(TrainCarriageID+i,id, i+1, "硬座", 118);
                if (!ticketsAdded1) {
                    ticketMapper.addTickets(id,"硬座", startStation, endStation);  //添加车票信息，只执行一次
                    ticketsAdded1 = true;
                }
                if (trainEntity != 0) {
                    //添加座位信息
                    for (int j = 0; j < 118; j++) {
                        long seatId = snowflakeIdWorker.nextId();
                        if (0==adminTrainMapper.addTrainSeat(seatId+j,TrainCarriageID+i, String.valueOf(j + 1))) {
                            result1 = false;
                        }else {
                            // 将座位信息存储到Redis中
                            String seatRedisKey = "carriageId:"+ (TrainCarriageID+i)+":seatId:"+ (seatId+j);
                            Map<String, Object> seatInfo = new HashMap<>();
                            seatInfo.put("seatType", String.valueOf(j + 1));
                            seatInfo.put("status", 0);
                            redisUtil.hmset(seatRedisKey, seatInfo);
                        }
                    }
                    // 将座位数量和类型存储到Redis中
                    String seatRedisKey = "train:" + id + ":carriage:" + (TrainCarriageID+i);
                    Map<String, Object> seatInfo = new HashMap<>();
                    seatInfo.put("totalSeats", 118);
                    seatInfo.put("seatType", "硬座");
                    redisUtil.hmset(seatRedisKey, seatInfo);
                }
            }
            for (int i = 0; i < hardSleeperCarriages; i++) {
                long TrainCarriageID = snowflakeIdWorker.nextId();
                trainEntity = adminTrainMapper.addTrainCarriage(TrainCarriageID+i,id, hardSeatCarriages+i+3, "硬卧", 66);
                if (!ticketsAdded2) {
                    ticketMapper.addTickets(id,"硬卧", startStation, endStation);  //添加车票信息，只执行一次
                    ticketsAdded2 = true;
                }
                if (trainEntity != 0) {
                    //添加座位信息
                    for (int j = 0; j < 66; j++) {
                        long seatId = snowflakeIdWorker.nextId();
                        if (0==adminTrainMapper.addTrainSeat(seatId,TrainCarriageID+i, String.valueOf(j + 1))) {
                            result2 = false;
                        }else {
                            // 将座位信息存储到Redis中
                            String seatRedisKey = "carriageId:"+ (TrainCarriageID+i)+":seatId:"+ (seatId+j);
                            Map<String, Object> seatInfo = new HashMap<>();
                            seatInfo.put("seatType", String.valueOf(j + 1));
                            seatInfo.put("status", 0);
                            redisUtil.hmset(seatRedisKey, seatInfo);
                        }
                    }
                    // 将座位数量和类型存储到Redis中
                    String seatRedisKey = "train:" + id + ":carriage:" + (TrainCarriageID+i);
                    Map<String, Object> seatInfo = new HashMap<>();
                    seatInfo.put("totalSeats", 66);
                    seatInfo.put("seatType", "硬卧");
                    redisUtil.hmset(seatRedisKey, seatInfo);
                }
            }
            for (int i = 0; i < softSleeperCarriages; i++) {
                long TrainCarriageID = snowflakeIdWorker.nextId();
                trainEntity = adminTrainMapper.addTrainCarriage(TrainCarriageID+i,id, hardSleeperCarriages+i+6, "软卧", 36);
                if (!ticketsAdded3) {
                    ticketMapper.addTickets(id,"软卧", startStation, endStation);  //添加车票信息，只执行一次
                    ticketsAdded3 = true;
                }
                if (trainEntity != 0) {
                    //添加座位信息
                    for (int j = 0; j < 36; j++) {
                        long seatId = snowflakeIdWorker.nextId();
                        if (0==adminTrainMapper.addTrainSeat(seatId+j,TrainCarriageID+i, String.valueOf(j + 1))) {
                            result3 = false;
                        }else {
                            // 将座位信息存储到Redis中
                            String seatRedisKey = "carriageId:"+ (TrainCarriageID+i)+":seatId:"+ (seatId+j);
                            Map<String, Object> seatInfo = new HashMap<>();
                            seatInfo.put("seatType", String.valueOf(j + 1));
                            seatInfo.put("status", 0);
                            redisUtil.hmset(seatRedisKey, seatInfo);
                        }
                    }
                    // 将座位数量和类型存储到Redis中
                    String seatRedisKey = "train:" + id + ":carriage:" + (TrainCarriageID+i);
                    Map<String, Object> seatInfo = new HashMap<>();
                    seatInfo.put("totalSeats", 36);
                    seatInfo.put("seatType", "软卧");
                    redisUtil.hmset(seatRedisKey, seatInfo);
                }
            }
            if (result1 && result2 && result3) {
                result = true;
            } else {
                throw new RuntimeException("添加普通列车座位信息失败");
            }
        } else {
            throw new RuntimeException("添加普通列车信息失败");
        }
        
        return result;
    }

    //添加高铁
    private Boolean addHighSpeedTrain(Map<String, Object> train) {
        Boolean result = false;
        Boolean result1 = true;
        Boolean result2 = true;
        Boolean result3 = true;
        String departureTime = (String) train.get("departureTime");  // 出发时间
        String arrivalTime = (String) train.get("arrivalTime");   // 到达时间
        Integer startStation = (Integer) train.get("startStation");  // 出发站
        Integer endStation = (Integer) train.get("endStation");  // 到达站
        String trainType = (String) train.get("trainType");// 列车类型
        String trainNumber = (String) train.get("trainNumber");  // 车次号
        Integer status = (Integer) train.get("status");// 状态
        String serviceLife = (String) train.get("serviceLife");// 服役年限
        String remark = (String) train.get("remark");   // 备注
        String manufactureDate = (String) train.get("manufactureDate");   // 制造时间
        String lastMaintenanceDate = (String) train.get("lastMaintenanceDate"); // 最后维护时间
        Integer businessClassCarriages = (Integer) train.get("businessClassCarriages");// 商务座
        Integer secondClassCarriages = (Integer) train.get("secondClassCarriages");// 二等座
        Integer firstClassCarriages = (Integer) train.get("firstClassCarriages");// 一等座
        String runDuration = new TimeCalculationExample().calculateTimeDifference(departureTime, arrivalTime);  // 运行时长

        //雪花算法获取id
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker();
        long id = snowflakeIdWorker.nextId();
        //添加车次信息
        int trainEntity = adminTrainMapper.addTrain(id,departureTime, arrivalTime, startStation, endStation, trainType, trainNumber, status, Double.valueOf(serviceLife), remark, manufactureDate, lastMaintenanceDate, runDuration);
        if (trainEntity != 0) {
            Boolean ticketsAdded1 = false;
            Boolean ticketsAdded2 = false;
            Boolean ticketsAdded3 = false;
            //添加车厢信息
            for (int i = 0; i < businessClassCarriages; i++) {

                long TrainCarriageID = snowflakeIdWorker.nextId();
                trainEntity = adminTrainMapper.addTrainCarriage(TrainCarriageID+i,id, i+1, "商务座", 20);
                if (! ticketsAdded1) {
                    //添加车票信息
                    ticketMapper.addTickets(id,"商务座", startStation, endStation);
                    ticketsAdded1 = true;
                }
                if (trainEntity != 0) {
                    //添加座位信息
                    for (int j = 0; j < 7; j++) {
                        String types[]= {"A", "B","C"};
                        for (int type = 0; type < 3; type++){
                            long seatId = snowflakeIdWorker.nextId();
                            if (0==adminTrainMapper.addTrainSeat(seatId+j+type,TrainCarriageID+i, j + 1+types[type])) {
                                result1 = false;
                            }else {
                                // 将座位信息存储到Redis中
                                String seatRedisKey = "carriageId:"+ (TrainCarriageID+i)+":seatId:"+ (seatId+j+type);
                                Map<String, Object> seatInfo = new HashMap<>();
                                seatInfo.put("seatType", j + 1+ types[type]);
                                seatInfo.put("status", 0);
                                redisUtil.hmset(seatRedisKey, seatInfo);
                            }
                        }
                    }
                    // 将座位数量和类型存储到Redis中
                    String seatRedisKey = "train:" + id + ":carriage:" + (TrainCarriageID+i);
                    Map<String, Object> seatInfo = new HashMap<>();
                    seatInfo.put("totalSeats", 21); // 7 * 3 = 21个座位
                    seatInfo.put("seatType", "商务座");
                    redisUtil.hmset(seatRedisKey, seatInfo);
                }
            }
            for (int i = 0; i < secondClassCarriages; i++) {
                long TrainCarriageID = snowflakeIdWorker.nextId();
                trainEntity = adminTrainMapper.addTrainCarriage(TrainCarriageID+i,id, businessClassCarriages+i+3, "二等座", 70);
                if (! ticketsAdded2) {
                    //添加车票信息
                    ticketMapper.addTickets(id,"二等座", startStation, endStation);
                    ticketsAdded2 = true;
                }
                if (trainEntity != 0) {
                    //添加座位信息
                    for (int j = 0; j < 14; j++) {
                        String types[]= {"A", "B","C","D","F"};
                        for (int type = 0; type < 5; type++){
                            long seatId = snowflakeIdWorker.nextId();
                            if (0==adminTrainMapper.addTrainSeat(seatId+j+type,TrainCarriageID+i, j + 1+types[type])) {
                                result2 = false;
                            }else {
                                // 将座位信息存储到Redis中
                                String seatRedisKey = "carriageId:"+ (TrainCarriageID+i)+":seatId:"+ (seatId+j+type);
                                Map<String, Object> seatInfo = new HashMap<>();
                                seatInfo.put("seatType", j + 1+ types[type]);
                                seatInfo.put("status", 0);
                                redisUtil.hmset(seatRedisKey, seatInfo);
                            }
                        }
                    }
                    // 将座位数量和类型存储到Redis中
                    String seatRedisKey = "train:" + id + ":carriage:" + (TrainCarriageID+i);
                    Map<String, Object> seatInfo = new HashMap<>();
                    seatInfo.put("totalSeats", 70); // 14 * 5 = 70个座位
                    seatInfo.put("seatType", "二等座");
                    redisUtil.hmset(seatRedisKey, seatInfo);
                }
            }
            for (int i = 0; i < firstClassCarriages; i++) {
                long TrainCarriageID = snowflakeIdWorker.nextId();
                trainEntity = adminTrainMapper.addTrainCarriage(TrainCarriageID+i,id, secondClassCarriages+i+6, "一等座", 50);
                if (! ticketsAdded3) {
                    //添加车票信息
                    ticketMapper.addTickets(id,"一等座", startStation, endStation);
                    ticketsAdded3 = true;
                }
                if (trainEntity != 0) {
                    //添加座位信息
                    for (int j = 0; j < 10; j++) {
                        String types[]= {"A", "B","C","D","F"};
                        for (int type = 0; type < 5; type++){
                            long seatId = snowflakeIdWorker.nextId();
                            if (0==adminTrainMapper.addTrainSeat(seatId+j+type,TrainCarriageID+i, j + 1+ types[type])) {
                                result3 = false;
                            }else {
                               // 将座位信息存储到Redis中
                               String seatRedisKey = "carriageId:"+ (TrainCarriageID+i)+":seatId:"+ (seatId+j+type);
                               Map<String, Object> seatInfo = new HashMap<>();
                               seatInfo.put("seatType", j + 1+ types[type]);
                               seatInfo.put("status", 0);
                               redisUtil.hmset(seatRedisKey, seatInfo);
                            }
                        }
                    }
                    // 将火车该车厢剩余票数存储到Redis中
                    String seatRedisKey = "train:" + id + ":carriage:" + (TrainCarriageID+i);
                    Map<String, Object> seatInfo = new HashMap<>();
                    seatInfo.put("totalSeats", 50); // 10 * 5 = 50个座位
                    seatInfo.put("seatType", "一等座");
                    redisUtil.hmset(seatRedisKey, seatInfo);
                }
            }
            
            if (result1 && result2 && result3) {
                result = true;
            } else {
                throw new RuntimeException("添加高铁座位信息失败");
            }
        } else {
            throw new RuntimeException("添加高铁列车信息失败");
        }
        
        return result;
    }
}
