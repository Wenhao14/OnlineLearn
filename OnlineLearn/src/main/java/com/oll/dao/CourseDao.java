package com.oll.dao;

import com.oll.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by NewDarker on 2018/5/20.
 */
@Repository
public interface CourseDao extends JpaRepository<Course,Long> {

    @Query("select c.cid,c.cimg,c.cname,c.ckeyspeaker from Course c where c.cisdel = 0")
    Page<Course> getHotCoures(Pageable pageable);

    @Query("select c.cid,c.cimg,c.cname,c.ckeyspeaker from Course c where c.cisdel = 0 and c.cispush = 1")
    Page<Course> getPushCourse(Pageable pageable);

    @Query("select c.cid,c.cimg,c.cname,c.ckeyspeaker from Course c where c.cisdel = 0 and c.mid = ?1 ")
    Page<Course> getCourseByMid(Long mid,Pageable pageable);

    @Query("select c.cid,c.cimg,c.cname,c.ckeyspeaker from Course c where c.cisdel = 0 and c.cname like ?1")
    Page<Course> getCourseByCnameLike(String key,Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Course c set c.cseltime = c.cseltime + 1 where c.cid = ?1")
    Integer upCSeltime(Long cid);
}
