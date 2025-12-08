package com.ticking.service.impl;

import com.ticking.entity.UserEntity;
import com.ticking.mapper.UserMapper;
import com.ticking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service("userService")
public class UserServiceImpl  implements IUserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 登录
     */
    @Override
    public UserEntity login(String userName, String password) {
        UserEntity user=userMapper.login(userName, password);
        return user;
    }

    /**
     * 注册
     */
    @Override
    public Boolean register(UserEntity user) {
        // 判断用户名是否已存在
        UserEntity result=userMapper.login(user.getUserName(),user.getPassword());
        if (result==null){
            // 获取当前时间
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataTime = now.format(formatter);
            Boolean users=userMapper.register(user.getUserName(),user.getPassword(),user.getPhone(),dataTime);
            return users;
        }else {
            return false;
        }
    }
}
