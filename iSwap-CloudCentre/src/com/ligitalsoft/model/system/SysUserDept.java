/*
 * @(#)SysUserDept.java
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
 * 机构 _用户关系表
 * @author  zhangx
 * @since   May 13, 2011 4:24:17 PM
 * @name    com.ligitalsoft.model.system.SysUserDept.java
 * @version 1.0
 */
@Table(name="SYS_USERDEPT")
@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class SysUserDept extends LongIdObject {

	private static final long serialVersionUID = -5428671321423146045L;
	
	private Long deptId;
	private Long userId;

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}

