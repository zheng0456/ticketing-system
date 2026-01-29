package com.ticking.config;

import com.ticking.utility.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加认证拦截器，对需要认证的路径进行拦截
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/passion/**", "/passenger/**", "/getCurrentUser", "/getCurrentUserId")  // 需要认证的路径
                .addPathPatterns("/trainOrder/**", "/trainOrder/**", "/getCurrentUser", "/getCurrentUserId")  //  需要认证的路径
                 .addPathPatterns("/userLogOut/**", "/userLogOut/**", "/getCurrentUser", "/getCurrentUserId")
                 .addPathPatterns("/personMessages/**", "/personMessages/**", "/getCurrentUser", "/getCurrentUserId")
                .excludePathPatterns("/login", "/register");  // 不需要认证的路径
    }
}