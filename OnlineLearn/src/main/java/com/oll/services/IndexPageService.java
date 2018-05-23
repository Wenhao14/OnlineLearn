package com.oll.services;

import com.oll.dao.CourseDao;
import com.oll.dao.NewsDao;
import com.oll.dao.NoticeDao;
import com.oll.model.Course;
import com.oll.model.News;
import com.oll.model.Notice;
import com.oll.util.BaseRtM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by NewDarker on 2018/5/11.
 * ��ҳ����
 */
@Service
public class IndexPageService {
    @Resource
    private NewsDao newsDao;
    @Resource
    private NoticeDao noticeDao;
    @Resource
    private CourseDao courseDao;
    /**
     * ��ҳ��ȡ����
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
            baseRtM.setRtMsg("�ɹ�");
            baseRtM.setRtMCode("T");
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("�ڲ�����");
        }finally {
            return baseRtM;
        }
    }

    /**
     * ��ҳ��ȡ����
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
            baseRtM.setRtMsg("�ɹ�");
            baseRtM.setRtMCode("T");
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMsg("�ڲ�����");
        }finally {
            return baseRtM;
        }
    }
    /**
     * ��ȡ���ſγ�
     * @return
     */
    public BaseRtM getHotCourse(Integer pNum,Integer pSize){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Sort sort = new Sort(Sort.Direction.DESC,"cseltime");
            Pageable pageable = new PageRequest(pNum,pSize,sort);
            List<Course> courses = courseDao.getHotCoures(pageable).getContent();
            baseRtM.setRtMCode("T");
            baseRtM.setRtMData(courses);
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMData("�ڲ�����!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * ��ȡ�Ƽ��γ�
     * @return
     */
    public BaseRtM getPushCourse(Integer pNum,Integer pSize){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Sort sort = new Sort(Sort.Direction.DESC,"cupdate");
            Pageable pageable = new PageRequest(pNum,pSize,sort);
            List<Course> courses = courseDao.getPushCourse(pageable).getContent();
            baseRtM.setRtMCode("T");
            baseRtM.setRtMData(courses);
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMData("�ڲ�����!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * ��ȡ���пγ�
     * @param pageNum
     * @param pageSize
     * @return
     */
    public BaseRtM getAllCourse(Integer pageNum,Integer pageSize){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Sort sort = new Sort(Sort.Direction.DESC,"cupdate");
            Pageable pageable = new PageRequest(pageNum,pageSize,sort);
            List<Course> courses = courseDao.getHotCoures(pageable).getContent();
            baseRtM.setRtMCode("T");
            baseRtM.setRtMData(courses);
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMData("�ڲ�����!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * ������ȡ�γ�
     * @param mId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public BaseRtM getCourseByModule(Long mId,Integer pageNum,Integer pageSize){
        BaseRtM baseRtM = new BaseRtM();
        try {
            Sort sort = new Sort(Sort.Direction.DESC,"cupdate");
            Pageable pageable = new PageRequest(pageNum,pageSize,sort);
            List<Course> courses = courseDao.getCourseByMid(mId,pageable).getContent();
            baseRtM.setRtMCode("T");
            baseRtM.setRtMData(courses);
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMData("�ڲ�����!");
        }finally {
            return baseRtM;
        }
    }

    /**
     * ���ؼ�����ȡ�γ�
     * @param key
     * @param pageNum
     * @param pageSize
     * @return
     */
    public BaseRtM getCourseByKey(String key,Integer pageNum,Integer pageSize){
        BaseRtM baseRtM = new BaseRtM();
        try {
            key = "%"+key+"%";
            Sort sort = new Sort(Sort.Direction.DESC,"cupdate");
            Pageable pageable = new PageRequest(pageNum,pageSize,sort);
            List<Course> courses = courseDao.getCourseByCnameLike(key,pageable).getContent();
            baseRtM.setRtMCode("T");
            baseRtM.setRtMData(courses);
        }catch (Exception e){
            baseRtM.setRtMCode("F");
            baseRtM.setRtMData("�ڲ�����!");
        }finally {
            return baseRtM;
        }
    }
}
