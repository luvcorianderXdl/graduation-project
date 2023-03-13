package com.qdu.graduationProject.wechatApplet.service;

import com.qdu.graduationProject.wechatApplet.entity.TestUser;
import com.qdu.graduationProject.wechatApplet.repository.TestRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xdl
 * @create 2023/3/7 16:25
 */
@Service
public class TestService {

    @Resource
    private TestRepository testRepository;

    public TestUser getUser(Long id) {
        //书写数据库操作逻辑
        return testRepository.testFindById(id);
    }
}
