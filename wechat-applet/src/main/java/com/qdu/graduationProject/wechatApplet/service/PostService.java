package com.qdu.graduationProject.wechatApplet.service;

import com.qdu.graduationProject.wechatApplet.entity.Section;
import com.qdu.graduationProject.wechatApplet.repository.PostRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service

public class PostService {
    @Resource
    private PostRepository postRepository;

    public List<Section> getAllSection(){

        return postRepository.getAllSection();
    }
}
