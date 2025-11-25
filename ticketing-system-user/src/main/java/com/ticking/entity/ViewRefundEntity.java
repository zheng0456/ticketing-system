package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 退票记录实体类  表名 view_refund
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewRefundEntity {

    private Long refundId;          // 退票ID（主键）
    private Long orderId;           // 关联订单ID（外键）
    private Long itemId;            // 关联订单详情ID（外键）
    private String refundNo;        // 退票流水号
    private Integer refundNum;      // 退票数量
    private BigDecimal refundAmount; // 退票金额（元）
    private String refundReason;    // 退票原因
    private Integer refundStatus;   // 退票状态：0-申请中 1-退票成功 2-退票失败
    private Date applyTime;         // 申请时间
    private Date handleTime;        // 处理时间
}