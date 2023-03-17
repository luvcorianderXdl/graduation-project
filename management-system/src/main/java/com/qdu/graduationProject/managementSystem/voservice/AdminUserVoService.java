package com.qdu.graduationProject.managementSystem.voservice;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.JSONUtil;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.commonUtils.utils.PageInfo;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.service.AdminUserService;
import com.qdu.graduationProject.managementSystem.vo.AdminUserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    public JSONResult changePassword(String loginId,String oldPass, String newPass, String confirmPass) throws Exception {
        if(loginId == null ||oldPass == null ||newPass == null ||confirmPass == null) {
            throw new Exception("请求参数错误");
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
}
