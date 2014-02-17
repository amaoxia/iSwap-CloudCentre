/*
 * @(#)TaskAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.action;




import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.ligitalsoft.datasharexchange.service.IChangeItemService;
import com.ligitalsoft.datasharexchange.service.IExchangeSendTaskService;
import com.ligitalsoft.datasharexchange.service.IReceTaskService;
import com.ligitalsoft.datasharexchange.service.ITaskService;
import com.ligitalsoft.model.changemanage.ReceiveResult;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
  
/**
 * 接收任务ACTION
 * @author daic
 * @since 2011-08-17 15:15:07
 * @name com.ligitalsoft.cloudstorage.action.ReceTaskAction.java
 * @version 1.0
 */
@Scope("prototype")
@Namespace("/exchange/recetask")
@Results( { })
@Action("receTaskAction")
public class ReceTaskAction extends FreemarkerBaseAction<ReceiveResult> {

    private static final long serialVersionUID = 5854980905054524517L;
	private IChangeItemService changeItemService;
	private IExchangeSendTaskService exchangeSendTaskService;
	private ITaskService taskService;
	private ISysDeptService sysDeptService;
	private ISysUserDeptService sysUserDeptService;
	private IReceTaskService receTaskService;
	
	@Override
	public String list() {
		return super.list();
	}
	@Autowired
	public void setReceTaskService(IReceTaskService receTaskService) {
		this.receTaskService = receTaskService;
	}
	@Autowired
	public void setSysUserDeptService(ISysUserDeptService sysUserDeptService) {
		this.sysUserDeptService = sysUserDeptService;
	}
	@Autowired
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}
	@Autowired
    public void setChangeItemService(IChangeItemService changeItemService) {
        this.changeItemService = changeItemService;
    }
	@Override
	protected IBaseServices<ReceiveResult> getEntityService() {
		return receTaskService;
	}
	
	
}
