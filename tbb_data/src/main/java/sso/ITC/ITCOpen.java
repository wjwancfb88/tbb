package sso.ITC;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import modal.Constant;
import modal.DhPerson;
import modal.Parameter;
import modal.UserContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sso.Crm.CrmToken;
import util.HttpClientHelper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

@Controller
public class ITCOpen {

    @Autowired
    private Constant constant;
    private static final String ENC = "UTF-8";
    private String getaccesstokenurl = "http://do.yunzhijia.com/openauth2/api/appAuth2";
    private String getusercontexturl = "http://do.yunzhijia.com/openauth2/api/getcontext";
    private String getuserinfo = "http://yunzhijia.com/openapi/third/v1/opendata-control/data/getperson";

    private String getNewAccessToken = "https://www.yunzhijia.com/gateway/oauth2/token/getAccessToken";
    private String getPerson = "https://www.yunzhijia.com/gateway/opendata-control/data/getperson?accessToken=";
    private String getancestororgs = "https://www.yunzhijia.com/gateway/opendata-control/data/getancestororgs?accessToken=";

    @RequestMapping(value="/itc/jobNo.do")
    public ModelAndView jobNo(HttpServletRequest request,HttpServletResponse response) throws Exception {
//        String ticket = request.getParameter("ticket");
        String ticket = "APPURLWITHTICKET565a21a98b0fc582dfac28f89ab070a0";

//        Map<String,Object> info = new HashMap<String,Object>();
        try{
            String accesstoken = getAccessToken();//获取accessToken
            UserContext usercontext = getUserContext(ticket,accesstoken);//用户上下文
            String openId = usercontext.getOpenid();
//            String openId = "5e55f871e4b00655c02686e4";
            String eid = usercontext.getEid();
            String userInfo = getUserInfo(openId, eid);//用户信息
            JSONArray ob = JSONArray.parseArray(userInfo);
            String simpleInfo = ob.getString(0);
            JSONObject job = JSONObject.parseObject(simpleInfo);
            String jobNo = job.getString("jobNo");//工号
            String orgId = job.getString("orgId");
            String name = job.getString("name");
            String orgs = getOrgs(orgId, eid);

            String user1 = ITCToken.getUsers("");//获取所有用户(value1为空查所有)
            JSONArray users = JSONArray.parseArray(user1);
            Map<String,String> map = new HashMap<String, String>();
            for (int i = 0;i < users.size();i++) {
                String user = users.getString(i);
                JSONObject jo = JSONObject.parseObject(user);
                String value7 = jo.getString("value7");//工号
                String value3 = jo.getString("value3");//账号
                map.put(value7, value3);
            }
            if (!map.containsKey(jobNo)){
                ITCToken.addUser(jobNo,orgs,name);//新增用户
            }
            String value3 = map.get(jobNo);//账号
            //工号已存在登陆携带参会信息如参会密码,工号,等等
            request.setAttribute("value3", value3);

            return new ModelAndView("itcopen");
        }catch(RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/itc/smsMeeting.do")
    public void smsMeeting(String meetId,String meetName,String createUser,String startTime,String endTime,String participants,String meetpwd,String zhibopwd,String zhuxipwd,String url){
        //会议名称、创建人、开始时间、结束时间、与会人员、会议密码、直播密码、主席密码、快速参会链接
        meetId = "123";
        meetName = "测试会议";
        createUser = "020760";
        startTime = "2020-01-01 00:00:00";
        endTime = "2020-01-01 01:00:00";
        participants = "021471;022160;021438";
        meetpwd = "c4ca4238a0b923820dcc509a6f75849b";
        zhibopwd = "c4ca4238a0b923820dcc509a6f75849b";
        zhuxipwd = "c4ca4238a0b923820dcc509a6f75849b";
        url = "http://183.63.112.236:2020/meeting/join.do?n=787198335";
        ITCToken.smsMeeting(meetId,meetName,createUser,startTime,endTime,participants,meetpwd,zhibopwd,zhuxipwd,url);
    }

    @RequestMapping("/itc.do")
    public void addPersons() throws Exception {
        List<DhPerson> persons = ITCToken.getPersons();
        String eid = "534488";
        for (DhPerson p:persons) {
            if (StringUtils.isNotBlank(p.getDepartment()) && StringUtils.isNotBlank(p.getJobNo())){
                String orgId = p.getOrgId();
                String jobNo = p.getJobNo();
                String name = p.getName();
                String orgs = getOrgs(orgId, eid);
                ITCToken.addUser(jobNo,orgs,name);
            }
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

    private String getAccessToken2(){
        JSONObject test = new JSONObject();
        test.put("scope", "app");
        test.put("appId", "500059258");
        test.put("secret", "CloudHubJ2eeDemo");
        test.put("timestamp", System.currentTimeMillis());
        String query = test.toString();
        String b = HttpClientHelper.post(getNewAccessToken,null,query);
        JSONObject jsonObject = JSONObject.parseObject(b);
        String accessToken = jsonObject.getJSONObject("data").getString("accessToken");
//        System.out.println("accessToken: "+accessToken);
        return accessToken;
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

    public Map getUsePhone(String od,String ed){
        String openid =od;
        String eid =ed;
        try{
            String userinfo = getUserInfo(openid,eid);
            Map<String,String>model = new HashMap<String,String>();
            model.put("userinfo", userinfo);
            return model;
        }catch(RuntimeException e){
           return null;
        }
    }

    private String getOrgs(String orgId,String eid) throws RuntimeException{
        Map<String,String> body = new HashMap<String,String>();
        body.put("orgId", orgId);
        body.put("eid", eid);
        try {
            JSONObject orgjson = JSONObject.parseObject(HttpClientHelper.post(getancestororgs+getAccessToken2(), null, body));
            String orgs=orgjson.getString("data");
            return orgs;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String getUserInfo(String openid,String eid) throws RuntimeException{
        Map<String,String> body = new HashMap<String,String>();
        body.put("openId", openid);
        body.put("eid", eid);
        try {
            JSONObject userinfojson = JSONObject.parseObject(HttpClientHelper.post(getPerson+getAccessToken2(), null, body));
            String userInfo=userinfojson.getString("data");
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    private List<Parameter>convert(Map<String,String>params){
        List<Parameter> list= new ArrayList<Parameter>();
        Iterator<String>iterator = params.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            String value = params.get(key);
            list.add(new Parameter(key,value));
        }
        return list;
    }

    private String generateHead(String appid, String appSecret, String url, String method, List<Parameter> body) throws UnsupportedEncodingException {
        String authorization = "OAuth oauth_consumer_key=\"%s\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"%s\",oauth_nonce=\"%s\",oauth_version=\"1.0\",oauth_signature=\"%s\"";
        appid = URLEncoder.encode(appid, ENC);
        long timestamp = System.currentTimeMillis()/1000;
        String nonce = URLEncoder.encode(UUID.randomUUID().toString(), ENC);
        String sign;
        try {
            sign = generateSign(appid, timestamp, nonce, appSecret,url,method,body);
        } catch (Exception e) {
            return null;
        }
        sign = URLEncoder.encode(sign, ENC);
        authorization = String.format(authorization, appid, timestamp + "", nonce, sign);
        return authorization;
    }

    private String generateSign(String appid, long timestamp, String nonce, String appSecret, String url, String method,
                                List<Parameter> body) throws Exception {
        StringBuilder basestring = new StringBuilder();
        basestring = basestring.append(method).append("&").append(URLEncoder.encode(url,ENC)).append("&");
        List<Parameter>params = new ArrayList<Parameter>();
        if(body != null){
            params.addAll(body);
        }
        params.add(new Parameter("oauth_consumer_key", appid));
        params.add(new Parameter("oauth_signature_method", "HMAC-SHA1"));
        params.add(new Parameter("oauth_timestamp", timestamp + ""));
        params.add(new Parameter("oauth_nonce",nonce));
        params.add(new Parameter("oauth_version", "1.0"));
        parseGetParameters(url,params);
        Collections.sort(params, new Comparator<Parameter>(){


            public int compare(Parameter o1, Parameter o2) {
                int value =  o1.getKey().compareTo(o2.getKey());
                if(value == 0){
                    return o1.getValue().compareTo(o2.getValue());
                }else{
                    return value;
                }
            }

        });
        int count = 1;
        for(Parameter temp:params){
            basestring.append(temp.getKey()).append("%3D").append(temp.getValue());
            if(count != params.size()){
                basestring.append("%26");
            }
            count++;
        }
        byte[] byteHMAC = null;
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec spec = new SecretKeySpec((appSecret + "&").getBytes("UTF-8"),
                "HmacSHA1");
        mac.init(spec);
        byteHMAC = mac.doFinal(basestring.toString().getBytes("UTF-8"));
        return encodeBase64(byteHMAC);
    }

    private String encodeBase64(byte[]input) throws Exception{
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod= clazz.getMethod("encode", byte[].class);
        mainMethod.setAccessible(true);
        Object retObj=mainMethod.invoke(null, new Object[]{input});
        return (String)retObj;
    }

    private void parseGetParameters(String url,List<Parameter>params)
    {
        int queryStart = url.indexOf("?");
        if (-1 != queryStart)
        {
            String[] queryStrs = url.substring(queryStart + 1).split("&");
            try
            {
                for (String query : queryStrs)
                {
                    String[] split = query.split("=");
                    if (split.length == 2){
                        params.add(new Parameter(URLDecoder
                                .decode(split[0], "UTF-8"), URLDecoder.decode(
                                split[1], "UTF-8")));
                    }else{
                        params.add(new Parameter(URLDecoder
                                .decode(split[0], "UTF-8"), ""));
                    }
                }
            } catch (UnsupportedEncodingException localUnsupportedEncodingException)
            {
            }
        }
    }
}