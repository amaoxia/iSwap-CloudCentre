/*
 * @(#)CloudNodeInfoDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudcenter.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.dao.hibernate.PowerHibernateDao;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.cloudcenter.CouldNodeDept;
import com.ligitalsoft.model.system.SysDept;

/**
 * @author lifh
 * @mail wslfh2005@163.com
 * @since 2011-6-16 上午09:57:35
 * @name com.ligitalsoft.cloudcenter.dao.CloudNodeInfoDao.java
 * @version 1.0
 */
@Repository
public class CloudNodeInfoDao extends EntityHibernateDao<CloudNodeInfo> {

    private PowerHibernateDao powerHibernateDao;

    /**
     * 删除应用和节点的关系
     * @param cloudNodeInfoId
     * @author lifh
     */
    public void removeAppCloudNode(Long cloudNodeInfoId) {
        String hql = "delete from AppCloudNode where cloudNodeInfo.id=?";
        powerHibernateDao.executeUpdate(hql, cloudNodeInfoId);
    }
    /**
     * 删除应用和机构的关系
     * @param cloudNodeInfoId
     * @author lifh
     */
    public void removeCouldNodeDept(Long cloudNodeInfoId) {
        String hql = "delete from CouldNodeDept where couldNode.id=?";
        powerHibernateDao.executeUpdate(hql, cloudNodeInfoId);
    }

    /**
     * 查询部署成功的节点
     * @param status
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<CloudNodeInfo> findListByStatus(int status) {
		String hql="from CloudNodeInfo where status=?";
		return powerHibernateDao.findListByHql(hql, status);
	}
    
    /**
     * 根据节点Id查询部门列表
     * @param nodeId
     * @return
     */
    public List<SysDept> findListDeptByNodeId(Long nodeId){
    	String hql="select e.dept  from CouldNodeDept  e where e.couldNode.id=?";
    	return powerHibernateDao.findListByHql(hql, nodeId);
    }
	/**
	 * 根据部门ID查询对应信息
	 * @author fangbin
	 * @param deptId
	 * @return
	 */
	public List<CloudNodeInfo> findListByDeptId(Long deptId){
		String hql = "select cni from CouldNodeDept cnd,CloudNodeInfo cni where  cni.status=1 and cnd.couldNode.id=cni.id";
		if(null!=deptId){
			hql+=" and cnd.dept.id="+deptId;
		}
		return powerHibernateDao.findListByHql(hql);
		
	}
    public PowerHibernateDao getPowerHibernateDao() {
        return powerHibernateDao;
    }
    
    @Autowired
    public void setPowerHibernateDao(PowerHibernateDao powerHibernateDao) {
        this.powerHibernateDao = powerHibernateDao;
    }

}
