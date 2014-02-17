/*
 * @(#)ISysRolePermissonService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.service;

import java.util.List;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.system.SysRolePermisson;

/**
 * 角色_权限信息
 * @author zhangx
 * @since May 20, 2011 4:19:06 PM
 * @name com.ligitalsoft.sysmanager.service.ISysRolePermissonService.java
 * @version 1.0
 */
public interface ISysRolePermissonService extends IBaseServices<SysRolePermisson> {

    /**
     * 批量添加角色权限
     * @param ids
     * @author zhangx
     */
    public void addByIds(String ids, Long roleId) throws ServiceException;
    /**
     * 根据角色删除角色权限关系
     * @param roleId
     *            角色ID
     * @author zhangx
     */
    public void deleteByRoleId(Long roleId);
    /**
     * 根据角色查询菜单信息
     * @return
     * @author zhangx
     */
    public List<SysRolePermisson> findPermissionByRoleId(Long roleId);
    

}
