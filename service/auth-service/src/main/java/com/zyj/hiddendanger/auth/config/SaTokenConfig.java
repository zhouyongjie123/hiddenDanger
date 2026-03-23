package com.zyj.hiddendanger.auth.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import com.zyj.hiddendanger.auth.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Sa-Token 核心配置类
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    /**
     * 注册 Sa-Token 拦截器 + 自定义认证拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义认证拦截器（核心）
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")  // 拦截所有请求
                .excludePathPatterns("/auth/login", "/error"); // 排除登录接口、错误页面
    }

    /**
     * 自定义 Sa-Token 配置（可选，根据业务调整）
     */
    @Bean
    public SaInterceptor saInterceptor() {
        return new SaInterceptor(handle -> {
            // 可在这里添加 Sa-Token 内置的认证规则，如：登录校验、角色校验等
            // StpUtil.checkLogin(); // 示例：强制登录校验（也可在自定义拦截器中做）
        });
    }
}
