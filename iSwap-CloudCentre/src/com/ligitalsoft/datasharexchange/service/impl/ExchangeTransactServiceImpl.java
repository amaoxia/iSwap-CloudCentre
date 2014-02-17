package com.ligitalsoft.datasharexchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.datasharexchange.dao.ExchangeTransactDao;
import com.ligitalsoft.datasharexchange.service.IExchangeTransactService;
import com.ligitalsoft.model.changemanage.ExchangeTransact;
/**
 *  催办实现类
 */

@Service("exchangeTransactService")
public class ExchangeTransactServiceImpl extends
		BaseSericesImpl<ExchangeTransact> implements IExchangeTransactService {
	private ExchangeTransactDao exchangeTransactDao;

	@Override
	public EntityHibernateDao<ExchangeTransact> getEntityDao() {
		return exchangeTransactDao;
	}

	@Autowired
	public void setExchangeTransactDao(ExchangeTransactDao exchangeTransactDao) {
		this.exchangeTransactDao = exchangeTransactDao;
	}

}
