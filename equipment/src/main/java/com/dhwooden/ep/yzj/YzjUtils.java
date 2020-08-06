package com.dhwooden.ep.yzj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhwooden.ep.modal.Contacter;
import com.dhwooden.ep.modal.Parameter;
import com.dhwooden.ep.modal.User;
import com.dhwooden.ep.modal.UserContext;
import com.dhwooden.ep.sms.SmsSender;
import com.dhwooden.ep.util.EncryptUtils;
import com.dhwooden.ep.util.HttpClientHelper;
import com.dhwooden.ep.util.SendPost;
import com.dhwooden.ep.yzj.mapper.JsonMapper;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.body.MultipartBody;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ResourceUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.*;

public class YzjUtils {
	
	private static final String GETCONTEXT_URL = "http://do.yunzhijia.com/openauth2/api/getcontext";
	private static final String AUTH2_URL = "http://do.yunzhijia.com/openauth2/api/appAuth2";
	private static String getuserinfo = "http://yunzhijia.com/openapi/third/v1/opendata-control/data/getperson";
	private static String getusercontexturl = "http://do.yunzhijia.com/openauth2/api/getcontext";
	private String getaccesstokenurl = "http://do.yunzhijia.com/openauth2/api/appAuth2";
	/**发送待办消息*/
	private static final String  TODONEWS_URL = "https://www.yunzhijia.com/gateway/newtodo/open/generatetodo.json?accessToken=";
	/**待办处理*/
	private static final String  TODONEWS_HANDLE_URL = "https://www.yunzhijia.com/gateway/newtodo/open/action.json";
	/**待办消息确认*/
	private static final String  TODONEWS_CONFIRM_URL = "https://www.yunzhijia.com/gateway/newtodo/open/checkcreatetodo.json";

	private static final String ACCESSTOKEN_URL = "https://www.yunzhijia.com/gateway/oauth2/token/getAccessToken";
	//同步接口 这里暂时用于获取单个人员信息
	private static String singleurl = "http://do.yunzhijia.com/openaccess/input/person/get";

	private static String todo = "https://www.yunzhijia.com/openapi/third/v1/newtodo/open/generatetodo.json";

	private static final String ENC = "UTF-8";
	//private static final String GETPERSON_URL = "https://www.yunzhijia.com/openapi/third/v1/opendata-control/data/getperson";
	
	public static YzjResult<AccessTokenData> getAccessToken(String id,String secret){
		String authorization = null;
//		try {
//			authorization = AppAuth2.appAuth2Treaty(id, secret);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//			throw new YzjException(e.getMessage());
//		}
		Map<String, String> head = new HashMap<String, String>();
		try {
			 authorization= AppAuth2.appAuth2Treaty(id, secret);
			head.put("authorization", authorization);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//JSONObject accesstokenjson = JSONObject.parseObject(HttpClientHelper.post(AUTH2_URL, head));
		//JSONObject js=accesstokenjson.getJSONObject("data");
		//String accesstoken = accesstokenjson.getJSONObject("data").getString("access_token");

		HttpGet httpGet = new HttpGet(AUTH2_URL);
		httpGet.addHeader( "authorization", authorization);
		String json = getResponseString(httpGet);
		return (YzjResult<AccessTokenData>)toResult(json,AccessTokenData.class);
	}
	
	
	public static UserContextData getUserContextData(String access_token,String ticket){
		HttpGet httpGet = new HttpGet(GETCONTEXT_URL+"?access_token="+access_token+"&ticket="+ticket);
		String json = getResponseString(httpGet);
		JsonMapper jm = JsonMapper.nonDefaultMapper();
		return jm.fromJson(json, UserContextData.class);
	}
	
	
	private static YzjResult toResult(String json,Class<?> dataClass){
		JsonMapper jm = JsonMapper.nonDefaultMapper();
    	YzjResult result = jm.fromJson(json, 
    			jm.constructParametricType(YzjResult.class, dataClass));
    	return result;
	}
	
	
	private static String getResponseString(HttpGet httpGet){
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpResponse responce = null;
		try {
			responce = httpClient.execute(httpGet);
		} catch (Exception e) {
			e.printStackTrace();
			throw new YzjException(e.getMessage());
		} 
		
		int resStatu = responce.getStatusLine().getStatusCode();
		
		//200正常  其他就不对  
		if (resStatu== HttpStatus.SC_OK) {
        	HttpEntity entity = responce.getEntity();
        	String s = null;
			try {
				s = EntityUtils.toString(entity, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
				throw new YzjException(e.getMessage());
			} 
			return s;
        }else{
        	throw new YzjException("HTTP ERROR:"+resStatu);
        }
	}

	public static void main(String[] args) throws Exception{

		System.out.println(ACCESSTOKEN_URL);
		com.mashape.unirest.http.HttpResponse<JsonNode> json = Unirest.post(ACCESSTOKEN_URL).header(
				HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded")
				.field("appId", "500065577")
				.field("timestamp", System.currentTimeMillis())
				.field("secret", "4DHJzOmDMVmAko7tkbRE")
				.field("scope", "app").asJson();
		System.out.println(json);
//		com.mashape.unirest.http.HttpResponse<JsonNode> a=body.asJson();
//
//		JsonNode js=a.getBody();
//		org.json.JSONObject jon=js.getObject();



//		System.out.println(YzjApps.OEMAPP.getAccessToken());
		
		//String c = "{openId: '57a00cfae4b0280b6e81d4be', eid: '534488'}";
		//String s = HttpUtil.post(GETPERSON_URL, c);
//		System.out.print(s);

	}



	/*
	public static void  getPerson() throws UnsupportedEncodingException{
		String json = "{openId: '57a00cfae4b0280b6e81d4be',eid: '534488'}";
		StringEntity postEntity = new StringEntity(json,"UTF-8");
		postEntity.setContentType("application/json");


		HttpPost httpPost = new HttpPost(GETPERSON_URL);
		httpPost.setEntity(postEntity);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse responce = null;
		try {
			responce = httpClient.execute(httpPost);
		} catch (Exception e) {
			e.printStackTrace();
			throw new YzjException(e.getMessage());
		}
		int resStatu = responce.getStatusLine().getStatusCode();

		//200正常  其他就不对
		if (resStatu==HttpStatus.SC_OK) {
        	HttpEntity entity = responce.getEntity();
        	String s = null;
			try {
				s = EntityUtils.toString(entity,"utf-8");
				System.out.println(s);
			} catch (Exception e) {
				e.printStackTrace();
				throw new YzjException(e.getMessage());
			}

        }else{
        	throw new YzjException("HTTP ERROR:"+resStatu);
        }
	}
	*/

	private static String purl = "http://do.yunzhijia.com/openaccess/input/person/getall";
	public List<User> queryPerson(int start) throws Exception {
		String jss="{'begin':"+start+"}";
		JsonNode jsonData = new JsonNode(jss);// json data without encrypt
		String path = ResourceUtils.getURL("classpath:").getPath()+"/static/yzj/534488.key";
		byte[] keyByte = EncryptUtils.getBytesFromFile(path);
		Key key = EncryptUtils.restorePrivateKey(keyByte);
		String s= EncryptUtils.encryptWithEncodeBase64UTF8(
				jsonData.toString(), key);
		MultipartBody body= Unirest
				.post(purl)
				.header("Content-Type", "application/x-www-form-urlencoded")
				.field("eid", "534488")
				.field("nonce", UUID.randomUUID().toString())
				.field("data",
						EncryptUtils.encryptWithEncodeBase64UTF8(
								jsonData.toString(), key));
		com.mashape.unirest.http.HttpResponse<JsonNode> a=body.asJson();

		JsonNode js=a.getBody();
		org.json.JSONObject jon=js.getObject();
		String info=jon.get("data").toString();
		List  array= JSONObject.parseArray(info);
		List<User>  arra= JSONObject.parseArray(info, User.class);
		return arra;
	}

	public  static String getUserInfo(String openid,String eid) throws RuntimeException{
		Map<String,String> body = new HashMap<String,String>();
		body.put("openId", openid);
		body.put("eid", eid);
		String companyname = null;
		try {
			Map<String,String>head = new HashMap<String,String>();
			head.put("Authorization", generateHead("500065577","4DHJzOmDMVmAko7tkbRE",getuserinfo,"POST",convert(body)));
			JSONObject userinfojson = JSONObject.parseObject(HttpClientHelper.post(getuserinfo, head, body));
			String userInfo=userinfojson.getString("data");
			return userInfo;
//			if(userinfojson.getBooleanValue("success")){
//				companyname = userinfojson.getJSONObject("data").getString("name");
//				return companyname;
//			}else{
//				throw new RuntimeException(userinfojson.getJSONObject("data").getString("error"));
//			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	} private static List<Parameter>convert(Map<String,String>params){
		List<Parameter> list= new ArrayList<Parameter>();
		Iterator<String>iterator = params.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			String value = params.get(key);
			list.add(new Parameter(key,value));
		}
		return list;
	}

	private static String generateHead(String appid, String appSecret, String url, String method, List<Parameter> body) throws UnsupportedEncodingException {
		String authorization = "OAuth oauth_consumer_key=\"%s\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"%s\",oauth_nonce=\"%s\",oauth_version=\"1.0\",oauth_signature=\"%s\"";
		appid = URLEncoder.encode(appid, ENC);
		long timestamp = System.currentTimeMillis()/1000;
		String nonce = URLEncoder.encode(UUID.randomUUID().toString(), ENC);
		String sign;
		try {
			sign = generateSign(appid, timestamp, nonce, appSecret,url,method,body);
		} catch (Exception e) {
			return null;
		}
		sign = URLEncoder.encode(sign, ENC);
		authorization = String.format(authorization, appid, timestamp + "", nonce, sign);
		return authorization;
	}

	private static String generateSign(String appid, long timestamp, String nonce, String appSecret, String url, String method,
								List<Parameter> body) throws Exception {
		StringBuilder basestring = new StringBuilder();
		basestring = basestring.append(method).append("&").append(URLEncoder.encode(url,ENC)).append("&");
		List<Parameter>params = new ArrayList<Parameter>();
		if(body != null){
			params.addAll(body);
		}
		params.add(new Parameter("oauth_consumer_key", appid));
		params.add(new Parameter("oauth_signature_method", "HMAC-SHA1"));
		params.add(new Parameter("oauth_timestamp", timestamp + ""));
		params.add(new Parameter("oauth_nonce",nonce));
		params.add(new Parameter("oauth_version", "1.0"));
		parseGetParameters(url,params);
		Collections.sort(params, new Comparator<Parameter>(){


			public int compare(Parameter o1, Parameter o2) {
				int value =  o1.getKey().compareTo(o2.getKey());
				if(value == 0){
					return o1.getValue().compareTo(o2.getValue());
				}else{
					return value;
				}
			}

		});
		int count = 1;
		for(Parameter temp:params){
			basestring.append(temp.getKey()).append("%3D").append(temp.getValue());
			if(count != params.size()){
				basestring.append("%26");
			}
			count++;
		}
		byte[] byteHMAC = null;
		Mac mac = Mac.getInstance("HmacSHA1");
		SecretKeySpec spec = new SecretKeySpec((appSecret + "&").getBytes("UTF-8"),
				"HmacSHA1");
		mac.init(spec);
		byteHMAC = mac.doFinal(basestring.toString().getBytes("UTF-8"));
		return encodeBase64(byteHMAC);
	}

	private static  String encodeBase64(byte[]input) throws Exception{
		Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method mainMethod= clazz.getMethod("encode", byte[].class);
		mainMethod.setAccessible(true);
		Object retObj=mainMethod.invoke(null, new Object[]{input});
		return (String)retObj;
	}

	private static void parseGetParameters(String url,List<Parameter>params)
	{
		int queryStart = url.indexOf("?");
		if (-1 != queryStart)
		{
			String[] queryStrs = url.substring(queryStart + 1).split("&");
			try
			{
				for (String query : queryStrs)
				{
					String[] split = query.split("=");
					if (split.length == 2){
						params.add(new Parameter(URLDecoder
								.decode(split[0], "UTF-8"), URLDecoder.decode(
								split[1], "UTF-8")));
					}else{
						params.add(new Parameter(URLDecoder
								.decode(split[0], "UTF-8"), ""));
					}
				}
			} catch (UnsupportedEncodingException localUnsupportedEncodingException)
			{
			}
		}
	}

	public  static UserContext getUserContext(String ticket,String accesstoken) throws Exception {
		try {
			Map<String,String>body = new HashMap<String,String>();
			body.put("ticket", ticket);
			body.put("access_token", accesstoken);
			JSONObject usercontextjson = JSONObject.parseObject(HttpClientHelper.post(getusercontexturl, null, body));
			return new UserContext(usercontextjson);
		} catch (Exception e) {
			throw e;
		}
	}


	private String getAccessToken() throws RuntimeException{
		String accesstoken = null;
		try {
			Map<String,String>head = new HashMap<String,String>();
			head.put("authorization", appAuth2Treaty("123","456"));
			JSONObject accesstokenjson = JSONObject.parseObject(HttpClientHelper.post(getaccesstokenurl, head));
			if(accesstokenjson.getBooleanValue("success")){
				accesstoken = accesstokenjson.getJSONObject("data").getString("access_token");
				return accesstoken;
			}else{
				throw new RuntimeException(accesstokenjson.getJSONObject("data").getString("error"));
			}
		} catch (Exception e) {
			throw new RuntimeException("system error");
		}
	}


	private String appAuth2Treaty(String appid, String appSecret) throws UnsupportedEncodingException {
		String authorization = "OpenAuth2 version=\"%s\", appid=\"%s\", timestamp=%d, nonce=\"%s\", sign=\"%s\"";
		String version = "1.1";
		appid = URLEncoder.encode(appid, ENC);
		long timestamp = System.currentTimeMillis();
		String nonce = URLEncoder.encode(UUID.randomUUID().toString(), ENC);
		String sign = shaHex(version, appid, timestamp + "", nonce, appSecret);
		sign = URLEncoder.encode(sign, ENC);
		authorization = String.format(authorization, version, appid, timestamp, nonce, sign);
		return authorization;
	}
	/**
	 * shaHex�㷨
	 *
	 * @param data
	 * @return
	 *
	 * select
	 */
	private String shaHex(String... data) {
		Arrays.sort(data);
		String join = StringUtils.join(data);
		String sign = DigestUtils.shaHex(join);
		return sign;
	}

	//根据电话获取用户信息
	public static List<Contacter> getUserByPhone(List<String> phones, String eid,String resource) throws Exception {
		Map map = new HashMap();
		map.put("eid", eid);
		map.put("type", 0);
		map.put("array", phones);
		String jss = JSON.toJSONString(map);
		// String jss="{'openId':'"+openId+"','type':"+0+",'array':"+eid+"}";
		JsonNode jsonData = new JsonNode(jss); // json data without encrypt
		//YzjDataApi api=new YzjDataApi();
		String keyFile = resource;
		// String filesource=System.getProperty("user.dir");
		byte[] keyByte = EncryptUtils.getBytesFromFile(keyFile);
		Key key = EncryptUtils.restorePrivateKey(keyByte);
		String s = EncryptUtils.encryptWithEncodeBase64UTF8(
				jsonData.toString(), key);
		MultipartBody body = Unirest
				.post(singleurl)
				.header("Content-Type", "application/x-www-form-urlencoded")
				.field("eid", "534488")
				.field("nonce", UUID.randomUUID().toString())
				.field("data",
						EncryptUtils.encryptWithEncodeBase64UTF8(
								jsonData.toString(), key));
		com.mashape.unirest.http.HttpResponse<JsonNode> a = body.asJson();
		String data = String.valueOf(a.getBody());
		JsonNode js = a.getBody();
		org.json.JSONObject jon = js.getObject();
		String info = jon.get("data").toString();
		List<Contacter> list = JSONArray.parseArray(info, Contacter.class);
		return list;

	}


}
