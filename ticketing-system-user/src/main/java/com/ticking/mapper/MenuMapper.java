package com.ticking.mapper;

import com.ticking.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {
    /**
     * 通过用户ID 获取菜单列表
     */
    @Select("select path,file_path from ROLE_QX where user_id=#{userId} ")
    List<MenuEntity> getMenuList(Long userId);
}
