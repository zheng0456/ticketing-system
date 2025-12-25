package com.ticking.controller;

import com.ticking.entity.RestUtil;
import com.ticking.service.ITicketService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class TicketController {
    @Autowired
    ITicketService ticketService;

    /**
     * 获取车票信息
     */
    @PostMapping("/admin/ticket")
    public @ResponseBody RestUtil selectAllTickets() {
        List<Map<String,Object>>tickets=ticketService.selectAllTickets();
        if (tickets!=null){
            return RestUtil.success(tickets);
        }else {
            return RestUtil.error("获取车票信息失败");
        }
    }
    /**
     * 修改 车票信息
     */
    @PostMapping("/admin/ticket/updata")
    public @ResponseBody RestUtil updateTicket(@RequestBody Map<String,Object> ticket) {
        Boolean result=ticketService.updateTicket(ticket);
        if (result){
            return RestUtil.success();
        }else {
            return RestUtil.error("修改车票信息失败");
        }
    }
}
