package com.qdu.graduationProject.managementSystem.service;

import com.qdu.graduationProject.commonUtils.utils.*;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.repository.AdminUserRepository;
import com.qdu.graduationProject.managementSystem.vo.AddAdminUserVo;
import com.qdu.graduationProject.managementSystem.vo.UpdateAdminUserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xdl
 * @date 2023/3/9 14:09
 */
@Service
@Transactional
public class AdminUserService {

    @Resource
    private AdminUserRepository adminUserRepository;

    public AdminUser login(String loginId, String password) {
        //敏感信息不建议直接在entity出现
        Map<String, Object> passAndSalt = adminUserRepository.getLoginInfo(loginId);
        AdminUser adminUser = adminUserRepository.findByLoginId(loginId);
        if (adminUser != null && passAndSalt != null && passAndSalt.get("password") != null && passAndSalt.get("salt") != null) {
            if (passAndSalt.get("password").equals(MD5Util.MD5Encode(password + passAndSalt.get("salt").toString()))) {
                return adminUser;
            }
        }
        return null;
    }

    public JSONResult changePassword(String loginId, String oldPass, String newPass, String confirmPass) {
        if (!newPass.equals(confirmPass)) {
            return JSONResult.error("两次输入密码不一致");
        } else {
            Map<String, Object> passAndSalt = adminUserRepository.getLoginInfo(loginId);
            if (passAndSalt != null && passAndSalt.get("password") != null && passAndSalt.get("salt") != null) {
                if (passAndSalt.get("password").equals(MD5Util.MD5Encode(oldPass + passAndSalt.get("salt").toString()))) {

                    adminUserRepository.changePassWord(loginId, MD5Util.MD5Encode(newPass + passAndSalt.get("salt").toString()));
                    return JSONResult.ok("修改成功,即将重新登录");
                }
            }
            return JSONResult.error("密码错误");
        }
    }

    public LayUITableJSONResult getByPage(Integer pageNo, Integer pageSize) throws Exception {

        Integer offset = (pageNo - 1) * pageSize;
        Integer totalCount = adminUserRepository.getTotalCount();
        if (totalCount < offset) {
            throw new Exception("请求参数错误");
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<AdminUser> list = adminUserRepository.findAllByOrderByUseFlagDesc(pageable);
        return LayUITableJSONResult.ok(totalCount, list.getContent());
    }

    public JSONResult addAdminUser(AddAdminUserVo vo) {
        List<String> loginIds = adminUserRepository.getSameLoginIds(vo.getLoginId());
        if (!loginIds.isEmpty()) {
            return JSONResult.error("登录id已存在");
        }
        if (!vo.getPassword().equals(vo.getConfirmPass())) {
            return JSONResult.error("确认密码错误");
        }
        AdminUser adminUser = new AdminUser();
        adminUser.setName(vo.getName());
        adminUser.setLoginId(vo.getLoginId());
        adminUser.setTels(vo.getTels());
        adminUser.setEmails(vo.getEmails());
        adminUser.setDescription(vo.getDescription());
        Timestamp addTime = DateUtil.getCurrentTimestamp();
        adminUser.setCreateTime(addTime);
        adminUser.setModifyTime(null);
        adminUser.setUseFlag(1);
        adminUserRepository.save(adminUser);
        String salt = RandomStringUtil.randomString(10);
        String password = MD5Util.MD5Encode(vo.getPassword() + salt);
        adminUserRepository.setSaltAndPassword(salt, password, vo.getLoginId());
        return JSONResult.ok("用户添加完毕");
    }

    public JSONResult deleteById(List<Long> idList, Long id) {
        //只调整没有删除的数据
        List<Long> ids = new ArrayList<>();
        idList.forEach(r -> {
            if (adminUserRepository.getUseFlagById(r) == 1) {
                ids.add(r);
            }
        });
        if (ids.isEmpty()) {
            return JSONResult.ok("已删除");
        }
        if (ids.contains(id)) {
            return JSONResult.error("禁止删除本人");
        }
        Timestamp modifyTime = DateUtil.getCurrentTimestamp();
        adminUserRepository.deleteByIds(ids, modifyTime);
        return JSONResult.ok("已删除");
    }

    public AdminUser getAdminUserById(Long id) {
        return adminUserRepository.getAdminUserById(id);
    }

    public JSONResult updateAdminUser(UpdateAdminUserVo vo) {
        AdminUser adminUser = adminUserRepository.getAdminUserById(vo.getId());
        if (adminUser.getUseFlag() == 0) {
            return JSONResult.error("已删除用户,禁止编辑");
        }
        if (vo.getName().equals(adminUser.getName()) && vo.getEmails().equals(adminUser.getEmails()) && vo.getTels().equals(adminUser.getTels()) && vo.getDescription().equals(adminUser.getDescription())) {
            return JSONResult.ok("修改成功");
        }
        adminUserRepository.updateAdminUser(vo.getId(), vo.getName(), vo.getTels(), vo.getEmails(), vo.getDescription(), DateUtil.getCurrentTimestamp());
        return JSONResult.ok("修改成功");
    }
}
