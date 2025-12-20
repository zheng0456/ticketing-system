package com.ticking.service.impl;

import com.ticking.entity.TrainCarriageEntity;
import com.etc.trainordersys.utils.SnowflakeIdWorker;
import com.ticking.entity.TrainEntity;
import com.ticking.entity.TrainStationEntity;
import com.ticking.mapper.AdminTrainMapper;
import com.ticking.service.IAdminTrainService;
import com.ticking.until.TimeCalculationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("adminTrainService")
public class AdminTrainServiceImpl implements IAdminTrainService {
    @Autowired
    AdminTrainMapper adminTrainMapper;
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
        return null;
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

        //随机数
        Random random = new Random();
        int rangeInt = random.nextInt(hardSeatCarriages + hardSleeperCarriages + softSleeperCarriages + 1);
        //雪花算法获取id
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker();
        long id = snowflakeIdWorker.nextId();
        //添加车次信息
        int trainEntity = adminTrainMapper.addTrain(id,departureTime, arrivalTime, startStation, endStation,trainType, trainNumber, status, Double.valueOf(serviceLife), remark, manufactureDate, lastMaintenanceDate, runDuration);
        if (trainEntity != 0) {
            //添加车厢信息
            for (int i = 0; i < hardSeatCarriages; i++) {
                long TrainCarriageID = snowflakeIdWorker.nextId();
                trainEntity = adminTrainMapper.addTrainCarriage(TrainCarriageID+i,id, rangeInt, "硬座", 118);
                if (trainEntity != 0) {
                    //添加座位信息
                    for (int j = 0; j < 118; j++) {
                        if (0==adminTrainMapper.addTrainSeat(TrainCarriageID+i, String.valueOf(j + 1))) {
                            result1 = false;
                        }
                    }
                }
            }
            for (int i = 0; i < hardSleeperCarriages; i++) {
                long TrainCarriageID = snowflakeIdWorker.nextId();
                trainEntity = adminTrainMapper.addTrainCarriage(TrainCarriageID+i,id, rangeInt, "硬卧", 66);
                if (trainEntity != 0) {
                    //添加座位信息
                    for (int j = 0; j < 66; j++) {
                        if (0==adminTrainMapper.addTrainSeat(TrainCarriageID+i, String.valueOf(j + 1))) {
                            result2 = false;
                        }
                    }
                }
            }
            for (int i = 0; i < softSleeperCarriages; i++) {
                long TrainCarriageID = snowflakeIdWorker.nextId();
                trainEntity = adminTrainMapper.addTrainCarriage(TrainCarriageID+i,id, rangeInt, "软卧", 36);
                if (trainEntity != 0) {
                    //添加座位信息
                    for (int j = 0; j < 36; j++) {
                        if (0==adminTrainMapper.addTrainSeat(TrainCarriageID+i, String.valueOf(j + 1))) {
                            result3 = false;
                        }
                    }
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

        //随机数
        Random random = new Random();
        int rangeInt = random.nextInt(businessClassCarriages + secondClassCarriages + firstClassCarriages + 1);
        //雪花算法获取id
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker();
        long id = snowflakeIdWorker.nextId();
        //添加车次信息
        int trainEntity = adminTrainMapper.addTrain(id,departureTime, arrivalTime, startStation, endStation, trainType, trainNumber, status, Double.valueOf(serviceLife), remark, manufactureDate, lastMaintenanceDate, runDuration);
        if (trainEntity != 0) {
            //添加车厢信息
            for (int i = 0; i < businessClassCarriages; i++) {
                long TrainCarriageID = snowflakeIdWorker.nextId();
                trainEntity = adminTrainMapper.addTrainCarriage(TrainCarriageID+i,id, rangeInt, "商务座", 20);
                if (trainEntity != 0) {
                    //添加座位信息
                    for (int j = 0; j < 7; j++) {
                        String types[]= {"A", "B","C"};
                        for (String type:types){
                            if (0==adminTrainMapper.addTrainSeat(TrainCarriageID+i, j + 1+ type)) {
                                result1 = false;
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < secondClassCarriages; i++) {
                long TrainCarriageID = snowflakeIdWorker.nextId();
                trainEntity = adminTrainMapper.addTrainCarriage(TrainCarriageID+i,id, rangeInt, "二等座", 70);
                if (trainEntity != 0) {
                    //添加座位信息
                    for (int j = 0; j < 14; j++) {
                        String types[]= {"A", "B","C","D","F"};
                        for (String type:types){
                            if (0==adminTrainMapper.addTrainSeat(TrainCarriageID+i, j + 1+ type)) {
                                result2 = false;
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < firstClassCarriages; i++) {
                long TrainCarriageID = snowflakeIdWorker.nextId();
                trainEntity = adminTrainMapper.addTrainCarriage(TrainCarriageID+i,id, rangeInt, "一等座", 50);
                if (trainEntity != 0) {
                    //添加座位信息
                    for (int j = 0; j < 10; j++) {
                        String types[]= {"A", "B","C","D","F"};
                        for (String type:types){
                            if (0==adminTrainMapper.addTrainSeat(TrainCarriageID+i, j + 1+ type)) {
                                result3 = false;
                            }
                        }
                    }
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
