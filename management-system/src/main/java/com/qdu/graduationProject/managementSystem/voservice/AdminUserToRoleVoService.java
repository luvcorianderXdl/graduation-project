package com.qdu.graduationProject.managementSystem.voservice;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.managementSystem.entity.Role;
import com.qdu.graduationProject.managementSystem.service.AdminUserToRoleService;
import com.qdu.graduationProject.managementSystem.service.RoleService;
import com.qdu.graduationProject.managementSystem.vo.AdminUserToRoleTableInfoVo;
import com.qdu.graduationProject.managementSystem.vo.UpdateAdminUserToRoleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xdl
 * @date 2023/4/3 15:16
 */
@Service
public class AdminUserToRoleVoService {

    @Resource
    private AdminUserToRoleService adminUserToRoleService;

    @Resource
    private RoleService roleService;

    public List<AdminUserToRoleTableInfoVo> getPageInfo(Long adminUserId) {
        List<Long> ids = adminUserToRoleService.getRoleIdsByAdminUserId(adminUserId);
        List<Role> roles = roleService.getAllByUseFlag();
        List<AdminUserToRoleTableInfoVo> vos = new ArrayList<>();
        roles.forEach(role -> {
            AdminUserToRoleTableInfoVo vo = new AdminUserToRoleTableInfoVo();
            vo.setAdminUserId(adminUserId);
            vo.setRoleId(role.getId());
            vo.setRoleName(role.getName());
            vo.setCheck(0L);
            for (Long id : ids) {
                if (id.equals(role.getId())) {
                    vo.setCheck(1L);
                }
            }
            vos.add(vo);
        });
        return vos;
    }

    public JSONResult updateAdminUserToRole(UpdateAdminUserToRoleVo vo) throws Exception {
        if (vo.getAdminUserId() == null) {
            throw new Exception("参数丢失");
        }
        return adminUserToRoleService.updateAdminUserToRole(vo.getRoleIds(), vo.getAdminUserId());
    }
}
