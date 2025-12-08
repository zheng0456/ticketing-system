package com.ticking.controller;

import com.ticking.entity.RestUtil;
import com.ticking.entity.UserEntity;
import com.ticking.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    IUserService userService;
    /**
     * 登录
     */
    @PostMapping("/login")
    public @ResponseBody RestUtil login(@RequestBody UserEntity users, HttpSession  session) {
        UserEntity user=userService.login(users.getUserName(), users.getPassword());
        if (user!=null){
            // 判断用户权限 1：普通用户  2：管理员
            if (user.getUserQxCode()==1){
                session.setAttribute("user",user);
                return RestUtil.success("/index",user);
            }else if (user.getUserQxCode()==2){
                session.setAttribute("user",user);
                return RestUtil.success("/admin",user);
            }
        }else {
            return RestUtil.error("用户名或密码错误");
        }
        return RestUtil.error("用户名或密码错误");
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public @ResponseBody RestUtil register(@RequestBody UserEntity user) {
        Boolean result=userService.register(user);
        if (result==true){
            return RestUtil.success();
        }else {
            return RestUtil.error("用户已存在");
        }
    }
}