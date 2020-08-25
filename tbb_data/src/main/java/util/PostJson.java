package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import modal.DhPerson;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.protocol.HTTP;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.Key;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * https POST请求
 */
public class PostJson {

    /**发送待办消息*/
    private static final String  TODONEWS_URL = "https://www.yunzhijia.com/gateway/newtodo/open/generatetodo.json?accessToken=";
    /**待办处理*/
    private static final String  TODONEWS_HANDLE_URL = "https://www.yunzhijia.com/gateway/newtodo/open/action.json?accessToken=";
    /**待办消息确认*/
    private static final String  TODONEWS_CONFIRM_URL = "https://www.yunzhijia.com/gateway/newtodo/open/checkcreatetodo.json?accessToken=";
    /**获取accessToken值*/
    private static final String ACCESSTOKEN_URL = "https://www.yunzhijia.com/gateway/oauth2/token/getAccessToken";
    /**获取所有人员的信息*/
    private static final String GETALL_URL = "https://www.yunzhijia.com/gateway/opendata-control/data/getallpersons?accessToken=";

    private static final String GETALL_ORGS_URL = "https://www.yunzhijia.com/gateway/opendata-control/data/getallorgs?accessToken=";

    /**发送待办消息时生成的待办id*/
    private static final Long sourceId = System.currentTimeMillis();
    private static final String charset = "UTF-8";

    private static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

//        @Override
//        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//
//        }
//
//        @Override
//        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//
//        }
//
//        @Override
//        public X509Certificate[] getAcceptedIssuers() {
//            return new X509Certificate[0];
//        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }

//        @Override
//        public boolean verify(String s, SSLSession sslSession) {
//            return true;
//        }
    }

    public static byte[] post(String url, String content, String charset) throws NoSuchAlgorithmException,
            KeyManagementException, IOException {
         /*类HttpsURLConnection似乎并没有提供方法设置信任管理器。其实，HttpsURLConnection通过SSLSocket来建立与HTTPS的安全连接，SSLSocket对象是由SSLSocketFactory生成的。
            * HttpsURLConnection提供了方法setSSLSocketFactory(SSLSocketFactory)设置它使用的SSLSocketFactory对象。
            * SSLSocketFactory通过SSLContext对象来获得，在初始化SSLContext对象时，可指定信任管理器对象。
            * */
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null,new TrustManager[]{new TrustAnyTrustManager()},new SecureRandom());
        URL console = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection)console.openConnection();
        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        conn.setDoOutput(true);
        conn.setRequestProperty(HTTP.CONTENT_TYPE,"application/json;charset=utf-8");
        conn.connect();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(content.getBytes(charset));
        out.flush();
        out.close();
        InputStream is = conn.getInputStream();
        if (is != null){
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1){
                outputStream.write(buffer,0,len);
            }
            is.close();
            return  outputStream.toByteArray();
        }
        return null;
    }

    public static byte[] post2(String url, String content, String charset) throws NoSuchAlgorithmException,
            KeyManagementException, IOException {
         /*类HttpsURLConnection似乎并没有提供方法设置信任管理器。其实，HttpsURLConnection通过SSLSocket来建立与HTTPS的安全连接，SSLSocket对象是由SSLSocketFactory生成的。
            * HttpsURLConnection提供了方法setSSLSocketFactory(SSLSocketFactory)设置它使用的SSLSocketFactory对象。
            * SSLSocketFactory通过SSLContext对象来获得，在初始化SSLContext对象时，可指定信任管理器对象。
            * */
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null,new TrustManager[]{new TrustAnyTrustManager()},new SecureRandom());
        URL console = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection)console.openConnection();
        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        conn.setDoOutput(true);
        conn.setRequestProperty(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded;charset=utf-8");
        conn.connect();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(content.getBytes(charset));
        out.flush();
        out.close();
        InputStream is = conn.getInputStream();
        if (is != null){
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1){
                outputStream.write(buffer,0,len);
            }
            is.close();
            return  outputStream.toByteArray();
        }
        return null;
    }

//    /**
//     * postA方式请求服务器(https协议)
//     * @param url
//     *            请求地址
//     * @param content
//     *            参数
//     * @param charset
//     *            编码
//     * @return
//     * @throws NoSuchAlgorithmException
//     * @throws KeyManagementException
//     * @throws IOException
//     */
//    public static byte[] postA(String url, String content, String charset) throws NoSuchAlgorithmException,
//            KeyManagementException, IOException {
//         /*类HttpsURLConnection似乎并没有提供方法设置信任管理器。其实，HttpsURLConnection通过SSLSocket来建立与HTTPS的安全连接，SSLSocket对象是由SSLSocketFactory生成的。
//            * HttpsURLConnection提供了方法setSSLSocketFactory(SSLSocketFactory)设置它使用的SSLSocketFactory对象。
//            * SSLSocketFactory通过SSLContext对象来获得，在初始化SSLContext对象时，可指定信任管理器对象。
//            * */
//        SSLContext sc = SSLContext.getInstance("SSL");
//        sc.init(null,new TrustManager[]{new TrustAnyTrustManager()},new SecureRandom());
//        URL console = new URL(url);
//        HttpsURLConnection conn = (HttpsURLConnection)console.openConnection();
//        conn.setSSLSocketFactory(sc.getSocketFactory());
//        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
//        conn.setDoOutput(true);
//        conn.setRequestProperty(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded;charset=utf-8");
//        conn.connect();
//        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
//        out.write(content.getBytes(charset));
//        out.flush();
//        out.close();
//        InputStream is = conn.getInputStream();
//        if (is != null){
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            while ((len = is.read(buffer)) != -1){
//                outputStream.write(buffer,0,len);
//            }
//            is.close();
//            return  outputStream.toByteArray();
//        }
//        return null;
//    }

//    public String doPost(String strUrl, String content) {
//        String result = "";
//        try {
//            URL url = new URL(strUrl);
//            //通过调用url.openConnection()来获得一个新的URLConnection对象，并且将其结果强制转换为HttpURLConnection.
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("POST");
//            //设置连接的超时值为30000毫秒，超时将抛出SocketTimeoutException异常
//            urlConnection.setConnectTimeout(30000);
//            //设置读取的超时值为30000毫秒，超时将抛出SocketTimeoutException异常
//            urlConnection.setReadTimeout(30000);
//            //将url连接用于输出，这样才能使用getOutputStream()。getOutputStream()返回的输出流用于传输数据
//            urlConnection.setDoOutput(true);
//            //设置通用请求属性为默认浏览器编码类型
//            urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
//            //getOutputStream()返回的输出流，用于写入参数数据。
//
//            OutputStream outputStream = urlConnection.getOutputStream();
//            outputStream.write(content.getBytes());
//            outputStream.flush();
//            outputStream.close();
//            //此时将调用接口方法。getInputStream()返回的输入流可以读取返回的数据。
//            InputStream inputStream = urlConnection.getInputStream();
//            byte[] data = new byte[1024];
//            StringBuilder sb = new StringBuilder();
//            //inputStream每次就会将读取1024个byte到data中，当inputSteam中没有数据时，inputStream.read(data)值为-1
//            while (inputStream.read(data) != -1) {
//                String s = new String(data, Charset.forName("utf-8"));
//                sb.append(s);
//            }
//            result = sb.toString();
//            inputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }


    public static String post(String url, String content)
            throws NoSuchAlgorithmException, KeyManagementException,
            IOException {
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                new SecureRandom());

        URL console = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
        conn.setDoOutput(true);
        conn.addRequestProperty("connection", "Keep-Alive");
//        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        conn.connect();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(content.getBytes("utf-8"));
        // 刷新、关闭
        out.flush();
        out.close();
        InputStream is = conn.getInputStream();
        if (is != null) {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            is.close();
            if(outStream!=null){
                return new String(outStream.toByteArray(),"utf-8");
            }
        }
        return null;
    }

//    public String getPost(String url) throws Exception{
//        URL rUrl = new URL(url);
//        HttpURLConnection urlConnection = (HttpURLConnection) rUrl.openConnection();
//        urlConnection.setRequestMethod("GET");
//        urlConnection.setDoOutput(true);
//        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
//        String line,readLine = "";
//        while ((line = br.readLine()) != null){
//            readLine += line;
//        }
//        br.close();
//        return readLine;
//    }

    public static String postUrlEncodeForm(String url) throws Exception {
        String result = "";

        //处理请求参数
        NameValuePair[] valuePairs = {
                new NameValuePair("eid", "534488"),
                new NameValuePair("time", "1990-01-01 00:00:00"),
                new NameValuePair("begin", "0"),
                new NameValuePair("count", "1000")
        };

        //设置client参数
        HttpClient httpClient = new HttpClient();

        //发送请求
//        HttpPost post = new HttpPost(url);
        PostMethod pm = new PostMethod(url);
        pm.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        pm.setRequestBody(valuePairs);

        int i = httpClient.executeMethod(pm);
        result = pm.getResponseBodyAsString();

        return result;
    }


    public static void main(String[] args) throws Exception{
//        for (int i = 0;i < 10000;i ++){
//            if (i %1000 == 0)
//            System.out.println(i);
//        }

//        PostJson pj = new PostJson();

//        String content = pj.getbaowen();
//        String charset = "UTF-8";

//        byte[] a = pj.post(ACCESSTOKEN_URL,content,charset);
//        String b = new String(a);
//        JSONObject jsonObject = JSONObject.parseObject(b);
//        String accessToken = jsonObject.getJSONObject("data").getString("accessToken");

//        content = pj.getParams();
//        byte[] postTODONEWS = pj.post(TODONEWS_URL + pj.getAccesstoken(), content, charset);
//        String c = new String(postTODONEWS);
//        JSONObject jsonObject1 = JSONObject.parseObject(c);
//        System.out.println(jsonObject1);

//        pj.getSmsNews();//发送待办消息
//        pj.getNewsConfirm("5cd8ba86e4b08d7338478e79");//待办消息确认   接收人的openId
//        pj.getNewsHandle("5cd8ba86e4b08d7338478e79");//待办消息处理

//        List<DhPerson> list = new ArrayList<DhPerson>();
//        List<DhPerson> personList = pj.getAllPerson(0, 1000);
//        List<DhPerson> personList1 = pj.getAllPerson(1000,2000);
//        List<DhPerson> personList2 = pj.getAllPerson(2000,3000);
//        list.addAll(personList);
//        list.addAll(personList1);
//        list.addAll(personList2);
//        String str = "";
//        for (DhPerson person:list) {
//            if (person.getName() != null && person.getName() != "")
//            System.out.println(person.getName() + " " + person.getDepartment() + " " + person.getOpenId() + " " + person.getJobNo());
//        }

//        pj.getAccesstokenUrl();

//        pj.getAccesstoken();
//        pj.getAllDeq();
//        getDES();

    }

    private static void getDES() throws Exception {
//        //1.生成KEY
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");//Key的生成器
//        keyGenerator.init(56);//指定keySize
//        SecretKey secretKey = keyGenerator.generateKey();
//        byte[] bytesKey = secretKey.getEncoded();
//
//        //2.KEY转换
//        DESKeySpec desKeySpec = new DESKeySpec(bytesKey);//实例化DESKey秘钥的相关内容
//        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");//实例一个秘钥工厂，指定加密方式
//        Key convertSecretKey = factory.generateSecret(desKeySpec);
//
//
//        //3.加密    DES/ECB/PKCS5Padding--->算法/工作方式/填充方式
//        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");//通过Cipher这个类进行加解密相关操作
//        cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
//        byte[] result = cipher.doFinal("12345678900".getBytes());//输入要加密的内容
//        System.out.println("加密的结果：" + Hex.encodeHexString(result));
////        System.out.println("加密的结果str：" + new String(result,"UTF-8"));
//
//        //4.解密
//        cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
//        byte[] bytes = Hex.decodeHex(Hex.encodeHexString(result));
//        result = cipher.doFinal(bytes);
//        System.out.println("解密结果：" + new String(result));
    }

    public static List<DhPerson> getAllPerson(int start, int end)throws Exception {
//        String a = doPost(GETALL_URL + getAccesstoken(),getAllPersonParams());
//        JSONObject jsonObject = JSONObject.parseObject(a);
//        System.out.println(jsonObject);
        byte[] bytes = post2(GETALL_URL + getAccesstoken(), getAllPersonParams(start,end), charset);
        JSONObject jsonObject = JSONObject.parseObject(new String(bytes,charset));
        JSONObject data = (JSONObject)jsonObject.get("data");
        JSONArray persons = data.getJSONArray("persons");
        List<DhPerson> list = new ArrayList<DhPerson>();
        int len = persons.size();
        for (int i = 0; i < len; ++i){
            JSONObject object = persons.getJSONObject(i);
            DhPerson person = new DhPerson();
            person.setDepartment(object.getString("department"));
            person.setGender(object.getString("gender"));
            person.setGlobalId(object.getString("globalId"));
            person.setIsAdmin(object.getString("isAdmin"));
            person.setJobNo(object.getString("jobNo"));
            person.setJobTitle(object.getString("jobTitle"));
            person.setName(object.getString("name"));
            person.setOpenId(object.getString("openId"));
            person.setOrgId(object.getString("orgId"));
            person.setStatus(object.getString("status"));
            list.add(person);
        }
        return list;
    }

    public static String getAllPersonParams(int start,int end) throws Exception{
        //&eid=534488&time=1990-01-01 00:00:00&begin=0&count=1000
        String result = "&eid=534488&time=1990-01-01 00:00:00&begin=" + start + "&count=" + end;
        return result;
    }

    public static String getAllDeq() throws Exception{
        String params = getAllDeqParams();
        byte[] bytes = post2(GETALL_ORGS_URL + getAccesstoken(), params, charset);
        JSONObject jsonObject = JSONObject.parseObject(new String(bytes));
        System.out.println(jsonObject);
        return "";
    }

    public static String getAllDeqParams() throws Exception{
        return "&eid=534488&begin=0&count=1000";
//        NameValuePair[] nameValuePairs = {
//                new NameValuePair("eid", "534488"),
//                new NameValuePair("begin", "0"),
//                new NameValuePair("count", "1000")
//        };
//        return nameValuePairs.toString();

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("eid", "534488");
//        jsonObject.put("begin", 0);
//        jsonObject.put("count", 1000);
//        return jsonObject.toString();
    }

    public static String getNewsHandle(String openId)throws KeyManagementException, NoSuchAlgorithmException, IOException{
        byte[] post = post(TODONEWS_HANDLE_URL + getAccesstoken(), getNewsHandleParams(openId), charset);
        String a = new String(post);
        JSONObject jsonObject = JSONObject.parseObject(a);
//        System.out.println("待办消息处理:"+jsonObject);
        return "";
    }

    private static String getNewsHandleParams(String openId) {
        JSONObject a = new JSONObject();
        a.put("sourcetype", "500065577");
        a.put("sourceitemid", sourceId);
        a.put("openids", openId);
        JSONObject b = new JSONObject();
        b.put("deal", "0");
        b.put("read", "0");
        a.put("actiontype", b);
        return a.toString();
    }

    /**
     * 待办消息确认
     * @param openId    接受人的openId
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getNewsConfirm(String openId) throws KeyManagementException, NoSuchAlgorithmException, IOException{
        byte[] post = post(TODONEWS_CONFIRM_URL + getAccesstoken(), getNewsParams(sourceId, openId), charset);
        String a = new String(post);
        JSONObject jsonObject = JSONObject.parseObject(a);
//        System.out.println("待办消息确认:"+jsonObject);
        return "";
    }

    /**
     * 发送待办消息
     * @param sourceId  发送待办消息时生成的待办id
     * @param openId    发送接收人的openId
     * @return
     */
    public static String getNewsParams(Long sourceId,String openId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sourcetype", "500065577");
        jsonObject.put("sourceitemid", sourceId);
        jsonObject.put("openId", openId);
        return jsonObject.toString();
    }

    public static void getSmsNews(String content,String fromOpenId,String toOpenId,String sourceId,String url) throws KeyManagementException, NoSuchAlgorithmException, IOException{
        byte[] post = post(TODONEWS_URL + getAccesstoken(), getParams(content, fromOpenId,
                toOpenId,sourceId,url), charset);
        String a = new String(post);
        JSONObject jsonObject = JSONObject.parseObject(a);
//        System.out.println("发送:"+jsonObject);
    }

    public static String getAccesstoken() throws KeyManagementException, NoSuchAlgorithmException, IOException{
        byte[] a = post(ACCESSTOKEN_URL,getbaowen(),charset);
        String b = new String(a);
        JSONObject jsonObject = JSONObject.parseObject(b);
        String accessToken = jsonObject.getJSONObject("data").getString("accessToken");
//        System.out.println("accessToken: "+accessToken);
        return accessToken;
    }

    public static String getAccesstokenUrl() throws KeyManagementException, NoSuchAlgorithmException, IOException {
        byte[] a = post(ACCESSTOKEN_URL,getparams(),charset);
        String b = new String(a);
        JSONObject jsonObject = JSONObject.parseObject(b);
        String accessToken = jsonObject.getJSONObject("data").getString("accessToken");
        System.out.println("accessToken: "+accessToken);
        return accessToken;
    }

    public static String getparams(){
        JSONObject test = new JSONObject();
        test.put("scope", "resGroupSecret");
        test.put("eid", "534488");
        test.put("secret", "8KZhldTJDhP1kw5ZFhiGeb1LfMNjAXYc");
        test.put("timestamp", sourceId);
        String query = test.toString();
        return query;
    }

    //构造嵌套的JSON报文方式，即在new一个JSONObject,并返回报文字符串
    public static String getbaowen(){
        JSONObject test = new JSONObject();
        test.put("scope", "app");
        test.put("appId", "500065577");
        test.put("secret", "4DHJzOmDMVmAko7tkbRE");
        test.put("timestamp", sourceId);
        String query = test.toString();
        return query;
    }

    public static String getParams(String content,String fromOpenId,String toOpenId,String sourceId,String url) throws KeyManagementException, NoSuchAlgorithmException, IOException{
        JSONObject params = new JSONObject();
        params.put("content", content);
        params.put("title", "参会信息");//必填
//        params.put("itemtitle", "拜访审批");
        params.put("headImg", "https://www.yunzhijia.com/space/c/photo/load?id=5a2f7ad750f8dd7810e79981");//必填
        params.put("appId", "500065577");//必填
        params.put("senderId", fromOpenId);
        params.put("url", url);//必填
        params.put("sourceId", sourceId);//必填
        params.put("sync", true);//同步代办

        JSONObject test = new JSONObject();
        test.put("DO",0);
        test.put("READ",0);

        JSONArray ja = new JSONArray();
        String[] split = toOpenId.split(";");
        for (String s:split) {
            JSONObject jb = new JSONObject();
            jb.put("status", test);
            jb.put("openId", s);//必填
            ja.add(jb);
        }

        params.put("params", ja);
//        System.out.println("发送时生成的sourceId:"+sourceId);
        return params.toString();
    }
}
