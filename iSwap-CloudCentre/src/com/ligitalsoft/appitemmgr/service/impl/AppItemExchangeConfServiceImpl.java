/*
 * @(#)AppMsgServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.appitemmgr.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.appitemmgr.dao.AppItemExchangeConfDao;
import com.ligitalsoft.appitemmgr.dao.AppItemExchangeConfDetailsDao;
import com.ligitalsoft.appitemmgr.service.AppItemExchangeConfService;
import com.ligitalsoft.datasharexchange.dao.ChangeItemDao;
import com.ligitalsoft.model.appitemmgr.AppItemExchangeConf;
import com.ligitalsoft.model.appitemmgr.AppItemExchangeConfDetails;
import com.ligitalsoft.model.changemanage.ChangeItem;

/**
 * @author lifh
 * @mail wslfh2005@163.com
 * @since 2011-6-15 上午10:31:25
 * @name com.ligitalsoft.cloudcenter.service.impl.AppMsgServiceImpl.java
 * @version 1.0
 */
@Service("appItemExchangeConfService")
public class AppItemExchangeConfServiceImpl extends BaseSericesImpl<AppItemExchangeConf> implements AppItemExchangeConfService {

	private AppItemExchangeConfDao appItemExchangeConfDao;
    
    private AppItemExchangeConfDetailsDao appItemExchangeConfDetailsDao;
    
    private ChangeItemDao changeItemDao;

    @SuppressWarnings("unchecked")
    public List<AppItemExchangeConf> findAllByProperty() {
        String hql = "from AppItemExchangeConf where isDeleted =" + com.ligitalsoft.util.Constant.ISNOTDELETED;
        return appItemExchangeConfDao.findListByHql(hql);
    }
    
    @Override
	public List<AppItemExchangeConf> addEntitys(
			List<AppItemExchangeConf> appItemExchangeConfList) {
    	if(appItemExchangeConfList!=null&&appItemExchangeConfList.size()>0){
    		for(AppItemExchangeConf appItemExchangeConf: appItemExchangeConfList){
    			if(appItemExchangeConf!=null){
    				addEntity(appItemExchangeConf);
    			}
    		}
    	}
		return appItemExchangeConfList;
	}

	@Override
	public List<AppItemExchangeConf> updateEntitys(
			List<AppItemExchangeConf> appItemExchangeConfList) {
		if(appItemExchangeConfList!=null&&appItemExchangeConfList.size()>0){
    		for(AppItemExchangeConf appItemExchangeConf: appItemExchangeConfList){
    			if(appItemExchangeConf!=null){
    				updateEntity(appItemExchangeConf);
    			}
    		}
    	}
		return appItemExchangeConfList;
	}
    
    @Override
	public AppItemExchangeConf addEntity(AppItemExchangeConf appItemExchangeConf) {
    	//新增交换配置时,因为发送端可以选多个，接收端也可以选多个,会根据发送端为基础生成记录存储
    	//有时发送端已经有记录了,所以是将新增加的接收端添加到库中(和编辑不一样,编辑时只能选择一个发送端)
    	AppItemExchangeConf entity = findAppItemExchangeConfBySendDept(appItemExchangeConf.getAppMsg().getId(), appItemExchangeConf.getAppItem().getId(), new String[]{appItemExchangeConf.getSendDept().getId()+""});
    	if(entity==null){
	    	appItemExchangeConfDao.save(appItemExchangeConf);
	    	
	    	//交换配置-发送
	    	ChangeItem changeItem = new ChangeItem();
	    	changeItem.setAppItemExchangeConf(appItemExchangeConf);
	    	changeItem.setSysDept(appItemExchangeConf.getSendDept());
	    	changeItem.setItemType(1);
	    	changeItemDao.save(changeItem);
    
    	
	    	if(appItemExchangeConf.getIsShare().equals(0)){
	    		//交换配置-共享
	    		ChangeItem shareChangeItem = new ChangeItem();
	    		shareChangeItem.setAppItemExchangeConf(appItemExchangeConf);
	    		shareChangeItem.setSysDept(appItemExchangeConf.getSendDept());
	    		shareChangeItem.setItemType(3);
	    		//shareChangeItem.setChangeItem(changeItem);
	        	changeItemDao.save(shareChangeItem);
	    	}
	    	
	    	List<AppItemExchangeConfDetails> appItemExchangeConfDetailsList = appItemExchangeConf.getAppItemExchangeConfDetails();
	    	if(appItemExchangeConfDetailsList!=null && appItemExchangeConfDetailsList.size()>0){
	    		for(AppItemExchangeConfDetails appItemExchangeConfDetails : appItemExchangeConfDetailsList){
	    			appItemExchangeConfDetails.setAppItemExchangeConf(appItemExchangeConf);
	    			appItemExchangeConfDetailsDao.save(appItemExchangeConfDetails);
	    			
	    			//交换配置-接收
	    			ChangeItem receiveChangeItem = new ChangeItem();
	    			receiveChangeItem.setAppItemExchangeConf(appItemExchangeConf);
	    			receiveChangeItem.setSysDept(appItemExchangeConfDetails.getReceiveDept());
	    			receiveChangeItem.setItemType(2);
	    			//receiveChangeItem.setChangeItem(changeItem);
	    	    	changeItemDao.save(receiveChangeItem);
	    		}
	    	}
    	}else{
    		appItemExchangeConf.setId(entity.getId());
    		updateAppItemExchangeConf(appItemExchangeConf, entity);
    	}
    	
		return appItemExchangeConf;
	}
    
    //只增不减
	public AppItemExchangeConf updateAppItemExchangeConf(AppItemExchangeConf appItemExchangeConf, AppItemExchangeConf entity) {
       	//appItemExchangeConfDao.update(appItemExchangeConf);
       	//appItemExchangeConfDetailsDao.removeAllByAppItemExchangeConfId(appItemExchangeConf.getId());
		List<AppItemExchangeConfDetails> appItemExchangeConfDetailsList = entity.getAppItemExchangeConfDetails();
		Set<Long> appItemExchangeConfDetailsIdSet = new HashSet<Long>();
		if(appItemExchangeConfDetailsList!=null&&appItemExchangeConfDetailsList.size()>0){
			for(AppItemExchangeConfDetails appItemExchangeConfDetails : appItemExchangeConfDetailsList){
				appItemExchangeConfDetailsIdSet.add(appItemExchangeConfDetails.getReceiveDept().getId());
			}
		}
		
		//如果以前没有共享,则要处理
		/*if(!entity.getIsShare().equals(appItemExchangeConf.getIsShare())&&appItemExchangeConf.getIsShare().equals(0)){
			//交换配置-共享
			ChangeItem shareChangeItem = new ChangeItem();
    		shareChangeItem.setAppItemExchangeConf(appItemExchangeConf);
    		shareChangeItem.setSysDept(appItemExchangeConf.getSendDept());
    		shareChangeItem.setItemType(3);
        	changeItemDao.save(shareChangeItem);
		}*/
		
       	appItemExchangeConfDetailsList = appItemExchangeConf.getAppItemExchangeConfDetails();
       	if(appItemExchangeConfDetailsList!=null && appItemExchangeConfDetailsList.size()>0){
       		for(AppItemExchangeConfDetails appItemExchangeConfDetails : appItemExchangeConfDetailsList){
       			if(appItemExchangeConfDetailsIdSet.contains(appItemExchangeConfDetails.getReceiveDept().getId()))continue;
       			appItemExchangeConfDetails.setAppItemExchangeConf(appItemExchangeConf);
       			appItemExchangeConfDetailsDao.save(appItemExchangeConfDetails);
       			
       			//交换配置-接收
       			ChangeItem receiveChangeItem = new ChangeItem();
    			receiveChangeItem.setAppItemExchangeConf(appItemExchangeConf);
    			receiveChangeItem.setSysDept(appItemExchangeConfDetails.getReceiveDept());
    			receiveChangeItem.setItemType(2);
    	    	changeItemDao.save(receiveChangeItem);
       		}
       	}
   		return appItemExchangeConf;
   	}
    
    @Override
   	public AppItemExchangeConf updateEntity(AppItemExchangeConf appItemExchangeConf) {
       	
    	AppItemExchangeConf entity = findAppItemExchangeConfBySendDept(appItemExchangeConf.getAppMsg().getId(), appItemExchangeConf.getAppItem().getId(), new String[]{appItemExchangeConf.getSendDept().getId()+""});
       	appItemExchangeConfDao.update(appItemExchangeConf);
       	
      //如果以前没有共享,则要处理
      	if(!entity.getIsShare().equals(appItemExchangeConf.getIsShare())&&appItemExchangeConf.getIsShare().equals(0)){
  			//交换配置-共享
  			ChangeItem shareChangeItem = new ChangeItem();
      		shareChangeItem.setAppItemExchangeConf(appItemExchangeConf);
      		shareChangeItem.setSysDept(appItemExchangeConf.getSendDept());
      		shareChangeItem.setItemType(3);
          	changeItemDao.save(shareChangeItem);
      	}
       	
       	appItemExchangeConfDetailsDao.removeAllByAppItemExchangeConfId(appItemExchangeConf.getId());
       	
       	List<AppItemExchangeConfDetails> appItemExchangeConfDetailsList = appItemExchangeConf.getAppItemExchangeConfDetails();
       	if(appItemExchangeConfDetailsList!=null && appItemExchangeConfDetailsList.size()>0){
       		for(AppItemExchangeConfDetails appItemExchangeConfDetails : appItemExchangeConfDetailsList){
       			appItemExchangeConfDetails.setAppItemExchangeConf(appItemExchangeConf);
       			appItemExchangeConfDetailsDao.save(appItemExchangeConfDetails);
       			
       			//交换配置-接收
       			ChangeItem receiveChangeItem = new ChangeItem();
    			receiveChangeItem.setAppItemExchangeConf(appItemExchangeConf);
    			receiveChangeItem.setSysDept(appItemExchangeConfDetails.getReceiveDept());
    			receiveChangeItem.setItemType(2);
    	    	changeItemDao.save(receiveChangeItem);
       		}
       	}
   		return appItemExchangeConf;
   	}
    
	@Override
	public void deleteEntityById(Long appItemExchangeConfId) throws ServiceException {
		AppItemExchangeConf entity = this.findById(appItemExchangeConfId);
		//删除交换配置
		changeItemDao.removeAllByAppItemExchangeConfId(appItemExchangeConfId);
		//删除指标配置详情
		appItemExchangeConfDetailsDao.removeAllByAppItemExchangeConfId(appItemExchangeConfId);
		//删除指标配置
		appItemExchangeConfDao.remove(entity);
	}
    
	@Override
	public AppItemExchangeConf findAppItemExchangeConfBySendDept(Long appMsgId,
			Long AppItemId, String[] sendDeptIdsArray) {
		String sendDeptIdsStr = "";
		for(String sendDeptId : sendDeptIdsArray){
			sendDeptIdsStr += sendDeptId+",";
		}
		sendDeptIdsStr = sendDeptIdsStr.substring(0, sendDeptIdsStr.length()-1);
		String hql = "from AppItemExchangeConf e where e.appMsg.id=? and e.appItem.id=? and e.sendDept.id in ("+sendDeptIdsStr+")";
		List<AppItemExchangeConf> appItemExchangeConfList = appItemExchangeConfDao.findListByHql(hql, appMsgId, AppItemId);
		if(appItemExchangeConfList!=null&&appItemExchangeConfList.size()>0)return appItemExchangeConfList.get(0);
		return null;
	}
    
    
    @Override
    public EntityHibernateDao<AppItemExchangeConf> getEntityDao() {
        return appItemExchangeConfDao;
    }

    @Autowired
    public void setItemDao(AppItemExchangeConfDao appItemExchangeConfDao) {
        this.appItemExchangeConfDao = appItemExchangeConfDao;
    }

    @Autowired
	public void setAppItemExchangeConfDetailsDao(
			AppItemExchangeConfDetailsDao appItemExchangeConfDetailsDao) {
		this.appItemExchangeConfDetailsDao = appItemExchangeConfDetailsDao;
	}

    @Autowired
	public void setChangeItemDao(ChangeItemDao changeItemDao) {
		this.changeItemDao = changeItemDao;
	}
}
