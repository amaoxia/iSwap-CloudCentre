/*
 * @(#)ChangeTableDescDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.changemanage.ChangeTableDesc;

/**
 * 交换表结构信息
 * @author zhangx
 * @since Jun 14, 2011 5:19:27 PM
 * @name com.ligitalsoft.datasharexchange.dao.ChangeTableDescDao.java
 * @version 1.0
 */
@Repository
public class ChangeTableDescDao extends EntityHibernateDao<ChangeTableDesc> {

    /**
     * 根据指标ID 查询 表结构
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<ChangeTableDesc> findListByItemId(Long itemId) {
        String hql = "select e from ChangeTableDesc e  where e.changeItem.id=?";
        return powerHibernateDao.findListByHql(hql, itemId);
    }
    
    /**
     * 更改表字段状态
     * @param ids
     * @param status
     * @author zhangx
     */
    public void updateStatus(Long[] ids, String status) {
        for (Long id : ids) {
            ChangeTableDesc desc = findById(id);
            desc.setIsShow(status);
            update(desc);
            this.getSession().flush();
        }
    }
}
