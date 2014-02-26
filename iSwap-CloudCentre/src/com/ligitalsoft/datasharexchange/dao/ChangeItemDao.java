/*
 * @(#)ChangeItemDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.changemanage.ChangeItem;

/**
 * 交换_指标dao层
 * @author zhangx
 * @since Jun 14, 2011 3:02:12 PM
 * @name com.ligitalsoft.cloudstorage.dao.ChangeItemDao.java
 * @version 1.0
 */
@Repository
public class ChangeItemDao extends EntityHibernateDao<ChangeItem> {
	
	public  List<ChangeItem> findListByDataTypeDeptId(String dataType,Long deptId){
		String hql="from ChangeItem e where e.dataType=? and e.sysDept.id=?";
		Object[] obj={dataType,deptId};
		return powerHibernateDao.findListByHql(hql, obj);
	}
    /**
     * 指标ID和共享状态查询
     * @param id
     * @param shareState
     * @return
     * @author zhangx
     */
    public List<ChangeItem> findListByShareState(String shareState){
        String hql="from ChangeItem e where e.shareState=?";
        return powerHibernateDao.findListByHql(hql, shareState);
    }
	 /**
     * 根据部门ID得到指标 
     * @param deptId
     * @return
     * @author daic
     */
	@SuppressWarnings("unchecked")
	public List<ChangeItem> findListByDeptId(Long deptId) {
        String hql = "select e from ChangeItem e where e.sysDept.id=? ";
        List<ChangeItem> temps = powerHibernateDao.findListByHql(hql, deptId);
        return temps;
    }
	
	public List<ChangeItem> findListByDeptId(Long deptId, long itemType) {
        String hql = "select e from ChangeItem e where e.itemType="+itemType+" and e.sysDept.id=? ";
        List<ChangeItem> temps = powerHibernateDao.findListByHql(hql, deptId);
        return temps;
    }
	
	public List<ChangeItem> findListByItemType(Integer[] itemTypeArray) {
		String itemTypeArrarStr = "";
		if(itemTypeArray!=null&&itemTypeArray.length>0){
			for(Integer itemType : itemTypeArray){
				itemTypeArrarStr += itemType+",";
			}
			if(itemTypeArrarStr.length()>1)itemTypeArrarStr = itemTypeArrarStr.substring(0, itemTypeArrarStr.length()-1);
		}
        String hql = "select e from ChangeItem e where e.itemType in("+itemTypeArrarStr+")";
        List<ChangeItem> temps = powerHibernateDao.findListByHql(hql);
        return temps;
    }
	
	/**
	 * 
	 * 根据部门ID查询数据交换总量和按时次数
	 * @author fangbin
	 * @param deptId
	 * @return
	 * @throws SQLException 
	 */
	public List<Map<String,String>> targetDataShare(String deptId) throws SQLException{
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    	PreparedStatement pstmt=null;
		Connection conn = null;
		Statement stmt = null;
		String dataBaseName="mysql";//定义数据类型
		conn = this.powerHibernateDao.getSession().connection();
		stmt = conn.createStatement();
		ResultSet rs = null;
		String sql="select cics.id ,cics.item_Name,cics.ontimeCount ,sre.data_num from ( select citem.id ,citem.item_Name,cst.ontimeCount from (select ci.id,ci.item_Name  from Change_Item ci where ci.DEPT_ID="+deptId+") as citem,(select cs.ITEM_ID,count(cs.send_State) as ontimeCount from CHANGE_Send_Task cs where cs.send_State='1'  group by  cs.ITEM_ID) as cst where  citem.id=cst.ITEM_ID) as cics left join (select  sum(sr.data_num) as data_num,sr.item_Id from send_result sr group by sr.item_Id  order by data_num desc)  as sre on sre.item_Id=cics.id order by sre.data_num desc";
		dataBaseName = conn.getMetaData().getDatabaseProductName();
    	rs = stmt.executeQuery(sql);
    	while (rs.next()) {
    		Map<String,String> rsmap=new HashMap<String,String>();
    		rsmap.put("targetId", rs.getString("id"));
    		rsmap.put("targetName", rs.getString("item_Name"));
    		rsmap.put("ontimeCount", rs.getString("ontimeCount"));
    		rsmap.put("dataNum", rs.getString("data_num"));
    		list.add(rsmap);
    	}
    	if(conn!=null){
    		conn.close();
    	}
		return list;
	}
	
	/**
	 * 根据指标项ids查询指标项列表
	 * @param Ids eg. 1,2,3
	 * @return
	 * @author HuJun
	 */
	public  List<ChangeItem> findListByIds(String ids, PageBean page){
		String hql="from ChangeItem e where e.id in ("+ids+")";
		return super.findListByPage(hql, page, null);
	}
	
	public int removeAllByAppItemExchangeConfId(Long appItemExchangeConfId){
		String hql = "delete ChangeItem e where e.id=?";
        return this.powerHibernateDao.executeUpdate(hql, appItemExchangeConfId);
	}
}
