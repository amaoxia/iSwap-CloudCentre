/*
 * @(#)SysEmailConfigAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.action;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.model.system.SysEmailConfig;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.sysmanager.service.ISysEmailConfigServaice;
import com.ligitalsoft.sysmanager.util.Costant;


/**
 * 系统邮件_ACTION
 * @author  zhangx
 * @since   Aug 3, 2011 5:52:13 PM
 * @name    com.ligitalsoft.sysmanager.action.SysEmailConfigAction.java
 * @version 1.0
 */
@Namespace("/sysmanager/email")
@Scope("prototype")
@Action("email")
@Results( { @Result(name = "listAction", location = "email!list.action", type = "redirectAction"),
        @Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker") })
public class SysEmailConfigAction extends FreemarkerBaseAction<SysEmailConfig> {


    /**
     * 
     */
    private static final long serialVersionUID = 8894498059413538323L;

    private ISysEmailConfigServaice sysEmailConfigServaice;
    
    @Override
    protected void onBeforeAdd() {
        SysUser sysUser = (SysUser) getHttpServletRequest().getSession().getAttribute(Costant.SESSION_USER);
       entityobj.setCreator(sysUser.getUserName());
    }
    
    @Override
    @SuppressWarnings("static-access")
    public String delete() {
        try {
            if(ids!=null&&ids.length>0){
                sysEmailConfigServaice.deleteAllByIds(ids);
            }
        } catch (ServiceException e) {
            this.errorInfo = "有关数据正在使用,删除数据失败!";
            return this.ERROR;
        }
        return "listAction";
    }
    /**
     *  检查邮箱账户是否唯一
     * @return
     * @author zhangx
     * @2010-12-30 下午08:10:24
     */
    public String checkEmail() {
        String result = "";
        String id = getHttpServletRequest().getParameter("id");
        String emailAccount = getHttpServletRequest().getParameter("emailAccount").trim();
        getHttpServletResponse().setCharacterEncoding("GBK");
        try {
            SysEmailConfig emailConfig = sysEmailConfigServaice.findUniqueByProperty("emailAccount", emailAccount);
            if (emailConfig == null) {
                result = "succ";
            } else {
                if (!StringUtils.isBlank(id)) {
                    if (emailConfig.getId().toString().equals(id)) {
                        result = "succ";
                    }
                }
            }
            Struts2Utils.renderText(result, "encoding:GBK");
        } catch (ServiceException e) {
            log.error("UserAction exception", e);
        }
        return null;
    }
    
    @Override
    protected IBaseServices<SysEmailConfig> getEntityService() {
        return sysEmailConfigServaice;
    }
    @Autowired
    public void setSysEmailConfigServaice(ISysEmailConfigServaice sysEmailConfigServaice) {
        this.sysEmailConfigServaice = sysEmailConfigServaice;
    }
}

