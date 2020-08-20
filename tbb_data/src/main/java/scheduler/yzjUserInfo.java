package scheduler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.body.MultipartBody;
import modal.CloudyUserInfo;
import modal.Department;
import modal.UserInfo;
import modal.YxtDepartment;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
//import org.junit.Test;
import util.EncryptUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by HP on 2017/7/4.
 */

/**
 * 云学堂数据同步
 */

public class yzjUserInfo {
    private String purl = "http://do.yunzhijia.com/openaccess/input/person/getall";
    private String durl = "http://do.yunzhijia.com/openaccess/input/dept/getall";
//    @Test
    public void getUserInfo() throws Exception {
        List<UserInfo> infolist=new ArrayList<UserInfo>();
        List<CloudyUserInfo> perlist=new ArrayList<CloudyUserInfo>();
        perlist=queryPerson(0) ;
        List<CloudyUserInfo> perlistother=new ArrayList<CloudyUserInfo>();
        perlistother=queryPerson(1001) ;
        perlist.addAll(perlistother);
        for(CloudyUserInfo info:perlist){
            UserInfo user=new UserInfo();
            user.setId(info.getOpenId());
            user.setUserName(info.getOpenId());
            user.setCnName(info.getName());
            user.setMail(info.getEmail());
            user.setSex(!info.getGender().equals("0")?"女":"男");
            user.setMobile(info.getPhone());
            user.setDepartment(info.getDepartment());
            user.setPassword(getSHA256Str(info.getOpenId()));
            infolist.add(user);

        }
        List<Department> departments =queryDapartment();
        List<YxtDepartment> yxtDepartments=new ArrayList<YxtDepartment>();
        for(Department dept:departments){
            List<String> users=new ArrayList<String>();
            for(UserInfo info:infolist){
                if(info.getDepartment().equals(dept.getDepartment())){
                    try {
                        users.add(info.getUserName());
                    }catch(Exception E){
                        System.out.println("users"+users+",info:"+info.getUserName());
                    }
                }
            }
            YxtDepartment yxtDepartment =new YxtDepartment();
            yxtDepartment.setID(dept.getId());
            yxtDepartment.setOuName(dept.getDepartment().substring(dept.getDepartment().lastIndexOf("\\")+1));
            yxtDepartment.setParentID(dept.getParentId());
            yxtDepartment.setUsers(users);
            yxtDepartments.add(yxtDepartment);
        }
        String jsonInfo= JSON.toJSONString(infolist);
        String departmentInfo=JSON.toJSONString(yxtDepartments);
        /**
         * ����һ���˺�ͬ��
         */
        subdata(jsonInfo);
        updateDepartment(departmentInfo);
        //subdata(jsonInfo);
    }
    public String getRrsource() throws URISyntaxException {
        URI uri = this.getClass().getResource("/").toURI();
        String p = uri.getPath();
        String filepath=p + "config/534488.key";
        return filepath;
    }

    public  List<CloudyUserInfo> queryPerson(int start) throws Exception {
        String jss="{'begin':"+start+"}";
        JsonNode jsonData = new JsonNode(jss);// json data without encrypt
        String keyFile = getRrsource() ;
        byte[] keyByte = EncryptUtils.getBytesFromFile(keyFile);
        Key key = EncryptUtils.restorePrivateKey(keyByte);
        String s=EncryptUtils.encryptWithEncodeBase64UTF8(
                jsonData.toString(), key);
        MultipartBody body= Unirest
                .post(purl)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("eid", "534488")
                .field("nonce", UUID.randomUUID().toString())
                .field("data",
                        EncryptUtils.encryptWithEncodeBase64UTF8(
                                jsonData.toString(), key));
        HttpResponse<JsonNode> a=body.asJson();

        JsonNode js=a.getBody();
        org.json.JSONObject jon=js.getObject();
        String info=jon.get("data").toString();
        List  array= JSONObject.parseArray(info);
        List<CloudyUserInfo>  arra= JSONObject.parseArray(info, CloudyUserInfo.class);
        return arra;
    }

    public  List<Department> queryDapartment() throws Exception {
        String jss="{'eid':'"+534488+"'}";
        JsonNode jsonData = new JsonNode(jss);// json data without encrypt
        String keyFile =getRrsource();
        byte[] keyByte = EncryptUtils.getBytesFromFile(keyFile);
        Key key = EncryptUtils.restorePrivateKey(keyByte);
        String s=EncryptUtils.encryptWithEncodeBase64UTF8(
                jsonData.toString(), key);
        MultipartBody body= Unirest
                .post(durl)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("eid", "534488")
                .field("nonce", UUID.randomUUID().toString())
                .field("data",
                        EncryptUtils.encryptWithEncodeBase64UTF8(
                                jsonData.toString(), key));
        HttpResponse<JsonNode> a=body.asJson();

        JsonNode js=a.getBody();
        org.json.JSONObject jon=js.getObject();
        String info=jon.get("data").toString();
        List  array= JSONObject.parseArray(info);
        List<Department>  arra= JSONObject.parseArray(info, Department.class);
        return arra;
    }

    //更新人员信息
    public  void subdata(String  infojson) throws IOException {
        String url="https://api.yunxuetang.cn/el/sync/users";
        String apiKey="20c08c37-3a85-4f9a-8c9b-f30f05a5b198";
        String salt= randomdata();
        String apisecret="a8cb2b97-eab8-41d9-b165-2a4822ec22b9";
        String signature=getSHA256Str(apisecret+ salt);
        //  String result=HttpClientHelper.post(url,head,body);
        JSONObject json = new JSONObject();
        json.put("apiKey",apiKey);
        json.put("salt",salt);
        json.put("signature",signature);
        json.put("islink", "true");
        json.put("users",infojson);
        StringEntity s= new StringEntity(json.toString(), "utf-8");
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setEntity(s);
        post.setHeader("Content-type", "application/json");
        post.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        post.addHeader("Accept-Language","en-us,en;q=0.5");
        org.apache.http.HttpResponse response = httpClient.execute(post);
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                entity.getContent(),"utf-8"));
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            buffer.append(temp);
        }
        String data=buffer.toString();
        JSONObject ob=JSONObject.parseObject(data);

    }

    //更新组织信息
    public  void updateDepartment(String  infojson) throws IOException {
        String url="https://api.yunxuetang.cn/el/sync/ous";
        String apiKey="20c08c37-3a85-4f9a-8c9b-f30f05a5b198";
        String salt= randomdata();
        String apisecret="a8cb2b97-eab8-41d9-b165-2a4822ec22b9";
        String signature=getSHA256Str(apisecret+ salt);
        //  String result=HttpClientHelper.post(url,head,body);
        JSONObject json = new JSONObject();
        json.put("apiKey",apiKey);
        json.put("salt",salt);
        json.put("signature",signature);
        json.put("isBaseInfo","false");
        json.put("ouInfo",infojson);
        StringEntity s= new StringEntity(json.toString(), "utf-8");
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setEntity(s);
        post.setHeader("Content-type", "application/json");
        post.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        post.addHeader("Accept-Language","en-us,en;q=0.5");
        org.apache.http.HttpResponse response = httpClient.execute(post);
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                entity.getContent(),"utf-8"));
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            buffer.append(temp);
        }
        String data=buffer.toString();
        JSONObject ob=JSONObject.parseObject(data);

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

    //获取随机数
    public String randomdata(){
        Random rm = new Random();

        double pross = (1 + rm.nextDouble()) * Math.pow(10,3);

        String fixLenthString = String.valueOf(pross);

        return fixLenthString.substring(1,3 + 1);
    }



}
