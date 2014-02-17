/*
 * @(#)TableinfoAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

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
import com.common.utils.json.JsonHelper;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.cloudstorage.service.IMetaDataTableAuthService;
import com.ligitalsoft.cloudstorage.service.ITableInfoService;
import com.ligitalsoft.model.changemanage.ChangeTableDesc;
import com.ligitalsoft.model.cloudstorage.TableInfo;

/**
 * 共享指标项的信息ACTION
 * 
 * @author zhangx
 * @since Jun 16, 2011 8:46:00 PM
 * @name com.ligitalsoft.cloudstorage.action.TableinfoAction.java
 * @version 1.0
 */
@Scope("prototype")
@Namespace("/cloudstorage/tableinfo")
@Results({
		@Result(name = StrutsAction.RELOAD, location = "tableinfo!list.action", type = "redirect", params = {
				"type", "${type}", "metaDataId", "${metaDataId}" }),
		@Result(name = "succ", location = "succ.ftl", type = "freemarker") })
public class TableinfoAction extends FreemarkerBaseAction<TableInfo> {

	/**
     * 
     */
	private static final long serialVersionUID = -8116586456620936289L;
	private ITableInfoService tableInfoService;
	private IMetaDataTableAuthService metaDataTableAuthService;// 申请service
	private Long metaDataId;// 指标ID
	private String itemName;
	private String tids;// 申请字段集合
	private Long deptId;// 当前部门ID
	private Long appId;// 应用ID
	private Long applyId;// 申请项Id
	private String view;//是否查看

	@Override
	protected void onBeforeList() {
		if (metaDataId != null && metaDataId != 0L) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("e.metaData.id");
			queryPara.setOp(Constants.OP_EQ);
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(metaDataId.toString());
			queryParas.add(queryPara);
		}
		if (!StringUtils.isBlank(itemName)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("e.metaData.targetName");
			queryPara.setOp(Constants.OP_LIKE);
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(itemName.toString());
			queryParas.add(queryPara);
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
			return RELOAD;
		} catch (Exception e) {
			this.errorInfo = "删除数据失败，有关联数据正在使用!";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	@Override
	public String update() {
		super.update();
		metaDataId = entityobj.getMetaData().getId();
		return "succ";
	}

	/**
	 * 添加之前的操作
	 */
	@SuppressWarnings("static-access")
	public String addDesc() {
		try {
			tableInfoService.createTableDescData(metaDataId);
		} catch (SQLException e) {
			this.errorInfo = "数据源连接出错,请检查信息或者通信情况!";
			return this.ERROR;
		}
		return StrutsAction.RELOAD;
	}

	/**
	 * 树形字段
	 * 
	 * @return
	 * @author zhangx
	 */
	public String tree() {
		JSONArray array = null;
		if (metaDataId != null && metaDataId != 0L && deptId != null && deptId != 0L) {
			array = tableInfoService.getTree(metaDataId, deptId, appId);
		}
		if (array != null) {
			Struts2Utils.renderJson(array, "encoding:GBK");
		}
		return null;
	}

	/**
	 * 树形字段
	 * 
	 * @return
	 * @author zhangx
	 */
	public String dataTree() {
		JSONArray array = null;
		if (metaDataId != null && metaDataId != 0L && deptId != null && deptId != 0L) {
			array = tableInfoService.getAuthTree(applyId, metaDataId, deptId,
					appId, "0");// 已申请数据项
		}
		if (array != null) {
			Struts2Utils.renderJson(array, "encoding:GBK");
		}
		return null;
	}

	/**
	 * 树形页面
	 * 
	 * @return
	 * @author zhangx
	 */
	public String treeMain() {
		return "treeMain";
	}

	/**
	 * 数据申请树
	 * 
	 * @return
	 * @author zhangx
	 */
	public String treeDataMain() {
		return "treeDataMain";
	}
	
	public String getTableInfo4Ajax(){
		String returnStr = "";
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			tableInfoService.createTableDescData(metaDataId);
			List<TableInfo> changeTableInfoList = tableInfoService.getTableInfoByMetaDataId(metaDataId);
			List<String> attributes = new ArrayList<String>();
			attributes.add("metaData");
			
			map.put("success", true);
			map.put("data", changeTableInfoList);
			returnStr += JsonHelper.toJsonString(map,null,true,attributes);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			returnStr += JsonHelper.toJsonString(map);
		}
		Struts2Utils.renderJson(returnStr, "encoding:GBK");
		return null;
	}

	@Override
	protected IBaseServices<TableInfo> getEntityService() {
		return tableInfoService;
	}

	@Autowired
	public void setTableInfoService(ITableInfoService tableInfoService) {
		this.tableInfoService = tableInfoService;
	}

	@Autowired
	public void setMetaDataTableAuthService(
			IMetaDataTableAuthService metaDataTableAuthService) {
		this.metaDataTableAuthService = metaDataTableAuthService;
	}

	// start property
	public Long getMetaDataId() {
		return metaDataId;
	}

	public void setMetaDataId(Long metaDataId) {
		this.metaDataId = metaDataId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getTids() {
		return tids;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	// end property
}
