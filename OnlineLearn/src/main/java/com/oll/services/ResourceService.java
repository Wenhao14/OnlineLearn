package com.oll.services;

import com.oll.cache.ShareLogin;
import com.oll.dao.AnswerDao;
import com.oll.dao.NewsDao;
import com.oll.dao.NoticeDao;
import com.oll.dao.TestPaperDao;
import com.oll.model.*;
import com.oll.util.BaseRtM;
import com.oll.util.CommonUtil;
import com.oll.util.DBUtil.SqlVehicel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    private CommonUtil patternUtil;
    @Resource
    private AnswerDao answerDao;
    @Resource
    private SqlVehicel sqlVehicel;
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

    /**
     * 获取未作答试题
     * @return
     */
    public BaseRtM getUnTps(Integer pageNum,Integer pageSize){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Sort sort = new Sort(Sort.Direction.DESC,"tpupdate");
            Pageable pageable = new PageRequest(pageNum,pageSize,sort);
            Long uid = shareLogin.getUser().getUid();
            List<Long> tpids = answerDao.getTpids(uid);
            if(tpids == null || tpids.size() < 1){
                tpids.add(0l);
            }
            Page<Testpaper> unTps = testPaperDao.getUnTPs(tpids,new Date(),pageable);
            List<Testpaper> tpList = unTps.getContent();
            int len = tpList.size();
            for(int i = 0;i < len;i++){
                tpList.get(i).setTpcontent("");
            }
            baseRtM.setRtMCode("T");
            baseRtM.setRtMData(tpList);
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * 获取试卷内容
     * @param tpId
     */
    public BaseRtM getTpContent(Long tpId){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Testpaper testpaper = testPaperDao.findOne(tpId);
            if(testpaper != null){
                baseRtM.setRtMCode("T");
                baseRtM.setRtMData(testpaper.getTpcontent());
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("没有该试卷!");
            }
        }catch (Exception e){
            e.printStackTrace();
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * 保存答题结果
     * @param tpId
     * @param score
     * @return
     */
    public BaseRtM addAnswer(Long tpId,String score){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Answer answer = new Answer();
            Long uid = shareLogin.getUser().getUid();
            answer.setTpid(tpId);
            answer.setAgrade(score);
            answer.setUid(uid);
            answer.setAdate(patternUtil.formatDateTime(new Date()));
            Object result = answerDao.save(answer);
            if(result instanceof Answer){
                baseRtM.setRtMCode("T");
                baseRtM.setRtMsg("答题结果保存成功!");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("对不起，答题结果保存失败!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误!");
        }finally {
            return baseRtM;
        }
    }
    /**
     * 获取已作答试卷
     * @param pageNum
     * @param pageSize
     * @return
     */
    public BaseRtM getEnTps(Integer pageNum,Integer pageSize){
        BaseRtM baseRtM = new BaseRtM();
        try {
            String sql = "SELECT tp.tpId,tp.tpName,tp.tpDescribe,a.aDate,a.aGrade FROM testpaper tp,answer a WHERE tp.tpId IN (SELECT a.tpID FROM answer a WHERE a.uId = ?) AND tp.tpIsDel = 0 ORDER BY a.aDate DESC LIMIT " + pageNum*pageSize+","+pageSize;
            Long uid = shareLogin.getUser().getUid();
            String[] papam = {Long.toString(uid)};
            List result = sqlVehicel.SqlSelect(sql,papam);
            baseRtM.setRtMCode("T");
            baseRtM.setRtMData(result);
        }catch (Exception e){
            e.printStackTrace();
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误!");
        }finally {
            return baseRtM;
        }
    }
}
