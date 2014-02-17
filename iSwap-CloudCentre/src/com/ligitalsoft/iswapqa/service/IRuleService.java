package com.ligitalsoft.iswapqa.service;

import java.util.List;
import java.util.Map;

import org.drools.KnowledgeBase;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.iswapqa.Rule;
import com.ligitalsoft.model.iswapqa.RuleFile;

/**
 * 规则SERVICE
 * @author daic
 * @since 2011-06-29 14:06:31
 * @name com.ligitalsoft.iswapqa.service.IRuleService.java
 * @version 1.0
 */
public interface IRuleService extends IBaseServices<Rule> {

    /**
     * 保存规则文件信息
     * @param ruleName
     *            attribute whens thens
     * @author daicheng
     */
    public void saveRules(String ruleName, Map<String, String> attribute, Map<String, String> whens,
                    Map<String, String> thens, String funcName, String funcBody, String fileName, String applyTo,
                    String paramTypeVal, String ruleId, String ruleFileId, String[] imports);
    /**
     * 保存单个规则
     * @param ruleName attribute whens thens
     * @author daicheng
     */
    public void saveRule(String ruleName, Map<String, String> attribute, Map<String, String> whens,
            Map<String, String> thens,String paramTypeVal,String ruleId);
    /**
     * 查询规则文件信息 通过id
     * @param id
     * @author daicheng
     */
    public RuleFile getRuleFileById(Long id);
    /**
     * 查询规则 通过id
     * @param id
     * @author daicheng
     */
    public List<Rule> getRulesByRuleId(Long ruleFileId);

    /**
     * 执行指定的规则文件
     * @param ruleName
     */
    public void execute(String ruleFileId, Object obj);

    /**
     * 将指定的规则文件，加载到规则库
     * @param ruleName
     * @return
     */
    public KnowledgeBase readKnowledgeBase(byte[] bytes);
    /**
     * 生成Rule对象
     * @param String
     *            ruleName,String attribute,String whenStr,String thenStr
     * @author daicheng
     */
    public Rule produceRule(String ruleName, String attribute, String whenStr, String thenStr);
}