package com.ligitalsoft.model.appitemmgr;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.model.system.SysDept;

@Entity
@Table(name = "CLOUDCENTER_APPITEMEXCHANGECONF_DETAILS")
public class AppItemExchangeConfDetails extends LongIdObject {

	private static final long serialVersionUID = -551026540773587066L;

	private AppItemExchangeConf appItemExchangeConf;
	
	/**
	 * 数据接收部门
	 */
	private SysDept receiveDept; 
	
	/**
	 * 创建时间
	 */
	private Date createDate = DateUtil.toDate(new Date());
	
	/**
	 * 状态
	 */
	private Integer status; 
	
	/**
	 * 删除标记
	 */
	private Integer isDeleted = 0;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appitemexchangeconf_id")
	public AppItemExchangeConf getAppItemExchangeConf() {
		return appItemExchangeConf;
	}

	public void setAppItemExchangeConf(AppItemExchangeConf appItemExchangeConf) {
		this.appItemExchangeConf = appItemExchangeConf;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receivedept_id")
	public SysDept getReceiveDept() {
		return receiveDept;
	}

	public void setReceiveDept(SysDept receiveDept) {
		this.receiveDept = receiveDept;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	} 

}
