package com.qdu.graduation_project.wechatapplet.repository;


import com.qdu.graduation_project.wechatapplet.entity.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xdl
 * @create 2023/3/7 16:27
 */
@Repository
public interface TestRepository extends JpaRepository<TestUser,Long> {

    @Query(value = "select tu from TestUser tu where tu.id = :id and tu.useFlag = 1",nativeQuery = false)
    TestUser testFindById(@Param("id") Long id);
}
