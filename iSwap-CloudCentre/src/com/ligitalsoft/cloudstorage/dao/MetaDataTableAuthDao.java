/*
 * @(#)MetaDataTableAuthDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.model.cloudstorage.MetaDataTableAuth;

/**
 * @author zhangx
 * @since Aug 14, 2011 8:56:38 PM
 * @name com.ligitalsoft.cloudstorage.dao.MetaDataTableAuthDao.java
 * @version 1.0
 * @SuppressWarnings("unchecked")
 */
@Repository
public class MetaDataTableAuthDao extends EntityHibernateDao<MetaDataTableAuth> {

	/**
	 * 申请ID
	 * 
	 * @param applyId
	 * @param tableId
	 * @return
	 * @author zhangx
	 */
	public List<MetaDataTableAuth> findByTableApplyId(Long applyId) {
		String hql = "select e from MetaDataTableAuth e where e.metaDataTableApply.id=? order by e.id asc";
		return powerHibernateDao.findListByHql(hql, applyId);
	}

	/**
	 * 申请ID
	 * 
	 * @param applyId
	 * @param tableId
	 * @return
	 * @author zhangx
	 */
	public List<MetaDataTableAuth> findByTableApplyIdStatus(Long applyId,
			String filedStatus) {
		String hql = "select e from MetaDataTableAuth e where e.metaDataTableApply.id=? and e.filedAuthState=? order by e.id asc";
		Object[] obj = { applyId, filedStatus };
		return powerHibernateDao.findListByHql(hql, obj);
	}

	public List<MetaDataTableAuth> findByDataAuthStateApplyId(Long applyId,
			String dataAuthState) {
		Object[] obj = { applyId, dataAuthState };
		String hql = "select e from MetaDataTableAuth e where e.metaDataTableApply.id=? and e.dataAuthState=? order by e.id asc";
		return powerHibernateDao.findListByHql(hql, obj);
	}

	/**
	 * 根据申请Id
	 * 
	 * @param applyId
	 * @param tableId
	 * @return
	 * @author zhangx
	 */
	public MetaDataTableAuth findByTableApplyTableId(Long applyId, Long tableId) {
		String hql = "select e from MetaDataTableAuth e where e.metaDataTableApply.id=? and e.tableInfo.id=? order by e.id asc";
		List<MetaDataTableAuth> list = powerHibernateDao.findListByHql(hql,
				new Object[] { applyId, tableId });
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * /** 申请ID 查找申请字段授权通过的
	 * 
	 * @param applyId
	 * @param tableId
	 * @return
	 * @author zhangx
	 */
	public List<MetaDataTableAuth> findByFiledAuthApplyId(Long applyId,
			String filedAuthState) {
		String hql = "select e from MetaDataTableAuth e where e.metaDataTableApply.id=? and e.filedAuthState=?  order by e.id asc";
		return powerHibernateDao.findListByHql(hql, new Object[] { applyId,
				filedAuthState });
	}

	/**
	 * 查找下载数据字段授权通过的
	 * 
	 * @param applyId
	 * @param tableId
	 * @return
	 * @author zhangx
	 */
	public List<MetaDataTableAuth> findByDataAuthStateApplyId(Long applyId) {
		String hql = "select e from MetaDataTableAuth e where e.metaDataTableApply.id=? and e.dataAuthState is not null order by e.id asc";
		return powerHibernateDao.findListByHql(hql, applyId);
	}

	/**
	 * 根据指标Id查询申请字段
	 * 
	 * @param itemId
	 * @return
	 * @author zhangx
	 */
	public List<MetaDataTableAuth> findByMetaId(Long itemId) {
		String hql = "select e from MetaDataTableAuth e where e.metaDataTableApply.metaData.id=? order by e.id asc";
		return powerHibernateDao.findListByHql(hql, itemId);
	}

	/**
	 * 根据指标Id查询申请字段
	 * 
	 * @param itemId
	 * @return
	 * @author zhangx
	 */
	public List<MetaDataTableAuth> findByMetaId(Long itemId, Long deptId,
			String dataShareState) {
		String hql = "select e from MetaDataTableAuth e where e.metaDataTableApply.metaData.id=? and e.metaDataTableApply.sysDept.id=?";
		Object[] obj = null;
		if (!StringUtils.isBlank(dataShareState)) {
			hql += "and e.dataAuthState=?";
			obj = new Object[] { itemId, deptId, dataShareState };
		} else {
			obj = new Object[] { itemId, deptId };
		}
		return powerHibernateDao.findListByHql(hql, obj);
	}

	/**
	 * 根据指标Id查询申请字段
	 * 
	 * @param itemId
	 *            deptId appId
	 * @return
	 * @author zhangx
	 */
	public List<MetaDataTableAuth> findByMetaId(Long itemId, Long deptId,
			Long appId, String dataShareState) {
		String hql = "select e from MetaDataTableAuth e where e.metaDataTableApply.metaData.id=? and e.metaDataTableApply.sysDept.id=? and e.metaDataTableApply.appMsg.id=?";
		Object[] obj = null;
		if (!StringUtils.isBlank(dataShareState)) {
			hql += "and e.dataAuthState=?";
			obj = new Object[] { itemId, deptId, appId, dataShareState };
		} else {
			obj = new Object[] { itemId, deptId, appId };
		}

		return powerHibernateDao.findListByHql(hql, obj);
	}

	/**
	 * 更具申请ID, 申请字段Id 查询某个具体的字段
	 * 
	 * @param applyId
	 * @param tableId
	 * @return
	 * @author zhangx
	 */
	public List<MetaDataTableAuth> findByTableApplyId(Long applyId, Long tableId) {
		String hql = "select e from MetaDataTableAuth e where e.metaDataTableApply.id=? and e.tableInfo.id=? order by e.id asc";
		return powerHibernateDao.findListByHql(hql, new Object[] { applyId,
				tableId });
	}
}
