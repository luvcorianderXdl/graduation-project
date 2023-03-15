package com.qdu.graduationProject.managementSystem.interceptor;

import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.security.util.Debug;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xdl
 * @create 2023/3/9 12:17
 */
public class LoginInterceptor implements HandlerInterceptor {

    // 返回true：代表已经登录，就可以访问后台资源
    // 返回false：代表没有登录，请求被拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginInterceptor.preHandle");
        String servletPath = request.getServletPath();
        System.out.println("servletPath: " + servletPath);
        HttpSession session = request.getSession();
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
        if (adminUser == null) {
            response.sendRedirect(request.getContextPath() + "/adminUser/getLoginPage");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("LoginInterceptor.postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("LoginInterceptor.afterCompletion");
    }
}

