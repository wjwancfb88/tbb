package com.tubaoapi.modules.http;

import org.apache.http.HttpHost;


public class HttpCommonClient extends HttpClient{
	
	public HttpCommonClient() {
		this(DEFAULT_CHARSET);
	}

	public HttpCommonClient(String charset) {
		super(charset);
	
	}

	@Override
	protected HttpHost getProxy() {
		return null;
	}
	
}
