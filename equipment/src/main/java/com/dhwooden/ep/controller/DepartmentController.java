package com.dhwooden.ep.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.Department;
import com.dhwooden.ep.modal.Equipment;
import com.dhwooden.ep.modal.Tree;
import com.dhwooden.ep.service.DepartmentService;
import com.dhwooden.ep.util.EncryptUtils;
import com.dhwooden.ep.util.HtmlUtil;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.body.MultipartBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Key;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2018-04-17
 */
@Controller
@RequestMapping("/admin/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    private static Logger logger = LoggerFactory.getLogger(DepartmentController.class);


    @RequestMapping("/list")
    @ResponseBody
    public void queryAll(HttpServletResponse response,HttpServletRequest request){
        Map map =new HashMap();
        List<Department>  de=departmentService.selectByMap(map);
        List<Tree>  tree=new ArrayList<>();
        for(Department department:de){
            if ("云南区域".equals(department.getName()) && "101000".equals(department.getWeights()) ||
                    "广西区域".equals(department.getName()) && "101000".equals(department.getWeights()) ||
                    "贵州区域".equals(department.getName()) && "101000".equals(department.getWeights()) ||
                    "用户".equals(department.getName())){
                continue;
            }

            if(department.getParentId()!=null){
                Tree tree1=new Tree();
                tree1.setId(department.getId());
                tree1.setName(department.getName());
                tree1.setpId(department.getParentId());
                tree1.setDepartment(department.getDepartment());
                tree1.setOpen(false);
                tree.add(tree1);
            }else {

            }


        }
//        Tree ss=new Tree();
//        ss.setId("123");
//        ss.setName("test");
//        ss.setpId("0");
//        tree.add(ss);
        HtmlUtil.writerJson(response,tree);
    }


    private static String purl = "http://do.yunzhijia.com/openaccess/input/person/getall";
    private static  String durl = "http://do.yunzhijia.com/openaccess/input/dept/getall";


//    @Scheduled(cron = "0 0 0 * * ?")
    @RequestMapping("/syn")
    @ResponseBody
    public String syn() throws Exception {
        List<Department> departments=queryDepartment();
        departmentService.deleteAll();
        departmentService.insertBatch(departments);
        Map map=new HashMap<>();
        departmentService.selectByMap(map);
        String str = "department插入数据: " + departments.size() + "条!";
        logger.info(str);
        return str;
    }
    public String getResource() throws URISyntaxException {
        URI uri = this.getClass().getResource("/").toURI();
        String p = uri.getPath();
        String filepath=p + "/static/config/534488.key";
        return filepath;
    }

    public  List<Department> queryDepartment() throws Exception {

        String jss="{'eid':'"+534488+"'}";
        JsonNode jsonData = new JsonNode(jss);// jsondata  without encrypt

        String keyFile = getResource();
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
        List<Department>  list= JSONObject.parseArray(info, Department.class);


        return list;

    }

    @Scheduled(cron = "0 0 0 * * ?")
    @RequestMapping("/start")
    @ResponseBody
    public String getSyn() throws Exception{
        logger.info("department定时任务开启!");
        String syn = syn();
        return syn;
    }

	
}
