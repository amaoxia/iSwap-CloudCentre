/*
 * @(#)ExchangeSystemConfigDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.model.changemanage.ReceiveResult;

/**
 * 接收结果dao层
 * 
 * @author daic
 * @since 2011-08-23 14:59:14
 * @name com.ligitalsoft.cloudstorage.dao.ReceiveResultDao.java
 * @version 1.0
 */
@Repository("receiveResultDao")
@SuppressWarnings("unused")
public class ReceiveResultDao extends EntityHibernateDao<ReceiveResult> {
	/**
	 * 查询数据接收量
	 * 
	 * @author fangbin
	 * @param map
	 * @return
	 */
	public int getReceiveResult(Map<String, String> map) {
		List<Object> params = new ArrayList<Object>(5);
		StringBuffer where = new StringBuffer();
		String sql = "select  sum(rr.dataNum) as dataNum  from receive_result rr ";
		if (!StringUtils.isBlank(map.get("startDate"))) {
			if (params.size() > 0) {
				where.append(" and ");
			}
			where.append(" rr.exchange_date >= '" + map.get("startDate")
					+ " 00:00:00'");

			params.add(map.get("startDate"));
		}
		if (!StringUtils.isBlank(map.get("endDate"))) {
			if (params.size() > 0) {
				where.append(" and ");
			}
			where.append(" rr.exchange_date <= '" + map.get("endDate")
					+ " 23:59:59'");

			params.add(map.get("endDate"));
		}
		if (!StringUtils.isBlank(map.get("itemId"))) {
			if (params.size() > 0) {
				where.append(" and ");
			}
			where.append(" rr.item_id = " + map.get("itemId"));
			params.add(map.get("deptId"));
		}
		if (!StringUtils.isBlank(map.get("receiveDeptId"))) {
			if (params.size() > 0) {
				where.append(" and ");
			}
			where.append(" rr.receive_dept_id = " + map.get("receiveDeptId"));
			params.add(map.get("receiveDeptId"));
		}
		Object[] paramsArr = null;
		if (params.size() > 0) {
			sql += " where " + where.toString();
			paramsArr = params.toArray();
		}
		return this.getTotalByHql(sql);
	}

	/**
	 * 查询数据接收记录
	 * 
	 * @author fangbin
	 * @param map
	 * @return
	 */
	public List<ReceiveResult> findReceiveResult(Map<String, String> map) {
		List<ReceiveResult> rrList = new ArrayList<ReceiveResult>();
		List<Object> params = new ArrayList<Object>(5);
		StringBuffer where = new StringBuffer();
		String hql = " from ReceiveResult rr ";
		if (!StringUtils.isBlank(map.get("startDate"))) {
			if (params.size() > 0) {
				where.append(" and ");
			}
			where.append(" rr.exchangeDate >= '" + map.get("startDate")
					+ " 00:00:00'");

			params.add(map.get("startDate"));
		}
		if (!StringUtils.isBlank(map.get("endDate"))) {
			if (params.size() > 0) {
				where.append(" and ");
			}
			where.append(" rr.exchangeDate <= '" + map.get("endDate")
					+ " 23:59:59'");

			params.add(map.get("endDate"));
		}
		if (!StringUtils.isBlank(map.get("itemId"))) {
			if (params.size() > 0) {
				where.append(" and ");
			}
			where.append(" rr.itemId = '" + map.get("itemId"));
			params.add(map.get("itemId"));
		}
		if (!StringUtils.isBlank(map.get("receiveDeptId"))) {
			if (params.size() > 0) {
				where.append(" and ");
			}
			where.append(" rr.receiveDeptId = " + map.get("receiveDeptId"));
			params.add(map.get("receiveDeptId"));
		}
		Object[] paramsArr = null;
		if (params.size() > 0) {
			hql += " where " + where.toString();
			paramsArr = params.toArray();
		}
		rrList = this.findListByHql(hql);
		return rrList;
	}
}
