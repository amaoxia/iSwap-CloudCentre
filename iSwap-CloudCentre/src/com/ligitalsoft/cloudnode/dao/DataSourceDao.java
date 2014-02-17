package com.ligitalsoft.cloudnode.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.cloudnode.DataSource;

@Repository
public class DataSourceDao extends EntityHibernateDao<DataSource> {
	@SuppressWarnings("unchecked")
	public DataSource findDatasourceIsExit(String ip, String port,
			String dbName) {
		String hql = "from  DataSource e where e.ip=? and e.port=? and e.dbName=? ";
		Object[] obj = { ip, port, dbName };
		List<DataSource> datas = powerHibernateDao.findListByHql(hql, obj);
		if (datas != null && datas.size() > 0) {
			return datas.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<DataSource> findDataSourcesByDept(String status,Long deptId){
		String hql = "from DataSource ds where ds.status=? and ds.sysDept.id=?";
		Object[] obj = { status, deptId };
		List<DataSource> datas = powerHibernateDao.findListByHql(hql, obj);
		return datas;
	}
}
