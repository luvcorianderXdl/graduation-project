package com.qdu.graduationProject.managementSystem.repository;

import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Map;


/**
 * @author xdl
 * @create 2023/3/9 14:25
 */
@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser,Long> {

    @Query(value = "select password,salt from admin_user where use_flag = 1 and login_id = :loginId",nativeQuery = true)
    Map<String,Object> getLoginInfo(String loginId);

    AdminUser findByLoginId(String loginId);


    @Modifying
    @Query(value = "update admin_user set password = :password where login_id = :loginId",nativeQuery = true)
    void changePassWord(@Param("loginId") String loginId, @Param("password") String password);
}
