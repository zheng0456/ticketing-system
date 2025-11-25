package com.ticking.controller;

import com.ticking.entity.RestUtil;
import com.ticking.entity.UserEntity;
import com.ticking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    IUserService userService;
    /**
     * 登录
     */
    @PostMapping("/login")
    public RestUtil login(@RequestBody UserEntity user) {
        Boolean result=userService.login(user);
        if (result==true){
            return RestUtil.success();
        }else {
            return RestUtil.error("用户名或密码错误");
        }
    }

    @PostMapping("/register")
    public RestUtil register(@RequestBody UserEntity user) {
        Boolean result=userService.register(user);
        if (result==true){
            return RestUtil.success();
        }else {
            return RestUtil.error("用户已存在");
        }
    }
}