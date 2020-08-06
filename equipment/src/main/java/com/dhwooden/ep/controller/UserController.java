package com.dhwooden.ep.controller;


import com.alibaba.fastjson.JSONObject;
import com.dhwooden.ep.modal.DhLogin;
import com.dhwooden.ep.modal.Equipment;
import com.dhwooden.ep.modal.User;
import com.dhwooden.ep.service.DhLoginService;
import com.dhwooden.ep.service.UserService;
import com.dhwooden.ep.util.EncryptUtils;
import com.dhwooden.ep.util.PostJson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.body.MultipartBody;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2018-04-18
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {
    private final Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

//    @Autowired
//    private DhLoginService dhLoginService;

    @RequestMapping("/login")
    public String login(String deptId) {
        logger.info("开始登陆");
        Subject currentUser = SecurityUtils.getSubject();
        // 验证用户是否验证，即是否登录
        if (!currentUser.isAuthenticated()) {
            String msg = "";
            // 把用户名和密码封装为 UsernamePasswordToken 对象

            UsernamePasswordToken token = new UsernamePasswordToken("admin","admin");

            // remembermMe记住密码
            token.setRememberMe(true);
            try {
                // 执行登录.
                currentUser.login(token);
                logger.info("登陆完成，获得角色信息");
                // 登录成功...
                return "login";
               // return "/common/frame";
            } catch (IncorrectCredentialsException e) {
                msg = "登录密码错误";
                System.out.println("登录密码错误!!!" + e);
                //return "redirect:/admin/login/index";
            } catch (ExcessiveAttemptsException e) {
                msg = "登录失败次数过多";

                System.out.println("登录失败次数过多!!!" + e);
                //return "redirect:/admin/login/index";
            } catch (LockedAccountException e) {
                msg = "帐号已被锁定";
                System.out.println("帐号已被锁定!!!" + e);
                //return "redirect:/admin/login/index";
            } catch (DisabledAccountException e) {
                msg = "帐号已被禁用";
                System.out.println("帐号已被禁用!!!" + e);
                //return "redirect:/admin/login/index";
            } catch (ExpiredCredentialsException e) {
                msg = "帐号已过期";
                System.out.println("帐号已过期!!!" + e);
                //return "redirect:/admin/login/index";
            } catch (UnknownAccountException e) {
                msg = "帐号不存在";
                System.out.println("帐号不存在!!!" + e);
                //return "redirect:/admin/login/index";
            } catch (UnauthorizedException e) {
                msg = "您没有得到相应的授权！";
                System.out.println("您没有得到相应的授权！" + e);
                //return "redirect:/admin/login/index";
            } catch (Exception e) {
                System.out.println("出错！！！" + e);
                //return "redirect:/admin/login/index";
            }

        }
        return "2";
    }

//    @RequestMapping("/index")
//    public String loginIndex(String deptId) {
//
//        return "/admin/login/content";
//    }

    @RequiresPermissions("entry:list")
    @RequestMapping("/testRole")
    @ResponseBody
    public String testShiro(String deptId) {

        return "test";
    }

    @RequiresPermissions("entry:delete")
    @RequestMapping("/testRole2")
    @ResponseBody
    public String testShiro2(String deptId) {

        return "test2";
    }

    @RequestMapping("/queryByDept")
    @ResponseBody
    public List<User> queryByDept(String deptId){
        Map map =new HashMap();
        map.put("org_id",deptId);
        List<User>  de=userService.selectByMap(map);
        return de;
    }

    @RequestMapping("/findByName")
    @ResponseBody
    public List<User> queryByName(String name){
        Map map =new HashMap();
        map.put("name",name);
        List<User>  de=userService.selectEmpByName(map);
        return de;
    }

    private static  String purl = "http://do.yunzhijia.com/openaccess/input/person/getall";
    private static  String durl = "http://do.yunzhijia.com/openaccess/input/dept/getall";
    private static  int start=0;

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

//    @Scheduled(cron = "0 0 0 * * ?")
    @RequestMapping("/syn")
    @ResponseBody
    public String syn() throws Exception {
        userService.deleteAll();
        List<User> users1=queryPerson(start);
        int i = 0;
        while (true){
            i += 1000;
            List<User> users = queryPerson(i);
            users1.addAll(users);
            if (users.size() %1000 != 0){
                break;
            }
        }

//        int insertNum = 0;
//        int updateNum = 0;
//        for (User user:users1){
//            User user1 = userService.selectByOpenId(user.getOpenId());
//            if (null == user1){
//                userService.insert(user);
//                insertNum++;
//            }else{
//                userService.updateByModal(user1);
//                updateNum++;
//            }
//        }

        userService.insertBatch(users1);

        String str = "user总数据: " + users1.size() + "条!";
        logger.info(str);
        return str;
    }

    public List<User> queryPerson(int start) throws Exception {
        String jss="{'begin':"+start+"}";
        JsonNode jsonData = new JsonNode(jss);// json data without encrypt
        String keyFile = "E:\\\\534488.key" ;
        byte[] keyByte = EncryptUtils.getBytesFromFile(keyFile);
        Key key = EncryptUtils.restorePrivateKey(keyByte);
        String s= EncryptUtils.encryptWithEncodeBase64UTF8(
                jsonData.toString(), key);
        MultipartBody body= Unirest
                .post(purl)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("eid", "534488")
                .field("nonce", UUID.randomUUID().toString())
                .field("data",EncryptUtils.encryptWithEncodeBase64UTF8(jsonData.toString(), key));
        HttpResponse<JsonNode> a=body.asJson();

        JsonNode js=a.getBody();
        org.json.JSONObject jon=js.getObject();
        String info=jon.get("data").toString();
        List array= JSONObject.parseArray(info);
        List<User>  arra= JSONObject.parseArray(info, User.class);
        return arra;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @RequestMapping("/start")
    @ResponseBody
    public String getSyn() throws Exception{
        logger.info("user定时任务开启!");
        String syn = syn();
        return syn;
    }

}

