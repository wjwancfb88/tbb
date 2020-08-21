package sso.ITC;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import modal.DhPerson;
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
import util.HttpClientHelper;
import util.PostJson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.zip.GZIPInputStream;


public class ITCToken {
    private static final Log LOG = LogFactory.getLog(ITCToken.class);
    public static final String API_URL = "http://183.63.112.236:2020/sdk/";
    public static final String SIGN_TYPE = "MD5";
    public static final String ENCODING = "utf-8";
    private static HttpClient httpClient = null;

    /**
     * 获取云用户列表
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String getUsers(String value1)throws ClientProtocolException, IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value1", value1);//企业ID,为空查所有人
        JSONObject r = ITCToken.apiGet(API_URL+"listCloudUsers.do?_json="+jsonObject);
        String data= r.getString("data");
        return data;
    }

    /**
     * 新增云用户
     * @param jobNo 工号
     * @param orgs
     * @throws Exception
     */
    public static void addUser(String jobNo,String orgs)throws Exception{
        JSONObject jsonObject = new JSONObject();
        List list = new ArrayList();
        JSONArray jsonArray = JSONArray.parseArray(orgs);
        for(int i = 0;i < jsonArray.size();i++){
            String string = jsonArray.getString(i);
            JSONObject jo = JSONObject.parseObject(string);
            String orgId = jo.getString("orgId");
            list.add(orgId);
        }

        //兔宝宝orgId -> 4aea1cae-f5d3-4377-93de-ce5768161f10
        //集团orgId -> d7ff078a-ddea-4a26-918c-1a15d0c4118e
        //青岛orgId -> dd37e5be-1952-4830-be3e-4488091fc38b
        String tbb = "4aea1cae-f5d3-4377-93de-ce5768161f10";
        String jt = "d7ff078a-ddea-4a26-918c-1a15d0c4118e";
        String qd = "dd37e5be-1952-4830-be3e-4488091fc38b";

        jsonObject.put("value1", "admin");//默认用户名
        jsonObject.put("value2", "c4ca4238a0b923820dcc509a6f75849b");//默认密码1,MD5加密,32位小写
        jsonObject.put("value3", jobNo);//手机号码/邮箱/工号
        if (list.contains(qd)){//青岛
            jsonObject.put("value4","10");//企业id
        }else {
            if (list.contains(tbb)){
                jsonObject.put("value4", "9");//兔宝宝
            }else if (list.contains(jt)){
                jsonObject.put("value4", "8");//集团
            }
        }
        jsonObject.put("value5", "0");//使用天数,默认为空,0为永久
//        jsonObject.put("value6", jobNo);//账号
        ITCToken.apiGet(API_URL + "addCloudUser.do?_json=" + jsonObject);
    }

    /**
     * 创建会议
     * @param value1 会议名称
     * @param value3 结束时间 yyyy-MM-dd HH:mm:ss 2018-02-26 13:05:56
     * @param value4 会议模板
     * @param value5 会议说明
     * @param value6 已选成员ID(终端或云用户ID)  多个;隔开   1;2;3
     * @param value7 扩展字段
     * @param value8 最大成员数,默认最大8
     * @param value9 用户ID 为空是admin用户
     */
    public static void createMeeting(String value1,String value3,String value4,String value5,String value6,String value7,String value8,String value9){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value1", value1);//会议名称   测试会议
        jsonObject.put("value3", value3);//结束时间 yyyy-MM-dd HH:mm:ss 2018-02-26 13:05:56
        jsonObject.put("value4", value4);//会议模板   1
        jsonObject.put("value5", value5);//会议说明
        jsonObject.put("value6", value6);//已选成员ID(终端或云用户ID)  多个;隔开   1;2;3
        jsonObject.put("value7", value7);//扩展字段
        jsonObject.put("value8", value8);//最大成员数
        jsonObject.put("value9", value9);//用户ID,为空是admin用户
        System.out.println(API_URL + "createMeeting.do?_json=" + jsonObject);
//        JSONObject object = ITCToken.apiGet(API_URL + "createMeeting.do?_json=" + jsonObject);

        try{
            String tem = URLEncoder.encode(jsonObject.toJSONString(), "UTF-8");
            JSONObject object = ITCToken.apiGet(API_URL + "createMeeting.do?_json=" + tem);
            String value = object.getJSONObject("data").getString("value1");//会议ID
//            System.out.println(value);
            List<DhPerson> persons = getPersons();
            for (DhPerson p:persons) {
                String openId = p.getOpenId();
                String jobNo = p.getJobNo();
                String[] split = value6.split(";");//已选人员工号数组
                for (String s:split) {
                    if (s.equals(jobNo)){

                    }
                }
            }
//            PostJson.getSmsNews(value1,value9,value6,value9);
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    /**
     * 获取云之家所有用户
     * @return
     */
    public static List<DhPerson> getPersons(){
        try {
            int start = 0;
            int end = 1000;
            List<DhPerson> lists = new ArrayList<DhPerson>();
            while (true) {
                List<DhPerson> list = PostJson.getAllPerson(start, end);
                if (list.size() == 0) {
                    break;
                }
                lists.addAll(list);
                start += 1000;
                end += 1000;
            }
            return lists;
        }catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }

    public static String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
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

//    public static String signUrl(String apiName, Map<String, String> bizParams) {
//        return signUrl(API_URL, apiName, bizParams);
//    }
//    public static String signUrl(String apiUrl, String apiName, Map<String, String> bizParams) {
//        // ���ǩ������
//        bizParams.put("appid", APP_ID);
//        bizParams.put("sign_type", SIGN_TYPE);
//        bizParams.put("timestamp", System.currentTimeMillis() + "");
//
//        String sign = sign(bizParams);
//        bizParams.put("sign", sign);
//
//        apiUrl = apiUrl + apiName + "?";
//        for (Map.Entry<String, String> e : bizParams.entrySet()) {
//            apiUrl += e.getKey() + "=" + urlEncode(e.getValue()) + "&";
//        }
//        apiUrl = apiUrl.substring(0, apiUrl.length() - 1);  // remove &
//        return apiUrl;
//    }
//    public static String sign(Map<String, String> params) {
//        Map<String, String> tMap = new TreeMap<String, String>(params);
//        StringBuffer sign2str = new StringBuffer();
//        for (Map.Entry<String, String> e : tMap.entrySet()) {
//            if (StringUtils.isBlank(e.getValue())) {
//                continue;
//            }
//            sign2str.append(e.getKey() + "=" + e.getValue()).append("&");
//        }
//        sign2str.append("appkey=" + APP_KEY);
//
//        String sign2sign = null;
//        if ("MD5".equalsIgnoreCase(SIGN_TYPE)) {
//            sign2sign = toMD5(sign2str.toString().getBytes());
//        } else if ("SHA1".equalsIgnoreCase(SIGN_TYPE)) {
//            sign2sign = toSHA1(sign2str.toString().getBytes());
//        }
//        return sign2sign;
//    }

//    public static String toMD5(byte[] input) {
//        MessageDigest digest = null;
//        try {
//            digest = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        digest.update(input);
//        return toHexString(digest.digest());
//    }
//
//    /**
//     * SHA1����
//     *
//     * @param input
//     * @return
//     */
//    public static String toSHA1(byte[] input) {
//        MessageDigest digest = null;
//        try {
//            digest = MessageDigest.getInstance("SHA1");
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        digest.update(input);
//        return toHexString(digest.digest());
//    }
//
//    /**
//     * URL����
//     *
//     * @param text
//     * @return
//     */
//    public static String urlEncode(String text) {
//        if (text == null) {
//            return null;
//        }
//        try {
//            return URLEncoder.encode(text, ENCODING);
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException("Exception encoding URL string: " + e);
//        }
//    }
//    private static String toHexString(byte[] data) {
//        StringBuilder sb = new StringBuilder();
//        for (byte b : data) {
//            sb.append(String.format("%02x", b));
//        }
//        return sb.toString();
//    }

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

    public static void main(String[] args) throws Exception {
        List<DhPerson> persons = getPersons();
        int i = 1;
        for (DhPerson p:persons) {
            if (StringUtils.isNotBlank(p.getDepartment()) && StringUtils.isNotBlank(p.getJobNo())){
                System.out.println(i++ + " " + p.getName() + " " + p.getDepartment() + " " + p.getOpenId() + " " + p.getJobNo());
            }
        }
    }
}
