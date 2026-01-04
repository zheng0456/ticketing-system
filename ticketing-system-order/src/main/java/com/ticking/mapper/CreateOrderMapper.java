package com.ticking.mapper;

import com.ticking.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CreateOrderMapper {
    /**
     * 根据用户ID获取用户信息
     */
    @Select("SELECT * FROM user WHERE user_id = #{userId} and status=1")
    UserEntity selectById(Long userId);
}
