/*
 * @(#)LoginInStrutsFilter.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import com.ligitalsoft.model.system.SysPermission;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.sysmanager.util.Costant;

/**
 * 权限验证
 * @author zhangx
 * @since May 28, 2011 11:05:03 PM
 * @name com.ligitalsoft.sysmanager.filter.LoginInStrutsFilter.java
 * @version 1.0
 */
public class LoginInStrutsFilter extends StrutsPrepareAndExecuteFilter {

    private final static String LINE = "/";
    private final static String MARK = "?";
    private final static String[] ACCESS = { "loginView.action", "toLogin.jsp", "login.ftl", "login.action" };

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
                    ServletException {
        HttpServletRequest reqeust = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = reqeust.getSession();
        String root = reqeust.getContextPath();
        String path = reqeust.getRequestURI();
        SysUser sysUser = (SysUser) session.getAttribute(Costant.SESSION_USER);
        String execPath = root;// 跳转到登陆页面路径
        if (sysUser == null) {
            if (!subStrPath(path)) {
                execPath += "/toLogin.jsp";
                response.sendRedirect(execPath);
                return;
            }
        }
        super.doFilter(arg0, arg1, arg2);
    }
    /**
     * 截取请求部分路径 *
     * @param path
     * @return
     * @author zhangx
     */
    public static boolean subStrPath(String path) {
        boolean falg = false;
        String subPath = path;
        int start = path.lastIndexOf(LINE);
        if (start > 0) {
            subPath = path.substring(start + 1, path.length());
        }
        int len = subPath.indexOf(MARK);
        if (len > 0) {
            subPath = subPath.substring(0, len).toString();
        }
        for (String url : ACCESS) {
            if (url.equals(subPath)) {
                falg = true;
                break;
            }
        }
        return falg;
    }
}
