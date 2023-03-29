package com.qdu.graduationProject.managementSystem.controller;

import com.qdu.graduationProject.commonUtils.utils.JSONUtil;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.voservice.SectionVoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xdl
 * @date 2023/3/28 17:34
 */
@Controller
@RequestMapping("/section")
public class SectionController {

    @Resource
    private SectionVoService sectionVoService;

    @RequestMapping("/getSectionListPage")
    public String getAdminUserListPage() {
        return "section_list";
    }

    @RequestMapping("/selectByPage")
    public void getByPage(HttpServletRequest req, HttpServletResponse resp) {
        try {
            LayUITableJSONResult result = sectionVoService.getByPage(req, resp);
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
        return sectionVoService.deleteByIds(ids, adminUser.getId());
    }
}
