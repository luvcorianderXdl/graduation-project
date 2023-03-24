package com.qdu.graduationProject.managementSystem.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author xdl
 * @date 2023/3/23 14:05
 */
@Getter
@Setter
@NoArgsConstructor
public class UpdateAdminUserVo {
    Long id;

    String name;

    String tels;

    String emails;

    String description;
}
