package com.ligitalsoft.sysmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.model.system.SysOperationlog;
import com.ligitalsoft.sysmanager.dao.SysOperationlogDao;
import com.ligitalsoft.sysmanager.service.ISysOperationlogService;

/**
 * 操作日志实现类
 * @author zhangx
 * @since May 16, 2011 1:02:39 PM
 * @name com.ligitalsoft.sysmanager.service.impl.SysOperationlogServiceImpl.java
 * @version 1.0
 */
@Service
public class SysOperationlogServiceImpl extends BaseSericesImpl<SysOperationlog> implements ISysOperationlogService {

    private SysOperationlogDao sysOperationlogDao;

    @Autowired
    public void setSysOperationlogDao(SysOperationlogDao sysOperationlogDao) {
        this.sysOperationlogDao = sysOperationlogDao;
    }

    @Override
    public EntityHibernateDao<SysOperationlog> getEntityDao() {
        return sysOperationlogDao;
    }
}