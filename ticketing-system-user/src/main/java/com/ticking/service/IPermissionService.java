package com.ticking.service;

import com.ticking.entity.PermissionEntity;

import java.util.List;
import java.util.Map;

public interface IPermissionService {
    boolean insertUser(PermissionEntity user);

    List<Map<String,Object>> getUserPermissionList();

    boolean updateUser(PermissionEntity user);

    boolean deleteUser(String userId);
}
