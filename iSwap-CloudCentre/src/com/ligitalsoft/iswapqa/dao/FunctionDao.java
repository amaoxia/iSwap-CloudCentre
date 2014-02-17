package com.ligitalsoft.iswapqa.dao;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.iswapqa.Function;

/**
 * 方法表DAO层
 * @author daic
 * @since 2011-08-04 14:34:41
 * @name com.ligitalsoft.iswapqa.dao.FunctionDao.java
 * @version 1.0
 */
@Repository("functionDao")
public class FunctionDao extends EntityHibernateDao<Function> {
	
	 /**
     * 查找规则文件中的方法
     * @return   List<Function>
     * @author daic
     */
	@SuppressWarnings("unchecked")
	public List<Function> getFuncsByRuleFileId(Long ruleFileId){
		String hql = "select r.funcId from FunctionFileRel r where r.ruleFileId = ? ";
		List<Long> funcIdList = this.findListByHql(hql,-1, -1, new Long[]{ruleFileId});
		List<Function> list = new ArrayList<Function>();
		for(Long funcId : funcIdList){
			list.add(this.findById(funcId));
		}
		return list;
	}
}