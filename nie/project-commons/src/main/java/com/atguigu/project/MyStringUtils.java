package com.atguigu.project;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyStringUtils {
    // yyyy-MM-dd hh:mm:ss
    
    // yyyy-MM-dd

    /**
     * 转换时间的工具类
     * @param date
     * @return
     */
    public static String formatSimpleDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }

        if (str.trim().equals("")) {
            return true;
        }
        
        return false;
    }

}
