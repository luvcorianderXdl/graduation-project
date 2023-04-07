package com.qdu.graduationProject.managementSystem.voservice;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.entity.Role;
import com.qdu.graduationProject.managementSystem.service.AdminUserToRoleService;
import com.qdu.graduationProject.managementSystem.service.RoleService;
import com.qdu.graduationProject.managementSystem.vo.AddRoleVo;
import com.qdu.graduationProject.managementSystem.vo.UpdateRoleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xdl
 * @date 2023/4/3 10:04
 */
@Service
public class RoleVoService {
    @Resource
    private RoleService roleService;

    @Resource
    private AdminUserToRoleService adminUserToRoleService;

    public LayUITableJSONResult getByPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AdminUser adminUser = (AdminUser) req.getSession().getAttribute("adminUser");
        List<Long> roles = adminUserToRoleService.getRoleIdsByAdminUserId(adminUser.getId());
        if (roles != null && roles.contains(3L)) {
            String pageNo = req.getParameter("page");
            String pageSize = req.getParameter("limit");
            if (pageNo == null || pageNo.equals("")) {
                pageNo = "1";
            }
            if (pageSize == null || pageSize.equals("")) {
                pageSize = "10";
            }
            return roleService.getByPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        } else {
            return LayUITableJSONResult.error("暂无数据");
        }
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

    public JSONResult deleteByIds(String ids, Long id) {
        if (ids == null || "".equals(ids)) {
            return JSONResult.error("请选择用户");
        }
        String[] temp = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String s : temp) {
            idList.add(Long.parseLong(s));
        }
        return roleService.deleteById(idList, id);
    }
}
