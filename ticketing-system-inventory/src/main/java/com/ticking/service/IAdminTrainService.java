package com.ticking.service;

import com.ticking.entity.TrainEntity;

import java.util.List;
import java.util.Map;

public interface IAdminTrainService {
    List<TrainEntity> selectTrainMessages();

    Boolean addTrain(Map<String, Object> train);

    Boolean updateTrain(Long id, Map<String, Object> train);

    Boolean deleteTrain(Long id);
}
