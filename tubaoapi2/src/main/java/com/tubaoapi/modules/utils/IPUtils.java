package com.tubaoapi.modules.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class IPUtils {
	public static String getClientIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");

      
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("X-Real-IP");
        }
        
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        
        //if(StringUtils.isNotBlank(ip)) {
        //    ip = ip.split(",")[0];
        //}
        
        return StringUtils.trimToEmpty(ip);

    }
}
