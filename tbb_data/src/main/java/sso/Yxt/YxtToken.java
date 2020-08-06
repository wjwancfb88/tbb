package sso.Yxt;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by HP on 2017/6/27.
 */
public class YxtToken {


    public static  String getToken(String username)throws ClientProtocolException, IOException  {
        String url="http://api.qida.yunxuetang.cn/v1/users/thirdtokens";
        //Map<String,String> body = new HashMap<String,String>();
        String userName=username;
        String apiKey="20c08c37-3a85-4f9a-8c9b-f30f05a5b198";
        String salt= "503f9cdb1856284b88c5d129c7ba3ef419";
        String apisecret="a8cb2b97-eab8-41d9-b165-2a4822ec22b9";
        String signature=getSHA256Str(apisecret + apiKey + salt + userName);
        JSONObject json = new JSONObject();
        json.put("userName",userName);
        json.put("apiKey",apiKey);
        json.put("salt",salt);
        json.put("signature",signature);
        StringEntity s= new StringEntity(json.toString(),"UTF-8");
        s.setContentType("text/json");
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
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
        String token=ob.getString("token");
        return token;
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
}
