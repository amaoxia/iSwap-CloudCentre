/*
 * @(#)CloudStorageMakeService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudcenter.service;

import java.util.List;

import com.common.framework.services.IBaseServices;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.cloudcenter.CloudStorageMake;

/**
 * 
 * 
 * 
 * CloudStorageMakeService.java
 * 
 * @author bianxj
 * @email  bianxiaojin@gmail.com
 * 2011-6-29
 * @description 
 * @see
 */
public interface CloudStorageMakeService extends IBaseServices<CloudStorageMake>{
	/**
	 * 分页查询
	 * @param appId
	 * @param nodeName
	 * @param page
	 * @return
	 */
	public List<CloudStorageMake> findAllByPage(Long appId, String storageName, PageBean page) ;
}
