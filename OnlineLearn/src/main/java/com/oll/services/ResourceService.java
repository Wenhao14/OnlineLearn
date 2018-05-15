package com.oll.services;

import com.oll.cache.ShareLogin;
import com.oll.dao.NewsDao;
import com.oll.dao.NoticeDao;
import com.oll.dao.TestPaperDao;
import com.oll.model.News;
import com.oll.model.Notice;
import com.oll.model.Testpaper;
import com.oll.model.User;
import com.oll.util.BaseRtM;
import com.oll.util.PatternUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by NewDarker on 2018/5/11.
 */
@Service
public class ResourceService {
    @Resource
    private TestPaperDao testPaperDao;
    @Resource
    private NewsDao newsDao;
    @Resource
    private NoticeDao noticeDao;
    @Resource
    private ShareLogin shareLogin;
    @Resource
    private PatternUtil patternUtil;

    /**
     * 发布试题
     * @param tpName
     * @param describe
     * @param passdate
     * @param content
     * @return
     */
    public BaseRtM addTestPaper(String tpName, String describe, Date passdate,String content){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Long uid = shareLogin.getUser().getUid();
            if(uid != null){
                Testpaper testPaper = new Testpaper();
                testPaper.setTpname(tpName);
                testPaper.setTpupuser(uid);
                testPaper.setTpisdel("0");
                testPaper.setTppassdate(passdate);
                testPaper.setTpdescribe(describe);
                testPaper.setTpcontent(content);
                String date = patternUtil.formatDate(new Date());
                testPaper.setTpupdate(date);
                Object result = testPaperDao.save(testPaper);
                if(result instanceof Testpaper){
                    baseRtM.setRtMCode("T");
                    baseRtM.setRtMsg("添加成功!");
                }else {
                    baseRtM.setRtMCode("F");
                    baseRtM.setRtMsg("添加失败!");
                }
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("账户状态异常!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误!");
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
                Object obj = newsDao.save(news);
                if(obj instanceof News){
                    baseRtM.setRtMCode("T");
                    baseRtM.setRtMsg("添加成功!");
                }else {
                    baseRtM.setRtMCode("F");
                    baseRtM.setRtMsg("添加失败!");
                }
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
                Object obj = noticeDao.save(notice);
                if(obj instanceof Notice){
                    baseRtM.setRtMCode("T");
                    baseRtM.setRtMsg("添加成功!");
                }else {
                    baseRtM.setRtMCode("F");
                    baseRtM.setRtMsg("添加失败!");
                }
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
