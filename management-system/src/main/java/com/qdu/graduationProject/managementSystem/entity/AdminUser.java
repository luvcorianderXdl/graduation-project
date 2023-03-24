package com.qdu.graduationProject.managementSystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
    private String createTime;

    @Column(name = "deleteTime")
    private String deleteTime;

    @Column(name = "use_flag")
    private Integer useFlag;


}