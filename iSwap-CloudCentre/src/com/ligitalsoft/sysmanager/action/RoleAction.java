/*
 * @(#)RoleAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.action;

import java.util.List;


import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.model.system.SysRole;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserRole;
import com.ligitalsoft.sysmanager.service.ISysRoleService;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;
import com.ligitalsoft.sysmanager.util.Costant;

/**
 * 角色管理Action
 * 
 * @author zhangx
 * @since May 16, 2011 1:42:06 PM
 * @name com.ligitalsoft.sysmanager.action.RoleAction.java
 * @version 1.0
 */
@Namespace("/sysmanager/role")
@Scope("prototype")
@Results({
		@Result(name = StrutsAction.LIST, location = "list.ftl", type = "freemarker"),
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
		@Result(name = "listAction", location = "role!list.action", type = "redirectAction") })
public class RoleAction extends FreemarkerBaseAction<SysRole> {

	private static final long serialVersionUID = -6227533454503679880L;
	private ISysRoleService sysRoleService;
	private ISysUserRoleService sysUserRoleService;

	/**
	 * 角色树
	 * 
	 * @return
	 * @author zhangx
	 */
	public String roleTree() {
		JSONArray roleTree = sysRoleService.roleTree();
		if (roleTree != null) {
			Struts2Utils.renderJson(roleTree, "encoding:GBK");
		}

		return null;
	}

	/**
	 * 删除角色
	 */
	@Override
	@SuppressWarnings("static-access")
	public String delete() {
		try {
			if (ids != null && ids.length > 0) {
				if (isExist(ids)) {
					sysRoleService.deleteAllByIds(ids);
				} else {
					this.errorInfo = "有关数据正在使用,删除数据失败!";
					return this.ERROR;
				}
			}
		} catch (ServiceException e) {
			this.errorInfo = "有关数据正在使用,删除数据失败!";
			return this.ERROR;
		}
		return "listAction";
	}

	/**
	 * 添加之前操作
	 */
	@Override
	protected void onBeforeAdd() {
		SysUser cUser = (SysUser) this.getSession().get(Costant.SESSION_USER);
		entityobj.setCreator(cUser.getUserName());
		super.onBeforeAdd();
	}

	/**
	 * 角色查看
	 * 
	 * @return
	 * @author zhangx
	 */
	public String roleView() {
		if (id != null) {
			try {
				entityobj = sysRoleService.findById(id);
			} catch (ServiceException e) {
				this.errorInfo = "查找角色信息失败!";
				log.error(this.errorInfo, e);
				return ERROR;
			}
		}
		return "roleView";
	}

	/**
	 * 检查角色编码是否重复
	 * 
	 * @return
	 * @author zhangx
	 */
	public String checkCode() {
		String result = "";
		String id = getHttpServletRequest().getParameter("id");
		String code = getHttpServletRequest().getParameter("code").trim();
		getHttpServletResponse().setCharacterEncoding("GBK");
		try {
			SysRole role = sysRoleService.findUniqueByProperty("code", code);
			if (role == null) {
				result = "succ";
			} else {
				if (!StringUtils.isBlank(id)) {
					if (role.getId().toString().equals(id)) {
						result = "succ";
					}
				}
			}
			Struts2Utils.renderText(result, "encoding:GBK");
		} catch (ServiceException e) {
			log.error("DeptAction exception", e);
		}
		return null;
	}

	/**
	 * 判断角色下 是否有用户
	 * 
	 * @return
	 */
	private boolean isExist(Long ids[]) {
		boolean fa = true;
		for (Long id : ids) {
			List<SysUserRole> usreRoles = sysUserRoleService
					.findListByRoleId(id);
			if (usreRoles != null && usreRoles.size() > 0) {
				fa = false;
				break;
			}
		}
		return fa;
	}

	@Autowired
	public void setSysRoleService(ISysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	@Autowired
	public void setSysUserRoleService(ISysUserRoleService sysUserRoleService) {
		this.sysUserRoleService = sysUserRoleService;
	}

	@Override
	protected IBaseServices<SysRole> getEntityService() {
		return sysRoleService;
	}
}
