package com.qdu.graduationProject.managementSystem.voservice;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.managementSystem.service.SectionService;
import com.qdu.graduationProject.managementSystem.vo.AddSectionVo;
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

    public LayUITableJSONResult getByPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String pageNo = req.getParameter("page");
        String pageSize = req.getParameter("limit");
        if (pageNo == null || pageNo.equals("")) {
            pageNo = "1";
        }
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        return sectionService.getByPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
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
}
