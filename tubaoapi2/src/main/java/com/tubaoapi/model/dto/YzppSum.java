package com.tubaoapi.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.Views;

/**
 * 易装品牌汇总
 */
public class YzppSum {
    /*经销商id*/
    @JsonView(Views.PublicView.class)
    private String customerID;
    /*供应商id*/
    @JsonView(Views.PublicView.class)
    private String oemsupplierID;
    /*物料id*/
    @JsonView(Views.PublicView.class)
    private String materialID;
    /*总数*/
    @JsonView(Views.PublicView.class)
    private int sum;
    /*易装配套产品柜体总数*/
    @JsonView(Views.PublicView.class)
    private int gtSum;
    /*易装配套产品(线型)总数*/
    @JsonView(Views.PublicView.class)
    private int xxSum;
    /*易装配套产品(门板)总数*/
    @JsonView(Views.PublicView.class)
    private int mbSum;
    /*易装配套总数*/
    @JsonView(Views.PublicView.class)
    private int fqty;
    /*开始时间*/
    @JsonView(Views.PublicView.class)
    private String createTime;
    /*类型*/
    @JsonView(Views.PublicView.class)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getFqty() {
        return fqty;
    }

    public void setFqty(int fqty) {
        this.fqty = fqty;
    }

    public String getOemsupplierID() {
        return oemsupplierID;
    }

    public void setOemsupplierID(String oemsupplierID) {
        this.oemsupplierID = oemsupplierID;
    }

    public int getGtSum() {
        return gtSum;
    }

    public void setGtSum(int gtSum) {
        this.gtSum = gtSum;
    }

    public int getXxSum() {
        return xxSum;
    }

    public void setXxSum(int xxSum) {
        this.xxSum = xxSum;
    }

    public int getMbSum() {
        return mbSum;
    }

    public void setMbSum(int mbSum) {
        this.mbSum = mbSum;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
