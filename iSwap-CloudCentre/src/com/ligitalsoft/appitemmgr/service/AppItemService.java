/*
 * @(#)AppMsgService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.appitemmgr.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.appitemmgr.AppItem;


/**
 * @author  lifh
 * @mail    wslfh2005@163.com
 * @since   2011-6-15 上午10:30:34
 * @name    com.ligitalsoft.cloudcenter.service.AppMsgService.java
 * @version 1.0
 */

public interface AppItemService extends IBaseServices<AppItem>{
	
	public List<AppItem> findAllByProperty() ;
	
}

