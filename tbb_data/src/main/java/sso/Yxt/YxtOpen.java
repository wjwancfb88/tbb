package sso.Yxt;

import com.alibaba.fastjson.JSONObject;
import modal.Constant;
import modal.UserContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import util.HttpClientHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by HP on 2017/6/27.
 */
@Controller
public class YxtOpen {

    @Autowired
    private Constant constant;
    private static final String ENC = "UTF-8";
    //
    private String getaccesstokenurl = "http://do.yunzhijia.com/openauth2/api/appAuth2";
    //
    private String getusercontexturl = "http://do.yunzhijia.com/openauth2/api/getcontext";

    @RequestMapping(value="/sso.do")
    public ModelAndView sso(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String ticket = request.getParameter("ticket");
        Map<String,Object> info = new HashMap<String,Object>();
        try{
            String accesstoken = getAccessToken();
            UserContext usercontext = getUserContext(ticket,accesstoken);
            info.put("usercontext",usercontext);
            String username=usercontext.getOpenid();
            request.getSession().setAttribute("user", usercontext);
            request.setAttribute("key", YxtToken.getToken(username));
            return new ModelAndView("open",info);
        }catch(RuntimeException e){
            e.printStackTrace();
            return error(ticket);
        }
    }

    private ModelAndView error(String content){
        Map<String,String>errorinfo = new HashMap<String,String>();
        StringBuilder msg = new StringBuilder();
        msg.append(content);
        errorinfo.put("errmsg", msg.toString());
        return new ModelAndView("error",errorinfo);
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

}
