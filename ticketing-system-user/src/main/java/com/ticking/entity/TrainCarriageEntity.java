package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 车厢信息实体类  表名 train_carriage
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainCarriageEntity {
    private Long id;            // 车厢唯一标识
    private Long trainId;       // 车次ID
    private String carriageNo;  // 车厢编号（如"1号车"）
    private String seatType;    // 座位类型（一等座、二等座等）
    private Integer totalSeats; // 总座位数
}