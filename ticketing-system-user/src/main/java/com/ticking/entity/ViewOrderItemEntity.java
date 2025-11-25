package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情实体类  表名 view_order_item
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewOrderItemEntity {

    private Long itemId;             // 订单详情ID（主键）
    private Long orderId;            // 关联订单ID（外键）
    private Long ticketTypeId;       // 关联门票类型ID（外键）
    private Long scenicId;           // 关联景点ID（冗余字段，优化查询）
    private String ticketName;       // 门票名称（冗余字段）
    private BigDecimal ticketPrice;  // 购票时单价（元，冗余字段）
    private Integer buyNum;          // 购买数量
    private BigDecimal subtotalAmount; // 小计金额（=ticket_price * buy_num）
    private Integer useStatus;       // 使用状态：0-未使用 1-已使用 2-已退票
    private Date createTime;         // 创建时间
    private Date updateTime;         // 更新时间
}