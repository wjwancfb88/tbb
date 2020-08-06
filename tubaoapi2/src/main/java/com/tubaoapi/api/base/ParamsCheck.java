package com.tubaoapi.api.base;

import org.apache.commons.lang3.StringUtils;

public class ParamsCheck {
	
	
	public static void checkExpectedOne(String fields,String... a){
		if(!hasOne(a)){
			throw new APIException("No filter selected. Expected one of " + fields);
		}
	}
	
	
	
	public static void checkUnsignedInteger(String field,String s){
		if(!StringUtils.isNumeric(s)){
			throw new APIException(String.format("Invalid unsigned integer value of %s:'%s'", field,s));
		}
	}
	
	public static void checkBoolean(String field,String s){
		if(!isBoolean(s)){
			throw new APIException(String.format("Invalid boolean value of %s:'%s'", field,s));
		}
	}
	
	public static void checkRange(String field,int value,int min,int max){
		if(!(value>=min && value<=max)){
			throw new APIException(String.format("Invalid value '%s',%s must be within the range [%s,%s]", value,field,min,max));
		}
	}
	
	
	public static boolean hasOne(String... a){
		boolean result = false;
		for(String s:a){
			if(StringUtils.isNotBlank(s)){
				return true;
			}
		}
		return result;
	}
	
	public static boolean isBoolean(String s){
		return "true".equalsIgnoreCase(s) || "false".equalsIgnoreCase(s);
	}
}
