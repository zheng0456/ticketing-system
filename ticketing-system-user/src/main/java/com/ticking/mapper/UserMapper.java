package com.ticking.mapper;

import com.ticking.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 通过用户名 密码 获取用户信息
     */
    @Select("select * from user where user_name=#{userName} and password=#{password} and status=1")
    UserEntity login(String userName, String password);

    @Insert("INSERT INTO user(user_id,user_name,password,phone,register_time,status) VALUES (#{userId},#{userName},#{password},#{phone},#{dataTime},1)")
    Boolean register(Long userId,String userName, String password, String phone,String dataTime);

    /**
     * 添加用户角色
     */
    @Insert("INSERT INTO user_role(user_id,role_id) VALUES (#{userId},#{roleId})")
    void addUserRole(long userId, int i);
}