package com.tubaoapi.modules.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StringUtils2 {
	
	//<p class=\"P0\">(.*?)</p>","<p>$1</p>"
	//CrawlUtils.replaceHTML(content,"<h2(.*?)>([\\s\\S]*?)</h2>", "")
	public static String replace(String content, String regex,	String replacement) {
		if (content == null) {
			return "";
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		if (m.find()) {
			content = content.replaceAll(regex, replacement);
		}
		return content;
	}
	
	
	public static String clearAll(String content) {
		content = clearHTML(content);
		content = clearTRN(content);
		return content;
	}
	/**
	 * 清除空格，换行
	 */
	public static String clearTRN(String content) {
		 Pattern p_space = Pattern.compile("\\s*|\t|\r|\n", Pattern.CASE_INSENSITIVE);  
	     Matcher m_space = p_space.matcher(content);  
	     content = m_space.replaceAll(""); 
	     return content;
	 }
	
	 public static String clearHTML(String content) {
		 Pattern p_space = Pattern.compile("<[^>]+>", Pattern.CASE_INSENSITIVE);  
	     Matcher m_space = p_space.matcher(content);  
	     content = m_space.replaceAll(""); 
	     return content;
	 }
	 

	
	
	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String trimSplitWithOneBlank(String text){
		return text.replaceAll(" +", " ").trim();
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
	
	
	public static boolean isContainChinese(String s){
		String regEx = "[\\u4e00-\\u9fa5]"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(s); 
		return m.find();
	}
	
	
	public static boolean isEnglish(String s){
		return StringUtils.isAsciiPrintable(s);
	}
	
	
	public static String createSlug(String text){
		text = text.toLowerCase();
		text =  text.replaceAll("[^0-9a-zA-Z ]", " ");
		text = text.replaceAll(" +", " "); //2个以上连续空格替换成1个空格
		text = StringUtils.trimToEmpty(text);
		if(StringUtils.isBlank(text)){
			return  Identities.uuid2().substring(0,10);
		}else{
			return  text.replaceAll(" ","-");
		}
	}
	
	public static String onlyAscii(String text){
		text =  text.replaceAll("[^0-9a-zA-Z ]", " ");
		text = text.replaceAll(" +", " "); //2个以上连续空格替换成1个空格
		return StringUtils.trimToEmpty(text);
	}
	
	public static void main(String[] args){
		String s = "GT5 - Aston Martin V12 Vantage \" 10 - Top Speed";
		System.out.println(onlyAscii(s));
	}
	
	/*
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
	
	
	public static boolean isOneLetter(String s){
		s = StringUtils.trimToEmpty(s);
		if(s.length()==1){
			char c = s.charAt(0);
			return (c>='A' && c<='Z') || (c>='a' && c<='z');
		}else{
			return false;
		}
		
	}

	*/
	
	

	
}
