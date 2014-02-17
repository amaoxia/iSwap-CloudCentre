package com.ligitalsoft.model.esb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.system.SysUser;

/**
 * iSwap ESB 流程管理
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-8 下午05:49:23
 *@Team 研发中心
 */
@Entity
@Table(name = "	ESB_WORKFLOW")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EsbWorkFlow extends LongIdObject{

	private static final long serialVersionUID = 1L;
	/**
	 * 流程名称
	 */
	private String workFlowName;
	/**
	 * 流程代码
	 */
	private String workFlowCode;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 流程的xml
	 */
	private String workFlowXml;
	/**
	 * 定制流程的xml
	 */
	private byte[] showXml;
	
	private Date createDate;
	/**
	 * 应用
	 */
	private AppMsg appMsg;
	/**
	 * 用户
	 */
	private SysUser sysUser;
	/**
	 * 流程任务
	 */
	private List<EsbTaskMsg> esbTask  = new  ArrayList<EsbTaskMsg>();
	
	private ChangeItem changeItem;

	public String getWorkFlowName() {
		return workFlowName;
	}

	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}

	public String getWorkFlowCode() {
		return workFlowCode;
	}

	public void setWorkFlowCode(String workFlowCode) {
		this.workFlowCode = workFlowCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWorkFlowXml() {
		return workFlowXml;
	}

	public void setWorkFlowXml(String workFlowXml) {
		this.workFlowXml = workFlowXml;
	}

	

	public byte[] getShowXml() {
		return showXml;
	}

	public void setShowXml(byte[] showXml) {
		this.showXml = showXml;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "APP_ID")
	public AppMsg getAppMsg() {
		return appMsg;
	}

	public void setAppMsg(AppMsg appMsg) {
		this.appMsg = appMsg;
	}
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	@OneToMany(mappedBy="workFlow",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	public List<EsbTaskMsg> getEsbTask() {
		return esbTask;
	}

	public void setEsbTask(List<EsbTaskMsg> esbTask) {
		this.esbTask = esbTask;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "CHANGE_ITEM_ID")
	public ChangeItem getChangeItem() {
		return changeItem;
	}

	public void setChangeItem(ChangeItem changeItem) {
		this.changeItem = changeItem;
	}
	
}
