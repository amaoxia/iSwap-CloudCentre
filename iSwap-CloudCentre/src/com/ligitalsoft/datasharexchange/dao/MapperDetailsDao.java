package com.ligitalsoft.datasharexchange.dao;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.changemanage.MapperDetails;
@Repository
public class MapperDetailsDao extends EntityHibernateDao<MapperDetails> {

	public int removeAllByMapperId(Long mapperId){
		 String hql = "delete MapperDetails e where e.mapper.id=?";
	     return powerHibernateDao.executeUpdate(hql, mapperId);
	}
}
