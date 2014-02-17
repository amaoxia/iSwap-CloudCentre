package com.ligitalsoft.iswapqa.dao;


import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.iswapqa.AuditResults;

/**
 * 审计结果DAO层
 * @author daic
 * @since 2011-07-27 10:22:15
 * @name com.ligitalsoft.iswapqa.dao.dao.AuditResultsDao.java
 * @version 1.0
 */
@Repository("auditResultsDao")
public class AuditResultsDao extends EntityHibernateDao<AuditResults> {
  
}