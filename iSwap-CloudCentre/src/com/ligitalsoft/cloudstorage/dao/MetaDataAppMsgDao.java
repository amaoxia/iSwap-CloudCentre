/*
 * @(#)MetaDataAppMsgDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.cloudstorage.MetaDataAppMsg;

/**
 * 元数据_应用DAO
 * @author zhangx
 * @since Jun 24, 2011 10:39:36 AM
 * @name com.ligitalsoft.cloudstorage.dao.MetaDataAppMsgDao.java
 * @version 1.0
 */
@Repository
public class MetaDataAppMsgDao extends EntityHibernateDao<MetaDataAppMsg> {

    /**
     * 根据元数据查询对应服务应用
     * @param metaId
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<MetaDataAppMsg> findListByMetaId(Long metaId) {
        String hql = "select e from MetaDataAppMsg e where e.metaData.id=?";
        return powerHibernateDao.findListByHql(hql, metaId);
    }
    /**
     * 根据指标Id删除id应用关联
     * @param metaId
     * @author zhangx
     */
    public void deleteByMetaId(Long metaId) {
        String hql = "delete  from  MetaDataAppMsg e where e.metaData.id=?";
        powerHibernateDao.executeUpdate(hql, metaId);
    }
    /**
     * 更改指标状态
     * @param ids
     * @param status
     * @author zhangx
     */
    public void updateStatus(Long[] ids, String status) {
        for (Long id : ids) {
            MetaDataAppMsg appMsg = findById(id);
            appMsg.setShareState(status);
            update(appMsg);
            this.getSession().flush();
        }
    }
    /**
     * 通过应用ID查询
     * @author fangbin
     * @param appId
     * @return
     */
    public List<MetaDataAppMsg> findListByAppId(Long appId){
    	 String hql = "from MetaDataAppMsg e where e.appMsg.id=? ";
         return powerHibernateDao.findListByHql(hql, appId);
    }
    /**
     * 数据接收按应用排名
     * @return
     */
    public List<Object []> appRank(){
    	String sql="select app.id,app.app_Name,cl_me_app.META_ID from  CLOUDCENTER_APP app  left   join   (select count(cma.META_ID) as META_ID ,cma.APP_ID from CLOUDSTOR_METADATA_APPMSG  cma group by cma.APP_ID) cl_me_app on app.id=cl_me_app.APP_ID order by cl_me_app.META_ID desc";
    	Query query = this.getSession().createSQLQuery(sql);
    	return query.list();
    }
    /**
     * 数据接收按指标项排名
     * @author fangbin
     * @param map
     * @return
     */
    public List<Object[]> targetRank(Map<String,String> map){
    	String sql="select rr.item_Id, rr.dataNum,rr.item_name from ( select * from CLOUDSTOR_METADATA_APPMSG where APP_ID ='"+map.get("appId")+"') cma , (select item_Id,sum(data_Num) as dataNum,item_name from  receive_result group by item_Id order by dataNum desc ) rr where  rr.item_Id = cma.META_ID";
    	Query query = this.getSession().createSQLQuery(sql);
    	return query.list();
    }
}
