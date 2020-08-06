package com.dhwooden.ep.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { APIException.class})
	@ResponseBody
	public final Object handleAPIException(APIException ex, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return new Message(HttpServletResponse.SC_BAD_REQUEST,ex.getMessage());
	}
	
	
	@ExceptionHandler(value = { Exception.class})
	@ResponseBody
	public final Object handleRuntime(Exception ex, HttpServletResponse response) {
		ex.printStackTrace();
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return new Message(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,ex.getMessage());
	}

	@ExceptionHandler(value = { AuthorizationException.class})
	@ResponseBody
	public final void handleAuthorizationException(AuthorizationException ex, HttpServletResponse response) throws IOException {
		ex.printStackTrace();
		//response.setCharacterEncoding("GBK");
		response.setHeader("Content-Type", "text/json;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//		JSONObject js= JSON.parseObject("{error:'无权限'}");
//		this.writeJson(response, js);
//		return  js;
		response.sendRedirect("/common/index");
		//return new Message(HttpServletResponse.SC_UNAUTHORIZED,js.toJSONString());
	}
	public void writeJson(HttpServletResponse resp ,JSONObject json ){
		PrintWriter out = null;
		try {
			//设定类容为json的格式
			resp.setContentType("application/json;charset=UTF-8");
			out = resp.getWriter();
			//写到客户端
			out.write(json.toJSONString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}

	
	
}