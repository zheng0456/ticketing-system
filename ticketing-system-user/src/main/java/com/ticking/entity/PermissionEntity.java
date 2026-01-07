package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissionEntity {
    private String user_id;
    private String userName;  // 用户名
    private String password;    // 密码
    private String phone;        // 手机
    private String [] role;   // 角色
    private String status;   // 状态
}
