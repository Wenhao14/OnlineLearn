package com.oll.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oll.cache.ShareLogin;
import com.oll.dao.SelcourseDao;
import com.oll.dao.UserDao;
import com.oll.dao.UserMsgDao;
import com.oll.model.Selcourse;
import com.oll.model.User;
import com.oll.model.UserMsg;
import com.oll.util.BaseRtM;
import com.oll.util.CommonUtil;
import com.oll.util.DBUtil.SqlVehicel;
import com.oll.util.MD5Encrypt;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by NewDarker on 2018/1/3.
 * �û���������
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
    @Resource
    private SqlVehicel sqlVehicel;
    @Resource
    private CommonUtil commonUtil;
    @Resource
    private SelcourseDao selcourseDao;
    private static final String randomPwdPool = "VWX03abcdefg47M89hmOPQR12STUnoABCqrDEFps56tuzGHIJwKLijklNYvsyZ";
    /**
     * �û���¼
     */
    public Boolean userLogin(String userName,String passWord){
        User user = userDao.getUserByUsername(userName);
        if(user instanceof User){
            if(user.getPassword().equals(md5Encrypt.toEncryptString(passWord))){
                if("N".equals(user.getHeadimg())){
                    user.setHeadimg("/img/undefultUI.jpg");
                }
                shareLogin.setSession(user);
                return true;
            }else{
                throw new RuntimeException("�������");
            }
        }else{
            throw new RuntimeException("�û���������");
        }
    }

    /**
     * �����û�ע��
     * @param json
     */
    public void patchAddUser(String json){
        JSONArray userList =  JSON.parseArray(json);
        int len = userList.size();
        String username;
        JSONObject jsonObject;
        List<User> users = new ArrayList<>(len);
        for(int i = 0;i < len;i++){
            User user = new User();
            jsonObject = (JSONObject) userList.get(i);
            username = jsonObject.getString("�û���");
            user.setUsername(username);
            user.setPassword(md5Encrypt.toEncryptString(username));
            user.setGrade("c");
            user.setIsdel("0");
            user.setHeadimg("N");
            user.setIsPerMsg("0");
            users.add(user);
        }
        userDao.save(users);
    }
    /**
     * ���������û�
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
        user.setHeadimg("N");
        user.setIsPerMsg("0");
        try {
            Object result = userDao.save(user);
            if(result instanceof User){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException("���û��Ѵ���!");
        }
    }
    /**
     * ����/�����û���Ϣ
     * @return
     */
    @Transactional
    public Boolean perfectUMsg(String realname,String email,String phone,String department ){
        UserMsg userMsg = new UserMsg();
        User user = shareLogin.getUser();
        userMsg.setUid(user.getUid());
        userMsg.setUname(realname);
        userMsg.setUmail(email);
        userMsg.setUphone(phone);
        userMsg.setUsection(department);
        userMsg.setUgoal(new Long(0));
        Object result = userMsgDao.save(userMsg);
        if(result instanceof UserMsg){
            if(userDao.alterIsPerMsg(user.getUid()) == 1){
                user.setIsPerMsg("1");
                shareLogin.alterRedisUMsg(user);
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * ����ͷ��
     * @param imgUrl
     * @return
     */
    public Boolean updataUserHeadImg(Long uid,String imgUrl){
        if(uid != null){
            Integer result = userDao.updateHeadImg(uid,imgUrl);
            if(result == 1){
                return true;
            }else {
                return false;
            }
        }else {
            throw new RuntimeException("�ڲ�����,��¼����!");
        }
    }
    /**
     * ������Ϣ
     */
    public BaseRtM updataUMsg(String msg,String type){
        Integer result = 0;
        BaseRtM baseRtM = new BaseRtM();
        try {
            Long uid = shareLogin.getUser().getUid();
            if(uid != null){
                if(type.equals("m")){
                    result = userMsgDao.updateUMail(uid,msg);
                }else if(type.equals("p")){
                    result = userMsgDao.updateUPhone(uid,msg);
                }else {
                    baseRtM.setRtMCode("F");
                    baseRtM.setRtMsg("�����쳣!");
                }
                if(result == 1){
                    baseRtM.setRtMCode("T");
                    baseRtM.setRtMsg("�޸ĳɹ�!");
                }else {
                    baseRtM.setRtMCode("F");
                    baseRtM.setRtMsg("�޸�ʧ��!");
                }
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("��¼��ʱ�����ڲ�����!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("�ڲ�����!");
        }finally {
            return baseRtM;
        }
    }
    /**
     * �޸�����
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
           throw new RuntimeException("�˻�״̬�쳣!");
        }
    }
    /**
     * �����û�����
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void userRank(){
        System.out.println("��������");
        List<UserMsg> usermsgs = userMsgDao.findAllUserMsg();
        int len = usermsgs.size();
        String rank;
        for (int i = 0;i < len;i++){
            UserMsg usermsg = usermsgs.get(i);
            rank = String.valueOf(i+1)+"/"+len;
            usermsg.setUrank(rank);
        }
        userMsgDao.save(usermsgs);
    }
    /**
     * ��������
     */
    public Boolean rePwd(String userName){
         String rePwd = createRandomPwd();
         Integer result = userDao.updatePwd(userName,rePwd);
         /*
            �����ʼ�
          */
         if(result == 1){
             return true;
         }else {
             return false;
         }
    }

    /**
     * �޸��û�Ȩ��
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
     * �һ�����
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
                   throw new RuntimeException("�������");
               }
           }else {
               throw new RuntimeException("���û�δ������Ϣ��");
           }
        }else {
            throw new RuntimeException("���û������ڣ�");
        }
    }

    /**
     * �������6λ����
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
     * �Ƿ��¼
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
     * ��֤����
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
            throw new RuntimeException("�ڲ�����,���Ժ�����!");
        }
    }

    /**
     * ��ȡ��ҳ��¼��չʾ�û���Ϣ
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
     * �˳���¼
     */
    public void loginOut(){
        shareLogin.loginOut();
    }

    /**
     * ��ȡ�û���Ϣ
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
                    user.setGrade("��������Ա");
                    break;
                }
                case "a":{
                    user.setGrade("��������Ա");
                    break;
                }
                case "b":{
                    user.setGrade("����Ա");
                    break;
                }
                case "c":{
                    user.setGrade("ѧԱ");
                    break;
                }
                default:{
                    user.setGrade("ѧԱ");
                }
            }
            msg.put("user",user);
            msg.put("userMsg",userMsg);
            baseRtM.setRtMCode("T");
            baseRtM.setRtMData(msg);
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("�ڲ�����!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * ����ͷ��ͼƬ
      * @param imgStr
     * @return
     */
    public BaseRtM GenerateImage(String imgStr) {   //���ֽ������ַ�������Base64���벢����ͼƬ
        BaseRtM baseRtM = new BaseRtM();
        if (imgStr == null){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("ͷ��ͼƬΪ��!");
            return baseRtM;
        }
        try {
            User user = shareLogin.getUser();
            user.setHeadimg(imgStr);
            if(shareLogin.alterRedisUMsg(user)){
                Boolean result = updataUserHeadImg(user.getUid(),imgStr);
                if(result){
                    baseRtM.setRtMCode("T");
                    baseRtM.setRtMsg("����ͷ��ɹ�!");
                }else {
                    baseRtM.setRtMCode("F");
                    baseRtM.setRtMsg("�޸�ͷ��ʧ��!");
                }
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("�޸�ͷ��ʧ��!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("�ڲ�����!");
        }finally {
            return baseRtM;
        }
    }
    /**
     * ��ȡ�ҵĿγ�
     * @param pageNum
     * @param pageSize
     * @return
     */
    public BaseRtM getSelCourse(Integer pageNum,Integer pageSize){
        BaseRtM baseRtM = new BaseRtM();
        try {
            String sql = "SELECT c.cId,c.cName,c.cKeySpeaker,c.cImg,sc.studyplan FROM course c,selcourse sc WHERE c.cid  = sc.cid AND sc.uid = ? AND c.cIsDel = 0 AND sc.studyplan != 1 ORDER BY sc.scid DESC LIMIT " + pageNum*pageSize+","+pageSize;
            Long uid = shareLogin.getUser().getUid();
            String[] param = {Long.toString(uid)};
            List result = sqlVehicel.SqlSelect(sql,param);
            baseRtM.setRtMCode("T");
            baseRtM.setRtMData(result);
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("�ڲ�����!");
        }finally {
            return baseRtM;
        }
    }
    /**
     * ��ȡ����ɿγ�
     * @param pageNum
     * @param pageSize
     * @return
     */
    public BaseRtM getEndCourse(Integer pageNum,Integer pageSize){
        BaseRtM baseRtM = new BaseRtM();
        try {
            String sql = "SELECT c.cId,c.cName,c.cKeySpeaker,c.cImg FROM course c,selcourse sc WHERE c.cid  = sc.cid AND sc.uid = ? AND c.cIsDel = 0 AND sc.studyplan = 1 ORDER BY sc.scid DESC LIMIT " + pageNum*pageSize+","+pageSize;
            Long uid = shareLogin.getUser().getUid();
            String[] param = {Long.toString(uid)};
            List result = sqlVehicel.SqlSelect(sql,param);
            baseRtM.setRtMCode("T");
            baseRtM.setRtMData(result);
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("�ڲ�����!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * �����û�����
     * @param goal
     */
    public void addUGoal(Long goal){
        try {
            Long uid = shareLogin.getUser().getUid();
            userMsgDao.alterUGoal(uid,goal);
        }catch (Exception e){
            return;
        }
    }
    /**
     * ���ѡ��
     */
    public BaseRtM addCouser(Long cid){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Long uid = shareLogin.getUser().getUid();
            Selcourse selcourse = new Selcourse();
            selcourse.setChnum("1");
            selcourse.setUid(uid);
            selcourse.setCid(cid);
            selcourse.setStudyhour("0");
            selcourse.setStudyplan("0");
            selcourse.setLatestime(commonUtil.formatDateTime(new Date()));
            selcourseDao.save(selcourse);
            baseRtM.setRtMCode("T");
            baseRtM.setRtMsg("��ӳɹ�����ʼѧϰ��!");
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("���...���ʧ��!");
        }finally {
            return baseRtM;
        }
    }
}
