/*
 * @(#)ExchangeSendTaskServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service.impl;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.datasharexchange.dao.ReceiveResultDao;
import com.ligitalsoft.datasharexchange.service.IReceTaskService;
import com.ligitalsoft.model.changemanage.ReceiveResult;

/**
 * 交换发送任务 实现类
 * @author daic
 * @since 2011-08-17 15:11:00
 * @name com.ligitalsoft.cloudstorage.service.impl.ExchangeSendTaskServiceImpl.java
 * @version 1.0
 */
@Service("receTaskService")
public class ReceTaskServiceImpl extends BaseSericesImpl<ReceiveResult> implements IReceTaskService {

    private ReceiveResultDao receiveResultDao;
    

    @Autowired
	public void setReceiveResultDao(ReceiveResultDao receiveResultDao) {
		this.receiveResultDao = receiveResultDao;
	}
	@Override
	public EntityHibernateDao<ReceiveResult> getEntityDao() {
		return receiveResultDao;
	}
	/**
	 * 获得接收的数据量
	 * @author fangbin
	 */
	@Override
	public int getDataNum(Map<String, String> map) {
		return receiveResultDao.getReceiveResult(map);
	}
	@Override
	public List<ReceiveResult> findReceiveResult(Map<String, String> map) {
		return receiveResultDao.findReceiveResult(map);
	}

	
	

}
