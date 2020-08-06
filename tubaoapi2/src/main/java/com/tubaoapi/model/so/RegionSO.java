package com.tubaoapi.model.so;

/**
 * Created by HP on 2017/8/4.
 */
public class RegionSO extends BaseSO<String> {
    /**
     * 编码
     */
    private String number;
    /**
     * 市名
     */
    private String name;
    /**
     * 所属城市id
     */
    private String cityId;

    /**
     * 最后更新时间
     */
    private String  lastUpdateTime;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}