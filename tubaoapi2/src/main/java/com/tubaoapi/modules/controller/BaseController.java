package com.tubaoapi.modules.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;



public abstract class BaseController {
	
	private final static String VIEW_ERROR_404 = "error/404";
	
	public String error404(Model model){
		model.addAttribute("responseErrorCode", HttpServletResponse.SC_NOT_FOUND);
		return VIEW_ERROR_404;
	}
}
