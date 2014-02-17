/*
 * @(#)IMetaDataTableAuthService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.service;

import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudstorage.MetaDataTableAuth;

/**
 * 申请数据项授权_SERVICE
 * @author zhangx
 * @since Aug 15, 2011 11:30:42 AM
 * @name com.ligitalsoft.cloudstorage.service.IMetaDataTableAuthService.java
 * @version 1.0
 */

public interface IMetaDataTableAuthService extends IBaseServices<MetaDataTableAuth> {

    /**
     * 申请Id查询申请字段信息
     * @param applyId
     * @return
     * @author zhangx
     */
    public List<MetaDataTableAuth> findByTableApplyId(Long applyId);
    /**
     * 申请Id查询申请字段数据信息
     * @param applyId
     * @return
     * @author zhangx
     */
    public List<MetaDataTableAuth> findByDataAuthStateApplyId(Long applyId);
    /**
     * 申请Id查询申请字段信息
     * @param applyId
     * @return
     * @author zhangx
     */
    public List<MetaDataTableAuth> findByFiledAuthApplyId(Long applyId,String status);
    
    /**
     *  根据指标ID 查询申请的字段
     * @return
     * @author zhangx
     */
    public List<MetaDataTableAuth> findByMetaId(Long itemId);
    /**
     * 查询下载数据授权通过的字段
     * @param applyId
     * @param dataAuthState
     * @return
     */
    public List<MetaDataTableAuth> findByDataAuthStateApplyId(Long applyId,String dataAuthState);
}
