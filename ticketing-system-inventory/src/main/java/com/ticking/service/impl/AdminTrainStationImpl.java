package com.ticking.service.impl;

import com.ticking.entity.TrainStationEntity;
import com.ticking.mapper.AdminTrainMapper;
import com.ticking.mapper.AdminTrainStationMapper;
import com.ticking.service.IAdminTrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminTrainStationService")
public class AdminTrainStationImpl implements IAdminTrainStationService {

    @Autowired
    AdminTrainStationMapper adminTrainStationMapper;

    /**
     * 查询所有车次站点
     */
    @Override
    public List<TrainStationEntity> selectAllTrainStation() {
        List<TrainStationEntity> data=adminTrainStationMapper.selectAllTrainStation();
        return data;
    }

    /**
     * 添加车次站点
     */
    @Override
    public Boolean addTrainStation(TrainStationEntity trainStation) {
        Boolean result=adminTrainStationMapper.addTrainStation(trainStation);
        return result;
    }
}
