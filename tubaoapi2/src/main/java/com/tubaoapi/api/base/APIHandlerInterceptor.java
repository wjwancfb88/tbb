package com.tubaoapi.api.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tubaoapi.modules.security.utils.Digests;
import com.tubaoapi.modules.utils.DateUtils2;
import com.tubaoapi.modules.utils.Encodes;

public class APIHandlerInterceptor implements HandlerInterceptor  {
	
	private static Logger logger = LoggerFactory.getLogger(APIHandlerInterceptor.class);

	
	

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(!(handler instanceof HandlerMethod)){
			return true;
		}
		String client = StringUtils.trimToEmpty(request.getParameter("client"));
		String time = StringUtils.trimToEmpty(request.getParameter("time"));
		String token = StringUtils.trimToEmpty(request.getParameter("token"));
		
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String start = ctx + "/api/" + client;
		String key = API.CLIENTS.get(client);
		
		if(key==null || !uri.startsWith(start)){
			throw new APIException(String.format("Invalid value of client:'%s'",client));
		}
		
		if(!StringUtils.isNumeric(time)){
			throw new APIException(String.format("Invalid value of time:'%s'",time));
		}
		
		if((System.currentTimeMillis() - Long.parseLong(time)) > (10 * DateUtils2.ONEMINUTE)){
			throw new APIException(String.format("Token is expired"));
		}
		
		if(!API.checkToken(client, key, time, token)){
			throw new APIException(String.format("Illegal access token:'%s'",token));
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//APIContext.clean();
	}
	
	
	public static boolean checkSign(String sign,String... strings){
		if(StringUtils.isBlank(sign))
			return false;
		StringBuilder sb = new StringBuilder();
		int i=0;
		for(String s:strings){
			if(i>0){
				sb.append("_");
			}
			sb.append(s);
			i++;
		}
		
		byte[] bs = Digests.sha1(sb.toString().getBytes());
		
		String serverSign = Encodes.encodeHex(bs);
		
		logger.debug("server sign:{}", serverSign);
		return serverSign.equalsIgnoreCase(sign);
	}

   
}
