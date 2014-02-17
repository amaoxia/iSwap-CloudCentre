/*
 * @(#)RuleAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.iswapqa.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.ligitalsoft.iswapqa.service.IAuditResultsService;
import com.ligitalsoft.model.iswapqa.AuditResults;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.service.ISysDeptService;

/**
 * 审计结果 Action
 * @author daic
 * @since 2011-07-27 10:13:46
 * @name com.ligitalsoft.iswapqa.action.AuditResultsAction.java
 * @version 1.0
 */
@Namespace("/iswapqa/auditResults")
@Results({
	    @Result(name = StrutsAction.LIST, location = "list.ftl", type = "freemarker"),
        @Result(name = StrutsAction.UPDATEVIEW, location = "dept!view.action", type = "redirect", params = { "id",
                "${id}", "flag", "${flag}" }) })
@Action("auditResultsAction")
public class AuditResultsAction extends FreemarkerBaseAction<AuditResults> {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private IAuditResultsService  auditResultsService;
    private ISysDeptService   sysDeptService;
    private List<SysDept>     listDepts        = new ArrayList<SysDept>();
    @Override
    protected IBaseServices<AuditResults> getEntityService() {
        return auditResultsService;
    }
    @Autowired
    public void setExtService(IAuditResultsService extService) {
        this.auditResultsService = extService;
    }
    @Autowired
    public void setSysDeptService(ISysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }
    public List<SysDept> getListDepts() {
        return listDepts;
    }
    public void setListDepts(List<SysDept> listDepts) {
        this.listDepts = listDepts;
    }

    @SuppressWarnings("static-access")
	@Override
    public String list() {
        try {
            this.onBeforeList();
            this.setQueryAndsort();// 设置查询条件
            // 分页查询
            this.listDatas = this.getAllObjectBypage();
            this.listDepts = sysDeptService.findAll();
            this.onAfterList();
            return this.LIST;
        }
        catch (Exception e) {
            this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
}
