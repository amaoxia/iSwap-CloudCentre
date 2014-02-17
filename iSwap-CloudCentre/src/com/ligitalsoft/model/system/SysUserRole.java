/*
 * @(#)SysUserRloe.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.system;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * 用户_角色关系表
 * 
 * @author zhangx
 * @since May 13, 2011 4:22:27 PM
 * @name com.ligitalsoft.model.system.SysUserRloe.java
 * @version 1.0
 */
@Table(name = "SYS_USERROLE")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUserRole extends LongIdObject {

	private static final long serialVersionUID = -6870805130134464990L;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 角色ID
	 */
	private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
