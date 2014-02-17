/*
 * @(#)ChangeItemAppMsgServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.datasharexchange.dao.ChangeItemAppMsgDao;
import com.ligitalsoft.datasharexchange.service.IChangeItemAppMsgService;
import com.ligitalsoft.model.changemanage.ChangeItemAppMsg;

/**
 * 指标_应用 实现类
 * @author zhangx
 * @since Jun 24, 2011 10:35:06 AM
 * @name com.ligitalsoft.datasharexchange.service.impl.ChangeItemAppMsgServiceImpl.java
 * @version 1.0
 */
@Service("changeItemAppMsgService")
public class ChangeItemAppMsgServiceImpl extends BaseSericesImpl<ChangeItemAppMsg> implements IChangeItemAppMsgService {

    private ChangeItemAppMsgDao changeItemAppMsgDao;

    @SuppressWarnings("unchecked")
    public List<ChangeItemAppMsg> findListByItemd(Long itemId) {
        return changeItemAppMsgDao.findListByItemd(itemId);
    }

    @Override
    public EntityHibernateDao<ChangeItemAppMsg> getEntityDao() {
        return changeItemAppMsgDao;
    }

    @Autowired
    public void setChangeItemAppMsgDao(ChangeItemAppMsgDao changeItemAppMsgDao) {
        this.changeItemAppMsgDao = changeItemAppMsgDao;
    }

}
