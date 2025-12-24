package com.ticking.service.impl;

import com.ticking.entity.TrainPassStationEntity;
import com.ticking.mapper.AdminTrainMapper;
import com.ticking.mapper.AdminTrainPassStationMapper;
import com.ticking.mapper.AdminTrainStationMapper;
import com.ticking.service.IAdminTrainPassStationService;
import com.ticking.until.TimeCalculationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("adminTrainPassStationService")
public class AdminTrainPassStationServiceImpl implements IAdminTrainPassStationService {
    @Autowired
    AdminTrainPassStationMapper adminTrainPassStationMapper;
    @Autowired
    AdminTrainMapper adminTrainMapper;
    @Autowired
    AdminTrainStationMapper adminTrainStationMapper;
    /**
     * 添加途径站点
     */
    @Override
    public Boolean addTrainPassStation(Map<String, Object> trainPassStation) {
        String arrivalTime = (String) trainPassStation.get("arrivalTime");
        String departureTime = (String) trainPassStation.get("departureTime");
        String passStationOrder = (String) trainPassStation.get("passStationOrder");  //途径顺序
        Integer stationId = (Integer) trainPassStation.get("stationId");   //站点id
        String trainId = (String) trainPassStation.get("trainId");
        String time = new TimeCalculationExample().calculateTimeDifference(arrivalTime,departureTime);
        Boolean result= adminTrainPassStationMapper.addTrainPassStation(Long.valueOf(trainId), Long.valueOf(stationId),passStationOrder,departureTime,arrivalTime,time);
        return result;
    }

    /**
     * 查询所有途径站点
     */
    @Override
    public List<TrainPassStationEntity> selectAllTrainPassStation() {
        List<TrainPassStationEntity> datas=adminTrainPassStationMapper.selectAllTrainPassStation();
        for (TrainPassStationEntity data:datas){
            String trainNumber = adminTrainMapper.selectTrainNumber(data.getTrainId());
            String stationName = adminTrainStationMapper.selectStationName(data.getStationId());
            data.setTrainNumber(trainNumber);
            data.setStationName(stationName);
        }
        return datas;
    }

    /**
     * 修改途径车站站点
     */
    @Override
    public Boolean updateTrainPassStation(Map<String, Object> trainPassStation) {
        String arrivalTime = (String) trainPassStation.get("arrivalTime");
        String departureTime = (String) trainPassStation.get("departureTime");
        String passStationOrder = (String) trainPassStation.get("passStationOrder");  //途径顺序
        Integer stationId = (Integer) trainPassStation.get("stationId");   //站点id
        String trainId = (String) trainPassStation.get("trainId");
        Integer passStationId= (Integer) trainPassStation.get("passStationId");
        String time = new TimeCalculationExample().calculateTimeDifference(arrivalTime,departureTime);
        Boolean result=adminTrainPassStationMapper.updateTrainPassStation(Long.valueOf(trainId), Long.valueOf(stationId),passStationOrder,departureTime,arrivalTime,time, Long.valueOf(passStationId));
        return result;
    }

    /**
     *  删除接口
     */
    @Override
    public Boolean deleteTrainPassStation(Long i) {
        Boolean result= adminTrainPassStationMapper.deleteTrainPassStation(i);
        return result;
    }
}
