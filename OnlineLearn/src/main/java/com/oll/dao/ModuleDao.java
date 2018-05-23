package com.oll.dao;

import com.oll.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

/**
 * Created by NewDarker on 2018/5/23.
 */
@Resource
public interface ModuleDao extends JpaRepository<Module,Long>{

}
