package com.qdu.graduationProject.managementSystem.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author xdl
 * @date 2023/3/31 11:36
 */
@Getter
@Setter
@NoArgsConstructor
public class UpdateSectionVo {
    Long id;

    String sectionName;

    String description;

    String sectionImage;
}
