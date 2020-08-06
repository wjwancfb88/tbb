package com.dhwooden.ep.base;



import com.dhwooden.ep.util.Digests;
import com.dhwooden.ep.util.Encodes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class API {
	public static final String DEFAULT_MAX_RESULTS = "10";
	public static final int MAX_RESULTS = 50;
	
	/**
	 * OA
	 */
	public static final String CLIENT_OA = "oa";
	public static final String CLIENT_OA_KEY = "iW3dVx";
	
	
	/**
	 * CRM
	 */
	public static final String CLIENT_CRM = "crm";
	public static final String CLIENT_CRM_KEY = "pXt7j";

	/**
	 * LeBang
	 */
	public static final String CLIENT_LEB = "leb";
	public static final String CLIENT_LEB_KEY = "t45re";
	

	
	public static final Map<String,String> CLIENTS = new HashMap<>();
	
	static {
		CLIENTS.put(CLIENT_OA, CLIENT_OA_KEY);
		CLIENTS.put(CLIENT_CRM, CLIENT_CRM_KEY);
		CLIENTS.put(CLIENT_LEB, CLIENT_LEB_KEY);
	}
	
	
	public static String buildToken(String client,String key,String time){
		StringBuilder sb = new StringBuilder();
		sb.append(client);
		sb.append("_");
		sb.append(key);
		sb.append("_");
		sb.append(time);
		return md5(sb.toString());
	}
	
	
	public static boolean checkToken(String client,String key,String time,String token){
		return buildToken(client, key, time).equals(token);
	}
	
	public static String md5(String s) {
		byte[] hashPassword = null;
		try {
			hashPassword = Digests.md5(new ByteArrayInputStream(s.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String aToken = Encodes.encodeHex(hashPassword);
		return aToken;
	}
	
	

}
