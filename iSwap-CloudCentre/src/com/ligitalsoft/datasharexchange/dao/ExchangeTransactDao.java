/*
 * @(#)ExchangeSystemConfigDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.dao;


import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.changemanage.ExchangeTransact;

/**
 * 催办dao层
 * @author daic
 * @since 2011-08-23 14:59:14
 * @name com.ligitalsoft.cloudstorage.dao.ExchangeTransactDao.java
 * @version 1.0
 */
@Repository("exchangeTransactDao")
public class ExchangeTransactDao extends EntityHibernateDao<ExchangeTransact> {

}
