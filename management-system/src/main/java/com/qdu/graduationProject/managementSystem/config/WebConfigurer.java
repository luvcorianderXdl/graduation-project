package com.qdu.graduationProject.managementSystem.config;

import com.qdu.graduationProject.managementSystem.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xdl
 * @date 2023/3/9 12:21
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

//    // 配置虚拟路径
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/pic/**")
//                .addResourceLocations("file:/D:/mypic/");
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }


    // 拦截器注册
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求
        // excludePathPatterns()   代表排除哪些请求不需要拦截
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/adminUser/login", "/adminUser/getLoginPage", "/auth/code", "/error", "/static/**");
    }

}
