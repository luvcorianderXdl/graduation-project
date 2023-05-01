package com.qdu.graduationProject.wechatApplet.controller;

import com.qdu.graduationProject.wechatApplet.vo.GridListVo;
import com.qdu.graduationProject.wechatApplet.vo.PostListVo;
import com.qdu.graduationProject.wechatApplet.voservice.PostVoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/getPost")
public class PostController {
    @Resource
    private PostVoService postVoService;

    @RequestMapping("/getGridList")
    @ResponseBody
    public List<GridListVo> getAllSection() {
        return postVoService.getGridList();
    }

    @RequestMapping("/getPostList")
    @ResponseBody
    public List<PostListVo> getPostById(Long type){
        System.out.println(type);
        return postVoService.getPostList(type);
    }
}
