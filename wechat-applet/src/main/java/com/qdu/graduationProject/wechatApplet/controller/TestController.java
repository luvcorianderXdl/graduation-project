package com.qdu.graduationProject.wechatApplet.controller;


import com.qdu.graduationProject.wechatApplet.vo.TestVo;
import com.qdu.graduationProject.wechatApplet.voservice.TestVoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * @author: xdl
 * @Created: 2023/3/7 15:19
 */
@Controller
@ResponseBody
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestVoService testVoService;

    @RequestMapping(value = "/test",method = {RequestMethod.POST})
    @ResponseBody
    public Object Test(@RequestBody TestVo testVo){
        try {
            return testVoService.getUser(testVo);
        } catch (Exception e) {
             e.printStackTrace();
             return e.getMessage();
        }
    }

}
