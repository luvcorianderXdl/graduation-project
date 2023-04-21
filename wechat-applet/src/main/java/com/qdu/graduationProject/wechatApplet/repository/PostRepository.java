package com.qdu.graduationProject.wechatApplet.repository;

import com.qdu.graduationProject.wechatApplet.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Section,Long> {

    @Query(value = "select * from s_section ",nativeQuery = true)
    List<Section> getAllSection();

}
