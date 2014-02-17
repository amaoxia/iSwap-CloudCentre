/*
 * @(#)UserAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.dao.Constants;
import com.common.framework.dao.RequestHelper;
import com.common.framework.dao.SortPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysRole;
import com.ligitalsoft.model.system.SysRolePermisson;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserDept;
import com.ligitalsoft.model.system.SysUserRole;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysRolePermissonService;
import com.ligitalsoft.sysmanager.service.ISysRoleService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;
import com.ligitalsoft.sysmanager.service.ISysUserService;
import com.ligitalsoft.sysmanager.util.Costant;

/**
 * 用户管理Action
 * 
 * @author zhangx
 * @since May 16, 2011 1:32:32 PM
 * @name com.ligitalsoft.sysmanager.action.UserAction.java
 * @version 1.0
 */
@Namespace("/sysmanager/user")
@Scope("prototype")
@Results({
		@Result(name = "listAction", location = "user!list.action", type = "redirectAction"),
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker") })
public class UserAction extends FreemarkerBaseAction<SysUser> {

	private static final long serialVersionUID = 8400647891041123475L;
	private ISysUserService sysUserService;
	private ISysRoleService sysRoleService;
	private ISysUserRoleService sysUserRoleService;
	private ISysUserDeptService sysUserDeptService;
	private ISysDeptService sysDeptService;
	private ISysRolePermissonService sysRolePermissonService;
	private List<SysRole> roles = new ArrayList<SysRole>();// 角色集合
	private int state = 0;// 操作状态
	private String deptName;// 查询元素
	private String userName;// 查询元素
	private List<Object[]> obj = new ArrayList<Object[]>();
	private SysUserDept userDept;
	private SysUserRole userRole;
	private Character status;
	private SysDept sysDept;

	// 显示添加视图之前 操作
	@Override
	protected void onBeforeAddView() {
		super.onBeforeAddView();
		List<SysRole> sysRoles = sysRoleService.findAll();
		for (SysRole sysRole : sysRoles) {
			List<SysRolePermisson> permissons = sysRolePermissonService
					.findPermissionByRoleId(sysRole.getId());
			if (permissons != null && permissons.size() > 0) {
				roles.add(sysRole);
			}
		}
	}

	@Override
	@SuppressWarnings("static-access")
	public String add() {
		SysUser cUser = (SysUser) this.getSession().get(Costant.SESSION_USER);
		if (cUser == null) {
			this.errorInfo = "会话已过期,请重新登录!";
			return this.ERROR;
		}
		entityobj.setCreator(cUser.getUserName());
		if(entityobj!=null){
		String passWord=entityobj.getPassword();
		if(!StringUtils.isBlank(passWord)){
			passWord=StringUtils.getPasWordStr(passWord);
			entityobj.setPassword(passWord);
			}
		}
		sysUserService.saveOrUpdate(entityobj, userDept, userRole);
		return RELOAD;
	}

	@Override
	protected void onBeforeUpdateView() {
		super.onBeforeUpdateView();
		List<SysRole> sysRoles = sysRoleService.findAll();
		for (SysRole sysRole : sysRoles) {
			List<SysRolePermisson> permissons = sysRolePermissonService
					.findPermissionByRoleId(sysRole.getId());
			if (permissons != null && permissons.size() > 0) {
				roles.add(sysRole);
			}
		}
		userDept = sysUserDeptService.findByUserId(id);
		userRole = sysUserRoleService.findByUserId(id);
		if (userDept != null) {
			try {
				sysDept = sysDeptService.findById(userDept.getDeptId());
			} catch (ServiceException e) {
				log.error("查找部门对象失败!");
			}
		}
	}

	@Override
	protected void onBeforeView() {
		super.onBeforeView();
		roles = sysRoleService.findAll();
		if (id != null) {
			userRole = sysUserRoleService.findByUserId(id);
			userDept = sysUserDeptService.findByUserId(id);
		}
		if (userDept != null) {
			try {
				sysDept = sysDeptService.findById(userDept.getDeptId());
			} catch (ServiceException e) {
				log.error("查找部门对象失败!");
			}
		}
	}

	@Override
	@SuppressWarnings("static-access")
	public String delete() {
		try {
			if (ids != null && ids.length > 0) {
				sysUserService.deleteAllByIds(ids);
			}
		} catch (ServiceException e) {
			this.errorInfo = "有关数据正在使用,删除数据失败!";
			return this.ERROR;
		}
		return "listAction";
	}

	/**
	 * 用户注册 检查邮箱是否唯一
	 * 
	 * @return
	 * @author zhangx
	 * @2010-12-30 下午08:10:24
	 */
	public String checkEmail() {
		String result = "";
		String id = getHttpServletRequest().getParameter("id");
		String email = getHttpServletRequest().getParameter("email").trim();
		getHttpServletResponse().setCharacterEncoding("GBK");
		try {
			SysUser user = sysUserService.findUniqueByProperty("email", email);
			if (user == null) {
				result = "succ";
			} else {
				if (!StringUtils.isBlank(id)) {
					if (user.getId().toString().equals(id)) {
						result = "succ";
					}
				}
			}
			Struts2Utils.renderText(result, "encoding:GBK");
		} catch (ServiceException e) {
			log.error("UserAction exception", e);
		}
		return null;
	}

	/**
	 * 用户登录 检查登录名称是否存在
	 * 
	 * @return
	 * @author zhangx
	 * @2010-12-30 下午08:16:30
	 */
	public String checkName() {
		String loginName = getHttpServletRequest().getParameter("loginName")
				.trim();
		String id = getHttpServletRequest().getParameter("id");
		String result = "";
		getHttpServletResponse().setCharacterEncoding("GBK");
		try {
			SysUser user = sysUserService.findUniqueByProperty("loginName",
					loginName);
			if (user == null) {
				result = "succ";
			} else {
				if (!StringUtils.isBlank(id)) {
					if (user.getId().toString().equals(id)) {
						result = "succ";
					}
				}
			}
			Struts2Utils.renderText(result, "encoding:GBK");
		} catch (ServiceException e) {
			log.error("userService invoke exception ", e);
		}
		return null;
	}

	/**
	 * 检查用户标识符是否存在
	 * 
	 * @return
	 * @author zhangx
	 */
	public String checkUid() {
		String userUid = getHttpServletRequest().getParameter("userUid").trim();
		String id = getHttpServletRequest().getParameter("id");
		String result = "";
		getHttpServletResponse().setCharacterEncoding("GBK");
		try {
			SysUser user = sysUserService.findUniqueByProperty("userUid",
					userUid);
			if (user == null) {
				result = "succ";
			} else {
				if (!StringUtils.isBlank(id)) {
					if (user.getId().toString().equals(id)) {
						result = "succ";
					}
				}
			}
			Struts2Utils.renderText(result, "encoding:GBK");
		} catch (ServiceException e) {
			log.error("userService invoke exception ", e);
		}
		return null;
	}

	@Override
	public String list() {
		sortParas.addAll(RequestHelper
				.getOrderParametersWithOrder(getHttpServletRequest()));// 在页面页面上的格式order[age]
		Map<String, String> map = new HashMap<String, String>();
		if (sortParas.size() == 0) {
			sortParas.add(new SortPara("u.id", Constants.DESC));// 初始以主键ID排序
		}
		if (!StringUtils.isBlank(userName)) {
			map.put("userName", userName);
		}
		if (!StringUtils.isBlank(deptName)) {
			map.put("deptName", deptName);
		}
		obj = sysUserService.findUserListByPage(page, map, sortParas);
		return LIST;
	}

	@Override
	@SuppressWarnings("static-access")
	public String update() {
		try {
			if (validData(entityobj)) {
				sysUserService.saveOrUpdate(entityobj, userDept, userRole);
			}
			return RELOAD;
		} catch (Exception e) {
			this.errorInfo = "修改数据失败，请稍候再试!";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	/**
	 * 设置用户状态
	 * 
	 * @return
	 * @author zhangx
	 */
	public String updateStatus() {
		sysUserService.updateStatus(ids, status);
		return "listAction";
	}

	// Override start
	@Override
	protected IBaseServices<SysUser> getEntityService() {
		return sysUserService;
	}

	// Override end
	// service start
	@Autowired
	public void setSysRoleService(ISysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	@Autowired
	public void setSysDeptService(ISysDeptService sysDeptService) {
		this.sysDeptService = sysDeptService;
	}

	@Autowired
	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	@Autowired
	public void setSysUserRoleService(ISysUserRoleService sysUserRoleService) {
		this.sysUserRoleService = sysUserRoleService;
	}

	@Autowired
	public void setSysUserDeptService(ISysUserDeptService sysUserDeptService) {
		this.sysUserDeptService = sysUserDeptService;
	}

	@Autowired
	public void setSysRolePermissonService(
			ISysRolePermissonService sysRolePermissonService) {
		this.sysRolePermissonService = sysRolePermissonService;
	}

	// service end

	// property start
	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Object[]> getObj() {
		return obj;
	}

	public void setObj(List<Object[]> obj) {
		this.obj = obj;
	}

	public SysUserDept getUserDept() {
		return userDept;
	}

	public void setUserDept(SysUserDept userDept) {
		this.userDept = userDept;
	}

	public SysUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(SysUserRole userRole) {
		this.userRole = userRole;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	public ISysDeptService getSysDeptService() {
		return sysDeptService;
	}
	// property end

}
