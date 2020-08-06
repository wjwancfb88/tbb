package com.tubaoapi.modules.utils;

import java.text.DecimalFormat;

public class NumberUtils {
	
	
	public static String format(Object value){  
		return format(",###.##", value);
	}
	
	public static String format(String pattern,Object value){  
		DecimalFormat df = new DecimalFormat();  
		df.applyPattern(pattern);
		return df.format(value);
	} 
	
	public static void main(String[] args){
		System.out.println(format(",###.##",34424545443.32343));
	}
}
