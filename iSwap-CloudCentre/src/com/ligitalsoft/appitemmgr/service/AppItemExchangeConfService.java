/*
 * @(#)AppMsgService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.appitemmgr.service;

import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.appitemmgr.AppItemExchangeConf;

/**
 * @author  lifh
 * @mail    wslfh2005@163.com
 * @since   2011-6-15 上午10:30:34
 * @name    com.ligitalsoft.cloudcenter.service.AppMsgService.java
 * @version 1.0
 */

public interface AppItemExchangeConfService extends IBaseServices<AppItemExchangeConf>{
	
	public List<AppItemExchangeConf> findAllByProperty();
	
	public AppItemExchangeConf addEntity(AppItemExchangeConf appItemExchangeConf);
	
	public AppItemExchangeConf updateEntity(AppItemExchangeConf appItemExchangeConf) ;
	
	public AppItemExchangeConf findAppItemExchangeConfBySendDept(Long appMsgId, Long AppItemId, Long sendDeptId);
}

