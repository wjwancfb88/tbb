package com.dhwooden.ep.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kaiwang on 2017/11/24.
 */
public class DateUtil {

    //获取某年某月的最后一天
    public static String getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
        return  new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    }
    //获取某年某月的第一天
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
        return   new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    }

    //String转date
    public static Date Stringfordate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }


    //获取date+1
    public static String afterDay(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(date));
        cal.add(Calendar.DATE,1);
        Date day =cal.getTime();
        String startdate = (new SimpleDateFormat("yyyy-MM-dd")).format(day);
        return  startdate;
    }

    //获取date+1
    public static String afterDay2(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mi:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(date));
        cal.add(Calendar.DATE,1);
        Date day =cal.getTime();
        String startdate = (new SimpleDateFormat("yyyy-MM-dd hh:mi:ss")).format(day);
        return  startdate;
    }

}
