/*
 * @(#)AppCloudNodeServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudcenter.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.cloudcenter.dao.AppCloudNodeDao;
import com.ligitalsoft.cloudcenter.dao.CloudNodeInfoDao;
import com.ligitalsoft.cloudcenter.service.AppCloudNodeService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudcenter.AppCloudNode;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;

/**
 * 
 * 
 * 
 * AppCloudNodeServiceImpl.java
 * 
 * @author bianxj
 * @email bianxiaojin@gmail.com 2011-6-24
 * @description
 * @see
 */
@Service("appCloudNodeService")
public class AppCloudNodeServiceImpl extends BaseSericesImpl<AppCloudNode>
		implements AppCloudNodeService {
	@Resource
	private CloudNodeInfoDao cloudNodeInfoDao;
	@Resource
	private AppCloudNodeDao appCloudNodeDao;

	@Override
	public List<AppMsg> findListByCloudId(Long cloudId) {
		String hql = "select e.appMsg from AppCloudNode e where e.cloudNodeInfo.id=?";
		return appCloudNodeDao.findListByHql(hql, cloudId);
	}

	public void removeNodeInfoByApp(Long appId) {
		String hql = "from AppCloudNode acn where acn.appMsg.id = ?";
		List<AppCloudNode> appCloudNodes = appCloudNodeDao.findListByHql(hql,
				appId);
		for (AppCloudNode appCloudNode : appCloudNodes) {
			appCloudNodeDao.remove(appCloudNode);
		}
	}

	public List<CloudNodeInfo> findNodeInfoListByApp(Long appId) {
		String hql = "select acn.cloudNodeInfo from AppCloudNode acn where acn.appMsg.id = ?";
		List<CloudNodeInfo> cloudNodeInfoList = appCloudNodeDao.findListByHql(
				hql, appId);
		return cloudNodeInfoList;
	}

	@Override
	public List<AppCloudNode> findListByAppId(Long appId) {
		return appCloudNodeDao.findListByAppId(appId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.framework.services.impl.BaseSericesImpl#getEntityDao()
	 */
	@Override
	public EntityHibernateDao<AppCloudNode> getEntityDao() {
		return appCloudNodeDao;
	}

}
