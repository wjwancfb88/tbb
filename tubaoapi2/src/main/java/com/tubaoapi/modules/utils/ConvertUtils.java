package com.tubaoapi.modules.utils;

import org.apache.commons.lang3.StringUtils;

public class ConvertUtils {
	public static Long string2Long(String s){
		if(StringUtils.isBlank(s) || !StringUtils.isNumeric(s))
			return null;
		
		try{
			return new Long(s);
		}catch(Exception e){
			return null;
		}
	}
	
	public static Integer string2Integer(String s){
		if(StringUtils.isBlank(s) || !StringUtils.isNumeric(s))
			return null;
		
		try{
			return new Integer(s);
		}catch(Exception e){
			return null;
		}
	}
	
	public static Double string2Double(String s){
		if(StringUtils.isBlank(s))
			return null;
		
		try{
			return new Double(s);
		}catch(Exception e){
			return null;
		}
	}
	
	public static String long2String(Long l){
		if(l==null)
			return "";
		else
			return String.valueOf(l);
	}
}
