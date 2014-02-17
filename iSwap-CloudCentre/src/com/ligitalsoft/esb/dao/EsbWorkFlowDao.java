package com.ligitalsoft.esb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.esb.EsbTaskMsg;
import com.ligitalsoft.model.esb.EsbWorkFlow;
/**
 * esb的流程持久化
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-13 下午07:29:25
 *@Team 研发中心
 */
@Repository
public class EsbWorkFlowDao extends EntityHibernateDao<EsbWorkFlow>{
	/**
	 * 根据应用取流程
	 * @param AppMsgId
	 * @return 
	 * @author  hudaowan
	 * @date 2011-10-19 下午06:40:36
	 */
	public List<EsbWorkFlow> getAllByAppMsg(Long AppMsgId){
		String hql = " from EsbWorkFlow wf where wf.appMsg.id=" + AppMsgId;
		List<EsbWorkFlow> workFlowList = this.findListByHql(hql);
		return workFlowList;
	}

} 
