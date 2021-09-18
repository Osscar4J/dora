package com.zhao.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @ClassName: DateUtils
 * @Author: zhaolianqi
 * @Date: 2021/9/18 14:15
 * @Version: v1.0
 */
public class DateUtils {

    /**
     * 日期转化成字符串
     * @Author zhaolianqi
     * @Date 2021/9/18 14:38
     * @param date 日期
     * @param dateStyle {@link DateStyle}
     */
    public static String date2string(Date date, DateStyle dateStyle){
        LocalDateTime localDate = date2localDateTime(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateStyle.getStyle());
        return formatter.format(localDate);
    }

    /**
     * 日期转化成字符串，默认格式：yyyy-MM-dd HH:mm:ss
     * @Author zhaolianqi
     * @Date 2021/9/18 14:39
     * @param date 日期
     */
    public static String date2string(Date date){
        return date2string(date, DateStyle.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 字符串转日期
     * @Author zhaolianqi
     * @Date 2021/9/18 14:58
     * @param dateStr 日期字符串
     * @param dateStyle {@link DateStyle}
     */
    public static Date string2date(String dateStr, DateStyle dateStyle){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateStyle.getStyle());
        return localDateTime2date(LocalDateTime.parse(dateStr, formatter));
    }

    /**
     * 字符串转日期
     * @Author zhaolianqi
     * @Date 2021/9/18 14:59
     * @param dateStr 字符串日期，默认格式：yyyy-MM-dd HH:mm:ss
     */
    public static Date string2date(String dateStr){
        return string2date(dateStr, DateStyle.YYYY_MM_DD_HH_MM_SS);
    }

    public static LocalDateTime date2localDateTime(Date date) {
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static  LocalDate date2localDate(Date date){
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.toLocalDate();
    }

    public static LocalTime date2localTime(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.toLocalTime();
    }

    public static Date localDateTime2date(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date localDate2date(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

}
