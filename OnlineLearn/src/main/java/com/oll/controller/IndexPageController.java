package com.oll.controller;

import com.oll.services.IndexPageService;
import com.oll.util.BaseRtM;
import com.oll.util.YzmUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by NewDarker on 2018/3/25.
 */
@RestController
@RequestMapping(value = "/index/api")
public class IndexPageController {
    @Resource
    private YzmUtil yzmUtil;
    @Resource
    private IndexPageService indexPageService;

    /**
     * 验证验证码
     * @param yzm
     * @return
     */
    @RequestMapping(value = "/yzmCheck",method = RequestMethod.POST)
    public Object yzmCheck(@RequestParam String yzm){
        BaseRtM baseRtM = new BaseRtM();
        try {
            if(yzmUtil.checkYzm(yzm)){
                baseRtM.setRtMCode("T");
                baseRtM.setRtMsg("验证通过");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("验证码错误!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg(e.getMessage());
        }finally {
            return baseRtM;
        }
    }

    /**
     * 生成验证码
     * @return
     */
    @RequestMapping(value = "/getYzm")
    public Object getYzm(){
        try {
            yzmUtil.getRandcode();
        } catch (IOException e) {
            return "F";
        }
        return null;
    }

    /**
     * 按页读取新闻
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getNews",method = RequestMethod.POST)
    public Object getNewsByPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return indexPageService.getNewsByPage(pageNum,pageSize);
    }

    /**
     * 按页读取公告
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getNotices",method = RequestMethod.POST)
    public Object getNoticesByPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return indexPageService.getNoticeByPage(pageNum, pageSize);
    }

    /**
     * 获取热门课程
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getHotC",method = RequestMethod.POST)
    public Object getHotCourse(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return indexPageService.getHotCourse(pageNum, pageSize);
    }

    /**
     * 获取推荐课程
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getPushC",method = RequestMethod.POST)
    public Object getPushCourse(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return indexPageService.getPushCourse(pageNum, pageSize);
    }

    /**
     * 获取所有课程
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getAC",method = RequestMethod.POST)
    public Object getAllCourse(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return indexPageService.getAllCourse(pageNum, pageSize);
    }

    /**
     * 根据分类读取课程
     * @param mid
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getCByMid")
    public Object getCourseByMid(@RequestParam Long mid,@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return indexPageService.getCourseByModule(mid, pageNum, pageSize);
    }

    /**
     * 根据关键字读取课程
     * @param key
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getCByKey")
    public Object getCourseByKey(@RequestParam String key,@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return indexPageService.getCourseByKey(key, pageNum, pageSize);
    }
}
