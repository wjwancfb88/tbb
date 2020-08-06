package com.tubaoapi.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class AdminBaseController extends BaseController {
	
	public static final String FLASH_MESSAGE = "flashMessage";
	public static final String FLASH_ERROR = "flashError";

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;


	public String redirectReferer() {
		String referer = request.getHeader("referer");
		if (referer == null)
			return "redirect:";
		else
			return "redirect:" + referer;
	}
	
	public void addFlashMessage(RedirectAttributes ra,String flashMessage){
		ra.addFlashAttribute(FLASH_MESSAGE, flashMessage);
	}
	
	public void addFlashError(RedirectAttributes ra,String flashError){
		ra.addFlashAttribute(FLASH_ERROR, flashError);
	}
	
	public void addModelMessage(Model model,String flashMessage){
		model.addAttribute(FLASH_MESSAGE, flashMessage);
	}
	
	public void addModelError(Model model,String flashError){
		model.addAttribute(FLASH_ERROR, flashError);
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public HttpServletResponse getResponse() {
		return response;
	}

}
