package sso.Yxt;

import com.alibaba.fastjson.JSONObject;
import modal.Constant;
import modal.UserContext;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import util.HttpClientHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by HP on 2017/7/7.
 */

@Controller
@RequestMapping(value="/web")
public class YxtWebJump {
    @Autowired
    private Constant constant;
    private static final String ENC = "UTF-8";

    private String getaccesstokenurl = "http://do.yunzhijia.com/openauth2/api/appAuth2";

    private String getusercontexturl = "http://do.yunzhijia.com/openauth2/api/getcontext";
    @RequestMapping(value="/sso.do")
    public void webjump(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String ticket = request.getParameter("ticket");
        String accesstoken = getAccessToken();
        UserContext usercontext = getUserContext(ticket,accesstoken);
        String username=usercontext.getOpenid();
        String url=HttpPost(username,getSHA256Str(username)).getString("data");
        response.sendRedirect(url);
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

    private String getAccessToken() throws RuntimeException{
        String accesstoken = null;
        try {
            Map<String,String> head = new HashMap<String,String>();
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
    /**
     * shaHex??
     *
     * @param data
     * @return
     */
    private String shaHex(String... data) {
        Arrays.sort(data);
        String join = StringUtils.join(data);
        String sign = DigestUtils.shaHex(join);
        return sign;
    }

    public   JSONObject  HttpPost(String username,String password) throws IOException {
        String url="https://api.yunxuetang.cn/el/sso";
        Map<String,String> head = new HashMap<String,String>();
        head.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        head.put("Accept-Language","en-us,en;q=0.5");
        String userName=username;
        String apiKey="20c08c37-3a85-4f9a-8c9b-f30f05a5b198";
        String salt= randomdata();
        String apisecret="a8cb2b97-eab8-41d9-b165-2a4822ec22b9";
        String signature=getSHA256Str(apisecret+ salt);
        JSONObject json = new JSONObject();
        json.put("uname",userName);
        json.put("pwd",password);
        json.put("orgidorcode",apiKey);
        json.put("apiKey",apiKey);
        json.put("salt",salt);
        json.put("signature",signature);
        StringEntity s= new StringEntity(json.toString(), "utf-8");
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        StringEntity postingString = new StringEntity("");// json����
        post.setEntity(s);
        post.setHeader("Content-type", "application/json");
        post.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        post.addHeader("Accept-Language","en-us,en;q=0.5");
        HttpResponse response = httpClient.execute(post);
        String ss= response.getEntity().getContent().toString();
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                entity.getContent(),"utf-8"));
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            buffer.append(temp);
        }
        String data=buffer.toString();
        JSONObject ob=JSONObject.parseObject(data);
        return ob;
        //JSONObject  re = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(),charset)));
    }


    public static String getSHA256Str(String str){
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

    //随机数
    public String randomdata(){
        Random rm = new Random();

        double pross = (1 + rm.nextDouble()) * Math.pow(10,3);

        String fixLenthString = String.valueOf(pross);

        return fixLenthString.substring(1,3 + 1);
    }
}
