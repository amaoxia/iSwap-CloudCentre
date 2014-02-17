package com.ligitalsoft.cloudnode.service;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudnode.MappingFile;

public interface IMappingFileService extends IBaseServices<MappingFile> {
	/**
	 * 更改发布状态
	 * 
	 * @author fangbin
	 * @param ids
	 * @param status
	 * @throws ServiceException
	 */
	public void updateStatus(Long[] ids, String status) throws ServiceException;
}
