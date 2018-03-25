package com.oll.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.oll.dao.UserDao;
import com.oll.model.User;
import com.oll.util.BaseRtM;
import com.oll.util.MD5Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by NewDarker on 2018/1/3.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    /**
     * 批量用户注册
     * @param json
     */
    public BaseRtM addUser(String json){
        JSONArray list =  JSON.parseArray(json);
        int len = list.size();
        User user;
        String username;
        BaseRtM baseRtM = new BaseRtM();
        baseRtM.setRtMCode(0);
        baseRtM.setRtMContext("无异常");
        List<BaseRtM> results = new ArrayList<>();
        for(int i = 0;i < len;i++){
            user = new User();
            username = list.getJSONObject(i).getString("工号");
            user.setUsername(username);
            user.setPassword(MD5Encrypt.toEncryptString(username));
            user.setScale(1);
            user.setHeadimg("");
            user.setRegistdate(new Date());
            user.setIntegral(0);
            user.setLearnmin(0);
            try {
                userDao.save(user);
            }catch (Exception e){
                BaseRtM rtm = new BaseRtM();
                rtm.setRtMContext(username);
                rtm.setRtMData(e.getMessage());
                results.add(rtm);
            }
            if(results.size() > 1){
                baseRtM.setRtMCode(1);
                baseRtM.setRtMContext("有异常");
                baseRtM.setRtMData(results);
            }
        }
        return baseRtM;
    }
    /**
     * 完善/更新用户信息
     * @return
     */
    public BaseRtM messagePerfect(String username,String realname,String email,String phone,String feeling ,String department ){
        BaseRtM baseRtM = new BaseRtM();
        try {
            userDao.upUserMessage(realname,email,phone,feeling,department,username);
            baseRtM.setRtMCode(0);
        }catch (Exception e){
            baseRtM.setRtMCode(1);
            baseRtM.setRtMContext(e.getMessage());
        }finally {
            return baseRtM;
        }
    }

    /**
     * 更新头像
     * @param username
     * @param img
     * @return
     */
    public BaseRtM updataUserHeadImg(String username,String img){
        BaseRtM baseRtM = new BaseRtM();
        try{
            userDao.upUserHeadImg(img,username);
            baseRtM.setRtMCode(0);
        }catch (Exception e){
            baseRtM.setRtMCode(1);
            baseRtM.setRtMContext(e.getMessage());
        }finally {
            return baseRtM;
        }
    }

    /**
     * 修改密码
     * @param username
     * @param password
     * @return
     */
    public BaseRtM updataUserPassword(String username,String password){
        BaseRtM baseRtM = new BaseRtM();
        try {
            userDao.upUserPassword(password,username);
            baseRtM.setRtMCode(0);
        }catch (Exception e){
            baseRtM.setRtMCode(1);
            baseRtM.setRtMContext(e.getMessage());
        }finally {
            return baseRtM;
        }
    }

    /**
     * 更新用户排名
     */
    public void userRank(){
        List<String> users = userDao.getAllRank();
        int rank = users.size();
        int i = 0;
        while (rank > 0){
            userDao.upUserRank(rank,users.get(i++));
        }
    }
}
