package com.qdu.graduationProject.wechatApplet.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginRequestVo {
    String appid;//必填 小程序 appId:wx628a161492f8488d
    String secret;//必填 小程序 appSecret:925ba47900369cd2044a24261318b307
    String code;//必填 登录时获取的 code，可通过wx.login获取
    String grantType ;//必填 授权类型，此处只需填写 authorization_code
}
