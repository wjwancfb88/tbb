package util;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by User on 2017/6/9.
 */
public class HttpClientHelper {
    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static String post(String url,Map<String,String> head){
        HttpClient client = new HttpClient();
        HttpClientParams clientparam = new HttpClientParams();
        clientparam.setConnectionManagerTimeout(10000);
        clientparam.setContentCharset("UTF-8");
        client.setParams(clientparam);
        HttpMethod method=new CustomPostMethod(url);
        if(head != null){
            Iterator<String> iterator = head.keySet().iterator();
            while(iterator.hasNext()){
                String key = iterator.next();
                method.addRequestHeader(key, head.get(key));
            }
        }
        String result = null;
        try {
            client.executeMethod(method);
            result = method.getResponseBodyAsString();
        } catch (Exception e) {
            return null;
        }
        method.releaseConnection();
        return result;
    }

    public static String post(String url,Map<String,String>head,String body){
        HttpClient client = new HttpClient();
        HttpClientParams clientparam = new HttpClientParams();
        clientparam.setConnectionManagerTimeout(10000);
        clientparam.setContentCharset("UTF-8");
        client.setParams(clientparam);
        CustomPostMethod method=new CustomPostMethod(url);
        if(head != null){
            Iterator<String> iterator = head.keySet().iterator();
            while(iterator.hasNext()){
                String key = iterator.next();
                method.addRequestHeader(key, head.get(key));
            }
        }
        String result = null;
        try {
            if(body != null && !"".equals(body)){
                method.setRequestEntity(new StringRequestEntity(body,"application/json","UTF-8"));
            }
            client.executeMethod(method);
            result = method.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        method.releaseConnection();
        return result;
    }

    public static String post(String url,Map<String,String>head,Map<String,String>body){
        HttpClient client = new HttpClient();
        HttpClientParams clientparam = new HttpClientParams();
        clientparam.setConnectionManagerTimeout(10000);
        clientparam.setContentCharset("UTF-8");
        client.setParams(clientparam);
        CustomPostMethod method=new CustomPostMethod(url);
        if(head != null && head.size() != 0){
            Iterator<String> iterator = head.keySet().iterator();
            while(iterator.hasNext()){
                String key = iterator.next();
                method.addRequestHeader(key, head.get(key));
            }
        }
        if(body != null && body.size() != 0){
            Iterator<String> iterator = body.keySet().iterator();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            while(iterator.hasNext()){
                String key = iterator.next();
                String value = body.get(key).toString();
                params.add(new NameValuePair(key,value));
            }
            method.addParameters(params.toArray(new NameValuePair[params.size()]));
        }
        String result = null;
        try {
            client.executeMethod(method);
            result = method.getResponseBodyAsString();
        } catch (Exception e) {
            return null;
        }
        method.releaseConnection();
        return result;
    }

    public static JSONObject httpGet(String url) throws IOException {
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.fromObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.info("获取token请求失败");
            }
        } catch (IOException e) {

        }
        return jsonResult;
    }

}
