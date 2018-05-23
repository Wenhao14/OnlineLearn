package com.oll.dao;

import com.oll.model.Selcourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by NewDarker on 2018/5/23.
 */
@Repository
public interface SelcourseDao extends JpaRepository<Selcourse,Long> {
      Selcourse getSelcourseByUidAndCid(Long uid,Long cid);
}
