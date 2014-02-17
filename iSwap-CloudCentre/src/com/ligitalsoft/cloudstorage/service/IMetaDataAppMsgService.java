/*
 * @(#)IMetaDataAppMsgService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.service;

import java.util.List;
import java.util.Map;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudstorage.MetaDataAppMsg;

/**
 * 元数据_应用SERVICE
 * @author zhangx
 * @since Jun 24, 2011 10:40:36 AM
 * @name com.ligitalsoft.cloudstorage.service.IMetaDataAppMsgService.java
 * @version 1.0
 */

public interface IMetaDataAppMsgService extends IBaseServices<MetaDataAppMsg> {

    /**
     * 根据元数据查询对应服务应用
     * @param metaId
     * @return
     * @author zhangx
     */
    public List<MetaDataAppMsg> findListByMetaId(Long metaId);

    /**
     * 更改指标状态
     * @param ids
     * @param status
     * @author zhangx
     */
    public void updateStatus(Long[] ids, String status) ;
    
    /**
     * 通过应用ID查询
     * @author fangbin
     * @param appId
     * @return
     */
    public List<MetaDataAppMsg> findListByAppId(Long appId);
    /**
     * 数据接收按应用排名
     * @author fangbin
     * @return
     */
    public List<Object[]> appRank();
    /**
     *  数据接收按指标排名
     *  @author fangbin
     * @param map
     * @return
     */
    public List<Object[]> targetRank(Map<String,String> map);
    


}
