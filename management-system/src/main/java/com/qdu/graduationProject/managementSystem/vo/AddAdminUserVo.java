package com.qdu.graduationProject.managementSystem.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author xdl
 * @create 2023/3/20 11:37
 */
@Getter
@Setter
@NoArgsConstructor
public class AddAdminUserVo {

    String name;

    String loginId;

    String tels;

    String emails;

    String description;

    String password;

    String confirmPass;
}
