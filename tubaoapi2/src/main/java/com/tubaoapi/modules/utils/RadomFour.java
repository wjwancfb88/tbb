package com.tubaoapi.modules.utils;

import java.util.Date;

/**
 * Created by kaiwang on 2018/7/13.
 */
public class RadomFour {

     public  String getFourNumBer(String factory){
        Date current = new Date();
        String number_date=(DateUtils2.d2s(current, "yyyyMMddhhmmss"));
        Integer num=(int)(Math.random()*9000)+1000;
        return factory+number_date+num;
    }


}
