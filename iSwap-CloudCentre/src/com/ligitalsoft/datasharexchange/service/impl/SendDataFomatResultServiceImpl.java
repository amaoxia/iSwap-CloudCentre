package com.ligitalsoft.datasharexchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.datasharexchange.dao.SendDataFomatResultDao;
import com.ligitalsoft.datasharexchange.service.ISendDataFomatResultService;
import com.ligitalsoft.model.changemanage.SendDataFomatResult;

@Service("sendDataFomatResultService")
public class SendDataFomatResultServiceImpl extends
		BaseSericesImpl<SendDataFomatResult> implements
		ISendDataFomatResultService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4907719811809958966L;

	private SendDataFomatResultDao sendDataFomatResultDao;

	@Autowired
	public void setSendDataFomatResultDao(
			SendDataFomatResultDao sendDataFomatResultDao) {
		this.sendDataFomatResultDao = sendDataFomatResultDao;
	}

	@Override
	public EntityHibernateDao<SendDataFomatResult> getEntityDao() {
		return sendDataFomatResultDao;
	}

}
