/*
 * @(#)MetaDataTableAuthServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.cloudstorage.dao.MetaDataTableAuthDao;
import com.ligitalsoft.cloudstorage.service.IMetaDataTableAuthService;
import com.ligitalsoft.model.cloudstorage.MetaDataTableAuth;

/**
 * 申请数据项授权表_实现类
 * @author zhangx
 * @since Aug 15, 2011 11:36:25 AM
 * @name com.ligitalsoft.cloudstorage.service.impl.MetaDataTableAuthServiceImpl.java
 * @version 1.0
 */
@Service
public class MetaDataTableAuthServiceImpl extends BaseSericesImpl<MetaDataTableAuth> implements
                IMetaDataTableAuthService {

    private MetaDataTableAuthDao metaDataTableAuthDao;

    public List<MetaDataTableAuth> findByTableApplyId(Long applyId) {
        return metaDataTableAuthDao.findByTableApplyId(applyId);
    }

    @Override
    public List<MetaDataTableAuth> findByFiledAuthApplyId(Long applyId, String status) {
        return metaDataTableAuthDao.findByFiledAuthApplyId(applyId,status);
    }
    
    @Override
    public List<MetaDataTableAuth> findByDataAuthStateApplyId(Long applyId) {
        return metaDataTableAuthDao.findByDataAuthStateApplyId(applyId);
    }

    @Override
    public List<MetaDataTableAuth> findByDataAuthStateApplyId(Long applyId,
    		String dataAuthState) {
    	return metaDataTableAuthDao.findByDataAuthStateApplyId(applyId,dataAuthState);
    }
    
    @Override
    public List<MetaDataTableAuth> findByMetaId(Long itemId) {
        return metaDataTableAuthDao.findByMetaId(itemId);
    }

    @Override
    public EntityHibernateDao<MetaDataTableAuth> getEntityDao() {
        return metaDataTableAuthDao;
    }

    @Autowired
    public void setMetaDataTableAuthDao(MetaDataTableAuthDao metaDataTableAuthDao) {
        this.metaDataTableAuthDao = metaDataTableAuthDao;
    }

}
