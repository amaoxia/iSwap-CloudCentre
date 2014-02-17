package com.ligitalsoft.model.changemanage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

@Entity
@Table(name = "Send_MessageLog")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SendMessageLog extends LongIdObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 67210889959751029L;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 发送内容
	 */
	private String message;
	/**
	 * 发送时间
	 */
	private Date sendDate;
	/**
	 * 创建时间
	 */
	private Date createDate = new Date();

	private String syncState = "0";

	@Column(length = 10)
	public String getSyncState() {
		return syncState;
	}

	public void setSyncState(String syncState) {
		this.syncState = syncState;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
