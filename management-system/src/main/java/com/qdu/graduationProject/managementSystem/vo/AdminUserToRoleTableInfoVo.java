package com.qdu.graduationProject.managementSystem.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author xdl
 * @date 2023/4/6 10:24
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminUserToRoleTableInfoVo {
    Long adminUserId;

    Long check;

    Long roleId;

    String roleName;
}
