package com.ticking.service;

import com.ticking.entity.UserEntity;

public interface IUserService {
    Boolean login(UserEntity user);

    Boolean register(UserEntity user);
}
