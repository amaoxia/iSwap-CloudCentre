/*
 * @(#)ChangeItem.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.changemanage;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;


/**
 * 交换发送任务表
 * @author daic
 * @since 2011-08-16 16:16:38
 * @name com.ligitalsoft.model.changemanage.ExchangeSendTask.java
 * @version 1.0
 */
@Entity
@Table(name = "CHANGE_SendTask")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExchangeSendTask extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = -3899748623842439512L;
	private ChangeItem item;
	private String taskName;//任务名称
	private Date execDate;//执行时间
	private String dataTimeZone;//数据时间段
	private String finishedState;//完成状态。0：未完成；1.完成
	private String sendState;//发送状态 1 按时发送(绿灯) 2 延时发送(黄灯) 3 超时发送(红灯)
	private Integer transactCount;//催办次数
	private Integer ontimeCount;//按时次数
	private Integer overtimeCount;//超时次数
	private String apps;//任务所属应用
	private Set<ExchangeDataSend> dataSends = new HashSet<ExchangeDataSend>(0);
	private Set<ExchangeTransact> transacts = new HashSet<ExchangeTransact>(0);
	
	private Date execStartDate;//任务执行开始时间
	private Date execEndDate;//任务执行结束时间
	private Date execLastDate;//任务执行最后日期
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
	public ChangeItem getItem() {
		return item;
	}
	public void setItem(ChangeItem item) {
		this.item = item;
	}
	public String getTaskName() {
		return taskName;
	}
	public String getApps() {
		return apps;
	}
	public void setApps(String apps) {
		this.apps = apps;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getExecDate() {
		return execDate;
	}
	public void setExecDate(Date execDate) {
		this.execDate = execDate;
	}
	public String getDataTimeZone() {
		return dataTimeZone;
	}
	public void setDataTimeZone(String dataTimeZone) {
		this.dataTimeZone = dataTimeZone;
	}
	public String getFinishedState() {
		return finishedState;
	}
	public void setFinishedState(String finishedState) {
		this.finishedState = finishedState;
	}
	public String getSendState() {
		return sendState;
	}
	public void setSendState(String sendState) {
		this.sendState = sendState;
	}
	public Integer getTransactCount() {
		return transactCount;
	}
	public void setTransactCount(Integer transactCount) {
		this.transactCount = transactCount;
	}
	public Integer getOntimeCount() {
		return ontimeCount;
	}
	public void setOntimeCount(Integer ontimeCount) {
		this.ontimeCount = ontimeCount;
	}
	public Integer getOvertimeCount() {
		return overtimeCount;
	}
	public void setOvertimeCount(Integer overtimeCount) {
		this.overtimeCount = overtimeCount;
	}
	
	public Date getExecStartDate() {
		return execStartDate;
	}
	@Transient
	public Set<ExchangeDataSend> getDataSends() {
		return dataSends;
	}
	public void setDataSends(Set<ExchangeDataSend> dataSends) {
		this.dataSends = dataSends;
	}
	@Transient
	public Set<ExchangeTransact> getTransacts() {
		return transacts;
	}
	public void setTransacts(Set<ExchangeTransact> transacts) {
		this.transacts = transacts;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setExecStartDate(Date execStartDate) {
		this.execStartDate = execStartDate;
	}
	public Date getExecEndDate() {
		return execEndDate;
	}
	public void setExecEndDate(Date execEndDate) {
		this.execEndDate = execEndDate;
	}
	public Date getExecLastDate() {
		return execLastDate;
	}
	public void setExecLastDate(Date execLastDate) {
		this.execLastDate = execLastDate;
	}
}
