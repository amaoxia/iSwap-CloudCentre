/*
 * @(#)ChangeItem.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.changemanage;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;


/**
 * 数据发送记录表
 * @author daic
 * @since 2011-08-16 16:16:38
 * @name com.ligitalsoft.model.changemanage.ExchangeDataSend.java
 * @version 1.0
 */
@Entity
@Table(name = "CHANGE_DataSend")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExchangeDataSend extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = -3899748623842439512L;
    private ChangeItem item;
	private String dataTimeZone;
	private Long sendCount;
	private Date realSendDate;
	private Date startExecDate;//开始执行时间
	private Date endExecDate;//结束执行时间
	private ExchangeSendTask sendTask;
	

	public ChangeItem getItem() {
		return item;
	}
	public void setItem(ChangeItem item) {
		this.item = item;
	}
	public String getDataTimeZone() {
		return dataTimeZone;
	}
	public void setDataTimeZone(String dataTimeZone) {
		this.dataTimeZone = dataTimeZone;
	}
	public Long getSendCount() {
		return sendCount;
	}
	public void setSendCount(Long sendCount) {
		this.sendCount = sendCount;
	}
	public Date getRealSendDate() {
		return realSendDate;
	}
	public void setRealSendDate(Date realSendDate) {
		this.realSendDate = realSendDate;
	}
	public Date getStartExecDate() {
		return startExecDate;
	}
	public void setStartExecDate(Date startExecDate) {
		this.startExecDate = startExecDate;
	}
	public Date getEndExecDate() {
		return endExecDate;
	}
	public void setEndExecDate(Date endExecDate) {
		this.endExecDate = endExecDate;
	}
	
	public ExchangeSendTask getSendTask() {
		return sendTask;
	}
	public void setSendTask(ExchangeSendTask sendTask) {
		this.sendTask = sendTask;
	}
	
	
	
}
