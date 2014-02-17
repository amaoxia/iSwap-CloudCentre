package com.ligitalsoft.classification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.classification.dao.ClassificationDao;
import com.ligitalsoft.classification.service.IClassificationService;
import com.ligitalsoft.model.classification.Classification;

@Service("classificationService")
public class ClassificationServiceImpl extends BaseSericesImpl<Classification>
		implements IClassificationService {
	@Autowired
	private ClassificationDao classificationDao;

	@Override
	public EntityHibernateDao<Classification> getEntityDao() {
		return classificationDao;
	}

}
