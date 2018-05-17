package com.oll.controller;

import com.oll.services.UserService;
import com.oll.util.BaseRtM;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    @RequestMapping(value = "/userLogin")
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
    @RequestMapping("/batchAdd")
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
    @RequestMapping("/singleAdd")
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
    @RequestMapping("/rePwd")
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
    @RequestMapping(value = "/alterPwd")
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
    @RequestMapping(value = "/alterGrade")
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
    @RequestMapping(value = "/checkPwd")
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
    @RequestMapping(value = "/alterUMsg")
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
    @RequestMapping(value = "/addUMsg")
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
    @RequestMapping(value = "/isLogin")
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
    @RequestMapping(value = "/findPwd")
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
    @RequestMapping("/loginOut")
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
    @RequestMapping(value = "/getMyMsg")
    public Object getMyMsg(){
       return userService.getMyMsg();
    }

    /**
     * 上传头像
     * @param hImg
     * @return
     */
    @RequestMapping(value = "/upHeadImg")
    public Object alterHeadImg(@RequestParam MultipartFile hImg){
        if(!hImg.isEmpty()){
            System.out.println("ok");
        }else {
            System.out.println("no");
        }
        return null;
    }
}
