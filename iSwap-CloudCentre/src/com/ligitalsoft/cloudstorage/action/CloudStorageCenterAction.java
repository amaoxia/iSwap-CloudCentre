/*
 * @(#)CloudStorageCenterAction.java
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
import com.ligitalsoft.cloudstorage.service.ICloudStorageCenterService;
import com.ligitalsoft.model.cloudstorage.CloudStorageCenter;

/**
 * 云存储中心的对象_ACTION
 * @author zhangx
 * @since Jun 15, 2011 12:52:07 AM
 * @name com.ligitalsoft.cloudstorage.action.CloudStorageCenterAction.java
 * @version 1.0
 */
@Scope("prototype")
@Namespace("/cloudstorage/cloudStorageCenter")
@Action("cloudStorageCenter")
@Results( { @Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
        @Result(name = "listAction", location = "cloudStorageCenter!list.action", type = "redirect") })
public class CloudStorageCenterAction extends FreemarkerBaseAction<CloudStorageCenter> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ICloudStorageCenterService cloudStorageCenterService;

    /**
     * 删除实体数据
     * @author huwanshan
     * @date 2010-12-8 下午01:34:01
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
    protected IBaseServices<CloudStorageCenter> getEntityService() {
        return cloudStorageCenterService;
    }
    @Autowired
    public void setCloudStorageCenterService(ICloudStorageCenterService cloudStorageCenterService) {
        this.cloudStorageCenterService = cloudStorageCenterService;
    }

}
