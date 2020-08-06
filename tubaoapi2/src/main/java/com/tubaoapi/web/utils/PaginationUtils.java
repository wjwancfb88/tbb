package com.tubaoapi.web.utils;

import com.tubaoapi.modules.persistence.pagination.model.Page;

public class PaginationUtils {

	public static String createPage(long pageNo,long current,String pageUrl,boolean isGet,String text){
		StringBuilder sb = new StringBuilder();
		sb.append("<li");
		if(pageNo==current){
			sb.append(" class='active'");
		}
		sb.append("><a href='");
				
		if(isGet){
			sb.append(pageUrl);
			if(pageNo>1){
				sb.append(pageNo);
			}
		}else{
			sb.append("javascript:gotoPage(").append(pageNo).append(");");
			
		}
		sb.append("'>");
		sb.append(text);
		sb.append("</a></li>");
		return sb.toString();
	}
	
	public static String createMobilePage(Page page,boolean next,String pageUrl,boolean isGet,String text){
		
		boolean disabled = !(next?page.hasNextPage():page.hasPreviousPage());
		long pageNo = 0L;
		if(next){
			pageNo = page.getPage()+1;
		}else{
			pageNo = page.getPage()-1;
		}
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("<li");
		sb.append(" class='");
		if(next){
			sb.append("next");
		}else{
			sb.append("previous");
		}
		if(disabled){
			sb.append(" disabled");
		}
		sb.append("'");
		sb.append("><a href='");
				
		if(isGet){
			if(disabled){
				sb.append("javascript:;");
			}else{
				sb.append(pageUrl);
				if(pageNo>1){
					sb.append("/page_").append(pageNo);
				}
			}
			
		}else{
			if(disabled){
				sb.append("javascript:;");
			}else{
				sb.append("javascript:gotoPage(").append(pageNo).append(");");
			}
			
		}
		sb.append("'>");
		if(next){
			sb.append(text);
			sb.append("<span aria-hidden='true'> &rarr;</span>");
		}else{
			sb.append("<span aria-hidden='true'>&larr; </span>");
			sb.append(text);
		}
		
		sb.append("</a></li>");
		return sb.toString();
	}
}
