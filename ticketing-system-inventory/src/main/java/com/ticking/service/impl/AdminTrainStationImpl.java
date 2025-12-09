package com.ticking.service.impl;

import com.ticking.entity.TrainStationEntity;
import com.ticking.mapper.AdminTrainMapper;
import com.ticking.mapper.AdminTrainStationMapper;
import com.ticking.service.IAdminTrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    /**
     * 修改车次站点
     */
    @Override
    public Boolean updateTrainStation(Long id, Map<String,Object> trainStation) {
        Boolean result=false;
        Integer statusCode=0;
        if (id!=null){
            String stationName=(String) trainStation.get("stationName");
            String province=(String) trainStation.get("province");
            String city=(String) trainStation.get("city");
            String status=(String) trainStation.get("status");
            if (!status.isEmpty()){
                statusCode=Integer.parseInt(status);
            }
            String address=(String) trainStation.get("address");
            String vote=(String) trainStation.get("vote");
            String createTime=(String) trainStation.get("createTime");
            result=adminTrainStationMapper.updateTrainStation(id,stationName,province,city,statusCode,address,vote,createTime);
        }else {
            return false;
        }
        return result;
    }

    /**
     * 删除车次站点
     */
    @Override
    public Boolean deleteTrainStation(Long id) {
        Boolean result=false;
        if (id!=null){
            result=adminTrainStationMapper.deleteTrainStation(id);
        }
        else {
            result=false;
        }
        return result;
    }

    /**
     * 批量删除车次站点
     */
    @Override
    public Boolean deleteTrainStations(Long[] id) {
        Boolean result=false;
        if (id!=null){
            for (Long i:id){
                result=adminTrainStationMapper.deleteTrainStation(i);
            }
        }
        return result;
    }
}
