package com.ticking.service.impl;

import com.ticking.entity.PermissionEntity;
import com.ticking.mapper.PermissionMapper;
import com.ticking.service.IPermissionService;
import com.ticking.utility.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    /**
     * 添加用户权限
     */
    @Override
    @Transactional
    public boolean insertUser(PermissionEntity user) {
        boolean result = false;
        //获取当前时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        String time = currentTime.format(formatter);

        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker();
        long id = snowflakeIdWorker.nextId();
        //添加用户表  user
        result = permissionMapper.insertUser(id, user.getUserName(), user.getPassword(), user.getPhone(), time);
        //获取用户权限
        String[] roles = user.getRole();
        if (roles.length <= 0) {
            throw new RuntimeException("用户权限不能为空");
        }
        for (String role : roles) {
            //插入用户权限表  user_role
            result = permissionMapper.insertUserRole(id, Long.valueOf(role));
        }
        return result;
    }

    /**
     * 获取用户权限列表
     */
    @Override
    public List<Map<String,Object>> getUserPermissionList() {
        return permissionMapper.getUserPermissionList();
    }

    /**
     * 修改用户
     */
    @Override
    public boolean updateUser(PermissionEntity user) {
        boolean result = false;
        if ("".equals(user.getStatus()) || user.getStatus() == null || user.getStatus().isEmpty()){
            //编辑下的修改
            result=updateUserMessages(user);
        }else {
            //编辑用户状态
            result=updateUserStatus(user);
        }
        return result;
    }

    /**
     * 删除用户
     */
    @Override
    public boolean deleteUser(String userId) {
        boolean result =permissionMapper.deleteUser(Long.valueOf(userId));
        result=permissionMapper.deleteUserRole(Long.valueOf(userId));
        return result;
    }

    /**
     * 修改用户状态
     */
    private boolean updateUserStatus(PermissionEntity user) {
        return permissionMapper.updateUserStatus(Long.valueOf(user.getUser_id()),Integer.valueOf(user.getStatus()));
    }

    /**
     * 修改用户信息
     */
    private boolean updateUserMessages(PermissionEntity user) {
        boolean result = permissionMapper.updateUserMessages(Long.valueOf(user.getUser_id()),user.getPhone());
        result=permissionMapper.deleteUserRole(Long.valueOf(user.getUser_id()));
        for (String role : user.getRole()){
            result = permissionMapper.insertUserRole(Long.valueOf(user.getUser_id()), Long.valueOf(role));
        }
        return result;
    }
}
