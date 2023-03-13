package com.qdu.graduationProject.managementSystem.config;

import com.qdu.graduationProject.managementSystem.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xdl
 * @create 2023/3/9 12:21
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


    /*<!-- 配置拦截器 -->
          <mvc:interceptors>
             <mvc:interceptor>
                <mvc:mapping path="/**"/>
                <mvc:exclude-mapping path=""/>
                <bean class="com.situ.mvc.interceptor.MyInterceptor1"></bean>
             </mvc:interceptor>
          </mvc:interceptors>*/
    // 这个方法用来注册拦截器，我们写的拦截器需要在这里配置才能生效
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // addPathPatterns("/**") 表示拦截所有的请求
//        // excludePathPatterns()   代表排除哪些请求不需要拦截
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login","/user/getLoginPage", "/auth/code", "/error", "/static/**");
//    }

}
