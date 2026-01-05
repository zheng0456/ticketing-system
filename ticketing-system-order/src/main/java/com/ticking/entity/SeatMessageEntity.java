package com.ticking.entity;

import lombok.Data;

@Data
public class SeatMessageEntity {
    private Long carriageId;   // 车厢ID
    private Long seatId;       // 座位ID
    private String seatNo; // 座位号
    private String seatType;   // 座位类型
}