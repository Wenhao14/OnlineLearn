package com.oll.controller;

import com.oll.services.ResourceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by NewDarker on 2018/3/25.
 */
@RestController
@RequestMapping(value = "/tp/api")
public class TestPaperController {
    @Resource
    private ResourceService resourceService;
    /**
     * 获取未完成试题
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getUnTps")
    public Object getUnTps(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        return resourceService.getUnTps(pageNum, pageSize);
    }

    /**
     * 获取试卷
     * @param tpId
     * @return
     */
    @RequestMapping(value = "/getTpCont")
    public Object getTpCont(@RequestParam Long tpId){
        return resourceService.getTpContent(tpId);
    }
    /**
     * 获取已完成试题
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getEnTps")
    public Object getEnTps(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return resourceService.getEnTps(pageNum, pageSize);
    }

    /**
     * 添加答题结果
     * @param tpId
     * @param score
     * @return
     */
    @RequestMapping(value = "/addAnswer")
    public Object addAnswer(@RequestParam Long tpId,@RequestParam String score){
        return resourceService.addAnswer(tpId,score);
    }
}
