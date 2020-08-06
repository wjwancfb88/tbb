package com.dhwooden.ep.controller;

import com.dhwooden.ep.modal.DhPerson;
import com.dhwooden.ep.service.DhPersonService;
import com.dhwooden.ep.util.PostJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/person")
public class DhPersonController {
    @Autowired
    private DhPersonService personService;
    @RequestMapping("save")
    @ResponseBody
    public String save() throws Exception{
        String flag = "";
//        PostJson pj = new PostJson();
        List<DhPerson> listAll = new ArrayList<DhPerson>();
        List<DhPerson> listTemp = new ArrayList<DhPerson>();
        int i = 0;
        int j = 1000;
        while (true){
            List<DhPerson> list = PostJson.getAllPerson(i,j);
            if (list.size() > 0){
                for (DhPerson person:list){
                    DhPerson dhPerson = personService.selectByOpenId(person.getOpenId());
                    if (null == dhPerson){
                        listTemp.add(person);
                    }
                }
            }else{
                break;
            }
            i += 1000;
            j += 1000;
        }
//        List<DhPerson> list = pj.getAllPerson(0,1000);
//        int len = list.size();
//        if (len > 0){
//            listAll.addAll(list);
//        }
//        if (len == 1000){
//            List<DhPerson> list2 = pj.getAllPerson(len,len + 1000);
//            len = list2.size();
//            if (len > 0){
//                listAll.addAll(list2);
//            }
//            if (len == 1000){
//                List<DhPerson> list3 = pj.getAllPerson(len + 1000,len + 2000);
//                len = list3.size();
//                if (len > 0){
//                    listAll.addAll(list3);
//                }
//                if (len == 1000){
//                    List<DhPerson> list4 = pj.getAllPerson(len + 2000, len + 3000);
//                    len = list4.size();
//                    if (len > 0){
//                        listAll.addAll(list4);
//                    }
//                }
//            }
//        }
        if (listTemp.size() > 0)
        listAll.addAll(listTemp);
        if (listAll.size() > 0){
            personService.insertBatch(listAll);
            flag = "1";
        }else{
            flag = "0";
        }
        return flag;
    }
}
