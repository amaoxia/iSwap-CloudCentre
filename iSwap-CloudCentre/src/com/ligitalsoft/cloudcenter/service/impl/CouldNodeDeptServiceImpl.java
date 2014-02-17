package com.ligitalsoft.cloudcenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.cloudcenter.dao.CouldNodeDeptDao;
import com.ligitalsoft.cloudcenter.service.ICouldNodeDeptService;
import com.ligitalsoft.model.cloudcenter.CouldNodeDept;

@Service
public class CouldNodeDeptServiceImpl extends BaseSericesImpl<CouldNodeDept>
		implements ICouldNodeDeptService {

	@Autowired
	private CouldNodeDeptDao couldNodeDeptDao;

	
	@Override
	public List<CouldNodeDept> findDeptByDeptId(Long deptId) {
		return couldNodeDeptDao.findCouldNodeDeptByDeptId(deptId);
	}
	
	@Override
	public EntityHibernateDao<CouldNodeDept> getEntityDao() {
		return couldNodeDeptDao;
	}
}
