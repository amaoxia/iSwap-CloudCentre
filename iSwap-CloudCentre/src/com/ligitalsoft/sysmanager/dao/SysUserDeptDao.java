/*
 * @(#)SysUserDept.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.system.SysUserDept;

/**
 * 用户部门DAO
 * @author zhangx
 * @since May 26, 2011 1:11:42 AM
 * @name com.ligitalsoft.sysmanager.dao.SysUserDept.java
 * @version 1.0
 */
@Repository
public class SysUserDeptDao extends EntityHibernateDao<SysUserDept> {

    /**
     * 根据用户Id 查找用户部门对象
     * @param userId
     * @return
     * @author zhangx
     */
    public SysUserDept findByUserId(Serializable userId) {
        String hql = "select e from SysUserDept e where e.userId=?";
        return (SysUserDept) powerHibernateDao.findUniqueByHql(hql, userId);
    }
    /**
     * 根据部门Id 查找用户
     * @param deptId
     * @return
     * @author daic
     */
    @SuppressWarnings("unchecked")
	public List<SysUserDept> findByDeptId(Long deptId) {
        String hql = "select e from SysUserDept e where e.deptId=?";
        return (List<SysUserDept>) powerHibernateDao.findListByHql(hql, deptId);
    }
    /**
     * 删除用户对应的权限信息
     * @param ids
     * @author zhangx
     */
    public void removeAllByUserIds(Serializable ids[]) {
        for (Serializable id : ids) {
            SysUserDept sysUserDept = findByUserId(id);
            if (sysUserDept != null) {
                powerHibernateDao.remove(sysUserDept);
            }
        }
    }
}
