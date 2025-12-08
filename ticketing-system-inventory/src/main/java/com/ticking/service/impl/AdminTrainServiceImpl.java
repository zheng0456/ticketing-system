package com.ticking.service.impl;

import com.ticking.entity.TrainCarriageEntity;
import com.ticking.entity.TrainEntity;
import com.ticking.mapper.AdminTrainMapper;
import com.ticking.service.IAdminTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("adminTrainService")
public class AdminTrainServiceImpl implements IAdminTrainService {
    @Autowired
    AdminTrainMapper adminTrainMapper;
    /**
     * 查询车次信息
     */
    @Override
    public List<TrainEntity> selectTrainMessages() {
        int sum=0;
        List<TrainEntity> data=null;
        List<TrainEntity> trains=adminTrainMapper.selectAllTrain();
        for (TrainEntity train:trains){
            List<TrainCarriageEntity> trainCarriage= adminTrainMapper.selectAllCarriages(train.getId());
            for (TrainCarriageEntity carriage:trainCarriage){
                sum+= carriage.getTotalSeats();   // 总座位数相加 = 一辆列车总载客量
                train.setSum(sum);
                data.add(train);
            }
        }
        return data;
    }
}
