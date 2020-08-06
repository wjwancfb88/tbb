package yzjCommon;

import com.alibaba.fastjson.JSONObject;
import modal.Constant;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import util.HttpClientHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 获取云之家AccessToken
 * Created by kaiwang on 2017/11/14.
 */
public class YzjDataAccessToken {
    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    private static final String ENC = "UTF-8";
    //
    private static String getaccesstokenurl = "http://do.yunzhijia.com/openauth2/api/appAuth2";
    //
    private String getusercontexturl = "http://do.yunzhijia.com/openauth2/api/getcontext";
    private static Constant constant;
    public static  String getAccessToken() throws RuntimeException{
        String accesstoken = null;
        try {
            Map<String,String> head = new HashMap<String,String>();
            head.put("authorization", appAuth2Treaty(constant.getAppid(),constant.getAppsecret()));
            JSONObject accesstokenjson = JSONObject.parseObject(HttpClientHelper.post(getaccesstokenurl, head));
            if(accesstokenjson.getBooleanValue("success")){
                accesstoken = accesstokenjson.getJSONObject("data").getString("access_token");
                logger.info("获取accesstoken成功");
                return accesstoken;
            }else{
                logger.error("获取accesstoken失败");
                throw new RuntimeException(accesstokenjson.getJSONObject("data").getString("error"));
            }
        } catch (Exception e) {
            throw new RuntimeException("system error");
        }
    }
    private  static String appAuth2Treaty(String appid, String appSecret) throws UnsupportedEncodingException {
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
    private  static String shaHex(String... data) {
        Arrays.sort(data);
        String join = StringUtils.join(data);
        String sign = DigestUtils.shaHex(join);
        return sign;
    }
}
