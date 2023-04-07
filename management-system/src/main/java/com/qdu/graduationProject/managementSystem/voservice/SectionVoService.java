package com.qdu.graduationProject.managementSystem.voservice;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.commonUtils.utils.UrlPrefixUtil;
import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import com.qdu.graduationProject.managementSystem.entity.Section;
import com.qdu.graduationProject.managementSystem.service.AdminUserToRoleService;
import com.qdu.graduationProject.managementSystem.service.SectionService;
import com.qdu.graduationProject.managementSystem.vo.AddSectionVo;
import com.qdu.graduationProject.managementSystem.vo.UpdateSectionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xdl
 * @date 2023/3/28 17:37
 */
@Service
public class SectionVoService {

    @Resource
    private SectionService sectionService;

    @Resource
    private AdminUserToRoleService adminUserToRoleService;

    public LayUITableJSONResult getByPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AdminUser adminUser = (AdminUser) req.getSession().getAttribute("adminUser");
        List<Long> roles = adminUserToRoleService.getRoleIdsByAdminUserId(adminUser.getId());
        if (roles != null && roles.contains(2L)) {
            String pageNo = req.getParameter("page");
            String pageSize = req.getParameter("limit");
            if (pageNo == null || pageNo.equals("")) {
                pageNo = "1";
            }
            if (pageSize == null || pageSize.equals("")) {
                pageSize = "10";
            }
            LayUITableJSONResult layUITableJSONResult = sectionService.getByPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
            ((List<Section>) layUITableJSONResult.getData()).forEach(r -> {
                r.setSectionImage(UrlPrefixUtil.getFullSectionPrefix() + r.getSectionImage());
            });
            return layUITableJSONResult;
        } else {
            return LayUITableJSONResult.error("暂无数据");
        }
    }

    public JSONResult deleteByIds(String ids, Long id) {
        if (ids == null || "".equals(ids)) {
            return JSONResult.error("请选择板块");
        }
        String[] temp = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String s : temp) {
            idList.add(Long.parseLong(s));
        }
        return sectionService.deleteById(idList, id);
    }

    public JSONResult addSection(AddSectionVo vo, Long id) {
        if (vo.getSectionName() == null || "".equals(vo.getSectionName())) {
            return JSONResult.error("板块名禁止为空");
        }
        if (vo.getDescription() == null || "".equals(vo.getDescription())) {
            return JSONResult.error("板块介绍禁止为空");
        }
        if (vo.getSectionImage() == null || "".equals(vo.getSectionImage())) {
            return JSONResult.error("请上传图片");
        }
        return sectionService.addSection(vo, id);
    }

    public Section getSectionById(Long id) {
        if (id != null) {
            Section section = sectionService.getSectionById(id);
            section.setSectionImage(UrlPrefixUtil.getFullSectionPrefix() + section.getSectionImage());
            return section;
        }
        return null;
    }

    public JSONResult updateSection(UpdateSectionVo vo, Long id) throws Exception {
        if (vo.getId() == null) {
            throw new Exception("参数缺失");
        }
        if (vo.getSectionName() == null || "".equals(vo.getSectionName())) {
            return JSONResult.error("板块名禁止为空");
        }
        if (vo.getDescription() == null || "".equals(vo.getDescription())) {
            return JSONResult.error("板块介绍禁止为空");
        }
        if (vo.getSectionImage() == null || "".equals(vo.getSectionImage())) {
            return JSONResult.error("请上传图片");
        }
        String[] temp = vo.getSectionImage().split("/");
        vo.setSectionImage(temp[temp.length - 1]);
        return sectionService.updateSection(vo, id);
    }
}
