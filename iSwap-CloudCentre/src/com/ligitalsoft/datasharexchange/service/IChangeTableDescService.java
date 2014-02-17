/*
 * @(#)IChangeTableDescService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service;

import java.sql.SQLException;
import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.changemanage.ChangeTableDesc;

/**
 * 指标表结构信息 SERVICE
 * @author zhangx
 * @since Jun 14, 2011 5:22:41 PM
 * @name com.ligitalsoft.datasharexchange.service.IChangeTableDescService.java
 * @version 1.0
 */

public interface IChangeTableDescService extends IBaseServices<ChangeTableDesc> {

    /**
     * 生成表数据结构
     * @author zhangx
     */
    public void createTableDescData(Long itemId) throws SQLException;
    
    public List<ChangeTableDesc> getTableDescByitemId(Long itemId) throws SQLException;

    /**
     * 设置表字段显示状态
     * @param ids
     * @param status
     * @author zhangx
     */
    public void updateStatus(Long[] ids, String status);
}
