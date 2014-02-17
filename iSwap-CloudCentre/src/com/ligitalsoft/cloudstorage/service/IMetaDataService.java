/*
 * @(#)IMetaDataService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudstorage.MetaData;

/**
 * 元数据管理 接口
 * @author zhangx
 * @since Jun 16, 2011 8:39:38 PM
 * @name com.ligitalsoft.cloudstorage.service.IMetaDataService.java
 * @version 1.0
 */
public interface IMetaDataService extends IBaseServices<MetaData> {

    /**
     * 添加元数据应用关系
     * @param metaData
     * @param appID
     * @author zhangx
     */
    public void save(MetaData metaData, String[] appId);
    
    
    /**
     * 修改元数据应用关系
     * @param metaData
     * @param appID
     * @author zhangx
     */
    public void update(MetaData metaData, String[] appId);
    
    /**
     * 得到元数据类型树
     * @return
     * @author zhangx
     */
   public JSONArray getTypeTree(String type,String url);
   
   /**
    * 修改指标发布状态
    * @param ids
    * @param status
    * @author zhangx
    */
   public void updateStatus(Long[] ids, String status);
   /**
    * 通过部门ID查询指标项
     * @author fangbin
    * @param deptId
    * @return
    */
   public List<MetaData> getAllByDeptId(String deptId);
}
