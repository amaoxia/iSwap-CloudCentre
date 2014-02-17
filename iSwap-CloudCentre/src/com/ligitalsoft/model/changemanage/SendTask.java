package com.ligitalsoft.model.changemanage;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.StringIdObject;

/**
 * 数据发送任务安排表
 * @author liuxd
 * @mail liuxiaodong_315@163.com
 * @since 2011-04-16
 * @name com.ligitalsoft.model.SendTask.java
 * @version 1.0
 */
@Entity
@Table(name = "send_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SendTask extends StringIdObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5482249572944532650L;
	/**
	 * 任务名称
	 */
	private String taskName;
	/**
	 * 执行日期
	 */
	private Date execDate; 
	/**
	 * 任务开始日期
	 */
	private Date execStartDate;
	/**
	 * 任务结束日期
	 */
	private Date execEndDate;
	/**
	 * 任务执行最后日期
	 */
	private Date execLastDate;
	/**
	 * 数据时间段
	 */
	private String dataTimeZone;
	/**
	 * 完成状态
	 */
	private String finishedState;
	/**
	 * 按时状态
	 */
	private String sendState;
	/**
	 * 催办次数
	 */
	private int transactCount;
	/**
	 * 按时次数
	 */
	private int ontimeCount;
	/**
	 * 超时次数
	 */
	private int overtimeCount;
	/**
	 * 指标项
	 */
	private Item item;
	public String getTaskName() {
		return taskName;
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
	public Date getExecStartDate() {
		return execStartDate;
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
	public int getTransactCount() {
		return transactCount;
	}
	public void setTransactCount(int transactCount) {
		this.transactCount = transactCount;
	}
	public int getOntimeCount() {
		return ontimeCount;
	}
	public void setOntimeCount(int ontimeCount) {
		this.ontimeCount = ontimeCount;
	}
	public int getOvertimeCount() {
		return overtimeCount;
	}
	public void setOvertimeCount(int overtimeCount) {
		this.overtimeCount = overtimeCount;
	}
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
}
