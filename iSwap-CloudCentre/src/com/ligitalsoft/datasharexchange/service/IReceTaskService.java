/*
 * @(#)IExchangeSendTaskService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service;

import java.util.List;
import java.util.Map;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.changemanage.ReceiveResult;

/**
 * 接收任务 SERVICE
 * 
 * @author daic
 * @since 2011-08-17 15:09:10
 * @name com.ligitalsoft.cloudstorage.service.IReceTaskService.java
 * @version 1.0
 */
public interface IReceTaskService extends IBaseServices<ReceiveResult> {
	/**
	 * 
	 * 获得接收的数据量
	 * @author fangbin
	 * @param map
	 * @return
	 */
	public int getDataNum(Map<String, String> map);
	/**
	 *  查询数据接收记录
	 * @author fangbin
	 * @param map
	 * @return
	 */
	public List<ReceiveResult> findReceiveResult(Map<String,String> map);
}
