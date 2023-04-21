package com.qdu.graduationProject.managementSystem.controller;

import com.qdu.graduationProject.commonUtils.utils.JSONUtil;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.voservice.UserVoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xdl
 * @date 2023/3/23 15:09
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserVoService userVoService;

    @RequestMapping("/getUserListPage")
    public String getAdminUserListPage() {
        return "user_list";
    }

    @RequestMapping("/selectByPage")
    public void getByPage(HttpServletRequest req, HttpServletResponse resp) {
        try {
            LayUITableJSONResult result = userVoService.getByPage(req, resp);
            JSONUtil.toJson(resp, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deleteByIds")
    @ResponseBody
    public Object deleteByIds(HttpServletRequest req, String ids) {
        HttpSession session = req.getSession();
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
        return userVoService.deleteByIds(ids, adminUser.getId());
    }

    @RequestMapping("/getByOpenId")
    @ResponseBody
    public void getByName(HttpServletRequest req, HttpServletResponse resp, String openId) {
        try {
            LayUITableJSONResult result = userVoService.getByPageAndOpenId(req, resp, openId);
            JSONUtil.toJson(resp, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
