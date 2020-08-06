package sso.BI;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.client.ClientProtocolException;
import util.HttpClientHelper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 2017/6/27.
 */
public class BIToken {


    public static  String getToken(String username)throws ClientProtocolException, IOException  {
        String url="http://61.153.182.58:3396/DAP/web/webloginByTrustIp.do?userid="+username;
        //Map<String,String> body = new HashMap<String,String>();
        net.sf.json.JSONObject json= HttpClientHelper.httpGet(url);
         String token=json.getString("user_token");
        return json.getString("user_token");
        //JSONObject  re = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(),charset)));
    }

    public static  String getToken2(String username)throws Exception  {
        String url="http://61.153.182.58:3402/DAP/web/webloginByTrustIp.do";
        Map<String,String> body = new HashMap<String,String>();
        body.put("userid",username);
        String result = HttpClientHelper.post(url, null,body);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String user_token = jsonObject.getString("user_token");
//        System.out.println("http://61.153.182.58:3402/DAP/open_home.do?Semf-SsoToken="+user_token);
        return user_token;
    }

    public static void main(String[] args)throws Exception {
        getToken2("020260");
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
