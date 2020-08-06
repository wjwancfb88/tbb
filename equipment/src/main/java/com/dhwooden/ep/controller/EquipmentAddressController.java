package com.dhwooden.ep.controller;

import com.alibaba.fastjson.JSONObject;
import com.dhwooden.ep.modal.EquipmentAddress;
import com.dhwooden.ep.service.EquipmentAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/equipmentAddress")
public class EquipmentAddressController {
    @Autowired
    private EquipmentAddressService equipmentAddressService;

    @RequestMapping("/address")
    @ResponseBody
    public List<EquipmentAddress> index(){
        Map map=new HashMap<>();
        return equipmentAddressService.selectByMap(map);
    }

    @RequestMapping("/selectById")
    @ResponseBody
    public List<EquipmentAddress> queryById(String id){
        Map map=new HashMap<>();
        map.put("id",id);
        return equipmentAddressService.selectByMap(map);
    }

    @RequestMapping("/add")
    @ResponseBody
    public void addName(String name){
        EquipmentAddress rq=new EquipmentAddress();
        rq.setName(name);
        equipmentAddressService.insert(rq);
    }

    @RequestMapping("/update")
    @ResponseBody
    public void update(String name,String id){
        EquipmentAddress rq=new EquipmentAddress();
        rq.setId(id);
        rq.setName(name);
        equipmentAddressService.updateByModal(rq);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void deleteType(String id){
        List<String> list = JSONObject.parseArray(id, String.class);
        equipmentAddressService.deleteBatchIds(list);
    }
}
