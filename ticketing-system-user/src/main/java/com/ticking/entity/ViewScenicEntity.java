package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 景点信息实体类  表名 view_scenic
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewScenicEntity {

    private Long scenicId;          // 景点ID（主键）
    private String scenicName;      // 景点名称
    private String scenicAddress;   // 景点地址
    private String scenicIntro;     // 景点简介
    private String openingTime;     // 开放时间（例：08:00-17:30）
    private String closingDay;      // 闭园日（例：每周一）
    private String contactPhone;    // 联系电话
    private Integer scenicStatus;   // 景点状态：0-停业 1-正常营业
    private Date createTime;        // 创建时间
    private Date updateTime;        // 更新时间
}