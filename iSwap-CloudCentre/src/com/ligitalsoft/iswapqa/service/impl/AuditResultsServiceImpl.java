package com.ligitalsoft.iswapqa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.iswapqa.dao.AuditResultsDao;
import com.ligitalsoft.iswapqa.service.IAuditResultsService;
import com.ligitalsoft.model.iswapqa.AuditResults;

/**
 *审计结果实现类
 * @author daic
 * @since 2011-07-21 17:27:48
 * @name ccom.ligitalsoft.iswapqa.service.impl.AuditResultsServiceImpl.java
 * @version 1.0
 */
@Transactional
@Service(value = "auditResultsService")
public class AuditResultsServiceImpl extends BaseSericesImpl<AuditResults> implements IAuditResultsService {
	@Autowired
	private AuditResultsDao auditResultsDao;
	@Override
	public EntityHibernateDao<AuditResults> getEntityDao() {
		return this.auditResultsDao;
	}

	
}