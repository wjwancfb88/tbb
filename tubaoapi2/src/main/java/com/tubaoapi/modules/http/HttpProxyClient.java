package com.tubaoapi.modules.http;

import org.apache.http.HttpHost;

public class HttpProxyClient extends HttpClient{
	
	private HttpHost proxy;
	
	public HttpProxyClient(String proxyHostname,int proxyPort,String proxyScheme){
		this(proxyHostname,proxyPort,proxyScheme,DEFAULT_CHARSET);
	}
	
	public HttpProxyClient(String proxyHostname,int proxyPort,String proxyScheme,String charset){
		super(charset);
		proxy = new HttpHost(proxyHostname, proxyPort, proxyScheme);  
	}

	@Override
	protected HttpHost getProxy() {
		return proxy;
	}
	
	/*
	public HttpProxyClient(String targetHostname,int targetPort,String targetScheme){
		
	}*/
}
