package com.ligitalsoft.model.cloudnode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * 流程的定时任务管理
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-9 下午06:12:17
 *@Team 研发中心
 */
@Entity
@Table(name = "CLOUDNODE_NODETASK")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NodeTaskMsg extends LongIdObject{

	private static final long serialVersionUID = 1L;
	/**
	 * 任务名称
	 */
    private String taskName;
	/**
	 * 流程
	 */
	private WorkFlow  workFlow;
	/**
	 * 名称
	 */
	private String times;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 开始时间
	 */
	private String startDate;
	/**
	 * 上次运行时间
	 */
	private String hisDate;
	/**
	 * 表达式
	 */
	private String cron;
	/**
	 * 启动流程的参数
	 */
	private String message;
	/**
	 * 状态
	 */
	private String status;
	
	private String createDate;
	
	

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKFLOW_ID")
	public WorkFlow getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(WorkFlow workFlow) {
		this.workFlow = workFlow;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getHisDate() {
		return hisDate;
	}

	public void setHisDate(String hisDate) {
		this.hisDate = hisDate;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}
}
