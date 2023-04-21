package com.qdu.graduationProject.wechatApplet.controller;

import com.qdu.graduationProject.wechatApplet.entity.Section;
import com.qdu.graduationProject.wechatApplet.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/getPost")
public class PostController {
    @Resource
    private PostService postService;

    @RequestMapping("/getGridList")
    @ResponseBody
    public List<Section> getAllSection() {
        return postService.getAllSection();
    }
}
