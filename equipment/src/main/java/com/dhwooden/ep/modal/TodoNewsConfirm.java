package com.dhwooden.ep.modal;

/**
 * 待办消息确认实体类
 */
public class TodoNewsConfirm {
    private String sourcetype;//应用ID，即appId
    private String sourceitemid;//即sourceId,生成的待办所关联的第三方服务业务记录的ID，是待办的批次号
    private String openId;//必填,待办接受人id

    public String getSourcetype() {
        return sourcetype;
    }

    public void setSourcetype(String sourcetype) {
        this.sourcetype = sourcetype;
    }

    public String getSourceitemid() {
        return sourceitemid;
    }

    public void setSourceitemid(String sourceitemid) {
        this.sourceitemid = sourceitemid;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "TodoNewsConfirm{" +
                "sourcetype='" + sourcetype + '\'' +
                ", sourceitemid='" + sourceitemid + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
