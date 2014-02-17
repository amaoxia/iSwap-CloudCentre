package com.ligitalsoft.cloudstorage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.cloudstorage.CouldDataSource;

@Repository
public class CouldDataSourceDao extends EntityHibernateDao<CouldDataSource> {
	public CouldDataSource findDatasourceIsExit(String ip, String port,
			String dbName) {
		String hql = "from  CouldDataSource e where e.ip=? and e.port=? and e.dbName=? ";
		Object[] obj = { ip, port, dbName };
		List<CouldDataSource> datas = powerHibernateDao.findListByHql(hql, obj);
		if (datas != null && datas.size() > 0) {
			return datas.get(0);
		}
		return null;
	}
}
