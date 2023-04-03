package com.qdu.graduationProject.managementSystem.voservice;

import com.qdu.graduationProject.managementSystem.service.AdminUserToRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xdl
 * @date 2023/4/3 15:16
 */
@Service
public class AdminUserToRoleVoService {

    @Resource
    private AdminUserToRoleService adminUserToRoleService;

    public List<Long> getRoleIdsByAdminUserId(Long id) {
        return adminUserToRoleService.getRoleIdsByAdminUserId(id);
    }
}
