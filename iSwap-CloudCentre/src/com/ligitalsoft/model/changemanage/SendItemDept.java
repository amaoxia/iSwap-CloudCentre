package com.ligitalsoft.model.changemanage;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.common.framework.domain.LongIdObject;
import com.ligitalsoft.model.cloudstorage.MetaData;
import com.ligitalsoft.model.system.SysDept;

/**
 * 部门需要的指标项
 * 
 * @author zhangx
 * 
 */
@Entity
@Table(name = "SEND_ITEM_DEPT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SendItemDept extends LongIdObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3177779799153014869L;

	/**
	 * 接收部门
	 */
	private SysDept sysDept;

	/**
	 * 部门名称
	 */
	private String deptNames;
	/**
	 * 部门IDS
	 */
	private String deptIds;
	/**
	 * 部门数量
	 */
	private int deptCount;
	/**
	 * 指标名称
	 */
	private String itemNames;
	/**
	 * 指标IDS
	 */
	private String itemIds;
	/**
	 * 指标数量
	 */
	private int itemCount;
	
	private ChangeItem changeItem;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "DEPT_ID")
	@Fetch(FetchMode.SELECT)
	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	public String getDeptNames() {
		return deptNames;
	}

	public void setDeptNames(String deptNames) {
		this.deptNames = deptNames;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public int getDeptCount() {
		return deptCount;
	}

	public void setDeptCount(int deptCount) {
		this.deptCount = deptCount;
	}

	public String getItemNames() {
		return itemNames;
	}

	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ITEM_ID")
	@Fetch(FetchMode.SELECT)
	public ChangeItem getChangeItem() {
		return changeItem;
	}

	public void setChangeItem(ChangeItem changeItem) {
		this.changeItem = changeItem;
	}
}
