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
/**
 * 考核总分排名表
 * @author liuxd
 * @mail liuxiaodong_315@163.com
 * @since 2011-04-16
 * @name com.ligitalsoft.model.CheckRank.java
 * @version 1.0
 */
@Entity
@Table(name = "PERF_CHECKRANK")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CheckRank extends LongIdObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4792555784860595195L;
	/**
	 * 总分
	 */
	private int totalPoint;
	/**
	 * 手工排序
	 */
	private int handOrder;
	/**
	 * 状态,1.汇总表, 2.排名表
	 */
	private String status;
	/**
	 * 部门模板关系
	 */
	private DeptModelContact deptModelContact;
	/**
	 * 考核
	 */
	private Check check;
	

	public int getTotalPoint() {
		return totalPoint;
	}
	
	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}
	
	public int getHandOrder() {
		return handOrder;
	}
	
	public void setHandOrder(int handOrder) {
		this.handOrder = handOrder;
	}
	
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_ID")	
	public DeptModelContact getDeptModelContact() {
		return deptModelContact;
	}
    
	public void setDeptModelContact(DeptModelContact deptModelContact) {
		this.deptModelContact = deptModelContact;
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
