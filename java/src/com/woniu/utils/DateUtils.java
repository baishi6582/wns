package com.woniu.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 关于时间的工具类
 * @author woniu
 * @date 2019/08/29 19:51
 */
public class DateUtils {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final int NEXT_DAY = 1;

    /**
     * 将字符串转换为Date类型
     * @param dateStr 需要转换的字符串日志
     * @param format 日期格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date str2Date(String dateStr, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr);
    }

    /**
     * 将Date类型转化为String类型
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 指定某一天的一个日期
     * @param date
     * @return
     */
    public static String getNextDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        date = calendar.getTime();
        return date2Str(date, DEFAULT_FORMAT);
    }

    /**
     * 获取当前时间的下一天
     * @return
     */
    public static String getNextDate() {
        Date date = new Date();
        return getNextDate(date);
    }

    /**
     * 获取一下个日期
     * @param dateStr
     * @return 返回“yyyy-MM-dd HH:mm:ss”的数据格式
     * @throws ParseException
     */
    public static String getNextDate(String dateStr) throws ParseException {
        return getNextDate(dateStr, DEFAULT_FORMAT, NEXT_DAY);
    }

    /**
     * 获取指定天数的延后或者前移几天
     * @param dateStr 指定的时间
     * @param delay 天数信息
     * @return
     * @throws ParseException
     */
    public static String getNextDate(String dateStr, int delay) throws ParseException {
        return getNextDate(dateStr, DEFAULT_FORMAT, delay);
    }

    /**
     * 获取指定天数与格式的延后或者前移几天
     * @param dateStr
     * @param format
     * @param delay
     * @return
     * @throws ParseException
     */
    public static String getNextDate(String dateStr, String format, int delay) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date newDate = sdf.parse(dateStr);
        calendar.setTime(newDate);
        calendar.add(Calendar.DATE, delay);
        newDate = calendar.getTime();
        return date2Str(newDate, format);
    }

}
