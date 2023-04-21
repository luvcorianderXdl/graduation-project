package com.qdu.graduationProject.managementSystem.voservice;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.entity.User;
import com.qdu.graduationProject.managementSystem.service.AdminUserToRoleService;
import com.qdu.graduationProject.managementSystem.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xdl
 * @date 2023/3/28 15:35
 */
@Service
public class UserVoService {

    @Resource
    private UserService userService;

    @Resource
    private AdminUserToRoleService adminUserToRoleService;

    public LayUITableJSONResult getByPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AdminUser adminUser = (AdminUser) req.getSession().getAttribute("adminUser");
        List<Long> roles = adminUserToRoleService.getRoleIdsByAdminUserId(adminUser.getId());
        if (roles != null && roles.contains(1L)) {
            String pageNo = req.getParameter("page");
            String pageSize = req.getParameter("limit");
            if (pageNo == null || pageNo.equals("")) {
                pageNo = "1";
            }
            if (pageSize == null || pageSize.equals("")) {
                pageSize = "10";
            }
            return userService.getByPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        } else {
            return LayUITableJSONResult.error("暂无数据");
        }
    }

    public JSONResult deleteByIds(String ids, Long id) {
        if (ids == null || "".equals(ids)) {
            return JSONResult.error("请选择用户");
        }
        String[] temp = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String s : temp) {
            idList.add(Long.parseLong(s));
        }
        return userService.deleteById(idList, id);
    }

    public LayUITableJSONResult getByPageAndOpenId(HttpServletRequest req, HttpServletResponse resp, String openId) throws Exception {
        AdminUser adminUser = (AdminUser) req.getSession().getAttribute("adminUser");
        List<Long> roles = adminUserToRoleService.getRoleIdsByAdminUserId(adminUser.getId());
        if (roles != null && roles.contains(1L)) {
            if (openId == null || "".equals(openId)) {
                return LayUITableJSONResult.error("暂无数据");
            }
            List<String> openIds = new ArrayList<>();
            Collections.addAll(openIds, openId.split(","));
            List<User> users = userService.getByOpenId(openIds);
            if (users != null) {
                LayUITableJSONResult result = new LayUITableJSONResult();
                result.setCode(0);
                result.setData(users);
                result.setMsg("查询成功");
                result.setCount(1);
                return result;
            }
        }
        return LayUITableJSONResult.error("暂无数据");
    }
}
