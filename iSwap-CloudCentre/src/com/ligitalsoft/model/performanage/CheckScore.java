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
 * 考核评分
 * @author liuxd
 * @mail liuxiaodong_315@163.com
 * @since 2011-04-16
 * @name com.ligitalsoft.model.CheckScore.java
 * @version 1.0
 */
@Entity
@Table(name = "PERF_CHECKSCORE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CheckScore extends LongIdObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3857384503522790888L;
	/**
	 * 手动打分
	 */
	private String mannulGrade;
	/**
	 * 自动打分
	 */
	private String sysGrade;
	/**
	 * 得分说明
	 */
	private String gradeExplain;
	/**
	 * 部门
	 */
	private Department department;
	/**
	 * 考核
	 */
	private Check check;
	/**
	 * 考核指标设置
	 */
	private CheckTargetSet checkTargetSet;
	/**
	 * 模板
	 */
	private Model model;
	
	
	public String getMannulGrade() {
		return mannulGrade;
	}

	public void setMannulGrade(String mannulGrade) {
		this.mannulGrade = mannulGrade;
	}

	public String getSysGrade() {
		return sysGrade;
	}

	public void setSysGrade(String sysGrade) {
		this.sysGrade = sysGrade;
	}

	public String getGradeExplain() {
		return gradeExplain;
	}
	
	public void setGradeExplain(String gradeExplain) {
		this.gradeExplain = gradeExplain;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_ID")	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "CHECK_ID")		
	public Check getCheck() {
		return check;
	}
	
	public void setCheck(Check check) {
		this.check = check;
	}
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "C_TARGET_SET_ID")			
	public CheckTargetSet getCheckTargetSet() {
		return checkTargetSet;
	}
	
	public void setCheckTargetSet(CheckTargetSet checkTargetSet) {
		this.checkTargetSet = checkTargetSet;
	}
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID")				
	public Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}

}
