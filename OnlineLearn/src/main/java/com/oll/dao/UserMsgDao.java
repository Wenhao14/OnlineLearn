package com.oll.dao;

import com.oll.model.UserMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by NewDarker on 2018/5/10.
 */
@Repository
public interface UserMsgDao extends JpaRepository<UserMsg,Long> {
    /**
     * 按积分降序查询所有用户
     * @return
     */
    //@Cacheable(key = "#p0")
    @Query("select um from UserMsg um order by um.ugoal DESC ")
    List<UserMsg> findAllUserMsg();
//    /**
//     * 更新排名
//     * @param uid
//     * @param rank
//     * @return
//     */
//    //@CachePut(key = "#p0")
//    @Query("update UserMsg um set um.urank = ?2 where um.uid = ?1 ")
//    Integer upUBank(Long uid,String rank);

    /**
     * 完善信息
     * @param s
     * @param <S>
     * @return
     */
    <S extends UserMsg> S save(S s);

    /**
     * 通过uid获取UserMsg实体
     * @param uid
     * @return
     */
    UserMsg findUserMsgByUid(Long uid);

    /**
     * 修改邮箱
     * @param uid
     * @param email
     * @return
     */
    @Modifying
    @Transactional
    @Query("update UserMsg um set um.umail = ?2 where um.uid = ?1")
    Integer updateUMail(Long uid,String email);

    /**
     * 修改手机号
     * @param uid
     * @param phone
     * @return
     */
    @Modifying
    @Transactional
    @Query("update UserMsg um set um.uphone = ?2 where um.uid = ?1")
    Integer updateUPhone(Long uid,String phone);

}
