package com.ligitalsoft.workflow.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.ehcache.Cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ManagementService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.springframework.stereotype.Component;

import com.common.cachetool.CacheTool;
import com.ligitalsoft.help.ISwapESBStart;
import com.ligitalsoft.workflow.IWorkFlowManager;
import com.ligitalsoft.workflow.exception.ProcessException;

/**
 * 对工作流的操作
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-7 下午02:07:08
 *@Team 研发中心
 */
@Component("workFlowManager")
public class WorkFlowManagerImpl implements IWorkFlowManager {
	  private final Log log = LogFactory.getLog(this.getClass());
	  private ISwapESBStart iSwapESBStart = ISwapESBStart.init();
	  protected ProcessEngine processEngine;
	  protected RepositoryService repositoryService;
	  protected ExecutionService executionService;
	  protected ManagementService managementService;
	  protected TaskService taskService;
	  protected HistoryService historyService;
	  protected IdentityService identityService;
	  
	@SuppressWarnings("static-access")
	@Override
	public boolean initWorkFlowEngine() throws ProcessException {
	    boolean flag = true;
	    try{
	    	log.info("开始初始化流程引擎......");
	     	processEngine = Configuration.getProcessEngine();
	     	iSwapESBStart.processEngine = processEngine;
	    	repositoryService = processEngine.getRepositoryService();
			executionService = processEngine.getExecutionService();
			historyService = processEngine.getHistoryService();
			managementService = processEngine.getManagementService();
			taskService = processEngine.getTaskService();
			identityService = processEngine.getIdentityService();
			log.info("流程引擎初始化成功.");
	    }catch(Exception e){
	    	flag = false;
	    	log.error("流程引擎初始化失败！", e);
	    	throw new ProcessException(e);
	    }
		return flag;
	}
	

	@SuppressWarnings("static-access")
	@Override
	public boolean deployWsXmlArray(Map<String,String> map)throws ProcessException {
		boolean flag = true;
		   
		try{
			this.loadEngine();
			for(Entry<String,String> entry:map.entrySet()){
				 String key = entry.getKey();
				 String xml = entry.getValue();
				 String deploymentDbid =repositoryService.createDeployment().addResourceFromString("xmlstring.jpdl.xml",xml).deploy();
				 iSwapESBStart.wfMap.put(key, deploymentDbid);
				log.info("流程名：【"+key+"】部署成功.");
			}
		}catch(Exception e){
			flag = false;
			log.error("批量部署流程失败！", e);
		}
		
		return flag;
	}

	
	@SuppressWarnings("static-access")
	@Override
	public Map<String, String> getDeployWsXmlArray() throws ProcessException {
		return iSwapESBStart.wfMap;
	}

	
	@SuppressWarnings("static-access")
	@Override
	public String getDeployWsXml(String key) throws ProcessException {
		String deploymentDbid = null;
		if(iSwapESBStart.wfMap.containsKey(key)){
			deploymentDbid = iSwapESBStart.wfMap.get(key);
		}
		return deploymentDbid;
	}

	@SuppressWarnings("static-access")
	@Override
	public String deployWsXmlString(String wsXml,String keyName) throws ProcessException {
		String deploymentDbid = null;
		try{
			this.loadEngine();
			deploymentDbid =repositoryService.createDeployment().addResourceFromString("xmlstring.jpdl.xml",wsXml).deploy();
			iSwapESBStart.wfMap.put(keyName, deploymentDbid);
			log.info("流程名：【"+keyName+"】部署成功.");
		}catch(Exception e){
			log.error("流程名：【"+keyName+"】部署工作流失败！", e);
			throw new ProcessException(e);
		}
		return deploymentDbid;
	}

	
	@SuppressWarnings("static-access")
	@Override
	public boolean deleteDeployment(String keyName)throws ProcessException {
		boolean  flag = true;
		try{
			this.loadEngine();
			if(iSwapESBStart.wfMap.containsKey(keyName)){
				String depId = iSwapESBStart.wfMap.get(keyName);
				repositoryService.deleteDeploymentCascade(depId);
				iSwapESBStart.wfMap.remove(keyName);
				log.info("流程名：【 "+keyName+"】取消部署成功.");
			}
		}catch(Exception e){
			flag = false;
			log.error("流程名：【"+keyName+"】取消流程部署失败！", e);
			throw new ProcessException(e);
		}
		return flag;
	}  
	
	@SuppressWarnings("static-access")
	@Override
	public Map<String, Object> runWorkFlow(String key,Map<String, Object> variables) throws ProcessException {
		Map<String,Object> map = new HashMap<String,Object>();
		ProcessInstance processInstance = null;
		String msg = "true";
		CacheTool tool = CacheTool.init();
		String cacheName = key+"_"+System.currentTimeMillis();
		try{
			 log.info("流程名：【"+key+"】开始运行......");
		     Cache cache = tool.createCache(cacheName);
			 this.loadEngine();
			 for(Map.Entry<String,Object> cache_map:variables.entrySet()){
				 String key_name = cache_map.getKey();
				 Object obj = cache_map.getValue();
				 tool.putCacheInfo(cache, key_name, obj);
			 }
			 Map<String,Object> wfMap = new HashMap<String,Object>();
			 wfMap.put("wf_chcache_infoname", cacheName);
			 processInstance = executionService.startProcessInstanceByKey(key,wfMap);
			 if(processInstance.isEnded()){
				 log.info("流程名：【"+key+"】运行完毕！");
			 }
			 String outputMsg = (String)tool.getCacheInfo(cache, "outputMsg");
			 map.put("msg", msg);
			 map.put("outputMsg", outputMsg);
			 
		}catch(Exception e){
			msg = "false";
			map.put("msg", msg);
			log.error("流程名：【"+key+"】运行失败！", e);
			throw new ProcessException(e);
		}finally{
			tool.removeCache(cacheName);
		}
		return map;
	}
  
	@SuppressWarnings("static-access")
	private  void loadEngine(){
		processEngine = iSwapESBStart.processEngine;
		repositoryService = processEngine.getRepositoryService();
		executionService = processEngine.getExecutionService();
		historyService = processEngine.getHistoryService();
		managementService = processEngine.getManagementService();
		taskService = processEngine.getTaskService();
		identityService = processEngine.getIdentityService();
	}

}
