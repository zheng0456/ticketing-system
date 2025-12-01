package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色权限实体类  表名 role
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
    private Integer id;
    private String name;  // 角色名称
    private String remark; // 角色描述
}
