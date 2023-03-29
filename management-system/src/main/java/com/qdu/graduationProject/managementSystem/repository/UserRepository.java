package com.qdu.graduationProject.managementSystem.repository;

import com.qdu.graduationProject.managementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author xdl
 * @date 2023/3/28 15:39
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select count(id) from u_user", nativeQuery = true)
    Integer getTotalCount();

    @Query(value = "select use_flag from u_user where id = :id", nativeQuery = true)
    Integer getUseFlagById(@Param("id") Long id);

    @Modifying
    @Query(value = "update u_user set modify_time = :modifyTime,delete_user_id = :deleteUserId,use_flag = 0 where id in (:ids)", nativeQuery = true)
    void deleteByIds(@Param("ids") List<Long> ids, @Param("deleteUserId") Long deleteUserId, @Param("modifyTime") Timestamp deleteTime);

}
