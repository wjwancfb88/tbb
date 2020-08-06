package com.tubaoapi.http;


public interface Proxy {
	
	public static final String HOSTNAME = "127.0.0.1";
	public static final int PORT = 8128;
	public static final String SCHEME = "http";
	
	
	public static final int SOCKS_PORT = 8126;
	
	

	/*
	HTTP(S)代理服务器：
	127.0.0.1:8128
	SOCKS代理服务器：
	127.0.0.1:8126	
	*/
}
