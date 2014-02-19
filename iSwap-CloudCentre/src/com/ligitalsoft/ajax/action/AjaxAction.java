/*
 * @(#)DeptAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.ajax.action;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.view.StrutsAction;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.ajax.service.IAjaxService;
import com.ligitalsoft.sysmanager.service.ISysDeptService;


/**
 * Ajaz Action
 * 
 * @author zhangx
 * @since May 16, 2011 1:40:13 PM
 * @name com.ligitalsoft.ajax.action.AjaxAction.java
 * @version 1.0
 */
@Namespace("/ajax")
@Scope("prototype")
public class AjaxAction extends StrutsAction {
	
	private ISysDeptService sysDeptService;
	private IAjaxService ajaxService;
	
	
	/**
	 * 应用指标管理下-交换指标管理-数据接收部门下拉树
	 * @return
	 */
	public String getDeptTree4AppItemExchangeConf() {
		String appMsgId = getStringParameter("appMsgId");
		String appItemId = getStringParameter("appItemId");
		String sendDeptId = getStringParameter("sendDeptId");
		JSONArray deptTree = ajaxService.depTree4AppItemExchangeConf(Long.parseLong(appMsgId), Long.parseLong(appItemId), Long.parseLong(sendDeptId));
		Struts2Utils.renderJson(deptTree, "encoding:GBK");
		return null;
	}
	
	@Autowired
	public void setSysDeptService(ISysDeptService sysDeptService) {
		this.sysDeptService = sysDeptService;
	}
	
	@Autowired
	public void setAjaxService(IAjaxService ajaxService) {
		this.ajaxService = ajaxService;
	}
}
