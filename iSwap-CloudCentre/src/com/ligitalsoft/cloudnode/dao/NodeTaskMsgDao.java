package com.ligitalsoft.cloudnode.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.cloudnode.NodeTaskMsg;
import com.ligitalsoft.model.cloudnode.WorkFlow;

@Repository
public class NodeTaskMsgDao extends EntityHibernateDao<NodeTaskMsg> {

	public List<NodeTaskMsg> findListByItemIdsStr(String itemIdsStr) {
		String hql = "from NodeTaskMsg e where e.workFlow.itemId in(" + itemIdsStr+")";
		List<NodeTaskMsg> nodeTaskMsgList = this.findListByHql(hql);
		return nodeTaskMsgList;
	}
	
	public int removeAllByByItemIdsStr(String itemIdsStr){
		String hql = "delete NodeTaskMsg e where e.workFlow.itemId in(" + itemIdsStr+")";
        return this.powerHibernateDao.executeUpdate(hql);
	}
}
