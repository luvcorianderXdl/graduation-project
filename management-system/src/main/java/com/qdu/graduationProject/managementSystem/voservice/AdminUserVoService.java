package com.qdu.graduationProject.managementSystem.voservice;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.JSONUtil;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.service.AdminUserService;
import com.qdu.graduationProject.managementSystem.vo.AddAdminUserVo;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xdl
 * @create 2023/3/9 14:04
 */
@Service
public class AdminUserVoService {

    @Resource
    private AdminUserService adminUserService;

    public void login(HttpServletRequest req, HttpServletResponse resp){
        String loginId = req.getParameter("loginId");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        if(loginId == null || "".equals(loginId)) {
           JSONUtil.toJson(resp,JSONResult.error("用户名禁止为空"));
        } else {
            AdminUser adminUser = adminUserService.login(loginId,password);
            if (adminUser == null) {
                 JSONUtil.toJson(resp,JSONResult.error("用户名或密码错误"));
            } else {
                session.setAttribute("adminUser", adminUser);
                JSONUtil.toJson(resp,JSONResult.ok("登录成功"));
            }
        }
    }

    public JSONResult changePassword(String loginId,String oldPass, String newPass, String confirmPass){
        if(loginId == null ||oldPass == null ||newPass == null ||confirmPass == null||"".equals(loginId)||"".equals(oldPass)||"".equals(newPass)||"".equals(confirmPass)) {
            return JSONResult.error("请求参数禁止为空");
        }else {
            return adminUserService.changePassword(loginId,oldPass,newPass,confirmPass);
        }
    }

    public LayUITableJSONResult getByPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String pageNo = req.getParameter("page");
        String pageSize = req.getParameter("limit");
        if (pageNo == null || pageNo.equals("")) {
            pageNo = "1";
        }
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        return adminUserService.getByPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
    }

    public JSONResult addAdminUser(AddAdminUserVo vo) {
        if(vo.getName() == null||"".equals(vo.getName())) {
            return JSONResult.error("用户名禁止为空");
        }if(vo.getLoginId() == null||"".equals(vo.getLoginId())) {
            return JSONResult.error("登录Id禁止为空");
        }if(vo.getTels() == null||"".equals(vo.getTels())) {
            return JSONResult.error("手机号禁止为空");
        }if(vo.getEmails() == null||"".equals(vo.getEmails())) {
            return JSONResult.error("邮箱禁止为空");
        }if(vo.getDescription() == null||"".equals(vo.getDescription())) {
            return JSONResult.error("账号描述禁止为空");
        }if(vo.getPassword() == null) {
            return JSONResult.error("密码禁止为空");
        }if(vo.getConfirmPass() == null) {
            return JSONResult.error("确认密码禁止为空");
        }
        return adminUserService.addAdminUser(vo);
    }

    public JSONResult deleteByIds(String ids,Long id){
        if(ids == null || "".equals(ids)){
            return JSONResult.error("请选择用户");
        }
        String[] temp = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String s : temp) {
            idList.add(Long.parseLong(s));
        }
        return adminUserService.deleteById(idList,id);
    }
}
