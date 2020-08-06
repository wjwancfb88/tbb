package com.dhwooden.ep.controller;


import com.dhwooden.ep.modal.EquipmentWork;
import com.dhwooden.ep.service.EquipmentWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2018-05-11
 */
@Controller
@RequestMapping("/ep/equipmentWork")
public class EquipmentWorkController {
    @Autowired
    private EquipmentWorkService equipmentService;
    @RequestMapping("/add")
    @ResponseBody
    public void add(String repairdept,String repaircode,String repairTime,String repairType,String repairer,HttpServletRequest request,HttpServletResponse rs) throws IOException, ParseException {
        EquipmentWork ew=new EquipmentWork();
        ew.setCreator("admin");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(repairTime!=null&&repairTime!="") {
            ew.setDate(sdf.parse(repairTime));
        }
        ew.setDealer(repairer);
        ew.setProblemMessage(repairType);
        ew.setEquipmentCode(repaircode);
        equipmentService.insert(ew);
        rs.sendRedirect("/admin/equipment/index");
    }

}

