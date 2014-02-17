package com.ligitalsoft.sysmanager.service;

import java.util.List;
import java.util.Map;

import com.common.framework.dao.SortPara;
import com.common.framework.services.IBaseServices;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserDept;
import com.ligitalsoft.model.system.SysUserRole;

/**
 * 用户信息SERVICE
 * @author zhangx
 * @since May 16, 2011 11:14:22 AM
 * @name com.ligitalsoft.sysmanager.service.ISysUserService.java
 * @version 1.0
 */
public interface ISysUserService extends IBaseServices<SysUser> {

    /**
     * 自定义分页
     * @param pageBean
     * @param args
     * @return
     * @author zhangx
     */
    public List<Object[]> findUserListByPage(PageBean pageBean, Map<String, String> args,List<SortPara> sortParas);

    /**
     * 添加或修改用户
     * @param usr
     * @param dept
     * @param role
     * @author zhangx
     */
    public void saveOrUpdate(SysUser usr, SysUserDept dept, SysUserRole role);

    /**
     * 设置用户状态
     * @param ids
     * @param status
     * @author zhangx
     */
    public void updateStatus(Long[] ids, Character status);
}