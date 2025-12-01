package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色菜单关联实体类  表名 role_menu
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuEntity {
    private Long id;
    private Long roleId;  // 角色id
    private Long menuId;  // 菜单id
}