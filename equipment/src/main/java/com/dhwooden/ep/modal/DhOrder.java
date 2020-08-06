package com.dhwooden.ep.modal;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * Created by kaiwang on 2019/6/27.
 */
@TableName("dh_order")
public class DhOrder {
    public String orderId;
    public String orderName;
    public String orderType;
    public String accountId;
    public String orderDate;
    public String remark;
    public Double total;
    public String paid;
    public String unpaid;
    public String invoiced;
    public String uninvoiced;
    public String owningUser;
    public String owningBusinessUnit;
    public String createdBy;
    public String createdOn;
    public String modifiedBy;
    public String modifiedOn;
    public String isDeleted;
    public String chanceId;
    public String jihuijindugengxin;
    public String cBaozhuangfangshi;
    public String cShengchanjiaohuoqi;
    public String cFujian;
    public String cZhiliangyaoqiu;
    public String cZongshuliangzhang;
    public String cZongshulianglifang;
    public String cZongshuliangjianshu;
    public String cDizhi;
    public String cDingdanleixing;
    public String cBizhong;
    public String cYufukuan;

    @Override
    public String toString() {
        return "DhOrder{" +
                "orderId='" + orderId + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderType='" + orderType + '\'' +
                ", accountId='" + accountId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", remark='" + remark + '\'' +
                ", total=" + total +
                ", paid='" + paid + '\'' +
                ", unpaid='" + unpaid + '\'' +
                ", invoiced='" + invoiced + '\'' +
                ", uninvoiced='" + uninvoiced + '\'' +
                ", owningUser='" + owningUser + '\'' +
                ", owningBusinessUnit='" + owningBusinessUnit + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedOn='" + modifiedOn + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                ", chanceId='" + chanceId + '\'' +
                ", jihuijindugengxin='" + jihuijindugengxin + '\'' +
                ", cBaozhuangfangshi='" + cBaozhuangfangshi + '\'' +
                ", cShengchanjiaohuoqi='" + cShengchanjiaohuoqi + '\'' +
                ", cFujian='" + cFujian + '\'' +
                ", cZhiliangyaoqiu='" + cZhiliangyaoqiu + '\'' +
                ", cZongshuliangzhang='" + cZongshuliangzhang + '\'' +
                ", cZongshulianglifang='" + cZongshulianglifang + '\'' +
                ", cZongshuliangjianshu='" + cZongshuliangjianshu + '\'' +
                ", cDizhi='" + cDizhi + '\'' +
                ", cDingdanleixing='" + cDingdanleixing + '\'' +
                ", cBizhong='" + cBizhong + '\'' +
                ", cYufukuan='" + cYufukuan + '\'' +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(String unpaid) {
        this.unpaid = unpaid;
    }

    public String getInvoiced() {
        return invoiced;
    }

    public void setInvoiced(String invoiced) {
        this.invoiced = invoiced;
    }

    public String getUninvoiced() {
        return uninvoiced;
    }

    public void setUninvoiced(String uninvoiced) {
        this.uninvoiced = uninvoiced;
    }

    public String getOwningUser() {
        return owningUser;
    }

    public void setOwningUser(String owningUser) {
        this.owningUser = owningUser;
    }

    public String getOwningBusinessUnit() {
        return owningBusinessUnit;
    }

    public void setOwningBusinessUnit(String owningBusinessUnit) {
        this.owningBusinessUnit = owningBusinessUnit;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getChanceId() {
        return chanceId;
    }

    public void setChanceId(String chanceId) {
        this.chanceId = chanceId;
    }

    public String getJihuijindugengxin() {
        return jihuijindugengxin;
    }

    public void setJihuijindugengxin(String jihuijindugengxin) {
        this.jihuijindugengxin = jihuijindugengxin;
    }

    public String getcBaozhuangfangshi() {
        return cBaozhuangfangshi;
    }

    public void setcBaozhuangfangshi(String cBaozhuangfangshi) {
        this.cBaozhuangfangshi = cBaozhuangfangshi;
    }

    public String getcShengchanjiaohuoqi() {
        return cShengchanjiaohuoqi;
    }

    public void setcShengchanjiaohuoqi(String cShengchanjiaohuoqi) {
        this.cShengchanjiaohuoqi = cShengchanjiaohuoqi;
    }

    public String getcFujian() {
        return cFujian;
    }

    public void setcFujian(String cFujian) {
        this.cFujian = cFujian;
    }

    public String getcZhiliangyaoqiu() {
        return cZhiliangyaoqiu;
    }

    public void setcZhiliangyaoqiu(String cZhiliangyaoqiu) {
        this.cZhiliangyaoqiu = cZhiliangyaoqiu;
    }

    public String getcZongshuliangzhang() {
        return cZongshuliangzhang;
    }

    public void setcZongshuliangzhang(String cZongshuliangzhang) {
        this.cZongshuliangzhang = cZongshuliangzhang;
    }

    public String getcZongshulianglifang() {
        return cZongshulianglifang;
    }

    public void setcZongshulianglifang(String cZongshulianglifang) {
        this.cZongshulianglifang = cZongshulianglifang;
    }

    public String getcZongshuliangjianshu() {
        return cZongshuliangjianshu;
    }

    public void setcZongshuliangjianshu(String cZongshuliangjianshu) {
        this.cZongshuliangjianshu = cZongshuliangjianshu;
    }

    public String getcDizhi() {
        return cDizhi;
    }

    public void setcDizhi(String cDizhi) {
        this.cDizhi = cDizhi;
    }

    public String getcDingdanleixing() {
        return cDingdanleixing;
    }

    public void setcDingdanleixing(String cDingdanleixing) {
        this.cDingdanleixing = cDingdanleixing;
    }

    public String getcBizhong() {
        return cBizhong;
    }

    public void setcBizhong(String cBizhong) {
        this.cBizhong = cBizhong;
    }

    public String getcYufukuan() {
        return cYufukuan;
    }

    public void setcYufukuan(String cYufukuan) {
        this.cYufukuan = cYufukuan;
    }
}
