package com.ticking.controller;

import com.ticking.entity.PassionEntity;
import com.ticking.entity.RestUtil;
import com.ticking.entity.UserEntity;
import com.ticking.service.IPassionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PassionController {

    @Autowired
    IPassionService passionService;

    /**
     * 展示用户乘车人
     */
    @PostMapping("/passenger/list")
    public @ResponseBody RestUtil queryPassion(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getAttribute("currentUser");
        if (user == null) {
            return RestUtil.error("未登录或登录已过期");
        }
        Long userId = user.getUserId();
        List<PassionEntity> data = passionService.queryPassion(userId);
        return RestUtil.success(data);
    }

    /**
     * 添加用户乘车人
     */
    @PostMapping("/passenger/add")
    public @ResponseBody RestUtil addPassion(@RequestBody PassionEntity passion, HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getAttribute("currentUser");
        if (user == null) {
            return RestUtil.error("未登录或登录已过期");
        }
        Long userId = user.getUserId();
        // 设置passion对象的userId，以便关联到当前用户
        passion.setUserId(userId);
        Boolean result = passionService.addPassion(passion);
        if (result == true) {
            return RestUtil.success();
        } else {
            return RestUtil.error("添加失败");
        }
    }
    /**
     * 修改用户乘车人
     */
    @PostMapping("/passenger/update")
    public @ResponseBody RestUtil updatePassion(@RequestBody PassionEntity passion) {
        Boolean result = passionService.updatePassion(passion);
        if (result == true) {
            return RestUtil.success();
        } else {
            return RestUtil.error("修改失败");
        }
    }
}
