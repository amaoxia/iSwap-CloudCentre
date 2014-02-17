package com.ligitalsoft.model.classification;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.ligitalsoft.model.changemanage.Item;
import com.ligitalsoft.model.system.SysDept;
/**
 * 
 * @author fangbin
 *
 */
@Entity
@Table(name = "DISTRIBUTEMSG_RULE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DistributeMsg extends LongIdObject {
	private static final long serialVersionUID = 1L;
	private String name;// 名称
	private String code;// 编码
	private String rule;// json规则
	private String status;// 状态
	private Item item;//指标名
	private SysDept dept;//部门
	private Date createDate;
	
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
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public SysDept getDept() {
		return dept;
	}
	public void setDept(SysDept dept) {
		this.dept = dept;
	}
	
}
