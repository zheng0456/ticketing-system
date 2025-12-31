package com.ticking.service;

import com.ticking.entity.PassionEntity;

import java.util.List;

public interface IPassionService {
    Boolean addPassion(PassionEntity passion);

    List<PassionEntity> queryPassion(Long userId);
}
