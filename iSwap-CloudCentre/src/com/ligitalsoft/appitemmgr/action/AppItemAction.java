/*
 * @(#)ItemAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.appitemmgr.action;

import java.util.List;

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
import com.ligitalsoft.appitemmgr.service.AppItemService;
import com.ligitalsoft.model.appitemmgr.AppItem;

/**
 * 
 * 指标库管理
 * @Company 中海纪元
 * @author  Administrator
 * @mail    moujunhu@163.com
 * @since   2013-12-11下午3:22:11
 * @name    ItemAction.java
 * @version iSwap V1.0
 * @Team    研发中心
 */
@Scope("prototype")
@Namespace("/appitemmgr/appItem")
@Action("appItem")
@Results({ @Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
        @Result(name = "listAction", location = "appItem!list.action", type = "redirectAction", params = {}) })
public class AppItemAction extends FreemarkerBaseAction<AppItem> {

    private static final long serialVersionUID = 5854980905054524517L;

    private AppItemService appItemService;
    
	@Override
	protected void onBeforeList() {
		QueryPara para = new QueryPara();
		para.setName("e.isDeleted");
		para.setType(Constants.TYPE_INTEGER);
		para.setOp(Constants.OP_EQ);
		para.setValue(com.ligitalsoft.util.Constant.ISNOTDELETED);
		queryParas.add(para);
		super.onBeforeList();
	}
	
	/*@Override
	public String list() {
		for(int i=1;i<=5;i++){
			Item changeItem = new Item();
			changeItem.setId(new Long(i));
			changeItem.setItemName("指标"+i);
			changeItem.setCreateDate(new Date());
			this.listDatas.add(changeItem);
		}
		return "list";
	}*/
	
	@Override
	public String delete() {
		super.delete();
		return "listAction";
	}



	/**
     * 检查指标名称唯一性
     * @return
     * @author zhangx
     */
    public String checkAppItemName() {
    	String id = getHttpServletRequest().getParameter("id");
        String appItemName = getHttpServletRequest().getParameter("appItemName").trim();
        String result = "";
        try {
            if (!StringUtils.isBlank(appItemName)) {
                entityobj = appItemService.findUniqueByProperty("appItemName",
                                java.net.URLDecoder.decode(appItemName, "UTF-8"));
            }
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
        } catch (Exception e) {
            log.error("userService invoke exception ", e);
        }
        return null;
    }
	
    /**
	 * Ajax获得指标列表信息
	 * 
	 * @return
	 * @author zhangx
	 */
	public String getAppItemList4Ajax() {
		List<AppItem> appItemList = appItemService.findAll();
		if (appItemList != null) {
			Struts2Utils.renderJson(appItemList, "encoding:GBK");
		}
		return null;
	}
    
	@Override
	public void onAfterAdd(){
		entityobj.setAppItemCode(entityobj.getId()+"");
		this.update();
	}
	
	@Override
	public String addView() {
		return "add";
	}

	@Override
	public String updateView() {
		return "update";
	}

	@Override
	protected IBaseServices<AppItem> getEntityService() {
		return appItemService;
	}

	@Autowired
	public void setAppItemService(AppItemService appItemService) {
		this.appItemService = appItemService;
	}

}
