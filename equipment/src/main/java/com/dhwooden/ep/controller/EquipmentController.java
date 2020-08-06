package com.dhwooden.ep.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.Department;
import com.dhwooden.ep.modal.Equipment;
import com.dhwooden.ep.modal.EquipmentType;
import com.dhwooden.ep.modal.User;
import com.dhwooden.ep.service.DepartmentService;
import com.dhwooden.ep.service.EquipmentService;
import com.dhwooden.ep.service.EquipmentTypeService;
import com.dhwooden.ep.service.UserService;
import com.dhwooden.ep.util.DateUtil;
import com.dhwooden.ep.util.FileExportImportUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *  前端控制�????
 * </p>
 *
 * @author wk
 * @since 2018-04-17
 */
@Controller
@RequestMapping("/admin/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentTypeService equipmentTypeService;
    private final Logger logger= LoggerFactory.getLogger(Equipment.class);
    //子节点
    static  List<String> childMenu=new ArrayList<String>();
    //子节点
    static  List<String> nodes=new ArrayList<String>();
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public  String  index(String flag,String ticket, Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        if (StringUtils.isNotBlank(ticket)){//ticket --> 云之家ticket
            flag = "admin";//admin-->增,删,改,查   2-->增,改,查   其他-->查
            model.addAttribute("ticket", ticket);
        }
        model.addAttribute("flag", flag);
    // 验证用户是否验证，即是否登录
        if (!currentUser.isAuthenticated()) {
            String msg = "";
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");

            // remembermMe记住密码
            token.setRememberMe(true);
            try {
                // 执行登录.
                currentUser.login(token);
                // 登录成功...
                return "admin/equipment/list";
            } catch (IncorrectCredentialsException var5) {
                msg = "登录密码错误";
                System.out.println("登录密码错误!!!" + var5);
            } catch (ExcessiveAttemptsException var6) {
                msg = "登录失败次数过多";
                System.out.println("登录失败次数过多!!!" + var6);
            } catch (LockedAccountException var7) {
                msg = "帐号已被锁定";
                System.out.println("帐号已被锁定!!!" + var7);
            } catch (DisabledAccountException var8) {
                msg = "帐号已被禁用";
                System.out.println("帐号已被禁用!!!" + var8);
            } catch (ExpiredCredentialsException var9) {
                msg = "帐号已过期";
                System.out.println("帐号已过期!!!" + var9);
            } catch (UnknownAccountException var10) {
                msg = "帐号不存在";
                System.out.println("帐号不存在!!!" + var10);
            } catch (UnauthorizedException var11) {
                msg = "您没有得到相应的授权！";
                System.out.println("您没有得到相应的授权！" + var11);
            } catch (Exception var12) {
                System.out.println("出错！！！" + var12);
            }


        }
        return "admin/equipment/list";
    }
    @RequestMapping({"/list"})
    @ResponseBody
    public Page<Equipment> list(HttpServletRequest request,HttpServletResponse rs,String id,String belong,String type,String modal,String category,String user,String deptname,String name,String equipmentname,String status,String dept,String startTime,String endTime,String ipAddress,String code,String offAddress,String remark) throws UnsupportedEncodingException {

        if(name!=null){
            name = new String(name.getBytes("iso-8859-1"), "utf-8");
        }
        if(deptname!=null){
            deptname = new String(deptname.getBytes("iso-8859-1"), "utf-8");
        }
        if(modal!=null){
            modal = new String(modal.getBytes("iso-8859-1"), "utf-8");
        }

        if(equipmentname!=null){
            equipmentname = new String(equipmentname.getBytes("iso-8859-1"), "utf-8");
        }
        if(dept!=null){
            dept = new String(dept.getBytes("iso-8859-1"), "utf-8");
        }
        if(offAddress!=null){
            offAddress = new String(offAddress.getBytes("iso-8859-1"), "utf-8");
        }
//        if(remark!=null && remark != ""){
//            remark = new String(remark.getBytes("iso-8859-1"), "utf-8");
//        }

        ConcurrentHashMap allmap=new ConcurrentHashMap();
        List<Department> menuList=this.departmentService.selectByMap(allmap);
        if(dept!=null) {
            treeList(menuList, dept);
        }
        new Equipment();
        HashMap map=new HashMap<>();
        map.put("username",name);
        if(childMenu!=null&&childMenu.size()>0) {
            map.put("department", childMenu);
        }
        if(id!=null&&id!=""){
            map.put("id",id);
        }
        map.put("user",user);
        map.put("type",type);
        map.put("modal",modal);
        map.put("category",category);
        map.put("deptname",deptname);
        map.put("belong",belong);
//        map.put("user",user);
        map.put("status",status);
        map.put("name",equipmentname);
        map.put("ipAddress",ipAddress);
        map.put("offAddress",offAddress);
        map.put("code",code);
        map.put("remark", remark);
        try {
            if(endTime!=null&&endTime!="") {
                endTime = DateUtil.afterDay(endTime);
            }
        } catch (ParseException var28) {
            var28.printStackTrace();
        }
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        if(childMenu.size()==0&&dept!=null){
            map.put("id", dept);
        }
        Page page=new Page<>();
        try {
           page = this.equipmentService.queryAll(new Page(0, 12), map);
        }catch(ConcurrentModificationException e){

        }finally{
            childMenu.clear();
            return page;
        }

    }

    @RequestMapping({"/totallist"})
    @ResponseBody
    public Page<Equipment> totallist(HttpServletRequest request,HttpServletResponse rs,String belong,String type,String modal,String category,String user,String deptname,String name,String equipmentname,String status,String dept,String startTime,String endTime,String ipAddress,String code,String offAddress,String remark) throws UnsupportedEncodingException {
        if(name!=null){
            name = new String(name.getBytes("iso-8859-1"), "utf-8");
        }
        if(deptname!=null){
            deptname = new String(deptname.getBytes("iso-8859-1"), "utf-8");
        }
        if(modal!=null){
            modal = new String(modal.getBytes("iso-8859-1"), "utf-8");
        }
        if(equipmentname!=null){
            equipmentname = new String(equipmentname.getBytes("iso-8859-1"), "utf-8");
        }
        if(dept!=null){
            dept = new String(dept.getBytes("iso-8859-1"), "utf-8");
        }
        if(ipAddress != null && ipAddress != ""){
            ipAddress = new String(ipAddress.getBytes("iso-8859-1"),"utf-8");
        }
        if(offAddress != null && offAddress != ""){
            offAddress = new String(offAddress.getBytes("iso-8859-1"),"utf-8");
        }
//        if(remark!=null && remark != ""){
//            remark = new String(remark.getBytes("iso-8859-1"), "utf-8");
//        }
        ConcurrentHashMap allmap=new ConcurrentHashMap();
        List<Department> menuList=this.departmentService.selectByMap(allmap);
//        List<User> list = this.userService.selectByMap(allmap);
        if(dept!=null) {
            treeMenuList(menuList, dept);
//            treeMenuList(menuList,list,dept);
        }
        new Equipment();
        HashMap map=new HashMap<>();
        map.put("username",name);
        if(nodes!=null&&nodes.size()>0) {
            map.put("department", nodes);
        }
        map.put("user",user);
        map.put("type",type);
        map.put("modal",modal);
        map.put("category",category);
        map.put("deptname",deptname);
        map.put("belong",belong);
//        map.put("user",user);
        map.put("status",status);
        map.put("name",equipmentname);
        map.put("ipAddress",ipAddress);
        map.put("offAddress",offAddress);
        map.put("remark", remark);
        map.put("code",code);
        try {
            if(endTime!=null&&endTime!="") {
                endTime = DateUtil.afterDay(endTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        if(nodes.size()==0&&dept!=null){
            map.put("id", dept);
        }
        Page page=new Page<>();
        try {
            page = this.equipmentService.queryAll(new Page(0, 12), map);
        }catch(ConcurrentModificationException e){

        }finally{
            nodes.clear();
            return page;
        }

    }

    public static  List<String> treeMenuList( List<Department> menuList, String pid){
        /*for(Department mu: menuList){
            if(pid.equals(mu.getId())){
                nodes.add(mu.getDepartment());
            }
            if(pid.equals(mu.getParentId())){
                //递归遍历下一级

                nodes.add(mu.getDepartment());
                treeMenuList(menuList,mu.getId());

            }

        }*/
		Iterator var2 = menuList.iterator();
		while(var2.hasNext()){
			Department mu = (Department)var2.next();
			if(pid.equals(mu.getId())){
				nodes.add(mu.getDepartment());
			}
			if(pid.equals(mu.getParentId())){
				nodes.add(mu.getDepartment());
				treeMenuList(menuList,mu.getId());
			}
		}

        return nodes;
    }

//    public static  List<String> treeMenuList( List<Department> menuList,List<User> list, String pid){
//        /*for(Department mu: menuList){
//            if(pid.equals(mu.getId())){
//                nodes.add(mu.getDepartment());
//            }
//            if(pid.equals(mu.getParentId())){
//                //递归遍历下一级
//
//                nodes.add(mu.getDepartment());
//                treeMenuList(menuList,mu.getId());
//
//            }
//
//        }*/
//
//        for (Department mu:menuList) {
//            for (User user:list) {
//                if (pid.equals(mu.getId()) && mu.getDepartment().equals(user.getDepartment())){
//                    nodes.add(mu.getDepartment() + "\\" + user.getName());
//                }
//                if (pid.equals(mu.getParentId()) && mu.getDepartment().equals(user.getDepartment())){
//                    nodes.add(mu.getDepartment() + "\\" + user.getName());
//                    treeMenuList(menuList,list,mu.getId());
//                }
//            }
//        }
//
//
//        Iterator var2 = menuList.iterator();
//        while(var2.hasNext()){
//            Department mu = (Department)var2.next();
//            if(pid.equals(mu.getId())){
//                nodes.add(mu.getDepartment());
//            }
//            if(pid.equals(mu.getParentId())){
//                nodes.add(mu.getDepartment());
//                treeMenuList(menuList,mu.getId());
//            }
//        }
//
//        return nodes;
//    }

    public static  List<String> treeList( List<Department> menuList, String pid){
        /*for(Department mu: menuList){
            if(pid.equals(mu.getId())){
                childMenu.add(mu.getDepartment());
            }
            if(pid.equals(mu.getParentId())){
                //递归遍历下一级

                childMenu.add(mu.getDepartment());
                treeList(menuList, mu.getId());

            }

        }*/
		Iterator var2 = menuList.iterator();
		while(var2.hasNext()){
			Department mu = (Department)var2.next();
			if(pid.equals(mu.getId())){
				childMenu.add(mu.getDepartment());
			}
			if(pid.equals(mu.getParentId())){
				childMenu.add(mu.getDepartment());
				treeList(menuList,mu.getId());
			}
		}

        return nodes;
    }


    @RequestMapping({"/add"})
    @ResponseBody
    public void add(String flag,String ticket,String guaranty,String category,String modal,String belong,String type,String equipmentName,String code,String date,Double price,String status,String dept,String user,String remarks,String ipAddress,String offAddress,HttpServletRequest request,HttpServletResponse rs) throws IOException, ParseException {
        Equipment eq=new Equipment();
        eq.setName(equipmentName!=null?equipmentName:"");
        eq.setCode(code!=null?code:"");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(date!=null&&date!="") {
            eq.setBuyDate(sdf.parse(date));
        }
        if(price!=null) {
            eq.setPrice(price);
        }
        if(guaranty!=null&&guaranty!=""){
            eq.setGuaranty(sdf.parse(guaranty));
        }
        if(ipAddress != null&& ipAddress != ""){
            eq.setIpAddress(ipAddress);
        }
        if (offAddress != null && offAddress != ""){
            eq.setOffAddress(offAddress);
        }
        eq.setFrom("0");
        eq.setCategory(category);
        eq.setModal(modal);
        eq.setStatus(status);
        eq.setDepartment(dept);
        if(user!=null){
//            String username=((User)this.userService.selectById(user)).getName();
            String username=this.userService.selectByOpenId(user).getName();
            eq.setUsername(username);
            eq.setUser(user);//openId
        }
        eq.setType(type);
        eq.setBelong(belong);
        eq.setRemark(remarks!=null?remarks:"");
        this.equipmentService.insert(eq);
        if (StringUtils.isNotBlank(ticket)){//ticket --> 云之家ticket
            flag = "admin";//admin-->增,删,改,查   2-->增,改,查   其他-->查
            rs.sendRedirect("/admin/equipment/index?flag="+flag+"&ticket="+ticket);
        }else{
            rs.sendRedirect("/admin/equipment/index?flag="+flag);
        }
        //rs.setHeader("refresh","1,/admin/equipment/index");
    }

    @RequestMapping({"/update"})
    @ResponseBody
    public void update(String flag,String ticket,String guaranty,String category,String modal,String belong,String id,String type,String equipmentName,String code,String date,Double price,String status,String dept,String user,String remark,String ipAddress,String offAddress,HttpServletRequest request,HttpServletResponse rs) throws IOException, ParseException {
        Equipment eq=new Equipment();
        eq.setId(id);
        eq.setName(equipmentName!=null?equipmentName:"");
        eq.setCode(code!=null?code:"");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(date!=null&&date!="") {
            eq.setBuyDate(sdf.parse(date));
        }
        if(price!=null) {
            eq.setPrice(price);
        }
        if(guaranty!=null&&guaranty!=""){
            eq.setGuaranty(sdf.parse(guaranty));
        }    
		if(ipAddress!=null&&ipAddress!=""){
			eq.setIpAddress(ipAddress);
		}
		if (offAddress != null && offAddress != ""){
		    eq.setOffAddress(offAddress);
        }
        eq.setModal(modal);
        eq.setCategory(category);
        eq.setStatus(status);
        eq.setBelong(belong);
        eq.setDepartment(dept);
        if(user!=null){
//            String username=((User)this.userService.selectById(user)).getName();
            String username=this.userService.selectByOpenId(user).getName();

            eq.setUsername(username);

            eq.setUser(user);//openId
        }
        eq.setType(type);
        eq.setRemark(remark!=null?remark:"");
        this.equipmentService.updateById(eq);
        if (StringUtils.isNotBlank(ticket)){//ticket --> 云之家ticket
            flag = "admin";//admin-->增,删,改,查   2-->增,改,查   其他-->查
            rs.sendRedirect("/admin/equipment/index?flag="+flag+"&ticket="+ticket);
        }else{
            rs.sendRedirect("/admin/equipment/index?flag="+flag);
        }
        //rs.setHeader("refresh","1,/admin/equipment/index");
    }

    /**
     * 设备信息导出
     */
    @RequestMapping({"/export"})
    public void userListExport(HttpServletRequest request, HttpServletResponse response) {
        try {
            //指定文件生成路径
            String dir = request.getSession().getServletContext().getRealPath("/excelfile/user");
            //文件�????
            Date date=new Date();
            DateFormat format = new SimpleDateFormat("yyyyMMddHH24mmss");
            String expName = format.format(date) ;
            //表头信息
            String[] headName = {"设备品牌","设备型号","设备类别","设备类型","设备使用负责人","部门","购置时间","价格","设备归属"};
                Map eq=new HashMap<>();
                Page<Equipment> page= equipmentService.queryAll(new Page<Equipment>(0, 12),eq);
                List<File> srcfile = new ArrayList<File>();//生成的excel的文件的list
                List<Equipment> eqList = page.getRecords();
                List<List<String>> list=userJoint(eqList);
                File file = FileExportImportUtil.createExcel(headName, list, expName, dir);
                srcfile.add(file);
            FileExportImportUtil.createRar(response, dir, srcfile, expName);//生成的多excel的压缩包
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<List<String>> userJoint(List<Equipment> userList){
        List<List<String>> list = new ArrayList<List<String>>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < userList.size(); i++) {
            try {

            List<String> small = new ArrayList<String>();
            small.add(userList.get(i).getName());
            small.add(userList.get(i).getModal());
            Map map=new HashMap<>();
            map.put("id",userList.get(i).getType());
            List<EquipmentType> equipmentTypes=equipmentTypeService.selectByMap(map);
            if(equipmentTypes.size()>0){
                EquipmentType type1=equipmentTypes.get(0);
                small.add(type1.getName());
            }else{
                small.add("");
            }

            map.put("id",userList.get(i).getCategory());
            map.put("type","s");
            List<EquipmentType> equipmentType=equipmentTypeService.selectByMap(map);
            if(equipmentType.size()>0){
                EquipmentType type=equipmentType.get(0);
                small.add(type.getName());
            }else{
                small.add("");
            }
            small.add(userList.get(i).getUsername());
            small.add(userList.get(i).getDepartment());
            small.add(format.format(userList.get(i).getBuyDate()));
            small.add(String.valueOf(userList.get(i).getPrice()));

            map.put("id",userList.get(i).getBelong());
            map.put("type","l");
            List<EquipmentType> equipmentbelong=equipmentTypeService.selectByMap(map);
            if(equipmentbelong.size()>0){
                EquipmentType type=equipmentbelong.get(0);

                small.add(type.getName());
            }else{
                small.add("");
            }

            list.add(small);

            }catch (Exception e){
                logger.debug(userList.get(i).getName()+"error");
                //System.out.println("yichange:+++++++++"+e);
            }
        }
        return list;
    }

    @RequestMapping("/deleteOne")
    @ResponseBody
    public int deleted(String id){
        boolean isSucc = equipmentService.deleteById(id);
        return isSucc ? 1 : 0;
    }

    @RequestMapping("/getEquipmentById")
    @ResponseBody
    public Equipment getEquipmentById(String id){
        Equipment isSucc = (Equipment)this.equipmentService.selectById(id);
        return isSucc;
    }


    @RequestMapping({"/importFile"})
    public String importFile(HttpServletResponse response,HttpServletRequest request,@RequestParam("myFile") MultipartFile myfile) throws Exception {
        String msg="";
        HSSFWorkbook wookbook = new HSSFWorkbook(myfile.getInputStream());
        HSSFSheet sheet = wookbook.getSheet("Sheet1");

        int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
        if(rows==0){
            throw new Exception("请填写数据");
        }
        for (int i = 1; i <= rows+1; i++) {
            // 读取左上端单元格
            HSSFRow row = sheet.getRow(i);
            // 行不为空
            if (row != null) {
                // **读取cell**
                //品牌
                String name = getCellValue(row.getCell((short) 0));
                if(name.isEmpty()){
                    break;
                }
                //型号
                String modal=getCellValue(row.getCell((short) 1));

                //类别
                String typename=getCellValue(row.getCell((short) 2));
                Map map=new HashMap<>();
                map.put("name",typename);
                String type="";
                List<EquipmentType> types=equipmentTypeService.selectByMap(map);
                if(types.size()==1){
                    type=types.get(0).getId();
                }else if(types.size()==0){
                    EquipmentType et=new EquipmentType();
                    et.setName(typename);
                    et.setType("b");
                    equipmentTypeService.insert(et);
                }
                //类型
                String categorynem = getCellValue(row.getCell((short) 3));
                Map map2 = new HashMap<>();
                map2.put("name",categorynem);
                String category = "";
                List<EquipmentType> categorys=equipmentTypeService.selectByMap(map2);
                if(categorys.size()==1){
                    category=categorys.get(0).getId();
                }else if(categorys.size()==0){
                    EquipmentType et=new EquipmentType();
                    et.setName(categorynem);
                    et.setType("s");
                    equipmentTypeService.insert(et);
                }
                //归属
                String belongnem=getCellValue(row.getCell((short) 4));
                Map map3=new HashMap<>();
                map3.put("name",belongnem);
                String belong="";
                List<EquipmentType> belongs=equipmentTypeService.selectByMap(map3);
                if(belongs.size()==1){
                    belong=belongs.get(0).getId();
                }

                String status=getCellValue(row.getCell((short)5));
                String price=getCellValue(row.getCell((short) 6));
                String code=getCellValue(row.getCell((short) 7));

                Equipment eq=new Equipment();
                eq.setName(name);

                String buydate=getCellValue(row.getCell((short) 8));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if(buydate!=null&&buydate!="") {
                    eq.setBuyDate(sdf.parse(buydate));
                }
                String remark=getCellValue(row.getCell((short) 9));
                String username=getCellValue(row.getCell((short)10));
                eq.setCode(code);
                eq.setStatus(status);
                eq.setModal(modal);
                eq.setType(type);
                eq.setCategory(category);
                eq.setBelong(belong);
                eq.setPrice(Double.valueOf(price));
                eq.setRemark(remark);
                eq.setUsername(username);
                User user=new User();
                user.setName(username);
                Map map4=new HashMap<>();
                map4.put("name",username);
                List<User> userlist=new ArrayList<>();
                try {
                    if(userlist.size()==1) {
                        eq.setUser(userlist.get(0).getId());
                    }
                }catch(Exception E){
                    return "usererror";
                }

                eq.setFrom("1");

                try {
                    equipmentService.insert(eq);
                }catch(Exception E){
                    return "admin/equipment/list";
                }finally {
                    //异常处理的统一出口，不管是否有异常、是否处理了异常都会执行
                }

                msg="success";

            }

        }
        return "admin/equipment/list";
    }

    public String getCellValue(HSSFCell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_FORMULA:
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    DecimalFormat df = new DecimalFormat("0");
                    value = df.format(cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue().trim();
                    break;
                default:
                    value = "";
                    break;
            }
        }
        return value.trim();
    }

    @Scheduled(cron = "0 30 0 * * ?")
//    @Scheduled(fixedRate = 60000)
    @RequestMapping("updateUserAndDepartment")
    @ResponseBody
    public String updateUserAndDepartment(){
        logger.info("开始同步!");
        equipmentService.updateUser();
        equipmentService.updateDepartment();
        logger.info("结束同步!");
        return "1";
    }

}
