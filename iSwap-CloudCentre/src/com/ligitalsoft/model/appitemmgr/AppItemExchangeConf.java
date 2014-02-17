package com.ligitalsoft.model.appitemmgr;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.model.system.SysDept;

@Entity
@Table(name = "CLOUDCENTER_APPITEMEXCHANGECONF")
public class AppItemExchangeConf extends LongIdObject {

	private static final long serialVersionUID = -551026540773587066L;

	private AppMsg appMsg;
	
	private AppItem appItem;
	
	/**
	 * 数据提供部门
	 */
	private SysDept sendDept; 
	
	/**
	 * 数据接收部门集合
	 */
	private List<AppItemExchangeConfDetails> appItemExchangeConfDetails;
	
	/**
	 * 是否共享 0:共享 1:不共享
	 */
	private Integer isShare;
	
	/**
	 * 描述
	 */
	private String remark;
	
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
    @JoinColumn(name = "appmsg_id")
	public AppMsg getAppMsg() {
		return appMsg;
	}

	public void setAppMsg(AppMsg appMsg) {
		this.appMsg = appMsg;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appitem_id")
	public AppItem getAppItem() {
		return appItem;
	}

	public void setAppItem(AppItem appItem) {
		this.appItem = appItem;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senddept_id")
	public SysDept getSendDept() {
		return sendDept;
	}

	public void setSendDept(SysDept sendDept) {
		this.sendDept = sendDept;
	}

	@OneToMany(mappedBy = "appItemExchangeConf", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	public List<AppItemExchangeConfDetails> getAppItemExchangeConfDetails() {
		return appItemExchangeConfDetails;
	}

	public void setAppItemExchangeConfDetails(List<AppItemExchangeConfDetails> appItemExchangeConfDetails) {
		this.appItemExchangeConfDetails = appItemExchangeConfDetails;
	}

	public Integer getIsShare() {
		return isShare;
	}

	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	} 
	
}
