/*
 * @(#)SysOperationlog.java
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
 * 操作日志
 * 
 * @author zhangx
 * @since May 13, 2011 4:07:42 PM
 * @name com.ligitalsoft.model.system.SysOperationlog.java
 * @version 1.0
 * 
 */
@Entity
@Table(name = "SYS_OPERATIONLOG")
public class SysOperationlog extends LongIdObject {

	private static final long serialVersionUID = 2605025095063160105L;
	/**
	 * 操作人ID
	 */
	private Long operId;
	/**
	 * 操作人名称
	 */
	private String operName;
	/**
	 * 操作子系统
	 */
	private String childSysName;
	/**
	 * 操作内容
	 */
	private String operCont;
	/**
	 * 所属角色
	 */
	private String role;
	/**
	 * 所属模块
	 */
	private String operModul;
	/**
	 * 操作时间
	 */
	private Date operDate = new Date();

	public Long getOperId() {
		return operId;
	}

	public void setOperId(Long operId) {
		this.operId = operId;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperCont() {
		return operCont;
	}

	public void setOperCont(String operCont) {
		this.operCont = operCont;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOperModul() {
		return operModul;
	}

	public void setOperModul(String operModul) {
		this.operModul = operModul;
	}

	public Date getOperDate() {
		return operDate;
	}

	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}

	public String getChildSysName() {
		return childSysName;
	}

	public void setChildSysName(String childSysName) {
		this.childSysName = childSysName;
	}
}
