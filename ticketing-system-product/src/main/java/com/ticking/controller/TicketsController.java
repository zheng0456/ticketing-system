package com.ticking.controller;

import com.ticking.entity.RestUtil;
import com.ticking.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class TicketsController {
    @Autowired
    ITicketService ticketService;
    /**
     * 获取车票信息
     */
    @PostMapping("/tickets")
    public @ResponseBody RestUtil queryTickets(@RequestBody Map<String,Object> tickets) {
        List<Map<String,Object>> data=ticketService.selectAllTickets(tickets);
        if (data!=null){
            return RestUtil.success(data);
        }
        return RestUtil.error("暂无数据");
    }
    /**
     * 获取车票 的座位信息 以及座位的数量
     */
    @PostMapping("/ticketMessages")
    public @ResponseBody RestUtil queryTicketMessages(@RequestBody Map<String,Object> request) {
        String trainIdStr = (String) request.get("trainId");
        Long ticketsId = null;
        try {
            ticketsId = Long.valueOf(trainIdStr);
        } catch (NumberFormatException e) {
            return RestUtil.error("参数错误：trainId必须为数字");
        }
        List<Map<String,Object>> data=ticketService.selectTicketMessages(ticketsId);
        if (data!=null && !data.isEmpty()){
            return RestUtil.success(data);
        }
        return RestUtil.error("暂无数据");
    }
}
