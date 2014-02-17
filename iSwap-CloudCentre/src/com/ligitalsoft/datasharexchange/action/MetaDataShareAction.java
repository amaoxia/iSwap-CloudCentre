/*
\ * @(#)MetaDataShareAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.action;

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
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.cloudstorage.service.IMetaDataAppMsgService;
import com.ligitalsoft.cloudstorage.service.IMetaDataService;
import com.ligitalsoft.cloudstorage.service.ITableInfoService;
import com.ligitalsoft.model.cloudstorage.MetaData;
import com.ligitalsoft.model.cloudstorage.MetaDataAppMsg;

/**
 * 数据共享发布_ACTION
 * 
 * @author zhangx
 * @since Aug 9, 2011 2:15:05 PM
 * @name com.ligitalsoft.datasharexchange.action.MetaDataShareAction.java
 * @version 1.0
 */
@Scope("prototype")
@Namespace("/exchange/share")
@Results({
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
		@Result(name = "listAppAction", location = "share!listApp.action", type = "redirectAction", params = {
				"appId", "${appId}" }) })
@Action("share")
public class MetaDataShareAction extends FreemarkerBaseAction<MetaData> {

	/**
     * 
     */
	private static final long serialVersionUID = -683473469782698610L;
	private IMetaDataService metaDataService;
	private ITableInfoService tableInfoService;
	private IMetaDataAppMsgService metaDataAppMsgService;
	private List<MetaDataAppMsg> dataApp = new ArrayList<MetaDataAppMsg>();
	private Long appId;// 应用Id
	private String type;
	private String deptName;

	/**
	 * 生成元数据树形页面
	 * 
	 * @return
	 * @author zhangx
	 */
	public String tree() {
		JSONArray array = null;
		if (!StringUtils.isBlank(type)) {
			if (type.equals("1")) {// 数据共享发布
				array = metaDataService.getTypeTree(null,
						"../../exchange/share/share!listApp.action");
			}
			if (type.equals("2")) {// 数据查看申请
				array = metaDataService.getTypeTree(null,
						"../../exchange/share/share!listApply.action");
			}
			if (type.equals("3")) {// 数据查看授权
				array = metaDataService.getTypeTree(null,
						"../../cloudstorage/apply/apply!listApplyAuth.action");
			}
		}
		if (array != null) {
			Struts2Utils.renderJson(array, "encoding:GBK");
		}

		return null;
	}

	/**
	 * 元数据树页面
	 * 
	 * @return
	 * @author zhangx
	 */
	public String treeMain() {
		return "treeMain";
	}

	public String main() {
		return "main";
	}

	/**
	 * 以应用查询指标
	 * 
	 * @return
	 * @author zhangx
	 */
	@SuppressWarnings("static-access")
	public String listApp() {
		if (appId != null && appId != 0L) {
			QueryPara para = new QueryPara();
			para.setName("e.appMsg.id");
			para.setOp(Constants.OP_EQ);
			para.setType(Constants.TYPE_LONG);
			para.setValue(appId.toString());
			queryParas.add(para);
			this.onBeforeList();
			this.setQueryAndsort();// 设置查询条件
		}
		try {
			dataApp = metaDataAppMsgService.findAllByPage(
					"from MetaDataAppMsg e", queryParas, sortParas, page);
		} catch (Exception e) {
			this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
			log.error(errorInfo, e);
			return this.ERROR;
		}
		return "listApp";
	}

	/**
	 * 申请人
	 * 
	 * @return
	 * @author zhangx
	 */
	@SuppressWarnings("static-access")
	public String listApply() {

		if (appId != null && appId != 0L) {
			QueryPara para = new QueryPara();
			para.setName("e.appMsg.id");
			para.setOp(Constants.OP_EQ);
			para.setType(Constants.TYPE_LONG);
			para.setValue(appId.toString());
			queryParas.add(para);
		}
		if (!StringUtils.isBlank(deptName)) {
			QueryPara para = new QueryPara();
			para.setName("e.metaData.sysDept.deptName");
			para.setOp(Constants.OP_LIKE);
			para.setType(Constants.TYPE_STRING);
			para.setValue(deptName);
			queryParas.add(para);
		}
		QueryPara paras = new QueryPara();
		paras.setName("e.shareState");
		paras.setOp(Constants.OP_EQ);
		paras.setType(Constants.TYPE_STRING);
		paras.setValue("1");// 已经发布指标
		queryParas.add(paras);
		this.onBeforeList();
		this.setQueryAndsort();// 设置查询条件
		try {
			dataApp = metaDataAppMsgService.findAllByPage(
					"from MetaDataAppMsg e", queryParas, sortParas, page);
		} catch (Exception e) {
			this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
			log.error(errorInfo, e);
			return this.ERROR;
		}
		return "listApply";
	}

	/**
	 * 
	 * @return
	 * @author zhangx
	 */
	public String listAuth() {
		return "listAuth";
	}

	/**
	 * 修改指标状态
	 * 
	 * @return
	 * @author zhangx
	 */
	public String updateStatus() {
		String status = getStringParameter("status");
		if (!StringUtils.isBlank(status)) {
			metaDataAppMsgService.updateStatus(ids, status);
		}
		return "listAppAction";
	}

	@Autowired
	public void setTableInfoService(ITableInfoService tableInfoService) {
		this.tableInfoService = tableInfoService;
	}

	@Autowired
	public void setMetaDataService(IMetaDataService metaDataService) {
		this.metaDataService = metaDataService;
	}

	@Override
	protected IBaseServices<MetaData> getEntityService() {
		return metaDataService;
	}

	@Autowired
	public void setMetaDataAppMsgService(
			IMetaDataAppMsgService metaDataAppMsgService) {
		this.metaDataAppMsgService = metaDataAppMsgService;
	}

	public List<MetaDataAppMsg> getDataApp() {
		return dataApp;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

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

}
