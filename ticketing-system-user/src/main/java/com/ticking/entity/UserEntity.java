package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用户实体类  表名 user
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    private Long userId;     //用户id
    private String userName;    //用户名
    private String password;     //用户密码
    private String realName;   //真实姓名
    private String idCard;     //身份证号
    private String phone;      //手机号
    private String registerTime;  //注册时间
    private String email;      //邮箱
    private Integer status;       //状态
    private String lastLoginTime; //最后登录时间
    private Integer userQxCode;   //用户权限码
}
