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
import com.ligitalsoft.model.system.SysDept;

/**
 * 数据接受记录表
 * @author daic
 * @since 2011-08-16 16:16:38
 * @name com.ligitalsoft.model.changemanage.ExchangeDataReceive.java
 * @version 1.0
 */
@Entity
@Table(name = "CHANGE_DataReceive")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExchangeDataReceive extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = -3899748623842439512L;
	private String dataTimeZone;
	private Long receCount;
	private Date realReceTime;
	private Date startExecDate;//开始执行时间
	private Date endExecDate;//结束执行时间
	
	private SysDept sendDept;//发送部门（非库结构属性）
	private ChangeItem item;//接收的指标标项（非库结构属性）
	public String getDataTimeZone() {
		return dataTimeZone;
	}
	public void setDataTimeZone(String dataTimeZone) {
		this.dataTimeZone = dataTimeZone;
	}
	public Long getReceCount() {
		return receCount;
	}
	public void setReceCount(Long receCount) {
		this.receCount = receCount;
	}
	public Date getRealReceTime() {
		return realReceTime;
	}
	public void setRealReceTime(Date realReceTime) {
		this.realReceTime = realReceTime;
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


	

	public SysDept getSendDept() {
		return sendDept;
	}
	public void setSendDept(SysDept sendDept) {
		this.sendDept = sendDept;
	}
	public ChangeItem getItem() {
		return item;
	}
	public void setItem(ChangeItem item) {
		this.item = item;
	}
}
