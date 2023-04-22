package com.qdu.graduationProject.wechatApplet.service;

import com.qdu.graduationProject.wechatApplet.entity.User;
import com.qdu.graduationProject.wechatApplet.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;
    public User getUserByOpenid(String openid){
        return userRepository.findUserByOpenid(openid);
    }
    public void addUser(User user){
        userRepository.save(user);
    }
}
