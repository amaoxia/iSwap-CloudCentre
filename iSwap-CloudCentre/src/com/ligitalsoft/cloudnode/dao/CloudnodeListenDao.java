package com.ligitalsoft.cloudnode.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.cloudnode.CloudnodeListen;
import com.ligitalsoft.model.cloudnode.DataSource;

/**
 * 云节点监听DAO
 * @author arcgismanager
 *
 */
@Repository
public class CloudnodeListenDao extends EntityHibernateDao<CloudnodeListen> {

	@SuppressWarnings("unchecked")
	public List<CloudnodeListen> findMongoDataSourcesByDept(String status,Long deptId){
		String hql = "from CloudnodeListen ds where ds.status=? and ds.sysDept.id=?";
		Object[] obj = { status, deptId };
		List<CloudnodeListen> datas = powerHibernateDao.findListByHql(hql, obj);
		return datas;
	}
}
