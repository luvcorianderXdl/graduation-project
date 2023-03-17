package com.qdu.graduationProject.managementSystem.controller;


import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.JSONUtil;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.voservice.AdminUserVoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/adminUser")
public class AdminUserController {
    @Resource
    private AdminUserVoService adminUserVoService;

    @RequestMapping("/getLoginPage")
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public void login(HttpServletRequest req, HttpServletResponse resp) {
//        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        String codeInSession = (String) session.getAttribute("codeInSession");
        String code = (String) session.getAttribute("codeInSession");
        if (!code.equalsIgnoreCase(codeInSession)) {
            JSONUtil.toJson(resp,JSONResult.error("验证码错误"));
        } else{
            adminUserVoService.login(req,resp);
        }
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest req, HttpServletResponse resp,HttpSession session) throws IOException {
        session.invalidate();
//        return "redirect:/adminUser/getLoginPage";
        resp.sendRedirect(req.getContextPath()+"/adminUser/getLoginPage");
    }

    @RequestMapping("/cp")
    public void cp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        return "forward:/WEB-INF/changePassword.jsp";
        req.getRequestDispatcher("/changePassword.jsp").forward(req,resp);
    }
    @RequestMapping("/changePassword")
    @ResponseBody
    public Object changePassword(String oldPass,String newPass,String confirmPass,HttpSession session){
        String loginId = ((AdminUser) session.getAttribute("adminUser")).getLoginId();
        try {
            JSONResult result = adminUserVoService.changePassword(loginId,oldPass,newPass,confirmPass);
            if (result.isOk()){
                session.invalidate();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/selectByPage")
    public void getByPage(HttpServletRequest req, HttpServletResponse resp){
        try {
            LayUITableJSONResult result = adminUserVoService.getByPage(req,resp);
            JSONUtil.toJson(resp,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
