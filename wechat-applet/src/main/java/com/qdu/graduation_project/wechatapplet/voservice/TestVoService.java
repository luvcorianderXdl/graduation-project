package com.qdu.graduation_project.wechatapplet.voservice;


import com.qdu.graduation_project.wechatapplet.entity.TestUser;
import com.qdu.graduation_project.wechatapplet.service.TestService;
import com.qdu.graduation_project.wechatapplet.vo.TestVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xdl
 * @create 2023/3/7 16:01
 */
@Service
public class TestVoService {

    @Resource
    private TestService testService;

    public TestUser getUser(TestVo testVo) throws Exception{
        //在此对请求结构提取出请求参数(HttpRequest,vo等)
        Long id = testVo.getId();

        //做合法性分析
        if(id == null){
            throw new Exception("请求参数缺失");
        } else {
            return testService.getUser(id);
        }
    }
}
