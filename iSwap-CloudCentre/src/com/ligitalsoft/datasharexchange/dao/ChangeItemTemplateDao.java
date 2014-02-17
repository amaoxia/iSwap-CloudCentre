/*
 * @(#)ChangeItemTemplateDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.changemanage.ChangeItemTemplate;

/**
 * 交换指标_模板DAO
 * @author zhangx
 * @since Jun 27, 2011 2:54:05 PM
 * @name com.ligitalsoft.datasharexchange.dao.ChangeItemTemplateDao.java
 * @version 1.0
 */
@Repository
public class ChangeItemTemplateDao extends EntityHibernateDao<ChangeItemTemplate> {
    /**
     * 根据指标查询指标文档
     * @param itemId
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public ChangeItemTemplate findByItemId(Long itemId) {
        String hql = "select e from ChangeItemTemplate e where e.changeItem.id=? ";
        List<ChangeItemTemplate> temps = powerHibernateDao.findListByHql(hql, itemId);
        if (temps == null || temps.size() == 0) {
            return null;
        } else {
            return temps.get(0);
        }
    }
    /**
     * 根据指标ID删除对应模板
     * @param itemId
     * @author zhangx
     */
    public void deleteByItemId(Long itemId){
        String hql="delete from  ChangeItemTemplate e where e.changeItem.id=?";
        powerHibernateDao.executeUpdate(hql, itemId);
    }
    /**
     * 设置模板状态
     * @param ids
     * @param status
     * @author zhangx
     */
    public void updateStatus(Long[] ids, String status) {
        for (Long id : ids) {
            ChangeItemTemplate template = findById(id);
            template.setState(status);
            update(template);
            this.getSession().flush();
        }
    }
}
