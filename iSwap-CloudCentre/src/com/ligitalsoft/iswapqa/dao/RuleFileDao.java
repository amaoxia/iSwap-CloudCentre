package com.ligitalsoft.iswapqa.dao;


import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.iswapqa.RuleFile;

/**
 * 规则文件DAO层
 * @author daic
 * @since 2011-07-21 17:32:22
 * @name com.ligitalsoft.iswapqa.dao.dao.RuleFileDao.java
 * @version 1.0
 */
@Repository("ruleFileDao")
public class RuleFileDao extends EntityHibernateDao<RuleFile> {
  
}