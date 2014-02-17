/*
 * @(#)AppCloudNodeService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudcenter.service;

import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudcenter.AppCloudNode;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;

/**
 * 
 * 
 * 
 * AppCloudNodeService.java
 * 
 * @author bianxj
 * @email bianxiaojin@gmail.com 2011-6-24
 * @description
 * @see
 */

public interface AppCloudNodeService extends IBaseServices<AppCloudNode> {
	/**
	 * 根据应用删除云端节点
	 * 
	 * @param appId
	 */
	public void removeNodeInfoByApp(Long appId);

	/**
	 * 根据应用获取云端节点集合
	 * 
	 * @param appId
	 * @return
	 */
	public List<CloudNodeInfo> findNodeInfoListByApp(Long appId);

	/**
	 * 查询appId下的节点
	 * 
	 * @param appId
	 * @return
	 */
	public List<AppCloudNode> findListByAppId(Long appId);
	
	public List<AppMsg> findListByCloudId(Long cloudId);
}
