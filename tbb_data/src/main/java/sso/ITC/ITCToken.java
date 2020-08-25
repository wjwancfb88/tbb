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
    public static void addUser(String jobNo,String orgs,String name)throws Exception{
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

        jsonObject.put("value1", name);//默认用户名
        jsonObject.put("value2", "c4ca4238a0b923820dcc509a6f75849b");//默认密码1,MD5加密,32位小写
        jsonObject.put("value3", jobNo);//手机号码/邮箱/工号
        if (list.contains(qd)){//青岛
            jsonObject.put("value4","15");//企业id
        }else {
            if (list.contains(tbb)){
                jsonObject.put("value4", "13");//兔宝宝
            }else if (list.contains(jt)){
                jsonObject.put("value4", "14");//集团
            }
        }
        jsonObject.put("value5", "0");//使用天数,默认为空,0为永久
//        jsonObject.put("value6", jobNo);//账号
//        System.out.println(jsonObject.toJSONString());
        String string = URLEncoder.encode(jsonObject.toJSONString(),"UTF-8");
        ITCToken.apiGet(API_URL + "addCloudUser.do?_json=" + string);
    }

    /**
     * 发送会议信息
     * @param meetId 会议ID
     * @param meetName 会议名称
     * @param createUser 创建人 工号
     * @param startTime 开始时间 2020-01-01 00:00:00
     * @param endTime 结束时间 2020-01-01 01:00:00
     * @param participants 参会人员(1;2;3;4) 工号
     * @param meetpwd 会议密码
     * @param zhibopwd 直播密码
     * @param zhuxipwd 主席密码
     * @param url 快速参会链接
     */
    public static void smsMeeting(String meetId,String meetName,String createUser,String startTime,String endTime,String participants,String meetpwd,String zhibopwd,String zhuxipwd,String url){
        //会议名称、创建人、开始时间、结束时间、与会人员、会议密码、直播密码、主席密码、快速参会链接
        try{
            List<DhPerson> persons = getPersons();//获取所有人员信息
            String content = "";
            String fromOpenId = "";
            for (DhPerson p:persons) {
                String openId = p.getOpenId();//用户唯一识别码
                String jobNo = p.getJobNo();//工号
                String name = p.getName();//用户名

                if (createUser.equals(jobNo)){
                    content = name + "邀请您加入会议!\n" +
                            "会议名称：" + meetName + "\n" +
                            "开始时间：" + startTime + "\n" +
                            "结束时间：" + endTime + "\n" +
                            "会议密码：" + meetpwd + "\n" +
                            "直播密码：" + zhibopwd + "\n" +
                            "主席密码：" + zhuxipwd;
                    fromOpenId = openId;
                }
            }
            PostJson.getSmsNews(content,fromOpenId,participants,meetId,url);
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

    public static void getSms(){
        //会议名称、创建人、开始时间、结束时间、与会人员、会议密码、直播密码、主席密码、快速参会链接
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
        } finally {
            method.releaseConnection();
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
//                System.out.println(i++ + " " + p.getName() + " " + p.getDepartment() + " " + p.getOpenId() + " " + p.getJobNo() + " " + p.getOrgId());
//                addUser(p.getJobNo(),p.getOrgId());
                System.out.println(p);
            }
        }
    }
}
