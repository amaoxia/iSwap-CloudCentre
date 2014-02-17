/*
 * @(#)TableInfoDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.changemanage.ChangeTableDesc;
import com.ligitalsoft.model.cloudstorage.TableInfo;

/**
 * 指标信息_表结构信息DAO
 * @author zhangx
 * @since Jun 16, 2011 8:42:07 PM
 * @name com.ligitalsoft.cloudstorage.dao.TableInfoDao.java
 * @version 1.0
 */
@Repository
public class TableInfoDao extends EntityHibernateDao<TableInfo> {

    /**
     *
     * @param itemId
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<TableInfo> findListByItemId(Long itemId) {
        String hql = "select e from TableInfo e where e.metaData.id=?";
        return powerHibernateDao.findListByHql(hql, itemId);
    }
}
