/*
 * @(#)IChangeItemDocumentService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.changemanage.ChangeItemDocument;

/**
 * 交换指标文档_文档管理SERVICE
 * @author zhangx
 * @since Jun 27, 2011 2:50:37 PM
 * @name com.ligitalsoft.datasharexchange.service.IChangeItemDocumentService.java
 * @version 1.0
 */

public interface IChangeItemDocumentService extends IBaseServices<ChangeItemDocument> {

    /**
     * 根据部门Id查询指标
     * @param deptId
     * @return
     * @author zhangx
     */
    public List<ChangeItemDocument> findListByDeptId(Long deptId);
    
    
    /**
     * 修改修改文档发布状态
     * @param type
     * @author zhangx
     */
    public void updateStatus(Serializable[] ids,String type) throws ServiceException,URISyntaxException ;
}
