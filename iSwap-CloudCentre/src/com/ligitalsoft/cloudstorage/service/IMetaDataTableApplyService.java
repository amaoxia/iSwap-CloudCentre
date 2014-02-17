/*
 * @(#)IMetaDataTableApplyService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.service;

import java.util.List;
import java.util.Map;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.cloudstorage.MetaDataTableApply;

/**
 * 数据申请查看表_SERVICE
 * @author zhangx
 * @since Aug 11, 2011 1:38:43 AM
 * @name com.ligitalsoft.cloudstorage.service.IMetaDataTableApplyService.java
 * @version 1.0
 */

public interface IMetaDataTableApplyService extends IBaseServices<MetaDataTableApply> {

    /**
     * 指标Id 申请对象 数据项
     * @param itemId
     * @param apply
     * @param tids
     * @author zhangx
     */
    public void saveOrUpdate(Long itemId, Long dataAppId, MetaDataTableApply apply, String tids)
                    throws ServiceException;

    /**
     * 字段授权
     * @param id
     * @param ids
     * @author zhangx
     */
    public void addAuth(Long id, Long[] ids, String noIds);

    /**
     * 数据授权
     * @param id
     * @param ids
     * @author zhangx
     */
    public void addDataAuth(Long id, Long[] ids, String noIds);

    /**
     * 查询当前部门是否申请此指标
     * @param itemId
     * @param deptId
     * @param appId
     * @return
     * @author zhangx
     */
    public MetaDataTableApply findByItemDeptId(Long itemId, Long deptId, Long appId);
    /**
     * 查询当前部门申请状态
     * @param itemId
     * @param deptId
     * @param appId
     * @return
     * @author zhangx
     */
    public MetaDataTableApply findByItemDataState(Long itemId, Long deptId, Long appId, String dataApplyState);

    /**
     * 增加数据申请信息
     * @author zhangx
     */
    public void addDataApply(MetaDataTableApply apply, String checkids, String nocheckids);

    /**
     * 查询数据
     * @return
     * @author zhangx
     */
    public List<Object[]> findDataList(Long id,PageBean pageBean)throws ServiceException ;
    /**
     * 查询数据
     * @return
     * @author zhangx
     */
    public List<Object[]> findDataList(Long id) throws ServiceException;
}
