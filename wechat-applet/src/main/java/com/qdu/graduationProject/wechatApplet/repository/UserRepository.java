package com.qdu.graduationProject.wechatApplet.repository;

import com.qdu.graduationProject.wechatApplet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByOpenid(String openid);

    User save(User user);
}
