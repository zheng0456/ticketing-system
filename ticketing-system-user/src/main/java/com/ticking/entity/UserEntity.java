package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    private Integer userId;     //用户id
    private String userName;    //用户名
    private String password;     //用户密码
    private String realName;   //真实姓名
    private String idCard;     //身份证号
    private String phone;      //手机号
    private String email;      //邮箱
    private String registerTime;   //注册时间
    private Integer status;       //状态
}
