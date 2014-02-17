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

@Entity
@Table(name = "CLOUDNODE_LOCALISTEN")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LocalHostListen extends LongIdObject {

	private static final long serialVersionUID = 1L;
	/**
	 * 监听名称
	 */
	private String listenName;
	/**
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 频率
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
	 * 应用
	 */
	private AppMsg appMsg;

	private String notes;
	/**
	 * 创建时间
	 */
	private Date createDate;

	public String getListenName() {
		return listenName;
	}

	public void setListenName(String listenName) {
		this.listenName = listenName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

}
