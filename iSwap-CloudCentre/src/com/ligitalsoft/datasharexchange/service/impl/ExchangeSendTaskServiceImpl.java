/*
 * @(#)ExchangeSendTaskServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.datasharexchange.dao.ChangeItemDao;
import com.ligitalsoft.datasharexchange.dao.ExchangeSendTaskDao;
import com.ligitalsoft.datasharexchange.service.IExchangeSendTaskService;
import com.ligitalsoft.model.changemanage.ExchangeSendTask;

/**
 * 交换发送任务 实现类
 * 
 * @author daic
 * @since 2011-08-17 15:11:00
 * @name 
 *       com.ligitalsoft.cloudstorage.service.impl.ExchangeSendTaskServiceImpl.java
 * @version 1.0
 */
@Service("exchangeSendTaskService")
public class ExchangeSendTaskServiceImpl extends
		BaseSericesImpl<ExchangeSendTask> implements IExchangeSendTaskService {

	private ChangeItemDao changeItemDao;
	private ExchangeSendTaskDao exchangeSendTaskDao;

	public List<Object[]> getSendDeptList(String beginTime, String endTime) {
		return exchangeSendTaskDao.getSendDeptList(beginTime, endTime);
	}

	@Autowired
	public void setChangeItemDao(ChangeItemDao changeItemDao) {
		this.changeItemDao = changeItemDao;
	}

	@Override
	public EntityHibernateDao<ExchangeSendTask> getEntityDao() {
		return exchangeSendTaskDao;
	}

	@Autowired
	public void setExchangeSendTaskDao(ExchangeSendTaskDao exchangeSendTaskDao) {
		this.exchangeSendTaskDao = exchangeSendTaskDao;
	}
	@Override
	public List<ExchangeSendTask> getSendTaskList(String beginTime, String endTime,
			String deptId) {
		return exchangeSendTaskDao.getSendTaskList(beginTime, endTime, deptId);
	}

	/**
	 * 通过开始结束时间以及所属应用查询部门发送任务
	 * 
	 * @author fangbin
	 */
	@Override
	public List<Object[]> getSendDeptList(String beginTime, String endTime,String appId) {
		
		List<Object[]> objList = exchangeSendTaskDao.getSendDeptList(beginTime,endTime, appId);
		return objList;
	}

}
