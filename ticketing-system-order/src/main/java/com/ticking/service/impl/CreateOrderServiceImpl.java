package com.ticking.service.impl;

import com.ticking.entity.TicketItem;
import com.ticking.entity.TrainTicketDTO;
import com.ticking.mapper.CreateOrderMapper;
import com.ticking.service.ICreateOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("createOrderService")
public class CreateOrderServiceImpl implements ICreateOrderService {

    @Autowired
    CreateOrderMapper createOrderMapper;

    /**
     * 创建订单
     */
    @Override
    public boolean createOrder(TrainTicketDTO trainTicketDTO) {
        String trainIds = trainTicketDTO.getTrainId();
        List<TicketItem> ticketList = trainTicketDTO.getTicketList();
        Long trainId = Long.valueOf(trainIds);
        return false;
    }
}
