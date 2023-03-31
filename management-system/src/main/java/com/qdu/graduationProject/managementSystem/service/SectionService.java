package com.qdu.graduationProject.managementSystem.service;

import com.qdu.graduationProject.commonUtils.utils.DateUtil;
import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.managementSystem.entity.Section;
import com.qdu.graduationProject.managementSystem.repository.SectionRepository;
import com.qdu.graduationProject.managementSystem.vo.AddSectionVo;
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

/**
 * @author xdl
 * @date 2023/3/28 17:39
 */
@Service
@Transactional
public class SectionService {

    @Resource
    private SectionRepository sectionRepository;

    public LayUITableJSONResult getByPage(Integer pageNo, Integer pageSize) throws Exception {
        Integer offset = (pageNo - 1) * pageSize;
        Integer totalCount = sectionRepository.getTotalCount();
        if (totalCount < offset) {
            throw new Exception("请求参数错误");
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Section> list = sectionRepository.findAllByOrderByUseFlagDesc(pageable);
        return LayUITableJSONResult.ok(totalCount, list.getContent());
    }

    public JSONResult deleteById(List<Long> idList, Long id) {
        //只调整没有删除的数据
        List<Long> ids = new ArrayList<>();
        idList.forEach(r -> {
            if (sectionRepository.getUseFlagById(r) == 1) {
                ids.add(r);
            }
        });
        if (ids.isEmpty()) {
            return JSONResult.ok("已删除");
        }
        Timestamp modifyTime = DateUtil.getCurrentTimestamp();
        sectionRepository.deleteByIds(ids, modifyTime, id);
        return JSONResult.ok("已删除");
    }

    public JSONResult addSection(AddSectionVo vo, Long id) {
        Section section = new Section();
        section.setSectionName(vo.getSectionName());
        section.setDescription(vo.getDescription());
        section.setSectionImage(vo.getSectionImage());
        Timestamp createTime = DateUtil.getCurrentTimestamp();
        section.setCreateTime(createTime);
        section.setModifyUserId(id);
        section.setModifyTime(null);
        section.setUseFlag(1);
        sectionRepository.save(section);
        return JSONResult.ok("板块添加完毕");
    }
}
