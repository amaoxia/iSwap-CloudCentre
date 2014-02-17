package com.ligitalsoft.sysmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.common.framework.dao.SortPara;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.system.SysDept;

/**
 * 部门DAO层
 * 
 * @author zhangx
 * @since May 16, 2011 11:01:19 AM
 * @name com.ligitalsoft.sysmanager.dao.SysDeptDao.java
 * @version 1.0
 */
@Repository
public class SysDeptDao extends EntityHibernateDao<SysDept> {

	public List<SysDept> findListNoId(Long deptId) {
		String hql = "from SysDept e where e.id !=  ?";
		return powerHibernateDao.findListByHql(hql, deptId);
	}

	/**
	 * 自定义用户分页
	 * 
	 * @param pageBean
	 * @param map
	 * @return
	 * @author zhangx
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findDeptItemListByPage(PageBean pageBean,
			Map<String, String> map, List<SortPara> sortParas) {
		Session session = powerHibernateDao.getSession();
		// select d.*,c.* from sys_dept d left join send_item_dept sid on
		// d.id=sid.dept_id left join change_item c on c.id=sid.item_id
		String countSql = "select count(distinct(d.id)) from sys_dept d left join send_item_dept sid on d.id=sid.dept_id left join change_item c on c.id=sid.item_id where 1=1";
		String sql = "select distinct(d.id),d.dept_name,d.dept_names,d.item_Count,d.date_create from sys_dept d left join send_item_dept sid on d.id=sid.dept_id left join change_item c on c.id=sid.item_id where 1=1";
		Set<String> keys = map.keySet();// 得到所有的key
		for (String key : keys) {
			 if (key.equals("itemName")) {
			 countSql += "  and  c.item_name like :itemName";
			 sql += "   and  c.item_name like :itemName";
			 }
			 if (key.equals("deptName")) {
			 countSql += "   and  d.dept_Name like :deptName";
			 sql += "    and  d.dept_Name like :deptName ";
			 }
		}
		 int i=0;
		 for (SortPara sort : sortParas) {
		 if(i==0){
		 sql+="   order by "+sort.getProperty()+"    "+sort.getOrder();
		 }else{
		 sql+=" , "+sort.getProperty()+" "+sort.getOrder();
		 }
		 i++;
		 }
		SQLQuery countquery = session.createSQLQuery(countSql);
		SQLQuery query = session.createSQLQuery(sql);
		 for (String key : keys) {
		 if (key.equals("deptName")) {
		 countquery.setParameter("deptName", "%"+map.get(key)+"%");
		 query.setParameter("deptName", "%"+map.get(key)+"%");
		 }
		 if (key.equals("itemName")) {
		 countquery.setParameter("itemName", "%"+map.get(key)+"%");
		 query.setParameter("itemName", "%"+map.get(key)+"%");
		 	}
		 }
		int count = Integer.parseInt(countquery.uniqueResult().toString());
		pageBean.setTotal(count);
		query.setFirstResult(pageBean.getStart());
		query.setMaxResults(pageBean.getPerPage());
		return query.list();
	}

	/**
	 * 查询部门下的子部门
	 * 
	 * @param fatherId
	 * @return
	 * @author zhangx
	 */
	@SuppressWarnings("unchecked")
	public List<SysDept> findListByFatherId(Long fatherId) {
		String hql = "from SysDept e where e.sysDept.id=? order by level asc";
		return powerHibernateDao.findListByHql(hql, fatherId);
	}
	
	/**
	 * 查询部门根节点
	 * @return
	 * @author HuJun
	 */
	@SuppressWarnings("unchecked")
	public List<SysDept> findListByRoot() {
		String hql = "from SysDept e where e.sysDept is null order by level asc";
		return powerHibernateDao.findListByHql(hql);
	}

	/**
	 * 查询所有一级机构
	 * 
	 * @return
	 * @author zhangx
	 */
	@SuppressWarnings("unchecked")
	public List<SysDept> findListRoot() {
		String hql = "from SysDept e where e.sysDept.id is null and 1=? order by level  asc";
		return powerHibernateDao.findListByHql(hql, 1);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		this.merge(entity);
	}

	/**
	 * 
	 * 根据部门指标个数据对部门排名
	 * 
	 * @author fangbin
	 * @return
	 * @throws SQLException
	 * 
	 */
	public List<Map<String, String>> deptRanking() throws SQLException {

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		Statement stmt = null;
		String dataBaseName = "mysql";// 定义数据类型
		conn = this.powerHibernateDao.getSession().connection();
		stmt = conn.createStatement();
		ResultSet rs = null;
		String sql = "SELECT dept.id as deptId,dept.dept_name as deptName,count(ci.id) as targetsize    FROM change_item ci right   join sys_dept dept on ci.dept_id=dept.id group by ci.dept_id order by size desc";
		dataBaseName = conn.getMetaData().getDatabaseProductName();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Map<String, String> rsmap = new HashMap<String, String>();
			rsmap.put("deptId", rs.getString("deptId"));
			rsmap.put("deptName", rs.getString("deptName"));
			rsmap.put("targetsize", rs.getString("targetsize"));
			list.add(rsmap);
		}
		if (stmt != null) {
			stmt.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		return list;
	}

	/**
	 * 
	 * @return
	 */
	public List<SysDept> findDeptOrderByLevel() {
		String hql = "from SysDept e order by e.level asc";
		return powerHibernateDao.findListByHql(hql);
	}
}