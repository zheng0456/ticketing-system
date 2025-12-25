package com.ticking.service.impl;

import com.ticking.mapper.AdminTrainMapper;
import com.ticking.mapper.TicketMapper;
import com.ticking.service.ITicketService;
import com.ticking.until.TimeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service("ticketService")
public class TicketServiceImpl implements ITicketService {
    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    AdminTrainMapper adminTrainMapper;
    /**
     * 查询所有车票信息
     */
    @Override
    public List<Map<String, Object>> selectAllTickets() {
        List<Map<String,Object>> tickets=ticketMapper.selectAllTickets(); //查询所有票
        for (Map<String,Object> ticket:tickets) {
            Object startTimeObj = ticket.get("start_time");
            Object endTimeObj = ticket.get("end_time");
            //时间格式化
            String startTime = TimeObject.timeFormat(startTimeObj);
            String endTime = TimeObject.timeFormat(endTimeObj);
            
            ticket.put("start_time",startTime);
            ticket.put("end_time",endTime);
        }
        return tickets;
    }

    /**
     * 修改车票信息
     */
    @Override
    public Boolean updateTicket(Map<String, Object> ticket) {
        String id = (String) ticket.get("id");
        String price = (String) ticket.get("price");
        BigDecimal adultPrice = new BigDecimal(price);
        Boolean result = ticketMapper.updateTicket(Long.valueOf(id),adultPrice,adultPrice.multiply(new BigDecimal(0.5)),adultPrice.multiply(new BigDecimal(0.75)));
        return result;
    }
}
