/*
 * @(#)CloudStorageMakeServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudcenter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.dao.hibernate.PowerHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.cloudcenter.dao.CloudStorageMakeDao;
import com.ligitalsoft.cloudcenter.service.CloudStorageMakeService;
import com.ligitalsoft.model.cloudcenter.CloudStorageMake;

/**
 * 
 * 
 * 
 * CloudStorageMakeServiceImpl.java
 * 
 * @author bianxj
 * @email  bianxiaojin@gmail.com
 * 2011-6-27
 * @description 
 * @see
 */
@Service("cloudStorageMakeService")
public class CloudStorageMakeServiceImpl extends BaseSericesImpl<CloudStorageMake> implements CloudStorageMakeService {

	@Resource
    private CloudStorageMakeDao cloudStorageMakeDao;

	public List<CloudStorageMake> findAllByPage(Long appId, String storageName, PageBean page) {
        String hql = "from CloudStorageMake node where 1=1";
        String countHql = "select count(*) from CloudStorageMake node where 1=1";
        Map<String, Object> param = new HashMap<String, Object>();

        if (appId != null && appId != 0l) {
            hql += " and node.appMsg.id=:appId ";
            countHql += " and node.appMsg.id=:appId ";
            param.put("appId", appId);
        }
        if (storageName != null && storageName.trim().length() != 0) {
            hql += " and node.storageName like :nodeName ";
            countHql += " and node.storageName like :nodeName ";
            param.put("nodeName", "%"+storageName+"%");
        }

        PowerHibernateDao powerHibernateDao = cloudStorageMakeDao.getPowerHibernateDao();
        Long count = (Long) powerHibernateDao.findUniqueByHql(countHql, param);
        List<CloudStorageMake> list = powerHibernateDao.findListByHql(hql, page.getStart(), page.getPerPage(), param);
        page.setTotal(count);
        return list;
    }
	
	/* (non-Javadoc)
	 * @see com.common.framework.services.impl.BaseSericesImpl#getEntityDao()
	 */
	@Override
	public EntityHibernateDao<CloudStorageMake> getEntityDao() {
		// TODO Auto-generated method stub
		return cloudStorageMakeDao;
	}

    
}
