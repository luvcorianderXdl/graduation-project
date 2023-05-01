package com.qdu.graduationProject.wechatApplet.repository;

import com.qdu.graduationProject.wechatApplet.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query(value = "select * from p_post where type = :type and use_flag = 1 and post_status = 2",nativeQuery = true)
    List<Post> getPostsByType(Long type);
}
