package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色关联实体类  表名 user_role
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity {
    private Long id;
    private Long userId;  // 用户id
    private Long roleId;  // 角色id
}