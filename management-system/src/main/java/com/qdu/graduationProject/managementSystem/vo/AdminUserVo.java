package com.qdu.graduationProject.managementSystem.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author xdl
 * @create 2023/3/16 10:42
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminUserVo {

    String name;

    String loginId;

    String tels;

    String emails;

    String description;

    String createTime;
}
