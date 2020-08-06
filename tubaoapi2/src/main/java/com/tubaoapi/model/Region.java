package com.tubaoapi.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.modules.persistence.model.IdModel;

/**
 * Created by kaiwang on 2017/8/4.
 */
public class Region  extends IdModel<String> {
    /**
     * 区名
     */
    @JsonView(Views.PublicView.class)
    private String name;
    /**
     * 城市id
     */
    @JsonView(Views.PublicView.class)
    private String cityId;
    /**
     * 编码
     */
    @JsonView(Views.PublicView.class)
    private String number;
    /**
     * 禁用状态
     */
    @JsonView(Views.PublicView.class)
    private String  deletedStatus;

    /**
     * 最后更新时间
     */
    @JsonView(Views.PublicView.class)
    private String  lastUpdateTime;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(String deletedStatus) {
        this.deletedStatus = deletedStatus;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
