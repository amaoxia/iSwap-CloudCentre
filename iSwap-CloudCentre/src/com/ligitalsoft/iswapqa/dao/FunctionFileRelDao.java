package com.ligitalsoft.iswapqa.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.iswapqa.FunctionFileRel;

/**
 * 方法和规则文件DAO层
 * @author daic
 * @since 2011-07-21 17:32:22
 * @name com.ligitalsoft.iswapqa.dao.FunctionFileRelDao.java
 * @version 1.0
 */
@Repository("functionFileRelDao")
public class FunctionFileRelDao extends EntityHibernateDao<FunctionFileRel> {
	 /**
     * 查找规则文件的关联
     * @return   List<FunctionFileRel>
     * @author daic
     */
	@SuppressWarnings("unchecked")
	public List<FunctionFileRel> getFuncsByRuleFileId(Long ruleFileId){
		String hql = "from FunctionFileRel r where r.ruleFileId = ? ";
		List<FunctionFileRel> functionFileRelList = this.findListByHql(hql,-1, -1, new Long[]{ruleFileId});
		return functionFileRelList;
	}
}