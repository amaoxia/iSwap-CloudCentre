/*
 * @(#)CloudStorageMakeDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudcenter.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.dao.hibernate.PowerHibernateDao;
import com.ligitalsoft.model.cloudcenter.CloudStorageMake;

/**
 * 
 * 
 * 
 * CloudStorageMakeDao.java
 * 
 * @author bianxj
 * @email  bianxiaojin@gmail.com
 * 2011-6-24
 * @description 
 * @see
 */
@Repository
public class CloudStorageMakeDao extends EntityHibernateDao<CloudStorageMake> {
	@Resource
    private PowerHibernateDao powerHibernateDao;

	public PowerHibernateDao getPowerHibernateDao() {
	        return powerHibernateDao;
	}

}
