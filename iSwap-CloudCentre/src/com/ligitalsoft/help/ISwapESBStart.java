package com.ligitalsoft.help;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.cachetool.CacheTool;
import com.common.jobtool.JobTool;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.esb.service.IEsbTaskMsgService;
import com.ligitalsoft.esb.service.IEsbWorkFlowService;
import com.ligitalsoft.help.cache.CacheConstant;
import com.ligitalsoft.help.task.ISwapESBWorkFlowJob;
import com.ligitalsoft.model.esb.EsbTaskMsg;
import com.ligitalsoft.model.esb.EsbWorkFlow;
import com.ligitalsoft.workflow.IWorkFlowManager;


/**
 * iSwap ESB服务总线的启动服务
 * @author hudaowan
 */
@Service("iSwapESBStart")
public class ISwapESBStart {
	private final Log log = LogFactory.getLog(this.getClass());
	public static ProcessEngine processEngine = null;
	public static Map<String,String> listenMap = new HashMap<String,String>();
	public static Map<String,String> wfMap = new HashMap<String,String>();
	public static Map<String,Object> wfCacheMap = new HashMap<String,Object>();
	public static Map<String,Object> wfvarMap = new HashMap<String,Object>();
	private static ISwapESBStart obj = null;
	static{
		listenMap.put("FTPServer", "true");
		listenMap.put("LocalHostServer", "true");
		listenMap.put("ScheduledServer", "true");
	}
	public static ISwapESBStart init(){
		if(obj == null){
			obj = new ISwapESBStart();
		}
		return obj;
	}
	
	@Autowired
	private IWorkFlowManager workFlowManager;
	
	@Autowired
	private IEsbWorkFlowService esbWorkFlowService;
	
	@Autowired
	private IEsbTaskMsgService  esbTaskMsgService;
	
	public void startiSwapESB(){
		try {
			log.info("开始启动【iSwapESB】......");
			workFlowManager.initWorkFlowEngine();
			log.info("【iSwapESB】启动成功。......");
			log.info("开始部署ESB流程......");
			this.deployWorkFlow();
			this.loadJob();
			log.info("ESB流程部署完成！");
			log.info("开始启动ESB流程任务......");
			log.info("流程任务启动完毕！");
		} catch (Exception e) {
			log.error("【iSwapESB】启动失败!",e);
		}
	}
	
	/**
	 * 加载工作
	 * @author hudaowan
	 * @date 2011-11-16下午02:43:00
	 */
	private void loadJob(){
		try {
			List<EsbTaskMsg> esbwfList = esbTaskMsgService.findByProperty("status","1");
			JobTool jobTool = JobTool.init();
			CacheTool tool = CacheTool.init();
			Cache cache = tool.findCache("esbWorkfolwCache");
			for(EsbTaskMsg esbTaskMsgLoad:esbwfList){
				if(!StringUtils.isBlank(esbTaskMsgLoad.getCron())){
					String jobName = esbTaskMsgLoad.getTaskName()+"#"+esbTaskMsgLoad.getWorkFlow().getWorkFlowName()+"#"+esbTaskMsgLoad.getWorkFlow().getWorkFlowCode();
					tool.putCacheInfo(cache, jobName, esbTaskMsgLoad.getMessage());
					jobTool.addJob(jobName, esbTaskMsgLoad.getCron(), ISwapESBWorkFlowJob.class);
				}
			}
		} catch (Exception e) {
			log.error("ESB流程任务加载失败！", e);
		}
	}
	
	/**
	 * 部署ESB的流程
	 * @author hudaowan
	 * @date 2011-10-19下午05:24:56
	 */
	private void deployWorkFlow(){
		try {
			List<EsbWorkFlow> esbwfList = esbWorkFlowService.findByProperty("status","1");
			for(EsbWorkFlow esb:esbwfList){
				if(!StringUtils.isBlank(esb.getWorkFlowXml())){
					workFlowManager.deployWsXmlString(esb.getWorkFlowXml(), esb.getWorkFlowCode());
				}
			}
		} catch (Exception e) {
			log.error("ESB流程部署失败！", e);
		}
	} 
}
