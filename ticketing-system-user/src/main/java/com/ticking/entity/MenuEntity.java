package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单实体类  表名 menu
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuEntity {
    private Long id;           // 菜单表主键
    private String path;       // 跳转路径
    private String name;       // 路由名称
    private String filePath;   // vue 所在路径位置
    private String isShowMenu; // 0 显示左侧菜单 1不显示
    private String sort;       // 排序字段
    private Long parentId;     // 父菜单id
    private String icon;       // 菜单图标
}