package com.qdu.graduationProject.managementSystem.service;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.managementSystem.entity.AdminUserToRole;
import com.qdu.graduationProject.managementSystem.repository.AdminUserToRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xdl
 * @date 2023/4/3 15:20
 */
@Service
@Transactional
public class AdminUserToRoleService {

    @Resource
    private AdminUserToRoleRepository adminUserToRoleRepository;

    public List<Long> getRoleIdsByAdminUserId(Long id) {
        return adminUserToRoleRepository.getRoleIdsByAdminUserId(id);
    }

    public JSONResult updateAdminUserToRole(List<Long> roleIds, Long adminUserId) {
        //根据id删除
        adminUserToRoleRepository.deleteAdminUserToRolesByAdminUserId(adminUserId);
        //重新添加
        if (roleIds != null && !roleIds.isEmpty()) {
            roleIds.forEach(roleId -> {
                AdminUserToRole temp = new AdminUserToRole();
                temp.setRoleId(roleId);
                temp.setAdminUserId(adminUserId);
                adminUserToRoleRepository.save(temp);
            });
        }
        return JSONResult.ok("权限修改完成");
    }
}
