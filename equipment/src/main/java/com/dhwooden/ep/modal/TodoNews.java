package com.dhwooden.ep.modal;

/**
 * 发送待办消息实体类
 * */
public class TodoNews {
    private String url;//必填,跳转url
    private String sourceId;//必填,生成的待办所关联的第三方服务业务记录的ID，待办的批次号，对应待办处理中的sourceitemid
    private String content;//待办内容
    private String title;//必填,内容显示
    private String itemtitle;//待办项标题内容显示,选填，如不填，则默认为title值
    private String headImg;//必填,待办在客户端显示的图URL
    private String appId;//必填,生成的待办所关联的第三方服务类型ID,appId和sourceId组合在一起标识云之家唯一的一条待办
    private String senderId;//待办的发送人的openId
    private Integer DO;//目标处理状态，0表示未办，1表示已办，默认为0
    private Integer READ;//目标读状态，0表示未读，1表示已读，默认为0
    private String openId;//必填,待办接受人ID，可填多人

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemtitle() {
        return itemtitle;
    }

    public void setItemtitle(String itemtitle) {
        this.itemtitle = itemtitle;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Integer getDO() {
        return DO;
    }

    public void setDO(Integer DO) {
        this.DO = DO;
    }

    public Integer getREAD() {
        return READ;
    }

    public void setREAD(Integer READ) {
        this.READ = READ;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "TodoNews{" +
                "url='" + url + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", itemtitle='" + itemtitle + '\'' +
                ", headImg='" + headImg + '\'' +
                ", appId='" + appId + '\'' +
                ", senderId='" + senderId + '\'' +
                ", DO=" + DO +
                ", READ=" + READ +
                ", openId='" + openId + '\'' +
                '}';
    }
}
