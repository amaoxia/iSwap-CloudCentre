/*
 * @(#)CloudNodeInfoService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudcenter.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.common.framework.services.IBaseServices;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.system.SysDept;

/**
 * @author lifh
 * @mail wslfh2005@163.com
 * @since 2011-6-16 上午09:58:07
 * @name com.ligitalsoft.cloudcenter.service.CloudNodeInfoService.java
 * @version 1.0
 */

public interface CloudNodeInfoService extends IBaseServices<CloudNodeInfo> {
	/**
	 * 删除应用和节点的关系
	 * 
	 * @param cloudNodeInfoId
	 * @author lifh
	 */
	public void removeAppCloudNode(Long cloudNodeInfoId);

	/**
	 * 删除应用和机构的关系
	 * 
	 * @param cloudNodeInfoId
	 * @author lifh
	 */
	public void removeCouldNodeDept(Long cloudNodeInfoId);

	public List<CloudNodeInfo> findAllByPage(Long appId, String nodeName,
			PageBean page);

	public List<CloudNodeInfo> findAllByPage(String nodeName);

	/**
	 * 得到部门tree
	 * 
	 * @param id
	 * @return
	 */
	public JSONArray getDeptTreeById(Long id);

	/**
	 * 根据节点部署状态查询
	 * 
	 * @param status
	 * @return
	 */
	public List<CloudNodeInfo> findListByStatus(int status);

	/**
	 * 根据节点Id查询部门列表
	 * 
	 * @param nodeId
	 * @return
	 */
	public List<SysDept> findListDeptByNodeId(Long nodeId);
	/**
	 * 　通过部门id在部门与云节点关系表中查询
	 * @author fangbin
	 * @param id
	 * @return
	 */
	public List<CloudNodeInfo> findListNodeByDeptId(Long id);
}
