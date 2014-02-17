/*
 * @(#)ExchangeTransact.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.changemanage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
 * 催办表
 * 
 * @author daic
 * @since 2011-08-16 16:16:38
 * @name com.ligitalsoft.model.changemanage.ExchangeTransact.java
 * @version 1.0
 */
@Entity
@Table(name = "CHANGE_Transact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExchangeTransact extends LongIdObject {
		
		public static final String DATA_TYPE_SYSTEM = "0";// 系统消息
	    public static final String DATA_TYPE_EMAIL= "1";// 邮件
	    public static final String DATA_TYPE_SHORTMESSAGE = "2";// 短信
	    public static Map<String, String> DATA_TYPE = new HashMap<String, String>();
	    static {
	        DATA_TYPE.put(DATA_TYPE_SYSTEM, "系统消息");
	        DATA_TYPE.put(DATA_TYPE_EMAIL, "邮件通知");
	        DATA_TYPE.put(DATA_TYPE_SHORTMESSAGE, "短信通知");
	    }

	private static final long serialVersionUID = -3899748623842439512L;
	/**
	 * 催办部门
	 */
	private SysDept departmentBySendDeptId;
	/**
	 * 发布任务对象
	 */
	private ExchangeSendTask sendTask;
	/**
	 * 催办方式
	 */
	private String transactType;
	/**
	 * 发送人
	 */
	private String sendUsername;
	/**
	 * 催办标题
	 */
	private String title;
	/**
	 * 催办内容
	 */
	private String content;
	/**
	 * 所属应用
	 */
	private String needReply;
	/**
	 * 是否是新的催办
	 */
	private String isNew;
	/**
	 * 发送时间
	 */
	private Date sendDate;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID")
	public SysDept getDepartmentBySendDeptId() {
		return departmentBySendDeptId;
	}

	public void setDepartmentBySendDeptId(SysDept departmentBySendDeptId) {
		this.departmentBySendDeptId = departmentBySendDeptId;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "SENDTASK_ID")
	public ExchangeSendTask getSendTask() {
		return sendTask;
	}

	public void setSendTask(ExchangeSendTask sendTask) {
		this.sendTask = sendTask;
	}

	public String getSendUsername() {
		return sendUsername;
	}

	public void setSendUsername(String sendUsername) {
		this.sendUsername = sendUsername;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNeedReply() {
		return needReply;
	}

	public void setNeedReply(String needReply) {
		this.needReply = needReply;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getTransactType() {
		return transactType;
	}

	public void setTransactType(String transactType) {
		this.transactType = transactType;
	}

}
