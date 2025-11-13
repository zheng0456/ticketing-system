package com.ticking.service.impl;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.ticking.entity.UserEntity;
import com.ticking.mapper.UserMapper;
import com.ticking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl  implements IUserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public UserEntity login(String user) {
        UserEntity userEntity = userMapper.login(user);
        return userEntity;
    }
}
