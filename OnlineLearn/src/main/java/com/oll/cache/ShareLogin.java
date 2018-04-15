package com.oll.cache;

import com.oll.model.User;
import com.oll.util.TokenFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * Created by NewDarker on 2018/1/10.
 */
@Service
public class ShareLogin {
     @Resource
     private RedisTemplate redisTemplate ;
     @Resource
     private TokenFactory tokenFactory;
     @Value("${app.config.sessionOutTime}")
     private Long sessionOutTime;
     @Resource
     private HttpServletRequest request;
     @Resource
     private HttpServletResponse response;
     /**
      * 设置session
      * @param user
      * @return
      */
     public String setSession(User user){
          Long uId = user.getUid();
          String token = (String) redisTemplate.opsForValue().get(uId);
          if(token != null){
               /*
                   强制退出之前的登录
                */
               redisTemplate.delete(uId);
               redisTemplate.delete(token);
          }
          token = tokenFactory.createToken();
          redisTemplate.opsForValue().set(uId,token,sessionOutTime,TimeUnit.MINUTES);
          redisTemplate.opsForValue().set(token,user,sessionOutTime,TimeUnit.MINUTES);
         /**
          * 存入session信息
          */
         setTokenToSession(token);
         /**
          * 将token写入cooking
          */
          Cookie cookie = new Cookie("token",token);
          cookie.setMaxAge(24*60*60*1000);
          cookie.setPath("/*");
          response.addCookie(cookie);
          return token;
     }
     /**
      * 刷新登录状态
      * @return
      */
     public void flushState(){
          String token = getTokenBySession();
          redisTemplate.expire(token,sessionOutTime,TimeUnit.MINUTES);
          User user = (User) redisTemplate.opsForValue().get(token);
          redisTemplate.expire(user.getUid(),sessionOutTime,TimeUnit.MINUTES);
     }
     /**
     * 获取用户Account
     */
     public User getUser(){
          String token = getTokenBySession();
          if(token == null){
               return null;
          }else {
               User user = (User) redisTemplate.opsForValue().get(token);
               return user;
          }
     }
     /**
      * 退出
      */
     public void loginOut(){
          String token = getTokenBySession();
          User user = (User) redisTemplate.opsForValue().get(token);
          redisTemplate.delete(user.getUid());
          redisTemplate.delete(token);
     }
     public String getTokenBySession(){
          String token = (String) request.getSession().getAttribute("token");
          return token;
     }
     public void setTokenToSession(String token){
          request.getSession().setAttribute("token",token);
     }

}
