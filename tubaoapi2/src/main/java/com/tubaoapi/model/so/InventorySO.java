package com.tubaoapi.model.so;

import java.util.List;

/**
 * Created by kaiwang on 2017/10/26.
 */
public class InventorySO extends BaseSO<String> {

    private String lastUpdateTime;

    private String fStorageOrgUnitID;

    private List<String> warehouseIds;

    public List<String> getWarehouseIds() {
        return warehouseIds;
    }

    public void setWarehouseIds(List<String> warehouseIds) {
        this.warehouseIds = warehouseIds;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getfStorageOrgUnitID() {
        return fStorageOrgUnitID;
    }

    public void setfStorageOrgUnitID(String fStorageOrgUnitID) {
        this.fStorageOrgUnitID = fStorageOrgUnitID;
    }
}
