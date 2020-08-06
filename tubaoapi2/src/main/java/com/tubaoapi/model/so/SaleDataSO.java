package com.tubaoapi.model.so;

/**
 * Created by kaiwang on 2017/11/24.
 */
public class SaleDataSO extends BaseSO<String> {
    //开始时间
    private String startTime;

    //结束时间
    private String endTime;

    //组织id
    private String orgId;

    //物料编码
    private String number;

    //用户id
    private String userId;

    //是否是易装B类
    private Boolean yzB;

    //是否是易装配套
    private Boolean yzPt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getYzB() {
        return yzB;
    }

    public void setYzB(Boolean yzB) {
        this.yzB = yzB;
    }

    public Boolean getYzPt() {
        return yzPt;
    }

    public void setYzPt(Boolean yzPt) {
        this.yzPt = yzPt;
    }
}
