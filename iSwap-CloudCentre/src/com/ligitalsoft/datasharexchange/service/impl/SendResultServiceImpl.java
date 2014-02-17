package com.ligitalsoft.datasharexchange.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.datasharexchange.dao.SendResultDao;
import com.ligitalsoft.datasharexchange.service.ISendResultService;
import com.ligitalsoft.model.changemanage.SendResult;
@Service("sendResultService")
public class SendResultServiceImpl extends BaseSericesImpl<SendResult> implements
		ISendResultService {
	
	@Autowired
	private SendResultDao sendResultDao;
	
	@Override
	public EntityHibernateDao<SendResult> getEntityDao() {
		return sendResultDao;
	}
	
	/**
	 * 数据发送记录
	 * @author fangbin
	 * @param map
	 * @return
	 */
	@Override
	public int getDataNum(Map<String,String> map){
		return this.sendResultDao.getDataNum(map);
	}

	
}
