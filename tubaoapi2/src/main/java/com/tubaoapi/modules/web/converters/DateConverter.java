package com.tubaoapi.modules.web.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;




public class DateConverter implements Converter<String, Date> {    
	@Override    
	public Date convert(String source) {    
		if(StringUtils.isBlank(source)){
			return null;
		}
		String fmt = null;
		source = StringUtils.trimToEmpty(source);
		
		if(source.length()>=8 && source.length()<=10){
			fmt = "yyyy-MM-dd";
		}else if(source.length()>10 && source.length()<=19){
			fmt = "yyyy-MM-dd HH:mm:ss";
		}else{
			return null;
		}
	    SimpleDateFormat dateFormat = new SimpleDateFormat(fmt);    
	    dateFormat.setLenient(false);    
	    try {    
	        return dateFormat.parse(source);    
	    } catch (ParseException e) {    
	        e.printStackTrace();    
	    }           
	    return null;    
	}  
}