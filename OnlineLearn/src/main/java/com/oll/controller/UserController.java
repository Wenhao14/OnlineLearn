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
 * 用户操作api
 */
@RestController
@RequestMapping(value = "/user/api")
public class UserController {
    @Resource
    private UserService userService;
    /**
     * 用户登录
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
              baseRtM.setRtMsg("登录成功！");
          }else {
              baseRtM.setRtMCode("F");
              baseRtM.setRtMsg("用户名或密码不正确！");
          }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg(e.getMessage());
        }finally {
            return baseRtM;
        }
    }

    /**
     * 批量用户注册
     * @param usersJson
     * @return
     */
    @RequestMapping(value = "/batchAdd",method = RequestMethod.POST)
    public Object batchAddUser(@RequestParam String usersJson){
        BaseRtM baseRtM = new BaseRtM();
        try{
            userService.patchAddUser(usersJson);
            baseRtM.setRtMCode("T");
            baseRtM.setRtMsg("批量添加成功!");
        }catch (Exception ex){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("批量添加失败!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * 单个用户注册
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
                baseRtM.setRtMsg("注册成功!");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("注册失败!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg(e.getMessage());
        }finally {
            return baseRtM;
        }
    }

    /**
     * 重置密码
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
                 baseRtM.setRtMsg("密码重置成功!");
             }else {
                 baseRtM.setRtMCode("F");
                 baseRtM.setRtMsg("密码重置失败!");
             }
        }catch (Exception e){
             baseRtM.setRtMCode("F");
             baseRtM.setRtMsg("内部错误，请稍后再试!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * 修改密码
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
               baseRtM.setRtMsg("修改成功!");
           }else {
               baseRtM.setRtMCode("F");
               baseRtM.setRtMsg("修改失败!");
           }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg(e.getMessage());
        }finally {
            return baseRtM;
        }

    }
    /**
     * 修改权限
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
                baseRtM.setRtMsg("设置成功!");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("设置失败!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误，请稍后再试!");

        }finally {
            return baseRtM;
        }
    }

    /**
     * 验证密码
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
                baseRtM.setRtMsg("原密码错误!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误，请稍后再试!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * 修改信息
     * @param msg
     * @param type
     * @return
     */
    @RequestMapping(value = "/alterUMsg",method = RequestMethod.POST)
    public Object alterUserMsg(@RequestParam String msg,@RequestParam String type){
        return userService.updataUMsg(msg,type);
    }

    /**
     * 完善信息
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
                baseRtM.setRtMsg("个人信息已完善!");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("个人信息完善失败!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误，请稍后再试!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * 是否已登录
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
     * 找回密码
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
                baseRtM.setRtMsg("密码找回成功，请在邮箱查收!");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("密码找回失败!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg(e.getMessage());
        }finally {
            return baseRtM;
        }
    }

    /**
     * 退出登录
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
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/getMyMsg",method = RequestMethod.POST)
    public Object getMyMsg(){
       return userService.getMyMsg();
    }

    /**
     * 上传头像
     * @param hImg
     * @return
     */
    @RequestMapping(value = "/upHeadImg",method = RequestMethod.POST)
    public Object alterHeadImg(@RequestParam String hImg){
        return userService.GenerateImage(hImg);
    }

    /**
     * 获取我的课程
     * @param pNum
     * @param pSize
     * @return
     */
    @RequestMapping(value = "/getSelC",method = RequestMethod.POST)
    public Object getSelCourse(@RequestParam Integer pNum,@RequestParam Integer pSize){
        return userService.getSelCourse(pNum, pSize);
    }

    /**
     * 获取我已结束课程
     * @param pNum
     * @param pSize
     * @return
     */
    @RequestMapping(value = "/getEndC",method = RequestMethod.POST)
    public Object getEndCourse(@RequestParam Integer pNum,@RequestParam Integer pSize){
        return userService.getEndCourse(pNum, pSize);
    }

    /**
     *添加选课
     * @param cid
     * @return
     */
    @RequestMapping(value = "/addC",method = RequestMethod.POST)
    public Object addCourse(@RequestParam Long cid){
        return userService.addCouser(cid);
    }
    /**
     * 更新学习进度
     */
    @RequestMapping(value = "/upSl",method = RequestMethod.POST)
    public Object upSelCouser(@RequestParam Long scid,@RequestParam String num,@RequestParam String sh,@RequestParam String sp){
        return null;
    }

    /**
     * 更新积分
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
