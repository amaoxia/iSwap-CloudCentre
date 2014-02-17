/*
 * @(#)RuleAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.iswapqa.action;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.jaxbutil.ObjectBindXmlImpl;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.iswapqa.plugin.parse.Field;
import com.ligitalsoft.iswapqa.plugin.parse.Method;
import com.ligitalsoft.iswapqa.plugin.parse.Object;
import com.ligitalsoft.iswapqa.plugin.parse.PluginInfo;
import com.ligitalsoft.iswapqa.service.IRuleFileService;
import com.ligitalsoft.iswapqa.service.IRuleService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.iswapqa.Rule;
import com.ligitalsoft.model.iswapqa.RuleFile;

/**
 * 规则Action
 * 
 * @author daic
 * @since 2011-07-05 15:29:35
 * @name com.ligitalsoft.iswapqa.action.RuleAction.java
 * @version 1.0
 */
@Namespace("/iswapqa/rule")
@Results( {
		@Result(name = "auditRulesWarehouse", location = "auditRulesWarehouse.ftl", type = "freemarker"),
		@Result(name = "addRulesShow1", location = "addRules1.ftl", type = "freemarker"),
		@Result(name = "addRulesShow2", location = "addRules2.ftl", type = "freemarker"),
		@Result(name = "addRuleShow", location = "listRule.ftl", type = "freemarker"),
		@Result(name = "editRule", location = "editRule.ftl", type = "freemarker"),
		@Result(name = "rulesList", location = "ruleFileAction!list.action", type = "redirect"),
		@Result(name = "editRulesShow1", location = "editRules1.ftl", type = "freemarker"),
		@Result(name = "editRulesShow2", location = "editRules2.ftl", type = "freemarker"),
		@Result(name = StrutsAction.VIEW, location = "view.ftl", type = "freemarker"),
		@Result(name = StrutsAction.UPDATEVIEW, location = "dept!view.action", type = "redirect", params = {
				"id", "${id}", "flag", "${flag}" }) })
@Action("ruleAction")
public class RuleAction extends FreemarkerBaseAction<Rule> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IRuleService ruleService;
	private IRuleFileService ruleFileService;

	private AppMsgService appMsgService;

	private String ruleName; // 规则名称
	private String ruleObj; // 规则对象
	private String[] attributeName; // 规则对象属性name
	private String[] cnName; // 规则对象属性name
	private String[] attributeType; // 规则对象属性对象类型
	private String[] attributeValue; // 规则对象属性对象值
	private Map<String, String> attributeMap= new HashMap<String, String>();// 规则对象Map
	private Map<String, String> whenMap = new HashMap<String, String>(); // when
	// Map

	private String salience;
	private String salienceVal;
	private String lock_on_active;
	private String lock_on_activeVal;
	private String ruleflow_group;
	private String ruleflow_groupVal;

	private String whenMapKey; // when的Map
	// key
	private String whenMapVal; // when的Map
	// Val
	private String thenMapVal;
	private String paramTypeVal; // 参数信息

	private String ruledes; // 规则描述
	private String funcName; // 方法名
	private String funcBody; // 方法体
	private String fileName; // 规则文件名称
	private String ruleId; // 规则Id
	private String ruleFileId; // 规则文件Id
	private String applyTo; // 所属应用
	private RuleFile ruleFile;
	private Rule rule;
	private String ruleAttribute;
	private List<AppMsg> appMsgList;
	private String[] paramTypeValArr;
	private List<com.ligitalsoft.iswapqa.plugin.parse.Object> objList; // 对象list
	private com.ligitalsoft.iswapqa.plugin.parse.Object ruleObject;
	private List<Method> methodList;
	private String thenVal;
	private String[] imports;
	private String ruleFileStr;
	@Override
	public String list() {
		return super.list();
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncBody() {
		return funcBody;
	}

	public void setFuncBody(String funcBody) {
		this.funcBody = funcBody;
	}

	@Autowired
	public void setRuleService(IRuleService ruleService) {
		this.ruleService = ruleService;
	}

	@Override
	protected IBaseServices<Rule> getEntityService() {
		return ruleService;
	}
	/**
	 *得到rule对象的属性值
	 * 
	 * @author daic
	 */
	public void getRuleObjAttr() {
	    String path = RuleAction.class.getResource("/").getPath();
        File file = new File(path + "/pluginConfig.xml");
        PrintWriter out = null;
        String ruleObjAttr="";
		try {
			this.getHttpServletResponse().setContentType("text/html");
    		this.getHttpServletResponse().setCharacterEncoding("GBK");
			ObjectBindXmlImpl objXml = new ObjectBindXmlImpl();
			PluginInfo pulPluginInfo = (PluginInfo) objXml.xmlToObj(file,
					"com.ligitalsoft.iswapqa.plugin.parse");
			objList = pulPluginInfo.getObject();
			out = this.getHttpServletResponse().getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Object obj : objList) {
			if (obj.getEnName().equals(ruleObj)) {
				ruleObject = obj;
				methodList = ruleObject.getMethods().getMethod();
			}
		}
		if(ruleObject!=null){
			List<Field> fieldList =ruleObject.getFields().getField();
			for(Field field:fieldList){
				ruleObjAttr=ruleObjAttr+field.getEnName()+"|"+field.getCnName()+"|"+field.getType()+";";
			}
		}
		out.println(ruleObjAttr);
	}
	/**
	 * 规则审计库列表显示页面
	 * 
	 * @author daic
	 */
	public String auditRulesWarehouse() {
		return "auditRulesWarehouse";
	}

	/**
	 * 跳转添加规则1页面
	 * 
	 * @author daic
	 */
	public String addRulesShow1() {
		String path = RuleAction.class.getResource("/").getPath();
		File file = new File(path + "/pluginConfig.xml");
		try {
			ObjectBindXmlImpl objXml = new ObjectBindXmlImpl();
			PluginInfo pulPluginInfo = (PluginInfo) objXml.xmlToObj(file,
					"com.ligitalsoft.iswapqa.plugin.parse");
			objList = pulPluginInfo.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		appMsgList = appMsgService.findAll();
		return "addRulesShow1";
	}
	/**
	 * 跳转添加其他规则页面
	 * 
	 * @author daic
	 */
	@SuppressWarnings("unchecked")
	public String addRuleShow() {
		HttpServletRequest request= ServletActionContext.getRequest();
	    HttpSession session = request.getSession();
	    attributeMap = (Map<String, String>) session.getAttribute("attributeMap");
	    attributeName =  (String[]) session.getAttribute("attributeName");
	    attributeType = (String[]) session.getAttribute("attributeType");
	    cnName = (String[]) session.getAttribute("cnName");
	    ruleObj =  (String) session.getAttribute("ruleObj");
		attributeMap = new TreeMap<String, String>();
		String path = RuleAction.class.getResource("/").getPath();
		File file = new File(path + "/pluginConfig.xml");
		try {
			ObjectBindXmlImpl objXml = new ObjectBindXmlImpl();
			PluginInfo pulPluginInfo = (PluginInfo) objXml.xmlToObj(file,
					"com.ligitalsoft.iswapqa.plugin.parse");
			objList = pulPluginInfo.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Object obj : objList) {
			if (obj.getEnName().equals(ruleObj)) {
				ruleObject = obj;
				methodList = ruleObject.getMethods().getMethod();
			}
		}
		
		for (int i = 0; i < attributeName.length; i++) {
			if (attributeName[i] != null && !attributeName[i].equals("")
					&& attributeType[i] != null && !attributeType[i].equals("")) {
				attributeMap.put(attributeName[i]+"|"+cnName[i], attributeType[i]);
			}
		}
		
		return "addRuleShow";
	}
	/**
	 * 保存单个规则
	 * 
	 * @author daic
	 */
	public void addRule() {
		Map<String, String> attribute = new HashMap<String, String>();
		Map<String, String> whens = new HashMap<String, String>();
		Map<String, String> thens = new HashMap<String, String>();
		// 设置规则属性Map
		
		try {
			paramTypeVal= java.net.URLDecoder.decode(paramTypeVal, "utf-8");
			attribute.put(java.net.URLDecoder.decode(salience, "utf-8"), java.net.URLDecoder.decode(salienceVal, "utf-8"));
			whens.put(java.net.URLDecoder.decode(whenMapKey, "utf-8"), java.net.URLDecoder.decode(whenMapVal, "utf-8"));
			thens.put("then", java.net.URLDecoder.decode(thenMapVal, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		ruleService.saveRule("rule2", attribute, whens, thens, paramTypeVal, ruleId);
	}
	/**
	 * 跳转添加规则2页面
	 * 
	 * @author daic
	 */
	@SuppressWarnings("deprecation")
	public String addRulesShow2() {
		String path = RuleAction.class.getResource("/").getPath();
		File file = new File(path + "/pluginConfig.xml");
		try {
			ObjectBindXmlImpl objXml = new ObjectBindXmlImpl();
			PluginInfo pulPluginInfo = (PluginInfo) objXml.xmlToObj(file,
					"com.ligitalsoft.iswapqa.plugin.parse");
			objList = pulPluginInfo.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Object obj : objList) {
			if (obj.getEnName().equals(ruleObj)) {
				ruleObject = obj;
				methodList = ruleObject.getMethods().getMethod();
			}
		}
		attributeMap = new TreeMap<String, String>();
		for (int i = 0; i < attributeName.length; i++) {
			if (attributeName[i] != null && !attributeName[i].equals("")
					&& attributeType[i] != null && !attributeType[i].equals("")) {
				attributeMap.put(attributeName[i]+"|"+cnName[i], attributeType[i]);
			}
		}
		    HttpServletRequest request= ServletActionContext.getRequest();
		    HttpSession session = request.getSession();
	        session.putValue("attributeMap", attributeMap);
	        session.putValue("attributeName", attributeName);
	        session.putValue("attributeType", attributeType);
	        session.putValue("cnName", cnName);
	        session.putValue("ruleObj", ruleObj);
		return "addRulesShow2";
	}

	/**
	 * 保存规则
	 * 
	 * @author daic
	 */
	public String addRules() {
		Map<String, String> attribute = new HashMap<String, String>();
		Map<String, String> whens = new HashMap<String, String>();
		Map<String, String> thens = new HashMap<String, String>();
		if(ruleFileStr!=null&&!ruleFileStr.equals("")){
			ruleName=ruleFileStr.split("rule")[1].split("'")[1].trim();
			attribute.put("salience", ruleFileStr.split("salience")[1].split(" ")[1].trim());
			whens.put(ruleFileStr.split("\\$")[1].split(":")[0].trim(), ruleFileStr.split("\\(")[1].split("\\)")[0].trim());
			thens.put("then", ruleFileStr.split("then")[1].replace("end", "").trim());
			imports = ruleFileStr.split("import")[1].split("rule")[0].split(";");
			String[] parmArr=ruleFileStr.split("\\(")[1].split("\\)")[0].replace("'", "").split(",");
			String[] paramTypeValArr = paramTypeVal.split(";");
			String paramTypeVal1="";
			for(int i=0;i<parmArr.length;i++){
				String type=paramTypeValArr[i].split("\\|")[1];
				String cnname="";
				if(type!=null&&(type.equals("number")||type.equals("date"))){
					cnname="||||"+paramTypeValArr[i].split("\\|")[7];
				}else{
					cnname="|"+paramTypeValArr[i].split("\\|")[3];
				}
				if (parmArr[i].contains("==")) {
					paramTypeVal1=paramTypeVal1+parmArr[i].split("==")[0]+"|"+type+"|"+parmArr[i].split("==")[1]+cnname;
				} else if (parmArr[i].contains("=")) {
					paramTypeVal1=paramTypeVal1+parmArr[i].split("=")[0]+"|"+type+"|"+parmArr[i].split("=")[1]+cnname;
				} else if (parmArr[i].contains(">")) {
					paramTypeVal1=paramTypeVal1+parmArr[i].split(">")[0]+"|"+type+"|"+">|"+parmArr[i].split(">")[1]+cnname;
				} else if (parmArr[i].contains("<")) {
					paramTypeVal1=paramTypeVal1+parmArr[i].split("<")[0]+"|"+type+"|"+"<|"+parmArr[i].split("<")[1]+cnname;
				} else if (parmArr[i].contains("=<")) {
					paramTypeVal1=paramTypeVal1+parmArr[i].split("=<")[0]+"|"+type+"|"+"=<|"+parmArr[i].split("=<")[1]+cnname;
				} else if (parmArr[i].contains(">=")) {
					paramTypeVal1=paramTypeVal1+parmArr[i].split(">=")[0]+"|"+type+"|"+">=|"+parmArr[i].split(">=")[1]+cnname;
				}
				paramTypeVal1=paramTypeVal1+";";
			}
			paramTypeVal=paramTypeVal1;
		}else{
			// 设置规则属性Map
			attribute.put(salience, salienceVal);
			whens.put(whenMapKey, whenMapVal);
			thens.put("then", thenMapVal);
		}
		ruleService.saveRules(ruleName, attribute, whens, thens, funcName,
				funcBody, fileName, applyTo, paramTypeVal, ruleId, ruleFileId,imports);
		return "rulesList";
	}
	/**
	 * 跳转编辑规则1页面
	 * 
	 * @author daic
	 */
	public String editRulesShow1() {
	    String path = RuleAction.class.getResource("/").getPath();
        File file = new File(path + "/pluginConfig.xml");
		try {
			ObjectBindXmlImpl objXml = new ObjectBindXmlImpl();
			PluginInfo pulPluginInfo = (PluginInfo) objXml.xmlToObj(file,
					"com.ligitalsoft.iswapqa.plugin.parse");
			objList = pulPluginInfo.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		appMsgList = appMsgService.findAll();
		try {
			ruleFile = ruleFileService.findById(Long.valueOf(ruleFileId));
			rule = ruleService.getRulesByRuleId(Long.valueOf(ruleFileId))
					.get(0);
			ruleObj = rule.getWhenStr().substring(
					rule.getWhenStr().indexOf("$") + 1,
					rule.getWhenStr().indexOf(":")).trim();
			String whenStr = rule.getWhenStr();
			String[] whenAttrVal = whenStr.split("\\(")[1].replace(")", "")
					.split(",");
			for (String str : whenAttrVal) {
				if (str.contains("==")) {
					whenMap.put(str.split("==")[0], str.split("==")[1]);
				} else if (str.contains("=")) {
					whenMap.put(str.split("=")[0], str.split("=")[1]);
				} else if (str.contains(">")) {
					whenMap.put(str.split(">")[0], str.split(">")[1]);
				} else if (str.contains("<")) {
					whenMap.put(str.split("<")[0], str.split("<")[1]);
				} else if (str.contains("=<")) {
					whenMap.put(str.split("=<")[0], str.split("=<")[1]);
				} else if (str.contains(">=")) {
					whenMap.put(str.split(">=")[0], str.split(">=")[1]);
				}
			}
			if(rule.getParamTypeVal()!=null&&rule.getParamTypeVal().contains(";")){
				paramTypeValArr = rule.getParamTypeVal().split(";");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "editRulesShow1";
	}

	public String getParamTypeVal() {
		return paramTypeVal;
	}

	public String[] getParamTypeValArr() {
		return paramTypeValArr;
	}

	/**
	 * 跳转添加规则2页面
	 * 
	 * @author daic
	 */
	@SuppressWarnings("deprecation")
	public String editRulesShow2() {
	    String path = RuleAction.class.getResource("/").getPath();
        File file = new File(path + "/pluginConfig.xml");
		try {
			ObjectBindXmlImpl objXml = new ObjectBindXmlImpl();
			PluginInfo pulPluginInfo = (PluginInfo) objXml.xmlToObj(file,
					"com.ligitalsoft.iswapqa.plugin.parse");
			objList = pulPluginInfo.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Object obj : objList) {
			if (obj.getEnName().equals(ruleObj)) {
				ruleObject = obj;
				methodList = ruleObject.getMethods().getMethod();
			}
		}
		attributeMap = new TreeMap<String, String>();
		for (int i = 0; i < attributeName.length; i++) {
			if (attributeName[i] != null && !attributeName[i].equals("")
					&& attributeType[i] != null && !attributeType[i].equals("")) {
				attributeMap.put(attributeName[i]+"|"+cnName[i], attributeType[i]);
			}
		}
		try {
			ruleFile = ruleFileService.findById(Long.valueOf(ruleFileId));
			if(!ruleFile.getRulesStr().contains("\n")){
			ruleFileStr = ruleFile.getRulesStr().replace(" ", "\n").replace(";", ";\n").replace("rule\n", "rule ")
					.replace("package\n", "package ").replace("import\n", "import ").replace("salience\n", "salience ");
			}else {
				ruleFileStr = ruleFile.getRulesStr();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rule = ruleService.getRulesByRuleId(Long.valueOf(ruleFileId)).get(0);
		ruleObj = rule.getWhenStr().substring(
				rule.getWhenStr().indexOf("$") + 1,
				rule.getWhenStr().indexOf(":")).trim();
		ruleAttribute = rule.getAttribute();
		paramTypeValArr = rule.getParamTypeVal().split(";");
		thenMapVal = rule.getThenStr().split("\\(")[0].split("\\.")[1].trim();
		thenVal = rule.getThenStr().split("'")[1].trim();
		   HttpServletRequest request= ServletActionContext.getRequest();
		    HttpSession session = request.getSession();
	        session.putValue("attributeMap", attributeMap);
	        session.putValue("attributeName", attributeName);
	        session.putValue("attributeType", attributeType);
	        session.putValue("cnName", cnName);
	        session.putValue("ruleObj", ruleObj);
	        session.putValue("ruleFileId", ruleFileId);
		return "editRulesShow2";
	}
	/**
	 * 跳转添加规则2页面
	 * 
	 * @author daic
	 */
	@SuppressWarnings("unchecked")
	public String editRuleShow() {
		 HttpServletRequest request= ServletActionContext.getRequest();
		 HttpSession session = request.getSession();
		  	attributeMap = (Map<String, String>) session.getAttribute("attributeMap");
		    attributeName =  (String[]) session.getAttribute("attributeName");
		    attributeType = (String[]) session.getAttribute("attributeType");
		    cnName = (String[]) session.getAttribute("cnName");
		    ruleObj =  (String) session.getAttribute("ruleObj");
		    ruleFileId =  (String) session.getAttribute("ruleFileId");
	    String path = RuleAction.class.getResource("/").getPath();
        File file = new File(path + "/pluginConfig.xml");
		try {
			ObjectBindXmlImpl objXml = new ObjectBindXmlImpl();
			PluginInfo pulPluginInfo = (PluginInfo) objXml.xmlToObj(file,
					"com.ligitalsoft.iswapqa.plugin.parse");
			objList = pulPluginInfo.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Object obj : objList) {
			if (obj.getEnName().equals(ruleObj)) {
				ruleObject = obj;
				methodList = ruleObject.getMethods().getMethod();
			}
		}
		attributeMap = new TreeMap<String, String>();
		for (int i = 0; i < attributeName.length; i++) {
			if (attributeName[i] != null && !attributeName[i].equals("")
					&& attributeType[i] != null && !attributeType[i].equals("")) {
				attributeMap.put(attributeName[i]+"|"+cnName[i], attributeType[i]);
			}
		}
		try {
			ruleFile = ruleFileService.findById(Long.valueOf(ruleFileId));
			if(!ruleFile.getRulesStr().contains("\n")){
			ruleFileStr = ruleFile.getRulesStr().replace(" ", "\n").replace(";", ";\n").replace("rule\n", "rule ")
					.replace("package\n", "package ").replace("import\n", "import ").replace("salience\n", "salience ");
			}else {
				ruleFileStr = ruleFile.getRulesStr();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rule = ruleService.getRulesByRuleId(Long.valueOf(ruleFileId)).get(0);
		ruleObj = rule.getWhenStr().substring(
				rule.getWhenStr().indexOf("$") + 1,
				rule.getWhenStr().indexOf(":")).trim();
		ruleAttribute = rule.getAttribute();
		paramTypeValArr = rule.getParamTypeVal().split(";");
		thenMapVal = rule.getThenStr().split("\\(")[0].split("\\.")[1].trim();
		thenVal = rule.getThenStr().split("'")[1].trim();
		return "editRule";
	}
	
	@Override
	public String delete() {
		return super.delete();
	}

	public RuleFile getRuleFile() {
		return ruleFile;
	}

	public void setRuleFile(RuleFile ruleFile) {
		this.ruleFile = ruleFile;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getFileName() {
		return fileName;
	}

	public IRuleFileService getRuleFileService() {
		return ruleFileService;
	}

	public void setRuleFileService(IRuleFileService ruleFileService) {
		this.ruleFileService = ruleFileService;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFuncName() {
		return funcName;
	}

	public String getRuleFileId() {
		return ruleFileId;
	}

	public void setRuleFileId(String ruleFileId) {
		this.ruleFileId = ruleFileId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleObj() {
		return ruleObj;
	}

	public void setRuleObj(String ruleObj) {
		this.ruleObj = ruleObj;
	}

	public String[] getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String[] attributeName) {
		this.attributeName = attributeName;
	}

	public String[] getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String[] attributeValue) {
		this.attributeValue = attributeValue;
	}

	public IRuleService getRuleService() {
		return ruleService;
	}

	public void setParamTypeValArr(String[] paramTypeValArr) {
		this.paramTypeValArr = paramTypeValArr;
	}

	public String getRuledes() {
		return ruledes;
	}

	public void setRuledes(String ruledes) {
		this.ruledes = ruledes;
	}

	public String getWhenMapKey() {
		return whenMapKey;
	}

	public void setWhenMapKey(String whenMapKey) {
		this.whenMapKey = whenMapKey;
	}

	public String getWhenMapVal() {
		return whenMapVal;
	}

	public void setWhenMapVal(String whenMapVal) {
		this.whenMapVal = whenMapVal;
	}

	public String getThenMapVal() {
		return thenMapVal;
	}

	public void setThenMapVal(String thenMapVal) {
		this.thenMapVal = thenMapVal;
	}

	public String getRuleflow_group() {
		return ruleflow_group;
	}

	public void setRuleflow_group(String ruleflowGroup) {
		ruleflow_group = ruleflowGroup;
	}

	public List<Method> getMethodList() {
		return methodList;
	}

	public void setMethodList(List<Method> methodList) {
		this.methodList = methodList;
	}

	public String getSalience() {
		return salience;
	}

	public void setSalience(String salience) {
		this.salience = salience;
	}

	public String getSalienceVal() {
		return salienceVal;
	}

	public void setSalienceVal(String salienceVal) {
		this.salienceVal = salienceVal;
	}

	public String getLock_on_active() {
		return lock_on_active;
	}

	public void setLock_on_active(String lockOnActive) {
		lock_on_active = lockOnActive;
	}

	public String getLock_on_activeVal() {
		return lock_on_activeVal;
	}

	public void setLock_on_activeVal(String lockOnActiveVal) {
		lock_on_activeVal = lockOnActiveVal;
	}

	public String getRuleflow_groupVal() {
		return ruleflow_groupVal;
	}

	public void setRuleflow_groupVal(String ruleflowGroupVal) {
		ruleflow_groupVal = ruleflowGroupVal;
	}

	public Map<String, String> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Map<String, String> attributeMap) {
		this.attributeMap = attributeMap;
	}

	public String[] getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String[] attributeType) {
		this.attributeType = attributeType;
	}

	public String getApplyTo() {
		return applyTo;
	}

	public void setApplyTo(String applyTo) {
		this.applyTo = applyTo;
	}

	public Map<String, String> getWhenMap() {
		return whenMap;
	}

	public void setWhenMap(Map<String, String> whenMap) {
		this.whenMap = whenMap;
	}

	public void setParamTypeVal(String paramTypeVal) {
		this.paramTypeVal = paramTypeVal;
	}

	public String getRuleAttribute() {
		return ruleAttribute;
	}

	public void setRuleAttribute(String ruleAttribute) {
		this.ruleAttribute = ruleAttribute;
	}

	public AppMsgService getAppMsgService() {
		return appMsgService;
	}

	public void setAppMsgService(AppMsgService appMsgService) {
		this.appMsgService = appMsgService;
	}

	public List<AppMsg> getAppMsgList() {
		return appMsgList;
	}

	public void setAppMsgList(List<AppMsg> appMsgList) {
		this.appMsgList = appMsgList;
	}

	public List<com.ligitalsoft.iswapqa.plugin.parse.Object> getObjList() {
		return objList;
	}

	public void setObjList(
			List<com.ligitalsoft.iswapqa.plugin.parse.Object> objList) {
		this.objList = objList;
	}

	public com.ligitalsoft.iswapqa.plugin.parse.Object getRuleObject() {
		return ruleObject;
	}

	public void setRuleObject(
			com.ligitalsoft.iswapqa.plugin.parse.Object ruleObject) {
		this.ruleObject = ruleObject;
	}

	public String getThenVal() {
		return thenVal;
	}

	public void setThenVal(String thenVal) {
		this.thenVal = thenVal;
	}

	public String[] getImports() {
		return imports;
	}

	public void setImports(String[] imports) {
		this.imports = imports;
	}

	public String getRuleFileStr() {
		return ruleFileStr;
	}

	public void setRuleFileStr(String ruleFileStr) {
		this.ruleFileStr = ruleFileStr;
	}

	public String[] getCnName() {
		return cnName;
	}

	public void setCnName(String[] cnName) {
		this.cnName = cnName;
	}
	
	
	
}
