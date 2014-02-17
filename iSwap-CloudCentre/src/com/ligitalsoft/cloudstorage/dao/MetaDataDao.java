/*
 * @(#)CloudNodeMgDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.model.cloudstorage.MetaData;


/**
 * 元数据管理 DAO
 * @author  zhangx
 * @since   Jun 15, 2011 12:31:18 AM
 * @name    com.ligitalsoft.cloudstorage.dao.CloudNodeMgDao.java
 * @version 1.0
 */
@Repository
public class MetaDataDao extends EntityHibernateDao<MetaData> {
    
    /**
     * 更改指标状态
     * @param ids
     * @param status
     * @author zhangx
     */
    public void updateStatus(Long[] ids, String status) {
        for (Long id : ids) {
            MetaData data = findById(id);
            data.setShareState(status);
            update(data);
            this.getSession().flush();
        }
    }
    /**
     * 通过部门ID查询指标项
     * @author fangbin
     * @param deptId
     * @return
     */
    public List<MetaData> getAllByDeptId(String deptId){
    	List<MetaData> metaDataList=new ArrayList<MetaData>();
    	String hql="from MetaData md where  md.sysDept.id="+deptId;
    	metaDataList=this.findListByHql(hql);
    	return metaDataList;
    	
    }

}

