/*
 * @(#)SysEmailConfigServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.model.system.SysEmailConfig;
import com.ligitalsoft.sysmanager.dao.SysEmailConfigDao;
import com.ligitalsoft.sysmanager.service.ISysEmailConfigServaice;

/**
 * 系统邮件_实现类
 * @author zhangx
 * @since Aug 3, 2011 5:23:28 PM
 * @name com.ligitalsoft.sysmanager.service.impl.SysEmailConfigServiceImpl.java
 * @version 1.0
 */
@Service
public class SysEmailConfigServiceImpl extends BaseSericesImpl<SysEmailConfig> implements ISysEmailConfigServaice {

    private SysEmailConfigDao sysEmailConfigDao;

    @Override
    public EntityHibernateDao<SysEmailConfig> getEntityDao() {
        return sysEmailConfigDao;
    }
    @Autowired
    public void setSysEmailConfigDao(SysEmailConfigDao sysEmailConfigDao) {
        this.sysEmailConfigDao = sysEmailConfigDao;
    }
}
