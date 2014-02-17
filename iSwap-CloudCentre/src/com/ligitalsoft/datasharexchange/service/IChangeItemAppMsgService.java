/*
 * @(#)IChangeItemAppMsgService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service;


import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.changemanage.ChangeItemAppMsg;


/**
 * 指标_应用SEVICE
 * @author  zhangx
 * @since   Jun 24, 2011 10:31:59 AM
 * @name    com.ligitalsoft.datasharexchange.service.IChangeItemAppMsgService.java
 * @version 1.0
 */

public interface IChangeItemAppMsgService extends IBaseServices<ChangeItemAppMsg> {
    /**
     * 根据元数据查询对应服务应用
     * @param metaId
     * @return
     * @author zhangx
     */
    public List<ChangeItemAppMsg> findListByItemd(Long itemId);
}

