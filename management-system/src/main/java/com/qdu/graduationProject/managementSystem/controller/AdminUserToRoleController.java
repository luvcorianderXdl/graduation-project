package com.qdu.graduationProject.managementSystem.controller;

import com.qdu.graduationProject.managementSystem.entity.Role;
import com.qdu.graduationProject.managementSystem.voservice.AdminUserToRoleVoService;
import com.qdu.graduationProject.managementSystem.voservice.RoleVoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xdl
 * @date 2023/4/3 14:51
 */
@Controller
@RequestMapping("/adminUserToRole")
public class AdminUserToRoleController {

    @Resource
    private AdminUserToRoleVoService adminUserToRoleVoService;
    
    @Resource
    private RoleVoService roleVoService;

    @RequestMapping("/getUpdatePage")
    public ModelAndView getUpdatePage(Long id) {
        List<Long> roleIds = adminUserToRoleVoService.getRoleIdsByAdminUserId(id);
        List<Role> roles = roleVoService.getAll();
        ModelAndView modelAndView = new ModelAndView("adminUserToRole_update");
        modelAndView.addObject("roleIds", roleIds);
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }

}
