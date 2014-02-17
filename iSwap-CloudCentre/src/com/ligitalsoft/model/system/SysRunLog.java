/*
 * @(#)SysRunLog.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.system;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.framework.domain.LongIdObject;

/**
 * 系统运行日志
 * 
 * @author zhangx
 * @since May 13, 2011 4:14:45 PM
 * @name com.ligitalsoft.model.system.SysRunLog.java
 * @version 1.0
 */
@Entity
@Table(name = "SYS_RUN_LOG")
public class SysRunLog extends LongIdObject {

	private static final long serialVersionUID = -8560912864408478111L;
	/**
	 * 日志文件名
	 */
	private String logName;
	/**
	 * 日志大小
	 */
	private String logSize;
	/**
	 * 创建时间
	 */
	private Date createdate = new Date();
	/**
	 * 日志类型
	 */
	private String logType;
	/**
	 * 日志文件路径
	 */
	private String logPath;

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getLogSize() {
		return logSize;
	}

	public void setLogSize(String logSize) {
		this.logSize = logSize;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}
}
