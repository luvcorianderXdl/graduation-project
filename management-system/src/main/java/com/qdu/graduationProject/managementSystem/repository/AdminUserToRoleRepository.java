package com.qdu.graduationProject.managementSystem.repository;

import com.qdu.graduationProject.managementSystem.entity.AdminUserToRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xdl
 * @date 2023/4/3 15:21
 */
@Repository
public interface AdminUserToRoleRepository extends JpaRepository<AdminUserToRole, Long> {

    @Query(value = "select role_id from admin_user_to_role where admin_user_id = :id", nativeQuery = true)
    List<Long> getRoleIdsByAdminUserId(@Param("id") Long id);
    
    void deleteAdminUserToRolesByAdminUserId(Long id);
}
