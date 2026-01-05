package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
/**
 * 接收用户选择座位信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketItem {
    private Long id;
    private String idNumber;
    private String departureStationId;
    private String arrivalStationId;
    private String ticketType;
    private BigDecimal price;
    private String seat;
    private String seatType;
}
