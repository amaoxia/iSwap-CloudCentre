/*
 * @(#)CloudStorageCenterImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.cloudstorage.dao.CloudStorageCenterDao;
import com.ligitalsoft.cloudstorage.service.ICloudStorageCenterService;
import com.ligitalsoft.model.cloudstorage.CloudStorageCenter;

/**
 * 云存储中心的对象 实现类
 * @author zhangx
 * @since Jun 15, 2011 12:48:03 AM
 * @name com.ligitalsoft.cloudstorage.service.impl.CloudStorageCenterImpl.java
 * @version 1.0
 */
@Service("cloudStorageCenterService")
public class CloudStorageCenterServiceImpl extends BaseSericesImpl<CloudStorageCenter> implements
                ICloudStorageCenterService {

	@Autowired
    private CloudStorageCenterDao cloudStorageCenterDao;

    @Override
    public EntityHibernateDao<CloudStorageCenter> getEntityDao() {
        return cloudStorageCenterDao;
    }


}
