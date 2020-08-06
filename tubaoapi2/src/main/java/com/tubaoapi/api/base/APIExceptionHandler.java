package com.tubaoapi.api.base;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { APIException.class})
	@ResponseBody
	public final Object handleAPIException(APIException ex, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return new Error(HttpServletResponse.SC_BAD_REQUEST,ex.getMessage());
	}
	
	
	@ExceptionHandler(value = { Exception.class})
	@ResponseBody
	public final Object handleRuntime(Exception ex, HttpServletResponse response) {
		ex.printStackTrace();
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return new Error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,ex.getMessage());
	}

	
	
	
}