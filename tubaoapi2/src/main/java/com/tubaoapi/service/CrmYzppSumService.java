package com.tubaoapi.service;

import com.tubaoapi.dao.YzppSumDao;
import com.tubaoapi.model.dto.YzppSum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 经销商物料汇总service
 */
@Service
public class CrmYzppSumService {
    @Autowired
    private YzppSumDao otherIssueBillEntryDao;

    //    public List<YzppSum> findOtherIssueBillData(List<String> wlId){
//        return otherIssueBillEntryDao.findOtherIssueBillData(wlId);
//    }
    public List<YzppSum> findOtherIssueBillData(){
        return otherIssueBillEntryDao.findOtherIssueBillData();
    }

    public List<YzppSum> findOrderData(String oemsupplierID,String createTime){
        List<YzppSum> orderData = otherIssueBillEntryDao.findOrderData(oemsupplierID,createTime);
        List<YzppSum> list = new ArrayList<YzppSum>();
        int tempGtSum = 0;
        int tempMbSum = 0;
        int tempXxSum = 0;
        YzppSum yzppGtSum = new YzppSum();
        YzppSum yzppMbSum = new YzppSum();
        YzppSum yzppXxSum = new YzppSum();
        for (YzppSum yz: orderData) {//'1roAAAIWJO1ECefw','1roAAAKz0AhECefw','1roAAAMYBd9ECefw'
            if ("1roAAAIWJO1ECefw".equals(yz.getMaterialID())){//易装产品柜体数量
                yzppGtSum.setOemsupplierID(yz.getOemsupplierID());
                yzppGtSum.setMaterialID(yz.getMaterialID());
                tempGtSum += yz.getFqty();
                tempMbSum += yz.getFqty() * 12;
            }
            if ("1roAAAKz0AhECefw".equals(yz.getMaterialID())){//易装产品线型数量
                yzppXxSum.setOemsupplierID(yz.getOemsupplierID());
                yzppXxSum.setMaterialID(yz.getMaterialID());
                tempXxSum += yz.getFqty();
            }
            if ("1roAAAMYBd9ECefw".equals(yz.getMaterialID())){//易装产品门板数量
                yzppMbSum.setOemsupplierID(yz.getOemsupplierID());
                yzppMbSum.setMaterialID(yz.getMaterialID());
                tempMbSum += yz.getFqty();
            }
        }
        yzppGtSum.setGtSum(tempGtSum);
        yzppXxSum.setXxSum(tempXxSum);
        yzppMbSum.setMbSum(tempMbSum);
        yzppGtSum.setType("gt");
        yzppXxSum.setType("xx");
        yzppMbSum.setType("mb");
        list.add(yzppGtSum);
        list.add(yzppXxSum);
        list.add(yzppMbSum);

        return list;
    }
}
