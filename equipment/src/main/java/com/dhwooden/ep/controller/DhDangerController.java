package com.dhwooden.ep.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.*;
import com.dhwooden.ep.service.*;
import com.dhwooden.ep.util.HtmlUtil;
import com.dhwooden.ep.yzj.*;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 *  前端控制�??
 * </p>
 *
 * @author wk
 * @since 2018-09-20
 */
@Controller
@RequestMapping("/admin/danger")
public class DhDangerController {
    private final Logger logger= LoggerFactory.getLogger(DhDangerController.class);
    @Autowired
    private DhDealerService dhDealerService;
    @Autowired
    private DhDangerService dhDangerService;
    @Autowired
    private DhDangerDealService dhDangerDealService;

    @Autowired
    private DhLoginService dhLoginService;
    @Autowired
    private DhManagerService dhManager;
    @Autowired
    private DhOpinionService dhOpinionService;
    @Autowired
    private DhDsdeptService dhDsdeptService;

    public static YzjApp MOBILE_CUSTOMER = new YzjApp("500065577", "4DHJzOmDMVmAko7tkbRE");

    @RequestMapping("/index")
    public String mainIndex(Model model,HttpServletResponse response,HttpServletRequest request) throws Exception {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || ip.indexOf(":") > -1) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                ip = null;
            }
        }

        String ticket = request.getParameter("ticket");
        logger.info("YZJ ticket:"+ticket);
        if(StringUtils.isBlank(ticket)){
            model.addAttribute("loginErrorMsg","ticket为空" );
            return "error";
        }

        YzjResult<AccessTokenData> accessTokenResult = this.MOBILE_CUSTOMER.getAccessToken();

        logger.info("accessTokenResult:"+accessTokenResult);


        if(!accessTokenResult.isSuccess()){
            model.addAttribute("loginErrorMsg", accessTokenResult);
            return "errot";
        }

        String accessToken = accessTokenResult.getData().getAccess_token();
        UserContextData userContextData = YzjUtils.getUserContextData(accessToken, ticket);
        YzjResult<AccessTokenData> result=YzjUtils.getAccessToken("500065577","4DHJzOmDMVmAko7tkbRE");
        String accesstoken= result.getData().getAccess_token();
        UserContext usercontext = YzjUtils.getUserContext(ticket, accesstoken);
        String oprenid=usercontext.getOpenid();
        String eid=usercontext.getEid();
        String userInfo= YzjUtils.getUserInfo(oprenid, eid);
        JSONArray ob= JSONArray.parseArray(userInfo);
        String simpleInfo=ob.getString(0);
        JSONObject job=JSONObject.parseObject(simpleInfo);
        String jobno=job.getString("jobNo");
        request.getSession().setAttribute("userContextData",userContextData);
        request.getSession().setAttribute("jobno",jobno);
        logger.info("userContextData:"+userContextData);
        return "/admin/danger/danger";

    }

    @RequestMapping("/testip")
    public void testIp(Model model,HttpServletResponse response,HttpServletRequest request) throws Exception {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || ip.indexOf(":") > -1) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                ip = null;
            }
        }
        if(null != ip && ip.length() > 15){
            if(ip.indexOf(",") > 0){
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        logger.info("ip:"+ip);

    }

    /**
     *跳转隐患新增界面
     * @param model
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/add")
    public String index(Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        UserContextData userContextData= (UserContextData) request.getSession().getAttribute("userContextData");
        String jobno= (String) request.getSession().getAttribute("jobno");
        request.getSession().setAttribute("jobno",jobno);
        dhDealerService.selectById(userContextData.getOpenid());
        return "/admin/danger/addList";

    }


    /**
     *显示隐患整改界面
     * @param model
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/deal")
    public String dealIndex(Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        List<DhDanger> dangerList=new ArrayList<>();
        Map tj= new HashMap();
        tj.put("danger_Status","4");
        String jobno= (String) request.getSession().getAttribute("jobno");
        Map rolemap =new HashMap<>();
        rolemap.put("job_no",jobno);
        List<DhManager> list=dhManager.selectByMap(rolemap);
        tj.put("duty_dept",list.get(0).getDept());
        tj.put("danger_Dealer",list.get(0).getJobNo());
        dangerList=dhDangerService.selectByMap(tj);
        model.addAttribute("dangerList",dangerList);
        return "/admin/danger/deal";

    }
    @RequestMapping("/mangerlist")
    @ResponseBody
    public Map mangerList(String wzp,String whq,Integer pageNumber,Integer limit,Model model, HttpServletResponse response,HttpServletRequest request,String id) throws Exception {
        String ticket = (String) request.getSession().getAttribute("ticket");
        Map map=new HashMap<>();
        if(ticket!=null){
            YzjResult<AccessTokenData> result=YzjUtils.getAccessToken("500065577","4DHJzOmDMVmAko7tkbRE");
            String accesstoken= result.getData().getAccess_token();
            UserContext usercontext = YzjUtils.getUserContext(ticket, accesstoken);
            String oprenid=usercontext.getOpenid();
            String eid=usercontext.getEid();
            String userInfo= YzjUtils.getUserInfo(oprenid, eid);
            JSONArray ob= JSONArray.parseArray(userInfo);
            String simpleInfo=ob.getString(0);
            JSONObject job=JSONObject.parseObject(simpleInfo);
            String jobno=job.getString("jobNo");
            Map rolemap=new HashMap<>();
            rolemap.put("job_no",jobno);
            //map.put("pwd",pwd);
            List<DhManager> list=dhManager.selectByMap(rolemap);
            //如果录入人是经理或�?�行政人�??
            if(list.size()>0&&list.get(0).getRole().equals("2")){
                    Map<String,Object> info=new HashMap<String, Object>();
                if(id!=null) {
                    map.put("duty_dept", id);

                }
                if(wzp!=null){
                    map.put("danger_status","2");
                }
                com.github.pagehelper.Page<DhDanger> mypage= PageHelper.startPage(pageNumber, limit);
                List<DhDanger> page = dhDangerService.selectByMap(map);
                 if(whq!=null){
                 Iterator<DhDanger> it = page.iterator();
                 while(it.hasNext()){
                    DhDanger x = it.next();
                    if(x.getDangerStatus().equals("1")||x.getDangerStatus().equals("3")||x.getDangerStatus().equals("4")||x.getIsNine().equals("0")){
                        it.remove();
                    }
                }
                }

                info.put("rows",page);
                info.put("total",mypage.getTotal());
                return info;
            }else if(list.get(0).getRole().equals("1")){
                //如果是分公司老�?? 只显示待分配记录和本公司记录
//                map.put("dangerStatus","2");
                String dept=list.get(0).getDept();
                map.put("duty_Dept",dept);
                map.put("is_nine","0");
                map.put("now_manager",jobno);
                Map<String,Object> info=new HashMap<String, Object>();
                com.github.pagehelper.Page<DhDanger> mypage= PageHelper.startPage(pageNumber, limit);
                List<DhDanger> page = dhDangerService.selectByMap(map);
                info.put("rows",page);
                info.put("total",mypage.getTotal());
                return info;
            } else if(list.get(0).getRole().equals("0")){
                //如果是分公司经理 显示提交会签功能
//                map.put("dangerStatus","4");
                String dept=list.get(0).getDept();
                map.put("is_nine", "0");
                Map<String,Object> info=new HashMap<String, Object>();
                com.github.pagehelper.Page<DhDanger> mypage= PageHelper.startPage(pageNumber, limit);
                List<DhDanger> page = dhDangerService.selectByMap(map);
                info.put("rows",page);
                info.put("total",mypage.getTotal());
                Iterator<DhDanger> it = page.iterator();
                while(it.hasNext()){
                    DhDanger x = it.next();
                    if(x.getDangerStatus().equals("3")){
                        it.remove();
                    }
                }

                return info;
            }
        }


        Map<String,Object> info=new HashMap<String, Object>();
        List<DhDanger> danger= dhDangerService.selectByMap(map);
        info.put("rows",danger);
        info.put("total", danger.size());
        return info;


    }

    @RequestMapping("/findMangerLeader")
    @ResponseBody
    public List<DhManager> findMangerLeader(String role,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        Map rolemap =new HashMap<>();
        rolemap.put("role",role);
        List<DhManager> list=dhManager.selectByMap(rolemap);
        return list;

    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String id,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
       dhDangerService.deleteById(id);
        return "success";
    }

    @RequestMapping("/manger")
    public String manger(Model model,HttpServletResponse response,HttpServletRequest request) throws Exception {
        String ticket = request.getParameter("ticket");
        YzjResult<AccessTokenData> result=YzjUtils.getAccessToken("500065577","4DHJzOmDMVmAko7tkbRE");
        String accesstoken= result.getData().getAccess_token();
        UserContext usercontext = YzjUtils.getUserContext(ticket, accesstoken);
        String openid=usercontext.getOpenid();
        String eid=usercontext.getEid();
        request.getSession().setAttribute("ticket",ticket);
        request.getSession().setAttribute("openId",openid);
        request.getSession().setAttribute("eid",eid);
        String userInfo= YzjUtils.getUserInfo(openid, eid);
        JSONArray ob= JSONArray.parseArray(userInfo);
        String simpleInfo=ob.getString(0);
        JSONObject job=JSONObject.parseObject(simpleInfo);
        String jobno=job.getString("jobNo");
        request.getSession().setAttribute("jobNo",jobno);
        Map rolemap=new HashMap<>();
        rolemap.put("job_no",jobno);
        //map.put("pwd",pwd);
        List<DhManager> list=dhManager.selectByMap(rolemap);
        request.setAttribute("role",list.get(0).getRole());
        if(list.get(0).getRole().equals("2")){
            model.addAttribute("role","2");
            request.setAttribute("role",2);
            return "/admin/danger/dangerManger";
        }
        else{
            return "/admin/danger/allDanger";
        }
        //model.addAttribute("dangerList",dangerList);


    }

    @RequestMapping("/dealDanger")
    public String dealDanger(Model model,HttpServletResponse response,String id) throws IOException {
        List<DhDanger> dangerList=new ArrayList<>();
        DhDanger danger=dhDangerService.selectById(id);
        model.addAttribute("danger",danger);
        return "/admin/danger/addDeal";

    }

    @RequestMapping("/dangerDetail")
    @ResponseBody
    public DhDanger dangerDetail(Model model,HttpServletResponse response,String id) throws IOException {
        List<DhDanger> dangerList=new ArrayList<>();
        DhDanger danger=dhDangerService.selectById(id);
        return danger;

    }
    /**
     * 指派
     * @param model
     * @param response

     * @return
     * @throws IOException
     */

    @RequestMapping("/zp")
    public String zp(HttpServletRequest request,String dangerDealer,Model model,HttpServletResponse response,String[] ids,String jobNo) throws IOException, ServletException {
        DhDanger danger=new DhDanger();
        List<DhDanger> list=new ArrayList<>();
        List<String> stringB = Arrays.asList(ids);
        list=dhDangerService.selectBatchIds(stringB);
        for(DhDanger  zpdanger:list){
            zpdanger.setNowManager(jobNo);
            zpdanger.setIsActive("0");
            zpdanger.setDangerStatus("4");
            zpdanger.setDangerDealer(jobNo);
        }
        dhDangerService.updateBatchById(list);

        //dhDangerService.updateByModal(danger);
        String ticket= (String) request.getSession().getAttribute("ticket");
        return "redirect://admin//danger/manger?ticket="+ticket;
    }

    @RequestMapping("/history")
    public String history(String dangerDealer,Model model,HttpServletResponse response,String id) throws IOException {
        return "/admin/danger/allDanger";
    }
    @RequestMapping("/zpnine")
    public String  zpNine(HttpServletRequest request,String jobNo,Model model,HttpServletResponse response,String id,String[] ids) throws IOException, ServletException {
        DhDanger danger=new DhDanger();
        List<DhDanger> list=new ArrayList<>();
        List<String> stringB = Arrays.asList(ids);
        list=dhDangerService.selectBatchIds(stringB);
        for(DhDanger  zpdanger:list){
           // zpdanger.setId(zpdanger.getId());
            zpdanger.setDangerStatus("4");
            zpdanger.setDangerDealer(jobNo);

        }

        dhDangerService.updateBatchById(list);
        String ticket= (String) request.getSession().getAttribute("ticket");
        return "redirect:/admin/danger/manger?ticket="+ticket;

    }
    @RequestMapping("/tj")
    public String tj(String jobNo,Model model,HttpServletResponse response,String id) throws IOException {
        DhDanger danger=new DhDanger();
        danger.setId(id);
        danger.setDangerStatus("0");
        danger.setIsActive("0");
        danger.setDangerDealer(jobNo);
        danger.setIsHq("1");
        dhDangerService.updateByModal(danger);
        return "/admin/danger/dangerManger";
    }

    /**
     * 通过
     * @param uid
     * @param model
     * @param response
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping("/pass")
    public String pass(String uid,Model model,HttpServletResponse response,String id) throws IOException {
        DhDanger danger=new DhDanger();
        danger.setId(id);
        danger.setDangerStatus("2");
        dhDangerService.updateByModal(danger);
        return "/admin/danger/dangerManger";
    }

    /**
     * 驳回
     * @param model
     * @param response
     * @param id
     * @return
     * @throws IOException
     */
//    @RequestMapping("/back")
//    public String back(String uid,Model model,HttpServletResponse response,String id) throws IOException {
//        DhDanger danger=new DhDanger();
//        danger.setId(id);
//        danger.setDangerStatus("3");
//        dhDangerService.updateByModal(danger);
//        return "/admin/danger/dangerManger";
//    }
    @RequestMapping("/hq")
    public void hq(Model model,HttpServletResponse response,String id,HttpServletRequest request,String hqmanager,String[] ids) throws Exception {
        DhDanger danger=new DhDanger();
        List<DhDanger> list=new ArrayList<>();
        List<String> stringB = Arrays.asList(ids);
        list=dhDangerService.selectBatchIds(stringB);
        Map map=new HashMap<>();
        map.put("phone",hqmanager);
        List<DhManager> managers=dhManager.selectByMap(map);
        for(DhDanger  zpdanger:list){
            zpdanger.setDangerStatus("1");
            zpdanger.setNowManager(managers.get(0).getJobNo());
        }
        dhDangerService.updateBatchById(list);

        List<String> openId= new ArrayList<>();
        List<String> phonelist=new ArrayList<>();
        phonelist.add(hqmanager);
        String eid= (String) request.getSession().getAttribute("eid");
        String path = ResourceUtils.getURL("classpath:").getPath()+"/static/yzj/534488.key";
        String congfig=java.net.URLDecoder.decode(path,"utf-8");
        List<Contacter> hqer= YzjUtils.getUserByPhone(phonelist, eid, congfig);
        openId.add(hqer.get(0).getOpenid());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = LocalDate.now().format(fmt);
        MessageSend.pubsendlink(ids,openId,java.net.URLDecoder.decode(dateStr,"utf-8"),hqmanager);
        request.getRequestDispatcher("/manger").forward(request,response);
    }
    @RequestMapping("/hqdetail")
    public String  hqdetail(String phone,Model model ,HttpServletResponse response,String ids,HttpServletRequest request,String  id) throws Exception {
        String[] aid=ids.split("_");
        List<String> stringB = Arrays.asList(aid);
        List<DhDanger> list=dhDangerService.selectBatchIds(stringB);
        Map omap=new HashMap<>();
        omap.put("danger_id",list.get(0).getId());
        omap.put("phone",phone);
        //omap.put("phone")
        List<DhOpinion>  olist=dhOpinionService.selectByMap(omap);
        try {
            if (olist.size() > 0) {
                return "/admin/danger/addHq";
            }
        }catch (Exception e) {
            return "/admin/danger/addHq";
        }
        model.addAttribute("dataList",list);
        model.addAttribute("phone",phone);
        return "/admin/danger/addHq";

    }

    /**
     * 提交会签意见
     * @param view
     * @param model
     * @param response
     * @param id
     * @param request
     * @throws Exception
     */
    @RequestMapping("/addHq")
    @ResponseBody
    public String  addHq(String phone,String view,Model model,HttpServletResponse response,String id,HttpServletRequest request,String[] ids) throws Exception {

        List<String> idlist=Arrays.asList(ids);
        List<DhDanger> danger=dhDangerService.selectBatchIds(idlist);
        for(String dangerid:idlist){
            DhOpinion opinion=new DhOpinion();
            opinion.setView(view);
            opinion.setViewTime(LocalDateTime.now());
            opinion.setOpinionManager(phone);
            opinion.setDangerId(dangerid);
            Map map=new HashMap();
            map.put("phone",phone);
            List<DhManager> managers=dhManager.selectByMap(map);
            opinion.setOpinionManager(managers.get(0).getName());
            opinion.setPhone(phone);
            dhOpinionService.insert(opinion);
        }
        //根据是否为逢9
        for(DhDanger dan:danger) {
            if (dan.getIsNine().equals("0")) {
                dan.setIsHq("0");
                //dan.setNowManager(dan.);
                dan.setDangerStatus("4");

            } else {
                dan.setIsHq("0");
                dan.setNowManager("019099");
                dan.setDangerStatus("2");
            }
        }

        dhDangerService.updateBatchById(danger);

        return "��ɻ�ǩ!";
    }

    /**
     * ��ȡ��ǩ���?

     * @param model
     * @param response
     * @param id
     * @param request
     * @throws Exception
     */
    @RequestMapping("/opinionList")
    @ResponseBody
    public List<DhOpinion>  opinionList(Model model,HttpServletResponse response,String id,HttpServletRequest request) throws Exception {
        Map map=new HashMap();
        map.put("danger_id",id);
        List<DhOpinion> list=dhOpinionService.selectByMap(map);
        return list;

    }


    /**
     * 提交整改信息
     * @param dealWay
     * @param model
     * @param response
     * @param id
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/addDealData")
    public String addDeal(String dealWay,Model model,HttpServletResponse response,String id,HttpServletRequest request) throws IOException {
        String basePath = request.getServletContext().getRealPath("danger/images/");
        String resource=System.getProperty("user.dir") +"\\src\\main\\resources\\static\\danger\\images\\";
        String path = ResourceUtils.getURL("classpath:").getPath()+"/static/danger/images/";

        MultipartFile s = ((MultipartRequest) request).getFile("file");
        path= URLDecoder.decode(path, "utf-8");
        String filePath = path + s.getOriginalFilename();
        File desFile = new File(filePath);
        String name=desFile.getName();
        s.transferTo(desFile);
        DhDangerDeal deal=new DhDangerDeal();
        deal.setDealUrl("/danger/images/" + name);
        deal.setDangerId(id);
        deal.setDealDate(LocalDateTime.now());
        deal.setDealWay(dealWay);
        logger.info("处理方式"+dealWay);
        dhDangerDealService.insert(deal);
        DhDanger danger=new DhDanger();
        danger.setDealUrl("/danger/images/" + name);
        danger.setDangerStatus("5");
        danger.setId(id);
        danger.setDealWay(dealWay);
        dhDangerService.updateByModal(danger);
        List<DhDanger> dangerList=new ArrayList<>();
        Map tj= new HashMap();
        String jobno= (String) request.getSession().getAttribute("jobno");
        tj.put("danger_Status",4);
        tj.put("danger_Dealer",jobno);
        dangerList=dhDangerService.selectByMap(tj);
        model.addAttribute("dangerList",dangerList);
        return "/admin/danger/deal";

    }

    /**
     * 经理或�?�检查人员录入安全问�??
     * @param request
     * @param dangerDescript
     * @param type
     * @param dutyDept
     * @param dealResult
     * @param model
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/addDanger")
    public String addDanger(HttpServletRequest request, String remark,String dangerDescript,String type,String dutyDept,String dealResult,Model model,HttpServletResponse response) throws Exception {
        int day=LocalDate.now().getDayOfMonth();
//
//        if(day!=8&&day!=9&&day!=10){
//            return "/admin/danger/error";
//        }
           //获取用户权限
            UserContextData user= (UserContextData) request.getSession().getAttribute("userContextData");
            String jobno= (String) request.getSession().getAttribute("jobno");
            Map rolemap=new HashMap<>();
            rolemap.put("job_no",jobno);
            //map.put("pwd",pwd);
            List<DhManager> list=dhManager.selectByMap(rolemap);

            String path = ResourceUtils.getURL("classpath:").getPath()+"/static/danger/images/";

            MultipartFile s = ((MultipartRequest) request).getFile("file");
            MultipartFile s1 = ((MultipartRequest) request).getFile("pic");
            path= URLDecoder.decode(path, "utf-8");
            String filePath = path + s.getOriginalFilename();
            File desFile = new File(path+UUID.randomUUID().toString()+".jpg");
            String name=desFile.getName();
            s.transferTo(desFile);


            DhDanger danger=new DhDanger();
            danger.setDealerName(user.getUsername());
            danger.setDutyDept(dutyDept);
            danger.setImgUrl("/danger/images/"+name);
            danger.setType(type);
            //会签状态为0
            danger.setIsHq("0");
            danger.setDangerDealer(jobno);
            danger.setRemark(remark);
            danger.setDangerDescript(dangerDescript);
            danger.setDealResult(dealResult);

            //逢9
            if(list.size()>0&&list.get(0).getRole().equals("0")){
                danger.setNowManager(jobno);
                danger.setDangerStatus("0");
                danger.setIsNine("0");
                danger.setIsHq("1");
            }else if(list.size()>0&&list.get(0).getRole().equals("2")) {
                danger.setNowManager("019099");
                danger.setDangerStatus("0");
                //领导未签字
                danger.setIsNine("1");
                danger.setIsHq("1");
            }else if(list.size()>0&&list.get(0).getRole().equals("1")) {
                danger.setNowManager("019099");
                danger.setDangerStatus("0");
                //领导未签字
                danger.setIsNine("1");
                danger.setIsHq("1");
            }
           dhDangerService.insert(danger);

        return "/admin/danger/addList";
    }
    @RequestMapping("/list")
    public String list(Model model,HttpServletResponse response) throws IOException {

        List<DhDanger> dangerList=new ArrayList<>();
        Map tj= new HashMap();
        //tj.put("overt",0);
        dangerList=dhDangerService.selectByMap(tj);
        model.addAttribute("dangerList",dangerList);
        return "/admin/danger/deal";

    }


    @RequestMapping("/getManager")
    @ResponseBody
    public List<DhManager> getManager(String role,Model model,HttpServletResponse response,String jobno,HttpServletRequest request) throws IOException {
        Map tj= new HashMap();
        String jobNo= (String) request.getSession().getAttribute("jobNo");
        tj.put("job_No",jobNo);
        List<DhManager> list=dhManager.selectByMap(tj);
        String dept=list.get(0).getDept();
        if(list.get(0).getRole().equals("2")){
            Map map= new HashMap();
            if(jobno!=null){
                map.put("job_no",jobno);
            }
            List<DhManager> mylist=dhManager.selectByMap(map);
            return mylist;
        }
        Map map= new HashMap();
        if(jobno!=null){
            map.put("job_no",jobno);
        }
        map.put("dept",dept);
        List<DhManager> mylist=dhManager.selectByMap(map);
        return mylist;

    }
    @RequestMapping("/getDept")
    @ResponseBody
    public List<DhDsdept> getDept(Model model,HttpServletResponse response,HttpServletRequest request) throws Exception {
       String jobno= (String) request.getSession().getAttribute("jobno");
       // String jobno=job.getString("jobNo");
        Map tj= new HashMap();
        tj.put("job_no",jobno);
        List<DhManager> list=dhManager.selectByMap(tj);
        String dept=list.get(0).getDept();
        Map map= new HashMap();
        if(!list.get(0).getRole().equals("2")){
            map.put("id",dept);
        }

        List<DhDsdept> deptlist=dhDsdeptService.selectByMap(map);
        return deptlist;

    }
}
