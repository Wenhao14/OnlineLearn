package com.oll.util;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NewDarker on 2018/5/11.
 */
@Service
public class PatternUtil {
    private static ThreadLocal<SimpleDateFormat> dateFormatPool = new ThreadLocal<>();

    public String formatDate(Date date){
        SimpleDateFormat dateFormat = dateFormatPool.get();
        if(null == dateFormat){
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormatPool.set(dateFormat);
        }
        return dateFormat.format(date);
    }
}
