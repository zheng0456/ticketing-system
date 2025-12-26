package com.ticking.service.impl;

import com.ticking.entity.MenuEntity;
import com.ticking.entity.UserEntity;
import com.ticking.mapper.MenuMapper;
import com.ticking.mapper.UserMapper;
import com.ticking.service.IUserService;
import com.ticking.utility.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service("userService")
public class UserServiceImpl  implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MenuMapper menuMapper;

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
    @Transactional // 开启事务
    @Override
    public Boolean register(UserEntity user) {
        // 判断用户名是否已存在
        UserEntity result=userMapper.login(user.getUserName(),user.getPassword());
        if (result==null){
            // 获取当前时间
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataTime = now.format(formatter);
            SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker();
            long userId = snowflakeIdWorker.nextId();
            Boolean users=userMapper.register(userId,user.getUserName(),user.getPassword(),user.getPhone(),dataTime);
            if (users==true){
                userMapper.addUserRole(userId,1);
            }
            return users;
        }else {
            return false;
        }
    }

    @Override
    public List<MenuEntity> getMenuList(Long userId) {
        List<MenuEntity> menuList=menuMapper.getMenuList(userId);
        return menuList;
    }
}
