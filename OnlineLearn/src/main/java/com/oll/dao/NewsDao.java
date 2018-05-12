package com.oll.dao;

import com.oll.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by NewDarker on 2018/5/11.
 */
public interface NewsDao extends JpaRepository<News,Long> {
    /**
     * 分页查询新闻
     * @param pageable
     * @return
     */
    Page<News> findAll(Pageable pageable);

    /**
     * 添加新闻
     */
    @Override
    <S extends News> S save(S s);

    /**
     * 删除新闻
     */
    @Override
    void delete(Long aLong);
}
