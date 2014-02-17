package com.ligitalsoft.sysmanager.service;

import java.io.Serializable;

import net.sf.json.JSONArray;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.system.SysRole;

/**
 * 角色信息SERVICE
 * @author zhangx
 * @since May 16, 2011 11:17:00 AM
 * @name com.ligitalsoft.sysmanager.service.ISysRoleService.java
 * @version 1.0
 */
public interface ISysRoleService extends IBaseServices<SysRole> {

    /**
     * 角色树形菜单
     * @return
     * @author zhangx
     */
    public JSONArray roleTree();
    
  /**
   * 删除多个对象的数据  角色和用户关联对象
   */
    public void deleteAllByIds(Serializable[] ids) throws ServiceException;
}