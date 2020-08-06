package sso.ITC;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import modal.Constant;
import modal.Parameter;
import modal.UserContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sso.Crm.CrmToken;
import util.HttpClientHelper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

@Controller
public class ITCApp {
    private String getusercontexturl = "http://do.yunzhijia.com/openauth2/api/getcontext";

    private String getaccesstokenurl = "http://do.yunzhijia.com/openauth2/api/appAuth2";
    private String getuserinfo = "http://yunzhijia.com/openapi/third/v1/opendata-control/data/getperson";
    @Autowired
    private Constant constant;
    private static final String ENC = "UTF-8";
    @RequestMapping(value="/appitc.do")
    public ModelAndView sso(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String ticket = request.getParameter("ticket");
//        ticket = "APPURLWITHTICKET6802a48d43b053c8703173348eb64fbb";
//        Map<String,Object> info = new HashMap<String,Object>();
//        try{
//            String accesstoken =getAccessToken();
//            UserContext usercontext = getUserContext(ticket,accesstoken);
//            info.put("usercontext",usercontext);
//            String username=usercontext.getOpenid();
//            String eid=usercontext.getEid();
//            String userInfo= getUserInfo(username, eid);
//            JSONArray ob= JSONArray.parseArray(userInfo);
//            String simpleInfo=ob.getString(0);
//            JSONObject job=JSONObject.parseObject(simpleInfo);
//            String jobno=job.getString("jobNo");
//
//            request.getSession().setAttribute("user", usercontext);
//            String token= CrmToken.getToken(jobno);
//            request.setAttribute("key",token );
            return new ModelAndView("itcopen");
//        }catch(RuntimeException e){
//            e.printStackTrace();
//            return error(ticket);
//        }
    }

    private String getUserInfo(String openid,String eid) throws RuntimeException{
        Map<String,String> body = new HashMap<String,String>();
        body.put("openId", openid);
        body.put("eid", eid);
        String companyname = null;
        try {
            Map<String,String>head = new HashMap<String,String>();
            head.put("Authorization", generateHead("500036938","9527",getuserinfo,"POST",convert(body)));
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
    private UserContext getUserContext(String ticket,String accesstoken) throws Exception {
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

    private ModelAndView error(String content){
        Map<String,String>errorinfo = new HashMap<String,String>();
        StringBuilder msg = new StringBuilder();
        msg.append(content);
        errorinfo.put("errmsg", msg.toString());
        return new ModelAndView("error",errorinfo);
    }
    private String getAccessToken() throws RuntimeException{
        String accesstoken = null;
        try {
            Map<String,String>head = new HashMap<String,String>();
            head.put("authorization", appAuth2Treaty(constant.getAppid(),constant.getAppsecret()));
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

    private String shaHex(String... data) {
        Arrays.sort(data);
        String join = StringUtils.join(data);
        String sign = DigestUtils.shaHex(join);
        return sign;
    }

    private List<Parameter> convert(Map<String,String>params){
        List<Parameter> list= new ArrayList<Parameter>();
        Iterator<String>iterator = params.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            String value = params.get(key);
            list.add(new Parameter(key,value));
        }
        return list;
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

    private String encodeBase64(byte[]input) throws Exception{
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod= clazz.getMethod("encode", byte[].class);
        mainMethod.setAccessible(true);
        Object retObj=mainMethod.invoke(null, new Object[]{input});
        return (String)retObj;
    }

    private void parseGetParameters(String url,List<Parameter>params)
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
}
