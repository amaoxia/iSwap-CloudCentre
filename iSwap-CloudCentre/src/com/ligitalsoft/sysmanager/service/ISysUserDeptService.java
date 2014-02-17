/*
 * @(#)ISysUserRoleService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.service;

import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserDept;
import com.ligitalsoft.model.system.SysUserRole;

/**
 * 用户部门service
 * @author zhangx
 * @since May 26, 2011 1:29:11 AM
 * @name com.ligitalsoft.sysmanager.service.ISysUserRoleService.java
 * @version 1.0
 */

public interface ISysUserDeptService extends IBaseServices<SysUserDept> {

    /**
     * 根据用户Id查询对象
     * @param userId
     * @return
     * @author zhangx
     */
    public SysUserDept findByUserId(Long userId);
    
    /**
     * 
     * @param dpetId
     * @return
     */
    public List<SysUserDept> findUserByDeptId(Long deptId);
    
    /**
     * 单点集成同步更新保存用户相关内容
     * @param user
     * @param dept
     * @param userDept
     * @param userRole
     */
    public void saveObjsForSSO(SysUser user,SysDept dept,SysUserDept userDept,SysUserRole userRole);
}
