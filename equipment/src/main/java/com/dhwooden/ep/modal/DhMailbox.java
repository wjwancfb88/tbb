package com.dhwooden.ep.modal;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.time.LocalDate;


/**
 * <p>
 * 
 * </p>
 *
 * @author wk
 * @since 2018-08-27
 */
@TableName("dh_mailbox")
public class DhMailbox extends Model<DhMailbox> {

    private static final long serialVersionUID = 1L;

	private String id;
	private String receiveName;
	private String sendName;
	private String content;
	private String anonymous;
	private String overt;
	private String dealResult;
	private String dealStatus;
	private String rid;
	private String title;
	private LocalDate sendTime;

	public LocalDate getSendTime() {
		return sendTime;
	}

	public void setSendTime(LocalDate sendTime) {
		this.sendTime = sendTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(String anonymous) {
		this.anonymous = anonymous;
	}

	public String getOvert() {
		return overt;
	}

	public void setOvert(String overt) {
		this.overt = overt;
	}

	public String getDealResult() {
		return dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}

	public String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
