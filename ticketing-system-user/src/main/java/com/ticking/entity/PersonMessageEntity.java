package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonMessageEntity {
    private Long userId;
    private String userName;
    private String phone;
    private String idCard;
    private String realName;
    private String cardType;
    private String discountType;
}
