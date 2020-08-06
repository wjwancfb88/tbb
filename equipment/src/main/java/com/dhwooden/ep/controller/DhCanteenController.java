package com.dhwooden.ep.controller;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.DhCanteen;
import com.dhwooden.ep.modal.DhDanger;
import com.dhwooden.ep.modal.Equipment;
import com.dhwooden.ep.service.DhCanteenService;
import com.dhwooden.ep.yzj.UserContextData;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2018-11-20
 */
@Controller
@RequestMapping("/ep/dhCanteen")
public class DhCanteenController {
    @Autowired
    private DhCanteenService dhCanteenService;
    @RequestMapping("/index")
    public String canteenIndex(Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        //UserContextData userContextData= (UserContextData) request.getSession().getAttribute("userContextData");

        return "/admin/xiaocanting/canteen";

    }
    @RequestMapping("/canteenlist")
    @ResponseBody
    public Map canteenlist(Integer pageNumber,Integer offset, Integer limit ,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        //UserContextData userContextData= (UserContextData) request.getSession().getAttribute("userContextData");
       // Page<Equipment> page=new Page<>();
        ConcurrentHashMap map=new ConcurrentHashMap();
       // Page<DhCanteen> pages=new Page<DhCanteen>(pageNumber,limit);
        com.github.pagehelper.Page<DhCanteen> mypage=PageHelper.startPage(pageNumber, limit);
        List<DhCanteen> page = dhCanteenService.selectByMap(map);
      //  pages.setRecords(page);
        Map<String,Object> info=new HashMap<String, Object>();
        info.put("rows",page);
        info.put("total",mypage.getTotal());
        return info;

    }

    @RequestMapping("/add")
    public String index(Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        UserContextData userContextData= (UserContextData) request.getSession().getAttribute("userContextData");

        return "/admin/xiaocanting/yy";

    }

    @RequestMapping("/addData")
    public String addData(String dept,String number,String type,String orderTime,String seat,String name,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        UserContextData userContextData= (UserContextData) request.getSession().getAttribute("userContextData");
        DhCanteen canteen=new DhCanteen();
        canteen.setDept(dept);
        canteen.setPnum(Integer.parseInt(number));
        canteen.setOrderTime(LocalDate.parse(orderTime));
        canteen.setType(type);
        canteen.setSeat(seat);
        dhCanteenService.insert(canteen);

        return "/admin/xiaocanting/yy";

    }



	
}
