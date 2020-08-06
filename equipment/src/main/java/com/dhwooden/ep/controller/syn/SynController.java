package com.dhwooden.ep.controller.syn;


import com.alibaba.fastjson.JSONObject;
import com.dhwooden.ep.modal.Department;
import com.dhwooden.ep.modal.Equipment;
import com.dhwooden.ep.modal.User;
import com.dhwooden.ep.service.DepartmentService;
import com.dhwooden.ep.service.EquipmentService;
import com.dhwooden.ep.service.UserService;


import com.dhwooden.ep.util.EncryptUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.body.MultipartBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2018-04-13
 */
@Controller
@RequestMapping("/user")
public class SynController {
    private static String purl = "http://do.yunzhijia.com/openaccess/input/person/getall";
    private static  String durl = "http://do.yunzhijia.com/openaccess/input/dept/getall";
    private static  int start=0;
    private static  int end=1000;
    @Autowired
    private UserService userService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
     //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    @RequestMapping("/syn")
    public void syn() throws Exception {
        List<User> users1=queryPerson(start);
        List<User> users2=queryPerson(end);
        users1.addAll(users2);
        //userService.deleteAll();
        Map map=new HashMap<>();
        List<User> list=userService.selectByMap(map);
        for(User user:users1){
            Boolean tf=false;
            for(User synuser:list){
                if(synuser.getPhone().equals(user.getPhone())){
                    synuser.setId(user.getId());
                    synuser.setOrgId(user.getOrgId());
                    userService.updateById(synuser);
                    Map equipmentMap=new HashMap<>();
                    equipmentMap.put("user",user.getId());

                    List<Equipment> li=equipmentService.selectByMap(equipmentMap);
                    for(Equipment eq:li){
                        eq.setUser(user.getId());
                        Department dlist= departmentService.selectById(user.getOrgId());
                        eq.setDepartment(dlist.getDepartment());
                        equipmentService.updateById(eq);
                    }


                    tf=true;
                    break;
                   // tf=true;
                }
            }
            if(tf==false){
                userService.insert(user);
            }


        }




    }

    public List<User> queryPerson(int start) throws Exception {
        String jss="{'begin':"+start+"}";
        JsonNode jsonData = new JsonNode(jss);// json data without encrypt
        String path = ResourceUtils.getURL("classpath:").getPath()+"/static/yzj/534488.key";
        byte[] keyByte = EncryptUtils.getBytesFromFile(path);
        Key key = EncryptUtils.restorePrivateKey(keyByte);
        String s= EncryptUtils.encryptWithEncodeBase64UTF8(
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
        List<User>  arra= JSONObject.parseArray(info, User.class);
        return arra;
    }
	
}
