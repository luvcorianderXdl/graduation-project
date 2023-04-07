package com.qdu.graduationProject.managementSystem.repository;

import com.qdu.graduationProject.managementSystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

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
    @Query(value = "update role set use_flag= 0,modify_time= :modifyTime,modify_user_id = :id where id in(:ids) and use_flag = 1", nativeQuery = true)
    void deleteByIds(Long id, List<Long> ids, Timestamp modifyTime);

    @Modifying
    @Query(value = "update role set name = :name ,description = :description,modify_time = :modifyTime,modify_user_id = :modifyUserId where id = :id and use_flag = 1", nativeQuery = true)
    void updateRole(@Param("id") Long id, @Param("name") String name, @Param("description") String description, @Param("modifyTime") Timestamp modifyTime, @Param("modifyUserId") Long modifyUserId);

    @Query(value = "select r from Role r where r.useFlag = :useFlag")
    List<Role> getAllByUseFlag(Integer useFlag);
}
