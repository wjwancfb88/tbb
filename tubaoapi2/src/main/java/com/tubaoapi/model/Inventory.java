package com.tubaoapi.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.modules.persistence.model.IdModel;

/**
 * Created by kaiwang on 2017/10/26.
 * 即时库存
 */

public class Inventory extends IdModel<String> {

    //财务组织
    private String companyOrgUnitID;
    //库存组织
    private String storageOrgUnitID;
    //库位
    private String locationId;
    //库存类型
    private String storeTypeID;
    //库存状态
    private String storeStatusID;
    //供应商
    private String supplierID;
    //经销商
    private String customerID;
    //项目号
    private String projectId;
    //跟踪号
    private String trackNumberID;
    //计量单位
    private  String unitId;
    //辅助计量单位
    private String assistUnitID;
    //现存辅助数量
    private String  curStoreAssistQty;
    //基本数量
    private String baseQty;
    //金额
    private String amount;
    //基本计量单位
    private String baseUnitID;
    //到期日期
    private String  exp;
    //辅助属性
    private String assistPropertyID;
    //锁库基本数量
    private String lockBaseQty;
    //锁库辅助数量
    private String lockAssistQty;
    //唯一键
    private String uniqueKey;
    //创建者
    private String creatorID;
    //创建时间
    private String createTime;
    //最后修改者
    private String lastUpdateUserID;
    //控制单元
    private String  controlUnitID;

    //仓库
    private String warehouseID;
   //物料
    private String materialID;
    //现存数量
    private String curStoreQty;

    private String lockQty;
    //批号
    private String lot;

    private String lastUpdateTime;
    @JsonView(Views.PublicView.class)
    public String getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(String warehouseID) {
        this.warehouseID = warehouseID;
    }
    @JsonView(Views.PublicView.class)
    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }
    @JsonView(Views.PublicView.class)
    public String getLockQty() {
        return lockQty;
    }

    public void setLockQty(String lockQty) {
        this.lockQty = lockQty;
    }
    @JsonView(Views.PublicView.class)
    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }
    @JsonView(Views.PublicView.class)
    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    @JsonView(Views.PublicView.class)
    public String getCurStoreQty() {
        if(curStoreQty.startsWith(".")){
            return "0".concat(curStoreQty);
        }else {
            return curStoreQty;
        }
    }

    public void setCurStoreQty(String curStoreQty) {
        this.curStoreQty = curStoreQty;
    }
    @JsonView(Views.PublicView.class)
    public String getCompanyOrgUnitID() {
        return companyOrgUnitID;
    }

    public void setCompanyOrgUnitID(String companyOrgUnitID) {
        this.companyOrgUnitID = companyOrgUnitID;
    }
    @JsonView(Views.PublicView.class)
    public String getStorageOrgUnitID() {
        return storageOrgUnitID;
    }

    public void setStorageOrgUnitID(String storageOrgUnitID) {
        this.storageOrgUnitID = storageOrgUnitID;
    }
    @JsonView(Views.PublicView.class)
    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    @JsonView(Views.PublicView.class)
    public String getStoreTypeID() {
        return storeTypeID;
    }

    public void setStoreTypeID(String storeTypeID) {
        this.storeTypeID = storeTypeID;
    }
    @JsonView(Views.PublicView.class)
    public String getStoreStatusID() {
        return storeStatusID;
    }

    public void setStoreStatusID(String storeStatusID) {
        this.storeStatusID = storeStatusID;
    }
    @JsonView(Views.PublicView.class)
    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }
    @JsonView(Views.PublicView.class)
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    @JsonView(Views.PublicView.class)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @JsonView(Views.PublicView.class)
    public String getTrackNumberID() {
        return trackNumberID;
    }

    public void setTrackNumberID(String trackNumberID) {
        this.trackNumberID = trackNumberID;
    }
    @JsonView(Views.PublicView.class)
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
    @JsonView(Views.PublicView.class)
    public String getAssistUnitID() {
        return assistUnitID;
    }

    public void setAssistUnitID(String assistUnitID) {
        this.assistUnitID = assistUnitID;
    }
    @JsonView(Views.PublicView.class)
    public String getCurStoreAssistQty() {
        return curStoreAssistQty;
    }

    public void setCurStoreAssistQty(String curStoreAssistQty) {
        this.curStoreAssistQty = curStoreAssistQty;
    }
    @JsonView(Views.PublicView.class)
    public String getBaseQty() {
        return baseQty;
    }

    public void setBaseQty(String baseQty) {
        this.baseQty = baseQty;
    }
    @JsonView(Views.PublicView.class)
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    @JsonView(Views.PublicView.class)
    public String getBaseUnitID() {
        return baseUnitID;
    }

    public void setBaseUnitID(String baseUnitID) {
        this.baseUnitID = baseUnitID;
    }
    @JsonView(Views.PublicView.class)
    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
    @JsonView(Views.PublicView.class)
    public String getAssistPropertyID() {
        return assistPropertyID;
    }

    public void setAssistPropertyID(String assistPropertyID) {
        this.assistPropertyID = assistPropertyID;
    }
    @JsonView(Views.PublicView.class)
    public String getLockBaseQty() {
        return lockBaseQty;
    }

    public void setLockBaseQty(String lockBaseQty) {
        this.lockBaseQty = lockBaseQty;
    }
    @JsonView(Views.PublicView.class)
    public String getLockAssistQty() {
        return lockAssistQty;
    }

    public void setLockAssistQty(String lockAssistQty) {
        this.lockAssistQty = lockAssistQty;
    }
    @JsonView(Views.PublicView.class)
    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
    @JsonView(Views.PublicView.class)
    public String getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(String creatorID) {
        this.creatorID = creatorID;
    }
    @JsonView(Views.PublicView.class)
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    @JsonView(Views.PublicView.class)
    public String getLastUpdateUserID() {
        return lastUpdateUserID;
    }

    public void setLastUpdateUserID(String lastUpdateUserID) {
        this.lastUpdateUserID = lastUpdateUserID;
    }
    @JsonView(Views.PublicView.class)
    public String getControlUnitID() {
        return controlUnitID;
    }

    public void setControlUnitID(String controlUnitID) {
        this.controlUnitID = controlUnitID;
    }
}
