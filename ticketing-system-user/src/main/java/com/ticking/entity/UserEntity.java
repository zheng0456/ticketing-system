package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    private Integer userId;  // id
    private String userName;   //用户名
    private String password;   //密码
    private String phone;     //手机号
    private String address;   // 地址
    private Integer sign;   //标记 标记用户=0 商家=1 管理员=2
    private Integer dr;     //删除标记 0正常 1删除
}
