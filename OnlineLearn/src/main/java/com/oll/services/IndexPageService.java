package com.oll.services;

import com.oll.dao.NewsDao;
import com.oll.dao.NoticeDao;
import com.oll.model.News;
import com.oll.model.Notice;
import com.oll.util.BaseRtM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by NewDarker on 2018/5/11.
 * 主页服务
 */
@Service
public class IndexPageService {
    @Resource
    private NewsDao newsDao;
    @Resource
    private NoticeDao noticeDao;
    /**
     * 按页读取新闻
     * @param pageNum
     * @return
     */
    public BaseRtM getNewsByPage(Integer pageNum,Integer pageSize){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Sort sort = new Sort(Sort.Direction.DESC,"nupdate");
            Pageable pageable = new PageRequest(pageNum,pageSize,sort);
            Page<News> news = newsDao.findAll(pageable);
            baseRtM.setRtMData(news.getContent());
            baseRtM.setRtMsg("成功");
            baseRtM.setRtMCode("T");
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误！");
        }finally {
            return baseRtM;
        }
    }

    /**
     * 按页读取公告
     * @param pageNum
     * @return
     */
    public BaseRtM getNoticeByPage(Integer pageNum,Integer pageSize){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Sort sort = new Sort(Sort.Direction.DESC,"ntupdate");
            Pageable pageable = new PageRequest(pageNum,pageSize,sort);
            Page<Notice> notices = noticeDao.findAll(pageable);
            baseRtM.setRtMData(notices.getContent());
            baseRtM.setRtMsg("成功");
            baseRtM.setRtMCode("T");
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误！");
        }finally {
            return baseRtM;
        }
    }
}
