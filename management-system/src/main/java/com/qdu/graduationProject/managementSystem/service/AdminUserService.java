package com.qdu.graduationProject.managementSystem.service;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.commonUtils.utils.MD5Util;
import com.qdu.graduationProject.commonUtils.utils.PageInfo;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.repository.AdminUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xdl
 * @create 2023/3/9 14:09
 */
@Service
@Transactional
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

    public JSONResult changePassword(String loginId,String oldPass, String newPass,String confirmPass) {
        if (!newPass.equals(confirmPass)) {
            return JSONResult.error("两次输入密码不一致");
        } else {
            Map<String, Object> passAndSalt = adminUserRepository.getLoginInfo(loginId);
            if (passAndSalt != null && passAndSalt.get("password") != null && passAndSalt.get("salt") != null) {
                if (passAndSalt.get("password").equals(MD5Util.MD5Encode(oldPass + passAndSalt.get("salt").toString()))) {
                    adminUserRepository.changePassWord(loginId, MD5Util.MD5Encode(newPass + passAndSalt.get("salt").toString()));
                    return JSONResult.ok("修改成功");
                }
            }
            return JSONResult.error("密码错误");
        }
    }

    public LayUITableJSONResult getByPage(Integer pageNo, Integer pageSize) throws Exception {
        Integer offset = (pageNo -1 ) *pageSize;
        Integer totalCount = adminUserRepository.getTotalCount();
        if(totalCount < offset) {
            throw new Exception("请求参数错误");
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable= PageRequest.of(pageNo - 1, pageSize, sort );
        Page<AdminUser> list = adminUserRepository.findAll(pageable);
        return LayUITableJSONResult.ok(totalCount,list.getContent());
    }
}
