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
import com.common.utils.date.DateUtil;
import com.ligitalsoft.model.system.SysDept;

/**
 * 云存储监听
 * 
 * @author arcgismanager
 * 
 */
@Entity
@Table(name = "CLOUDNODE_LISTEN")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CloudnodeListen extends LongIdObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4511385510694046478L;
	/**
	 * 监听名称
	 */
	private String listenName;
	/**
	 * 实例名称
	 */
	private String dbName;
	/**
	 * 表名称
	 */
	private String collectionName;
	/**
	 * 字段名称
	 */
	private String filedName;
	/**
	 * 字段状态
	 */
	private String filedStatus;
	
	/**
	 * 创建时间
	 */
	private Date createDate = DateUtil.toDate(new Date());
	/**
	 * 流程对下岗
	 */
	private WorkFlow workFlow;

	/**
	 * 状态
	 */
	private String status = "0";

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
	 * 名称
	 */
	private String times;

	private SysDept sysDept;
	
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKFLOW_ID")
	public WorkFlow getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(WorkFlow workFlow) {
		this.workFlow = workFlow;
	}

	public String getListenName() {
		return listenName;
	}

	public void setListenName(String listenName) {
		this.listenName = listenName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getFiledStatus() {
		return filedStatus;
	}

	public void setFiledStatus(String filedStatus) {
		this.filedStatus = filedStatus;
	}


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
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

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID")
	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

}
