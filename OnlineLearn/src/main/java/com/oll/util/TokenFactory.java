package com.oll.util;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by NewDarker on 2018/1/10.
 */
@Service
public class TokenFactory {
    public String createToken(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
