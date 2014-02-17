/*
 * @(#)PermissionAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.utils.common.StringUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysPermission;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserDept;
import com.ligitalsoft.model.system.SysUserRole;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysPermissionService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;
import com.ligitalsoft.sysmanager.util.Costant;

/**
 * 菜单管理类
 * 
 * @author zhangx
 * @since May 16, 2011 1:43:59 PM
 * @name com.ligitalsoft.sysmanager.action.PermissionAction.java
 * @version 1.0
 */
@Scope("prototype")
@Namespace("/")
@Results({ @Result(name = "left", location = "left.jsp", type = "dispatcher") })
public class MenuAction extends FreemarkerBaseAction<SysPermission> {

	private static final long serialVersionUID = -2442126398325516402L;
	private ISysPermissionService sysPermissionService;
	private ISysUserRoleService sysUserRoleService;
	private ISysUserDeptService sysUserDeptService;
	private ISysDeptService sysDeptService;
	private List<SysPermission> lst = new ArrayList<SysPermission>();
	private String parentId;

	/**
	 * 前置机菜单
	 * 
	 * @return
	 */
	@Action("cloudNodeMenu")
	public String cloudNodeMenu() {
		SysUser user = (SysUser) getSession().get(Costant.SESSION_USER);
		JSONArray tree = null;
		if("administrator".equals(user.getUserName())){
			tree = sysPermissionService.cloudNodeMenu(id, null);
		}else{
			SysDept dept = getSysDept();
			Long deptId = null;
			if (null != dept) {
				if (!dept.getDeptUid().equalsIgnoreCase("XXZX")) {
					deptId = dept.getId();
				}
				tree = sysPermissionService.cloudNodeMenu(id, deptId);
			}
		}
		Struts2Utils.renderJson(tree, "encoding:GBK");
		return NONE;
	}

	/**
	 * 前置机子菜单
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@Action("childrenMenu")
	public String childrenMenu() throws ServiceException {
		SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
		try {
			if (!StringUtils.isBlank(parentId)) {
				JSONArray tree = sysPermissionService.cloudNodeChildrenMenu(
						parentId, getStringParameter("sysNum").trim(), "administrator".equals(user.getUserName())?null:getuserRoleId().getRoleId());
				Struts2Utils.renderJson(tree, "encoding:GBK");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	@Action("centerControlMenu")
	public String centerControlMenu() {
		JSONArray tree = null;
		SysUser sysUser = (SysUser)getSession().get(Costant.SESSION_USER);
		if("administrator".equals(sysUser.getUserName())){
			tree = sysPermissionService.centerControlMenu(id, null);
		}else{
			tree = sysPermissionService.centerControlMenu(id,
				getuserRoleId().getRoleId());
		}
		Struts2Utils.renderJson(tree, "encoding:GBK");
		return NONE;
	}

	@Action("menu")
	public String left() {
		SysUser sysUser = (SysUser)getSession().get(Costant.SESSION_USER);
		if("administrator".equals(sysUser.getUserName())){
			lst = sysPermissionService.findListRoot();
		}else{
			lst = sysPermissionService.findListRootByRoleId(getuserRoleId()
				.getRoleId());
		}
		return "left";
	}

	/**	
	 * 获取用户角色ID
	 * 
	 * @return
	 */
	private SysUserRole getuserRoleId() {
		SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
		if (user != null) {
			return sysUserRoleService.findByUserId(user.getId());
		}
		return null;
	}

	/**
	 * 获取用户部门ID
	 * 
	 * @return
	 */
	private SysUserDept getUserDeptId() {
		SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
		if (user != null) {
			return sysUserDeptService.findByUserId(user.getId());
		}
		return null;
	}

	/**
	 * 获取部门
	 * 
	 * @return
	 */
	private SysDept getSysDept() {
		try {
			SysUser user = (SysUser) getSession().get(Costant.SESSION_USER);
			if (null != user) {
				SysDept dept = sysDeptService.findById(getUserDeptId()
						.getDeptId());
				return dept;
			}
		} catch (ServiceException e) {
			log.error("未找到用户所属机构!", e);
		}
		return null;
	}

	@Override
	protected IBaseServices<SysPermission> getEntityService() {
		return sysPermissionService;
	}

	@Autowired
	public void setSysPermissionService(
			ISysPermissionService sysPermissionService) {
		this.sysPermissionService = sysPermissionService;
	}

	@Autowired
	public void setSysDeptService(ISysDeptService sysDeptService) {
		this.sysDeptService = sysDeptService;
	}

	@Autowired
	public void setSysUserRoleService(ISysUserRoleService sysUserRoleService) {
		this.sysUserRoleService = sysUserRoleService;
	}

	@Autowired
	public void setSysUserDeptService(ISysUserDeptService sysUserDeptService) {
		this.sysUserDeptService = sysUserDeptService;
	}

	public List<SysPermission> getLst() {
		return lst;
	}

	public void setLst(List<SysPermission> lst) {
		this.lst = lst;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
