package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 车次途经站点实体类  表名 train_pass_station
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainPassStationEntity {
    private Long id;            // 主键
    private Long trainId;       // 车次ID
    private Long stationId;     // 途经站点ID
    private Integer passOrder;  // 途经顺序（1-出发站、2-第一途经站）
    private String arriveTime;  // 到站时间
    private String departTime;  // 离站时间
    private String stayDuration;// 停留时长（如"2分"）
}