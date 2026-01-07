package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

/**
 * 用户角色表
 */
public class UserRoleEntity {
    private Long id;
    private Long userId;
    private Long roleId;
}
