package com.ticking.mapper;

import com.ticking.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where user_name=#{userName} and password=#{password} and status=1")
    UserEntity login(String userName, String password);

    @Insert("INSERT INTO user(user_name,password,phone,register_time,status) VALUES (#{userName},#{password},#{phone},#{dataTime},1)")
    Boolean register(String userName, String password, String phone,String dataTime);

}