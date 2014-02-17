package com.ligitalsoft.cloudnode.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.cloudnode.DataSource;
import com.ligitalsoft.model.cloudnode.MessageListen;
@Repository
public class MessageListenDao extends EntityHibernateDao<MessageListen> {

	@SuppressWarnings("unchecked")
	public List<MessageListen> findMQDataSourcesByDept(String status,Long deptId){
		String hql = "from MessageListen ds where ds.status=? and ds.sysDept.id=?";
		Object[] obj = { status, deptId };
		List<MessageListen> datas = powerHibernateDao.findListByHql(hql, obj);
		return datas;
	}
}
