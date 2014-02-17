package com.ligitalsoft.iswapqa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.iswapqa.dao.RuleDao;
import com.ligitalsoft.iswapqa.service.IRuleFileService;
import com.ligitalsoft.iswapqa.service.IRuleService;
import com.ligitalsoft.model.iswapqa.Function;
import com.ligitalsoft.model.iswapqa.FunctionFileRel;
import com.ligitalsoft.model.iswapqa.Rule;
import com.ligitalsoft.model.iswapqa.RuleFile;
import com.ligitalsoft.model.iswapqa.RuleFileRel;

/**
 * 规则实现类
 * @author daic
 * @since 2011-06-29 15:25:18
 * @name ccom.ligitalsoft.iswapqa.service.impl.RuleServiceImpl.java
 * @version 1.0
 */
@Service
public class RuleServiceImpl extends BaseSericesImpl<Rule> implements IRuleService {

    private RuleDao          ruleDao;

    private IRuleFileService ruleFileService;

    @Override
    public void delete(Rule entity) throws ServiceException {
        super.delete(entity);
    }

    @Override
    public void deleteById(Serializable id) throws ServiceException {
        super.deleteById(id);
    }

    @Override
    public void saveOrUpdate(Rule entity) throws ServiceException {
        super.saveOrUpdate(entity);
    }

    @Override
    public void update(Rule entity) throws ServiceException {
        super.update(entity);
    }

    @Override
    public Rule findById(Serializable id) throws ServiceException {
        return super.findById(id);
    }

    @Override
    public EntityHibernateDao<Rule> getEntityDao() {
        return ruleDao;
    }

    @Autowired
    public void setRuleDao(RuleDao ruleDao) {
        this.ruleDao = ruleDao;
    }

    public IRuleFileService getRuleFileService() {
        return ruleFileService;
    }

    @Autowired
    public void setRuleFileService(IRuleFileService ruleFileService) {
        this.ruleFileService = ruleFileService;
    }

    // 对funcation进行解析存入对象中
    public Function creatFunction(String funcName, String funcBody) {
        Function funcation = new Function();
        if ((funcName != null) && (funcBody != null) && (!funcName.equals("")) && (!funcBody.equals(""))) {
            funcName = funcName.trim();// 去掉首尾空格
            funcBody = funcBody.trim();

            funcation.setFuncName(funcName);
            funcation.setFuncBody(funcBody);
            Map<String, String> funcParam = new HashMap<String, String>();// 解析参数
            String paramStr = funcName.split("\\(")[1];
            paramStr = paramStr.replace(")", "");
            String[] paramArr = paramStr.split(",");
            for (String param : paramArr) {
                if (param != null && !param.equals("")) {
                    funcParam.put(param.split(" ")[1], param.split(" ")[0]);
                }
            }
            funcation.setFuncParam(funcParam);
            if (funcBody.contains("return")) {// 解析返回值
                String funcRetn = funcBody.split("return")[1].split(";")[0].replace(" ", "");
                funcation.setFuncRetn(funcRetn);
            }
            funcation.setFuncParam(funcParam);
        }
        return funcation;
    }

    // 生成function字符串
    public String getFunctionString(Function funcation) {
        String functionStr = null;
        functionStr = "function " + funcation.getFuncName() + "{" + funcation.getFuncBody() + "}";
        return functionStr;
    }

    // 生成rule语句字符串
    public String creatRuleString(String ruleName, Map<String, String> attribute, Map<String, String> whens,
                    Map<String, String> thens) {
        String ruleStr = null;
        ruleStr = "rule '" + ruleName + "' ";
        Iterator<String> it = attribute.keySet().iterator(); // 设置属性值
        String attr;
        String attrValue;
        while (it.hasNext()) {
            attr = (String) it.next();
            attrValue = (String) attribute.get(attr);
            if (attr != null && attrValue != null) {
                ruleStr = ruleStr + attr + " " + attrValue + " ";
            }
        }
        ruleStr = ruleStr + " when ";// 设置when值
        Iterator<String> itw = whens.keySet().iterator();
        String obj;
        String objValue;
        while (itw.hasNext()) {
            obj = (String) itw.next();
            objValue = (String) whens.get(obj);
            ruleStr = ruleStr + "$" + obj + ":" + obj + "(" + objValue + ");";
        }
        ruleStr = ruleStr + " then " + thens.get("then");// 设置then值
        ruleStr = ruleStr + " end ";
        return ruleStr;
    }

    // 解析生成Rule对象
    public Rule creatRule(String ruleName, String attribute, String whenStr, String thenStr) {
        Rule rule = new Rule();
        rule.setRuleName(ruleName);
        rule.setAttribute(attribute);
        // 设置when条件
        Map<String, String> whens = new HashMap<String, String>();
        String wkey = whenStr.split(":")[0];
        String wvalue = whenStr.split("\\(")[1].replace(")", "").replace(";", "");
        whens.put(wkey, wvalue);
        rule.setWhens(whens);
        // 设置then条件
        Map<String, String> thens = new HashMap<String, String>();
        thens.put("then", thenStr);
        rule.setThens(thens);
        return rule;
    }

    public static void main(String[] args) {
        RuleServiceImpl r = new RuleServiceImpl();
        Function f = r.creatFunction("  setName(String id,String rule,Object obj)  ",
                        "  Rule rule = new Rule();return rule;  ");
        String fstr = r.getFunctionString(f);
        System.out.println(fstr);
        String ruleName = "规则1";
        Map<String, String> attribute = new HashMap<String, String>();
        attribute.put("salience", "1");
        attribute.put("lock-on-active", "true");
        attribute.put("ruleflow-group", "'creditcardpayment'");
        Map<String, String> whens = new HashMap<String, String>();
        whens.put("UserInfo",
                        " ((hasHouse==true && hasCar==false) || (hasHouse==false && hasCar==true)) && salary<=20000 && salary>=10000");
        Map<String, String> thens = new HashMap<String, String>();
        thens.put("then", "update($user);");
        String rule = r.creatRuleString(ruleName, attribute, whens, thens);
        System.out.println(rule);
    }
    @SuppressWarnings("unchecked")
	@Override
    public void saveRules(String ruleName, Map<String, String> attribute, Map<String, String> whens,
                    Map<String, String> thens, String funcName, String funcBody, String fileName, String applyTo,
                    String paramTypeVal, String ruleId, String ruleFileId, String[] imports) {
        // 保存方法
        Function function = this.creatFunction(funcName, funcBody);
        ruleDao.saveOrUpdateFunc(function);

        // 保存规则文件信息
        RuleFile ruleFile = new RuleFile();
        if (ruleFileId != null && !ruleFileId.equals("")) {
            ruleFile.setId(Long.valueOf(ruleFileId));
        }
        ruleFile.setFileName(fileName);
        ruleFile.setPackageName("test");
        String importss = "";
        for (String imp : imports) {
            if (imp != null && !imp.equals("")) {
                if (imp.contains(";")) {
                    importss = importss + imp + "";
                }
                else {
                    importss = importss + imp + ";";
                }
            }
        }
        ruleFile.setImports(importss);
        ruleFile.setGlobals("java.util.List list;");
        ruleFile.setQueries("");
        // List<Function> funcList = new ArrayList<Function>();
        // funcList.add(function);
        // ruleFile.setFuncList(funcList);
        String rulesStr = "";
        ruleFile.setRulesStr(rulesStr);
        ruleFile.setApplyTo(applyTo);
        ruleFile.setCreater("admin");
        ruleFile.setCreationTime(new Date());
        ruleDao.saveOrUpdateRules(ruleFile);

        // 保存方法和文件关联信息
        if (ruleFileId != null && !ruleFileId.equals("")) {
        }
        else {
            FunctionFileRel functionFileRel = new FunctionFileRel();
            functionFileRel.setFuncId(function.getId());
            functionFileRel.setRuleFileId(ruleFile.getId());
            ruleDao.saveOrUpdateFunctionFileRel(functionFileRel);
        }

        // 保存规则信息
        String ruleStr = this.creatRuleString(ruleName, attribute, whens, thens);
        Rule rule = new Rule();
        if (ruleId != null && !ruleId.equals("")) {
            rule.setId(Long.valueOf(ruleId));
        }
        rule.setRuleStr(ruleStr);
        rule.setRuleName(ruleName);
        rule.setParamTypeVal(paramTypeVal);
        Iterator<String> it = attribute.keySet().iterator(); // 设置属性值
        String attrValue = "";
        while (it.hasNext()) {
            String attr = (String) it.next();
            String attrVal = (String) attribute.get(attr);
            if (attr != null && attrValue != null) {
                attrValue = attrValue + attr + " " + attrVal + " ";
            }
        }
        rule.setAttribute(attrValue);
        String whenStr = "";
        whenStr = whenStr + " when ";// 设置when值
        Iterator<String> itw = whens.keySet().iterator();
        String obj = "";
        String objValue = "";
        while (itw.hasNext()) {
            obj = (String) itw.next();
            objValue = (String) whens.get(obj);
            whenStr = whenStr + "$" + obj + ":" + obj + "(" + objValue + ");";
        }
        rule.setWhenStr(whenStr);
        String thenStr = "";
        thenStr = thenStr + " then " + thens.get("then");// 设置then值
        thenStr = thenStr + " end ";
        rule.setThenStr(thenStr);
        // 保存Rule对象
        try {
            this.saveOrUpdate(rule);
        }
        catch (ServiceException e) {
            e.printStackTrace();
        }

        // 保存规则和文件关联信息
        if (ruleFileId != null && !ruleFileId.equals("")) {
        
 
        }else {
            HttpServletRequest request= ServletActionContext.getRequest();
    	    HttpSession session = request.getSession();
            List<Long> ruleIdList = (List<Long>) session.getAttribute("ruleIdList");
            if(ruleIdList!=null){
            	for(Long ruleid :ruleIdList){
            		 RuleFileRel ruleFilerel = new RuleFileRel();
            	     ruleFilerel.setRuleFileId(ruleFile.getId());
            	     ruleFilerel.setRuleId(ruleid);
            	     ruleDao.saveOrUpdateRuleFileRel(ruleFilerel);
            	}
            }
            RuleFileRel ruleFileRel = new RuleFileRel();
            ruleFileRel.setRuleFileId(ruleFile.getId());
            ruleFileRel.setRuleId(rule.getId());
            ruleDao.saveOrUpdateRuleFileRel(ruleFileRel);
        }

        rulesStr = "package " + ruleFile.getPackageName() + " import " + ruleFile.getImports() + " global " + ruleFile.getGlobals();
        List<Rule> ruleList = ruleDao.getRulesByRuleId(ruleFile.getId());
        for (Rule rule1 : ruleList) {
            rulesStr = rulesStr + rule1.getRuleStr() + " ";
        }

        ruleFile.setRulesStr(rulesStr);
        ruleDao.saveOrUpdateRules(ruleFile);

    }
    @SuppressWarnings("deprecation")
	@Override
    public void saveRule(String ruleName, Map<String, String> attribute, Map<String, String> whens,
            Map<String, String> thens,String paramTypeVal,String ruleId) {
		
		// 保存规则信息
		String ruleStr = this.creatRuleString(ruleName, attribute, whens, thens);
		Rule rule = new Rule();
		if(ruleId!=null&&!ruleId.equals(""))
		{
			rule.setId(Long.valueOf(ruleId));
		}
		rule.setRuleStr(ruleStr);
		rule.setRuleName(ruleName);
		rule.setParamTypeVal(paramTypeVal);
		Iterator<String> it = attribute.keySet().iterator(); // 设置属性值
		String attrValue = "";
		while (it.hasNext()) {
		    String attr = (String) it.next();
		    String attrVal = (String) attribute.get(attr);
		    if (attr != null && attrValue != null) {
		        attrValue = attrValue + attr + " " + attrVal + " ";
		    }
		}
		rule.setAttribute(attrValue);
		String whenStr = "";
		whenStr = whenStr + " when ";// 设置when值
		Iterator<String> itw = whens.keySet().iterator();
		String obj = "";
		String objValue = "";
		while (itw.hasNext()) {
		    obj = (String) itw.next();
		    objValue = (String) whens.get(obj);
		    whenStr = whenStr + "$" + obj + ":" + obj + "(" + objValue + ");";
		}
		rule.setWhenStr(whenStr);
		String thenStr = "";
		thenStr = thenStr + " then " + thens.get("then");// 设置then值
		thenStr = thenStr + "list.add('测试成功') end ";
		rule.setThenStr(thenStr);
		// 保存Rule对象
		try {
		    this.saveOrUpdate(rule);
		}
		catch (ServiceException e) {
		    e.printStackTrace();
		}
		HttpServletRequest request= ServletActionContext.getRequest();
	    HttpSession session = request.getSession();
	    List<Long> ruleIdList = new ArrayList<Long>();
	    ruleIdList.add(rule.getId());
        session.putValue("ruleIdList", ruleIdList);
}


    @Override
    public RuleFile getRuleFileById(Long id) {
        RuleFile ruleFile = ruleDao.getRuleFileById(id);
        return ruleFile;
    }

    @Override
    public List<Rule> getRulesByRuleId(Long ruleFileId) {
        List<Rule> ruleList = ruleDao.getRulesByRuleId(ruleFileId);
        return ruleList;
    }

    @Override
    public void execute(String ruleFileId, Object obj) {

        RuleFile ruleFile = null;

        try {
            ruleFile = ruleFileService.findById(Long.valueOf(ruleFileId));
        }
        catch (NumberFormatException e) {
            e.getLocalizedMessage();
        }
        catch (ServiceException e) {
            e.getLocalizedMessage();
        }

        String ruleStr = ruleFile.getRulesStr().replace("'", "\"");

        KnowledgeBase kbase = readKnowledgeBase(ruleStr.getBytes());

        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");

        List<String> list = new ArrayList<String>();

        ksession.setGlobal("list", list);

        ksession.insert(obj);

        ksession.fireAllRules();
        if (list.size() > 0) {
            System.out.println(list.get(0));
        }
        else {
            System.out.println("+++++");
        }

        logger.close();

    }

    @Override
    public KnowledgeBase readKnowledgeBase(byte[] bytes) {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newByteArrayResource(bytes), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error : errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("无法解析规则文件");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }

    @Override
    public Rule produceRule(String ruleName, String attribute, String whenStr, String thenStr) {
        return this.creatRule(ruleName, attribute, whenStr, thenStr);
    }
}