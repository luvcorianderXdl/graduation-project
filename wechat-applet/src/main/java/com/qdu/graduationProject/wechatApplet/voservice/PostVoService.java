package com.qdu.graduationProject.wechatApplet.voservice;

import com.qdu.graduationProject.wechatApplet.entity.Section;
import com.qdu.graduationProject.wechatApplet.service.PostService;
import com.qdu.graduationProject.wechatApplet.vo.GridListVo;
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

}
