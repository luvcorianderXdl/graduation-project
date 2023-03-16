package com.qdu.graduationProject.managementSystem.voservice;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.service.AdminUserService;
import com.qdu.graduationProject.managementSystem.vo.LoginVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author xdl
 * @create 2023/3/9 14:04
 */
@Service
public class AdminUserVoService {

    @Resource
    private AdminUserService adminUserService;

    public JSONResult login(String loginId,String password,HttpSession session) throws Exception {
        if("".equals(loginId)) {
            throw new Exception("用户名禁止为空");
        } else {
            AdminUser adminUser = adminUserService.login(loginId,password);
            if (adminUser == null) {
                return JSONResult.error("用户名或密码错误");
            } else {
                session.setAttribute("adminUser", adminUser);
                return JSONResult.ok("登录成功");
            }
        }
    }
}
