package com.qdu.graduationProject.managementSystem.repository;

import com.qdu.graduationProject.managementSystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @author xdl
 * @date 2023/4/3 10:09
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select count(id) from role", nativeQuery = true)
    Integer getTotalCount();

    Role getRoleById(Long id);


    @Modifying
    @Query(value = "update role set name = :name ,description = :description,modify_time = :modifyTime,modify_user_id = :modifyUserId where id = :id", nativeQuery = true)
    void updateRole(@Param("id") Long id, @Param("name") String name, @Param("description") String description, @Param("modifyTime") Timestamp modifyTime, @Param("modifyUserId") Long modifyUserId);
}
