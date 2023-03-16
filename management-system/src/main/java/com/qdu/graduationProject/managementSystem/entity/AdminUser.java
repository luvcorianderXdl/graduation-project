package com.qdu.graduationProject.managementSystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xdl
 * @create 2023/3/9 12:18
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

    @Column(name = "password")
    private String password;

    @Column(name = "use_flag")
    private Integer useFlag;

    @Column(name = "salt")
    private String salt;
}