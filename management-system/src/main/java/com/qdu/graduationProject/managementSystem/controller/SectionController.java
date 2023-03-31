package com.qdu.graduationProject.managementSystem.controller;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.JSONUtil;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.entity.Section;
import com.qdu.graduationProject.managementSystem.vo.AddSectionVo;
import com.qdu.graduationProject.managementSystem.vo.UpdateSectionVo;
import com.qdu.graduationProject.managementSystem.voservice.SectionVoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public String getSectionListPage() {
        return "section_list";
    }

    @RequestMapping("/getAddPage")
    public String getSectionAddPage() {
        return "section_add";
    }

    @RequestMapping("/getUpdatePage")
    public ModelAndView getUpdatePage(Long id) {
        Section section = sectionVoService.getSectionById(id);
        ModelAndView modelAndView = new ModelAndView("section_update");
        modelAndView.addObject("section", section);
        return modelAndView;
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

    @RequestMapping("/addSection")
    @ResponseBody
    public JSONResult addSection(HttpServletRequest req, AddSectionVo vo) {
        HttpSession session = req.getSession();
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
        return sectionVoService.addSection(vo, adminUser.getId());
    }

    @RequestMapping("/updateSection")
    @ResponseBody
    public Object updateSection(HttpServletRequest req, UpdateSectionVo vo) {
        try {
            HttpSession session = req.getSession();
            AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
            return sectionVoService.updateSection(vo, adminUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
