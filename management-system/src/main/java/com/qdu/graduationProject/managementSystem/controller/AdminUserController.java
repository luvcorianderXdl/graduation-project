package com.qdu.graduationProject.managementSystem.controller;


import com.qdu.graduationProject.managementSystem.voservice.AdminUserVoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class AdminUserController {
    @Resource
    private AdminUserVoService adminUserVoService;

    @RequestMapping("/getLoginPage")
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object login(String name, String password, String code, HttpSession session) {
        String codeInSession = (String) session.getAttribute("codeInSession");
//        if (!code.equalsIgnoreCase(codeInSession)) {
//            return JSONResult.error("验证码错误");
//        }
        try {
            return adminUserVoService.login(name, password,session);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/getLoginPage";
    }
}
