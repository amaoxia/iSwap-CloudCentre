/*
 * @(#)CloudNodeMgServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.cloudstorage.dao.CloudNodeMgDao;
import com.ligitalsoft.cloudstorage.service.ICloudNodeMgService;
import com.ligitalsoft.model.cloudstorage.CloudNodeMg;

/**
 * 云存储节点的管理 实现类
 * @author zhangx
 * @since Jun 15, 2011 12:33:28 AM
 * @name com.ligitalsoft.cloudstorage.service.impl.CloudNodeMgServiceImpl.java
 * @version 1.0
 */
@Service
public class CloudNodeMgServiceImpl extends BaseSericesImpl<CloudNodeMg> implements ICloudNodeMgService {

    private CloudNodeMgDao cloudNodeMgDao;

    @Override
    public EntityHibernateDao<CloudNodeMg> getEntityDao() {
        return cloudNodeMgDao;
    }
    @Autowired
    public void setCloudNodeMgDao(CloudNodeMgDao cloudNodeMgDao) {
        this.cloudNodeMgDao = cloudNodeMgDao;
    }

}
