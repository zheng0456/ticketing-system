package com.ticking.mapper;

import com.ticking.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * from user where userId =1  ")
    UserEntity login(String userid);
}
