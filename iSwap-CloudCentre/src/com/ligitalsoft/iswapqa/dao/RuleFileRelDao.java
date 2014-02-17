package com.ligitalsoft.iswapqa.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.iswapqa.RuleFileRel;

/**
 * 规则文件DAO层
 * @author daic
 * @since 2011-07-21 17:32:22
 * @name com.ligitalsoft.iswapqa.dao.RuleFileRelDao.java
 * @version 1.0
 */
@Repository("ruleFileRelDao")
public class RuleFileRelDao extends EntityHibernateDao<RuleFileRel> {
	 /**
     * 查找规则文件关联
     * @return  List<RuleFileRel>
     * @author daic
     */
	@SuppressWarnings("unchecked")
	public List<RuleFileRel> getRuleFileRelsByRuleFileId(Long ruleFileId){
		String hql = "from RuleFileRel r where r.ruleFileId = ? ";
		List<RuleFileRel> ruleFileRelList = this.findListByHql(hql,-1, -1, new Long[]{ruleFileId});
		return ruleFileRelList;
	}
}