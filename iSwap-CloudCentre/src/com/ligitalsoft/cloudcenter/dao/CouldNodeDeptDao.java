package com.ligitalsoft.cloudcenter.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.cloudcenter.CouldNodeDept;

@Repository
public class CouldNodeDeptDao extends EntityHibernateDao<CouldNodeDept> {

	/**
	 * 根据节点Id找对应的信息
	 * 
	 * @param nodeId
	 * @return
	 */
	public List<CouldNodeDept> findListByNodeId(Long nodeId) {
		String hql = "select e from CouldNodeDept e where e.couldNode.id=?";
		return powerHibernateDao.findListByHql(hql, nodeId);
	}

	/**
	 * 根据deptId找对应的信息
	 * 
	 * @param nodeId
	 * @return
	 */
	public List<CloudNodeInfo> findListByDeptId(Long deptId) {
		String hql = "select e.couldNode from CouldNodeDept e where e.dept.id=?";
		return powerHibernateDao.findListByHql(hql, deptId);
	}

	/**
	 * 根据deptId找对应的信息
	 * 
	 * @param nodeId
	 * @return
	 */
	public List<CouldNodeDept> findCouldNodeDeptByDeptId(Long deptId) {
		String hql = "select e from CouldNodeDept e where e.dept.id=?";
		return powerHibernateDao.findListByHql(hql, deptId);
	}

}
