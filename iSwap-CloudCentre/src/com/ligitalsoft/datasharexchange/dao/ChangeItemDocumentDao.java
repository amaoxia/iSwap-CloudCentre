/*
 * @(#)ChangeItemDocumentDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.changemanage.ChangeItemDocument;


/**
 * 交换指标文档_文档管理DAO
 * @author  zhangx
 * @since   Jun 27, 2011 2:48:47 PM
 * @name    com.ligitalsoft.datasharexchange.dao.ChangeItemDocumentDao.java
 * @version 1.0
 */
@Repository
public class ChangeItemDocumentDao extends EntityHibernateDao<ChangeItemDocument> {
    /**
     * 根据指标ID查询指标文档
     * @param itemId
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public ChangeItemDocument findByItemId(Long itemId) {
        String hql = "select e from ChangeItemDocument e where e.changeItem.id=? ";
        List<ChangeItemDocument> temps = powerHibernateDao.findListByHql(hql, itemId);
        if (temps == null || temps.size() == 0) {
            return null;
        } else {
            return temps.get(0);
        }
    }
    /**
     * 根据指标ID,状态查询指标文档
     * @param itemId
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public  List<ChangeItemDocument> findByItemId(Long itemId,String exchangeStatus) {
        String hql = "select e from ChangeItemDocument e where e.changeItem.id=? and e.exchangeState=?";
        List<ChangeItemDocument> temps = powerHibernateDao.findListByHql(hql, new Object[]{itemId,exchangeStatus});
            return temps;
    }
    
    /**
     * 根据指标ID删除对应模板
     * @param itemId
     * @author zhangx
     */
    public void deleteByItemId(Long itemId){
        String hql="delete from  ChangeItemDocument e where e.changeItem.id=?";
        powerHibernateDao.executeUpdate(hql, itemId);
    }
    /**
     * 查询
     * @param fileName
     * @return
     * @author zhangx
     */
    public ChangeItemDocument findByDocumentName(String documentName){
        String hql="select e from ChangeItemDocument e where e.documentName=?";
       List<ChangeItemDocument> list=new ArrayList<ChangeItemDocument>();
       list=   powerHibernateDao.findListByHql(hql, documentName);
       if(list.size()!=0){
           return list.get(0);
       }
       return null;
    }
}

