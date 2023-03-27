package com.qdu.graduationProject.managementSystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author xdl
 * @date 2023/3/9 12:18
 */
@Getter
@Setter
@Table(name = "admin_user")
@Entity
public class AdminUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login_id")
    private String loginId;

//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "salt")
//    private String salt;

    @Column(name = "tels")
    private String tels;

    @Column(name = "emails")
    private String emails;

    @Column(name = "description")
    private String description;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @Column(name = "modify_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp modifyTime;

    @Column(name = "deleteTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp deleteTime;
    
    @Column(name = "use_flag")
    private Integer useFlag;


}