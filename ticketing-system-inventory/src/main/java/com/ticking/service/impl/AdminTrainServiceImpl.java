package com.ticking.service.impl;

import com.ticking.entity.TrainCarriageEntity;
import com.ticking.entity.TrainEntity;
import com.ticking.mapper.AdminTrainMapper;
import com.ticking.service.IAdminTrainService;
import com.ticking.until.TimeCalculationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimerTask;

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
        return trains;
    }

    /**
     * 添加车次信息
     */
    @Override
    public Boolean addTrain(Map<String, Object> train) {
        Boolean result = false;
        Boolean result1 = false;
        Boolean result2 = false;
        Boolean result3 = false;
        TrainCarriageEntity trainCarriageEntity = new TrainCarriageEntity();
        String departureTime = (String) train.get("departureTime");  // 出发时间
        String arrivalTime = (String) train.get("arrivalTime");   // 到达时间
        Integer startStation = (Integer) train.get("startStation");  // 出发站
        Integer endStation = (Integer) train.get("endStation");  // 到达站
        String intermediateStations = (String) train.get("intermediateStations");  // 中转站
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

        String runDuration = new TimeCalculationExample().calculateTimeDifference(departureTime, arrivalTime);
        if (businessClassCarriages == 0 && secondClassCarriages == 0 && firstClassCarriages == 0) {
            //普通列车 和 动车

            Integer hardSeatCarriages = (Integer) train.get("hardSeatCarriages");// 硬座
            Integer hardSleeperCarriages = (Integer) train.get("hardSleeperCarriages");  // 硬卧
            Integer softSleeperCarriages = (Integer) train.get("softSleeperCarriages");  // 软卧


            //随机数
            Random random = new Random();
            int rangeInt = random.nextInt(hardSeatCarriages + hardSleeperCarriages + softSleeperCarriages + 1);
            //添加车次信息
            TrainEntity trainEntity = adminTrainMapper.addTrain(departureTime, arrivalTime, startStation, endStation, intermediateStations, trainType, trainNumber, status, serviceLife, remark, manufactureDate, lastMaintenanceDate, runDuration);
            if (trainEntity != null) {
                //添加车厢信息
                for (int i = 0; i < hardSeatCarriages; i++) {
                    trainCarriageEntity = adminTrainMapper.addTrainCarriage(trainEntity.getId(), rangeInt, "硬座", 118);
                    if (trainCarriageEntity != null) {
                        //添加座位信息
                        for (int j = 0; j < 118; j++) {
                            result1 = adminTrainMapper.addTrainSeat(trainCarriageEntity.getId(), j + 1);
                        }
                    }
                }
                for (int i = 0; i < hardSleeperCarriages; i++) {
                    trainCarriageEntity = adminTrainMapper.addTrainCarriage(trainEntity.getId(), rangeInt, "硬卧", 66);
                    if (trainCarriageEntity != null) {
                        //添加座位信息
                        for (int j = 0; j < 66; j++) {
                            result2 = adminTrainMapper.addTrainSeat(trainCarriageEntity.getId(), j + 1);
                        }
                    }
                }
                for (int i = 0; i < softSleeperCarriages; i++) {
                    trainCarriageEntity = adminTrainMapper.addTrainCarriage(trainEntity.getId(), rangeInt, "软卧", 36);
                    if (trainCarriageEntity != null) {
                        //添加座位信息
                        for (int j = 0; j < 36; j++) {
                            result3 = adminTrainMapper.addTrainSeat(trainCarriageEntity.getId(), j + 1);
                        }
                    }
                }
                if (result1 == true && result2 == true && result3 == true) {
                    result = true;
                }
            }
        } else {
            //高铁
            //随机数
            Random random = new Random();
            int rangeInt = random.nextInt(businessClassCarriages + secondClassCarriages + firstClassCarriages + 1);
            //添加车次信息
            TrainEntity trainEntity = adminTrainMapper.addTrain(departureTime, arrivalTime, startStation, endStation, intermediateStations, trainType, trainNumber, status, serviceLife, remark, manufactureDate, lastMaintenanceDate, runDuration);
            if (trainEntity != null) {
                //添加车厢信息
                for (int i = 0; i < businessClassCarriages; i++) {
                    trainCarriageEntity = adminTrainMapper.addTrainCarriage(trainEntity.getId(), rangeInt, "商务座", 20);
                    if (trainCarriageEntity != null) {
                        //添加座位信息
                        for (int j = 0; j < 20; j++) {
                            String type[]= {"A", "B","C"};
                            result1 = adminTrainMapper.addTrainSeat(trainCarriageEntity.getId(), j + 1);
                        }
                    }
                }
                for (int i = 0; i < secondClassCarriages; i++) {
                    trainCarriageEntity = adminTrainMapper.addTrainCarriage(trainEntity.getId(), rangeInt, "二等座", 70);
                    if (trainCarriageEntity != null) {
                        //添加座位信息
                        for (int j = 0; j < 70; j++) {
                            result2 = adminTrainMapper.addTrainSeat(trainCarriageEntity.getId(), j + 1);
                        }
                    }
                }
                for (int i = 0; i < firstClassCarriages; i++) {
                    trainCarriageEntity = adminTrainMapper.addTrainCarriage(trainEntity.getId(), rangeInt, "一等座", 50);
                    if (trainCarriageEntity != null) {
                        //添加座位信息
                        for (int j = 0; j < 50; j++) {
                            result3 = adminTrainMapper.addTrainSeat(trainCarriageEntity.getId(), j + 1);
                        }
                    }
                }
                if (result1 == true && result2 == true && result3 == true) {
                    result = true;
                }
            }
        }
        return result;
    }
}
