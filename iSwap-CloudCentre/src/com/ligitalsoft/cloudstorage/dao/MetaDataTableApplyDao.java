/*
 * @(#)MetaDataTableApplyDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.model.cloudstorage.MetaDataTableApply;

/**
 * 数据申请查看表
 * @author zhangx
 * @since Aug 11, 2011 1:37:48 AM
 * @name com.ligitalsoft.cloudstorage.dao.MetaDataTableApplyDao.java
 * @version 1.0
 */
@Repository
public class MetaDataTableApplyDao extends EntityHibernateDao<MetaDataTableApply> {

    /**
     * 查询当前指标是否申请
     * @param itemId
     * @param deptId
     * @return
     * @author zhangx
     */
    public MetaDataTableApply findByItemDeptId(Long itemId, Long deptId,String type) {
        String hql = "select e from  MetaDataTableApply e  where e.sysDept.id=? and e.metaData.id=?";
        Object[] obj=null;
        if(!StringUtils.isBlank(type)){
            hql+=" and  e.dataApplyState=?";
            obj=new Object[]{ deptId, itemId,type};
        }else{
            obj=new Object[]{ deptId, itemId};
        }
        List<MetaDataTableApply> apply = powerHibernateDao.findListByHql(hql, obj);
        if (apply != null && apply.size() != 0) {
            return apply.get(0);
        }
        return null;
    }
    
    /**
     * 查询当前指标是否申请
     * @param itemId
     * @param deptId
     * @param appId
     * @return
     * @author zhangx
     */
    public MetaDataTableApply findByItemDeptId(Long itemId, Long deptId,Long appId,String type) {
        String hql = "select e from  MetaDataTableApply e  where e.sysDept.id=? and e.metaData.id=? and e.appMsg.id=?";
        Object[] obj=null;
        if(!StringUtils.isBlank(type)){
            hql+=" and  e.dataApplyState=?";
            obj=new Object[]{ deptId, itemId,appId,type};
        }else{
            obj=new Object[]{ deptId, itemId,appId};
        }
        List<MetaDataTableApply> apply = powerHibernateDao.findListByHql(hql, obj);
        if (apply != null && apply.size() != 0) {
            return apply.get(0);
        }
        return null;
    }
}
