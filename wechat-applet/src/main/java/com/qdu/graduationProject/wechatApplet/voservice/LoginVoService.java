package com.qdu.graduationProject.wechatApplet.voservice;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.qdu.graduationProject.commonUtils.utils.WXResultCode;
import com.qdu.graduationProject.wechatApplet.entity.User;
import com.qdu.graduationProject.wechatApplet.service.UserService;
import com.qdu.graduationProject.wechatApplet.vo.LoginRequestVo;
import com.qdu.graduationProject.wechatApplet.vo.LoginResponseVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class LoginVoService {
    @Resource
    private UserService userService;

    public String getSessionId(LoginRequestVo reqVo) {
        //构造url并请求
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type={3}";
        String replaceUrl = url.replace("{0}", reqVo.getAppid()).replace("{1}", reqVo.getSecret()).replace("{2}", reqVo.getCode()).replace("{3}", reqVo.getGrantType());
        String res = HttpUtil.get(replaceUrl);
        System.out.println(reqVo);

        //json字符串转成对象
        LoginResponseVo respVo = JSONUtil.toBean(res, LoginResponseVo.class);
        User userInfo = userService.getUserByOpenid(respVo.getOpenid());
        if(userInfo!=null){
            respVo.setUserInfo(userInfo);
        }else {
            userInfo = new User();
            userInfo.setOpenid(respVo.getOpenid());
            userInfo.setName("微信用户");
            userService.addUser(userInfo);
        }

        //TODO 打印结果看看后面删除
        System.out.println(respVo);

        //如果出错，通过返回的错误码识别错误信息
        if (respVo.getErrcode() != 0) {
            respVo.setErrmsg(WXResultCode.getErrorMessage(respVo.getErrcode()));
        }
        //转回json传回去
        return JSONUtil.toJsonStr(respVo);
    }
}
