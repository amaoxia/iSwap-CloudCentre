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
 * 考核指标
 * @author liuxd
 * @mail liuxiaodong_315@163.com
 * @since 2011-04-15
 * @name com.ligitalsoft.model.CheckTarget.java
 * @version 1.0
 */
@Entity
@Table(name = "PERF_CHECKTARGET")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CheckTarget extends LongIdObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1634386000414133472L;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 考核项编码
	 */
	private String checkCode;
	/**
	 * 考核项内容和标准
	 */
	private String content;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 父节点
	 */
	private CheckTarget parent;
	/**
	 * 状态:1,冻结 2,激活
	 */	
	private String status;
	/**
	 * 状态:1,自动打分 2,手动打分
	 */	
	private String isAutoScore;
	/**
	 * 排序
	 */	
	private int sort;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getCheckCode() {
		return checkCode;
	}
	
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")	
	public CheckTarget getParent() {
		return parent;
	}

	public void setParent(CheckTarget parent) {
		this.parent = parent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSort() {
		return sort;
	}
	
	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getIsAutoScore() {
		return isAutoScore;
	}

	public void setIsAutoScore(String isAutoScore) {
		this.isAutoScore = isAutoScore;
	}  
	
}
