package com.qdu.graduationProject.wechatApplet.service;

import com.qdu.graduationProject.wechatApplet.entity.Post;
import com.qdu.graduationProject.wechatApplet.entity.Section;
import com.qdu.graduationProject.wechatApplet.repository.PostRepository;
import com.qdu.graduationProject.wechatApplet.repository.SectionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service

public class PostService {
    @Resource
    private SectionRepository sectionRepository;

    @Resource
    private PostRepository postRepository;
    public List<Section> getAllSection(){

        return sectionRepository.getAllSection();
    }
    public List<Post> getPostById(Long type){
        return postRepository.getPostsByType(type);
    }
}
