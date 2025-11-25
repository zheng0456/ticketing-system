package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 门票库存实体类  表名 view_ticket_stock
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewTicketStockEntity {

    private Long stockId;           // 库存ID（主键）
    private Long ticketTypeId;      // 关联门票类型ID（外键）
    private Integer totalStock;     // 总库存数量
    private Integer usedStock;      // 已售数量
    private Integer remainingStock; // 剩余库存（=total_stock - used_stock）
    private Date updateTime;        // 更新时间
}