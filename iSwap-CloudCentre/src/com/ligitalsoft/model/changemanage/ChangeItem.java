/*
 * @(#)ChangeItem.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.changemanage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.model.appitemmgr.AppItemExchangeConf;
import com.ligitalsoft.model.cloudnode.DataSource;
import com.ligitalsoft.model.system.SysDept;

/**
 * 交换_指标
 * 
 * @author zhangx
 * @since Jun 14, 2011 10:30:46 AM
 * @name com.ligitalsoft.model.changemanage.ChangeItem.java
 * @version 1.0
 */
@Entity
@Table(name = "CHANGE_ITEM")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChangeItem extends LongIdObject {

	public static final String DATA_TYPE_DOC = "0";// 文档
	public static final String DATA_TYPE_DATABASE = "1";// 数据源

	public static Map<String, String> DATA_TYPE = new HashMap<String, String>();
	static {
		DATA_TYPE.put(DATA_TYPE_DOC, "文档");
		DATA_TYPE.put(DATA_TYPE_DATABASE, "数据源");
	}

	/**
     * 
     */
	private static final long serialVersionUID = -3899748623842439512L;
	/**
	 * 指标名称
	 */
	private String itemName;
	/**
	 * 指标编码
	 */
	private String itemCode;
	/**
	 * 表名称
	 */
	private String tableName;

	/**
	 * 所属部门
	 */
	private SysDept sysDept;
	
	/**
	 * 数据类型 0----文档 1--数据源
	 */
	private String dataType;
	/**
	 * 文档数据结构 0--非结构化数据(doc) 1--结构化数据(xml,xls,csv)
	 */
	private String dataStructure;
	/**
	 * 文档数据具体类型
	 */
	private String dataValue;
	
	/**
	 * 数据源类型 0:数据库数据源、1:FTP数据源、2:MQ数据源、3:WS数据源、4:MONGODB数据源
	 * 
	 */
	private String dsType;
	
	/**
	 * 数据源
	 */
	private DataSource datSource;

	/**
	 * 文档共享状态
	 */
	public String shareState = "0";

	/**
	 * 创建时间
	 */
	private Date createDate = DateUtil.toDate(new Date());

	/**
	 * 是否已推送到目录
	 */
	private boolean hasProped;

	/**
	 * 指标下的数据结构
	 */
	private List<ChangeTableDesc> tableDescs = new ArrayList<ChangeTableDesc>();

	/**
	 * 指标对应服务
	 */
	private List<ChangeItemAppMsg> itemApps = new ArrayList<ChangeItemAppMsg>();

	/**
	 * 指标下文档对象
	 */
	private List<ChangeItemDocument> document = new ArrayList<ChangeItemDocument>();
	/**
	 * 指标下模板对象
	 */
	private List<ChangeItemTemplate> template = new ArrayList<ChangeItemTemplate>();
	/**
	 * 指标所属规则
	 */
	private ChangeItemCycle changeItemCycle;
	
	/**  指标类型 1：发送 2：接收 3：共享**/
	private Integer itemType;
	
	private AppItemExchangeConf appItemExchangeConf;
	
	/** 数据提供的交换配置(冗余项)**/
	private ChangeItem changeItem;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPT_ID")
	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "SOURCE_ID")
	public DataSource getDatSource() {
		return datSource;
	}

	public void setDatSource(DataSource datSource) {
		this.datSource = datSource;
	}

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "changeItem", fetch = FetchType.LAZY)
	public List<ChangeTableDesc> getTableDescs() {
		return tableDescs;
	}

	public void setTableDescs(List<ChangeTableDesc> tableDescs) {
		this.tableDescs = tableDescs;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataStructure() {
		return dataStructure;
	}

	public void setDataStructure(String dataStructure) {
		this.dataStructure = dataStructure;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@OneToMany(mappedBy = "changeItem", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	public List<ChangeItemAppMsg> getItemApps() {
		return itemApps;
	}

	public void setItemApps(List<ChangeItemAppMsg> itemApps) {
		this.itemApps = itemApps;
	}

	@OneToMany(mappedBy = "changeItem", cascade = CascadeType.REMOVE)
	// 默认延时加载
	public List<ChangeItemDocument> getDocument() {
		return document;
	}

	public void setDocument(List<ChangeItemDocument> document) {
		this.document = document;
	}

	@OneToMany(mappedBy = "changeItem", cascade = CascadeType.REMOVE)
	public List<ChangeItemTemplate> getTemplate() {
		return template;
	}

	public void setTemplate(List<ChangeItemTemplate> template) {
		this.template = template;
	}

	@ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "CYCLE_ID")
	public ChangeItemCycle getChangeItemCycle() {
		return changeItemCycle;
	}

	public void setChangeItemCycle(ChangeItemCycle changeItemCycle) {
		this.changeItemCycle = changeItemCycle;
	}

	public String getShareState() {
		return shareState;
	}

	public void setShareState(String shareState) {
		this.shareState = shareState;
	}

	public boolean isHasProped() {
		return hasProped;
	}

	public void setHasProped(boolean hasProped) {
		this.hasProped = hasProped;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public String getDsType() {
		return dsType;
	}

	public void setDsType(String dsType) {
		this.dsType = dsType;
	}

	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "appitemexchangeconf_id")
	public AppItemExchangeConf getAppItemExchangeConf() {
		return appItemExchangeConf;
	}
	
	public void setAppItemExchangeConf(AppItemExchangeConf appItemExchangeConf) {
		this.appItemExchangeConf = appItemExchangeConf;
	}
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "changeitem_id")
	public ChangeItem getChangeItem() {
		return changeItem;
	}

	public void setChangeItem(ChangeItem changeItem) {
		this.changeItem = changeItem;
	}

	@Transient
	public String getDataTypeStr(){
		String dataTypeStr = "文档";
		if(getDataType()==null)return "";
		if(getDataType().equals("0")){
			dataTypeStr = "文档";
		}else if(getDataType().equals("1")){
			dataTypeStr = "数据库";
		}
		return dataTypeStr;
	}
	
	@Transient
	public String getDsTypeStr(){
		String dsTypeStr = "数据库数据源";
		if(getDsType()==null)return "";
		if(getDsType().equals("0")){
			dsTypeStr = "数据库数据源";
		}else if(getDsType().equals("1")){
			dsTypeStr = "FTP数据源";
		}else if(getDsType().equals("2")){
			dsTypeStr = "MQ数据源";
		}else if(getDsType().equals("3")){
			dsTypeStr = "WS数据源";
		}else if(getDsType().equals("4")){
			dsTypeStr = "MONGODB数据源";
		}
		return dsTypeStr;
	}
	
	@Transient
	public String getItemTypeStr(){
		String itemTypeStr = "发送";
		if(getItemType()==null)return "";
		if(getItemType().equals(1)){
			itemTypeStr = "发送";
		}else if(getItemType().equals(2)){
			itemTypeStr = "接收";
		}else if(getItemType().equals(3)){
			itemTypeStr = "共享";
		}
		return itemTypeStr;
	}
	
	@Transient
	public String getDataStructureStr(){
		String dataStructureStr = "非结构化数据";
		if(getDataStructure()==null)return "";
		if(getDataStructure().equals("0")){
			dataStructureStr = "非结构化数据";
		}else if(getDataStructure().equals("1")){
			dataStructureStr = "结构化数据";
		}
		return dataStructureStr;
	}
}
