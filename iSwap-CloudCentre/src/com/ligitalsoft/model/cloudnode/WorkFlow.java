package com.ligitalsoft.model.cloudnode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.system.SysDept;

/**
 * 云端节点流程管理
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-9 下午05:23:04
 *@Team 研发中心
 */
@Entity
@Table(name = "CLOUDNODE_WORKFLOW")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkFlow extends LongIdObject{

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
	 * 应用
	 */
	private AppMsg appMsg;
	/**
	 * 属于部门
	 */
	private SysDept sysDept;
	/**
	 * 指标项ID
	 */
	private String itemId;
	/**
	 * 节点信息
	 */
	private CloudNodeInfo cloudNodeInfo;
	/**
	 * 状态 0为未部署，1为部署
	 */
	private String status;
	/**
	 * 流程类型 0为接收流程，1为发送流程
	 */
	private String wfType;
	/**
	 * 数据类型 0为数据库数据，1为 文档数据
	 * 
	 */
	private String dataType;
	/**
	 * 数据源类型 0:数据库数据源、1:FTP数据源、2:MQ数据源、3:WS数据源、4:MONGODB数据源
	 * 
	 */
	private String dsType;
	
	/**
	 * 数据源Id
	 * 
	 */
	private Long dsId;
	/**
	 * 流程xml
	 */
	private String workFlowXml;
	/**
	 * 定制流程xml
	 */
	private byte[] showXml;
	
	private Date createDate;
	/**
	 * 备注
	 */
	private String notes;
	
	private List<NodeTaskMsg>  taskList = new ArrayList<NodeTaskMsg>();
	
	private ChangeItem changeItem;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "CHANGE_ITEM_ID")
	public ChangeItem getChangeItem() {
		return changeItem;
	}

	public void setChangeItem(ChangeItem changeItem) {
		this.changeItem = changeItem;
	}

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
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "APP_ID")
	public AppMsg getAppMsg() {
		return appMsg;
	}

	public void setAppMsg(AppMsg appMsg) {
		this.appMsg = appMsg;
	}
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID")
	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}
	
	@JoinColumn(name = "ITEM_ID")
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "NODE_ID")
	public CloudNodeInfo getCloudNodeInfo() {
		return cloudNodeInfo;
	}

	public void setCloudNodeInfo(CloudNodeInfo cloudNodeInfo) {
		this.cloudNodeInfo = cloudNodeInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWfType() {
		return wfType;
	}

	public void setWfType(String wfType) {
		this.wfType = wfType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Column(length=4000,columnDefinition="text" )
	public String getWorkFlowXml() {
		return workFlowXml;
	}

	public void setWorkFlowXml(String workFlowXml) {
		this.workFlowXml = workFlowXml;
	}
	
	@Column(columnDefinition="LONGBLOB")
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	@OneToMany(mappedBy="workFlow",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	public List<NodeTaskMsg> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<NodeTaskMsg> taskList) {
		this.taskList = taskList;
	}

	public String getDsType() {
		return dsType;
	}

	public void setDsType(String dsType) {
		this.dsType = dsType;
	}

	public Long getDsId() {
		return dsId;
	}

	public void setDsId(Long dsId) {
		this.dsId = dsId;
	}
}
