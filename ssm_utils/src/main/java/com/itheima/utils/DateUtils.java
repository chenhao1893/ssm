package com.itheima.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理类
 */
public class DateUtils {

    /**
     * 日期转为字符串
     * @param date  要转换的日期
     * @param patt  转换后的格式
     * @return
     */
    public static String dateToString(Date date,String patt){
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        String format=sdf.format(date);
        return format;
    }

    /**
     * 字符串转为日期
     * @param str  要转换的字符串
     * @param patt 转换后的格式
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        Date date=sdf.parse(str);
        return date;
    }
}
