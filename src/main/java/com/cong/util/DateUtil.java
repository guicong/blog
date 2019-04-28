package com.cong.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author guicong
 * @description
 * @since 2019-04-22
 */
public class DateUtil {

    /**
     * 将当前时间按指定格式返回
     * @param pattern
     * @return
     */
    public static String getCurrentTimeByFormat (String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 得到几天前或几天后的时间并按指定格式返回
     * @param days
     * @param pattern
     * @return
     */
    public static String  getSeveralDaysByFormat (long days, String pattern) {
        return  LocalDateTime.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }


    public static void main(String[] args) {
        System.out.println(getSeveralDaysByFormat(-1, "yyyy-MM-dd HH:mm:ss"));
    }

}
