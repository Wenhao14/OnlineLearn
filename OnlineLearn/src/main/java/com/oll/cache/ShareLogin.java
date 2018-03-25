package com.oll.cache;

import com.oll.model.User;
import com.oll.util.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by NewDarker on 2018/1/10.
 */
@Service
public class ShareLogin {
     @Autowired
     private RedisTemplate redisTemplate;
     @Autowired
     private StringRedisTemplate stringRedisTemplate;
     @Autowired
     private TokenFactory tokenFactory;

     /**
      * 记录登录状态
      * @param user
      * @return
      */
     public String login(User user){
          boolean exists = stringRedisTemplate.hasKey(user.getUsername());
          if(exists){
               forceLoginOut(user.getUsername(),stringRedisTemplate.opsForValue().get(user.getUsername()));
          }
          String token = tokenFactory.createToken();
          ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
          valueOperations.set(token,user, AppConfig.LOGIN_OUTTIME, TimeUnit.MINUTES);
          stringRedisTemplate.opsForValue().set(user.getUsername(),token,AppConfig.LOGIN_OUTTIME,TimeUnit.MINUTES);
          return token;
     }

     /**
      * 刷新登录状态
      * @param token
      * @return
      */
     public User loginKeep(String token){
         boolean exists = redisTemplate.hasKey(token);
         if(!exists){//用户登录过期或token伪造
             return null;
         }
         redisTemplate.expire(token,AppConfig.LOGIN_OUTTIME,TimeUnit.MINUTES);
         User user = (User) redisTemplate.opsForValue().get(token);
         stringRedisTemplate.expire(user.getUsername(),AppConfig.LOGIN_OUTTIME,TimeUnit.MINUTES);
         return user;
     }

     /**
      * 退出
      * @param token
      */
     public void loginOut(String token){
          User user = (User) redisTemplate.opsForValue().get(token);
          stringRedisTemplate.delete(user.getUsername());
          redisTemplate.delete(token);
     }

     /**
      * 强制退出
      * @param token
      * @param userName
      */
     public void forceLoginOut(String token,String userName){
          stringRedisTemplate.delete(userName);
          redisTemplate.delete(token);
     }
}
