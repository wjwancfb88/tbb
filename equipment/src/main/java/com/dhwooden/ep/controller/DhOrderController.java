package com.dhwooden.ep.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhwooden.ep.controller.wx.OpenApiSDK;
import com.dhwooden.ep.modal.DhOrder;
import com.dhwooden.ep.service.DhOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by kaiwang on 2019/6/27.  BI-crm订单数据获取
 */
@RestController
@RequestMapping("/admin/order")
public class DhOrderController {
    @Autowired
    public DhOrderService dhOrderService;

    @RequestMapping({"/test"})
    public void test(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date time = calendar.getTime();

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                //System.out.println("start...");
                orderList();
                //System.out.println("end...");
            }
        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行1000 * 60 * 60 * 24
    }

    @RequestMapping({"/list"})
    public void orderList(){
        String url = "salesorder/list";
        int i = 1;
        int num = 0;
//        Timer t = new Timer();
//        t.schedule(new MyTimer(),TimerUitl.df.get().parse("2019-06-27 09:30:30"),10000);
        while (true) {
            Map<String, String> params = OpenApiSDK.baseApiParams();
            params.put("page_no",i+++"");
            params.put("user", "xx");
            params.put("password", OpenApiSDK.toMD5("xx".getBytes()));
            params.put("fields","orderId,orderName,orderType,accountId,orderDate,remark,total,paid,unpaid,invoiced,uninvoiced,owningUser,owningBusinessUnit,createdBy,createdOn,modifiedBy,modifiedOn,isDeleted,chanceId,jihuijindugengxin,c__baozhuangfangshi,c__shengchanjiaohuoqi,c__fujian,c__zhiliangyaoqiu,c__zongshuliangzhang,c__zongshulianglifang,c__zongshuliangjianshu,c__dizhi,c__dingdanleixing,c__bizhong,c__yufukuan");
            params.put("op_user","027-9b442b9a-d2be-4d46-93d2-0313593ec927");
            params.put("page_size","200");
            String apiUrl = OpenApiSDK.signUrl(url, params);
            JSONObject r = OpenApiSDK.apiGet(apiUrl);
            if (r.getJSONArray("data")!=null&&r.getJSONArray("data").isEmpty()){break;}
            JSONArray arr = r.getJSONArray("data");
            int len = arr.size();
            for (int j = 0; j < len; j++) {
                JSONObject obj = (JSONObject)arr.get(j);
                DhOrder dor = obj.toJavaObject(DhOrder.class);
                if (dor != null) {
                    //System.out.println(dor);
                    DhOrder dhOrder = dhOrderService.selectOrderById(dor.getOrderId());
                    if (dhOrder == null) {
                        dhOrderService.insert(dor);
                        num++;
                    }
                }
            }
        }
        //System.out.println("-----"+num+"-----");
    }

    private void accountList(){
        String url = "account/list";
        int i = 1;
        int num = 0;
//        Timer t = new Timer();
//        t.schedule(new MyTimer(),TimerUitl.df.get().parse("2019-06-27 09:30:30"),10000);
        while (true) {
            Map<String, String> params = OpenApiSDK.baseApiParams();
            params.put("page_no",i+++"");
            params.put("user", "xx");
            params.put("password", OpenApiSDK.toMD5("xx".getBytes()));
            params.put("fields","orderId,orderName,orderType,accountId,orderDate,remark,total,paid,unpaid,invoiced,uninvoiced,owningUser,owningBusinessUnit,createdBy,createdOn,modifiedBy,modifiedOn,isDeleted,chanceId,jihuijindugengxin,c__baozhuangfangshi,c__shengchanjiaohuoqi,c__fujian,c__zhiliangyaoqiu,c__zongshuliangzhang,c__zongshulianglifang,c__zongshuliangjianshu,c__dizhi,c__dingdanleixing,c__bizhong,c__yufukuan");
            params.put("op_user","027-9b442b9a-d2be-4d46-93d2-0313593ec927");
            params.put("page_size","200");
            String apiUrl = OpenApiSDK.signUrl(url, params);
            JSONObject r = OpenApiSDK.apiGet(apiUrl);
            if (r.getJSONArray("data")!=null&&r.getJSONArray("data").isEmpty()){break;}
            JSONArray arr = r.getJSONArray("data");
            int len = arr.size();
            for (int j = 0; j < len; j++) {
                JSONObject obj = (JSONObject)arr.get(j);
                DhOrder dor = obj.toJavaObject(DhOrder.class);
                if (dor != null) {
                    //System.out.println(dor);
                    DhOrder dhOrder = dhOrderService.selectOrderById(dor.getOrderId());
                    if (dhOrder == null) {
                        dhOrderService.insert(dor);
                        num++;
                    }
                }
            }
        }
        System.out.println("-----"+num+"-----");
    }
}
