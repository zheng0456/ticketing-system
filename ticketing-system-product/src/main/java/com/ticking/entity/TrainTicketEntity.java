package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 火车 车票信息  表 ticket_price
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainTicketEntity {
    private Long Id;     //主键
    private Long trainId;  //列车次 id
    private Long startStationId;  //出发站 id
    private Long endStationId;  //终点站 id
    private String seatType;  //座位类型
    private BigDecimal  adultPrice;  //成人票价
    private BigDecimal childPrice; //儿童票价
    private BigDecimal  studentPrice; //学生票价
}
