package com.ligitalsoft.model.serverinput;

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
import com.ligitalsoft.model.system.SysDept;

/**
 * FTPServer注册
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-6-12 下午06:57:49
 * @Team 研发中心
 */
@Entity
@Table(name = "SERVINPUT_FTPSERVERINFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FtpServerInfo extends LongIdObject {

	private static final long serialVersionUID = 1L;
	/**
	 * ftp服务的名称
	 */
	private String ftpServerName;
	/**
	 * 端口
	 */
	private String port;
	/**
	 * 地址
	 */
	private String address;

	private SysDept sysDept;
	/**
	 * ftp的路径
	 */
	private String filePath;
	/**
	 * 连接超时时间
	 */
	private String outTimes;

	private String userName;

	private String password;
	/**
	 * 是否备份
	 */
	private String isBackup;

	private String notes;
	/**
	 * 状态
	 */
	private int status;

	/**
	 * 创建时间
	 */
	private Date dateCreate = new Date();

	public String getFtpServerName() {
		return ftpServerName;
	}

	public void setFtpServerName(String ftpServerName) {
		this.ftpServerName = ftpServerName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID")
	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOutTimes() {
		return outTimes;
	}

	public void setOutTimes(String outTimes) {
		this.outTimes = outTimes;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsBackup() {
		return isBackup;
	}

	public void setIsBackup(String isBackup) {
		this.isBackup = isBackup;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
