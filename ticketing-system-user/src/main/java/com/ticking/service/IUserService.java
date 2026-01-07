package com.ticking.service;

import com.ticking.entity.MenuEntity;
import com.ticking.entity.UserEntity;

import java.util.List;

public interface IUserService {
    UserEntity login(String userName, String password);

    Boolean register(UserEntity user);

    List<MenuEntity> getMenuList(Long userId);
    UserEntity getUserById(Long userId);

    boolean deleteUser(Long userId);
}
