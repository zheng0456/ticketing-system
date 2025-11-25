package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生优惠信息实体类  表名 student_discount
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDiscountEntity {
    private Long id;                    // 主键
    private Long userId;                // 学生用户ID
    private String schoolName;          // 学校名称
    private String studentId;           // 学生证号
    private Long discountStartStation;  // 优惠区间出发站ID
    private Long discountEndStation;    // 优惠区间到达站ID
    private String validStartDate;      // 优惠有效期起始日
    private String validEndDate;        // 优惠有效期截止日
    private Integer remainTimes;        // 剩余优惠次数（每年4次）
}