package com.mmm.server.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil
{
    private static SimpleDateFormat sdfDate=new SimpleDateFormat(ConstantFinalUtil.BUNDLE.getString("format.date"));
    private static SimpleDateFormat sdfDateime=new SimpleDateFormat(ConstantFinalUtil.BUNDLE.getString("format.datetime"));

    /**
     * 进行日期时间格式转换  yyyy-MM-dd HH:mm:ss
     * */
    public static String formatDateTime(Date date) {
        return sdfDateime.format(date);
    }

    /**
     * 进行日期格式转换  yyyy-MM-dd
     * */
    public static String formatDate(Date date) {
        return sdfDate.format(date);
    }

    /**
     * 字符串转日期时间
     * */
    public static Date parseDateTime(String now) {
        try {
            return sdfDateime.parse(now);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转化日期
     * */
    public static Date parseDate(String now) {
        try {
            return sdfDate.parse(now);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

