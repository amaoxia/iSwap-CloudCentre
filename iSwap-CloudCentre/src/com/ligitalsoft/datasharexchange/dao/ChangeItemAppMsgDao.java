/*
 * @(#)ChangeItemAppMsgDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.changemanage.ChangeItemAppMsg;


/**
 * 指标_应用DAO
 * @author  zhangx
 * @since   Jun 24, 2011 10:29:31 AM
 * @name    com.ligitalsoft.datasharexchange.dao.ChangeItemAppMsgDao.java
 * @version 1.0
 */
@Repository
public class ChangeItemAppMsgDao extends EntityHibernateDao<ChangeItemAppMsg> {
    
    
    /**
     * 根据元数据查询对应服务应用
     * @param metaId
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<ChangeItemAppMsg> findListByItemd(Long itemId) {
        String hql = "select e from ChangeItemAppMsg e where e.changeItem.id=?";
        return powerHibernateDao.findListByHql(hql, itemId);
    }
    /**
     * 根据指标Id删除id应用关联
     * @param metaId
     * @author zhangx
     */
    public void deleteByItemId(Long metaId) {
        String hql = "delete  from  ChangeItemAppMsg e where e.changeItem.id=?";
        powerHibernateDao.executeUpdate(hql, metaId);
    }
}

