/*
 * @(#)ChangeItemTemplateServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.datasharexchange.dao.ChangeItemTemplateDao;
import com.ligitalsoft.datasharexchange.service.IChangeItemTemplateService;
import com.ligitalsoft.model.changemanage.ChangeItemTemplate;

/**
 * 交换指标_模板  实现类
 * @author zhangx
 * @since Jun 27, 2011 2:58:39 PM
 * @name com.ligitalsoft.datasharexchange.service.impl.ChangeItemTemplateServiceImpl.java
 * @version 1.0
 */
@Service("changeItemTemplateService")
public class ChangeItemTemplateServiceImpl extends BaseSericesImpl<ChangeItemTemplate> implements
                IChangeItemTemplateService {

    private ChangeItemTemplateDao changeItemTemplateDao;

    
    @Override
    public void updateStatus(Long[] ids, String status) throws ServiceException{
        for (Long id : ids) {
            ChangeItemTemplate template=  findById(id);
            if(!StringUtils.isBlank(template.getUploadState())){//表示上传过的模板
                template.setState(status);//设置状态
               update(template);//修改状态
               this.getEntityDao().getSession().flush();//即时操作
               continue;
            }
        }
    }
    
    @Override
    public EntityHibernateDao<ChangeItemTemplate> getEntityDao() {
        return changeItemTemplateDao;
    }

    @Autowired
    public void setChangeItemTemplateDao(ChangeItemTemplateDao changeItemTemplateDao) {
        this.changeItemTemplateDao = changeItemTemplateDao;
    }

}
