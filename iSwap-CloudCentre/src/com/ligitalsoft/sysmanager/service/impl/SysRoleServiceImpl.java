package com.ligitalsoft.sysmanager.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.utils.tree.ztree.Node;
import com.ligitalsoft.model.system.SysRole;
import com.ligitalsoft.sysmanager.dao.SysRoleDao;
import com.ligitalsoft.sysmanager.dao.SysRolePermissonDao;
import com.ligitalsoft.sysmanager.dao.SysUserRoleDao;
import com.ligitalsoft.sysmanager.service.ISysRoleService;

/**
 * 角色实现类
 * 
 * @author zhangx
 * @since May 16, 2011 12:57:52 PM
 * @name com.ligitalsoft.sysmanager.service.impl.SysRoleServiceImpl.java
 * @version 1.0
 */
@Service
public class SysRoleServiceImpl extends BaseSericesImpl<SysRole> implements
		ISysRoleService {

	private SysRoleDao sysRoleDao;
	private SysUserRoleDao sysUserRoleDao;
	private SysRolePermissonDao sysRolePermissonDao;

	
	@Override
	public JSONArray roleTree() {
		List<Node> nodes = new ArrayList<Node>();
		List<SysRole> roles = findAll();
		Node root = new Node();
		root.setName("角色项");
		root.setId(-1L+"");
		root.setPid(0L+"");
		root.setOpen(true);
		nodes.add(root);
		for (SysRole role : roles) {
			Node node = new Node();
			node.setId(role.getId()+"");
			node.setPid(-1L+"");
			node.setName(role.getName());
			nodes.add(node);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("name") || name.equals("id")
						|| name.equals("pid")||name.equals("open")) {
					return false;
				} else {
					return true;
				}
			}
		});
		return JSONArray.fromObject(nodes, jsonConfig);
	}

	@Override
	public void deleteAllByIds(Serializable[] ids) throws ServiceException {
		for (Serializable serializableId : ids) {
			sysRolePermissonDao.deleteByRoleId(serializableId);// 删除菜单
			sysUserRoleDao.removeAllByRoleId(serializableId);// 删除用户
		}
		sysRoleDao.removeAllByIds(ids);// 删除角色
	}

	@Override
	public EntityHibernateDao<SysRole> getEntityDao() {
		return sysRoleDao;
	}

	@Autowired
	public void setSysRolePermissonDao(SysRolePermissonDao sysRolePermissonDao) {
		this.sysRolePermissonDao = sysRolePermissonDao;
	}

	@Autowired
	public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
		this.sysUserRoleDao = sysUserRoleDao;
	}

	@Autowired
	public void setSysRoleDao(SysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}
}