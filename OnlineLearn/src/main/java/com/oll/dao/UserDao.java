package com.oll.dao;

import com.oll.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by NewDarker on 2018/1/3.
 */
@Repository
public interface UserDao extends JpaRepository<User,Long>  {
     /**
      * 登录验证
      * @param userName
      * @return
      */
     User getUserByUsernameAndIsdel(String userName,String isDel);
     /**
      * 修改密码
      * @param userName
      * @param newPwd
      * @return
      */
     @Modifying
     @Transactional
     @Query("update User u set u.password = ?2 where u.username = ?1")
     Integer updatePwd(String userName,String newPwd);
     /**
      * 修改用户权限
      * @param userName
      * @param grade
      * @return
      */
     //@CachePut(key = "#p0")
     @Modifying
     @Transactional
     @Query("update User u set u.grade = ?2 where u.username = ?1")
     Integer updateGrade(String userName,String grade);
     /**
      * 修改头像
      * @param uid
      * @param imgUrl
      * @return
      */
    // @CachePut(key = "#p0")
     @Modifying
     @Transactional
     @Query("update User u set u.headimg = ?2 where u.uid = ?1")
     Integer updateHeadImg(Long uid,String imgUrl);

     /**
      * 更新用户信息状态
      * @param uid
      * @return
      */
     @Modifying
     @Transactional
     @Query("update User u set u.isPerMsg = 1 where u.uid = ?1")
     Integer alterIsPerMsg(Long uid);

     /**
      * 逻辑删除、恢复删除用户
      * @param uName
      * @return
      */
     @Modifying
     @Transactional
     @Query("update User u set u.isdel = ?2 where u.username = ?1")
     Integer userDelOrRe(String uName,String flag);

     /**
      * 获取已删除用户
      * @param isDel
      * @return
      */
     Page<User> findByIsdel(String isDel, Pageable pageable);

     /**
      * 物理删除用户
      * @param userName
      * @return
      */
     @Transactional
     Integer deleteByUsername(String userName);
}
