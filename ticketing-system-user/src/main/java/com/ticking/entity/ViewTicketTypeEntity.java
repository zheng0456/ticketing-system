package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 门票类型实体类  表名 view_ticket_type
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewTicketTypeEntity {

    private Long ticketTypeId;        // 门票类型ID（主键）
    private Long scenicId;            // 关联景点ID（外键）
    private String ticketName;        // 门票名称（例：成人票、儿童票、学生票）
    private BigDecimal ticketPrice;   // 门票单价（元）
    private BigDecimal discountPrice; // 优惠价（元，NULL表示无优惠）
    private Integer validDays;        // 门票有效期（天，默认1天有效）
    private Integer minAge;           // 最低适用年龄（0表示无限制）
    private Integer maxAge;           // 最高适用年龄（120表示无限制）
    private Integer requireCert;      // 是否需要证件：0-不需要 1-需要（如学生证、身份证）
    private Integer stockWarning;     // 库存预警阈值（低于该值提醒补货）
    private Integer ticketStatus;     // 门票状态：0-下架 1-上架
    private Date createTime;          // 创建时间
    private Date updateTime;          // 更新时间
}