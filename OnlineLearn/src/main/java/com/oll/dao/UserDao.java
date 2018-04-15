package com.oll.dao;

import com.oll.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by NewDarker on 2018/1/3.
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {

     User getUserByUsername(String userName);

}
