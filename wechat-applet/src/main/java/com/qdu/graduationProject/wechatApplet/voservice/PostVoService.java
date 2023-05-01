package com.qdu.graduationProject.wechatApplet.voservice;

import com.qdu.graduationProject.wechatApplet.entity.Post;
import com.qdu.graduationProject.wechatApplet.entity.Section;
import com.qdu.graduationProject.wechatApplet.service.PostService;
import com.qdu.graduationProject.wechatApplet.vo.GridListVo;
import com.qdu.graduationProject.wechatApplet.vo.PostListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostVoService {

    @Resource
    private PostService postService;
    public List<GridListVo> getGridList(){
        List<Section> sectionList = postService.getAllSection();
        return sectionList.stream().map(section -> new GridListVo(section.getId(),section.getSectionName(),section.getSectionImage())).collect(Collectors.toList());
    }
    public List<PostListVo> getPostList(Long type){
        List<Post> postList = postService.getPostById(type);
        System.out.println(postList);
        return postList.stream().map(post -> new PostListVo(post.getId(),post.getTitle(),post.getCover(),post.getDescription(),post.getContent(),post.getStarNum(),post.getCreateTime())).collect(Collectors.toList());
    }

}
