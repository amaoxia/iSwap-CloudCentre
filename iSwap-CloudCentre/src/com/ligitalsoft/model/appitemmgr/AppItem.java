/*
 * @(#)ChangeItem.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.appitemmgr;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;

/**
 * 指标
 * 
 * @author zhangx
 * @since Jun 14, 2011 10:30:46 AM
 * @name com.ligitalsoft.model.appitemmgr.Item.java
 * @version 1.0
 */
@Entity
@Table(name = "CLOUDCENTER_APPITEM")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppItem extends LongIdObject {

	private static final long serialVersionUID = -3899748623842439512L;
	/**
	 * 指标名称
	 */
	private String appItemName;
	/**
	 * 指标编码
	 */
	private String appItemCode;
	
	/**
	 * 指标描述
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private Date createDate = DateUtil.toDate(new Date());
	
	/**
	 * 删除标记 1:已删除 0:未删除
	 */
	private Integer isDeleted = 0;

	public String getAppItemName() {
		return appItemName;
	}

	public void setAppItemName(String appItemName) {
		this.appItemName = appItemName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getAppItemCode() {
		return appItemCode;
	}

	public void setAppItemCode(String appItemCode) {
		this.appItemCode = appItemCode;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

}
