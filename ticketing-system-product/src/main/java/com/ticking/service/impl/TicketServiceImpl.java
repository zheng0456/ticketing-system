package com.ticking.service.impl;

import com.ticking.mapper.TicketsMapper;
import com.ticking.service.ITicketService;
import com.ticking.until.TimeCalculationExample;
import com.ticking.until.TimeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("ticketService")
public class TicketServiceImpl implements ITicketService {
    @Autowired
    TicketsMapper ticketsMapper;
    /**
     * 查询所有车票信息
     */
    @Override
    public List<Map<String, Object>> selectAllTickets(Map<String, Object> tickets) {
        String departure = (String) tickets.get("departure");   //出发站
        String destination = (String) tickets.get("destination");   //终点站
        String departureTime = (String) tickets.get("departDate");  //出发时间
        String arrivalTime = (String) tickets.get("arrivalTime");   //到达时间

        List<Map<String, Object>> ticket=ticketsMapper.selectAllTickets(destination, departure, departureTime, arrivalTime);
        for (Map<String, Object> ticket1 : ticket){
            // 处理时间字段，可能为LocalDateTime或String类型
            Object startTimeObj = ticket1.get("start_time");
            Object endTimeObj = ticket1.get("end_time");
            String start_time = TimeObject.timeFormat(startTimeObj);
            String end_time = TimeObject.timeFormat(endTimeObj);
            String time= new TimeCalculationExample().calculateTimeDifference(start_time, end_time);  //耗时
            Long tran_id = (Long) ticket1.get("id");
            List<Map<String, Object>> trainCarriage=ticketsMapper.selectTrainCarriage(tran_id);
            List<Map <String, Object>>carriage=new ArrayList<>();
            for (Map<String, Object> trainCarriage1 : trainCarriage){
                String  seat_type = (String) trainCarriage1.get("seat_type");
                BigDecimal num = (BigDecimal) trainCarriage1.get("num");
                carriage.add(Map.of("seat_type",seat_type,"num",num));
                ticket1.put("carriage",carriage);
            }
            ticket1.put("time",time);
        }
        return ticket;
    }

    /**
     * 查询车票 座位 信息
     */
    @Override
    public List<Map<String, Object>> selectTicketMessages(Long ticketsId) {
        List<Map<String, Object>> ticket=ticketsMapper.selectTicketMessages(ticketsId);
        return ticket;
    }
}
