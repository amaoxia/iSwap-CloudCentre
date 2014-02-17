package com.ligitalsoft.cloudnode.service;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudnode.LocalHostListen;

public interface ILocalHostListenService extends IBaseServices<LocalHostListen> {
	/**
	 * 状态改变时修改缓存中的部署的FTP信息
	 * @author fangbin
	 * @param entity
	 */
	public void updateStatus(LocalHostListen entity) ;
}
