package com.tubaoapi.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.tubaoapi.Config;



public class SiteFilter extends OncePerRequestFilter {
	
	
	private static Config config = Config.getInstance();
	
	

	

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		

		
		String uri = request.getRequestURI();
		uri = uri.replaceAll(request.getContextPath(), "");
		

		

		
		filterChain.doFilter(request, response);
		
		
		
		
	}
	

	
}
