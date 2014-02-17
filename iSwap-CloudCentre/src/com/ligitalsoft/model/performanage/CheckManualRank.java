package com.ligitalsoft.model.performanage;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.ligitalsoft.model.performanage.Department;

/**
 * 考核手动排名表
 * @author liuxd
 * @mail liuxiaodong_315@163.com
 * @since 2011-05-11
 * @name com.ligitalsoft.model.CheckManualRank.java
 * @version 1.0
 */
@Entity
@Table(name = "PERF_CHECKMANUALRANK")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CheckManualRank extends LongIdObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5567758667825876233L;
	/**
	 * 部门
	 */
	private Department department;
	/**
	 * 考核
	 */
	private Check check;
	/**
	 * 手工排序
	 */
	private int handOrder;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_ID")	
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public int getHandOrder() {
		return handOrder;
	}
	
	public void setHandOrder(int handOrder) {
		this.handOrder = handOrder;
	}

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "CHECK_ID")	
	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

}
