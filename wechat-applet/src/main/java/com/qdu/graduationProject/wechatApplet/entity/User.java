package com.qdu.graduationProject.wechatApplet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author xdl
 * @date 2023/3/23 16:59
 */
@Getter
@Setter
@Table(name = "u_user")
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "grade")
    private String grade;

    @Column(name = "class")
    private Integer classNo;

    @Column(name = "profile_photo")
    private String profilePhoto;

    @Column(name = "personal_signature")
    private String personalSignature;

    @Column(name = "openid")
    private String openid;

    @Column(name = "session_key")
    private String sessionKey;

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
