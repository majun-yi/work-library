package com.work.library.config;

import com.work.library.config.global.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 * @Description 将自定义拦截器 注册到 spring容器中
 * @Date 2021/1/17 18:40
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //"/*" 是拦截所有的文件夹，不包含子文件夹
        //"/**" 是拦截所有的文件夹及里面的子文件夹
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
    }
}
