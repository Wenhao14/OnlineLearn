package com.oll.controller;

import com.oll.services.UserService;
import com.oll.util.BaseRtM;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * �û�����api
 */
@RestController
@RequestMapping(value = "/user/api")
public class UserController {
    @Resource
    private UserService userService;
    /**
     * �û���¼
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public Object userLogin(@RequestParam String userName,@RequestParam String passWord){
        BaseRtM baseRtM = new BaseRtM();
        try {
          Boolean result = userService.userLogin(userName,passWord);
          if(result == true){
              baseRtM.setRtMCode("T");
              baseRtM.setRtMsg("��¼�ɹ���");
          }else {
              baseRtM.setRtMCode("F");
              baseRtM.setRtMsg("�û��������벻��ȷ��");
          }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg(e.getMessage());
        }finally {
            return baseRtM;
        }
    }

    /**
     * �����û�ע��
     * @param usersJson
     * @return
     */
    @RequestMapping(value = "/batchAdd",method = RequestMethod.POST)
    public Object batchAddUser(@RequestParam String usersJson){
        BaseRtM baseRtM = new BaseRtM();
        try{
            userService.patchAddUser(usersJson);
            baseRtM.setRtMCode("T");
            baseRtM.setRtMsg("������ӳɹ�!");
        }catch (Exception ex){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("�������ʧ��!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * �����û�ע��
     * @param userName
     * @param grade
     * @return
     */
    @RequestMapping(value = "/singleAdd",method = RequestMethod.POST)
    public Object singleAddUser(@RequestParam String userName,@RequestParam String grade){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Boolean result = userService.addUser(userName,grade);
            if(result){
                baseRtM.setRtMCode("T");
                baseRtM.setRtMsg("ע��ɹ�!");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("ע��ʧ��!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg(e.getMessage());
        }finally {
            return baseRtM;
        }
    }

    /**
     * ��������
     * @param userName
     * @return
     */
    @RequestMapping(value = "/rePwd",method = RequestMethod.POST)
    public Object rePwd(@RequestParam String userName){
        BaseRtM baseRtM = new BaseRtM();
        try {
             Boolean result = userService.rePwd(userName);
             if(result){
                 baseRtM.setRtMCode("T");
                 baseRtM.setRtMsg("�������óɹ�!");
             }else {
                 baseRtM.setRtMCode("F");
                 baseRtM.setRtMsg("��������ʧ��!");
             }
        }catch (Exception e){
             baseRtM.setRtMCode("F");
             baseRtM.setRtMsg("�ڲ��������Ժ�����!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * �޸�����
     * @param newPwd
     * @return
     */
    @RequestMapping(value = "/alterPwd",method = RequestMethod.POST)
    public Object alterPwd(@RequestParam String newPwd){
        BaseRtM baseRtM = new BaseRtM();
        try {
           Boolean result = userService.alterPwd(newPwd);
           if(result){
               baseRtM.setRtMCode("T");
               baseRtM.setRtMsg("�޸ĳɹ�!");
           }else {
               baseRtM.setRtMCode("F");
               baseRtM.setRtMsg("�޸�ʧ��!");
           }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg(e.getMessage());
        }finally {
            return baseRtM;
        }

    }
    /**
     * �޸�Ȩ��
     * @param userName
     * @param grade
     * @return
     */
    @RequestMapping(value = "/alterGrade",method = RequestMethod.POST)
    public Object alterGrade(@RequestParam String userName,@RequestParam String grade){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Boolean result = userService.alterUGrade(userName,grade);
            if(result){
                baseRtM.setRtMCode("T");
                baseRtM.setRtMsg("���óɹ�!");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("����ʧ��!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("�ڲ��������Ժ�����!");

        }finally {
            return baseRtM;
        }
    }

    /**
     * ��֤����
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/checkPwd",method = RequestMethod.POST)
    public Object checkPassWord(@RequestParam String pwd){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Boolean result = userService.checkPwd(pwd);
            if(result){
                baseRtM.setRtMCode("T");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("ԭ�������!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("�ڲ��������Ժ�����!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * �޸���Ϣ
     * @param msg
     * @param type
     * @return
     */
    @RequestMapping(value = "/alterUMsg",method = RequestMethod.POST)
    public Object alterUserMsg(@RequestParam String msg,@RequestParam String type){
        return userService.updataUMsg(msg,type);
    }

    /**
     * ������Ϣ
     * @param name
     * @param email
     * @param phone
     * @param dp
     * @return
     */
    @RequestMapping(value = "/addUMsg",method = RequestMethod.POST)
    public Object addUserMsg(@RequestParam String name,@RequestParam String email,@RequestParam String phone,@RequestParam String dp){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Boolean result = userService.perfectUMsg(name,email,phone,dp);
            if(result){
                baseRtM.setRtMCode("T");
                baseRtM.setRtMsg("������Ϣ������!");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("������Ϣ����ʧ��!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("�ڲ��������Ժ�����!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * �Ƿ��ѵ�¼
     * @return
     */
    @RequestMapping(value = "/isLogin",method = RequestMethod.POST)
    public Object isLogin(){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Boolean result = userService.isLogin();
            if(result){
                baseRtM.setRtMCode("T");
                Map msg = userService.getLoginUMsg();
                baseRtM.setRtMData(msg);
            }else {
                baseRtM.setRtMCode("F");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
        }finally {
            return baseRtM;
        }
    }

    /**
     * �һ�����
     * @param userName
     * @param email
     * @return
     */
    @RequestMapping(value = "/findPwd",method = RequestMethod.POST)
    public Object findPwd(@RequestParam String userName,@RequestParam String email){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Boolean result = userService.findPwd(userName,email);
            if(result){
                baseRtM.setRtMCode("T");
                baseRtM.setRtMsg("�����һسɹ��������������!");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("�����һ�ʧ��!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg(e.getMessage());
        }finally {
            return baseRtM;
        }
    }

    /**
     * �˳���¼
     * @return
     */
    @RequestMapping(value = "/loginOut",method = RequestMethod.POST)
    public Object loginOut(){
        BaseRtM baseRtM = new BaseRtM();
        try {
            userService.loginOut();
            baseRtM.setRtMCode("T");
        }catch (Exception e){
            baseRtM.setRtMCode("F");
        }finally {
            return baseRtM;
        }
    }

    /**
     * ��ȡ�û���Ϣ
     * @return
     */
    @RequestMapping(value = "/getMyMsg",method = RequestMethod.POST)
    public Object getMyMsg(){
       return userService.getMyMsg();
    }

    /**
     * �ϴ�ͷ��
     * @param hImg
     * @return
     */
    @RequestMapping(value = "/upHeadImg",method = RequestMethod.POST)
    public Object alterHeadImg(@RequestParam String hImg){
        return userService.GenerateImage(hImg);
    }

    /**
     * ��ȡ�ҵĿγ�
     * @param pNum
     * @param pSize
     * @return
     */
    @RequestMapping(value = "/getSelC",method = RequestMethod.POST)
    public Object getSelCourse(@RequestParam Integer pNum,@RequestParam Integer pSize){
        return userService.getSelCourse(pNum, pSize);
    }

    /**
     * ��ȡ���ѽ����γ�
     * @param pNum
     * @param pSize
     * @return
     */
    @RequestMapping(value = "/getEndC",method = RequestMethod.POST)
    public Object getEndCourse(@RequestParam Integer pNum,@RequestParam Integer pSize){
        return userService.getEndCourse(pNum, pSize);
    }

    /**
     *���ѡ��
     * @param cid
     * @return
     */
    @RequestMapping(value = "/addC",method = RequestMethod.POST)
    public Object addCourse(@RequestParam Long cid){
        return userService.addCouser(cid);
    }
    /**
     * ����ѧϰ����
     */
    @RequestMapping(value = "/upSl",method = RequestMethod.POST)
    public Object upSelCouser(@RequestParam Long scid,@RequestParam String num,@RequestParam String sh,@RequestParam String sp){
        return null;
    }

    /**
     * ���»���
     * @param goal
     * @return
     */
    @RequestMapping(value = "/addGoal",method = RequestMethod.POST)
    public Object addGoal(@RequestParam Long goal){
        userService.addUGoal(goal);
        return null;
    }

    /**
     *
     * @param scId
     * @param vNum
     * @param ct
     * @param jd
     * @return
     */
    @RequestMapping(value = "/upLT",method = RequestMethod.POST)
    public Object upLearnTime(@RequestParam Long scId,@RequestParam String vNum,@RequestParam String ct,@RequestParam String jd){
        return userService.upVLearnTime(scId, vNum, ct, jd);
    }
}
