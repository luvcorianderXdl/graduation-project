package com.qdu.graduationProject.managementSystem.service;

import com.qdu.graduationProject.commonUtils.utils.MD5Util;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.repository.AdminUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xdl
 * @create 2023/3/9 14:09
 */
@Service
public class AdminUserService {

    @Resource
    private AdminUserRepository adminUserRepository;

    public AdminUser login(String loginId, String password) {
        AdminUser adminUser = adminUserRepository.findByLoginId(loginId);
        if(adminUser != null && adminUser.getPassword().equals(MD5Util.MD5Encode(password + adminUser.getSalt()))) {
            return adminUser;
        } else {
            return null;
        }
    }
}
