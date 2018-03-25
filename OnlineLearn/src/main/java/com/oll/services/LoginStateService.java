package com.oll.services;

import com.oll.dao.UserDao;
import com.oll.model.User;
import com.oll.util.BaseRtM;
import com.oll.util.MD5Encrypt;
import com.oll.cache.ShareLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by NewDarker on 2018/1/11.
 */
@Service
public class LoginStateService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ShareLogin shareLogin;
    /**
     * 检查用户合法性
     * @param userName
     * @param passward
     */
    public BaseRtM userLogin(String userName, String passward){
        User user = userDao.getUserByUsername();
        BaseRtM baseRtM = new BaseRtM();
        if(user != null){
            if(user.getPassword().equals(MD5Encrypt.toEncryptString(passward))) {
                //删除密码
                user.setPassword("");
                String token = shareLogin.login(user);
                //判断是否第一次登录
                if(null == user.getRealname() || user.getRealname().equals("")){
                    baseRtM.setRtMCode(0);
                }else {
                    baseRtM.setRtMCode(1);
                }
                baseRtM.setRtMContext(token);
                baseRtM.setRtMData(user);
            }else{
                //密码错误
                baseRtM.setRtMCode(2);
                baseRtM.setRtMContext("密码错误");
            }
        }else{
            //用户名不存在
            baseRtM.setRtMCode(3);
            baseRtM.setRtMContext("用户名不存在");
        }
        return baseRtM;
    }


}
