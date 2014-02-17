/*
 * @(#)ISysUserRoleService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.service;

import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.system.SysUserRole;

/**
 * 用户角色service
 * 
 * @author zhangx
 * @since May 26, 2011 1:29:11 AM
 * @name com.ligitalsoft.sysmanager.service.ISysUserRoleService.java
 * @version 1.0
 */

public interface ISysUserRoleService extends IBaseServices<SysUserRole> {

	/**
	 * 根据用户Id查询对象
	 * 
	 * @param userId
	 * @return
	 * @author zhangx
	 */
	public SysUserRole findByUserId(Long userId);

	/**
	 * 角色下用户列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<SysUserRole> findListByRoleId(Long roleId);
}
