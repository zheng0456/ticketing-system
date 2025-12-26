package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单实体类  表名 menu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuEntity {
    private Long id;                // 菜单表主键ID
    private String path;            // 跳转路径
    private String name;            // 路由名称
    private String filePath;        // vue所在路径位置
    private String isShowMenu;      // 是否显示左侧菜单(0显示 1不显示)
    private String sort;            // 排序字段
    private Long parentId;          // 父菜单ID
    private String icon;            // 菜单图标
}