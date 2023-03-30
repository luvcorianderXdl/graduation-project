package com.qdu.graduationProject.managementSystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author xdl
 * @date 2023/3/28 16:55
 */
@Getter
@Setter
@Table(name = "s_section")
@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "section_name")
    private String sectionName;

    @Column(name = "description")
    private String description;

    @Column(name = "section_image")
    private String sectionImage;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @Column(name = "modify_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp modifyTime;

    @Column(name = "modify_user_id")
    private Long modifyUserId;

    @Column(name = "use_flag")
    private Integer useFlag;
}
