/*
 * @(#)MetaDataBasicTypeAction.java
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

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.cloudstorage.service.IMetaDataBasicTypeService;
import com.ligitalsoft.model.cloudstorage.MetaDataBasicType;

/**
 * 元数据库类型_基础库类型_ACTION
 * @author zhangx
 * @since Jul 19, 2011 5:35:13 PM
 * @name com.ligitalsoft.cloudstorage.action.MetaDataBasicTypeAction.java
 * @version 1.0
 */
@Namespace("/cloudstorage/basicType")
@Action("basicType")
@Scope("prototype")
@Results( { @Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
                     @Result(name="listAction",location="basicType!list.action",type="redirectAction")
})
public class MetaDataBasicTypeAction extends FreemarkerBaseAction<MetaDataBasicType> {

    /**
     * 
     */
    private static final long serialVersionUID = 8900012500301995369L;
    private IMetaDataBasicTypeService metaDataBasicTypeService;

    /**
     * 检查
     * @return
     * @author zhangx
     * @2010-12-30 下午08:10:24
     */
    public String checkBasicTypeCode() {
        String result = "";
        String id = getHttpServletRequest().getParameter("id");
        String basicTypeCode = getHttpServletRequest().getParameter("basicTypeCode").trim();
        getHttpServletResponse().setCharacterEncoding("GBK");
        try {
            MetaDataBasicType metaDataBasicType = metaDataBasicTypeService.findUniqueByProperty("basicTypeCode", basicTypeCode);
            if (metaDataBasicType == null) {
                result = "succ";
            } else {
                if (!StringUtils.isBlank(id)) {
                    if (metaDataBasicType.getId().toString().equals(id)) {
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
    protected IBaseServices<MetaDataBasicType> getEntityService() {
        return metaDataBasicTypeService;
    }
    @Autowired
    public void setMetaDataBasicTypeService(IMetaDataBasicTypeService metaDataBasicTypeService) {
        this.metaDataBasicTypeService = metaDataBasicTypeService;
    }

}
