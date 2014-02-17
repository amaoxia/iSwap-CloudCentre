package com.ligitalsoft.sysmanager.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.common.framework.dao.SortPara;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserDept;
import com.ligitalsoft.model.system.SysUserRole;
import com.ligitalsoft.sysmanager.dao.SysUserDao;
import com.ligitalsoft.sysmanager.dao.SysUserDeptDao;
import com.ligitalsoft.sysmanager.dao.SysUserRoleDao;
import com.ligitalsoft.sysmanager.service.ISysUserService;

/**
 * 用户实现类
 * 
 * @author zhangx
 * @since May 16, 2011 12:49:10 PM
 * @name com.ligitalsoft.sysmanager.service.impl.SysUserServiceImpl.java
 * @version 1.0
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseSericesImpl<SysUser> implements
		ISysUserService {

	private SysUserDao sysUserDao;
	private SysUserRoleDao sysUserRoleDao;
	private SysUserDeptDao sysUserDeptDao;

	public List<Object[]> findUserListByPage(PageBean pageBean,
			Map<String, String> args, List<SortPara> sortParas) {
		return sysUserDao.findUserListByPage(pageBean, args, sortParas);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveOrUpdate(SysUser user, SysUserDept dept, SysUserRole role) {
		sysUserDao.saveOrUpdate(user);
		if (role.getUserId() == null || role.getUserId() == 0) {
			role.setUserId(user.getId());
		}
		if (dept.getUserId() == null || dept.getUserId() == 0) {
			dept.setUserId(user.getId());
		}
		sysUserRoleDao.saveOrUpdate(role);
		sysUserDeptDao.saveOrUpdate(dept);
	}

	@Override
	public void updateStatus(Long[] ids, Character status) {
		sysUserDao.updateStatus(ids, status);
	}

	@Override
	public void deleteAllByIds(Serializable[] ids) throws ServiceException {
		super.deleteAllByIds(ids);
	}

	@Autowired
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	@Autowired
	public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
		this.sysUserRoleDao = sysUserRoleDao;
	}

	@Autowired
	public void setSysUserDeptDao(SysUserDeptDao sysUserDeptDao) {
		this.sysUserDeptDao = sysUserDeptDao;
	}

	@Override
	public EntityHibernateDao<SysUser> getEntityDao() {
		return sysUserDao;
	}
}