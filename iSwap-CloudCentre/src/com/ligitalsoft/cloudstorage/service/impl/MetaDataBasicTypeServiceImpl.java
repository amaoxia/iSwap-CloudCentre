/*
 * @(#)MetaDataBasicTypeServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.cloudstorage.dao.MetaDataBasicTypeDao;
import com.ligitalsoft.cloudstorage.service.IMetaDataBasicTypeService;
import com.ligitalsoft.model.cloudstorage.MetaDataBasicType;

/**
 * @author zhangx
 * @since Jul 19, 2011 5:31:39 PM
 * @name com.ligitalsoft.cloudstorage.service.impl.MetaDataBasicTypeServiceImpl.java
 * @version 1.0
 */
@Service
public class MetaDataBasicTypeServiceImpl extends BaseSericesImpl<MetaDataBasicType> implements IMetaDataBasicTypeService {

    private MetaDataBasicTypeDao metaDataBasicTypeDao;

    @Override
    public EntityHibernateDao<MetaDataBasicType> getEntityDao() {
        return metaDataBasicTypeDao;
    }

    @Autowired
    public void setMetaDataBasicTypeDao(MetaDataBasicTypeDao metaDataBasicTypeDao) {
        this.metaDataBasicTypeDao = metaDataBasicTypeDao;
    }
}
