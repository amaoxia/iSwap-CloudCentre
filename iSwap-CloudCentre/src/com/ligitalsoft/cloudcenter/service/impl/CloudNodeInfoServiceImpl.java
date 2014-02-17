/*
 * @(#)CloudNodeInfoServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudcenter.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.dao.hibernate.PowerHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.cloudcenter.dao.CloudNodeInfoDao;
import com.ligitalsoft.cloudcenter.dao.CouldNodeDeptDao;
import com.ligitalsoft.cloudcenter.service.CloudNodeInfoService;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.cloudcenter.CouldNodeDept;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.dao.SysDeptDao;
import com.ligitalsoft.util.Node;

/**
 * @author lifh
 * @mail wslfh2005@163.com
 * @since 2011-6-16 上午09:58:41
 * @name com.ligitalsoft.cloudcenter.service.impl.CloudNodeInfoServiceImpl.java
 * @version 1.0
 */
@Service("cloudNodeInfoService")
public class CloudNodeInfoServiceImpl extends BaseSericesImpl<CloudNodeInfo>
		implements CloudNodeInfoService {

	private CloudNodeInfoDao cloudNodeInfoDao;
	private SysDeptDao sysDeptDao;
	private CouldNodeDeptDao couldNodeDeptDao;

	@Override
	public List<CloudNodeInfo> findAllByPage(Long appId, String nodeName,
			PageBean page) {
		String hql = "select distinct node.cloudNodeInfo from AppCloudNode node where 1=1";
		String countHql = "select count(distinct node.cloudNodeInfo) from AppCloudNode node where 1=1";
		Map<String, Object> param = new HashMap<String, Object>();
		if (appId != null && appId != 0l) {
			hql += " and node.appMsg.id=:appId ";
			countHql += " and node.appMsg.id=:appId ";
			param.put("appId", appId);
		}
		if (nodeName != null && nodeName.trim().length() != 0) {
			hql += " and node.cloudNodeInfo.nodesName like :nodeName ";
			countHql += " and node.cloudNodeInfo.nodesName like :nodeName ";
			param.put("nodeName", "%" + nodeName.trim() + "%");
		}
		hql+="order by node.cloudNodeInfo.id desc";
		PowerHibernateDao powerHibernateDao = cloudNodeInfoDao
				.getPowerHibernateDao();
		Long count = (Long) powerHibernateDao.findUniqueByHql(countHql, param);
		List<CloudNodeInfo> list = powerHibernateDao.findListByHql(hql,
				page.getStart(), page.getPerPage(), param);
		page.setTotal(count);
		return list;
	}

	public List<CloudNodeInfo> findAllByPage(String nodeName) {
		String hql = "from CloudNodeInfo where nodesName like " + "'%"
				+ nodeName + "%'";
		return cloudNodeInfoDao.findListByHql(hql);
	}

	@Override
	public JSONArray getDeptTreeById(Long id) {
		// 创建节点
		List<Node> nodes = new ArrayList<Node>();
		List<SysDept> depts = sysDeptDao.findAll(-1, -1);
		List<CouldNodeDept> list = null;
		if (id != null && id != 0L) {
			list=couldNodeDeptDao.findListByNodeId(id);
		}
		Node root = new Node();
		root.setName("组织机构");
		root.setId(-1L);
		root.setPid(0L);
		root.setOpen(true);
		nodes.add(root);
		if (depts != null && depts.size() != 0) {
			for (SysDept dept : depts) {
				Node node = new Node();
				node.setName(dept.getDeptName());//设置节点名称
				node.setId(dept.getId());//设置节点Id
				if (dept.getSysDept() != null
						&& dept.getSysDept().getId() != 0L) {
					node.setPid(dept.getSysDept().getId());
				} else {
					node.setPid(-1L);
				}
				if (list != null && list.size() > 0) {
					for (CouldNodeDept couldNodeDept : list) {
						if (couldNodeDept.getDept().getId() == dept.getId()) {
							node.setChecked(true);
							break;
						}
					}
				}
				nodes.add(node);
			}
		}
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("name") || name.equals("id")
						|| name.equals("pid") || name.equals("checked")||name.equals("open")) {
					return false;
				} else {
					return true;
				}
			}
		});
		return JSONArray.fromObject(nodes, config);
	}
	
	/**
	 * 　通过部门id在部门与云节点关系表中查询
	 * @author fangbin
	 * @param id
	 * @return
	 */
	public List<CloudNodeInfo> findListNodeByDeptId(Long id){
		List<CloudNodeInfo> cloudNodeInfoList=cloudNodeInfoDao.findListByDeptId(id);
		return cloudNodeInfoList;
	}
	@Override
	public List<SysDept> findListDeptByNodeId(Long nodeId) {
		
		return cloudNodeInfoDao.findListDeptByNodeId(nodeId);
	}
	
	@Override
	public List<CloudNodeInfo> findListByStatus(int status) {
		return cloudNodeInfoDao.findListByStatus(status);
	}
	
	@Override
	public void removeAppCloudNode(Long cloudNodeInfoId) {
		cloudNodeInfoDao.removeAppCloudNode(cloudNodeInfoId);
	}

	@Override
	public void removeCouldNodeDept(Long cloudNodeInfoId) {
		cloudNodeInfoDao.removeCouldNodeDept(cloudNodeInfoId);
	}

	@Override
	public EntityHibernateDao<CloudNodeInfo> getEntityDao() {
		return cloudNodeInfoDao;
	}

	@Autowired
	public void setCloudNodeInfoDao(CloudNodeInfoDao cloudNodeInfoDao) {
		this.cloudNodeInfoDao = cloudNodeInfoDao;
	}

	@Override
	public List<CloudNodeInfo> findAll() {
		return super.findAll();
	}

	@Autowired
	public void setSysDeptDao(SysDeptDao sysDeptDao) {
		this.sysDeptDao = sysDeptDao;
	}

	@Autowired
	public void setCouldNodeDeptDao(CouldNodeDeptDao couldNodeDeptDao) {
		this.couldNodeDeptDao = couldNodeDeptDao;
	}

}
