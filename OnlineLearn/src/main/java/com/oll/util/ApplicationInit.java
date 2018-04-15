package com.oll.util;

import com.oll.cache.ShareLogin;
import com.oll.filter.PermissionFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by NewDarker on 2018/4/15.
 */
@Component
public class ApplicationInit implements CommandLineRunner {
    @Resource
    private ShareLogin shareLogin;
    @Override
    public void run(String... strings) throws Exception {
        PermissionFilter.setShareLogin(shareLogin);
    }
}
