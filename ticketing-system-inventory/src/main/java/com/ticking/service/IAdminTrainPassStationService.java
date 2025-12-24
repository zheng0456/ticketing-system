package com.ticking.service;

import com.ticking.entity.TrainPassStationEntity;

import java.util.List;
import java.util.Map;

public interface IAdminTrainPassStationService {
    Boolean addTrainPassStation(Map<String, Object> trainPassStation);

    List<TrainPassStationEntity> selectAllTrainPassStation();

    Boolean updateTrainPassStation(Map<String, Object> trainPassStation);
}
