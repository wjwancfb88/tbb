package com.dhwooden.ep.controller.wx;


import com.dhwooden.ep.util.HttpClientHelper;
import com.dhwooden.ep.util.PostJson;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2018-04-18
 */
@Controller
@RequestMapping("/wx/dl")
public class WXController {
    private final Logger logger= LoggerFactory.getLogger(WXController.class);

    String URL="https://open.weixin.qq.com/connect/oauth2/authorize";
    private String token = "flag";

    @RequestMapping("/syn")
    @ResponseBody
    public void syn() throws Exception {
//        String url=URL+"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx119d493b885c9198&redirect_uri=http://app.tubaoyz.com/admin/entry/loading&response_type=code&scope=snsapi_base&state=s";
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdb0d3982019dedcd&redirect_uri=http://61.153.182.58:3401/admin/entry/loading?flag=2&response_type=code&scope=snsapi_base&state=s#wechat_redirect";
//        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdb0d3982019dedcd&redirect_uri=http://61.153.182.58:3401/admin/entry/loading&response_type=code&scope=snsapi_base&state=s";
        JSONObject jsonObject = HttpClientHelper.httpGet(url);
        System.out.println(jsonObject);
    }
    @RequestMapping("/mypage")
    @ResponseBody
    public String mypage(String code) throws Exception {
        String url="https://api.weixin.qq.com/sns/oauth2/access_token";
//        JSONObject json=HttpClientHelper.httpGet(url);
        String str = "appid=wx119d493b885c9198&secret=d1ac107caf422a22ef07e1467b63583e&code=\""+code+"\"&grant_type=authorization_code";
        byte[] bytes = PostJson.post2(url, str, "UTF-8");
        com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSONObject.parseObject(new String(bytes));
        logger.info(json.toString());
        //JSONObject ss = JSONObject.fromObject(json);
//        String openid = json.getString("openid");
        // String url=URL+"?appid=wx3e4a89adbc62b1e9&redirect_uri=http://www.xxx.com/wechat/event/myPreReg.html&response_type=code&scope=snsapi_base&state=s";
       // HttpClientHelper.httpGet(url);
        return "code";
    }

    @RequestMapping(value = "/wx",method = RequestMethod.GET)
    @ResponseBody
    public String wxInterface(HttpServletRequest request, HttpServletResponse response)throws Exception{
        logger.info("开始验证微信服务器信息");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        logger.info("signature : " + signature + ",timestamp : " + timestamp + ",nonce : " + nonce + ",echostr : " + echostr);
        if (checkSignature(signature,timestamp,nonce)){
            logger.info("微信服务器信息验证成功!");
            return echostr;
        }else{
            return null;
        }
    }

    public boolean checkSignature(String signature,String timestamp,String nonce){
        String[] arr = new String[]{token,timestamp,nonce};
        Arrays.sort(arr);
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i < arr.length; i++){
            sb.append(arr[i]);
        }
        String tempStr = null;
        try{
            tempStr = getSha1(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("微信签名加密认证!");
        sb = null;
        return tempStr != null ? tempStr.equals(signature) : false;
    }

    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}

