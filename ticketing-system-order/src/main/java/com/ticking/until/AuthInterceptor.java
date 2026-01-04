package com.ticking.until;

import com.ticking.entity.UserEntity;
import com.ticking.service.IUserService;
import com.ticking.utility.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取Token
        String token = request.getHeader("Authorization");
        
        // 如果请求头中没有Authorization，则尝试从参数中获取
        if (token == null || token.isEmpty()) {
            token = request.getParameter("token");
        }
        
        // 如果token以"Bearer "开头，则去除前缀
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 验证token
        if (token != null && !token.isEmpty() && com.ticking.utility.JwtUtil.validateToken(token)) {
            // 从token中获取用户ID
            Long userId = JwtUtil.getUserIdFromToken(token);
            if (userId != null) {
                // 通过用户ID从数据库获取用户信息
                UserEntity user = userService.getUserById(userId);
                if (user != null) {
                    // 将用户信息存储到request中，供控制器使用
                    request.setAttribute("currentUser", user);
                    return true;
                }
            }
        }

        // 如果验证失败，设置响应状态并返回false
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("{\"code\": 401, \"message\": \"未授权访问\"}");
        return false;
    }
}