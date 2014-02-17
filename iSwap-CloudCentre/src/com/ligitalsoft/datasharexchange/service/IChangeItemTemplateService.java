/*
 * @(#)IChangeItemTemplateService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service;


import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.changemanage.ChangeItemTemplate;


/**
 * 交换指标_模板SERVICE
 * @author  zhangx
 * @since   Jun 27, 2011 2:57:39 PM
 * @name    com.ligitalsoft.datasharexchange.service.IChangeItemTemplateService.java
 * @version 1.0
 */
public interface IChangeItemTemplateService extends IBaseServices<ChangeItemTemplate> {
    /**
     * 设置模板状态
     * @param ids 
     * @param status
     * @author zhangx
     */
    public void updateStatus(Long[] ids, String status)throws ServiceException;
}

