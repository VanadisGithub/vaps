package com.vanadis.lang.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-08-05 14:32
 */
public class Times {

    public final static String YEAR = "yyyy";

    public final static String MONTH = "yyyyMM";

    public final static String DATE = "yyyyMMdd";

    public final static String YYYY_MM_DD = "yyyy-MM-dd";

    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";

    public static void main(String[] args) {
        System.out.println(Times.formatTime(LocalDateTime.now(), YYYY_MM_DD_HH_MM_SS));
        System.out.println(Times.convertStrToLDT("2019-08-05 02:43:11", YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * Date转换为LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param time
     * @return
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定时间的指定格式
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 字符串转LocalDateTime
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static LocalDateTime convertStrToLDT(String dateStr, String pattern) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }
}
