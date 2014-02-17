/*
 * @(#)ITableInfoService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.service;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONArray;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.changemanage.ChangeTableDesc;
import com.ligitalsoft.model.cloudstorage.TableInfo;

/**
 * 指标信息_表结构信息SERVICE
 * @author zhangx
 * @since Jun 16, 2011 8:43:21 PM
 * @name com.ligitalsoft.cloudstorage.service.ITableInfoService.java
 * @version 1.0
 */

public interface ITableInfoService extends IBaseServices<TableInfo> {

    /**
     * 生成表数据结构
     * @author zhangx
     */
    public void createTableDescData(Long itemId) throws SQLException;
    
    /**
     * 树结构
     * @return
     * @author zhangx
     */
     public JSONArray getTree(Long itemId,Long deptId,Long appId);
     
     /**
      * 树结构
      * @return
      * @author zhangx
      */
      public JSONArray getAuthTree(Long applyId,Long itemId,Long deptId,Long appId,String dataShareState);

      public List<TableInfo> getTableInfoByMetaDataId(Long metaDataId) throws SQLException;
}
