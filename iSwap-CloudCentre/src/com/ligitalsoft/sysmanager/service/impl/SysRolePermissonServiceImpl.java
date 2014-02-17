/*
 * @(#)SysRolePermissonServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.model.system.SysPermission;
import com.ligitalsoft.model.system.SysRolePermisson;
import com.ligitalsoft.sysmanager.dao.SysPermissionDao;
import com.ligitalsoft.sysmanager.dao.SysRolePermissonDao;
import com.ligitalsoft.sysmanager.service.ISysRolePermissonService;

/**
 * @author zhangx
 * @since May 20, 2011 4:22:21 PM
 * @name com.ligitalsoft.sysmanager.service.impl.SysRolePermissonServiceImpl.java
 * @version 1.0
 */
@Service
public class SysRolePermissonServiceImpl extends BaseSericesImpl<SysRolePermisson> implements ISysRolePermissonService {

    private SysRolePermissonDao sysRolePermissonDao;
    private SysPermissionDao sysPermissionDao;

    @Override
    public void addByIds(String ids, Long roleId) throws ServiceException {
        // 所有选中的父类ID
        StringBuffer temp = new StringBuffer();
        List<String> list = new ArrayList<String>();
        String[] args = ids.split(",");
        for (String str : args) {
            if (!StringUtils.isBlank(str)) {
                Long id = Long.parseLong(str);
                if (id!=null&&id != 0) {
                    SysPermission permission = sysPermissionDao.findById(id);
                    getAllIds(permission, temp);
                }
            }
        }
        String[] temps = temp.toString().split(",");
        for (int i = 0; i < temps.length; i++) {
            if (!StringUtils.isBlank(temps[i])) {
                if (!isExist(list, temps[i])) {
                    list.add(temps[i]);
                }
            }
        }
        SysRolePermisson permisson = null;
        // 重置当前角色权限菜单
        deleteByRoleId(roleId);
        // 添加当前权限菜单
        if (list != null && list.size() > 0) {
            for (String string : list) {
                if (!StringUtils.isBlank(string)) {
                    Long permissionId = Long.parseLong(string);
                    permisson = new SysRolePermisson();
                    permisson.setPermissionId(permissionId);
                    permisson.setRoleId(roleId);
                    insert(permisson);
                }
            }
        }
    }
    /**
     * 判断一个数在数组中是否出现过
     * @return
     * @author zhangx
     */
    public boolean isExist(List<String> args, String temp) {
        boolean fa = false;
        for (String string : args) {
            if (!StringUtils.isBlank(string) && !StringUtils.isBlank(temp)) {
                if (string.toString().equals(temp.toString())) {
                    fa = true;
                    break;
                }
            }
        }
        return fa;
    }

    /**
     * 得到所有菜单id
     * @param dataList
     * @param args
     * @return
     * @author zhangx
     */
    public StringBuffer getAllIds(SysPermission permission, StringBuffer args) {
        args.append("," + permission.getId());
        if (permission.getPermission() == null) {
            return args;
        }
        SysPermission pers = sysPermissionDao.findById(permission.getPermission().getId());
        if (pers != null) {
            getAllIds(pers, args);
        }
        return args;
    }
    public void deleteByRoleId(Long roleId) {
        sysRolePermissonDao.deleteByRoleId(roleId);
    }

    @SuppressWarnings("unchecked")
    public List<SysRolePermisson> findPermissionByRoleId(Long roleId) {
        String hql = "from SysRolePermisson where roleId=?";
        return sysRolePermissonDao.findListByHql(hql, roleId);
    }
    @Autowired
    public void setSysRolePermissonDao(SysRolePermissonDao sysRolePermissonDao) {
        this.sysRolePermissonDao = sysRolePermissonDao;
    }

    @Autowired
    public void setSysPermissionDao(SysPermissionDao sysPermissionDao) {
        this.sysPermissionDao = sysPermissionDao;
    }
    @Override
    public EntityHibernateDao<SysRolePermisson> getEntityDao() {
        return sysRolePermissonDao;
    }

}
