package com.tubaoapi.modules.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        return   new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    }

}
