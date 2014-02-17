/*
 * @(#)CloudNodeMakeAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudcenter.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudcenter.service.AppCloudNodeService;
import com.ligitalsoft.cloudcenter.service.CloudNodeInfoService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudcenter.AppCloudNode;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.sysmanager.service.ISysDeptService;

/**
 * 
 * 云端节点定制
 * 
 * CloudNodeMakeAction.java
 * 
 * @author bianxj
 * @email  bianxiaojin@gmail.com
 * 2011-6-23
 * @description 
 * @see
 */
@Namespace("/cloudcenter/cloudNodeMake")
@Results({ @Result(name = StrutsAction.RELOAD, location = "cloudNodeMake!list.action?appId=${appId}", type = "redirect")
         })
@Action("cloudNodeMake") 
public class CloudNodeMakeAction extends FreemarkerBaseAction<AppCloudNode> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7517273541312285782L;


    @Resource
    private CloudNodeInfoService cloudNodeInfoService;
    @Resource
    private ISysDeptService sysDeptService;
    @Resource
    private AppMsgService appMsgService;
    @Resource
    private AppCloudNodeService appCloudNodeService;
    
    private List<AppMsg> appMsgs;
    private AppMsg appMsg;
    private List<CloudNodeInfo> cloudNodeInfos;
    private List<CloudNodeInfo> hasCloudNodeInfos;
    private Long appId;
    private Long[] nodeIds;
    private String nodeName;
    
    public String search() {
   		try {
			super.onBeforeList();
			appMsgs = appMsgService.findAllByProperty();
			
			if(null == appId && !appMsgs.isEmpty()){
				appMsg = appMsgs.get(0);
				appId = appMsg.getId();
			}else if(null != appId && !appMsgs.isEmpty()){
				appMsg = appMsgService.findById(appId);
			}
			cloudNodeInfos = cloudNodeInfoService.findAllByPage(nodeName);
			hasCloudNodeInfos = appCloudNodeService.findNodeInfoListByApp(appId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return LIST;
	}
    
    @Override
	protected void onBeforeList() {
		// TODO Auto-generated method stub
		try {
			super.onBeforeList();
			appMsgs = appMsgService.findAllByProperty();
			
			if(null == appId && !appMsgs.isEmpty()){
				appMsg = appMsgs.get(0);
				appId = appMsg.getId();
			}else if(null != appId && !appMsgs.isEmpty()){
				appMsg = appMsgService.findById(appId);
			}
			
			cloudNodeInfos = cloudNodeInfoService.findAll();
			hasCloudNodeInfos = appCloudNodeService.findNodeInfoListByApp(appId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	 
    @Override
	protected void onBeforeAdd() {
		// TODO Auto-generated method stub
		super.onBeforeAdd();
		appCloudNodeService.removeNodeInfoByApp(appId);
	}


	@Override
	public String add() {
    	 try {
    		 this.onBeforeAdd();
    		 if(null != nodeIds && null != appId){
	    		 for (Long nodeId : nodeIds) {
	    			 AppCloudNode acn = new AppCloudNode();
	    			 AppMsg app = new AppMsg();
	    			 CloudNodeInfo cni = new CloudNodeInfo();
	    			 app.setId(appId);
	    			 cni.setId(nodeId);
	    			 
	    			 acn.setAppMsg(app);
	    			 acn.setCloudNodeInfo(cni);
	    			 appCloudNodeService.insert(acn);
				 }
    		 }
             this.onAfterAdd();
             return RELOAD;
         } catch (Exception e) {
             this.errorInfo = "添加数据失败，请稍候再试!";
             log.error(errorInfo, e);
             return this.ERROR;
         }
	}
	
	public String getCloudNodeInfoListByAppMsg(){
		cloudNodeInfos = cloudNodeInfoService.findAll();
		hasCloudNodeInfos = appCloudNodeService.findNodeInfoListByApp(appId);
		return RELOAD;
	}

	@Override
    protected IBaseServices<AppCloudNode> getEntityService() {
        return appCloudNodeService;
    }

	public List<AppMsg> getAppMsgs() {
		return appMsgs;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long[] getNodeIds() {
		return nodeIds;
	}

	public void setNodeIds(Long[] nodeIds) {
		this.nodeIds = nodeIds;
	}


	public List<CloudNodeInfo> getCloudNodeInfos() {
		return cloudNodeInfos;
	}


	public List<CloudNodeInfo> getHasCloudNodeInfos() {
		return hasCloudNodeInfos;
	}


	public void setHasCloudNodeInfos(List<CloudNodeInfo> hasCloudNodeInfos) {
		this.hasCloudNodeInfos = hasCloudNodeInfos;
	}


	public AppMsg getAppMsg() {
		return appMsg;
	}


	public void setAppMsg(AppMsg appMsg) {
		this.appMsg = appMsg;
	}


	public String getNodeName() {
		return nodeName;
	}


	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	 
}
