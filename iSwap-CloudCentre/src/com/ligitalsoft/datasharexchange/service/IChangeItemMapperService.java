package com.ligitalsoft.datasharexchange.service;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.changemanage.ChangeItemMapper;
import com.ligitalsoft.model.changemanage.Mapper;

public interface IChangeItemMapperService extends IBaseServices<ChangeItemMapper> {
	/**
	 * 更改发布状态
	 * 
	 * @author fangbin
	 * @param ids
	 * @param status
	 * @throws ServiceException
	 */
	public void updateStatus(Long[] ids, String status) throws ServiceException;
	
	public Mapper save(ChangeItemMapper mapper);
	
}
