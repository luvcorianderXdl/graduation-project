package com.qdu.graduationProject.managementSystem.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author xdl
 * @date 2023/4/6 17:04
 */
@Getter
@Setter
@NoArgsConstructor
public class UpdateAdminUserToRoleVo {
    List<Long> roleIds;

    Long adminUserId;
}
