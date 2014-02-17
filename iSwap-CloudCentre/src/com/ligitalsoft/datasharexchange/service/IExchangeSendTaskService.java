/*
 * @(#)IExchangeSendTaskService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service;


import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.changemanage.ExchangeSendTask;

/**
 * 交换发送任务 SERVICE
 * @author daic
 * @since 2011-08-17 15:09:10
 * @name com.ligitalsoft.cloudstorage.service.IExchangeSendTaskService.java
 * @version 1.0
 */
public interface IExchangeSendTaskService extends IBaseServices<ExchangeSendTask> {
	/**
	 * 获得发送部门的列表
	 * 
	 * @param beginTime endTime
	 * @return
	 */
	List<Object[]> getSendDeptList(String beginTime, String endTime);
	
	/**
	 * 通过开始结束时间以及所属应用查询部门发送任务
	 * @author fangbin
	 * @param beginTime
	 * @param endTime
	 * @param appId
	 * @return
	 */
	public List<Object[]> getSendDeptList(String beginTime, String endTime,String appId) ;
	/**
	 * 获得部门发送任务的列表
	 * 
	 * @param deptId beginTime endTime
	 * @return
	 */
	List<ExchangeSendTask> getSendTaskList(String beginTime, String endTime,String deptId);
}
