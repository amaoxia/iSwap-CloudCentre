/*
 * @(#)SysUserRoleDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.system.SysUserRole;

/**
 * 部门角色DAO
 * 
 * @author zhangx
 * @since May 26, 2011 1:11:33 AM
 * @name com.ligitalsoft.sysmanager.dao.SysUserRoleDao.java
 * @version 1.0
 */
@Repository
public class SysUserRoleDao extends EntityHibernateDao<SysUserRole> {

	/**
	 * 根据用户Id查询对象
	 * 
	 * @param userId
	 * @return
	 * @author zhangx
	 */
	public SysUserRole findByUserId(Serializable userId) {
		String hql = "select e from  SysUserRole e where e.userId=?";
		return (SysUserRole) powerHibernateDao.findUniqueByHql(hql, userId);
	}

	/**
	 * 删除角色对应的用户
	 * 
	 * @param userId
	 * @author zhangx
	 */
	public void removeAllByRoleId(Serializable roleId) {
		String hql = "delete from SysUserRole e where e.roleId=?";
		powerHibernateDao.executeUpdate(hql, roleId);
	}

	/**
	 * 删除用户对应的权限信息
	 * 
	 * @param ids
	 * @author zhangx
	 */
	public void removeAllByUserIds(Serializable ids[]) {
		for (Serializable id : ids) {
			SysUserRole sysUserRole = findByUserId(id);
			if (sysUserRole != null) {
				powerHibernateDao.remove(sysUserRole);
			}
		}
	}

	/**
	 * 角色下用户列表
	 * 
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SysUserRole> findListByRoleId(Long roleId) {
		String hql = "from SysUserRole e where e.roleId=?";
		return powerHibernateDao.findListByHql(hql, roleId);
	}
}
