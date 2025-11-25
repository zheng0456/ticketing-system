package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体类  表名 view_order
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewOrderEntity {

    private Long orderId;          // 订单ID（主键）
    private String orderNo;        // 订单编号（唯一，如：ORD2025112500001）
    private Long userId;           // 关联用户ID（外键）
    private BigDecimal totalAmount; // 订单总金额（元）
    private BigDecimal payAmount;   // 实际支付金额（元）
    private Byte orderStatus;      // 订单状态：0-待支付 1-已支付 2-已取消 3-已完成 4-已退票
    private Date payDeadline;      // 支付截止时间（超时自动取消）
    private String remark;         // 订单备注
    private Date createTime;       // 创建时间
    private Date updateTime;       // 更新时间
}