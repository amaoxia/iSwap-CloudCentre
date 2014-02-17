/*
 * @(#)SysRolePermissonDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.system.SysRolePermisson;

/**
 * 权限菜单DAO
 * @author zhangx
 * @since May 20, 2011 4:15:56 PM
 * @name com.ligitalsoft.sysmanager.dao.SysRolePermissonDao.java
 * @version 1.0
 */

@Repository
public class SysRolePermissonDao extends EntityHibernateDao<SysRolePermisson> {

    /**
     * 根据角色删除角色权限关系
     * @param roleId
     *            角色ID
     * @author zhangx
     */
    public void deleteByRoleId(Serializable roleId) {
        String hql = "delete from  SysRolePermisson where roleId=?";
        powerHibernateDao.executeUpdate(hql, roleId);
    }
    /**
     * 根据角色查询菜单信息
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<SysRolePermisson> findPermissionByRoleId(Long roleId) {
            String hql="from  SysRolePermisson where  roleId=?";
            return powerHibernateDao.findListByHql(hql,roleId);
    }
}
