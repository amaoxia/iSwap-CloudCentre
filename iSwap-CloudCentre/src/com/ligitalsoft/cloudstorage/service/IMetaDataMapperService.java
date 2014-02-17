package com.ligitalsoft.cloudstorage.service;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.changemanage.Mapper;
import com.ligitalsoft.model.cloudstorage.MetaDataMapper;

public interface IMetaDataMapperService extends IBaseServices<MetaDataMapper> {
	/**
	 * 更改发布状态
	 * 
	 * @author fangbin
	 * @param ids
	 * @param status
	 * @throws ServiceException
	 */
	public void updateStatus(Long[] ids, String status) throws ServiceException;
	
	public Mapper save(MetaDataMapper mapper);
	
}
