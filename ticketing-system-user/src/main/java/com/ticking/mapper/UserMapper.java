package com.ticking.mapper;

import com.ticking.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * from user where ")
    UserEntity login(UserEntity user);
}
