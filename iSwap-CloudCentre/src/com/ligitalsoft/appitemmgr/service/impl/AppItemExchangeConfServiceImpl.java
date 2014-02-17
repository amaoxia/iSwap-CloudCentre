/*
 * @(#)AppMsgServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.appitemmgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.appitemmgr.dao.AppItemExchangeConfDao;
import com.ligitalsoft.appitemmgr.dao.AppItemExchangeConfDetailsDao;
import com.ligitalsoft.appitemmgr.service.AppItemExchangeConfService;
import com.ligitalsoft.datasharexchange.dao.ChangeItemDao;
import com.ligitalsoft.model.appitemmgr.AppItemExchangeConf;
import com.ligitalsoft.model.appitemmgr.AppItemExchangeConfDetails;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.cloudstorage.MetaData;

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
	public AppItemExchangeConf addEntity(AppItemExchangeConf appItemExchangeConf) {
    	
    	appItemExchangeConfDao.save(appItemExchangeConf);
    	
    	ChangeItem changeItem = new ChangeItem();
    	changeItem.setAppItemExchangeConf(appItemExchangeConf);
    	changeItem.setSysDept(appItemExchangeConf.getSendDept());
    	changeItem.setItemType(1);
    	changeItemDao.save(changeItem);
    	
    	if(appItemExchangeConf.getIsShare().equals(0)){
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
    			
    			ChangeItem receiveChangeItem = new ChangeItem();
    			receiveChangeItem.setAppItemExchangeConf(appItemExchangeConf);
    			receiveChangeItem.setSysDept(appItemExchangeConfDetails.getReceiveDept());
    			receiveChangeItem.setItemType(2);
    			//receiveChangeItem.setChangeItem(changeItem);
    	    	changeItemDao.save(receiveChangeItem);
    		}
    	}
    	
		return appItemExchangeConf;
	}
    
    @Override
   	public AppItemExchangeConf updateEntity(AppItemExchangeConf appItemExchangeConf) {
       	
       	appItemExchangeConfDao.update(appItemExchangeConf);
       	
       	appItemExchangeConfDetailsDao.removeAllByAppItemExchangeConfId(appItemExchangeConf.getId());
       	
       	List<AppItemExchangeConfDetails> appItemExchangeConfDetailsList = appItemExchangeConf.getAppItemExchangeConfDetails();
       	if(appItemExchangeConfDetailsList!=null && appItemExchangeConfDetailsList.size()>0){
       		for(AppItemExchangeConfDetails appItemExchangeConfDetails : appItemExchangeConfDetailsList){
       			appItemExchangeConfDetails.setAppItemExchangeConf(appItemExchangeConf);
       			appItemExchangeConfDetailsDao.save(appItemExchangeConfDetails);
       		}
       	}
   		return appItemExchangeConf;
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
