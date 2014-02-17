package com.ligitalsoft.cloudstorage.dao;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.changemanage.MapperDetails;
import com.ligitalsoft.model.cloudstorage.MetaDataMapperDetails;
@Repository
public class MetaDataMapperDetailsDao extends EntityHibernateDao<MetaDataMapperDetails> {

	public int removeAllByMapperId(Long mapperId){
		 String hql = "delete MetaDataMapperDetails e where e.mapper.id=?";
	     return powerHibernateDao.executeUpdate(hql, mapperId);
	}
}
