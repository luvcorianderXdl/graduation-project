package com.qdu.graduationProject.managementSystem.repository;

import com.qdu.graduationProject.managementSystem.entity.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author xdl
 * @date 2023/3/28 17:40
 */
@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    @Query(value = "select count(id) from s_section", nativeQuery = true)
    Integer getTotalCount();

    @Query(value = "select use_flag from s_section where id = :id", nativeQuery = true)
    Integer getUseFlagById(@Param("id") Long id);

    @Modifying
    @Query(value = "update s_section set modify_time = :modifyTime,use_flag = 0,modify_user_id = :modifyUserId where id in (:ids)", nativeQuery = true)
    void deleteByIds(@Param("ids") List<Long> ids, @Param("modifyTime") Timestamp modifyTime, @Param("modifyUserId") Long modifyUserId);

    Page<Section> findAllByOrderByUseFlagDesc(Pageable pageable);

    Section getSectionById(Long id);

    @Modifying
    @Query(value = "update s_section set section_name = :sectionName,description =:description,section_image = :sectionImage,modify_time = :modifyTime,modify_user_id =:modifyUserId where id = :id", nativeQuery = true)
    void updateSection(@Param("id") Long id, @Param("sectionName") String sectionName, @Param("description") String description, @Param("sectionImage") String sectionImage, @Param("modifyTime") Timestamp modifyTime, @Param("modifyUserId") Long modifyUserId);

    @Query(value = "select section_image from s_section where use_flag = 1", nativeQuery = true)
    List<String> getImagesByUseFlag();
}
