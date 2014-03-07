/*
 * @(#)ItemAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.appitemmgr.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.ligitalsoft.appitemmgr.service.AppItemExchangeConfService;
import com.ligitalsoft.model.appitemmgr.AppItemExchangeConf;

/**
 * 
 * 指标交换配置
 * @Company 中海纪元
 * @author  Administrator
 * @mail    moujunhu@163.com
 * @since   2013-12-11下午3:22:11
 * @name    ItemAction.java
 * @version iSwap V1.0
 * @Team    研发中心
 */
@Scope("prototype")
@Namespace("/appitemmgr/appItemExchangeConf")
@Action("appItemExchangeConf")
@Results({ @Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
        @Result(name = "listAction", location = "appItemExchangeConf!list.action", type = "redirectAction", params = {}) })
public class AppItemExchangeConfAction extends FreemarkerBaseAction<AppItemExchangeConf> {

    private static final long serialVersionUID = 5854980905054524517L;
	
    private AppItemExchangeConfService appItemExchangeConfService;
    
    private List<AppItemExchangeConf> appItemExchangeConfList;
    
    private AppItemExchangeConf appItemExchangeConf;
    
	@Override
	public String add() {
		appItemExchangeConfService.addEntitys(appItemExchangeConfList);
		return StrutsAction.RELOAD;
	}
	
	public String addAppItemExchangeConf() {
		appItemExchangeConfService.addEntity(appItemExchangeConf);
		return StrutsAction.RELOAD;
	}
	
	@Override
	public String update() {
		try{
			appItemExchangeConfService.updateEntity(appItemExchangeConf);
			return StrutsAction.RELOAD;
		}catch(Exception e){
			log.error("AppItemExchangeConfService invoke delete exception ", e);
			return ERROR;
		}
		
	}

	@Override
	public String delete() {
		try{
			appItemExchangeConfService.deleteEntityByIds(ids);
			return "listAction";
		}catch(Exception e){
			log.error("AppItemExchangeConfService invoke delete exception ", e);
			return ERROR;
		}
	}
	
	public List<AppItemExchangeConf> getAppItemExchangeConfList() {
		return appItemExchangeConfList;
	}

	public void setAppItemExchangeConfList(List<AppItemExchangeConf> appItemExchangeConfList) {
		this.appItemExchangeConfList = appItemExchangeConfList;
	}
	
	public AppItemExchangeConf getAppItemExchangeConf() {
		return appItemExchangeConf;
	}

	public void setAppItemExchangeConf(AppItemExchangeConf appItemExchangeConf) {
		this.appItemExchangeConf = appItemExchangeConf;
	}

	@Autowired
	public void setAppItemExchangeConfService(
			AppItemExchangeConfService appItemExchangeConfService) {
		this.appItemExchangeConfService = appItemExchangeConfService;
	}

	@Override
	protected IBaseServices<AppItemExchangeConf> getEntityService() {
		return appItemExchangeConfService;
	}

}
