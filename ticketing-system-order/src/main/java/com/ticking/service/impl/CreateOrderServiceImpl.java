package com.ticking.service.impl;

import com.ticking.entity.SeatMessageEntity;
import com.ticking.entity.TicketItem;
import com.ticking.entity.TrainTicketDTO;
import com.ticking.mapper.CreateOrderMapper;
import com.ticking.service.ICreateOrderService;
import com.ticking.until.SelectedSeats;
import com.ticking.utility.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("createOrderService")
public class CreateOrderServiceImpl implements ICreateOrderService {

    @Autowired
    CreateOrderMapper createOrderMapper;

    /**
     * 创建订单
     */
    @Override
    public boolean createOrder(TrainTicketDTO trainTicketDTO, Long userId) {
        String trainIds = trainTicketDTO.getTrainId();
        String selectedSeats = trainTicketDTO.getSelectedSeats();
        List<TicketItem> ticketList = trainTicketDTO.getTicketList();
        Long trainId = Long.valueOf(trainIds);   // 获取车次ID
        
        // 解析座位信息
        if (selectedSeats != null && !selectedSeats.isEmpty()) {
            Map<String, String> seatInfoMap =new SelectedSeats().parseSelectedSeats(selectedSeats);
            List<SeatMessageEntity> seats =createOrderMapper.selectSeats(trainId);
        }

        createOrders(userId,trainId,trainTicketDTO);
        return false;
    }

    private void createOrders(Long userId, Long trainId, TrainTicketDTO trainTicketDTO) {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker();
        for (TicketItem ticketItem : trainTicketDTO.getTicketList()){
            Long orderId = snowflakeIdWorker.nextId();
            createOrderMapper.createOrder(orderId,userId,trainId,ticketItem.getTicketType(),ticketItem.getPrice(),ticketItem.getIdNumber());
        }
    }
}
