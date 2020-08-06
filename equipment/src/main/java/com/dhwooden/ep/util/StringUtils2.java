package com.dhwooden.ep.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtils2 {
	public static String capitalizeWords(String text){
		text = trimSplitWithOneBlank(text);
		String[] a = text.split(" ");
		for(int i=0;i<a.length;i++){
			a[i]=StringUtils.capitalize(a[i]);
		}
		return StringUtils.join(a," ");
	}
	
	public static String trimSplitWithOneBlank(String text){
		return text.replaceAll(" +", " ");
	}
	
	public static String trimSplitWithoutBlank(String text){
		return text.replaceAll(" +", "");
	}
	
	public static String cutString(String content,int length){
		if(content==null){
			return "";
		}
		if(content.length()>length){
			content = content.substring(0,length-1);
		}
		return content;
	}
	
	/*
	 * 是否是数字或小数
	 */
	public static boolean isNumber(String s){
		if(StringUtils.isBlank(s)){
			return false;
		}
		String reg = "\\d+(\\.\\d+)?";
		return s.matches(reg);
	}
}
