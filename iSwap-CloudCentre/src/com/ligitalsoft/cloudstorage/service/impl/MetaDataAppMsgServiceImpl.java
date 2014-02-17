/*
 * @(#)MetaDataAppMsgServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.cloudstorage.dao.MetaDataAppMsgDao;
import com.ligitalsoft.cloudstorage.service.IMetaDataAppMsgService;
import com.ligitalsoft.model.cloudstorage.MetaDataAppMsg;

/**
 * 元数据_应用 业务现实类
 * @author zhangx
 * @since Jun 24, 2011 10:42:19 AM
 * @name com.ligitalsoft.cloudstorage.service.impl.MetaDataAppMsgServiceImpl.java
 * @version 1.0
 */
@Service("metaDataAppMsgService")
public class MetaDataAppMsgServiceImpl extends BaseSericesImpl<MetaDataAppMsg> implements IMetaDataAppMsgService {

    private MetaDataAppMsgDao metaDataAppMsgDao;

    @Override
    public List<MetaDataAppMsg> findListByMetaId(Long metaId) {
        return metaDataAppMsgDao.findListByMetaId(metaId);
    }
    
    @Override
    public void updateStatus(Long[] ids, String status) {
        metaDataAppMsgDao.updateStatus(ids, status);
    }

    @Override
    public EntityHibernateDao<MetaDataAppMsg> getEntityDao() {
        return metaDataAppMsgDao;
    }

    @Autowired
    public void setMetaDataAppMsgDao(MetaDataAppMsgDao metaDataAppMsgDao) {
        this.metaDataAppMsgDao = metaDataAppMsgDao;
    }
    /**
     * 通过应用ID查询
     * @author fangbin
     */
	@Override
	public List<MetaDataAppMsg> findListByAppId(Long appId) {
		
		return metaDataAppMsgDao.findListByAppId(appId);
	}
	/**
	 * 数据接收按应用排名
	 * @author fangbin
	 */
	@Override
	public List<Object[]> appRank() {
		return metaDataAppMsgDao.appRank();
	}
	/**
	 * 数据接收按指标排名
	 * @author fangbin
	 */
	@Override
	public List<Object[]> targetRank(Map<String, String> map) {
		return metaDataAppMsgDao.targetRank(map);
	}

}
