/*
 * @(#)AppCloudNodeDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudcenter.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.dao.hibernate.PowerHibernateDao;
import com.ligitalsoft.model.cloudcenter.AppCloudNode;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;

/**
 * 
 * 
 * 
 * AppCloudNodeDao.java
 * 
 * @author bianxj
 * @email  bianxiaojin@gmail.com
 * 2011-6-24
 * @description 
 * @see
 */
@Repository
public class AppCloudNodeDao extends EntityHibernateDao<AppCloudNode> {
	public List<AppCloudNode> findListByAppId(Long appId){
		String hql="from AppCloudNode e where e.appMsg.id=?";
		return powerHibernateDao.findListByHql(hql, appId);
	}

}
