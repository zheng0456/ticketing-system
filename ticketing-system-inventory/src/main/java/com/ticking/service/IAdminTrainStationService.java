package com.ticking.service;

import com.ticking.entity.TrainStationEntity;

import java.util.List;
import java.util.Map;

public interface IAdminTrainStationService {
    List<TrainStationEntity> selectAllTrainStation();

    Boolean addTrainStation(TrainStationEntity trainStation);

    Boolean updateTrainStation(Long id, Map<String,Object> trainStation);
}
