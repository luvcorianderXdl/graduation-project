package com.qdu.graduationProject.wechatApplet.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseVo {
    String sessionKey;//会话密钥
    String unionid;//用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回，详见 UnionID 机制说明。
    String errmsg;//错误信息
    String openid;//用户唯一标识
    int errcode;//错误码

}
