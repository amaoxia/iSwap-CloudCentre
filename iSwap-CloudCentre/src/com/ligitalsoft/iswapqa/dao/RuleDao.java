package com.ligitalsoft.iswapqa.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.iswapqa.Function;
import com.ligitalsoft.model.iswapqa.FunctionFileRel;
import com.ligitalsoft.model.iswapqa.Rule;
import com.ligitalsoft.model.iswapqa.RuleFile;
import com.ligitalsoft.model.iswapqa.RuleFileRel;

/**
 * 规则DAO层
 * @author daic
 * @since 2011-07-06 16:52:38
 * @name com.ligitalsoft.iswapqa.dao.dao.RuleDao.java
 * @version 1.0
 */
@Repository
public class RuleDao extends EntityHibernateDao<Rule> {
    /**
     * 保存Rules信息
     * @return
     * @author daic
     */
	public void saveOrUpdateRules(RuleFile ruleFile){
		this.powerHibernateDao.saveOrUpdate(ruleFile);
	}
    /**
     * 保存方法信息
     * @return
     * @author daic
     */
	public void saveOrUpdateFunc(Function function){
		this.powerHibernateDao.saveOrUpdate(function);
	}
	 /**
     * 查找规则文件中的规则
     * @return  List<Rule>
     * @author daic
     */
	@SuppressWarnings("unchecked")
	public List<Rule> getRulesByRuleId(Long ruleFileId){
		String hql = "select r.ruleId from RuleFileRel r where r.ruleFileId = ? ";
		List<Long> ruleIdList = this.findListByHql(hql,-1, -1, new Long[]{ruleFileId});
		List<Rule> list = new ArrayList<Rule>();
		for(Long ruleId : ruleIdList){
			list.add(this.findById(ruleId));
		}
		return list;
	}
	/**
     * 方法和规则文件关联信息
     * @return
     * @author daic
     */
	public void saveOrUpdateFunctionFileRel(FunctionFileRel functionFileRel){
		this.powerHibernateDao.saveOrUpdate(functionFileRel);
	}
	/**
     * 方法和规则文件关联信息
     * @return
     * @author daic
     */
	public void saveOrUpdateRuleFileRel(RuleFileRel ruleFileRel){
		this.powerHibernateDao.saveOrUpdate(ruleFileRel);
	}
	/**
     * 查询规则文件信息根据ID
     * @return
     * @author daic
     */
	public RuleFile getRuleFileById(Long id){
		String hql = "select r.id from RuleFile r where r.id = ? ";
		RuleFile ruleFile = (RuleFile) this.findUniqueByHql(hql,-1, -1, id);
		return ruleFile;
	}
}