package com.dhwooden.ep.yzj;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.dhwooden.ep.modal.Parameter;
import com.dhwooden.ep.util.HttpClientHelper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by kaiwang on 2017/12/29.
 */
public class MessageSend {
    private static String changetodostatus = "http://yunzhijia.com/openapi/third/v1/newtodo/open/action.json";
    private static String todo = "https://www.yunzhijia.com/openapi/third/v1/newtodo/open/generatetodo.json";
    private static final String ENC = "UTF-8";
    private static String url = "http://do.yunzhijia.com/pubacc/pubsend";


    public static void pubsendlink(String[] sourceId,List<String> openid,String type,String phone){

        String sourceIds="";
        for(int i=0;i<sourceId.length;i++){
            sourceIds=sourceIds+"_"+sourceId[i];
        }

        JSONObject from = new JSONObject();
        String no = "534488";
        String pubid = "XT-524ec51f-187e-4db6-9b49-f5b33d712d9d";
        String time = String.valueOf(System.currentTimeMillis());
        String nonce = UUID.randomUUID().toString();
        from.put("no", no);
        from.put("pub",pubid);
        from.put("time", time);
        from.put("nonce", nonce);
        String pubtoken=shaHex(no,pubid,"3547c62f5d0f3d355a9013f34e80f20e",nonce,time);
        from.put("pubtoken", pubtoken);
        JSONArray to = new JSONArray();
        JSONObject user = new JSONObject();
        //UserContext usercontext = (UserContext)request.getSession().getAttribute("user");
        // user.put("no",usercontext.getEid());
        JSONArray openids = new JSONArray();

        for(String op:openid){
            openids.add(op);
        }
        user.put("user", openids);
        user.put("no","534488");
        // to.add(user);
        user.put("user", openids);
        to.add(user);
        JSONObject msg = new JSONObject();
        String sourceid = UUID.randomUUID().toString();
        String message= "测试";
        if(type.equals("0")){
             message="测试";

        }
        msg.put("text","new message");
        msg.put("sourceid", sourceid);
        msg.put("appid", "4DHJzOmDMVmAko7tkbRE");
        //msg.put("url", "http://61.153.180.246:8008//admin//danger//hqdetail?ids="+sourceIds+"&phone="+phone);
       // msg.put("url", "http://app.tubaoyz.com//admin//danger//hqdetail?ids="+sourceIds+"&phone="+phone);
         msg.put("url", "http://61.153.182.58:3395//admin//danger//hqdetail?ids="+sourceIds+"&phone="+phone);
         msg.put("todo", 1);
        JSONObject content = new JSONObject();
        content.put("from", from);
        content.put("to", to);
        content.put("msg", msg);
        content.put("type", 5);
        String result = HttpClientHelper.post(url, null, content.toJSONString());
    }






    public void tt(){
        JSONObject params = new JSONObject();
        params.put("sourceId", 123456);
        params.put("appId", 10339);
        //JSONArray openids = new JSONArray();
        // params.put("openids", openids);
        JSONObject actiontype = new JSONObject();
        actiontype.put("url", "www.baidu.com");
        actiontype.put("title", "");
        actiontype.put("headImg", "www.baidu.com");
        List list=new ArrayList();
        Map map=new HashMap();
        map.put("openId","58f8138be4b070e84236aba1");
        Map mapp=new HashMap();
        mapp.put("DO",0);
        map.put("status", mapp);
        list.add(map);
        actiontype.put("params", list);
        params.put("actiontype", actiontype);
        try {
            Map<String,String> head = new HashMap<String,String>();
            head.put("Authorization", generateHead("10339","123456",todo,"POST",null));
            head.put("Content-Type", "application/json");
            System.out.println(params.toJSONString());
            JSONObject userinfojson = JSONObject.parseObject(HttpClientHelper.post(todo, head, params.toJSONString()));
            if(!userinfojson.getBooleanValue("success")){
                System.out.println(userinfojson.toJSONString());
                throw new RuntimeException(userinfojson.getJSONObject("data").getString("error"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    private String generateHead(String appid, String appSecret, String url, String method, List<Parameter> body) throws UnsupportedEncodingException {
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

    private String generateSign(String appid, long timestamp, String nonce, String appSecret, String url, String method,
                                List<Parameter> body) throws Exception {
        StringBuilder basestring = new StringBuilder();
        basestring = basestring.append(method).append("&").append(URLEncoder.encode(url, ENC)).append("&");
        List<Parameter> params = new ArrayList<Parameter>();
        if(body != null){
            params.addAll(body);
        }
        params.add(new Parameter("oauth_consumer_key", appid));
        params.add(new Parameter("oauth_signature_method", "HMAC-SHA1"));
        params.add(new Parameter("oauth_timestamp", timestamp + ""));
        params.add(new Parameter("oauth_nonce",nonce));
        params.add(new Parameter("oauth_version", "1.0"));
        parseGetParameters(url,params);
        Collections.sort(params, new Comparator<Parameter>() {


            public int compare(Parameter o1, Parameter o2) {
                int value = o1.getKey().compareTo(o2.getKey());
                if (value == 0) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
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

    private void parseGetParameters(String url,List<Parameter> params)
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

    private String encodeBase64(byte[]input) throws Exception {
        Class clazz= Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod= clazz.getMethod("encode", byte[].class);
        mainMethod.setAccessible(true);
        Object retObj=mainMethod.invoke(null, new Object[]{input});
        return (String)retObj;
    }

    /**
     * shaHex算法
     *
     * @param data
     * @return
     */
    private static String shaHex(String... data) {
        Arrays.sort(data);
        String join = StringUtils.join(data);
        String sign = DigestUtils.shaHex(join);
        return sign;
    }

}
