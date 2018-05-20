package com.oll.util;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NewDarker on 2018/5/11.
 */
@Service
public class CommonUtil {
    private static ThreadLocal<SimpleDateFormat> dateFormatPool = new ThreadLocal<>();
    private static ThreadLocal<SimpleDateFormat> dateTimeFormatPool = new ThreadLocal<>();
    private static final String hIPath = "src/main/resources/static/upload/headImg/";
    private static final String hIRelativePath = "/upload/headImg/";
    /**
     * 格式化日期
     * @param date
     * @return
     */
    public String formatDate(Date date){
        SimpleDateFormat dateFormat = dateFormatPool.get();
        if(null == dateFormat){
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormatPool.set(dateFormat);
        }
        return dateFormat.format(date);
    }

    /**
     * 格式化日期时间
     * @param date
     * @return
     */
    public String formatDateTime(Date date){
        SimpleDateFormat dateTimeFormat = dateFormatPool.get();
        if(null == dateTimeFormat){
            dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateTimeFormatPool.set(dateTimeFormat);
        }
        return dateTimeFormat.format(date);
    }

    public static String gethIPath() {
        return hIPath;
    }

    public static String gethIRelativePath() {
        return hIRelativePath;
    }
}
