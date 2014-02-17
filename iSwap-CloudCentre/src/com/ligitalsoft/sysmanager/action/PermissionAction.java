/*
 * @(#)PermissionAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.model.system.SysPermission;
import com.ligitalsoft.model.system.SysRolePermisson;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserRole;
import com.ligitalsoft.sysmanager.service.ISysPermissionService;
import com.ligitalsoft.sysmanager.service.ISysRolePermissonService;
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
@Namespace("/sysmanager/permission")
@Scope("prototype")
@Results({
		@Result(name = "main", location = "main.ftl", type = "freemarker"),
		@Result(name = "permission", location = "permission.ftl", type = "freemarker") })
public class PermissionAction extends FreemarkerBaseAction<SysPermission> {

	private static final long serialVersionUID = -2442126398325516402L;
	private ISysPermissionService sysPermissionService;
	private Long roleId;// 角色ID
	private ISysRolePermissonService sysRolePermissonService;
	private List<SysRolePermisson> lst = new ArrayList<SysRolePermisson>();
	private ISysUserRoleService sysUserRoleService;

	/**
	 * 根据用户权限生成菜单权限 xml 数据形式
	 * 
	 * @author zhangx
	 */
	public void createPermissionXml() {
		HttpSession session = this.getHttpServletRequest().getSession();
		SysUser user = (SysUser) session.getAttribute(Costant.SESSION_USER);
		if (user == null) {
			log.error("会话过期,请重新登录!");
			return;
		}
		SysUserRole userRole = sysUserRoleService.findByUserId(user.getId());
		if (userRole == null) {
			log.error("用户未分配权限");
			return;
		}
		Document document = sysPermissionService.createPermissionXml(userRole
				.getRoleId());
		Struts2Utils.render("text/xml", document.asXML(), "encoding:UTF-8");
	}

	/**
	 * 菜单树
	 * 
	 * @return
	 * @author zhangx
	 */
	public String permissionTree() {
		JSONArray permissionTree = sysPermissionService.permissionTree(roleId);
		Struts2Utils.renderJson(permissionTree, "encoding:GBK");
		return null;
	}

	/**
	 * 菜单树界面
	 * 
	 * @return
	 * @author zhangx
	 */
	public String permission() {
		return "permission";
	}

	/**
	 * 权限显示主页面
	 * 
	 * @return
	 * @author zhangx
	 */
	public String main() {
		return "main";
	}

	/**
	 * 添加权限，角色信息
	 * 
	 * @return
	 * @author zhangx
	 */
	public String addPermissionRole() {
		int falg = 1;
		String permissionIds = this.getHttpServletRequest().getParameter(
				"permissionIds");
		try {
			sysRolePermissonService.addByIds(permissionIds, roleId);
		} catch (ServiceException e) {
			falg = 0;
			log.error("批量添加角色_权限信息失败!", e);
		}
		Struts2Utils.renderText(falg + "", "encoding:gbk");
		return null;
	}


	@Override
	protected IBaseServices<SysPermission> getEntityService() {
		return sysPermissionService;
	}

	// Service start
	@Autowired
	public void setSysPermissionService(
			ISysPermissionService sysPermissionService) {
		this.sysPermissionService = sysPermissionService;
	}

	@Autowired
	public void setSysRolePermissonService(
			ISysRolePermissonService sysRolePermissonService) {
		this.sysRolePermissonService = sysRolePermissonService;
	}

	@Autowired
	public void setSysUserRoleService(ISysUserRoleService sysUserRoleService) {
		this.sysUserRoleService = sysUserRoleService;
	}

	// Service end

	// property start
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<SysRolePermisson> getLst() {
		return lst;
	}

	public void setLst(List<SysRolePermisson> lst) {
		this.lst = lst;
	}
	// property end
}
