/*
 * @(#)IChangeItemService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.defcat.webservice.CatalogWebService;
import com.ligitalsoft.model.changemanage.ChangeItemCycle;
import com.ligitalsoft.model.changemanage.ChangeItem;

/**
 * 交换_指标 SERVICE
 * @author zhangx
 * @since Jun 14, 2011 3:21:57 PM
 * @name com.ligitalsoft.cloudstorage.service.IChangeItemService.java
 * @version 1.0
 */
public interface IChangeItemService extends IBaseServices<ChangeItem> {

    /**
     * 添加指标应用关系
     * @param metaData
     * @param appID
     * @author zhangx
     */
    public void save(ChangeItem item, String[] appId,ChangeItemCycle cycle);

    /**
     * 修改元指标应用关系
     * @param metaData
     * @param appID
     * @author zhangx
     */
    public void update(ChangeItem item, String[] appId,ChangeItemCycle cycle);
    /**
     * 根据交换类型得到指标 
     * @param dataType
     * @param deptId
     * @return
     * @author zhangx
     */
    public List<ChangeItem> findListByDataType(String dataType,Long deptId);
    /**
     * 根据部门ID得到指标 
     * @param deptId
     * @return
     * @author daic
     */
    public List<ChangeItem> findListByDeptId(Long deptId);

    /**
     * 推送到目录
     * @param itemIds
     * @param catalogId
     * @param categoryIds
     * @author lifh
     */
    public void pushToCatalog(String itemIds, Long catalogId, String categoryIds);
    /**
     * 得到目录 service 
     * @return
     * @author lifh
     */
    public CatalogWebService getService();
    /**
     * 根据部门ID查询数据交换总量和按时次数
	 * @author fangbin
     * @param deptId
     * @return
     */
    public List<Map<String,String>> targetDataShare(String deptId);
    
    /**
     * 获得所有部门下的指标项
     * @author fangbin
     * @param itemId
     * @return
     */
    public JSONArray getItemTree(Long itemId);
    
    public JSONArray getItemTree(Long deptid, Long itemId);
    
    public ChangeItem findChangeItemById(Long id);
    
    public JSONArray getAppItemTreeByDeptId4Node(Long deptId);
    
    public JSONArray getAppItemTreeByDeptId4Center();
    
    public ChangeItem findSendChangeItemByChangeConfId(Integer itemType, Integer dataType, Long changeConfId);

    public void forcedDelete(Long[] ids, String delType);
}
