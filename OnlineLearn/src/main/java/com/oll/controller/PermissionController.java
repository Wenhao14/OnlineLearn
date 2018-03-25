package com.oll.controller;

import com.oll.services.LoginStateService;
import com.oll.util.BaseRtM;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by NewDarker on 2018/3/25.
 * 平台操作权限控制
 */
@RestController
@RequestMapping(value = "/permission/api")
public class PermissionController {

    @Resource
    private LoginStateService loginStateService;

    @RequestMapping("/login")
    public Object userLogin(@RequestParam String userName, @RequestParam String passWord){
        BaseRtM baseRtM = loginStateService.userLogin(userName,passWord);
        return baseRtM;
    }
}
