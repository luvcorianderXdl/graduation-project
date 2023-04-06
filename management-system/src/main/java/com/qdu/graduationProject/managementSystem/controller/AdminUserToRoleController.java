package com.qdu.graduationProject.managementSystem.controller;

import com.qdu.graduationProject.managementSystem.vo.AdminUserToRoleTableInfoVo;
import com.qdu.graduationProject.managementSystem.vo.UpdateAdminUserToRoleVo;
import com.qdu.graduationProject.managementSystem.voservice.AdminUserToRoleVoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping("/getUpdatePage")
    public ModelAndView getUpdatePage(Long id) {
        List<AdminUserToRoleTableInfoVo> vos = adminUserToRoleVoService.getPageInfo(id);
        ModelAndView modelAndView = new ModelAndView("adminUserToRole_update");
        modelAndView.addObject("vos", vos);
        return modelAndView;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(UpdateAdminUserToRoleVo vo) {
        try {
            return adminUserToRoleVoService.updateAdminUserToRole(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
