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
import com.ligitalsoft.model.serverinput.FtpServerInfo;
import com.ligitalsoft.model.system.SysDept;

/**
 * Ftp监听管理
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-9 下午04:11:46
 *@Team 研发中心
 */
@Entity
@Table(name = "CLOUDNODE_FTPLISTEN")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FtpListen  extends LongIdObject{

	private static final long serialVersionUID = 1L;
	/**
	 * 监听名称
	 */
	private String listenName;
	/**
	 * 流程
	 */
	private WorkFlow workFlow;
	/**
	 * 应用
	 */
	private AppMsg appMsg;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 频率
	 */
	private String times;
	/**
	 * ftp服务器
	 */
	private FtpServerInfo ftpServerInfo;
	/**
	 * 描述
	 */
	private String notes;
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	private SysDept sysDept;

	
	public String getListenName() {
		return listenName;
	}

	public void setListenName(String listenName) {
		this.listenName = listenName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "FTP_ID")
	public FtpServerInfo getFtpServerInfo() {
		return ftpServerInfo;
	}

	public void setFtpServerInfo(FtpServerInfo ftpServerInfo) {
		this.ftpServerInfo = ftpServerInfo;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
