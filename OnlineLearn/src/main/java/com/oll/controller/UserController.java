package com.oll.controller;

import com.oll.services.UserService;
import com.oll.util.BaseRtM;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户操作api
 */
@RestController
@RequestMapping(value = "/user/api")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    public Object userLogin(@RequestParam String userName,@RequestParam String passWord){
        BaseRtM baseRtM = new BaseRtM();
        try {
          Boolean result = userService.userLogin(userName,passWord);
          if(result == true){
              baseRtM.setRtMCode("T");
              baseRtM.setRtMsg("登录成功");
          }else {
              baseRtM.setRtMCode("F");
              baseRtM.setRtMsg("用户名或密码不正确");
          }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg(e.getMessage());
        }finally {
            return baseRtM;
        }
    }


    /*
       批量注册用户
     */
    @RequestMapping("/batchAdd")
    public Object batchAddUser(@RequestParam String usersJson){
       BaseRtM baseRtM =  userService.addUser(usersJson);
       return baseRtM;
    }
    /*
       单个注册用户
     */
    @RequestMapping("/singleAdd")
    public Object singleAddUser(@RequestParam String userName){

        return null;
    }
    /*
       设置管理员
     */
    public Object setAdminUser(@RequestParam String userName,@RequestParam String grade){

        return null;
    }
    /**
     * 验证密码
     */
    @RequestMapping(value = "test")
    public Object checkPassWord(){
        return "ok";
    }

    /**
     * 修改信息
     */
    public Object alterUserMsg(@RequestParam String msg,@RequestParam String msgName){
        return null;
    }
    /**
     * 完善信息
     */
    public Object addUserMsg(@RequestParam String jsonMsg){
        return null;
    }
    /**
     * 逻辑删除用户
     */
    public Object logicDel(@RequestParam String userName){
        return null;
    }
    /**
     * 恢复删除
     */
    public Object renewDel(@RequestParam String userName){
        return null;
    }
    /**
     * 物理删除用户
     */
    public Object realDel(){
       return null;
    }
    /**
     * 批量获取用户信息
     */
    public Object batchGetMsg(@RequestParam String pageSize){
        return null;
    }
    /**
     * 单个获取用户信息
     *
     * */
    public Object singleGetMsg(@RequestParam String userName){
        return null;
    }
    /**
     * 找回密码
     */
    public Object findPassWord(@RequestParam String userName){
        return null;
    }
}
