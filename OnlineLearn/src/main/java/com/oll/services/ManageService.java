package com.oll.services;

import com.oll.cache.ShareLogin;
import com.oll.dao.NewsDao;
import com.oll.dao.NoticeDao;
import com.oll.dao.UserDao;
import com.oll.model.News;
import com.oll.model.Notice;
import com.oll.model.User;
import com.oll.util.BaseRtM;
import com.oll.util.CommonUtil;
import com.oll.util.DBUtil.SqlVehicel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by NewDarker on 2018/5/24.
 */
@Service
public class ManageService {
    @Resource
    private UserDao userDao;
    @Resource
    private SqlVehicel sqlVehicel;
    @Resource
    private ShareLogin shareLogin;
    @Resource
    private CommonUtil commonUtil;
    @Resource
    private NewsDao newsDao;
    @Resource
    private NoticeDao noticeDao;
    /**
     * 用户管理删除、恢复物理删除
     * @param type
     * @param uNme
     * @return
     */
    public BaseRtM userDeal(String type,String uNme){
        BaseRtM baseRtM = new BaseRtM();
        try{
            if(type.equals("1")){
                userDao.userDelOrRe(uNme,"1");
                baseRtM.setRtMCode("T");
                baseRtM.setRtMsg("删除成功");
            }else if(type.equals("2")){
                userDao.userDelOrRe(uNme,"0");
                baseRtM.setRtMCode("T");
                baseRtM.setRtMsg("恢复成功");
            }else if(type.equals("3")){
                userDao.deleteByUsername(uNme);
                baseRtM.setRtMCode("T");
                baseRtM.setRtMsg("删除成功");
            }else {
               baseRtM.setRtMCode("F");
               baseRtM.setRtMsg("参数异常!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误!");
        }finally {
           return baseRtM;
        }
    }

    /**
     * 获取逻辑删除的用户
     * @param pNum
     * @param pSize
     * @return
     */
    public BaseRtM getLogicDelU(Integer pNum,Integer pSize) {
        BaseRtM baseRtM = new BaseRtM();
        try {
            Pageable pageable = new PageRequest(pNum, pSize);
            List<User> users = userDao.findByIsdel("1", pageable).getContent();
            baseRtM.setRtMCode("T");
            baseRtM.setRtMData(users);
        } catch (Exception e) {
            e.printStackTrace();
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误!");
        } finally {
            return baseRtM;
        }
    }

    /**
     * 用户排名
     * @param type
     * @param pNum
     * @param pSize
     * @return
     */
    public BaseRtM userRank(String type,Integer pNum,Integer pSize){
        BaseRtM baseRtM = new BaseRtM();
        try {
            String sql;
            if(type.equals("2")){
                sql = "SELECT u.uId,u.userName,um.uName,um.uGoal,um.uRank FROM USER u,usermsg um WHERE u.uId = um.uId AND u.isDel = 0 ORDER BY um.uGoal ASC LIMIT ";

            }else {
                sql = "SELECT u.uId,u.userName,um.uName,um.uGoal,um.uRank FROM USER u,usermsg um WHERE u.uId = um.uId AND u.isDel = 0 ORDER BY um.uGoal DESC LIMIT ";
            }
            sql +=  pNum*pSize+","+pSize;
            List result = sqlVehicel.SqlSelect(sql,null);
            baseRtM.setRtMCode("T");
            baseRtM.setRtMData(result);
        } catch (Exception e) {
            e.printStackTrace();
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("内部错误!");
        } finally {
            return baseRtM;
        }
    }

    /**
     * 添加新闻公告
     * @param type
     * @param title
     * @param content
     * @return
     */
    public BaseRtM addNN(String type,String title,String content){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Long uid = shareLogin.getUser().getUid();
            if(type.equals("1")){
                News news = new News();
                news.setNupuser(uid);
                news.setNtitle(title);
                news.setNurl(content);
                news.setNupdate(commonUtil.formatDate(new Date()));
                newsDao.save(news);
                baseRtM.setRtMCode("T");
                baseRtM.setRtMsg("发布新闻成功!");
            }else if(type.equals("2")){
                Notice notice = new Notice();
                notice.setNtupuser(uid);
                notice.setNtcontent(content);
                notice.setNttitle(title);
                notice.setNtupdate(commonUtil.formatDate(new Date()));
                noticeDao.save(notice);
                baseRtM.setRtMCode("T");
                baseRtM.setRtMsg("发布公告成功!");
            }else {
                baseRtM.setRtMCode("F");
                baseRtM.setRtMsg("非法参数!");
            }
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("发布失败!");
        }finally {
            return baseRtM;
        }
    }
}
