package com.tubaoapi.web.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class CustomMappingExceptionResolver extends SimpleMappingExceptionResolver{

	@Override
	protected ModelAndView getModelAndView(String viewName, Exception ex,
			HttpServletRequest request) {
		String uri = request.getRequestURI();
		uri = uri.replaceAll(request.getContextPath(), "");
		if(uri.startsWith("/api/") && viewName.equals("error/500")){
			ex.printStackTrace();
			return new ModelAndView("error/500_api");
		}else{
			return super.getModelAndView(viewName, ex, request);
		}
	}
}
