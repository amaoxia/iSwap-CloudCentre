/*
 * @(#)ChangeItemDocument.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

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

import com.common.framework.domain.LongIdObject;

/**
 * 交换指标文档_文档管理
 * 
 * @author zhangx
 * @since Jun 23, 2011 10:07:17 AM
 * @name com.ligitalsoft.model.changemanage.ChangeItemDocument.java
 * @version 1.0
 */
@Entity
@Table(name = "CHANGE_ITEM_DOCUMENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChangeItemDocument extends LongIdObject {

	/**
     * 
     */
	private static final long serialVersionUID = 507937630086257534L;
	/**
	 * 文档名称
	 */
	private String documentName;
	/**
	 * 上传时间
	 */
	private Date uploadDate;
	/**
	 * 上传状态 (0,未上传 1上传)
	 */
	private String uploadState = "0";
	/**
	 * 上传路径
	 */
	private String uploadPath;
	/**
	 * 上传人
	 */
	private String creator;
	/**
	 * IP上传地址
	 */
	private String ipAddress;
	/**
	 * 交换状态
	 */
	private String exchangeState;
	/**
	 * 关联指标
	 */
	private ChangeItem changeItem;
	/**
	 * 交换错误信息
	 */
	private String errorInfo;
	/**
	 * 文档发布状态
	 */
	private String shareState;

	/**
	 * 关联流程Id
	 */
	private Long workFlowId;

	/**
	 * 流程名称
	 */
	private String workFlowName;

	public Long getWorkFlowId() {
		return workFlowId;
	}

	public void setWorkFlowId(Long workFlowId) {
		this.workFlowId = workFlowId;
	}

	
	public String getWorkFlowName() {
		return workFlowName;
	}

	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUploadState() {
		return uploadState;
	}

	public void setUploadState(String uploadState) {
		this.uploadState = uploadState;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getExchangeState() {
		return exchangeState;
	}

	public void setExchangeState(String exchangeState) {
		this.exchangeState = exchangeState;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID")
	public ChangeItem getChangeItem() {
		return changeItem;
	}

	public void setChangeItem(ChangeItem changeItem) {
		this.changeItem = changeItem;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getShareState() {
		return shareState;
	}

	public void setShareState(String shareState) {
		this.shareState = shareState;
	}

}