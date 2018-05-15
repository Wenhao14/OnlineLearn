package com.oll.controller;

import com.oll.services.IndexPageService;
import com.oll.util.BaseRtM;
import com.oll.util.YzmUtil;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping(value = "/yzmCheck")
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
    @RequestMapping(value = "/getNews")
    public Object getNewsByPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return indexPageService.getNewsByPage(pageNum,pageSize);
    }

    /**
     * 按页读取公告
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getNotices")
    public Object getNoticesByPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return indexPageService.getNoticeByPage(pageNum, pageSize);
    }
}
