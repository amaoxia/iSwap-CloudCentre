package com.ligitalsoft.model.performanage;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * 
 *@Description 
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-5-28 下午05:36:46
 *@Team 研发中心
 */
@Entity
@Table(name = "PERF_MODEL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Model extends LongIdObject {

	
	private static final long serialVersionUID = 1L;
	/**
	 * 模板名称
	 */
	private String name;
	/**
	 * 考核年度 yyyy
	 */
	private String checkYear;
	/**
	 * 模板状态 1表示激活,2表示冻结
	 */
	private String status;
	/**
	 * 部门选择(季)的状态 1表示该模板按季度考核时有选择部门 、2表示该模板按季度考核时未选择部门
	 */
	private String deptModeQuarter;
	/**
	 * 部门选择(年度)的状态 1表示该模板按年度考核时有选择部门 、2表示该模板按年度考核时未选择部门
	 */
	private String deptModeYear;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCheckYear() {
		return checkYear;
	}

	public void setCheckYear(String checkYear) {
		this.checkYear = checkYear;
	}

	public String getDeptModeQuarter() {
		return deptModeQuarter;
	}

	public void setDeptModeQuarter(String deptModeQuarter) {
		this.deptModeQuarter = deptModeQuarter;
	}

	public String getDeptModeYear() {
		return deptModeYear;
	}

	public void setDeptModeYear(String deptModeYear) {
		this.deptModeYear = deptModeYear;
	}

}
