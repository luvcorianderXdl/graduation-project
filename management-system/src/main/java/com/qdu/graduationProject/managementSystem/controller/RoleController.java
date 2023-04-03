package com.qdu.graduationProject.managementSystem.controller;

import com.qdu.graduationProject.commonUtils.utils.JSONUtil;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.entity.Role;
import com.qdu.graduationProject.managementSystem.vo.AddRoleVo;
import com.qdu.graduationProject.managementSystem.vo.UpdateRoleVo;
import com.qdu.graduationProject.managementSystem.voservice.RoleVoService;
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
 * @date 2023/4/3 9:58
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleVoService roleVoService;

    @RequestMapping("/getRoleListPage")
    public String getPermissionListPage() {
        return "role_list";
    }

    @RequestMapping("/getUpdatePage")
    public ModelAndView getUpdatePage(Long id) {
        Role role = roleVoService.getRoleById(id);
        ModelAndView modelAndView = new ModelAndView("role_update");
        modelAndView.addObject("role", role);
        return modelAndView;
    }

    @RequestMapping("/getAddPage")
    public String getAddPage() {
        return "role_add";
    }

    @RequestMapping("/selectByPage")
    public void getByList(HttpServletRequest req, HttpServletResponse resp) {
        try {
            LayUITableJSONResult result = roleVoService.getByPage(req, resp);
            JSONUtil.toJson(resp, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public Object updateRole(HttpServletRequest req, UpdateRoleVo vo) {
        try {
            HttpSession session = req.getSession();
            AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
            return roleVoService.updateRole(vo, adminUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public Object addRole(HttpServletRequest req, AddRoleVo vo) {
        HttpSession session = req.getSession();
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
        return roleVoService.addRole(vo, adminUser.getId());

    }

}
