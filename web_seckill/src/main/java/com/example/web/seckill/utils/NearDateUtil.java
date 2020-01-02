package com.example.web.seckill.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 刘欢
 * @Date 2019/12/30
 */
public class NearDateUtil {

    public static Date getNearDate(Date date) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String nowStr = simpleDateFormat.format(date);
        nowStr = nowStr.substring(0, nowStr.lastIndexOf(":"));
        String nearDateStr = nowStr+ ":00";
        return simpleDateFormat.parse(nearDateStr);
    }

}
