package com.ticking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CreateOrderController {
    /**
     * 创建订单
     */
    @PostMapping("/createOrder")
    public String createOrder() {
        return "createOrder";
    }
}
