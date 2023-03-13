package com.qdu.graduationProject.managementSystem.repository;

import com.qdu.graduationProject.managementSystem.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author xdl
 * @create 2023/3/9 14:25
 */
@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser,Long> {

    AdminUser findByName(String userName);
}
