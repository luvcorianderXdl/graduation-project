package com.qdu.graduationProject.managementSystem.service;

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
}
