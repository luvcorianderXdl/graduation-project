package com.qdu.graduationProject.managementSystem.service;

import com.qdu.graduationProject.commonUtils.utils.DateUtil;
import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.LayUITableJSONResult;
import com.qdu.graduationProject.managementSystem.entity.Role;
import com.qdu.graduationProject.managementSystem.repository.RoleRepository;
import com.qdu.graduationProject.managementSystem.vo.AddRoleVo;
import com.qdu.graduationProject.managementSystem.vo.UpdateRoleVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author xdl
 * @date 2023/4/3 10:07
 */
@Service
@Transactional
public class RoleService {
    @Resource
    private RoleRepository roleRepository;

    public LayUITableJSONResult getByPage(int pageNo, int pageSize) throws Exception {
        Integer offset = (pageNo - 1) * pageSize;
        Integer totalCount = roleRepository.getTotalCount();
        if (totalCount < offset) {
            throw new Exception("请求参数错误");
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Role> list = roleRepository.findAll(pageable);
        return LayUITableJSONResult.ok(totalCount, list.getContent());
    }

    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }

    public JSONResult updateRole(UpdateRoleVo vo, Long modifyUserId) {
        roleRepository.updateRole(vo.getId(), vo.getName(), vo.getDescription(), DateUtil.getCurrentTimestamp(), modifyUserId);
        return JSONResult.ok("修改成功");
    }

    public JSONResult addRole(AddRoleVo vo, Long id) {
        Role role = new Role();
        role.setName(vo.getName());
        role.setDescription(vo.getDescription());
        role.setCreateTime(DateUtil.getCurrentTimestamp());
        role.setModifyTime(null);
        role.setModifyUserId(id);
        roleRepository.save(role);
        return JSONResult.ok("添加成功");
    }
}
