package com.qdu.graduationProject.wechatApplet.controller;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.wechatApplet.vo.LoginRequestVo;
import com.qdu.graduationProject.wechatApplet.voservice.LoginVoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/wxLogin")
public class LoginController {
    @Resource
    private LoginVoService loginVoService;

    @RequestMapping("/getSession")
    @ResponseBody
    public String getSession(@RequestBody LoginRequestVo vo) {
        try {
            return loginVoService.getSessionId(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
