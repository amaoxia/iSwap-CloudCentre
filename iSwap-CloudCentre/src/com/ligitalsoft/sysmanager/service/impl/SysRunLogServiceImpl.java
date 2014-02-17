package com.ligitalsoft.sysmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.model.system.SysRunLog;
import com.ligitalsoft.sysmanager.dao.SysRunLogDao;
import com.ligitalsoft.sysmanager.service.ISysRunLogService;

/**
 * 系统运行日志实现类
 * @author zhangx
 * @since May 16, 2011 1:07:21 PM
 * @name com.ligitalsoft.sysmanager.service.impl.SysRunLogServiceImpl.java
 * @version 1.0
 */
@Service
public class SysRunLogServiceImpl extends BaseSericesImpl<SysRunLog> implements ISysRunLogService {

    private SysRunLogDao sysRunLogDao;

    @Override
    public EntityHibernateDao<SysRunLog> getEntityDao() {
        return sysRunLogDao;
    }
    @Autowired
    public void setSysRunLogDao(SysRunLogDao sysRunLogDao) {
        this.sysRunLogDao = sysRunLogDao;
    }

}