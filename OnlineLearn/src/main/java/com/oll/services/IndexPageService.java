package com.oll.services;

import com.oll.cache.ShareLogin;
import com.oll.dao.NewsDao;
import com.oll.dao.NoticeDao;
import com.oll.model.News;
import com.oll.model.Notice;
import com.oll.model.User;
import com.oll.util.BaseRtM;
import com.oll.util.PatternUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
    @Resource
    private ShareLogin shareLogin;
    @Resource
    private PatternUtil patternUtil;
    /**
     * 按页读取新闻
     * @param pageNum
     * @return
     */
    public BaseRtM getNewsByPage(Integer pageNum){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Sort sort = new Sort(Sort.Direction.DESC,"nupdate");
            Pageable pageable = new PageRequest(pageNum,6,sort);
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
    public BaseRtM getNoticeByPage(Integer pageNum){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Sort sort = new Sort(Sort.Direction.DESC,"ntupdate");
            Pageable pageable = new PageRequest(pageNum,6,sort);
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

    /**
     * 添加新闻
     * @return
     */
    public BaseRtM addNews(String title,String url){
        BaseRtM baseRtM = new BaseRtM();
        try {
            User user = shareLogin.getUser();
            News news;
            if(user != null){
                news = new News();
                news.setNtitle(title);
                news.setNurl(url);
                String upDate = patternUtil.formatDate(new Date());
                news.setNupdate(upDate);
                news.setNupuser(user.getUid());
                baseRtM.setRtMCode("T");
                baseRtM.setRtMsg("添加成功!");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("当前操作不合法!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * 添加公告
     * @return
     */
    public BaseRtM addNotice(String title,String content){
        BaseRtM baseRtM = new BaseRtM();
        try {
            User user = shareLogin.getUser();
            Notice notice;
            if(user != null){
                notice = new Notice();
                notice.setNttitle(title);
                notice.setNtcontent(content);
                String upDate = patternUtil.formatDate(new Date());
                notice.setNtupdate(upDate);
                notice.setNtupuser(user.getUid());
                noticeDao.save(notice);
                baseRtM.setRtMCode("T");
                baseRtM.setRtMsg("添加成功!");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("当前操作不合法!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误!");
        }finally {
            return baseRtM;
        }
    }
}
