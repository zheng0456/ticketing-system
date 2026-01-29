package com.ticking.mapper;

import com.ticking.entity.PersonMessageEntity;
import com.ticking.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 根据用户ID获取用户信息
     */
    @Select("SELECT * FROM user WHERE user_id = #{userId} and status=1")
    UserEntity selectById(Long userId);

    @Delete("DELETE FROM user WHERE user_id = #{userId}")
    boolean deleteUser(Long userId);

    @Delete("DELETE FROM user_id_card WHERE user_id = #{userId}")
    boolean deleteUserPassion(Long userId);

    @Delete("DELETE FROM user_role WHERE user_id = #{userId}")
    boolean deleteUserRole(Long userId);

    @Select("SELECT role_id FROM user_role WHERE user_id = #{userId}")
    Long selectRoleId(Long userId);

    @Delete("DELETE FROM role_menu WHERE role_id = #{roleId}")
    boolean deleteUserRoleMeau(Long roleId);

    @Select("SELECT * FROM user u left join user_id_card uid on u.user_id=uid.user_id WHERE u.user_id = #{userId}")
    PersonMessageEntity selectPersonMessages(Long userId);

    /**
     * 修改用户信息
     */
    @Update("UPDATE user SET phone = #{phone},id_card=#{idCard},real_name=#{realName} WHERE user_id = #{userId}")
    boolean updateUserMessages(Long userId, String phone, String idCard, String realName);

    /**
     * 修改用户身份证信息
     */
    @Insert("INSERT INTO user_id_card(user_id,card_type,card_id,phone,discount_type,name) VALUES (#{userId},#{cardType},#{idCard},#{phone},#{discountType},#{realName})")
    boolean updateUserIdCard(Long userId, String cardType, String idCard, String realName, String discountType, String phone);
}