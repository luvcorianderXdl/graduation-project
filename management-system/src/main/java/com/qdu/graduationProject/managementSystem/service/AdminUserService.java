package com.qdu.graduationProject.managementSystem.service;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.commonUtils.utils.MD5Util;
import com.qdu.graduationProject.commonUtils.utils.RandomStringUtil;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.repository.AdminUserRepository;
import com.qdu.graduationProject.managementSystem.vo.AddAdminUserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                    return JSONResult.ok("修改成功,即将重新登录");
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

    public JSONResult addAdminUser(AddAdminUserVo vo) {
        List<String> loginIds = adminUserRepository.getSameLoginIds(vo.getLoginId());
        if(!loginIds.isEmpty()) {
            return JSONResult.error("登录id已存在");
        }
        if(!vo.getPassword().equals(vo.getConfirmPass())){
            return JSONResult.error("确认密码错误");
        }
        AdminUser adminUser = new AdminUser();
        adminUser.setName(vo.getName());
        adminUser.setLoginId(vo.getLoginId());
        adminUser.setTels(vo.getTels());
        adminUser.setEmails(vo.getEmails());
        adminUser.setDescription(vo.getDescription());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(date);
        adminUser.setCreateTime(formattedDate);
        adminUser.setDeleteTime("");
        adminUser.setUseFlag(1);
        adminUserRepository.save(adminUser);
        String salt = RandomStringUtil.randomString(10);
        String password = MD5Util.MD5Encode(vo.getPassword() + salt);
        adminUserRepository.setSaltAndPassword(salt,password,vo.getLoginId());
        return JSONResult.ok("用户添加完毕");
    }
}
