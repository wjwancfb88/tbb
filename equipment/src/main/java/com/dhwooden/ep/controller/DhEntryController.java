package com.dhwooden.ep.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.DhEntry;
import com.dhwooden.ep.modal.Equipment;
import com.dhwooden.ep.modal.Test;
import com.dhwooden.ep.modal.User;
import com.dhwooden.ep.service.DhEntryService;
import com.dhwooden.ep.service.UserService;
import com.dhwooden.ep.sms.SmsSender;

import com.dhwooden.ep.sms.SmsTemplate;
import com.dhwooden.ep.util.DateUtil;
import com.dhwooden.ep.util.FileExportImportUtil;
import com.dhwooden.ep.util.HttpClientHelper;
import com.dhwooden.ep.util.PostJson;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2018-07-05
 */
@Controller
@RequestMapping("/admin/entry")
public class DhEntryController {
    private static Logger logger = LoggerFactory.getLogger(DhEntryController.class);

    private Cipher cipher = null;
    private Key convertSecretKey = null;
    private int des = 0;

    @Autowired
    private DhEntryService dhEntryService;

    @Autowired
    private UserService userService;

    public  static  String msg;

    @RequestMapping("/entrylist")
    public String entrylist(Model model,HttpServletResponse response,String flag) throws IOException {
        if (flag == null || "".equals(flag)){
            flag = "2";
        }
        Integer temp = Integer.valueOf(flag);
        if (temp <= 0 || temp > 4){
            flag = "2";
        }
        model.addAttribute("flag",flag);
        Subject currentUser = SecurityUtils.getSubject();
        // 验证用户是否验证，即是否登录
        if (!currentUser.isAuthenticated()) {
            String msg = "";
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
            //yyyyyyyyyyysa
            // remembermMe记住密码
            token.setRememberMe(true);
            try {
                // 执行登录.
                currentUser.login(token);
                // 登录成功...
                return "admin/entry/entrylist";
            } catch (IncorrectCredentialsException e) {
                msg = "登录密码错误";
                System.out.println("登录密码错误!!!" + e);
            } catch (ExcessiveAttemptsException e) {
                msg = "登录失败次数过多";
                System.out.println("登录失败次数过多!!!" + e);
            } catch (LockedAccountException e) {
                msg = "帐号已被锁定";
                System.out.println("帐号已被锁定!!!" + e);
            } catch (DisabledAccountException e) {
                msg = "帐号已被禁用";
                System.out.println("帐号已被禁用!!!" + e);
            } catch (ExpiredCredentialsException e) {
                msg = "帐号已过期";
                System.out.println("帐号已过期!!!" + e);
            } catch (UnknownAccountException e) {
                msg = "帐号不存在";
                System.out.println("帐号不存在!!!" + e);
            } catch (UnauthorizedException e) {
                msg = "您没有得到相应的授权！";
                System.out.println("您没有得到相应的授权！" + e);
            } catch (Exception e) {
                System.out.println("出错！！！" + e);
            }


        }

        return "admin/entry/entrylist";
    }

    @RequestMapping("/loading")
    public String loading(String code,Model model,HttpServletResponse response,HttpServletRequest request) throws Exception {
//        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxdb0d3982019dedcd&secret=215d6da4ab61377ce1398614ccd285dd&code="+code+"&grant_type=authorization_code";
//        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx119d493b885c9198&secret=d1ac107caf422a22ef07e1467b63583e&code="+code+"&grant_type=authorization_code";
//        JSONObject json= HttpClientHelper.httpGet(url);
//        logger.info(json.toString());
        //JSONObject ss = JSONObject.fromObject(json);
//        String openid = json.getString("openid");
//        request.getSession().setAttribute("openid",openid);

        //appid=wxdb0d3982019dedcd&secret=215d6da4ab61377ce1398614ccd285dd&js_code=043q9SMd0vMlov1C4MNd0hwbNd0q9SMI&grant_type=authorization_code
//        Map map=new HashMap();
        //openid=o0okw5T9tJgspGtIqPzcLyKQu2s8
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
//        String parame = "appid=wxdb0d3982019dedcd&secret=215d6da4ab61377ce1398614ccd285dd&js_code="+ code +"&grant_type=authorization_code";

//        String parame = "appid=wxdb0d3982019dedcd&secret=215d6da4ab61377ce1398614ccd285dd&js_code=043q9SMd0vMlov1C4MNd0hwbNd0q9SMI&grant_type=authorization_code";
//        String post = post(url, parame);
//        JSONObject json = JSONObject.parseObject(post);
//        String openid = json.getString("openid");
//        logger.info(openid);
//        map.put("open_id",openid);
//        List<DhEntry> list=dhEntryService.selectByMap(map);

        String flag = request.getParameter("flag");
        if(flag == null || "".equals(flag) || Integer.valueOf(flag) > 4 || Integer.valueOf(flag) <= 0){
            flag = String.valueOf(2);
        }

        model.addAttribute("flag",flag);
//        request.getSession().setAttribute("openid",openid);
//        if(list!=null&&list.size()>0){
//            model.addAttribute("dataList",list.get(0));
//            return "admin/entry/page3";
//        }else {
            return "admin/entry/page2";
//        }
    }

    @RequestMapping("/selectByVistor")
    @ResponseBody
    public DhEntry selectByVistor(HttpServletRequest request,HttpServletResponse response) throws Exception{
        List<DhEntry> list = new ArrayList<>();
        DhEntry entry = null;
        Map map = new HashMap();
        String flag = request.getParameter("flag");
        if(StringUtils.isBlank(flag) || Integer.valueOf(flag) > 4 || Integer.valueOf(flag) <= 0){
            flag = String.valueOf(2);
        }
        String vphone = request.getParameter("vphone");
        if (StringUtils.isNotBlank(vphone)){
//            map.put("status","1");
            map.put("vistor_phone",vphone);
            map.put("flag", flag);
            list = dhEntryService.selectByMap(map);
            if(list.size() > 0){
                entry = list.get(0);
            }else{
                entry = new DhEntry();
            }
        }else{
            entry = new DhEntry();
        }
        return entry;
    }

//    @RequiresPermissions("entry:list")
    @RequestMapping("/add")
    public String add(HttpServletRequest request,Model model,String plateNo,String type,String idCard,HttpServletResponse response,String vphone,String vistorname,String dept,String phone,String username,String vistortime,String reason,String flag) throws Exception {
        this.des = 0;//重置快速回复次数
        if (vphone != null && vphone !="" && StringUtils.isNotBlank(phone)){
            if (flag == null || StringUtils.isBlank(flag)){
                flag = "2";
            }
            Integer temp = Integer.valueOf(flag);
            if (temp <= 0 || temp > 4){
                flag = "2";
            }
//            Map map=new HashMap();
//        List<DhEntry> list= new ArrayList<DhEntry>();
//        if(vphone==""||vphone==null){
//            return "admin/entry/page2";
//        }
//        String openid= (String) request.getSession().getAttribute("openid");
//            map.put("status","0");
//            map.put("vistor_phone",vphone);
//            map.put("flag", flag);
//            List<DhEntry> list=dhEntryService.selectByMap(map);
//            if(list.size()>0){
//            return "admin/entry/error";
//                DhEntry entry = list.get(0);
//                String vphone1 = entry.getVistorPhone();
//                String vistor = entry.getVistor();
//                String idCard1 = entry.getIdCard();
//                String plateNo1 = entry.getPlateNo();
//                model.addAttribute("vphone",vphone1);
//                model.addAttribute("vistorname",vistor);
//                model.addAttribute("idCard",idCard1);
//                model.addAttribute("plateNo",plateNo1);
//                return "admin/entry/page2";
//            }else {
                Map mapNames = new HashMap();
                mapNames.put("name", username);
                List<User> userList = userService.selectEmpByName(mapNames);
                User user = null;
                if (userList.size() > 0){
                    user = userList.get(0);
                }else{
                    user = new User();
                    user.setOpenId(UUID.randomUUID().toString().replace("-", ""));
                }
                DhEntry entry = new DhEntry();
                entry.setVistor(vistorname);
                entry.setIdCard(idCard);
                entry.setOpenId(user.getOpenId());
//                entry.setOpenId(openid);
                entry.setType(type);
                entry.setVistorPhone(vphone);
                entry.setDept(dept);
                entry.setPlateNo(plateNo);
                entry.setUser(username);
                entry.setUserPhone(phone);
                entry.setReason(reason);
                entry.setStatus("0");
                entry.setMsgStatus("0");
                entry.setIntime(new Date());
                entry.setFlag(flag);
                dhEntryService.insert(entry);
                //smsTaskSender.sendSms();
                Map<String, String> data = new HashMap<>();
                data.put("name", vistorname);
                data.put("phone", vphone);

                //1.生成KEY
                KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");//Key的生成器
                keyGenerator.init(56);//指定keySize
                SecretKey secretKey = keyGenerator.generateKey();
                byte[] bytesKey = secretKey.getEncoded();

                //2.KEY转换
                DESKeySpec desKeySpec = new DESKeySpec(bytesKey);//实例化DESKey秘钥的相关内容
                SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");//实例一个秘钥工厂，指定加密方式
                convertSecretKey = factory.generateSecret(desKeySpec);

                //3.加密    DES/ECB/PKCS5Padding--->算法/工作方式/填充方式
                cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");//通过Cipher这个类进行加解密相关操作
                cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
                byte[] result = cipher.doFinal(vphone.getBytes());//输入要加密的内容
//                System.out.println("加密的结果：" + Hex.encodeHexString(result));

                //4.解密
//                cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
//                result = cipher.doFinal(result);
//                System.out.println("解密结果：" + new String(result));





//                aa(null, Hex.encodeHexString(result));
                String url = "http://61.153.182.58:3401/admin/entry/aa?aa="+Hex.encodeHexString(result);
//                String url = "http://app.tubaoyz.com/admin/entry/aa?aa="+Hex.encodeHexString(result);
//                System.out.println(url);
//                String url = "http://localhost/admin/entry/aa?aa="+Hex.encodeHexString(result);
                data.put("url", url);
                msg = vphone;
                if ("1".equals(flag)){//家居产业园
                    data.put("address", "家居产业园");
                    String text = SmsTemplate.buildText(SmsTemplate.ENTRY_TO_USER, data);
                    String text2 = SmsTemplate.buildText(SmsTemplate.ATTENTION_TO_USER, data);
                    SmsSender.sendSms(text,phone);
                    SmsSender.sendSms(text2,vphone);
                }else if ("2".equals(flag)){//武康南门
                    data.put("address", "临溪街588号");
                    String text = SmsTemplate.buildText(SmsTemplate.ENTRY_TO_USER, data);
                    String text2 = SmsTemplate.buildText(SmsTemplate.ATTENTION_TO_USER2, data);
                    SmsSender.sendSms(text,phone);
                    SmsSender.sendSms(text2,vphone);
                }else if ("3".equals(flag)){//供应中心
                    data.put("address", "丰庆街788号");
                    String text = SmsTemplate.buildText(SmsTemplate.ENTRY_TO_USER, data);
                    String text2 = SmsTemplate.buildText(SmsTemplate.ATTENTION_TO_USER2, data);
                    SmsSender.sendSms(text,phone);
                    SmsSender.sendSms(text2,vphone);
                }else if ("4".equals(flag)) {
                    data.put("address", "丰庆街701号");//701北门
                    String text = SmsTemplate.buildText(SmsTemplate.ENTRY_TO_USER, data);
                    String text2 = SmsTemplate.buildText(SmsTemplate.ATTENTION_TO_USER2, data);
                    SmsSender.sendSms(text,phone);
                    SmsSender.sendSms(text2,vphone);
                }
                model.addAttribute("flag",flag);
                return "admin/entry/imfo";
//            }
        }else{
            return "admin/entry/page2";
        }
    }

    @RequestMapping("/aa")
    public String aa(Model model,String aa)throws Exception{
        logger.info("aa des: " + this.des);
        if(this.des >= 1){
            return "admin/entry/show3";
        }
        //4.解密
        cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
        byte[] bb = cipher.doFinal(Hex.decodeHex(aa));
//        System.out.println(new String(bb));
        model.addAttribute("phone",new String(bb));
        return "admin/entry/show";
    }

    @RequestMapping("/show")
    public String show(HttpServletRequest request,HttpServletResponse response) throws Exception {
        logger.info("show des: " + this.des);
        String phone = request.getParameter("phone");
        if (StringUtils.isNotBlank(phone) && phone.length() != 11 || null == phone || this.des >= 1){
            return "admin/entry/show3";
        }
        String agree = request.getParameter("agree");
        String busy = request.getParameter("busy");
        String meet = request.getParameter("meet");
        String outside = request.getParameter("outside");
        if(StringUtils.isNotBlank(agree)){
            SmsSender.sendSms(SmsTemplate.buildText(SmsTemplate.QUICK_REPLY_AGREE,new HashMap<>()),phone);
        }else if (StringUtils.isNotBlank(busy)){
            SmsSender.sendSms(SmsTemplate.buildText(SmsTemplate.QUICK_REPLY,new HashMap<>()),phone);
        }else if (StringUtils.isNotBlank(meet)){
            SmsSender.sendSms(SmsTemplate.buildText(SmsTemplate.QUICK_REPLY_MEET,new HashMap<>()),phone);
        }else if (StringUtils.isNotBlank(outside)){
            SmsSender.sendSms(SmsTemplate.buildText(SmsTemplate.QUICK_REPLY_OUTSIDE,new HashMap<>()),phone);
        }
        this.des++;//0可以访问,大于等于1不能访问
        logger.info(String.valueOf(this.des));
        return "admin/entry/show2";
    }


    @RequestMapping("/datalist")
    @ResponseBody
    public Page<DhEntry> list(Model model,String insTime,String ineTime,String outTime,String outeTime,String flag,String vistor,String plateNo,String user) throws IOException {
        Map map=new HashMap<>();
        try {
            if(ineTime!=null&&ineTime!="") {
                ineTime = DateUtil.afterDay(ineTime);
            }
            if (ineTime == null || "".equals(ineTime)){
                ineTime = "2088-12-31";
            }
            if(vistor != null && !"".equals(vistor)){
                vistor = new String(vistor.getBytes("ISO-8859-1"),"UTF-8");
//                System.out.println("vistor: "+vistor);
            }
            if(plateNo != null && !"".equals(plateNo)){
                plateNo = new String(plateNo.getBytes("ISO-8859-1"),"UTF-8");
//                System.out.println("plateNo: "+plateNo);
//                plateNo = URLEncoder.encode(plateNo,"UTF-8");
//                plateNo = URLDecoder.decode(plateNo,"UTF-8");
            }
            if(user != null && !"".equals(user)){
                user = new String(user.getBytes("ISO-8859-1"),"UTF-8");
//                System.out.println("user: "+user);
//                user = URLEncoder.encode(user,"UTF-8");
//                user = URLDecoder.decode(user,"UTF-8");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        map.put("ineTime",ineTime);
        map.put("insTime",insTime);
        map.put("outTime",outTime);
        map.put("outeTime",outeTime);
        map.put("flag", flag);
        map.put("vistor", vistor);
        map.put("plateNo", plateNo);
        map.put("user", user);
        return dhEntryService.selectByPage(new Page<DhEntry>(0, 12),map);
        //return Page(new Page<Equipment>(0, 12),list);
        //SmsSender.sendSms(text,vphone);
    }
    @RequestMapping("/findMsg")
    @ResponseBody
    public String findMsg(Model model ) throws IOException {
        Map map=new HashMap<>();
        //map.put("username",1);
        map.put("msg_status","0");
        List<DhEntry> list=dhEntryService.selectByMap(map);
        if(list.size()>0){
            DhEntry entry=new DhEntry();
            entry.setMsgStatus("1");
            entry.setId(list.get(0).getId());
            dhEntryService.updateByid(entry);
            return list.get(0).getVistorPhone();

        }else{
        return "0";
        }
       // return dhEntryService.selectByPage(new Page<DhEntry>(0, 12),map);
        // return Page(new Page<Equipment>(0, 12),list);
        //SmsSender.sendSms(text,vphone);
    }
    @RequestMapping("/stopMsg")
    @ResponseBody
    public void stopMsg(Model model ) throws IOException {

        // return dhEntryService.selectByPage(new Page<DhEntry>(0, 12),map);
        // return Page(new Page<Equipment>(0, 12),list);
        //SmsSender.sendSms(text,vphone);
    }

    @RequestMapping("/cstatus")
    @ResponseBody
    public String cstatus(Model model,String id ) throws IOException {
        DhEntry entry=new DhEntry();
        entry.setStatus("1");
        entry.setId(id);
        entry.setOuttime(new Date());
        dhEntryService.updateByid(entry);
       // dhEntryService.
        // return dhEntryService.selectByPage(new Page<DhEntry>(0, 12),map);
        // return Page(new Page<Equipment>(0, 12),list);
        //SmsSender.sendSms(text,vphone);
        return "success";
    }


    @RequestMapping("/testadd")
    @ResponseBody
    public String test(Model model,String problem,String answer ) throws IOException {
        Test test=new Test();
        test.setAnswer(answer);
        test.setProblem(problem);
        dhEntryService.insertTest(test);
        // dhEntryService.
        // return dhEntryService.selectByPage(new Page<DhEntry>(0, 12),map);
        // return Page(new Page<Equipment>(0, 12),list);
        //SmsSender.sendSms(text,vphone);
        return "success";
    }

    @RequestMapping("/mytest")
    public String mytest(Model model,HttpServletResponse response) {
        return "admin/entry/test";
    }
    /**
     * 信息导出
     */
    @RequestMapping("/export")
    public void userListExport(HttpServletRequest request, HttpServletResponse response) {
        try {
            //指定文件生成路径
            String dir = request.getSession().getServletContext().getRealPath("/excelfile/user");
            //文件�????
            Date date=new Date();
            DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String expName = format.format(date) ;
            //表头信息
            String[] headName = {"被访人","到访人","车牌号","到访人身份证号码","到访人电话","入厂时间","离厂时间","入厂原因","所在地"};
            Map eq=new HashMap<>();
            String flag = request.getParameter("flag");
            eq.put("flag", flag);
            List<DhEntry> list= dhEntryService.selectByMap(eq);
            List<List<String>> elist = new ArrayList<List<String>>();
            for(DhEntry entry:list){
                List<String> small = new ArrayList<String>();
                small.add(entry.getUser());
                small.add(entry.getVistor());
                small.add(entry.getPlateNo());
                small.add(entry.getIdCard());
                small.add(entry.getVistorPhone());

                //DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                small.add(format.format(entry.getIntime()));
                if(entry.getOuttime()==null){
                    small.add("-");
                }else{
                    small.add(format.format(entry.getOuttime()));
                }
               // small.add(format.format(entry.getOuttime()));
                small.add(entry.getReason());
                if (StringUtils.isNotBlank(entry.getFlag())){
                    String address = entry.getFlag();
                    if ("1".equals(address)){
                        address = "家居产业园";
                    }else if ("2".equals(address)){
                        address = "武康南门";
                    }else if ("3".equals(address)){
                        address = "供应中心";
                    }else if("4".equals(address)){
                        address = "武康北门";
                    }
                    small.add(address);
                }else{
                    small.add("-");
                }
                elist.add(small);
            }
            // Page<Equipment> page= equipmentService.queryAll(new Page<Equipment>(0, 12),eq);
            List<File> srcfile = new ArrayList<File>();//生成的excel的文件的list
            List<DhEntry> eqList =new ArrayList<>();
            //L//ist<List<String>> list=new ArrayList<>();
            File file = FileExportImportUtil.createExcel(headName, elist, expName, dir);
            srcfile.add(file);

            response.setCharacterEncoding("utf-8");
            FileExportImportUtil.createRar(response, dir, srcfile, expName);//生成的多excel的压缩包
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送HttpPost请求
     *
     * @param strURL
     *            服务地址
     * @param params
     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
     * @return 成功:返回json字符串<br/>
     */
    public static String post(String strURL, String params) {
//        System.out.println(strURL);
//        System.out.println(params);
        BufferedReader reader = null;
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            // connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();

            //如果一定要使用如下方式接收响应数据， 则响应必须为: response.getWriter().print(StringUtils.join("{\"errCode\":\"1\",\"errMsg\":\"", message, "\"}")); 来返回
//            int length = (int) connection.getContentLength();// 获取长度
//            if (length != -1) {
//                byte[] data = new byte[length];
//                byte[] temp = new byte[512];
//                int readLen = 0;
//                int destPos = 0;
//                while ((readLen = is.read(temp)) > 0) {
//                    System.arraycopy(temp, 0, data, destPos, readLen);
//                    destPos += readLen;
//                }
//                String result = new String(data, "UTF-8"); // utf-8编码
//                System.out.println(result);
//                return result;
//            }

            return res;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }
}
