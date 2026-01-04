package com.ticking.controller;


import com.ticking.entity.RestUtil;
import com.ticking.entity.TrainTicketDTO;
import com.ticking.entity.UserEntity;
import com.ticking.service.ICreateOrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class CreateOrderController {
    @Autowired
    ICreateOrderService createOrderService;
    /**
     * 创建订单
     */
    @PostMapping("/createOrder")
    public RestUtil createOrder(@RequestBody TrainTicketDTO trainTicketDTO, HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getAttribute("currentUser");
        if (user == null) {
            return RestUtil.error("未登录或登录已过期");
        }
        Long userId = user.getUserId();
        boolean result =createOrderService.createOrder(trainTicketDTO);
        if (result == true) {
            return RestUtil.success();
        } else {
            return RestUtil.error("创建订单失败");
        }
    }
}
