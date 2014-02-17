/*
 * @(#)AuthRolePermission.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.util;

import java.util.ArrayList;
import java.util.List;



import com.common.framework.help.SpringContextHolder;
import com.ligitalsoft.model.system.SysPermission;
import com.ligitalsoft.model.system.SysUserRole;
import com.ligitalsoft.sysmanager.service.ISysPermissionService;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;

/**
 * 菜单工具类
 * @author zhangx
 * @since May 29, 2011 2:18:10 PM
 * @name com.ligitalsoft.sysmanager.util.AuthRolePermission.java
 * @version 1.0
 */
public class AuthRolePermission {
    private static List<SysPermission> persmissions = new ArrayList<SysPermission>();
    private static AuthRolePermission  authRolePermission;
    private AuthRolePermission() {

    }
    private static class AuthRolePermissionHolder{
        /**
         * 静态初始化器，由JVM保证线程安全
         * @author shawl
         */
        private static AuthRolePermission instance = new AuthRolePermission();
    }
    /**
     * 对象实例
     * @return
     * @author zhangx
     */
    public static AuthRolePermission getInstance(Long userId) {
        if(authRolePermission==null){
            persmissions=getRolePermission(userId);
            authRolePermission= AuthRolePermissionHolder.instance;
        }
      return authRolePermission;
    }
    /**
     * 得到用户所有类
     * @return
     * @author zhangx
     */
    private static  List<SysPermission> getRolePermission(Long userId) {
      //  ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath*:config/spring/applicationContext*.xml");
        ISysUserRoleService sysUserRoleService =  SpringContextHolder.getBean("sysUserRoleService");//SpringContextHolder.getApplicationContext()
        ISysPermissionService sysPermissionService = SpringContextHolder.getBean("sysPermissionService");// SpringContextHolder.getApplicationContext()
        SysUserRole sysUserRole = sysUserRoleService.findByUserId(userId);
        persmissions = sysPermissionService.findListExcludeByRoleId(sysUserRole.getRoleId());
        return persmissions;
    }
    
    public  List<SysPermission> getPersmissions() {
        return persmissions;
    }

}