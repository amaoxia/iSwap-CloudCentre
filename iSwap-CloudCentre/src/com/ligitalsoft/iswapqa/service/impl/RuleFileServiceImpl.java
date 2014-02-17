package com.ligitalsoft.iswapqa.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.iswapqa.dao.FunctionDao;
import com.ligitalsoft.iswapqa.dao.FunctionFileRelDao;
import com.ligitalsoft.iswapqa.dao.RuleDao;
import com.ligitalsoft.iswapqa.dao.RuleFileDao;
import com.ligitalsoft.iswapqa.dao.RuleFileRelDao;
import com.ligitalsoft.iswapqa.service.IRuleFileService;
import com.ligitalsoft.model.iswapqa.Function;
import com.ligitalsoft.model.iswapqa.FunctionFileRel;
import com.ligitalsoft.model.iswapqa.Rule;
import com.ligitalsoft.model.iswapqa.RuleFile;
import com.ligitalsoft.model.iswapqa.RuleFileRel;

/**
 * 规则文件实现类
 * @author daic
 * @since 2011-07-21 17:27:48
 * @name ccom.ligitalsoft.iswapqa.service.impl.RuleFileServiceImpl.java
 * @version 1.0
 */
@Transactional
@Service(value = "ruleFileService")
public class RuleFileServiceImpl extends BaseSericesImpl<RuleFile> implements IRuleFileService {
	@Autowired
	private RuleFileDao ruleFileDao;
	@Autowired
	private RuleDao ruleDao;
	@Autowired
	private FunctionDao functionDao;
	@Autowired
	private FunctionFileRelDao functionFileRelDao;
	@Autowired
	private RuleFileRelDao ruleFileRelDao;
	@Override
	public EntityHibernateDao<RuleFile> getEntityDao() {
		return this.ruleFileDao;
	}
	@Override
	public void deleteAllByIds(Serializable[] ids) throws ServiceException {
		for(Serializable ruleFileId:ids){
			List<Rule> ruleList = ruleDao.getRulesByRuleId(Long.valueOf(ruleFileId.toString()));
			for(Rule rule :ruleList){
				ruleDao.remove(rule);
			}
			List<Function> funcList = functionDao.getFuncsByRuleFileId(Long.valueOf(ruleFileId.toString()));
			for(Function func:funcList){
				functionDao.remove(func);
			}
			List<FunctionFileRel> functionFileRelList = functionFileRelDao.getFuncsByRuleFileId(Long.valueOf(ruleFileId.toString()));
			for(FunctionFileRel functionFileRel:functionFileRelList){
				functionFileRelDao.remove(functionFileRel);
			}
			List<RuleFileRel> ruleFileRelList = ruleFileRelDao.getRuleFileRelsByRuleFileId(Long.valueOf(ruleFileId.toString()));
			for(RuleFileRel ruleFileRel:ruleFileRelList){
				ruleFileRelDao.remove(ruleFileRel);
			}
		}
		super.deleteAllByIds(ids);
	}

	
}