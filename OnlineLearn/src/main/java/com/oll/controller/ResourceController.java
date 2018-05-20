package com.oll.controller;

import com.oll.services.ResourceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by NewDarker on 2018/5/13.
 */
@RestController
@RequestMapping("/resource/api")
public class ResourceController {
    @Resource
    private ResourceService resourceService;

    /**
     * 发布试题
     * @param title
     * @param descibe
     * @param passDate
     * @param content
     * @return
     */
    @RequestMapping(value = "/addTp")
    public Object addTestPage(@RequestParam String title,@RequestParam String descibe,@RequestParam Date passDate,@RequestParam String content){
       return resourceService.addTestPaper(title,descibe,passDate,content);
    }

    /**
     * 发布新闻
     * @param title
     * @param url
     * @return
     */
    @RequestMapping(value = "/addNews")
    public Object addNews(@RequestParam String title,@RequestParam String url){
        return resourceService.addNews(title,url);
    }

    /**
     * 发布公告
     * @param title
     * @param content
     * @return
     */
    @RequestMapping(value = "/addNotice")
    public Object addNotice(@RequestParam String title,@RequestParam String content){
        return resourceService.addNotice(title, content);
    }

}
