package com.ticking.service;

import com.ticking.entity.UserEntity;

public interface IUserService {
    UserEntity login(String userName, String password);

    Boolean register(UserEntity user);
}
