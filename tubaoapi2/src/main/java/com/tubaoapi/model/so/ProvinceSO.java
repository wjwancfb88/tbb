package com.tubaoapi.model.so;

/**
 * Created by HP on 2017/8/4.
 */
public class ProvinceSO extends BaseSO<String> {
    /**
     * 编码
     */
    private String number;
    /**
     * 省名
     */
    private String name;


    private String lastUpdateTime;

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

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
