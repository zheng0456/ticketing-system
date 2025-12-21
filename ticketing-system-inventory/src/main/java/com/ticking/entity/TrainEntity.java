package com.ticking.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 车次信息实体类  表名 train
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainEntity {
    private Long id;                // 车次唯一标识
    private String trainNo;         // 车次编号（如"G123"）
    private Long startStationId;    // 出发站点ID
    private Long endStationId;      // 到达站点ID
    private String startTime;       // 出发时间
    private String endTime;         // 到达时间
    private String runDuration;     // 运行时长（如"4小时30分"）
    private String trainType;       // 列车类型（G-高铁、D-动车等）
    private Integer operateStatus;  // 运营状态（0-停运，1-正常，2-维修）
    private String createTime;      // 创建时间
    private String maintenanceTime;   //维护时间
    private String note;  //备注
    private BigDecimal fuyiTime;   //服役年限
    private String startStationName;   //出发站名称
    private String endStationName;    //到达站名称
}