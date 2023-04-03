package com.qdu.graduationProject.managementSystem.voservice;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.managementSystem.entity.Role;
import com.qdu.graduationProject.managementSystem.service.RoleService;
import com.qdu.graduationProject.managementSystem.vo.AddRoleVo;
import com.qdu.graduationProject.managementSystem.vo.UpdateRoleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xdl
 * @date 2023/4/3 10:04
 */
@Service
public class RoleVoService {
    @Resource
    private RoleService roleService;

    public LayUITableJSONResult getByPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String pageNo = req.getParameter("page");
        String pageSize = req.getParameter("limit");
        if (pageNo == null || pageNo.equals("")) {
            pageNo = "1";
        }
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        return roleService.getByPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
    }

    public Role getRoleById(Long id) {
        return roleService.getRoleById(id);
    }

    public JSONResult updateRole(UpdateRoleVo vo, Long id) throws Exception {
        if (vo.getId() == null) {
            throw new Exception("参数丢失");
        }
        if (vo.getName() == null || "".equals(vo.getName())) {
            return JSONResult.error("名称禁止为空");
        }
        if (vo.getDescription() == null || "".equals(vo.getDescription())) {
            return JSONResult.error("描述禁止为空");
        }
        return roleService.updateRole(vo, id);
    }

    public JSONResult addRole(AddRoleVo vo, Long id) {
        if (vo.getName() == null || "".equals(vo.getName())) {
            return JSONResult.error("名称禁止为空");
        }
        if (vo.getDescription() == null || "".equals(vo.getDescription())) {
            return JSONResult.error("描述禁止为空");
        }
        return roleService.addRole(vo, id);
    }
}
