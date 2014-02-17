package com.ligitalsoft.datasharexchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.datasharexchange.dao.SendMessageLogDao;
import com.ligitalsoft.datasharexchange.service.ISendMessageLogService;
import com.ligitalsoft.model.changemanage.SendMessageLog;

@Service("sendMessageLogService")
public class SendMessageLogServiceImpl extends BaseSericesImpl<SendMessageLog>
		implements ISendMessageLogService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4907719811809958966L;

	private SendMessageLogDao sendMessageLogDao;

	@Override
	public EntityHibernateDao<SendMessageLog> getEntityDao() {
		return sendMessageLogDao;
	}

	@Autowired
	public void setSendMessageLogDao(SendMessageLogDao sendMessageLogDao) {
		this.sendMessageLogDao = sendMessageLogDao;
	}

}
