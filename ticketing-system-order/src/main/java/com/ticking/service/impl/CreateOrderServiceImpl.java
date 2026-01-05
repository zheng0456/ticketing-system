package com.ticking.service.impl;

import com.ticking.entity.SeatMessageEntity;
import com.ticking.entity.TicketItem;
import com.ticking.entity.TrainTicketDTO;
import com.ticking.mapper.CreateOrderMapper;
import com.ticking.service.ICreateOrderService;
import com.ticking.until.CurrentTime;
import com.ticking.until.OrderNoGenerator;
import com.ticking.utility.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

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
        List<TicketItem> ticketList = trainTicketDTO.getTicketList();
        Long trainId = Long.valueOf(trainIds);   // 获取车次ID
        for (TicketItem ticketItem : ticketList){
            String seatType = ticketItem.getSeatType();
            String seatNo = "%"+ticketItem.getSeat()+"%";
            List<SeatMessageEntity> seats =createOrderMapper.selectSeats(trainId,seatType,seatNo);
            // 随机选择一个座位
            if (seats != null && !seats.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(seats.size());
                SeatMessageEntity selectedSeat = seats.get(randomIndex);
                createOrders(userId,trainId,trainTicketDTO,selectedSeat.getCarriageId(),selectedSeat.getSeatId(),selectedSeat.getSeatNo());
            }
        }
        return false;
    }

    private void createOrders(Long userId, Long trainId, TrainTicketDTO trainTicketDTO, Long carriageId, Long seatId, String seatNo) {
        Boolean order=false;
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker();
        // 在方法中添加时间获取
        String createTime = CurrentTime.createCurrentTime();
        // 获取当前时间并加30分钟 为订单自动消除时间
        String payDeadline = CurrentTime.afterTime();

        String orderNo = "T"+OrderNoGenerator.generateOrderNo();
        for (TicketItem ticketItem : trainTicketDTO.getTicketList()){
            Long orderId = snowflakeIdWorker.nextId();
            String arrivalStationId = ticketItem.getArrivalStationId();
            Long endStationId = Long.valueOf(arrivalStationId);
            String departureStationId = ticketItem.getDepartureStationId();
            Long startStationId = Long.valueOf(departureStationId);
            order = createOrderMapper.createOrder(orderId,orderNo, userId, trainId,startStationId, endStationId, ticketItem.getTicketType(), ticketItem.getPrice(), ticketItem.getIdNumber(), carriageId, seatId, seatNo, createTime, payDeadline);
            if (order==false){
                throw new RuntimeException("创建订单失败");
            }
        }
    }
}
