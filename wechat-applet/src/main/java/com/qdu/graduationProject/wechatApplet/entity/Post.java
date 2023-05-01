package com.qdu.graduationProject.wechatApplet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Table(name = "p_post")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cover")
    private String cover;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "star_num")
    private Long starNum;

    @Column(name = "like_num")
    private Long likeNum;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;


    @Column(name = "use_flag")
    private Integer useFlag;

    @Column(name = "type")
    private Long type;
}
