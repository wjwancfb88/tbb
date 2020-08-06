package com.dhwooden.ep.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhwooden.ep.controller.wx.OpenApiSDK;
import com.dhwooden.ep.modal.DhOrder;
import com.dhwooden.ep.modal.WLYY;
import com.dhwooden.ep.service.DhOrderService;
import com.dhwooden.ep.service.DhWlyyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by kaiwang on 2019/6/27.  BI-crm网络预约数据获取
 */
@RestController
@RequestMapping("/admin/wlyy")
public class DhWlyyController {
    @Autowired
    public DhWlyyService dhWlyyService;

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
                wlyyList();
                //System.out.println("end...");
            }
        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行1000 * 60 * 60 * 24
    }

    @RequestMapping({"/list"})
    public void wlyyList(){
        dhWlyyService.deleteAll();
        String url = "customentity/list";
        int i = 1;
        int num = 0;
        List<WLYY> list = new ArrayList<WLYY>();
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf.format(date);
//        Timer t = new Timer();
//        t.schedule(new MyTimer(),TimerUitl.df.get().parse("2019-06-27 09:30:30"),10000);
        while (true) {
            Map<String, String> params = OpenApiSDK.baseApiParams();
            params.put("page_no",i+++"");
//            params.put("user", "xx");
//            params.put("password", OpenApiSDK.toMD5("xx".getBytes()));
            params.put("entity", "e__wlyy");
            params.put("fields","c__biaoti,c__chengshi,c__dianshangbupanduanyixiang,c__dingzhichanpin,c__dingzhiyiyuan,c__fenpeizhuangtai,c__gendankefubeizhu,c__genjincishu,c__huifangkefubeizhu,c__huodong,c__kehu,c__kehudengji,c__kehudizhi,c__kehushouji,c__kehusuozaichengshi,c__kehuxingming,c__kehuxuqiu,c__laiyuan,c__lastAssignOn,c__leixing,c__sheng,c__shi,c__tuiguangpingtai,c__type,c__url,c__wlyyId,c__xianqu,c__xiaoqu,c__yixiangfenlei,c__yixiangjingxiaoshang,c__yusuanfanwei,c__zhuangtai,c__zhuangxiujieduan,c__zuijinchulishijian,c__zuijingenjinneirong,createdBy,createdOn,isDeleted,modifiedBy,modifiedOn,owningBusinessUnit,owningUser");
//            params.put("fields","c__biaoti");
//            params.put("op_user","027-9b442b9a-d2be-4d46-93d2-0313593ec927");
            params.put("op_user","027-60ae6f58-8463-46d2-8590-2a065fb1ac7f");
            params.put("page_size","200");
            String apiUrl = OpenApiSDK.signUrl(url, params);
            JSONObject r = OpenApiSDK.apiGet(apiUrl);
            if (r.getJSONArray("data")!=null&&r.getJSONArray("data").isEmpty()){break;}
            JSONArray arr = r.getJSONArray("data");
            int len = arr.size();
            for (int j = 0; j < len; j++) {
                JSONObject obj = (JSONObject)arr.get(j);
                WLYY wlyy = new WLYY();
                wlyy.setDianshangbupanduanyixiang(obj.getString("c__dianshangbupanduanyixiang"));
                wlyy.setZhuangtai(obj.getString("c__zhuangtai"));
                wlyy.setDingzhichanpin(obj.getString("c__dingzhichanpin"));
                wlyy.setKehusuozaichengshi(obj.getString("c__kehusuozaichengshi"));
                wlyy.setLeixing(obj.getString("c__leixing"));
                wlyy.setWlyyId(obj.getString("c__wlyyId"));
                wlyy.setKehuxingming(obj.getString("c__kehuxingming"));
                wlyy.setShi(obj.getString("c__shi"));
                wlyy.setLastAssignOn(obj.getString("c__lastAssignOn"));
                wlyy.setKehudizhi(obj.getString("c__kehudizhi"));
                wlyy.setYixiangjingxiaoshang(obj.getString("c__yixiangjingxiaoshang"));
                wlyy.setBiaoti(obj.getString("c__biaoti"));
                wlyy.setDingzhiyiyuan(obj.getString("c__dingzhiyiyuan"));
                wlyy.setCreatedOn(obj.getString("createdOn"));
                wlyy.setCreatedBy(obj.getString("createdBy"));
                wlyy.setGendankefubeizhu(obj.getString("c__gendankefubeizhu"));
                wlyy.setKehuxuqiu(obj.getString("c__kehuxuqiu"));
                wlyy.setModifiedOn(obj.getString("modifiedOn"));
                wlyy.setYixiangfenlei(obj.getString("c__yixiangfenlei"));
                wlyy.setIsDeleted(obj.getString("isDeleted"));
                wlyy.setFenpeizhuangtai(obj.getString("c__fenpeizhuangtai"));
                wlyy.setZuijinchulishijian(obj.getString("c__zuijinchulishijian"));
                wlyy.setModifiedBy(obj.getString("modifiedBy"));
                wlyy.setUrl(obj.getString("c__url"));
                wlyy.setKehudengji(obj.getString("c__kehudengji"));
                wlyy.setSheng(obj.getString("c__sheng"));
                wlyy.setGenjincishu(obj.getString("c__genjincishu"));
                wlyy.setKehushouji(obj.getString("c__kehushouji"));
                wlyy.setOwningUser(obj.getString("owningUser"));
                wlyy.setType(obj.getString("c__type"));
                wlyy.setZhuangxiujieduan(obj.getString("c__zhuangxiujieduan"));
                wlyy.setHuifangkefubeizhu(obj.getString("c__huifangkefubeizhu"));
                wlyy.setZuijingenjinneirong(obj.getString("c__zuijingenjinneirong"));
                wlyy.setXianqu(obj.getString("c__xianqu"));
                wlyy.setChengshi(obj.getString("c__chengshi"));
                wlyy.setHuodong(obj.getString("c__huodong"));
                wlyy.setTuiguangpingtai(obj.getString("c__tuiguangpingtai"));
                wlyy.setXiaoqu(obj.getString("c__xiaoqu"));
                wlyy.setYusuanfanwei(obj.getString("c__yusuanfanwei"));
                wlyy.setKehu(obj.getString("c__kehu"));
                wlyy.setLaiyuan(obj.getString("c__laiyuan"));
                wlyy.setOwningBusinessUnit(obj.getString("owningBusinessUnit"));

//                WLYY tempWlyy = dhWlyyService.selectWlyyById(wlyy.getWlyyId());
//                if (tempWlyy == null) {
                    list.add(wlyy);
                    num++;
//                }
            }
        }
        dhWlyyService.insertBatch(list);
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
                WLYY dor = obj.toJavaObject(WLYY.class);
                if (dor != null) {
                    //System.out.println(dor);
                    WLYY wlyy = dhWlyyService.selectWlyyById(dor.getWlyyId());
                    if (wlyy == null) {
                        dhWlyyService.insert(dor);
                        num++;
                    }
                }
            }
        }
        System.out.println("-----"+num+"-----");
    }
}
