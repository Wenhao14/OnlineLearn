package com.oll.dao;

import com.oll.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by NewDarker on 2018/5/15.
 */
@Repository
public interface AnswerDao extends JpaRepository<Answer,Long> {
    @Override
    <S extends Answer> S save(S s);
    @Query("select aw.tpid from Answer aw where aw.uid = ?1")
    List<Long> getTpids(Long uid);
    @Query("select aw.tpid from Answer aw where aw.uid = ?1")
    List<Answer> getAnswerByPageNum(Long uid, Pageable pageable);
}
