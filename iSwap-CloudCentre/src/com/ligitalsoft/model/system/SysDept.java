/*
 * @(#)SysDept.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;

/**
 * 机构信息
 * 
 * @author zhangx
 * @since May 13, 2011 3:33:01 PM
 * @name com.ligitalsoft.model.system.SysDept.java
 * @version 1.0
 */
@Entity
@Table(name = "SYS_DEPT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysDept extends LongIdObject {

	private static final long serialVersionUID = 315916606665703731L;
	/**
	 * 上级机构ID
	 */
	// private Long fatherid;
	/**
	 * 机构标识符
	 */
	private String deptUid;
	/**
	 * 机构名称
	 */
	private String deptName;
	/**
	 * 机构简称
	 */
	private String shortName;
	/**
	 * 组织机构代码
	 */
	private String orgCode;
	/**
	 * 机构地址
	 */
	private String address;
	/**
	 * 机构联系人
	 */
	private String linkman;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 创建时间
	 */
	private Date dateCreate = DateUtil.toDate(new Date());
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 排序
	 */
	private Integer level;
	/**
	 * 机构描述
	 */
	private String descript;
	/**
	 * 删除状态 0,删除 1,启用
	 */
	private Character status = '1';

	/**
	 * 父机构
	 */
	private SysDept sysDept;
	/**
	 * 子类机构
	 */
	private List<SysDept> childrenDept = new ArrayList<SysDept>();
	/**
	 * 部门图标路径
	 */
	private String logoPath;

	

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "fatherid")
	@Fetch(FetchMode.SELECT)
	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	// public Long getFatherid() {
	// return fatherid;
	// }
	//
	// public void setFatherid(Long fatherid) {
	// this.fatherid = fatherid;
	// }

	public String getDeptUid() {
		return deptUid;
	}

	public void setDeptUid(String deptUid) {
		this.deptUid = deptUid;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	@OneToMany(mappedBy = "sysDept", cascade = { CascadeType.ALL })
	public List<SysDept> getChildrenDept() {
		return childrenDept;
	}

	public void setChildrenDept(List<SysDept> childrenDept) {
		this.childrenDept = childrenDept;
	}
}
