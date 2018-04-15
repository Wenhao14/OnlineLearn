package com.oll.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.oll.cache.ShareLogin;
import com.oll.dao.UserDao;
import com.oll.model.User;
import com.oll.util.BaseRtM;
import com.oll.util.MD5Encrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by NewDarker on 2018/1/3.
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private  MD5Encrypt md5Encrypt;
    @Resource
    private ShareLogin shareLogin;

    /**
     * 用户登录
     */
    public Boolean userLogin(String userName,String passWord){
        User user = userDao.getUserByUsername(userName);
        if(user != null){
            if(user.getPassword().equals(md5Encrypt.toEncryptString(passWord))){
                user.setPassword(null);
                shareLogin.setSession(user);
                return true;
            }else{
                throw new RuntimeException("密码错误");
            }
        }else{
            throw new RuntimeException("用户名不存在");
        }
    }

    /**
     * 批量用户注册
     * @param json
     */
    public BaseRtM addUser(String json){
        JSONArray list =  JSON.parseArray(json);
        int len = list.size();
        User user;
        String username;
        for(int i = 0;i < len;i++){
            user = new User();
        }
        return null;
    }
    /**
     * 完善/更新用户信息
     * @return
     */
    public BaseRtM messagePerfect(String username,String realname,String email,String phone,String feeling ,String department ){
        return null;
    }

    /**
     * 更新头像
     * @param username
     * @param img
     * @return
     */
    public BaseRtM updataUserHeadImg(String username,String img){
        return null;
    }

    /**
     * 修改密码
     * @param username
     * @param password
     * @return
     */
    public BaseRtM updataUPwd(String username,String password){
        return null;
    }

    /**
     * 更新用户排名
     */
    public void userRank(){

    }
}
