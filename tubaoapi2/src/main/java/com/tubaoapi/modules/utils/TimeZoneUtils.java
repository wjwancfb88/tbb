package com.tubaoapi.modules.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeZoneUtils {
	public static Date utcToDate(String utc){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");  
        try {
			return format.parse(utc);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}   
         
	}
}
