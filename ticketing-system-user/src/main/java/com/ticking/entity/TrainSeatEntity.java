package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 座位信息实体类  表名 train_seat
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainSeatEntity {
    private Long id;                // 座位唯一标识
    private Long carriageId;        // 车厢ID
    private String seatNo;          // 座位号（如"1A""3C"）
    private Integer seatStatus;     // 状态（0-未售，1-已售，2-锁定）
}