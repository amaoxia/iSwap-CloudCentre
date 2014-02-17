/*
 * @(#)AppMsgAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.appitemmgr.action;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudcenter.service.AppCloudNodeService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudcenter.AppCloudNode;

/**
 * @author lifh
 * @mail wslfh2005@163.com
 * @since 2011-6-15 上午10:33:50
 * @name com.ligitalsoft.cloudcenter.action.AppMsgAction.java
 * @version 1.0
 */
@Namespace("/appitemmgr/appMsg")
@Results({
		@Result(name = StrutsAction.RELOAD, location = "appMsg!list.action", type = "redirect"),
		@Result(name = "refresh", location = "../saveResult.ftl", type = "freemarker") })
@Action("appMsg")
public class AppMsgAction extends FreemarkerBaseAction<AppMsg> {

	private static final long serialVersionUID = 1L;

	private AppMsgService appMsgService;
	private String type;// 查看标志位
	private int status;
	private AppCloudNodeService appCloudNodeService;

	public String add() {
		super.add();
		entityobj.setAppCode(entityobj.getId()+"");
		super.update();
		return "refresh";
	}

	public String update() {
		super.update();
		return "refresh";
	}

	/**
	 * Ajax获得应用列表信息
	 * 
	 * @return
	 * @author zhangx
	 */
	public String getAppMsgList4Ajax() {
		List<AppMsg> appMsgList = appMsgService.findAll();
		if (appMsgList != null) {
			Struts2Utils.renderJson(appMsgList, "encoding:GBK");
		}
		return null;
	}
	
	/**
	 * 生成应用树形页面
	 * 
	 * @return
	 * @author zhangx
	 */
	public String tree() {
		JSONArray array = null;
		if (!StringUtils.isBlank(type)) {
			if (type.equals("1")) {// appMsgService.getTree();
				array = appMsgService
						.getTree("../../exchange/share/share!listApp.action");
			}
			if (type.equals("2")) {
				array = appMsgService
						.getTree("../../exchange/share/share!listApply.action");
			}
			if (type.equals("3")) {
				array = appMsgService
						.getTree("../../cloudstorage/apply/apply!listApplyAuth.action");
			}
			if (type.equals("4")) {
				array = appMsgService
						.getTree("../../cloudstorage/dataAuth/dataAuth!listApply.action");
			}
			if (type.equals("5")) {
				array = appMsgService
						.getTree("../../cloudstorage/dataAuth/dataAuth!listApplyAuth.action");
			}
			if (type.equals("6")) {
				array = appMsgService
						.getTree("../../cloudstorage/apply/apply!serachAppList.action");
			}
		}
		if (array != null) {
			Struts2Utils.renderJson(array, "encoding:GBK");
		}

		return null;
	}


	/**
	 * 应用树页面
	 * 
	 * @return
	 * @author zhangx
	 */
	public String treeMain() {
		return "treeMain";
	}

	/**
	 * 修改应用状态
	 * @return
	 * @throws Exception
	 */
	
	public String changeStatus() throws Exception {
		if(status==0){
			List<AppCloudNode> appCloudNodes = appCloudNodeService
			.findListByAppId(id);
			if(appCloudNodes!=null&&appCloudNodes.size()>0){
				this.errorInfo="此应用正在被使用,暂不能禁用!";
				log.error(this.errorInfo);
				return this.ERROR;
			}
		}
		AppMsg appMsg = appMsgService.findById(id);
		appMsg.setStatus(status);
		appMsgService.saveOrUpdate(appMsg);
		return StrutsAction.RELOAD;
	}
	/**
	 * 检查角色编码是否重复
	 * 
	 * @return
	 * @author zhangx
	 */
	public String checkCode() {
		String result = "";
		String id = getStringParameter("id");
		String code = getStringParameter("appCode").trim();
		getHttpServletResponse().setCharacterEncoding("GBK");
		try {
			entityobj = appMsgService.findUniqueByProperty("appCode", code);
			if (entityobj == null) {
				result = "succ";
			} else {
				if (!StringUtils.isBlank(id)) {
					if (entityobj.getId().toString().equals(id)) {
						result = "succ";
					}
				}
			}
			Struts2Utils.renderText(result, "encoding:GBK");
		} catch (ServiceException e) {
			log.error("DeptAction exception", e);
		}
		return null;
	}
	
	@Override
	protected IBaseServices<AppMsg> getEntityService() {
		return appMsgService;
	}

	@Autowired
	public void setAppMsgService(AppMsgService appMsgService) {
		this.appMsgService = appMsgService;
	}

	@Autowired
	public void setAppCloudNodeService(AppCloudNodeService appCloudNodeService) {
		this.appCloudNodeService = appCloudNodeService;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
