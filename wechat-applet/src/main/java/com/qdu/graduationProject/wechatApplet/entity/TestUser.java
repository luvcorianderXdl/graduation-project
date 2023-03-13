package com.qdu.graduationProject.wechatApplet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xdl
 * @create 2023/3/7 15:46
 */
@Getter
@Setter
@Table(name = "u_user")
@Entity
public class TestUser implements Serializable {
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

    @Column(name = "personal_signature")
    private String personalSignature;

    @Column(name = "class")
    private Integer myClass;

    @Column(name = "use_flag")
    private Integer useFlag;
}
