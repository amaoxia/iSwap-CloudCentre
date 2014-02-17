/*
 * @(#)CloudNodeMgAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.ligitalsoft.cloudstorage.service.ICloudNodeMgService;
import com.ligitalsoft.model.cloudstorage.CloudNodeMg;

/**
 * 云存储节点的管理_ACTION
 * @author zhangx
 * @since Jun 15, 2011 12:39:45 AM
 * @name com.ligitalsoft.cloudstorage.action.CloudNodeMgAction.java
 * @version 1.0
 */
@Scope("prototype")
@Namespace("/cloudstorage/cloudNodeMg")
@Action("cloudNodeMg")
@Results( { @Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
                    @Result(name="listAction",location="cloudNodeMg!list.action",type="redirectAction")
})
public class CloudNodeMgAction extends FreemarkerBaseAction<CloudNodeMg> {

    /**
     * 
     */
    private static final long serialVersionUID = 3940027291905031794L;
    private ICloudNodeMgService cloudNodeMgService;

    /**
     * 删除实体数据
     * @author zhangx
     * @return
     */
    @SuppressWarnings("static-access")
    public String delete() {
        try {
            this.onBeforeDelete();
            this.getEntityService().deleteAllByIds(ids);
            this.onAfterDelete();
            return "listAction";
        } catch (Exception e) {
            this.errorInfo = "删除数据失败，有关联数据正在使用!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
    @Override
    protected IBaseServices<CloudNodeMg> getEntityService() {
        return cloudNodeMgService;
    }
    @Autowired
    public void setCloudNodeMgService(ICloudNodeMgService cloudNodeMgService) {
        this.cloudNodeMgService = cloudNodeMgService;
    }
}
