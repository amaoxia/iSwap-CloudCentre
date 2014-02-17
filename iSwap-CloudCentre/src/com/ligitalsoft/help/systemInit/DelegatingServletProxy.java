package com.ligitalsoft.help.systemInit;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.common.framework.help.SpringContextHolder;

/**
 * 对servlet进行代理注入到spring
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-6 下午07:25:45
 *@Team 研发中心
 */
public class DelegatingServletProxy extends GenericServlet {
	   
	private static final long serialVersionUID = 541830859717605107L;
	private String targetBean;
    private Servlet proxy;

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        proxy.service(req, res);
    }

    @Override
    public void init() throws ServletException {
        this.targetBean = getServletName();
        getServletBean();
        proxy.init(getServletConfig());
    }

    private void getServletBean() {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        SpringContextHolder contextHolder=new SpringContextHolder();
        contextHolder.setApplicationContext(wac);
        this.proxy = (Servlet) wac.getBean(targetBean);
    }

}
