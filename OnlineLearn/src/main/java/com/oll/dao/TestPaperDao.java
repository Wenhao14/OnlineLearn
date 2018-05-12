package com.oll.dao;

import com.oll.model.Testpaper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by NewDarker on 2018/5/12.
 */
public interface TestPaperDao extends JpaRepository<Testpaper,Long> {
    @Override
    <S extends Testpaper> S save(S s);
}
