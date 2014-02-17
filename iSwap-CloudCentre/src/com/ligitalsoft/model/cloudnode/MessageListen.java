package com.ligitalsoft.model.cloudnode;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.serverinput.JmsServerInfo;
import com.ligitalsoft.model.system.SysDept;
/**
 * 
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-12 下午10:25:43
 *@Team 研发中心
 */
@Entity
@Table(name = "CLOUDNODE_JMSLISTEN")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageListen  extends LongIdObject{

	private static final long serialVersionUID = 1L;
    /**
     * 监听名称
     */
	private String listenName;
	/**
	 *频率
	 */
	private String times;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 流程
	 */
	private WorkFlow workFlow;
	/**
	 * jms服务
	 */
	private JmsServerInfo jmsServerInfo;
	/**
	 * 应用
	 */
	private AppMsg appMsg;
	
	
	/**
	 * 队列名称
	 */
	private String queueName;
	
	
	private String notes;
	
	private Date createDate;
	

	private SysDept sysDept;
	
	public String getListenName() {
		return listenName;
	}

	public void setListenName(String listenName) {
		this.listenName = listenName;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKFLOW_ID")
	public WorkFlow getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(WorkFlow workFlow) {
		this.workFlow = workFlow;
	}
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "JMS_ID")
	public JmsServerInfo getJmsServerInfo() {
		return jmsServerInfo;
	}

	public void setJmsServerInfo(JmsServerInfo jmsServerInfo) {
		this.jmsServerInfo = jmsServerInfo;
	}
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "APP_ID")
	public AppMsg getAppMsg() {
		return appMsg;
	}

	public void setAppMsg(AppMsg appMsg) {
		this.appMsg = appMsg;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID")
	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}
}
