package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.junit.Test;
import util.HttpClientHelper;

import java.io.*;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;

/**
 * Created by kaiwang on 2017/11/6.
 */
public class Logintest {
        private static final Log LOG = LogFactory.getLog(Logintest.class);
        public static final String API_URL = "http://tbb.wisecrm365.cn/gw/";
        public static final String APP_ID = "000-E585CFA3-EDD8-4DA0-9B33-F2CD228667EA";
        public static final String APP_KEY = "dF8T06U3gbaJI8HT1U3BoqqPiMoIDAWh0t2k6sYUK0r";
        public static final String SIGN_TYPE = "MD5";
        public static final String ENCODING = "utf-8";
        private static HttpClient httpClient = null;
//        @Test
        public   void getToken()throws ClientProtocolException, IOException {
                Map<String, String> params = new HashMap<String, String>();
                params.put("appid", APP_ID);
                params.put("sign_type", SIGN_TYPE);
                params.put("timestamp", System.currentTimeMillis() + "");
               // params.put("type","0");
                params.put("fields","userId,userName");
               // params.put("password", "bc11947b6b9a7df5cb814697c8920810");
                String apiUrl = Logintest.signUrl("user/list", params);
                JSONObject r = Logintest.apiGet(apiUrl);
                String data= r.getString("data");
               // JSONObject ob=JSONObject.parseObject(data);
                //String token=ob.getString("login_token");

        }
//        @Test
        public   void getbiToken()throws ClientProtocolException, IOException {
                String url="http://61.153.182.58:3396/DAP/web/webloginByTrustIp.do?userid="+"011772";
                //Map<String,String> body = new HashMap<String,String>();
                net.sf.json.JSONObject json= HttpClientHelper.httpGet(url);
                String token=json.getString("user_token");
               // return json.getString("user_token");
                // JSONObject ob=JSONObject.parseObject(data);
                //String token=ob.getString("login_token");

        }
        public static JSONObject apiGet(String apiUrl) {
                HttpMethod method = new GetMethod(apiUrl);
                try {
                        int status = getHttpClient().executeMethod(method);
                        if (status != 200) {
                                LOG.error("HTTP/GET: " + status + ", URL: " + apiUrl);
                                return null;
                        }

                        String result = readResponse(method);
                        if (result == null) {
                                LOG.error("HTTP/GETURL: " + apiUrl);
                                return null;
                        }
                        return JSON.parseObject(result);
                } catch (Exception e) {
                        LOG.error("HTTP/GET", e);
                        return null;
                }
        }

        public static String signUrl(String apiName, Map<String, String> bizParams) {
                return signUrl(API_URL, apiName, bizParams);
        }
        public static String signUrl(String apiUrl, String apiName, Map<String, String> bizParams) {
                // ���ǩ������
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
         * SHA1����
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
         * URL����
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
        private static String toHexString(byte[] data) {
                StringBuilder sb = new StringBuilder();
                for (byte b : data) {
                        sb.append(String.format("%02x", b));
                }
                return sb.toString();
        }

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

}
