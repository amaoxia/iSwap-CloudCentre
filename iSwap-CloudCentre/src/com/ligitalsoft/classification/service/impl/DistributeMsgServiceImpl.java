package com.ligitalsoft.classification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.classification.dao.DistributeMsgDao;
import com.ligitalsoft.classification.service.IDistributeMsgService;
import com.ligitalsoft.model.classification.DistributeMsg;
@Service("distributeMsgService")
public class DistributeMsgServiceImpl extends BaseSericesImpl<DistributeMsg>
		implements IDistributeMsgService {
	@Autowired
	private DistributeMsgDao distributeMsgDao;
	
	@Override
	public EntityHibernateDao<DistributeMsg> getEntityDao() {
		return distributeMsgDao;
	}


}
