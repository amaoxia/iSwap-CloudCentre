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

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.ligitalsoft.iswapqa.plugin.custom.UserInfo;
import com.ligitalsoft.iswapqa.service.IRuleFileService;
import com.ligitalsoft.iswapqa.service.IRuleService;
import com.ligitalsoft.model.iswapqa.RuleFile;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.service.ISysDeptService;

/**
 * 规则文件Action
 * @author daic
 * @since 2011-07-21 17:16:01
 * @name com.ligitalsoft.iswapqa.action.RuleFileAction.java
 * @version 1.0
 */
@Namespace("/iswapqa/rule")
@Results({
        @Result(name = StrutsAction.LIST, location = "list.ftl", type = "freemarker"),
        @Result(name = "testShow", location = "testShow.ftl", type = "freemarker"),
        @Result(name = StrutsAction.RELOAD, location = "ruleFileAction!list.action", type = "redirect"),
        @Result(name = StrutsAction.VIEW, location = "view.ftl", type = "freemarker"),
        @Result(name = StrutsAction.UPDATEVIEW, location = "dept!view.action", type = "redirect", params = { "id",
                "${id}", "flag", "${flag}" }) })
@Action("ruleFileAction")
public class RuleFileAction extends FreemarkerBaseAction<RuleFile> {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private IRuleFileService  ruleFileService;
    private IRuleService      ruleService;
    private ISysDeptService   sysDeptService;
    private List<SysDept>     listDepts        = new ArrayList<SysDept>();
    private String            fileName;
    private String            ruleFileId;                                 // 规则文件Id
    private String            testStr;

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }
    public String getTestStr() {
        return testStr;
    }
    @Override
    public String delete() {
        try {
            ruleFileService.deleteAllByIds(ids);
        }
        catch (ServiceException e) {
            e.printStackTrace();
        }
        return RELOAD;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected IBaseServices<RuleFile> getEntityService() {
        return ruleFileService;
    }

    public IRuleFileService getRuleFileService() {
        return ruleFileService;
    }

    public void setRuleFileService(IRuleFileService ruleFileService) {
        this.ruleFileService = ruleFileService;
    }

    public IRuleService getRuleService() {
        return ruleService;
    }

    @Autowired
    public void setRuleService(IRuleService ruleService) {
        this.ruleService = ruleService;
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

    @Autowired
    public void setExtService(IRuleFileService extService) {
        this.ruleFileService = extService;
    }

    public void setRuleFileId(String ruleFileId) {
        this.ruleFileId = ruleFileId;
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

    public String test() {

        UserInfo user = new UserInfo();

        user.setAge(11);
        user.setGender("男");
        user.setName("顶替");

        ruleService.execute(ruleFileId, user);

        return StrutsAction.LIST;
    }

}
