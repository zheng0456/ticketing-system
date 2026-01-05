package com.ticking.utility;

import com.ticking.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 用户工具类 - 提供获取当前登录用户信息的便捷方法
 */
public class UserUtil {
    
    /**
     * 从请求中获取当前登录用户的ID
     * @param request HTTP请求对象
     * @return 用户ID，如果未登录则返回null
     */
    public static Long getCurrentUserId(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getAttribute("currentUser");
        return user != null ? user.getUserId() : null;
    }
    
    /**
     * 从请求中获取当前登录用户的完整信息
     * @param request HTTP请求对象
     * @return 用户实体，如果未登录则返回null
     */
    public static UserEntity getCurrentUser(HttpServletRequest request) {
        return (UserEntity) request.getAttribute("currentUser");
    }
    
    /**
     * 检查用户是否已登录
     * @param request HTTP请求对象
     * @return 如果已登录返回true，否则返回false
     */
    public static boolean isUserLoggedIn(HttpServletRequest request) {
        return request.getAttribute("currentUser") != null;
    }
}