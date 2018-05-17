package com.oll.dao;

import com.oll.model.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by NewDarker on 2018/5/11.
 */
@Repository
public interface NoticeDao extends JpaRepository<Notice,Long> {
    /**
     * 分页查询公告
     * @param pageable
     * @return
     */
    Page<Notice> findAll(Pageable pageable);

    /**
     * 添加公告
     */
    @Override
    <S extends Notice> S save(S s);

    /**
     * 删除公告
     */
    @Override
    void delete(Long aLong);
}
