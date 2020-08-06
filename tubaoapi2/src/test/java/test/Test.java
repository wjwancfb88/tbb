package test;

import java.util.Map;

import com.tubaoapi.modules.web.Servlets;

public class Test {
	
	private static final String API_BASE_URL = "http://localhost:8080/tubaoapi";
	
	public static String buildUrl(String uri,Map<String, Object> params){
		String url = API_BASE_URL + uri + "?" + Servlets.encodeParameterString(params);
		return url;
	}
	
}
