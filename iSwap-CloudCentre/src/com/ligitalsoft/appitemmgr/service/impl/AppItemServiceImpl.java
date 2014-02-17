/*
 * @(#)AppMsgServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.appitemmgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.appitemmgr.dao.AppItemDao;
import com.ligitalsoft.appitemmgr.service.AppItemService;
import com.ligitalsoft.model.appitemmgr.AppItem;

/**
 * @author lifh
 * @mail wslfh2005@163.com
 * @since 2011-6-15 上午10:31:25
 * @name com.ligitalsoft.cloudcenter.service.impl.AppMsgServiceImpl.java
 * @version 1.0
 */
@Service("appItemService")
public class AppItemServiceImpl extends BaseSericesImpl<AppItem> implements AppItemService {

    private AppItemDao appItemDao;

    @SuppressWarnings("unchecked")
    public List<AppItem> findAllByProperty() {
        String hql = "from AppItem where isDeleted =" + com.ligitalsoft.util.Constant.ISNOTDELETED;
        return appItemDao.findListByHql(hql);
    }

    @Override
    public EntityHibernateDao<AppItem> getEntityDao() {
        return appItemDao;
    }

    @Autowired
    public void setItemDao(AppItemDao appItemDao) {
        this.appItemDao = appItemDao;
    }

}
