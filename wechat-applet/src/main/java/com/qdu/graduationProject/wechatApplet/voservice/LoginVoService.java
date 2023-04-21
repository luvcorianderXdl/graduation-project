package com.qdu.graduationProject.wechatApplet.voservice;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.WXResultCode;
import com.qdu.graduationProject.wechatApplet.vo.LoginRequestVo;
import com.qdu.graduationProject.wechatApplet.vo.LoginResponseVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Service
public class LoginVoService {

    public String getSessionId(LoginRequestVo reqVo) throws Exception {
        //构造url并请求
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type={3}";
        String replaceUrl = url.replace("{0}",reqVo.getAppid()).replace("{1}",reqVo.getSecret()).replace("{2}", reqVo.getCode()).replace("{3}",reqVo.getGrantType());
        String res = HttpUtil.get(replaceUrl);
        System.out.println(reqVo);
        LoginResponseVo respVo = JSONUtil.toBean(res,LoginResponseVo.class);

        //TODO 打印结果看看后面删除
        System.out.println(respVo.toString());

        //如果出错，通过返回的错误码识别错误信息
        if(respVo.getErrcode()!=0){
            respVo.setErrmsg(WXResultCode.getErrorMessage(respVo.getErrcode()));
        }
        return JSONUtil.toJsonStr(respVo);
    }
}
