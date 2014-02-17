/*
 * @(#)LoginAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.framework.exception.ServiceException;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.model.system.SysRole;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserRole;
import com.ligitalsoft.sysmanager.service.ISysRoleService;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;
import com.ligitalsoft.sysmanager.service.ISysUserService;
import com.ligitalsoft.sysmanager.util.Costant;

/**
 * 用户登录Action
 * 
 * @author zhangx
 * @since May 24, 2011 6:44:19 PM
 * @name com.ligitalsoft.sysmanager.action.LoginAction.java
 * @version 1.0
 */
@Namespace("/")
@Results({
		@Result(name = "input", location = "login.ftl", type = "freemarker"),
		@Result(name = "firstViews", location = "menu.action", type = "redirect"),
		@Result(name = "index", location = "index.ftl", type = "freemarker"),
		@Result(name = "loginView", location = "loginView.action", type = "redirect") })
public class LoginAction extends StrutsAction {

	private static final long serialVersionUID = -3552596184831841181L;

	private String loginName;// 用户名
	private String password;// 密码
	private String loginNameMsg;// 用户名严重信息
	private String passwordMsg;// 密码验证信息
	private String checkValue;// 是否写入cookie

	private ISysUserService sysUserService;// service
	private ISysUserRoleService sysUserRoleService;// 用户_角色service
	private ISysRoleService roleService;
	// private ISysPermissionService sysPermissionService;// 菜单service
	private final static String COOKIENAME = "secretKey";// cookie名称
	private final static int COOKIEMAXAGE = 60 * 60 * 24 * 30;// cooke有效期限

	/**
	 * 用户登录界面
	 * 
	 * @return
	 * @author zhangx
	 */
	@Action("loginView")
	public String input() {
		Cookie cookie = getCurrentCookie(COOKIENAME);
		if (cookie != null) {
			String temp = cookie.getValue();
			if (!StringUtils.isBlank(temp)) {
				String[] values = temp.split("_");
				loginName = values[0];
				password = values[1];
				checkValue = "1";
			}
		}
		return INPUT;
	}

	/**
	 * 用户登录界面
	 * 
	 * @return
	 * @author zhangx
	 */
	@Action("firstViews")
	public String firstViews() {
		SysUser sysUser = (SysUser) getSession().get(Costant.SESSION_USER);
		SysUserRole sysUserRole = sysUserRoleService.findByUserId(sysUser
				.getId());
		if (sysUserRole != null) {
			SysRole role;
			try {
				role = roleService.findById(sysUserRole.getRoleId());
				if (role != null) {
					getHttpServletRequest().setAttribute("roleName",
							role.getName());
				}
			} catch (ServiceException e) {
				loginNameMsg = "系统不存在此用户，请您核实用户名";
				return INPUT;
			}

		}
		/*
		 * Cookie cookie = getCurrentCookie(COOKIENAME); if (cookie != null) {
		 * String temp = cookie.getValue(); if (!StringUtils.isBlank(temp)) {
		 * String[] values = temp.split("_"); loginName = values[0]; password =
		 * values[1]; checkValue = "1"; } }
		 */
		return "index";
	}

	/**
	 * 用户登录验证
	 * 
	 * @return
	 * @author zhangx
	 */
	@Action("login")
	public String login() {
		boolean login = false;
		SysUser sysUser = new SysUser();
		sysUser.setUserName("administrator");
		try {
			if (!StringUtils.isBlank(loginName)) {
				if("administrator".equals(loginName)){
					if (!StringUtils.isBlank(password)) {
						if ("f865b53623b121fd34ee5426c792e5c33af8c227".equals(StringUtils.getPasWordStr(password.trim()))) {
							login = true;
						}
					}
				}else{
					sysUser = sysUserService.findUniqueByProperty(
							"loginName", loginName.trim());
					if (sysUser == null) {
						cleanProperty();
						loginNameMsg = "系统不存在此用户，请您核实用户名";
						return INPUT;
					}
					// 判断用户状态
					if (sysUser.getStatus() == '0') {
						cleanProperty();
						loginNameMsg = "用户已被禁用，请联系管理员";
						return INPUT;
					}
					if (!StringUtils.isBlank(password)) {
						String  newpwd="";
						newpwd=StringUtils.getPasWordStr(password.trim());
						if (sysUser.getPassword().equals(newpwd)) {
							login = true;
						} else {
							passwordMsg = "密码不正确，请您核实后重新输入";
							cleanProperty();
							return INPUT;
						}
					} else {
						passwordMsg = "密码不能为空，请您核实后重新输入";
						cleanProperty();
						return INPUT;
					}
				}
				if(login){
						if (!StringUtils.isBlank(checkValue)
							&& checkValue.equals("1")) {// 是否保存信息
						String value = loginName + "_" + password.trim();
						Cookie cookie = new Cookie(COOKIENAME, value);
						cookie.setPath("/");// 设置cookie路径
						cookie.setMaxAge(COOKIEMAXAGE);// 有效日期30天
						this.getHttpServletResponse().addCookie(cookie);
					} else {
						Cookie cookie = getCurrentCookie(COOKIENAME);
						if (cookie != null) {
							cookie.setValue("");
							cookie.setMaxAge(0);
							cookie.setPath("/");
							this.getHttpServletResponse().addCookie(cookie);
						}
					}
					getSession().put(Costant.SESSION_USER, sysUser);
					getHttpServletRequest().setAttribute("userName",
							sysUser.getUserName());// 前台展示用户名
					if("administrator".equals(loginName)){
						getHttpServletRequest().setAttribute(
								"roleName", "超级管理员");
					}else{
						SysUserRole sysUserRole = sysUserRoleService
								.findByUserId(sysUser.getId());
						if (sysUserRole != null) {
							SysRole role = roleService.findById(sysUserRole
									.getRoleId());
							if (role != null) {
								getHttpServletRequest().setAttribute(
										"roleName", role.getName());
							}
	//							List<SysPermission> persmissions = sysPermissionService
	//									.findListExcludeByRoleId(sysUserRole
	//											.getRoleId());
	//							getSession()
	//									.put(Costant.SESSION_ROLE, persmissions);
						}
					}
				}
			} else {
				loginNameMsg = "用户名不能为空，请您核实用户名";
				cleanProperty();
				return INPUT;
			}
		} catch (ServiceException e) {
			loginNameMsg = "系统不存在此用户，请您核实用户名";
			return INPUT;
		}
		return "firstViews";
	}

	/**
	 * 退出系统
	 * 
	 * @return
	 * @author zhangx
	 */
	@Action("logout")
	public String logout() {
		HttpSession session = Struts2Utils.getSession();
		session.removeAttribute(Costant.SESSION_USER);// 清空session
		return "loginView";
	}

	/**
	 * 得到当前cookie对象
	 * 
	 * @param key
	 * @return
	 * @author zhangx
	 */
	public Cookie getCurrentCookie(String key) {
		Cookie currCookie = null;
		Cookie[] cookie = this.getHttpServletRequest().getCookies();
		if (cookie != null) {
			for (Cookie ck : cookie) {
				if (ck.getName().equals(key)) {
					currCookie = ck;
					break;
				}
			}
		}
		return currCookie;
	}

	public static void main(String[] args) {
		String pwd = "admin    ";
		System.out.println(StringUtils.getPasWordStr(pwd));
	}

	/**
	 * 清空属性值
	 * 
	 * @author zhangx
	 */
	public void cleanProperty() {
		loginName = "";
		password = "";
		checkValue = "";
	}

	// property start
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginNameMsg() {
		return loginNameMsg;
	}

	public void setLoginNameMsg(String loginNameMsg) {
		this.loginNameMsg = loginNameMsg;
	}

	public String getPasswordMsg() {
		return passwordMsg;
	}

	public void setPasswordMsg(String passwordMsg) {
		this.passwordMsg = passwordMsg;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	// property end

	// service
	@Autowired
	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	@Autowired
	public void setSysUserRoleService(ISysUserRoleService sysUserRoleService) {
		this.sysUserRoleService = sysUserRoleService;
	}

	// @Autowired
	// public void setSysPermissionService(
	// ISysPermissionService sysPermissionService) {
	// this.sysPermissionService = sysPermissionService;
	// }

	@Autowired
	public void setRoleService(ISysRoleService roleService) {
		this.roleService = roleService;
	}

}
