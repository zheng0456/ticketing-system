package com.ticking.mapper;

import com.ticking.entity.PermissionEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionMapper {
    /**
     * 插入用户 user 表
     */
    @Insert("INSERT INTO user(user_id,user_name,password,phone,register_time,status) VALUES (#{id},#{username},#{password},#{phone},#{time},1)")
    boolean insertUser(long id, String username, String password, String phone, String time);

    /**
     * 插入用户角色 user_role 表
     */
    @Insert("INSERT INTO user_role(user_id,role_id) VALUES (#{id},#{roleId})")
    boolean insertUserRole(long id, Long roleId);

    /**
     * 获取用户权限列表
     */
    @Select("SELECT * FROM user_role ur LEFT JOIN user u ON ur.user_id = u.user_id")
    List<Map<String, Object>> getUserPermissionList();

    /**
     * 更新用户信息
     */
    @Update("UPDATE user SET phone = #{phone} WHERE user_id = #{userId}")
    boolean updateUserMessages(Long userId, String phone);


    /**
     * 修改用户 状态
     */
    @Update("UPDATE user SET status = #{status} WHERE user_id = #{userId}")
    boolean updateUserStatus(Long userId, Integer status);

    /**
     * 删除用户
     */
    @Delete("DELETE FROM user WHERE user_id = #{userId}")
    boolean deleteUser(Long userId);

    /**
     * 删除用户角色
     */
    @Delete("DELETE FROM user_role WHERE user_id = #{userId}")
    boolean deleteUserRole(Long valueOf);
}
