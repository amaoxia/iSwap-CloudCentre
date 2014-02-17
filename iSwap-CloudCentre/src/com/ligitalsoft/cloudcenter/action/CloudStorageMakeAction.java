/*
 * @(#)CloudStorageMakeAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudcenter.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudcenter.service.AppCloudNodeService;
import com.ligitalsoft.cloudcenter.service.CloudStorageMakeService;
import com.ligitalsoft.cloudstorage.service.ICloudNodeMgService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudcenter.CloudStorageMake;
import com.ligitalsoft.model.cloudstorage.CloudNodeMg;
import com.ligitalsoft.sysmanager.service.ISysDeptService;

/**
 * 
 * 云存储空间定制
 * 
 * CloudStorageMakeAction.java
 * 
 * @author bianxj
 * @email  bianxiaojin@gmail.com
 * 2011-6-27
 * @description 
 * @see
 */
@Namespace("/cloudcenter/cloudStorageMake")
@Results({ @Result(name = StrutsAction.RELOAD, location = "cloudStorageMake!list.action", type = "redirect"),
		   @Result(name = "refresh", location = "../saveResult.ftl", type = "freemarker")
         })
@Action("cloudStorageMake") 
public class CloudStorageMakeAction extends FreemarkerBaseAction<CloudStorageMake> {

    /**
     * LOG used to output the Log info..
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private CloudStorageMakeService cloudStorageMakeService;
    @Resource
    private ICloudNodeMgService cloudNodeMgService;
    @Resource
    private ISysDeptService sysDeptService;
    @Resource
    private AppMsgService appMsgService;
    @Resource
    private AppCloudNodeService appCloudNodeService;
    
    private List<AppMsg> appMsgs;
    
    private String storageName;
    private Long appId;
    private Double preStorageCount;
    
    public String list() {
        try {
            this.onBeforeList();
            // 分页查询
            this.listDatas = this.cloudStorageMakeService.findAllByPage(appId, storageName, page);
            this.onAfterList();
            return this.LIST;
        } catch (Exception e) {
            this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
    
    
    
    @Override
	protected void onBeforeView() {
		// TODO Auto-generated method stub
		super.onBeforeView();
		appMsgs = appMsgService.findAll();
	}



	@Override
	protected void onBeforeList() {
		// TODO Auto-generated method stub
		super.onBeforeList();
		appMsgs = appMsgService.findAll();
	}

	@Override
	protected void onBeforeUpdateView() {
		// TODO Auto-generated method stub
		super.onBeforeUpdateView();
		appMsgs = appMsgService.findAllByProperty();
	}
    
	public String add() {
        super.add();
        return "refresh";
    }
    public String update() {
        super.update();
        return "refresh";
    }
    
	@Override
	protected void onBeforeAdd() {
		// TODO Auto-generated method stub
		super.onBeforeAdd();
		getModel().setCreateDate(new Date());
	}
	
	protected void calPreStorageCount() {
		List<CloudNodeMg> CloudNodeMgList = cloudNodeMgService.findAll();
		Double tempCount = 0d;
		for (CloudNodeMg cloudNodeMg : CloudNodeMgList) {
			try {
				tempCount += Double.valueOf(cloudNodeMg.getStorageCount());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		preStorageCount = tempCount * 0.2;
	}

	@Override
	protected void onBeforeAddView() {
		// TODO Auto-generated method stub
		super.onBeforeAddView();
		appMsgs = appMsgService.findAllByProperty();
		calPreStorageCount();
	}

	/* (non-Javadoc)
	 * @see com.common.framework.view.BaseAction#getEntityService()
	 */
	@Override
	protected IBaseServices<CloudStorageMake> getEntityService() {
		// TODO Auto-generated method stub
		return cloudStorageMakeService;
	}

	public List<AppMsg> getAppMsgs() {
		return appMsgs;
	}

	public void setAppMsgs(List<AppMsg> appMsgs) {
		this.appMsgs = appMsgs;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}



	public Double getPreStorageCount() {
		return preStorageCount;
	}



	public void setPreStorageCount(Double preStorageCount) {
		this.preStorageCount = preStorageCount;
	}


   
     
}
