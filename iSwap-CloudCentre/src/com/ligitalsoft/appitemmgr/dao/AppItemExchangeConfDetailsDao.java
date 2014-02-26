/*
 * @(#)AppMsgDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.appitemmgr.dao;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.appitemmgr.AppItemExchangeConfDetails;


/**
 * @author  lifh
 * @mail    wslfh2005@163.com
 * @since   2011-6-15 上午10:28:48
 * @name    com.ligitalsoft.cloudcenter.dao.AppMsgDao.java
 * @version 1.0
 */
@Repository
public class AppItemExchangeConfDetailsDao extends EntityHibernateDao<AppItemExchangeConfDetails> {

	 public int removeAllByAppItemExchangeConfId(Long appItemExchangeConfId) {
	        String hql = "delete AppItemExchangeConfDetails e where e.appItemExchangeConf.id=?";
	        return this.powerHibernateDao.executeUpdate(hql, appItemExchangeConfId);
	    }
}

