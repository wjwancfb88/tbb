package yzjCommon;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.body.MultipartBody;
import modal.Contacter;
import modal.Parameter;
import util.EncryptUtils;
import util.HttpClientHelper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.*;

/**
 * Created by kaiwang on 2017/10/24.
 */
public class YzjDataApi {
    //同步接口 这里暂时用于获取单个人员信息
    private static String singleurl="http://do.yunzhijia.com/openaccess/input/person/get";
    private static final String ENC = "UTF-8";
    private static String getuserinfo = "http://yunzhijia.com/openapi/third/v1/opendata-control/data/getperson";
    public String getRrsource(){
        return  this.getClass().getClass().getResource("/").getPath()+"config/534488.key";
    }
    public static String getPersonInfo(String openId,String eid,String resource) throws Exception {
        List<String> openids=new ArrayList();
        openids.add(openId);
        Map map=new HashMap();
        map.put("eid",eid);
        map.put("type",1);
        map.put("array",openids);
        String jss = JSON.toJSONString(map);
       // String jss="{'openId':'"+openId+"','type':"+0+",'array':"+eid+"}";
        JsonNode jsonData = new JsonNode(jss); // json data without encrypt
        //YzjDataApi api=new YzjDataApi();
        String keyFile =resource;
       // String filesource=System.getProperty("user.dir");
        byte[] keyByte = EncryptUtils.getBytesFromFile(keyFile);
        Key key = EncryptUtils.restorePrivateKey(keyByte);
        String s= EncryptUtils.encryptWithEncodeBase64UTF8(
                jsonData.toString(), key);
        MultipartBody body= Unirest
                .post(singleurl)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("eid", "534488")
                .field("nonce", UUID.randomUUID().toString())
                .field("data",
                        EncryptUtils.encryptWithEncodeBase64UTF8(
                                jsonData.toString(), key));
        HttpResponse<JsonNode> a=body.asJson();
        String data= String.valueOf(a.getBody());
        JsonNode js=a.getBody();
        org.json.JSONObject jon=js.getObject();
        String info=jon.get("data").toString();
        List<Contacter> list= JSONArray.parseArray(info, Contacter.class);
        return list.get(0).getJobNo();


    }


    private static String getUserInfo(String openid,String eid) throws RuntimeException{
        Map<String,String> body = new HashMap<String,String>();
        body.put("openId", openid);
        body.put("eid", eid);
        String companyname = null;
        try {
            Map<String,String>head = new HashMap<String,String>();
            head.put("Authorization", generateHead("500037233","9528",getuserinfo,"POST",convert(body)));
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

    private static String encodeBase64(byte[]input) throws Exception{
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

    private static List<Parameter>convert(Map<String,String>params){
        List<Parameter> list= new ArrayList<Parameter>();
        Iterator<String>iterator = params.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            String value = params.get(key);
            list.add(new Parameter(key,value));
        }
        return list;
    }
}
