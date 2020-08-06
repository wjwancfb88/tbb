package com.dhwooden.ep.controller;

import com.alibaba.fastjson.JSONObject;
import com.dhwooden.ep.modal.EquipmentType;
import com.dhwooden.ep.service.EquipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2018-05-18
 */
@Controller
@RequestMapping("/admin/equipmentType")
public class EquipmentTypeController {
    @Autowired
    private EquipmentTypeService equipmentTypeService;
    @RequestMapping("/type")
    @ResponseBody
    public   List<EquipmentType>  index(){
        Map map=new HashMap<>();
        map.put("type","b");
        return equipmentTypeService.selectByMap(map);
    }
    @RequestMapping("/queryById")
    @ResponseBody
    public   List<EquipmentType>  index(String parentId){
        Map map=new HashMap<>();
        if(parentId.equals("2")){
            parentId="1";
        }
        map.put("parent_id",parentId);
        return equipmentTypeService.selectByMap(map);
    }
    @RequestMapping("/queryAll")
        @ResponseBody
        public   List<EquipmentType>  queryall(String type){
        Map map=new HashMap<>();
        map.put("type",type);
        return equipmentTypeService.selectByMap(map);
    }
    @RequestMapping("/selectById")
    @ResponseBody
    public   List<EquipmentType>  queryByid(String id){
        Map map=new HashMap<>();
        map.put("id",id);
        return equipmentTypeService.selectByMap(map);
    }

    @RequestMapping("/add")
    @ResponseBody
    public   void  addType(String name,String type,String parentId){
        EquipmentType rq=new EquipmentType();
        rq.setType(type);
        rq.setName(name);
        if(parentId!=null) {
            rq.setParentId(parentId);
        }
        equipmentTypeService.insert(rq);
    }

    @RequestMapping("/update")
    @ResponseBody
    public   void  update(String name,String id,String type){
        EquipmentType rq=new EquipmentType();
        rq.setId(id);
        rq.setName(name);
        rq.setType(type);
        equipmentTypeService.updateByModal(rq);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public   void  deleteType(String id){
        List<String> list = JSONObject.parseArray(id, String.class);
        equipmentTypeService.deleteBatchIds(list);
       // String[] s=ids.split(",");
//

    }
	
}
