package com.qdu.graduationProject.managementSystem.controller;


import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.JSONUtil;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.vo.AddAdminUserVo;
import com.qdu.graduationProject.managementSystem.vo.UpdateAdminUserVo;
import com.qdu.graduationProject.managementSystem.voservice.AdminUserVoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author xdl
 * @date 2023/3/9 12:17
 */
@Controller
@RequestMapping("/adminUser")
public class AdminUserController {
    @Resource
    private AdminUserVoService adminUserVoService;

    @RequestMapping("/getLoginPage")
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping("/getAdminUserListPage")
    public String getAdminUserListPage() {
        return "adminUser_list";
    }

    @RequestMapping("/getChangePasswordPage")
    public String getChangePasswordPage() {
        return "adminUser_changePassword";
    }

    @RequestMapping("/getAddPage")
    public String getAdminUserPage() {
        return "adminUser_add";
    }

    @RequestMapping("/getUpdatePage")
    public ModelAndView getUpdatePage(Long id) {
        AdminUser adminUser = adminUserVoService.getAdminUserById(id);
        ModelAndView modelAndView = new ModelAndView("adminUser_update");
        modelAndView.addObject("adminUser", adminUser);
        return modelAndView;
    }

    @RequestMapping("/login")
    @ResponseBody
    public void login(HttpServletRequest req, HttpServletResponse resp) {
        //取出用户传递的验证码
        String code = req.getParameter("code");
        //取出正确验证码
        HttpSession session = req.getSession();
        String codeInSession = (String) session.getAttribute("codeInSession");
        //比对
        if (!code.equalsIgnoreCase(codeInSession)) {
            JSONUtil.toJson(resp, JSONResult.error("验证码错误"));
        } else {
            adminUserVoService.login(req, resp);
        }
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        session.invalidate();
        resp.sendRedirect(req.getContextPath() + "/adminUser/getLoginPage");
    }

    @RequestMapping("/changePassword")
    @ResponseBody
    public Object changePassword(String oldPass, String newPass, String confirmPass, HttpSession session) {
        String loginId = ((AdminUser) session.getAttribute("adminUser")).getLoginId();
        JSONResult result = adminUserVoService.changePassword(loginId, oldPass, newPass, confirmPass);
        if (result.isOk()) {
            session.invalidate();
        }
        return result;
    }

    @RequestMapping("/selectByPage")
    public void getByPage(HttpServletRequest req, HttpServletResponse resp) {
        try {
            LayUITableJSONResult result = adminUserVoService.getByPage(req, resp);
            JSONUtil.toJson(resp, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/addAdminUser")
    @ResponseBody
    public Object addAdminUser(HttpServletRequest req, AddAdminUserVo vo) {
        HttpSession session = req.getSession();
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
        return adminUserVoService.addAdminUser(vo, adminUser.getId());
    }

    @RequestMapping("/deleteByIds")
    @ResponseBody
    public Object deleteByIds(HttpServletRequest req, String ids) {
        HttpSession session = req.getSession();
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
        return adminUserVoService.deleteByIds(ids, adminUser.getId());
    }

    @RequestMapping("/updateAdminUser")
    @ResponseBody
    public Object updateAdminUser(HttpServletRequest req, UpdateAdminUserVo vo) {
        try {
            HttpSession session = req.getSession();
            AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
            return adminUserVoService.updateAdminUser(vo, adminUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
