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
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserDept;
import com.ligitalsoft.model.system.SysUserRole;
import com.ligitalsoft.sysmanager.dao.SysDeptDao;
import com.ligitalsoft.sysmanager.dao.SysUserDao;
import com.ligitalsoft.sysmanager.dao.SysUserDeptDao;
import com.ligitalsoft.sysmanager.dao.SysUserRoleDao;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;

/**
 * @author zhangx
 * @since May 26, 2011 1:36:28 AM
 * @name com.ligitalsoft.sysmanager.service.impl.SysUserRoleServiceImpl.java
 * @version 1.0
 */
@Service("sysUserDeptService")
public class SysUserDeptServiceImpl extends BaseSericesImpl<SysUserDept> implements ISysUserDeptService {

    private SysUserDeptDao sysUserDeptDao;
    private SysDeptDao sysDeptDao;
    private SysUserDao sysUserDao;
    private SysUserRoleDao sysUserRoleDao;
    
    public SysUserDept findByUserId(Long userId) {
        return sysUserDeptDao.findByUserId(userId);
    }
    /**
     * 单点集成同步更新保存用户相关对象内容
     * @param user
     * @param dept
     * @param userDept
     * @param userRole
     */
    public void saveObjsForSSO(SysUser user,SysDept dept,SysUserDept userDept,SysUserRole userRole){
    	sysUserDao.save(user);
    	sysDeptDao.save(dept);
    	
    	userDept.setDeptId(dept.getId());
    	userDept.setUserId(user.getId());
    	sysUserDeptDao.save(userDept);
    	
    	userRole.setUserId(user.getId());
    	sysUserRoleDao.save(userRole);
    }
   @Override
	public List<SysUserDept> findUserByDeptId(Long deptId) {
		return sysUserDeptDao.findByDeptId(deptId);
	}
    @Autowired
    public void setSysUserDeptDao(SysUserDeptDao sysUserDeptDao) {
        this.sysUserDeptDao = sysUserDeptDao;
    }
    @Autowired
    public void setSysDeptDao(SysDeptDao sysDeptDao) {
		this.sysDeptDao = sysDeptDao;
	}
    @Autowired
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
    @Autowired
	public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
		this.sysUserRoleDao = sysUserRoleDao;
	}
	@Override
    public EntityHibernateDao<SysUserDept> getEntityDao() {
        return sysUserDeptDao;
    }

}
