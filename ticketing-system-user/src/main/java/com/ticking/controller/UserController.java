package com.ticking.controller;

import com.ticking.entity.MenuEntity;
import com.ticking.entity.RestUtil;
import com.ticking.entity.UserEntity;
import com.ticking.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
            // 判断用户权限 访问的页面
            Long userId = user.getUserId();
            List<MenuEntity> menuList= userService.getMenuList(userId);
            session.setAttribute("menuList",menuList);
            session.setAttribute("user",user);
            return RestUtil.success(menuList);
        }else {
            return RestUtil.error("用户名或密码错误");
        }
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