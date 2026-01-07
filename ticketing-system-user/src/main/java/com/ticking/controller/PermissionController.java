package com.ticking.controller;

import com.ticking.entity.PermissionEntity;
import com.ticking.entity.RestUtil;
import com.ticking.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class PermissionController {
    @Autowired
    IPermissionService permissionService;

    /**
     * 获取添加用户 权限列表
     */
    @PostMapping("/permission/add")
    public @ResponseBody RestUtil getAddUserPermissionList(@RequestBody PermissionEntity  user) {
        boolean result =permissionService.insertUser(user);
        if (result == true) {
            return RestUtil.success();
        } else {
            return RestUtil.error("添加失败");
        }
    }

    /**
     * 查询 用户权限列表
     */
    @PostMapping("/permission/list")
    public @ResponseBody RestUtil getUserPermissionList() {
        List<Map<String,Object>> list = permissionService.getUserPermissionList();
        if (list != null){
            return RestUtil.success(list);
        }else {
            return RestUtil.error("查询失败");
        }
    }
    /**
     * 修改编辑用户状态 和 角色
     */
    @PostMapping("/permission/update")
    public @ResponseBody RestUtil updateUser(@RequestBody PermissionEntity user) {
        boolean result = permissionService.updateUser(user);
        if (result == true) {
            return RestUtil.success();
        } else {
            return RestUtil.error("修改失败");
        }
    }

    /**
     * 删除用户
     */
    @PostMapping("/permission/delete")
    public @ResponseBody RestUtil deleteUser(@RequestBody  Map<String,Object> id) {
        String userId= (String) id.get("user_id");
        boolean result = permissionService.deleteUser(userId);
        if (result == true) {
            return RestUtil.success();
        } else {
            return RestUtil.error("删除失败");
        }
    }
}
