package com.tubaoapi.modules.web.upload;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ServletFileUploadContext {
	
	private boolean multipart;
	private Map<String, FileItem> fileMap = new  HashMap<String, FileItem>();
	
	
	public ServletFileUploadContext(HttpServletRequest request){
		multipart= ServletFileUpload.isMultipartContent(request);
		if(!multipart){
			return ;
		}
		
		try{
			 FileItemFactory factory = new DiskFileItemFactory();
	         ServletFileUpload upload = new ServletFileUpload(factory);
	         List<FileItem> items = upload.parseRequest(request);
	         for(FileItem item:items){
	        	if(!item.isFormField() && item.getSize()>0){
	        		fileMap.put(item.getFieldName(), item);
	        	}
	         }
		}catch(FileUploadException e){
			e.printStackTrace();
		}
		
	}
	
	public FileItem getFileItem(String name){
		return fileMap.get(name);
	}
}
