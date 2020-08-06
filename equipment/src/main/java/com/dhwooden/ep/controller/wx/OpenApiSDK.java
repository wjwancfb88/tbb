/*
 Copyright (c) 2015 WiseCRM.com. All rights reserved.
 WiseCRM.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dhwooden.ep.controller.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPInputStream;

import com.alibaba.fastjson.JSONArray;
import com.dhwooden.ep.modal.DhOrder;
import com.dhwooden.ep.service.DhOrderService;
import com.dhwooden.ep.service.impl.DhOrderServiceImpl;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Zhao Fangfang
 * @since 2016-3-6
 * @version $Id$
 */
@Controller
@RequestMapping("/openApi")
public class OpenApiSDK {

    private static final Log LOG = LogFactory.getLog(OpenApiSDK.class);

    // 请修改为相应参数
//    public static final String API_URL = "https://www.365.wisecrm.com/gw/";
    public static final String API_URL = "http://crm.tubaobao.com/gw/";
    public static final String APP_ID = "000-C201F846-E27C-4B68-8F03-FF70DEBEF534";
    public static final String APP_KEY = "n3N1R1zJ9slCS3mbDZacBdzm3hNItpu50aEaQ99XfGs";

    public static final String SIGN_TYPE = "MD5";
    public static final String ENCODING = "utf-8";

    private static HttpClient httpClient = null;

    @Autowired
    public DhOrderService dhOrderService;
    /**
     * 发送 HTTP/GET API 请求
     *
     * @param apiUrl
     * @return
     */
    public static JSONObject apiGet(String apiUrl) {
        HttpMethod method = new GetMethod(apiUrl);
        //System.out.println("url: "+apiUrl);
        try {
            int status = getHttpClient().executeMethod(method);
            if (status != 200) {
                LOG.error("HTTP/GET 返回错误的状态码: " + status + ", URL: " + apiUrl);
                return null;
            }

            String result = readResponse(method);
            if (result == null) {
                LOG.error("HTTP/GET 返回空数据. URL: " + apiUrl);
                return null;
            }
            return JSON.parseObject(result);
        } catch (Exception e) {
            LOG.error("HTTP/GET 请求出现错误", e);
            return null;
        }
    }

    /**
     * 发送 HTTP/POST API 请求
     *
     * @param apiUrl
     * @param postdata
     * @return
     */
    public static JSONObject apiPost(String apiUrl, String postdata) {
        PostMethod method = new PostMethod(apiUrl);
        try {
            if (postdata != null) {
                method.setRequestEntity(new StringRequestEntity(postdata, "application/json", ENCODING));
            }

            int status = getHttpClient().executeMethod(method);
            if (status != 200) {
                LOG.error("HTTP/POST 返回错误的状态码: " + status + ", 请求 URL: " + apiUrl);
                return null;
            }

            String result = readResponse(method);
            if (result == null) {
                LOG.error("HTTP/POST 返回了空数据. 请求 URL: " + apiUrl);
                return null;
            }
            return JSON.parseObject(result);
        } catch (Exception e) {
            LOG.error("HTTP/POST 请求出现错误", e);
            return null;
        }
    }

    /**
     * 生成带签名的 API 请求 URL
     *
     * @param bizParams
     * @return
     */
    public static String signUrl(String apiName, Map<String, String> bizParams) {
        return signUrl(API_URL, apiName, bizParams);
    }

    /**
     * 生成带签名的 API 请求 URL
     *
     * @param apiUrl
     * @param apiName
     * @param bizParams
     * @return
     */
    public static String signUrl(String apiUrl, String apiName, Map<String, String> bizParams) {
        // 添加签名参数
        bizParams.put("appid", APP_ID);
        bizParams.put("sign_type", SIGN_TYPE);
        bizParams.put("timestamp", System.currentTimeMillis() + "");

        String sign = sign(bizParams);
        bizParams.put("sign", sign);

        apiUrl = apiUrl + apiName + "?";
        for (Map.Entry<String, String> e : bizParams.entrySet()) {
            apiUrl += e.getKey() + "=" + urlEncode(e.getValue()) + "&";
        }
        apiUrl = apiUrl.substring(0, apiUrl.length() - 1);  // remove &
        return apiUrl;
    }

    /**
     * 生成签名字符串
     *
     * @param params
     * @return
     */
    public static String sign(Map<String, String> params) {
        Map<String, String> tMap = new TreeMap<String, String>(params);
        StringBuffer sign2str = new StringBuffer();
        for (Map.Entry<String, String> e : tMap.entrySet()) {
            if (StringUtils.isBlank(e.getValue())) {
                continue;
            }
            sign2str.append(e.getKey() + "=" + e.getValue()).append("&");
        }
        sign2str.append("appkey=" + APP_KEY);

        String sign2sign = null;
        if ("MD5".equalsIgnoreCase(SIGN_TYPE)) {
            sign2sign = toMD5(sign2str.toString().getBytes());
        } else if ("SHA1".equalsIgnoreCase(SIGN_TYPE)) {
            sign2sign = toSHA1(sign2str.toString().getBytes());
        }
        return sign2sign;
    }

    /**
     * 基本 API 参数（主要是鉴权方面的）
     *
     * @return
     */
    public static Map<String, String> baseApiParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", APP_ID);
        params.put("sign_type", SIGN_TYPE);
        params.put("timestamp", System.currentTimeMillis() + "");
        return params;
    }

    /**
     * 基本 API 参数（主要是鉴权方面的）
     *
     * @param userId
     * @return
     */
    public static Map<String, String> baseApiParams(Serializable userId) {
        Map<String, String> params = baseApiParams();
        params.put("op_user", userId.toString());
        return params;
    }

    // ----------- 辅助方法

    /**
     * 获取 HttpClient
     *
     * @return
     */
    public static HttpClient getHttpClient() {
        if (httpClient != null) {
            return httpClient;
        }

        HttpClientParams clientParams = new HttpClientParams();
        clientParams.setConnectionManagerTimeout(5 * 1000);
        clientParams.setSoTimeout(15 * 1000);
        clientParams.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, ENCODING);
        clientParams.setConnectionManagerClass(MultiThreadedHttpConnectionManager.class);
        httpClient = new HttpClient(clientParams);

        HttpConnectionManagerParams connectionParams = httpClient.getHttpConnectionManager().getParams();
        connectionParams.setDefaultMaxConnectionsPerHost(20);
        connectionParams.setMaxTotalConnections(200);
        connectionParams.setConnectionTimeout(15 * 1000);
        return httpClient;
    }

    /**
     * 读取结果集
     *
     * @param method
     * @return
     * @throws IOException
     */
    public static String readResponse(HttpMethod method) throws IOException {
        String responseText = null;
        BufferedReader reader = null;
        try {
            InputStream stream = method.getResponseBodyAsStream();
            if (stream.available() > -1) {
                Header encoding = method.getResponseHeader("Content-Encoding");
                if (encoding != null && encoding.getValue().toUpperCase().contains("GZIP")) {
                    stream = new GZIPInputStream(stream);
                }
                String enc = StringUtils.defaultIfEmpty(
                        method.getParams().getContentCharset(), ENCODING);
                reader = new BufferedReader(new InputStreamReader(stream, enc));

                StringBuffer sb = new StringBuffer();
                char[] buffer = new char[1024 * 4];
                int read = -1;
                while ((read = reader.read(buffer)) > 0) {
                    char[] chunk = new char[read];
                    System.arraycopy(buffer, 0, chunk, 0, read);
                    sb.append(chunk);
                }
                responseText = sb.toString();
            }

        } catch (IOException ex) {
            throw ex;
        } finally {
            try {
                reader.close();
            } catch (Exception ex){}
            method.releaseConnection();
        }
        return responseText;
    }

    /**
     * MD5加密
     *
     * @param input
     * @return
     */
    public static String toMD5(byte[] input) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(input);
        return toHexString(digest.digest());
    }

    /**
     * SHA1加密
     *
     * @param input
     * @return
     */
    public static String toSHA1(byte[] input) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(input);
        return toHexString(digest.digest());
    }

    /**
     * URL编码
     *
     * @param text
     * @return
     */
    public static String urlEncode(String text) {
        if (text == null) {
            return null;
        }
        try {
            return URLEncoder.encode(text, ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Exception encoding URL string: " + e);
        }
    }

    /**
     * @param data
     * @return
     */
    private static String toHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // 测试
    public static void main(String[] args) {
        DhOrderService dhOrderService = new DhOrderServiceImpl();
        int i = 1;
//        Timer t = new Timer();
//        t.schedule(new MyTimer(),TimerUitl.df.get().parse("2019-06-27 09:30:30"),10000);
        while (true) {
            Map<String, String> params = OpenApiSDK.baseApiParams();
            params.put("page_no",i+++"");
            params.put("user", "xx");
            params.put("password", toMD5("xx".getBytes()));
            params.put("fields","orderId,orderName,orderType,accountId,orderDate,remark,total,paid,unpaid,invoiced,uninvoiced,owningUser,owningBusinessUnit,createdBy,createdOn,modifiedBy,modifiedOn,isDeleted,chanceId,jihuijindugengxin,c__baozhuangfangshi,c__shengchanjiaohuoqi,c__fujian,c__zhiliangyaoqiu,c__zongshuliangzhang,c__zongshulianglifang,c__zongshuliangjianshu,c__dizhi,c__dingdanleixing,c__bizhong,c__yufukuan");
            params.put("op_user","027-9b442b9a-d2be-4d46-93d2-0313593ec927");
            params.put("page_size","200");
            String apiUrl = OpenApiSDK.signUrl("salesorder/list", params);
            JSONObject r = OpenApiSDK.apiGet(apiUrl);
            if (r.getJSONArray("data")!=null&&r.getJSONArray("data").isEmpty()){break;}
            JSONArray arr = r.getJSONArray("data");
            int len = arr.size();
            for (int j = 0; j < len; j++) {
                JSONObject obj = (JSONObject)arr.get(j);
                DhOrder dor = obj.toJavaObject(DhOrder.class);
                if (dor != null)
                dhOrderService.insert(dor);
            }
        }
    }

    @RequestMapping("/test")
    public void test(){
        System.out.println(111);
        int i = 1;
//        Timer t = new Timer();
//        t.schedule(new MyTimer(),TimerUitl.df.get().parse("2019-06-27 09:30:30"),10000);
        while (true) {
            Map<String, String> params = OpenApiSDK.baseApiParams();
            params.put("page_no",i+++"");
            params.put("user", "xx");
            params.put("password", toMD5("xx".getBytes()));
            params.put("fields","orderId,orderName,orderType,accountId,orderDate,remark,total,paid,unpaid,invoiced,uninvoiced,owningUser,owningBusinessUnit,createdBy,createdOn,modifiedBy,modifiedOn,isDeleted,chanceId,jihuijindugengxin,c__baozhuangfangshi,c__shengchanjiaohuoqi,c__fujian,c__zhiliangyaoqiu,c__zongshuliangzhang,c__zongshulianglifang,c__zongshuliangjianshu,c__dizhi,c__dingdanleixing,c__bizhong,c__yufukuan");
            params.put("op_user","027-9b442b9a-d2be-4d46-93d2-0313593ec927");
            params.put("page_size","200");
            String apiUrl = OpenApiSDK.signUrl("salesorder/list", params);
            JSONObject r = OpenApiSDK.apiGet(apiUrl);
            if (r.getJSONArray("data")!=null&&r.getJSONArray("data").isEmpty()){break;}
            JSONArray arr = r.getJSONArray("data");
            int len = arr.size();
            for (int j = 0; j < len; j++) {
                JSONObject obj = (JSONObject)arr.get(j);
                DhOrder dor = obj.toJavaObject(DhOrder.class);
                if (dor != null) {
                    dhOrderService.insert(dor);
                }
            }
        }
    }
}
class MyTimer extends TimerTask{
    @Override
    public void run() {
        DateFormat dateFormat = TimerUitl.df.get();
        System.out.println("我的任务运行了" + dateFormat.format(new Date()));
    }
}
class TimerUitl {
    public static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
}
