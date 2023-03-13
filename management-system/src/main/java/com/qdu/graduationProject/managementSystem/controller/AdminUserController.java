package com.qdu.graduationProject.managementSystem.controller;


import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.vo.LoginVo;
import com.qdu.graduationProject.managementSystem.voservice.AdminUserVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class AdminUserController {
    @Autowired
    private AdminUserVoService adminUserVoService;

    @RequestMapping("/checkUserLogin")
    @ResponseBody
    public JSONResult checkUserLogin(HttpSession session) {
        AdminUser adminUser = (AdminUser) session.getAttribute("user");
        return adminUser == null ? JSONResult.error() : JSONResult.ok();
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    @ResponseBody
    public Object login(@RequestBody LoginVo vo, HttpSession session) {
        try {
           JSONResult jsonResult =  adminUserVoService.login(session,vo);
           return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }
    @RequestMapping("/getLoginPage")
    public String getLoginPage() {
        return "login";
    }
}