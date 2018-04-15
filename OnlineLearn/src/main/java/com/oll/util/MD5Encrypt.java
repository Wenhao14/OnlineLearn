package com.oll.util;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * Created by NewDarker on 2017/11/15.
 * 密码加密
 */
@Service
public class MD5Encrypt {
    public String toEncryptString(String psw){
        String encryptPsw;
        try {
            encryptPsw = new BASE64Encoder().encode(MessageDigest.getInstance("MD5").digest(psw.getBytes("utf-8")));
            return encryptPsw.substring(1,7) + encryptPsw.substring(13,19);
        } catch (Exception e) {
            throw new RuntimeException("密码加密失败");
        }
    }
}
