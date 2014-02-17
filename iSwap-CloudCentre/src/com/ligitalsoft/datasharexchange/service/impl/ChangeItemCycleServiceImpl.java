/*
 * @(#)ChangItemCycleServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.datasharexchange.dao.ChangeItemCycleDao;
import com.ligitalsoft.datasharexchange.service.IChangeItemCycleService;
import com.ligitalsoft.model.changemanage.ChangeItemCycle;

/**
 * 指标交换DAO
 * @author zhangx
 * @since Aug 16, 2011 1:52:26 PM
 * @name com.ligitalsoft.datasharexchange.service.impl.ChangItemCycleServiceImpl.java
 * @version 1.0
 */
@Service("changeItemCycleService")
public class ChangeItemCycleServiceImpl extends BaseSericesImpl<ChangeItemCycle> implements IChangeItemCycleService {

    private ChangeItemCycleDao changItemCycleDao;

    @Override
    public EntityHibernateDao<ChangeItemCycle> getEntityDao() {
        return changItemCycleDao;
    }
    @Autowired
    public void setChangItemCycleDao(ChangeItemCycleDao changItemCycleDao) {
        this.changItemCycleDao = changItemCycleDao;
    }

}
