package com.ligitalsoft.datasharexchange.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.model.changemanage.SendResult;

/**
 * 数据发送记录
 * @author fangbin
 *
 */
@Repository
public class SendResultDao extends EntityHibernateDao<SendResult> {
	/**
	 * 获得接收的数据量
	 * @author fangbin
	 * @param map
	 * @return
	 */
	public int getDataNum(Map<String,String> map){
		List<Object> params = new ArrayList<Object>(5);
		StringBuffer where = new StringBuffer();
		int i =0;
		String hql="select sum(sr.dataNum) as datanum from SendResult sr ";
		if(!StringUtils.isBlank(map.get("startDate"))){
			if(params.size()>0){
				where.append(" and ");
			}
			where.append(" sr.exchangeDate >= '"+map.get("startDate")+" 00:00:00'");
			
			params.add(map.get("startDate"));
		}
		if(!StringUtils.isBlank(map.get("endDate"))){
			if(params.size()>0){
				where.append(" and ");
			}
			where.append(" sr.exchangeDate <= '"+map.get("endDate")+ " 23:59:59'");
			
			params.add(map.get("endDate"));
		}
		if(!StringUtils.isBlank(map.get("itemId"))){
			if(params.size()>0){
				where.append(" and ");
			}
			where.append(" sr.itemId ="+map.get("itemId"));
		}
		Object[] paramsArr = null;
		if(params.size()>0){
			hql += " where "+where.toString();
			paramsArr = params.toArray();
		}  
		i=this.getTotalByHql(hql);
		return i ;
	}
}
