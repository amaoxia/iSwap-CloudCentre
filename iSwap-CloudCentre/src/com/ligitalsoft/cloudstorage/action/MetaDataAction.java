/*
 * @(#)MetaDataAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.dao.Constants;
import com.common.framework.dao.QueryPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudstorage.service.ICloudDataSourceService;
import com.ligitalsoft.cloudstorage.service.IMetaDataAppMsgService;
import com.ligitalsoft.cloudstorage.service.IMetaDataBasicTypeService;
import com.ligitalsoft.cloudstorage.service.IMetaDataService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudstorage.CouldDataSource;
import com.ligitalsoft.model.cloudstorage.MetaData;
import com.ligitalsoft.model.cloudstorage.MetaDataAppMsg;
import com.ligitalsoft.model.cloudstorage.MetaDataBasicType;

/**
 * 元数据的管理_ACTION
 * @author zhangx
 * @since Jun 16, 2011 8:47:55 PM
 * @name com.ligitalsoft.cloudstorage.action.MetaDataAction.java
 * @version 1.0
 */
@Namespace("/cloudstorage/metaData")
@Action("metaData")
@Scope("prototype")
@Results( {
        @Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
        @Result(name = "listAction", location = "metadata!list.action", type = "redirect", params = { "type", "${type}" ,"deptId", "${deptId}","deptName", "${deptName}"}),
        @Result(name = "listMetaAction", location = "metaData!listMeta.action", type = "redirect", params = { "type",
                "${type}" }) })
public class MetaDataAction extends FreemarkerBaseAction<MetaData> {

    /**
     * 
     */
    private static final long serialVersionUID = -703467407551868412L;
    // /////service
    private IMetaDataService metaDataService;
    private AppMsgService appMsgService;
    private IMetaDataAppMsgService metaDataAppMsgService;
    private IMetaDataBasicTypeService metaDataBasicTypeService;
    private ICloudDataSourceService cloudDataSourceService;
    // /////property
    private String type;// 元数据类型
    private String deptName;// 部门名称
    private List<CouldDataSource> dataSources = new ArrayList<CouldDataSource>();// 数据源
    private List<AppMsg> appMsgs = new ArrayList<AppMsg>();// 应用服务
    private List<MetaDataAppMsg> metaApp = new ArrayList<MetaDataAppMsg>();// 元数据应用服务
    private List<MetaDataBasicType> basics = new ArrayList<MetaDataBasicType>();// 基础库类型
    private Long deptId;//部门id
   
    
    /**
     * 添加之前
     */
    @Override
    protected void onBeforeAddView() {
        appMsgs = appMsgService.findAllByProperty();
        dataSources = cloudDataSourceService.findAll();
        basics = metaDataBasicTypeService.findAll();
    }
    /**
     * 修改之前
     */
    @Override
    protected void onBeforeUpdateView() {
        appMsgs = appMsgService.findAllByProperty();
        dataSources = cloudDataSourceService.findAll();
        basics = metaDataBasicTypeService.findAll();
        metaApp = metaDataAppMsgService.findListByMetaId(id);
    }
    /**
     * 根据元数据库类型显示列表
     * @throws UnsupportedEncodingException 
     */
    @Override
    protected void onBeforeList() {
        super.onBeforeList();
        /*if (!StringUtils.isBlank(deptName)) {
            QueryPara queryPara = new QueryPara();// 初始化查询参数
            queryPara.setName("e.sysDept.deptName");
            queryPara.setOp(Constants.OP_LIKE);
            queryPara.setType(Constants.TYPE_STRING);
            queryPara.setValue(deptName);
            queryParas.add(queryPara);
        }*/
        if(getDeptId()!=null){
			 QueryPara queryPara = new QueryPara();
           queryPara.setName("e.sysDept.id");
           queryPara.setOp(Constants.OP_EQ_VALUE);
           queryPara.setType(Constants.TYPE_LONG);
           queryPara.setValue(getDeptId()+"");
           queryParas.add(queryPara);
		}
        if (!StringUtils.isBlank(type)) {
            QueryPara queryPara = new QueryPara();// 初始化查询参数
            queryPara.setName("e.type");
            queryPara.setOp(Constants.OP_EQ);
            queryPara.setType(Constants.TYPE_STRING);
            queryPara.setValue(type);
            queryParas.add(queryPara);
        }
    }

    /**
     * 新增保存具体的实体
     * @date 2010-12-8 下午01:25:45
     * @return
     */
    @Override
    @SuppressWarnings("static-access")
    public String add() {
        onAfterAdd();
        try {
            if (validData(entityobj)) {// 验证业务逻辑数据
                this.onBeforeAdd();
                String[] appMgName = this.getParameter("appMgName");
                metaDataService.save(entityobj, appMgName);
                this.onAfterAdd();
            }
            return StrutsAction.RELOAD;
        } catch (Exception e) {
            this.errorInfo = "添加数据失败，请稍候再试!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
    /**
     * 修改保存具体的实体
     * @date 2010-12-8 下午01:25:54
     * @return
     */
    @Override
    @SuppressWarnings("static-access")
    public String update() {
        try {
            if (validData(entityobj)) {
                this.onBeforeUpdate();
                String[] appMgName = this.getParameter("appMgName");
                metaDataService.update(entityobj, appMgName);
                this.onAfterUpdate();
            }
            return RELOAD;
        } catch (Exception e) {
            this.errorInfo = "修改数据失败，请稍候再试!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }

    /**
     * 删除数据
     */
    @Override
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

    /**
     * 用户注册 检查邮箱是否唯一
     * @return
     * @author zhangx
     * @2010-12-30 下午08:10:24
     */
    public String checkEmail() {
        String result = "";
        String id = getHttpServletRequest().getParameter("id");
        String overPeopleEmail = getHttpServletRequest().getParameter("overPeopleEmail").trim();
        getHttpServletResponse().setCharacterEncoding("GBK");
        try {
            MetaData data = metaDataService.findUniqueByProperty("overPeopleEmail", overPeopleEmail);
            if (data == null) {
                result = "succ";
            } else {
                if (!StringUtils.isBlank(id)) {
                    if (data.getId().toString().equals(id)) {
                        result = "succ";
                    }
                }
            }
            Struts2Utils.renderText(result, "encoding:GBK");
        } catch (ServiceException e) {
            log.error("MetaData exception", e);
        }
        return null;
    }
    /**
     * 生成元数据树形页面
     * @return
     * @author zhangx
     */
    public String tree() {
        JSONArray array = null;
        if (!StringUtils.isBlank(type)) {
            if (type.equals("1")) {// 数据共享发布
                array = metaDataService.getTypeTree(null, "metadata!listMeta.action");
            }
            if (type.equals("2")) {// 数据查看申请
                array = metaDataService.getTypeTree(null, "metadata!listApply.action");
            }
            if (type.equals("3")) {// 数据查看授权
                array = metaDataService.getTypeTree(null, "../../cloudstorage/apply/apply!listMetaAuth.action");
            }
            if (type.equals("4")) {// 数据查看授权
                array = metaDataService.getTypeTree(null, "../../cloudstorage/dataAuth/dataAuth!listMeta.action");
            }
            if (type.equals("5")) {// 数据查看授权
                array = metaDataService.getTypeTree(null, "../../cloudstorage/dataAuth/dataAuth!listMetaAuth.action");
            }
            if (type.equals("6")) {// 数据查看授权
                array = metaDataService.getTypeTree(null, "../../cloudstorage/apply/apply!serachList.action");
            }
        }
        if (array != null) {
            Struts2Utils.renderJson(array, "encoding:GBK");
        }
        return null;
    }
    /**
     * 元数据树页面
     * @return
     * @author zhangx
     */
    public String treeMain() {

        return "treeMain";
    }
    /**
     * 根据应用查询指标
     * @return
     * @author zhangx
     */
    @SuppressWarnings("static-access")
    public String listApp() {
        try {
            this.onBeforeList();
            this.setQueryAndsort();// 设置查询条件
            // 分页查询
            this.listDatas = this.getAllObjectBypage();
            this.onAfterList();
            return "listApp";
        } catch (Exception e) {
            this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
    /**
     * 元数据类型指标
     * @return
     * @author zhangx
     */
    @SuppressWarnings("static-access")
    public String listMeta() {
    	if(!StringUtils.isBlank(deptName)){
    		  QueryPara queryPara = new QueryPara();// 初始化查询参数
              queryPara.setName("e.sysDept.deptName");
              queryPara.setOp(Constants.OP_LIKE);
              queryPara.setType(Constants.TYPE_STRING);
              queryPara.setValue(deptName);
              queryParas.add(queryPara);
    	}
        if (!StringUtils.isBlank(type)) {
            QueryPara queryPara = new QueryPara();// 初始化查询参数
            queryPara.setName("e.type");
            queryPara.setOp(Constants.OP_EQ);
            queryPara.setType(Constants.TYPE_STRING);
            queryPara.setValue(type);
            queryParas.add(queryPara);
        } else {
            QueryPara queryPara = new QueryPara();// 初始化查询参数
            queryPara.setName("e.type");
            queryPara.setOp(Constants.OP_NE);
            queryPara.setType(Constants.TYPE_STRING);
            queryPara.setValue("3");
            queryParas.add(queryPara);
        }
        try {
            this.onBeforeList();
            this.setQueryAndsort();// 设置查询条件
            // 分页查询
            this.listDatas = this.getAllObjectBypage();
            this.onAfterList();
            return "listMeta";
        } catch (Exception e) {
            this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }

    /**
     * 查询所有发布的指标 元数据类型指标
     * @return
     * @author zhangx
     */
    @SuppressWarnings("static-access")
    public String listApply() {
        if (!StringUtils.isBlank(type)) {
            QueryPara queryPara = new QueryPara();// 初始化查询参数
            queryPara.setName("e.type");
            queryPara.setOp(Constants.OP_EQ);
            queryPara.setType(Constants.TYPE_STRING);
            queryPara.setValue(type);
            queryParas.add(queryPara);
        } else {
            QueryPara queryPara = new QueryPara();// 初始化查询参数
            queryPara.setName("e.type");
            queryPara.setOp(Constants.OP_NE);
            queryPara.setType(Constants.TYPE_STRING);
            queryPara.setValue("3");// 基础库
            queryParas.add(queryPara);
        }
        QueryPara queryPara = new QueryPara();// 初始化查询参数
        queryPara.setName("e.shareState");
        queryPara.setOp(Constants.OP_EQ);
        queryPara.setType(Constants.TYPE_STRING);
        queryPara.setValue("1");
        queryParas.add(queryPara);
        try {
            this.onBeforeList();
            this.setQueryAndsort();// 设置查询条件
            // 分页查询
            this.listDatas = this.getAllObjectBypage();
            this.onAfterList();
            return "listApply";
        } catch (Exception e) {
            this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
    /**
     * 添加之前对对象的操作
     */
    @Override
    protected void onBeforeAdd() {
        validatorProperty();
    }

    /**
     * 修改之前的操作
     */
    @Override
    protected void onBeforeUpdate() {
        validatorProperty();
    }
    @Override
    protected void onBeforeView() {
        appMsgs = appMsgService.findAll();
        dataSources = cloudDataSourceService.findAll();
        basics = metaDataBasicTypeService.findAll();
        metaApp = metaDataAppMsgService.findListByMetaId(id);
    }
    /**
     * 验证实体属性
     * @author zhangx
     */
    private void validatorProperty() {
        if (entityobj.getCouldDataSource() == null || entityobj.getCouldDataSource().getId() == null) {
            entityobj.setCouldDataSource(null);
        }
        if (entityobj.getSysDept() == null || entityobj.getSysDept().getId() == null) {
            entityobj.setSysDept(null);
        }
    }
    /**
     * 设置发布状态
     * @return
     * @author zhangx
     */
    public String updateStatus() {
        String status = getStringParameter("status");
        if (!StringUtils.isBlank(status)) {
            metaDataService.updateStatus(ids, status);
        }
        return "listMetaAction";
    }
    // ///service Into
    @Override
    protected IBaseServices<MetaData> getEntityService() {
        return metaDataService;
    }

    @Autowired
    public void setMetaDataAppMsgService(IMetaDataAppMsgService metaDataAppMsgService) {
        this.metaDataAppMsgService = metaDataAppMsgService;
    }
    @Autowired
    public void setMetaDataService(IMetaDataService metaDataService) {
        this.metaDataService = metaDataService;
    }
    @Autowired
    public void setAppMsgService(AppMsgService appMsgService) {
        this.appMsgService = appMsgService;
    }

    @Autowired
    public void setMetaDataBasicTypeService(IMetaDataBasicTypeService metaDataBasicTypeService) {
        this.metaDataBasicTypeService = metaDataBasicTypeService;
    }
    
    @Autowired
    public void setCloudDataSourceService(
			ICloudDataSourceService cloudDataSourceService) {
		this.cloudDataSourceService = cloudDataSourceService;
	}
    // start property

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public List<CouldDataSource> getDataSources() {
        return dataSources;
    }

    public List<AppMsg> getAppMsgs() {
        return appMsgs;
    }

    public List<MetaDataAppMsg> getMetaApp() {
        return metaApp;
    }

    public void setMetaApp(List<MetaDataAppMsg> metaApp) {
        this.metaApp = metaApp;
    }

    public List<MetaDataBasicType> getBasics() {
        return basics;
    }
    // end property
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

}
