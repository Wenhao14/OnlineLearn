package com.oll.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oll.cache.ShareLogin;
import com.oll.dao.UserDao;
import com.oll.dao.UserMsgDao;
import com.oll.model.User;
import com.oll.model.UserMsg;
import com.oll.util.BaseRtM;
import com.oll.util.MD5Encrypt;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by NewDarker on 2018/1/3.
 * 用户基本服务
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private  MD5Encrypt md5Encrypt;
    @Resource
    private ShareLogin shareLogin;
    @Resource
    private UserMsgDao userMsgDao;

    private static final String randomPwdPool = "VWX03abcdefg47M89hmOPQR12STUnoABCqrDEFps56tuzGHIJwKLijklNYvsyZ";
    /**
     * 用户登录
     */
    public Boolean userLogin(String userName,String passWord){
        User user = userDao.getUserByUsername(userName);
        if(user instanceof User){
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
    public Integer patchAddUser(String json){
        Integer successNum = 0;
        JSONArray userList =  JSON.parseArray(json);
        int len = userList.size();
        String username;
        JSONObject jsonObject;
        for(int i = 0;i < len;i++){
            User user = new User();
            jsonObject = (JSONObject) userList.get(i);
            username = jsonObject.getString("用户名");
            user.setUsername(username);
            user.setPassword(md5Encrypt.toEncryptString(username));
            user.setGrade("b");
            user.setIsdel("0");
            user.setHeadimg("xxxx");
            try {
               Object result = userDao.save(user);
               if(result instanceof User){
                   successNum++;
               }
            }catch (Exception e){
                continue;
            }
        }
        return successNum;
    }
    /**
     * 单个增加用户
     */
    public Boolean addUser(String userName,String grade){
        String relGrade;
        switch (grade){
            case "0":{
                relGrade = "s";
                break;
            }
            case "1":{
                relGrade = "a";
                break;
            }
            case "2":{
                relGrade = "b";
                break;
            }
            default:{
                relGrade = "c";
            }
        }
        User user = new User();
        user.setUsername(userName);
        user.setPassword(md5Encrypt.toEncryptString(userName));
        user.setGrade(relGrade);
        user.setIsdel("0");
        user.setHeadimg("/img/undefultUI.jpg");
        try {
            Object result = userDao.save(user);
            if(result instanceof User){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException("该用户已存在!");
        }
    }
    /**
     * 完善/更新用户信息
     * @return
     */
    public Boolean perfectUMsg(String realname,String email,String phone,String department ){
        UserMsg userMsg = new UserMsg();
        userMsg.setUid(shareLogin.getUser().getUid());
        userMsg.setUname(realname);
        userMsg.setUmail(email);
        userMsg.setUphone(phone);
        userMsg.setUsection(department);
        userMsg.setUgoal(new Long(0));
        Object result = userMsgDao.save(userMsg);
        if(result instanceof UserMsg){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 更新头像
     * @param uid
     * @param imgUrl
     * @return
     */
    public Boolean updataUserHeadImg(String uid,String imgUrl){
        Integer result = userDao.updateHeadImg(uid,imgUrl);
        if(result == 1){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 更新信息
     */
    public BaseRtM updataUMsg(String msg,String type){
        Integer result = 0;
        BaseRtM baseRtM = new BaseRtM();
        try {
            Long uid = shareLogin.getUser().getUid();
            if(uid != null){
                if(type.equals("e")){
                    result = userMsgDao.updateUMail(uid,msg);
                }else if(type.equals("p")){
                    result = userMsgDao.updateUPhone(uid,msg);
                }else {
                    baseRtM.setRtMCode("F");
                    baseRtM.setRtMsg("参数异常!");
                }
                if(result == 1){
                    baseRtM.setRtMCode("T");
                    baseRtM.setRtMsg("修改成功!");
                }else {
                    baseRtM.setRtMCode("F");
                    baseRtM.setRtMsg("修改失败!");
                }
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("登录过时，或内部错误!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误!");
        }finally {
            return baseRtM;
        }
    }
    /**
     * 修改密码
     * @param password
     * @return
     */
    public Boolean alterPwd(String password){
        password = md5Encrypt.toEncryptString(password);
        User user = shareLogin.getUser();
        if(user != null){
            user.setPassword(password);
            Object obj = userDao.save(user);
            if(obj instanceof User){
                return true;
            }else {
                return false;
            }
        }else {
           throw new RuntimeException("账户状态异常!");
        }
    }
    /**
     * 更新用户排名
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void userRank(){
        System.out.println("排名运行");
        List<UserMsg> usermsgs = userMsgDao.findAllUserMsg();
        int len = usermsgs.size();
        String rank;
        for (int i = 0;i < len;i++){
            UserMsg usermsg = usermsgs.get(i);
            try {
                rank = String.valueOf(i+1)+"/"+len;
                userMsgDao.upUBank(usermsg.getUid(),rank);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }
    /**
     * 重置密码
     */
    public Boolean rePwd(String userName){
         String rePwd = createRandomPwd();
         Integer result = userDao.updatePwd(userName,rePwd);
         /*
            发送邮件
          */
         if(result == 1){
             return true;
         }else {
             return false;
         }
    }

    /**
     * 修改用户权限
     * @param userName
     * @param Grade
     * @return
     */
    public Boolean alterUGrade(String userName,String Grade){
        Integer result = userDao.updateGrade(userName,Grade);
        if(result == 1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 找回密码
     * @param userName
     * @param email
     * @return
     */
    public Boolean findPwd(String userName,String email){
        Object user = userDao.getUserByUsername(userName);
        if(user instanceof User){
           Object userMsg = userMsgDao.findUserMsgByUid(((User) user).getUid());
           if(userMsg instanceof UserMsg){
               if(email.equals(((UserMsg) userMsg).getUmail())){
                    return rePwd(userName);
               }else {
                   throw new RuntimeException("邮箱错误！");
               }
           }else {
               throw new RuntimeException("该用户未完善信息！");
           }
        }else {
            throw new RuntimeException("该用户不存在！");
        }
    }

    /**
     * 生成随机6位密码
     * @return
     */
    private String createRandomPwd(){
        int len = randomPwdPool.length();
        Random random = new Random();
        StringBuilder rePwd = new StringBuilder();
        for(int i = 0;i < 6;i++){
            rePwd = rePwd.append(String.valueOf(randomPwdPool.charAt(random.nextInt(len))));
        }
        System.out.println(rePwd);
        return md5Encrypt.toEncryptString(rePwd.toString());
    }

    /**
     * 是否登录
     * @return
     */
    public Boolean isLogin(){
        User user = shareLogin.getUser();
        if(user != null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 验证密码
     * @return
     */
    public Boolean checkPwd(String pwd){
        Long uid = shareLogin.getUser().getUid();
        User user = userDao.findOne(uid);
        pwd = md5Encrypt.toEncryptString(pwd);
        if(user instanceof User){
            if(user.getPassword().equals(pwd)){
                return true;
            }else {
                return false;
            }
        }else {
            throw new RuntimeException("内部错误,请稍后再试!");
        }
    }

    /**
     * 获取首页登录后展示信息
     * @return
     */
    public Map getLoginUMsg(){
        User user = shareLogin.getUser();
        Map<String,String> msg = new HashMap<>();
        msg.put("hi",user.getHeadimg());
        UserMsg userMsg = userMsgDao.findOne(user.getUid());
        if(userMsg == null){
            msg.put("um","F");
        }else {
            msg.put("um",userMsg.getUname());
        }
        return msg;
    }

    /**
     * 退出登录
     */
    public void loginOut(){
        shareLogin.loginOut();
    }

    /**
     * 获取用户信息
     * @return
     */
    public BaseRtM getMyMsg(){
        BaseRtM baseRtM = new BaseRtM();
        try {
            User user = shareLogin.getUser();
            UserMsg userMsg = userMsgDao.getOne(user.getUid());
            Map<String,Object> msg = new HashMap<>();
            switch (user.getGrade()){
                case "s":{
                    user.setGrade("超级管理员");
                    break;
                }
                case "a":{
                    user.setGrade("超级管理员");
                    break;
                }
                case "b":{
                    user.setGrade("管理员");
                    break;
                }
                case "c":{
                    user.setGrade("管理员");
                    break;
                }
                default:{
                    user.setGrade("学员");
                }
            }
            msg.put("user",user);
            msg.put("userMsg",userMsg);
            baseRtM.setRtMCode("T");
            baseRtM.setRtMData(msg);
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误!");
        }finally {
            return baseRtM;
        }
    }
}
