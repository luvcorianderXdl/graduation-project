package com.qdu.graduationProject.wechatApplet.voservice;

import cn.hutool.http.HttpUtil;
import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.wechatApplet.vo.LoginRequestVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Service
public class LoginVoService {

    public JSONResult getSessionId( HttpServletResponse resp,LoginRequestVo reqVo){

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type={3}";
        String replaceUrl = url.replace("{0}",reqVo.getAppid()).replace("{1}",reqVo.getSecret()).replace("{2}", reqVo.getCode()).replace("{3}",reqVo.getGrantType());
        String res = HttpUtil.get(replaceUrl);
        resp.setContentType("application/json");
        try {
            System.out.println(res);
            resp.getWriter().write(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONResult.ok(res);
    }
}
