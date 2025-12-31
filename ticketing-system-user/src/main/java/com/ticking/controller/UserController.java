package com.ticking.controller;

import com.ticking.entity.MenuEntity;
import com.ticking.entity.RestUtil;
import com.ticking.entity.UserEntity;
import com.ticking.service.IUserService;
import com.ticking.utility.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    IUserService userService;
    /**
     * 登录
     */
    @PostMapping("/login")
    public @ResponseBody RestUtil login(@RequestBody UserEntity users) {
        UserEntity user=userService.login(users.getUserName(), users.getPassword());
        if (user!=null){
            // 判断用户权限 访问的页面
            Long userId = user.getUserId();
            List<MenuEntity> menuList= userService.getMenuList(userId);
            
            // 生成JWT token，替代session存储
            String token = JwtUtil.generateToken(userId, user.getUserName());
            
            // 将菜单列表和token一起返回给前端
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("menuList", menuList);
            responseData.put("token", token);
            responseData.put("user", user);
            
            return RestUtil.success(responseData);
        }else {
            return RestUtil.error("用户名或密码错误");
        }
    }

    /**
     * 获取当前用户信息
     */
    @PostMapping("/getCurrentUser")
    public @ResponseBody RestUtil getCurrentUser(HttpServletRequest request) {
        // 从request中获取通过拦截器设置的当前用户信息
        UserEntity currentUser = (UserEntity) request.getAttribute("currentUser");
        if (currentUser != null) {
            return RestUtil.success(currentUser);
        } else {
            return RestUtil.error("未登录");
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