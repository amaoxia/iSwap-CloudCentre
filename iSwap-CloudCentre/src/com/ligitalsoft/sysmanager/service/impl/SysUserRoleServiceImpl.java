/*
 * @(#)SysUserRoleServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.model.system.SysUserRole;
import com.ligitalsoft.sysmanager.dao.SysUserRoleDao;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;

/**
 * @author zhangx
 * @since May 26, 2011 1:36:28 AM
 * @name com.ligitalsoft.sysmanager.service.impl.SysUserRoleServiceImpl.java
 * @version 1.0
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends BaseSericesImpl<SysUserRole> implements ISysUserRoleService {

    private SysUserRoleDao sysUserRoleDao;

    @Override
    public SysUserRole findByUserId(Long userId) {
        return sysUserRoleDao.findByUserId(userId);
    }
    
    
    @Override
    public List<SysUserRole> findListByRoleId(Long roleId) {
    	return sysUserRoleDao.findListByRoleId(roleId);
    }
    
    @Override
    public EntityHibernateDao<SysUserRole> getEntityDao() {
        return sysUserRoleDao;
    }

    
    
    @Autowired
    public void setUserRoleDao(SysUserRoleDao sysUserRoleDao) {
        this.sysUserRoleDao = sysUserRoleDao;
    }
}
