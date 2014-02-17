package com.ligitalsoft.iswapmq.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.cloudnode.DataSource;
import com.ligitalsoft.model.serverinput.WebServerInfo;

@Repository("webServiceDao")
public class WebServiceDao extends EntityHibernateDao<WebServerInfo> {

	@SuppressWarnings("unchecked")
	public List<WebServerInfo> findWSDataSourcesByDept(String status,Long deptId){
		String hql = "from WebServerInfo ds where ds.status=? and ds.sysDept.id=?";
		Object[] obj = { status, deptId };
		List<WebServerInfo> datas = powerHibernateDao.findListByHql(hql, obj);
		return datas;
	}
}
