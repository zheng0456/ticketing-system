package com.ticking.controller;


import com.ticking.entity.RestUtil;
import com.ticking.entity.TrainTicketDTO;
import com.ticking.service.ICreateOrderService;
import com.ticking.utility.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CreateOrderController {
    @Autowired
    ICreateOrderService createOrderService;
    /**
     * 创建订单
     */
    @PostMapping("/trainOrder/createOrder")
    public @ResponseBody RestUtil createOrder(@RequestBody TrainTicketDTO trainTicketDTO, HttpServletRequest request) {
        Long userId = UserUtil.getCurrentUserId(request);
        if (userId == null) {
            return RestUtil.error("未登录或登录已过期");
        }
        String orderNo =createOrderService.createOrder(trainTicketDTO,userId);
        if (!orderNo.isEmpty()) {
            return RestUtil.success(orderNo);
        } else {
            return RestUtil.error("创建订单失败");
        }
    }
}
