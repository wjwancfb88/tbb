package com.tubaoapi.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.modules.persistence.model.IdModel;

/**
 * Created by kaiwang on 2017/11/24.
 */
public class SaleData extends IdModel<String> {

    private double  amount;

    private int  qty;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    //A类板材销售额
    @JsonView(Views.PublicView.class)
    private double bc;
    //A类板材销售量
    @JsonView(Views.PublicView.class)
    private int bcQty;
    //A类五金销售额
    @JsonView(Views.PublicView.class)
    private double wj;
    //A类胶黏剂销售额
    @JsonView(Views.PublicView.class)
    private  double jnj;
    //A类涂料销售额
    @JsonView(Views.PublicView.class)
    private  double tl;
    //A类墙纸销售额
    @JsonView(Views.PublicView.class)
    private double qz;
    //A类顺芯版销售额
    @JsonView(Views.PublicView.class)
    private double sxb;
    //A类顺芯版销售量
    @JsonView(Views.PublicView.class)
    private int sxbQty;

    //A类总销售额
    @JsonView(Views.PublicView.class)
    private double aTotalAmount;
    //B类总品牌费
    @JsonView(Views.PublicView.class)
    private double bOem;
    //B类总销售额
    @JsonView(Views.PublicView.class)
    private double bTotalAmount;


//    //B类易装总品牌费
//    @JsonView(Views.PublicView.class)
//    private double bYzOem;
//
//    //B类易装总销售额
//    @JsonView(Views.PublicView.class)
//    private double bYzTotalAmount;


    //易装配套销售额
    @JsonView(Views.PublicView.class)
    private double yzPtTotalAmount;

    public double getWj() {
        return wj;
    }

    public void setWj(double wj) {
        this.wj = wj;
    }

    public double getBc() {
        return bc;
    }

    public void setBc(double bc) {
        this.bc = bc;
    }

    public int getBcQty() {
        return bcQty;
    }

    public void setBcQty(int bcQty) {
        this.bcQty = bcQty;
    }

    public double getJnj() {
        return jnj;
    }

    public void setJnj(double jnj) {
        this.jnj = jnj;
    }

    public double getTl() {
        return tl;
    }

    public void setTl(double tl) {
        this.tl = tl;
    }

    public double getQz() {
        return qz;
    }

    public void setQz(double qz) {
        this.qz = qz;
    }

    public double getSxb() {
        return sxb;
    }

    public void setSxb(double sxb) {
        this.sxb = sxb;
    }

    public int getSxbQty() {
        return sxbQty;
    }

    public void setSxbQty(int sxbQty) {
        this.sxbQty = sxbQty;
    }

    public double getaTotalAmount() {
        return aTotalAmount;
    }

    public void setaTotalAmount(double aTotalAmount) {
        this.aTotalAmount = aTotalAmount;
    }

    public double getbOem() {
        return bOem;
    }

    public void setbOem(double bOem) {
        this.bOem = bOem;
    }

    public double getbTotalAmount() {
        return bTotalAmount;
    }

    public void setbTotalAmount(double bTotalAmount) {
        this.bTotalAmount = bTotalAmount;
    }


//    public double getbYzOem() {
//        return bYzOem;
//    }
//
//    public void setbYzOem(double bYzOem) {
//        this.bYzOem = bYzOem;
//    }
//
//    public double getbYzTotalAmount() {
//        return bYzTotalAmount;
//    }
//
//    public void setbYzTotalAmount(double bYzTotalAmount) {
//        this.bYzTotalAmount = bYzTotalAmount;
//    }

    public double getYzPtTotalAmount() {
        return yzPtTotalAmount;
    }

    public void setYzPtTotalAmount(double yzPtTotalAmount) {
        this.yzPtTotalAmount = yzPtTotalAmount;
    }
}
