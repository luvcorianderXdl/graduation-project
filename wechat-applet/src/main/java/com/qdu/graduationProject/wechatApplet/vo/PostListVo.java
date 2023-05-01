package com.qdu.graduationProject.wechatApplet.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostListVo {
    private Long id;
    private String title;
    private String cover;

    private String description;

    private String content;

    private Long starNum;

    private Timestamp createTime;
}
