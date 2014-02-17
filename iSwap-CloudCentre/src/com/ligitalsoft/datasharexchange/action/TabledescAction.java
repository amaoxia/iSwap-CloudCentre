/*
 * @(#)TabledescAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ligitalsoft.datasharexchange.service.IChangeTableDescService;
import com.ligitalsoft.model.changemanage.ChangeTableDesc;

/**
 * 交换_指标表结构
 * 
 * @author zhangx
 * @since Jun 14, 2011 5:39:41 PM
 * @name com.ligitalsoft.datasharexchange.action.TabledescAction.java
 * @version 1.0
 */
@Scope("prototype")
@Namespace("/exchange/tabledesc")
@Results({
		@Result(name = StrutsAction.RELOAD, location = "tabledesc!list.action", type = "redirectAction", params = {
				"itemId", "${itemId}" }),
		@Result(name = "reloadList", location = "succ.ftl", type = "freemarker") })
public class TabledescAction extends FreemarkerBaseAction<ChangeTableDesc> {

	/**
     * 
     */
	private static final long serialVersionUID = 186626217425969480L;
	private IChangeTableDescService changeTableDescService;
	private Long itemId;// 指标ID
	private String status;// 是否显示 1,0
	private String itemName;//

	public String addDesc() {
		try {
			changeTableDescService.createTableDescData(itemId);
		} catch (SQLException e) {
			this.errorInfo = "读取数据失败,请联系管理员!";
			log.error(this.errorInfo, e);
			return ERROR;
		}
		return StrutsAction.RELOAD;
	}

	public String listView(){
		this.list();
		return "listView";
	}
	
	public String getTableDesc4Ajax(){
		String returnStr = "";
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			changeTableDescService.createTableDescData(itemId);
			List<ChangeTableDesc> changeTableDescList = changeTableDescService.getTableDescByitemId(itemId);
			List<String> attributes = new ArrayList<String>();
			attributes.add("changeItem");
			
			map.put("success", true);
			map.put("data", changeTableDescList);
			returnStr += JsonHelper.toJsonString(map,null,true,attributes);
		} catch (Exception e) {
			map.put("success", false);
			returnStr += JsonHelper.toJsonString(map);
		}
		Struts2Utils.renderJson(returnStr, "encoding:GBK");
		return null;
	}
	
	/**
	 * 添加操作
	 */
	@Override
	@SuppressWarnings("static-access")
	public String update() {
		try {
			changeTableDescService.update(entityobj);
		} catch (Exception e) {
			this.errorInfo = "修改数据失败，请稍候再试!";
			log.error(errorInfo, e);
			return this.ERROR;
		}
		return "reloadList";
	}

	/**
	 * 删除实体数据
	 */
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

	/**
	 * 设置表字段状态
	 * 
	 * @return
	 * @author zhangx
	 */
	public String updateStatus() {
		changeTableDescService.updateStatus(ids, status);
		return StrutsAction.RELOAD;
	}

	// 列表之前操作 手动设置查询参数
	@Override
	protected void onBeforeList() {
		super.onBeforeList();
		if (itemId != null && itemId != 0L) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("e.changeItem.id");
			queryPara.setOp(Constants.OP_EQ);
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(itemId + "");
			queryParas.add(queryPara);
		}
		if (!StringUtils.isBlank(itemName)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("e.changeItem.itemName");
			queryPara.setOp(Constants.OP_LIKE);
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(itemName);
			queryParas.add(queryPara);
		}
		getHttpServletRequest().setAttribute("view", getHttpServletRequest().getParameter("view"));
	}

	@Override
	protected IBaseServices<ChangeTableDesc> getEntityService() {
		return changeTableDescService;
	}

	// start service
	@Autowired
	public void setChangeTableDescService(
			IChangeTableDescService changeTableDescService) {
		this.changeTableDescService = changeTableDescService;
	}

	// end service

	// start property
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	// end property
}
