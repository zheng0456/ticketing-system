package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户乘车人实体类  表名 user_id_card
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassionEntity {
    private Long id;
    private Long userId;    // 用户ID
    private String name;    // 姓名
    private String phone;     // 手机号
    private String cardId;      // 证件号码
    private String cardType;      // 证件类型
    private String discountType;   // 优惠类型
}
