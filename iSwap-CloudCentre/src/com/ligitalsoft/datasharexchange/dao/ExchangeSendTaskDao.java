/*
 * @(#)ExchangeSendTaskDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.model.changemanage.ExchangeSendTask;
import com.ligitalsoft.util.Constant;
import com.ligitalsoft.util.ExchangeDateUtil;

/**
 * 交换发送任务dao层
 * @author daic
 * @since 2011-08-16 16:30:38
 * @name com.ligitalsoft.cloudstorage.dao.ExchangeSendTaskDao.java
 * @version 1.0
 */
@Repository("exchangeSendTaskDao")
public class ExchangeSendTaskDao extends EntityHibernateDao<ExchangeSendTask> implements Constant {
	@SuppressWarnings("unchecked")
	public ExchangeSendTask getSendTask(long itemId,Date execDate){
		List<ExchangeSendTask> list = findListByHql(
				"from ExchangeSendTask st where st.item.id = ? and st.execDate = ?",
				new Object[] { itemId, execDate });
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	@SuppressWarnings({ "unchecked", "null" })
	public boolean checkTask(String dateTime) {
		String sql = "from ExchangeSendTask st where st.execDate like '"+dateTime+"%'";
		List<ExchangeSendTask> list = findListByHql(sql);
		if (list!=null || list.size()>0) {
			return true;
		}else{
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getSendDeptList(String beginTime, String endTime) {
		String sql = "select d.ID,d.dept_name,T1.sendState from sys_dept d LEFT JOIN  " +
				"(select it.dept_id as deptid,max(st.SEND_STATE) as sendState ,dept.dept_name as deptname " +
				"from change_send_task st,change_item it , sys_dept dept,change_item_cycle ic  " +
				"where st.item_id=it.ID and 1=1 and it.dept_id=dept.ID  and ic.id=it.cycle_id and " +
				"(( ? between st.EXEC_START_DATE and st.EXEC_END_DATE)  or (? between st.EXEC_START_DATE and st.EXEC_END_DATE)) " +
				"and st.FINISHED_STATE=? " +
				"group by it.DEPT_ID ) t1 " +
				"on  D.ID=T1.deptid  WHERE D.ID<>?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setParameter(0, ExchangeDateUtil.strToDate(beginTime,"yyyy-MM-dd"));
		query.setParameter(1, ExchangeDateUtil.strToDate(endTime,"yyyy-MM-dd"));
		query.setParameter(2, String.valueOf(TASK_STATE_UNFINISHED));
		query.setParameter(3, "1");
		return query.list();
		}
	/**
	 * 　通过开始结束时间以及所属应用查询部门发送任务
	 * @author fangbin
	 * @param beginTime
	 * @param endTime
	 * @param appId
	 * @return
	 */
	public List<Object[]> getSendDeptList(String beginTime, String endTime,String appId) {
		String sql = "select d.ID,d.dept_name,T1.sendState from sys_dept d LEFT JOIN  " +
				"(select it.dept_id as deptid,max(st.SEND_STATE) as sendState ,dept.dept_name as deptname " +
				"from change_send_task st,change_item it , sys_dept dept,change_item_cycle ic ,CHANGE_ITEM_APPMSG cia " +
				"where st.item_id=it.ID and 1=1 and it.dept_id=dept.ID  and ic.id=it.cycle_id and " +
				"(( ? between st.EXEC_START_DATE and st.EXEC_END_DATE)  or (? between st.EXEC_START_DATE and st.EXEC_END_DATE)) " +
				"and st.FINISHED_STATE=? " ;
				if(!StringUtils.isBlank(appId)){
					sql+=" and cia.APP_ID in ("+appId+") and cia.ITEM_ID=it.id "; 
				}
				sql+="group by it.DEPT_ID ) t1 " +
				"on  D.ID=T1.deptid  WHERE D.ID<>?";
		
		Query query = this.getSession().createSQLQuery(sql);
		query.setParameter(0, ExchangeDateUtil.strToDate(beginTime,"yyyy-MM-dd"));
		query.setParameter(1, ExchangeDateUtil.strToDate(endTime,"yyyy-MM-dd"));
		query.setParameter(2, String.valueOf(TASK_STATE_UNFINISHED));
		query.setParameter(3, "1");
		return query.list();
		}
	@SuppressWarnings("unchecked")
	public List<ExchangeSendTask> getSendTaskList(String beginTime, String endTime,
			String deptId) {
		List<ExchangeSendTask> list=  findListByHql(
				"from ExchangeSendTask st where st.item.sysDept.id = ? and (( ? between st.execStartDate  and st.execEndDate)  or (? between st.execStartDate and st.execEndDate)) and st.finishedState = ?",
				new Object[] { Long.valueOf(deptId), ExchangeDateUtil.strToDate(beginTime,"yyyy-MM-dd"),
						ExchangeDateUtil.strToDate(endTime,"yyyy-MM-dd"),String.valueOf(TASK_STATE_UNFINISHED)});
		return list;
	}
}
