package com.oll.dao;

import com.oll.model.Classhour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by NewDarker on 2018/5/22.
 */
public interface ClasshourDao extends JpaRepository<Classhour,Long> {
    List<Classhour> getClasshourByCid(Long cid);
}
