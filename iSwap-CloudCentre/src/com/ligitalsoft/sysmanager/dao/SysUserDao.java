package com.ligitalsoft.sysmanager.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.common.framework.dao.SortPara;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.system.SysUser;

/**
 * 用户DAO
 * 
 * @author zhangx
 * @since May 16, 2011 10:58:51 AM
 * @name com.ligitalsoft.sysmanager.dao.SysUserDao.java
 * @version 1.0
 */
@Repository
public class SysUserDao extends EntityHibernateDao<SysUser> {

	/**
	 * 自定义用户分页
	 * 
	 * @param pageBean
	 * @param map
	 * @return
	 * @author zhangx
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findUserListByPage(PageBean pageBean,
			Map<String, String> map, List<SortPara> sortParas) {
		Session session = powerHibernateDao.getSession();
		///方式一
		/*	
		String countSql = "select count(1) from sys_user u left join sys_userrole ur on u.id=ur.user_id left join sys_role r on r.id=ur.role_id left join sys_userdept ud on ud.user_id=u.id left join  sys_dept d on d.id=ud.dept_id";
	    String sql = "select u.id,u.user_name,u.date_Create,d.dept_name,r.name,u.status,r.code from sys_user u left join sys_userrole ur on u.id=ur.user_id left join sys_role r on r.id=ur.role_id left join sys_userdept ud on ud.user_id=u.id left join  sys_dept d on d.id=ud.dept_id";
	    */
		///方式二
		String countSql ="select count(1) from sys_user u,sys_role r,sys_dept d,sys_userrole ur,sys_userdept ud where u.id=ur.user_id and r.id=ur.role_id and  u.id=ud.user_id and d.id=ud.dept_id";
		String sql ="select u.id,u.user_name,u.date_Create,d.dept_name,r.name,u.status,r.code from sys_user u,sys_role r,sys_dept d,sys_userrole ur,sys_userdept ud where u.id=ur.user_id and r.id=ur.role_id and  u.id=ud.user_id and d.id=ud.dept_id";
		Set<String> keys = map.keySet();// 得到所有的key
		for (String key : keys) {
			if (key.equals("deptName")) {
				countSql += "  and  d.dept_name like :deptName";
				sql += "   and  d.dept_name like :deptName ";
			}
			if (key.equals("userName")) {
				countSql += "   and  u.user_name like :userName";
				sql += "    and  u.user_name like :userName ";
			}
		}
		int i = 0;
		for (SortPara sort : sortParas) {
			if (i == 0) {
				sql += "   order by " + sort.getProperty() + "    "
						+ sort.getOrder();
			} else {
				sql += " , " + sort.getProperty() + " " + sort.getOrder();
			}
			i++;
		}
		SQLQuery countquery = session.createSQLQuery(countSql);
		SQLQuery query = session.createSQLQuery(sql);
		for (String key : keys) {
			if (key.equals("deptName")) {
				countquery.setParameter("deptName", "%" + map.get(key) + "%");
				query.setParameter("deptName", "%" + map.get(key) + "%");
			}
			if (key.equals("userName")) {
				countquery.setParameter("userName", "%" + map.get(key) + "%");
				query.setParameter("userName", "%" + map.get(key) + "%");
			}
		}
		int count = Integer.parseInt(countquery.uniqueResult().toString());
		pageBean.setTotal(count);
		query.setFirstResult(pageBean.getStart());
		query.setMaxResults(pageBean.getPerPage());
		return query.list();
	}

	/**
	 * 更改用户状态
	 * 
	 * @param ids
	 * @param status
	 * @author zhangx
	 */
	public void updateStatus(Long[] ids, Character status) {
		for (Long id : ids) {
			SysUser sysUser = findById(id);
			sysUser.setStatus(status);
			update(sysUser);
			this.getSession().flush();
		}
	}
}