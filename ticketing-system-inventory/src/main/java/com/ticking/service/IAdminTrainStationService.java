package com.ticking.service;

import com.ticking.entity.TrainStationEntity;

import java.util.List;

public interface IAdminTrainStationService {
    List<TrainStationEntity> selectAllTrainStation();

    Boolean addTrainStation(TrainStationEntity trainStation);
}
