package com.tbb.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tbb.entity.Cdmxmx;
import com.tbb.entity.Dd;
import com.tbb.entity.Wlyy;
import com.tbb.entity.Tqdj;
import com.tbb.service.ICdmxmxService;
import com.tbb.service.IDdService;
import com.tbb.service.ITqdjService;
import com.tbb.service.IWlyyService;
import com.tbb.util.OpenApiSDK;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cfb
 * @since 2020-04-02
 */
@Controller
@RequestMapping("/bi")
public class BIController {
    private static Logger logger = LoggerFactory.getLogger(BIController.class);

    @Autowired
    private IWlyyService wlyyService;

    @Autowired
    private ITqdjService tqdjService;

    @Autowired
    private IDdService ddService;

    @Autowired
    private ICdmxmxService cdmxmxService;

//    @Scheduled(cron = "0 30 * * * ?")//每天晚上12点执行"0 0 0 * * ?"
//    @Scheduled(cron = "*/5 * * * * ?")//每隔5秒执行一次
//    @Scheduled(fixedRate = 5000)//每隔5秒执行一次
    @RequestMapping({"/start"})
    public void start(){
//        logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        logger.info("任务开始执行!! --->" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        wlyy();//wlyy网络预约
        tqdj();//tqdj特权订金
        dd();//dd易装
//        cdmxmx();//拆单明细
        logger.info("任务执行完毕!! --->" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    @RequestMapping("/selectAll")
	public void selectAll(){
        List<Wlyy> eWlyy2s = wlyyService.selectByMap(new HashMap<>());
        logger.info(String.valueOf(eWlyy2s.size()));
    }

    @Scheduled(cron = "0 0 0 * * ?")//每天晚上12点执行"0 0 0 * * ?"
    @RequestMapping("wlyy")
    public void wlyy(){
        wlyyService.deleteAll();
        String url = "customentity/list";
        int i = 1;
        int i1 = 0;
        int i2 = 0;
        int num = 0;
        List<Wlyy> list = new ArrayList<Wlyy>();
        while (true) {
            Map<String, String> params = OpenApiSDK.baseApiParams();
            params.put("page_no",i+++"");
            params.put("entity", "e__wlyy");
            params.put("fields","c__biaoti,c__chengshi,c__dianshangbupanduanyixiang,c__dingzhichanpin,c__dingzhiyiyuan,c__fenpeizhuangtai,c__gendankefubeizhu,c__genjincishu,c__huifangkefubeizhu,c__huodong,c__kehu,c__kehudengji,c__kehudizhi,c__kehushouji,c__kehusuozaichengshi,c__kehuxingming,c__kehuxuqiu,c__laiyuan,c__lastAssignOn,c__leixing,c__sheng,c__shi,c__tuiguangpingtai,c__type,c__url,c__wlyyId,c__xianqu,c__xiaoqu,c__yixiangfenlei,c__yixiangjingxiaoshang,c__yusuanfanwei,c__zhuangtai,c__zhuangxiujieduan,c__zuijinchulishijian,c__zuijingenjinneirong,createdBy,createdOn,isDeleted,modifiedBy,modifiedOn,owningBusinessUnit,owningUser");
            params.put("op_user","027-60ae6f58-8463-46d2-8590-2a065fb1ac7f");
            params.put("page_size","200");
            String apiUrl = OpenApiSDK.signUrl(url, params);
            JSONObject r = OpenApiSDK.apiGet(apiUrl);
            if (r!=null&&r.getJSONArray("data").isEmpty()){break;}
            JSONArray arr = r.getJSONArray("data");
            int len = arr.size();
            i2 += len;
            for (int j = 0; j < len; j++) {
                JSONObject obj = (JSONObject)arr.get(j);
                Wlyy wlyy = new Wlyy();
                wlyy.setCDianshangbupanduanyixiang(obj.getString("c__dianshangbupanduanyixiang"));
                wlyy.setCZhuangtai(obj.getString("c__zhuangtai"));
                wlyy.setCDingzhichanpin(obj.getString("c__dingzhichanpin"));
                wlyy.setCKehusuozaichengshi(obj.getString("c__kehusuozaichengshi"));
                wlyy.setCLeixing(obj.getString("c__leixing"));
                wlyy.setCWlyyid(obj.getString("c__wlyyId"));
                wlyy.setCKehuxingming(obj.getString("c__kehuxingming"));
                wlyy.setCShi(obj.getString("c__shi"));
                wlyy.setCLastassignon(obj.getString("c__lastAssignOn"));
                wlyy.setCKehudizhi(obj.getString("c__kehudizhi"));
                wlyy.setCYixiangjingxiaoshang(obj.getString("c__yixiangjingxiaoshang"));
                wlyy.setCBiaoti(obj.getString("c__biaoti"));
                wlyy.setCDingzhiyiyuan(obj.getString("c__dingzhiyiyuan"));
                wlyy.setCreatedOn(obj.getString("createdOn"));
                wlyy.setCreatedBy(obj.getString("createdBy"));
                wlyy.setCGendankefubeizhu(obj.getString("c__gendankefubeizhu"));
                wlyy.setCKehuxuqiu(obj.getString("c__kehuxuqiu"));
                wlyy.setModifiedOn(obj.getString("modifiedOn"));
                wlyy.setCYixiangfenlei(obj.getString("c__yixiangfenlei"));
                wlyy.setIsDeleted(obj.getString("isDeleted"));
                wlyy.setCFenpeizhuangtai(obj.getString("c__fenpeizhuangtai"));
                wlyy.setCZuijinchulishijian(obj.getString("c__zuijinchulishijian"));
                wlyy.setModifiedBy(obj.getString("modifiedBy"));
                wlyy.setCUrl(obj.getString("c__url"));
                wlyy.setCKehudengji(obj.getString("c__kehudengji"));
                wlyy.setCSheng(obj.getString("c__sheng"));
                wlyy.setCGenjincishu(obj.getString("c__genjincishu"));
                wlyy.setCKehushouji(obj.getString("c__kehushouji"));
                wlyy.setOwningUser(obj.getString("owningUser"));
                wlyy.setCType(obj.getString("c__type"));
                wlyy.setCZhuangxiujieduan(obj.getString("c__zhuangxiujieduan"));
                wlyy.setCHuifangkefubeizhu(obj.getString("c__huifangkefubeizhu"));
                wlyy.setCZuijingenjinneirong(obj.getString("c__zuijingenjinneirong"));
                wlyy.setCXianqu(obj.getString("c__xianqu"));
                wlyy.setCChengshi(obj.getString("c__chengshi"));
                wlyy.setCHuodong(obj.getString("c__huodong"));
                wlyy.setCTuiguangpingtai(obj.getString("c__tuiguangpingtai"));
                wlyy.setCXiaoqu(obj.getString("c__xiaoqu"));
                wlyy.setCYusuanfanwei(obj.getString("c__yusuanfanwei"));
                wlyy.setCKehu(obj.getString("c__kehu"));
                wlyy.setCLaiyuan(obj.getString("c__laiyuan"));
                wlyy.setOwningBusinessUnit(obj.getString("owningBusinessUnit"));

                list.add(wlyy);
                num++;
            }
        }
        if (list.size() > 0){
            wlyyService.insertBatch(list);
        }
//        if (updateList.size() > 0){
//            wlyyService.updateBatchById(updateList);
//        }
        logger.info("wlyy总数据: " + i2 + "条!");
        logger.info("wlyy插入数据: " + num + "条! 更新数据: " + i1 + "条!");
    }

    @Scheduled(cron = "0 1 0 * * ?")//每天晚上12点执行"0 0 0 * * ?"
    @RequestMapping("dd")
    public void dd(){
        ddService.deleteAll();
        String url = "customentity/list";
        int i = 1;
        int i1 = 0;
        int num = 0;
        int len2 = 0;
        List<Dd> list = new ArrayList<Dd>();
//        List<Dd> updateList = new ArrayList<Dd>();
        while (true) {
            Map<String, String> params = OpenApiSDK.baseApiParams();
            params.put("page_no",i+++"");
            params.put("entity", "e__dd");
            params.put("fields","c__anzhuangdanshuliang,c__anzhuangfei,c__anzhuangrenyuan,c__baojiafujian,c__baozhuangyanse,c__beihuoqingkuang,c__beizhu,c__chaidanfujian,c__chaidanshenlingren,c__chaidanwanchengriqi,c__ddh,c__ddId,c__dingdanfujian,c__dingdanhao,c__dingdanleixing,c__dingdanzhuangtai,c__gtje,c__guitijine,c__jiagongzhongxin,c__jingxiaoshang,c__kehu,c__kehudianhua,c__kehudizhi,c__kehuxingming,c__paidanqingkuang,c__qitafei,c__shengchanwangongriqi,c__waidingmenfujian,c__waidingmenjine,c__wujinfujian,c__wujinpeijianjine,c__wuliuxinxi,c__xiangguanyizhuangfankuidan,c__yaoqiujiaohuoshijian,c__yunshufei,c__zongjine,c__zongmianji,createdBy,createdOn,currentFlowStepId,isDeleted,latestAuditer,latestAuditTime,modifiedBy,modifiedOn,nextAuditers,owningBusinessUnit,owningUser,recentTurnUsers,state");
            params.put("op_user","027-60ae6f58-8463-46d2-8590-2a065fb1ac7f");
            params.put("page_size","200");
            String apiUrl = OpenApiSDK.signUrl(url, params);
            JSONObject r = OpenApiSDK.apiGet(apiUrl);
            if (r!=null&&r.getJSONArray("data").isEmpty()){break;}
            JSONArray arr = r.getJSONArray("data");
            int len = arr.size();
            len2 += len;
            for (int j = 0; j < len; j++) {
                JSONObject obj = (JSONObject)arr.get(j);
                Dd dd = new Dd();
                dd.setCAnzhuangdanshuliang(obj.getString("c__anzhuangdanshuliang"));
                dd.setCAnzhuangfei(obj.getString("c__anzhuangfei"));
                dd.setCAnzhuangrenyuan(obj.getString("c__anzhuangrenyuan"));
                dd.setCBaojiafujian(obj.getString("c__baojiafujian"));
                dd.setCBaozhuangyanse(obj.getString("c__baozhuangyanse"));
                dd.setCBeihuoqingkuang(obj.getString("c__beihuoqingkuang"));
                dd.setCBeizhu(obj.getString("c__beizhu"));
                dd.setCChaidanfujian(obj.getString("c__chaidanfujian"));
                dd.setCChaidanshenlingren(obj.getString("c__chaidanshenlingren"));
                dd.setCChaidanwanchengriqi(obj.getString("c__chaidanwanchengriqi"));
                dd.setCDdh(obj.getString("c__ddh"));
                dd.setCDdid(obj.getString("c__ddId"));
                dd.setCDingdanfujian(obj.getString("c__dingdanfujian"));
                dd.setCDingdanhao(obj.getString("c__dingdanhao"));
                dd.setCDingdanleixing(obj.getString("c__dingdanleixing"));
                dd.setCDingdanzhuangtai(obj.getString("c__dingdanzhuangtai"));
                dd.setCGtje(obj.getString("c__gtje"));
                dd.setCGuitijine(obj.getString("c__guitijine"));
                dd.setCJiagongzhongxin(obj.getString("c__jiagongzhongxin"));
                dd.setCJingxiaoshang(obj.getString("c__jingxiaoshang"));
                dd.setCKehu(obj.getString("c__kehu"));
                dd.setCKehudianhua(obj.getString("c__kehudianhua"));
                dd.setCKehudizhi(obj.getString("c__kehudizhi"));
                dd.setCKehuxingming(obj.getString("c__kehuxingming"));
                dd.setCPaidanqingkuang(obj.getString("c__paidanqingkuang"));
                dd.setCQitafei(obj.getString("c__qitafei"));
                dd.setCShengchanwangongriqi(obj.getString("c__shengchanwangongriqi"));
                dd.setCWaidingmenfujian(obj.getString("c__waidingmenfujian"));
                dd.setCWaidingmenjine(obj.getString("c__waidingmenjine"));
                dd.setCWujinfujian(obj.getString("c__wujinfujian"));
                dd.setCWujinpeijianjine(obj.getString("c__wujinpeijianjine"));
                dd.setCWuliuxinxi(obj.getString("c__wuliuxinxi"));
                dd.setCXiangguanyizhuangfankuidan(obj.getString("c__xiangguanyizhuangfankuidan"));
                dd.setCYaoqiujiaohuoshijian(obj.getString("c__yaoqiujiaohuoshijian"));
                dd.setCYunshufei(obj.getString("c__yunshufei"));
                dd.setCZongjine(obj.getString("c__zongjine"));
                dd.setCZongmianji(obj.getString("c__zongmianji"));
                dd.setCreatedby(obj.getString("createdBy"));
                dd.setCreatedon(obj.getString("createdOn"));
                dd.setCurrentflowstepid(obj.getString("currentFlowStepId"));
                dd.setIsdeleted(obj.getString("isDeleted"));
                dd.setLatestauditer(obj.getString("latestAuditer"));
                dd.setLatestaudittime(obj.getString("latestAuditTime"));
                dd.setModifiedby(obj.getString("modifiedBy"));
                dd.setModifiedon(obj.getString("modifiedOn"));
                dd.setNextauditers(obj.getString("nextAuditers"));
                dd.setOwningbusinessunit(obj.getString("owningBusinessUnit"));
                dd.setOwninguser(obj.getString("owningUser"));
                dd.setRecentturnusers(obj.getString("recentTurnUsers"));
                dd.setState(obj.getString("state"));

//                Dd tempDd = ddService.selectById(dd.getCDdid());
//                if (tempDd == null){
                    list.add(dd);
                    num++;
//                }else{
//                    updateList.add(dd);
//                    i1++;
//                }
            }
        }
        if (list.size() > 0)
            ddService.insertBatch(list);
//        if (updateList.size() > 0)
//            ddService.updateBatchById(updateList);
        logger.info("dd总数据: " + len2 + "条!");
        logger.info("dd插入数据: " + num + "条! 更新数据: " + i1 + "条!");
    }

    @Scheduled(cron = "0 2 0 * * ?")//每天晚上12点执行"0 0 0 * * ?"
    @RequestMapping("tqdj")
    public void tqdj(){
        tqdjService.deleteAll();
        String url = "customentity/list";
        int i = 1;
        int i1 = 0;
        int num = 0;
        int len2 = 0;
        List<Tqdj> list = new ArrayList<Tqdj>();
//        List<Tqdj> updateList = new ArrayList<Tqdj>();
        while (true) {
            Map<String, String> params = OpenApiSDK.baseApiParams();
            params.put("page_no",i+++"");
            params.put("entity", "e__tqdj");
            params.put("fields","c__accountid,c__address,c__amount,c__consignee,c__dingdanhao,c__dingdanpanduanzhuangtai,c__dingzhichanpin,c__dingzhiyiyuan,c__fenpeizhuangtai,c__fujian,c__gendankefubeizhu,c__huifangkefubeizhu,c__kehudengji,c__kehusuozaichengshi,c__laiyuan,c__lastAssignOn,c__maijiahuiyuanming,c__message,c__orderdate,c__phone,c__remarks,c__shifouweijingxiaoshangdingdan,c__storename,c__tianmaoID,c__title,c__tqdjId,c__xghd,c__xgwlyy,c__xiaoqu,c__yusuanfanwei,c__yuyuemendian,c__zhuangtai,c__zhuangxiujieduan,c__zuijinchulishijian,createdBy,createdOn,isDeleted,modifiedBy,modifiedOn,owningBusinessUnit,owningUser");
            params.put("op_user","027-60ae6f58-8463-46d2-8590-2a065fb1ac7f");
            params.put("page_size","200");
            String apiUrl = OpenApiSDK.signUrl(url, params);
            JSONObject r = OpenApiSDK.apiGet(apiUrl);
            if (r!=null&&r.getJSONArray("data").isEmpty()){break;}
            JSONArray arr = r.getJSONArray("data");
            int len = arr.size();
            len2 += len;
            for (int j = 0; j < len; j++) {
                JSONObject obj = (JSONObject)arr.get(j);
                Tqdj tqdj = new Tqdj();
                tqdj.setCAccountid(obj.getString("c__accountid"));
                tqdj.setCAddress(obj.getString("c__address"));
                tqdj.setCAmount(obj.getString("c__amount"));
                tqdj.setCConsignee(obj.getString("c__consignee"));
                tqdj.setCDingdanhao(obj.getString("c__dingdanhao"));
                tqdj.setCDingdanpanduanzhuangtai(obj.getString("c__dingdanpanduanzhuangtai"));
                tqdj.setCDingzhichanpin(obj.getString("c__dingzhichanpin"));
                tqdj.setCDingzhiyiyuan(obj.getString("c__dingzhiyiyuan"));
                tqdj.setCFenpeizhuangtai(obj.getString("c__fenpeizhuangtai"));
                tqdj.setCFujian(obj.getString("c__fujian"));
                tqdj.setCGendankefubeizhu(obj.getString("c__gendankefubeizhu"));
                tqdj.setCHuifangkefubeizhu(obj.getString("c__huifangkefubeizhu"));
                tqdj.setCKehudengji(obj.getString("c__kehudengji"));
                tqdj.setCKehusuozaichengshi(obj.getString("c__kehusuozaichengshi"));
                tqdj.setCLaiyuan(obj.getString("c__laiyuan"));
                tqdj.setCLastassignon(obj.getString("c__lastAssignOn"));
                tqdj.setCMaijiahuiyuanming(obj.getString("c__maijiahuiyuanming"));
                tqdj.setCMessage(obj.getString("c__message"));
                tqdj.setCOrderdate(obj.getString("c__orderdate"));
                tqdj.setCPhone(obj.getString("c__phone"));
                tqdj.setCRemarks(obj.getString("c__remarks"));
                tqdj.setShifouweijingxiaoshangdingdan(obj.getString("c__shifouweijingxiaoshangdingdan"));
                tqdj.setCStorename(obj.getString("c__storename"));
                tqdj.setCTianmaoid(obj.getString("c__tianmaoID"));
                tqdj.setCTitle(obj.getString("c__title"));
                tqdj.setCTqdjid(obj.getString("c__tqdjId"));
                tqdj.setCXghd(obj.getString("c__xghd"));
                tqdj.setCXgwlyy(obj.getString("c__xgwlyy"));
                tqdj.setCXiaoqu(obj.getString("c__xiaoqu"));
                tqdj.setCYusuanfanwei(obj.getString("c__yusuanfanwei"));
                tqdj.setCYuyuemendian(obj.getString("c__yuyuemendian"));
                tqdj.setCZhuangtai(obj.getString("c__zhuangtai"));
                tqdj.setCZhuangxiujieduan(obj.getString("c__zhuangxiujieduan"));
                tqdj.setCZuijinchulishijian(obj.getString("c__zuijinchulishijian"));
                tqdj.setCreatedby(obj.getString("createdBy"));
                tqdj.setCreatedon(obj.getString("createdOn"));
                tqdj.setIsdeleted(obj.getString("isDeleted"));
                tqdj.setModifiedby(obj.getString("modifiedBy"));
                tqdj.setModifiedon(obj.getString("modifiedOn"));
                tqdj.setOwningbusinessunit(obj.getString("owningBusinessUnit"));
                tqdj.setOwninguser(obj.getString("owningUser"));

//                Tqdj tempTqdj = tqdjService.selectById(tqdj.getCTqdjid());
//                if (tempTqdj == null){
                    list.add(tqdj);
                    num++;
//                }else{
//                    i1++;
//                    updateList.add(tqdj);
//                }
            }
        }
        if (list.size() > 0)
            tqdjService.insertBatch(list);
//        if (updateList.size() > 0)
//            tqdjService.updateBatchById(updateList);
        logger.info("tqdj总数据: " + len2 + "条!");
        logger.info("tqdj插入数据: " + num + "条! 更新数据: " + i1 + "条!");
    }

    //@Scheduled(cron = "0 0 * * * ?")//每天晚上12点执行"0 0 0 * * ?"
    @RequestMapping("cdmxmx")
    public void cdmxmx(){
        logger.info("start!");
        cdmxmxService.deleteAll(getDay());
        logger.info("delete over! 删除时间大于: " + getDay());
        String url = "entity/adv-query";
        int i = 1;
        int num = 0;
        int len3 = 0;
        List<Cdmxmx> list = new ArrayList<Cdmxmx>();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        jsonObject2.put("fieldName","c__mx1Id.createdOn");
        jsonObject2.put("operator", ">");
        jsonObject2.put("value",getDay());
        jsonObject3.put("fieldName","c__mianbanyanse");
        jsonObject3.put("operator", "$NOT_NULL$");
        jsonObject3.put("value","");
        List<JSONObject> lists = new ArrayList<JSONObject>();
        lists.add(jsonObject2);
        lists.add(jsonObject3);
        jsonObject.put("type", "AND");
        jsonObject.put("filters", lists);
        while (true) {
            Map<String, String> params = OpenApiSDK.baseApiParams();
            params.put("page_no",i+++"");//起始页
            params.put("entity", "e__cdmxmx");//实体表
            params.put("fields","c__cdmxmxId,c__mx1Id,c__mx1Id.createdOn,c__banmianjim,c__mianbanyanse,c__banhoumm");//字段
            params.put("page_size","200");//每页记录数
            String apiUrl = OpenApiSDK.signUrl(url, params);
            JSONObject r = OpenApiSDK.apiPost(apiUrl,jsonObject.toJSONString());
            if (r != null && r.getJSONArray("data").isEmpty()){
                if (list.size() > 0){
                    long s = System.currentTimeMillis();
                    cdmxmxService.insertOrUpdateBatch(list);
                    long e = System.currentTimeMillis();
                    logger.info("插入"+list.size()+"条数据用时:" + (int)(e - s)/1000 + "秒.当前数据: " + num + "条");
                    list.clear();
                }
                break;
            }//查不到数据跳出循环
            JSONArray arr = r.getJSONArray("data");
            int len = arr.size();
            len3 += len;
            for (int j = 0; j < len; j++) {
                JSONObject obj = (JSONObject)arr.get(j);
                    Cdmxmx cdmxmx = new Cdmxmx();
                    cdmxmx.setCdmxmxId(obj.getString("c__cdmxmxId"));
                    cdmxmx.setMx1Id(obj.getString("c__mx1Id"));
                    if(StringUtils.isBlank(obj.getString("c__banmianjim"))){
                        cdmxmx.setBanmianjim(new BigDecimal(0.000));
                    }else{
                        cdmxmx.setBanmianjim(obj.getBigDecimal("c__banmianjim"));
                    }
                    cdmxmx.setMianbanyanse(obj.getString("c__mianbanyanse"));
                    cdmxmx.setBanhoumm(obj.getString("c__banhoumm"));
                    cdmxmx.setCreatedOn(obj.getString("c__mx1Id.createdOn"));
                    list.add(cdmxmx);
                    num++;
//                    logger.info(num+"条数据");
            }
            if (list.size() %10000 == 0){
                long s = System.currentTimeMillis();
                cdmxmxService.insertBatch(list);
                long e = System.currentTimeMillis();
                logger.info("插入"+list.size()+"条数据用时:" + (int)(e - s)/1000 + "秒.当前数据: " + num + "条");
                list.clear();
            }
        }
//        long s = System.currentTimeMillis();
//        cdmxmxService.insertOrUpdateBatch(list);
//        long e = System.currentTimeMillis();
//        logger.info("插入"+list.size()+"条数据用时:" + (int)(e - s)/1000 + "秒.当前数据: " + num + "条");
//        list.clear();
        logger.info("cdmxmx总数据: " + len3 + "条!");
        logger.info("cdmxmx插入数据: " + num + "条!");
    }

    public static void main(String[] args) {
//        System.out.println(getDay());

//        long current=System.currentTimeMillis();//当前时间毫秒数
//        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
//        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
//        long yesterday=System.currentTimeMillis()-24*60*60*1000;//昨天的这一时间的毫秒数
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date(current);
//        Date date2 = new Date(yesterday);
//        Date date3 = new Date(zero);
//        Date date4 = new Date(twelve);
//
//        System.out.println(sdf.format(date));//当前时间
//        System.out.println(sdf.format(date2));//昨天这一时间点
//        System.out.println(sdf.format(date3));//今天零点零分零秒
//        System.out.println(sdf.format(date4));//今天23点59分59秒
    }

    public static String getDay(){
        Calendar now = Calendar.getInstance();
        long nowTimeInMillis = now.getTimeInMillis();
        long day = 86400000L * 12;//当天数据
        long zero = nowTimeInMillis / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long time = zero - day - 1;
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);//当前时间
        return format;
    }
}
