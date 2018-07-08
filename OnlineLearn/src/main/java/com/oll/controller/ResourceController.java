package com.oll.controller;

import com.oll.services.ResourceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
     * @param content
     * @return
     */
    @RequestMapping(value = "/addTp")
    public Object addTestPage(@RequestParam String title,@RequestParam String descibe,@RequestParam String content){
       Date passDate = new Date();
        return resourceService.addTestPaper(title,descibe,passDate,content);
    }

//    /**
//     * 发布新闻
//     * @param title
//     * @param url
//     * @return
//     */
//    @RequestMapping(value = "/addNews",method = RequestMethod.POST)
//    public Object addNews(@RequestParam String title,@RequestParam String url){
//        return resourceService.addNews(title,url);
//    }
//
//    /**
//     * 发布公告
//     * @param title
//     * @param content
//     * @return
//     */
//    @RequestMapping(value = "/addNotice",method = RequestMethod.POST)
//    public Object addNotice(@RequestParam String title,@RequestParam String content){
//        return resourceService.addNotice(title, content);
//    }

    /**
     * 获取课程信息
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getVMsg")
    public Object getVideoMsg(@RequestParam Long cid){
        return resourceService.getVideoMsg(cid);
    }

    /**
     * 获取所有分类
     * @return
     */
    @RequestMapping(value = "/getAM",method = RequestMethod.POST)
    public Object getAllModule(){
        return resourceService.getModule();
    }
}
