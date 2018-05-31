package com.oll.controller;

import com.oll.services.ManageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by NewDarker on 2018/5/24.
 */
@RestController
@RequestMapping("/manage/api")
public class ManageController {
    @Resource
    private ManageService manageService;

    /**
     * 用户删除处理
     * @param type
     * @param uName
     * @return
     */
    @RequestMapping(value = "/uDeal",method = RequestMethod.POST)
    public Object userDeal(@RequestParam String type,@RequestParam String uName){
        return manageService.userDeal(type,uName);
    }

    /**
     * 获取已删除用户
     * @param pNum
     * @param pSize
     * @return
     */
    @RequestMapping(value = "/getADU",method = RequestMethod.POST)
    public Object getEnDelUser(@RequestParam Integer pNum,@RequestParam Integer pSize){
        return manageService.getLogicDelU(pNum, pSize);
    }

    /**
     * 用户排名
     * @param type
     * @param pNum
     * @param pSize
     * @return
     */
    @RequestMapping(value = "/uRank",method = RequestMethod.POST)
    public Object userRank(@RequestParam String type,@RequestParam Integer pNum,@RequestParam Integer pSize){
        return manageService.userRank(type, pNum, pSize);
    }

    /**
     * 发布新闻公告
     * @param type
     * @param title
     * @param content
     * @return
     */
    @RequestMapping(value = "/addNN",method = RequestMethod.POST)
    public Object addNN(@RequestParam String type,@RequestParam String title,@RequestParam String content){
        return manageService.addNN(type, title, content);
    }
}
