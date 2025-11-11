package com.ticking.controller;

import com.ticking.entity.UserEntity;
import com.ticking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    IUserService userService;
    /**
     * 登录
     */
    @PostMapping("/login")
    public String login(UserEntity user) {
        UserEntity users=userService.login(user);
        return "/index";
    }
}
