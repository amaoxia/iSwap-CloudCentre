package com.ligitalsoft.cloudnode.service;



import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudnode.CloudnodeListen;
import com.ligitalsoft.model.cloudnode.DataSource;

public interface ICloudnodeListenService extends IBaseServices<CloudnodeListen> {
	/**
	 *将启动的任务添加到缓存中
	 */
	public void updateStatus(CloudnodeListen cloudnodeListen);
	
	 public List<CloudnodeListen> findMongoDataSourcesByDept(String status,Long deptId);
}
