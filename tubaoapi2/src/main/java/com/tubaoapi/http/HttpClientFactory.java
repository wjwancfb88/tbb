package com.tubaoapi.http;

import com.tubaoapi.Config;
import com.tubaoapi.modules.http.HttpClient;
import com.tubaoapi.modules.http.HttpCommonClient;
import com.tubaoapi.modules.http.HttpProxyClient;

public class HttpClientFactory {
	
	public static HttpClient create(){
		boolean useProxy = false;
		
		Config config = Config.getInstance();
		
		if(Config.getInstance()==null || !config.isProd()){
			useProxy = true;
		}
		return create(useProxy);
	}
	
	public static HttpClient create(boolean useProxy){
		
		return useProxy?new HttpProxyClient(Proxy.HOSTNAME, Proxy.PORT, Proxy.SCHEME):new HttpCommonClient();
	}
}
