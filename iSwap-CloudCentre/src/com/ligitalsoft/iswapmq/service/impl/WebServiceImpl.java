package com.ligitalsoft.iswapmq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.iswapmq.dao.WebServiceDao;
import com.ligitalsoft.iswapmq.service.IWebService;
import com.ligitalsoft.model.serverinput.WebServerInfo;


@Transactional
@Service(value = "webService")
public class WebServiceImpl  extends BaseSericesImpl<WebServerInfo> implements IWebService
{
    @Autowired
    private WebServiceDao webServiceDao;
    
    @Override
    public EntityHibernateDao<WebServerInfo> getEntityDao()
    {
        return this.webServiceDao;
    }

	@Override
	public List<WebServerInfo> findWSDataSourcesByDept(String status,
			Long deptId) {
		return webServiceDao.findWSDataSourcesByDept(status, deptId);
	}
}
