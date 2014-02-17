package com.ligitalsoft.datasharexchange.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.changemanage.SendItemDept;

@Repository
public class SendItemDeptDao extends EntityHibernateDao<SendItemDept> {
	public  List<SendItemDept> findListDeptId(Long deptId){
		String hql="from  SendItemDept e where e.sysDept.id=?";
		return powerHibernateDao.findListByHql(hql, deptId);
	}
	public  List<SendItemDept> findListItemId(Long itemId){
		String hql="from  SendItemDept e where e.changeItem.id=?";
		return powerHibernateDao.findListByHql(hql, itemId);
	}
	public void deleteByDeptId(Long deptId){
		String hql="delete from  SendItemDept e where e.sysDept.id=?";
		powerHibernateDao.executeUpdate(hql, deptId);
	}
}
