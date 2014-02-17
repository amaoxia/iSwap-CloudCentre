package com.ligitalsoft.model.cloudcenter;

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
 * 云端节点和部门之间的关系
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-12 下午08:16:58
 *@Team 研发中心
 */
@Entity
@Table(name = "CLOUDCENTER_NODEDEPT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CouldNodeDept extends LongIdObject{

	
	private static final long serialVersionUID = 1L;
	/**
	 * 云端节点信息
	 */
	private CloudNodeInfo  couldNode;
	/**
	 * 部门信息
	 */
	private SysDept  dept;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},  fetch = FetchType.LAZY)
	@JoinColumn(name = "NODE_ID")
	public CloudNodeInfo getCouldNode() {
		return couldNode;
	}

	public void setCouldNode(CloudNodeInfo couldNode) {
		this.couldNode = couldNode;
	}
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},  fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID")
	public SysDept getDept() {
		return dept;
	}

	public void setDept(SysDept dept) {
		this.dept = dept;
	}
	
}
