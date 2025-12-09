package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 火车站点实体类  表名 train_station
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainStationEntity {
    private Long id;            // 站点唯一标识
    private String stationName; // 站点名称（如"北京西站"）
    private String province;    // 所属省份
    private String city;        // 所属城市
    private Integer status;  // 拼音缩写（如"BXP"）
    private Integer sortNo;     // 区域排序号
    private String address;   // 地址
    private String vote;  // 备注
    private String createTime ;   // 创建时间
}