package com.ligitalsoft.model.changemanage;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.StringIdObject;
import com.ligitalsoft.model.performanage.Department;

/**
 * 指标项管理
 * @author liuxd
 * @mail liuxiaodong_315@163.com
 * @since 2011-04-16
 * @name com.ligitalsoft.Department.java
 * @version 1.0
 */
@Entity
@Table(name = "item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Item extends StringIdObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8985296592200763008L;
	/**
	 * 指标项名称
	 */
	private String name;
	/**
	 * 指标项代码
	 */
	private String code;
	/**
	 * 时间字段名
	 */
	private String fieldName;
	/**
	 * 交换周期
	 */
	private String exchangeCycle;
	/**
	 * 规定交换日期规则
	 */
	private String exchangeDateRule;
	/**
	 * 是否采用系统默认规则
	 */
	private String useDefaultRule;
	/**
	 * 绿灯规则
	 */
	private String ruleGreenNotify;
	/**
	 * 黄灯规则
	 */
	private String ruleYellowNotify;
	/**
	 * 红灯规则
	 */
	private String ruleRedNotify;
	/**
	 * 是否全量发送
	 */
	private String isAllSend;
	/**
	 * 部门
	 */
	private Department department;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getExchangeCycle() {
		return exchangeCycle;
	}
	public void setExchangeCycle(String exchangeCycle) {
		this.exchangeCycle = exchangeCycle;
	}
	public String getExchangeDateRule() {
		return exchangeDateRule;
	}
	public void setExchangeDateRule(String exchangeDateRule) {
		this.exchangeDateRule = exchangeDateRule;
	}
	public String getRuleGreenNotify() {
		return ruleGreenNotify;
	}
	public void setRuleGreenNotify(String ruleGreenNotify) {
		this.ruleGreenNotify = ruleGreenNotify;
	}
	public String getRuleYellowNotify() {
		return ruleYellowNotify;
	}
	public void setRuleYellowNotify(String ruleYellowNotify) {
		this.ruleYellowNotify = ruleYellowNotify;
	}
	public String getRuleRedNotify() {
		return ruleRedNotify;
	}
	public void setRuleRedNotify(String ruleRedNotify) {
		this.ruleRedNotify = ruleRedNotify;
	}
	public String getUseDefaultRule() {
		return useDefaultRule;
	}
	public void setUseDefaultRule(String useDefaultRule) {
		this.useDefaultRule = useDefaultRule;
	}
	public String getIsAllSend() {
		return isAllSend;
	}
	public void setIsAllSend(String isAllSend) {
		this.isAllSend = isAllSend;
	}
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_ID")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	

}
