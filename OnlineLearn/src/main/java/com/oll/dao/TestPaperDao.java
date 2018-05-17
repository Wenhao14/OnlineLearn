package com.oll.dao;

import com.oll.model.Testpaper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by NewDarker on 2018/5/12.
 */
@Repository
public interface TestPaperDao extends JpaRepository<Testpaper,Long> {
    @Override
    <S extends Testpaper> S save(S s);
    @Query("select tp from Testpaper tp where tp.tpisdel = '0' and tp.tpid not in ?1 and tp.tppassdate >= ?2")
    Page<Testpaper> getUnTPs(List<Long> tpids, Date now,Pageable pageable);
//    @Query("select tp from Testpaper  tp where tp.tpisdel = '0' and tp.tpid in ?1")
//    Page<Testpaper> getEnTps(List<Long> tpids,Pageable pageable);
}
