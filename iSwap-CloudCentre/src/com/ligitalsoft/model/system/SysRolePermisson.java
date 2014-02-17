/*
 * @(#)SysRolePermisson.java
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
 * 角色_权限关系表
 * 
 * @author zhangx
 * @since May 13, 2011 4:19:29 PM
 * @name com.ligitalsoft.model.system.SysRolePermisson.java
 * @version 1.0
 */
@Table(name = "SYS_ROLEPERMISSION")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysRolePermisson extends LongIdObject {

	private static final long serialVersionUID = 6738166004084621805L;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 权限ID
	 */
	private Long permissionId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
}
