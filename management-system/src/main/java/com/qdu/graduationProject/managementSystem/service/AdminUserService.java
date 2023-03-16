package com.qdu.graduationProject.managementSystem.service;

import com.qdu.graduationProject.commonUtils.utils.MD5Util;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.repository.AdminUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xdl
 * @create 2023/3/9 14:09
 */
@Service
public class AdminUserService {

    @Resource
    private AdminUserRepository adminUserRepository;

    public AdminUser login(String loginId, String password) {
        //敏感信息不建议直接在entity出现
        Map<String,Object> passAndSalt = adminUserRepository.getLoginInfo(loginId);
        AdminUser adminUser = adminUserRepository.findByLoginId(loginId);
        if(adminUser != null && passAndSalt != null && passAndSalt.get("password") != null && passAndSalt.get("salt") != null) {
            if(passAndSalt.get("password").equals(MD5Util.MD5Encode(password + passAndSalt.get("salt").toString()))) {
                return adminUser;
            }
        }
        return null;
    }

}
