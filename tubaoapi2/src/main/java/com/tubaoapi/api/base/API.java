package com.tubaoapi.api.base;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.modules.security.utils.Digests;
import com.tubaoapi.modules.utils.Encodes;

public class API {
	public static final String DEFAULT_MAX_RESULTS = "50";
	public static final int MAX_RESULTS = 200;
	
	/**
	 * 新格尔门业
	 */
	public static final String CLIENT_THINKER = "thinker";
	public static final String CLIENT_THINKER_KEY = "iX7dVy";
	
	/**
	 * CRM
	 */
	public static final String CLIENT_CRM = "crm";
	public static final String CLIENT_CRM_KEY = "pXt7j";

	/**
	 * Eas
	 */
	public static final String CLIENT_EAS = "eas";
	public static final String CLIENT_EAS_KEY = "eXj5d";
	/**
	 * LeBang
	 */
	public static final String CLIENT_LEB = "leb";
	public static final String CLIENT_LEB_KEY = "t45re";

	/**
	 * fcs
	 */
	public static final String CLIENT_MES= "mes";
	public static final String CLIENT_MES_KEY = "w9rfw";

	public static final String CLIENT_DING= "ding";
	public static final String CLIENT_DING_KEY = "x7rn3";


	
	public static final Map<String,String> CLIENTS = new HashMap<String, String>();
	
	static {
		CLIENTS.put(CLIENT_THINKER, CLIENT_THINKER_KEY);
		CLIENTS.put(CLIENT_CRM, CLIENT_CRM_KEY);
		CLIENTS.put(CLIENT_EAS, CLIENT_EAS_KEY);
		CLIENTS.put(CLIENT_LEB, CLIENT_LEB_KEY);
		CLIENTS.put(CLIENT_MES, CLIENT_MES_KEY);
		CLIENTS.put(CLIENT_DING, CLIENT_DING_KEY);
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
	
	
	public static void setIdOrIdsToBaseSO(BaseSO<String> so,String id){
		if(StringUtils.isNotBlank(id)){
			if(id.indexOf(",")!=-1){
				so.setIds(Arrays.asList(id.split(",")));
			}else{
				so.setId(id);
			}
		}
	}
}
