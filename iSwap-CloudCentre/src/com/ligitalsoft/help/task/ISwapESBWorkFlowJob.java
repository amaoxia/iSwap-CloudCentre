package com.ligitalsoft.help.task;

import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.Cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.common.cachetool.CacheTool;
import com.ligitalsoft.help.ISwapESBStart;
import com.ligitalsoft.workflow.IWorkFlowManager;
import com.ligitalsoft.workflow.impl.WorkFlowManagerImpl;

public class ISwapESBWorkFlowJob implements Job{
	private final Log log = LogFactory.getLog(this.getClass());
	@SuppressWarnings("static-access")
	@Override
	public void execute(JobExecutionContext context)throws JobExecutionException {
		String jobName = context.getJobDetail().getKey().getName();
		String[] array = jobName.split("#");
		log.info("名称：【"+jobName+"】 开始执行...");
		ISwapESBStart initiSwap = ISwapESBStart.init();
		try {
			 CacheTool tool = CacheTool.init();
			 Cache cache = tool.findCache("esbWorkfolwCache");
			
			 String flag = initiSwap.listenMap.get(array[2]);
			 if(!"true".equals(flag)){
				initiSwap.listenMap.put(array[2],"true");
				String msg = (String)tool.getCacheInfo(cache, jobName);
				this.runWorkFlow(array[2], msg);
				initiSwap.listenMap.put(array[2],"false");   
			 }
			 log.info("名称：【"+jobName+"】 任务执行完毕！");
		} catch (Exception e) {
			initiSwap.listenMap.put(array[2],"false");   
			log.error("任务名称：【"+jobName+"】 任务执行失败！",e);
		}
	}

	/**
	 * 运行流程
	 * @author hudaowan
	 * @date 2011-11-16下午01:57:20
	 * @param wfName
	 * @param xmlAttr
	 * @return
	 */
	private String runWorkFlow(String wfName, String xmlAttr) {
		String msg="true";
		IWorkFlowManager workFlowManager = new  WorkFlowManagerImpl();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			map.put("inputmessage", xmlAttr);
			String workflowKey= workFlowManager.getDeployWsXml(wfName);
			if(workflowKey!=null){
				workFlowManager.runWorkFlow(wfName, map);
				log.info("流程名：【"+wfName+"】运行成功!");
			}else{
				log.info("没有找到流程名：【"+wfName+"】!");
				msg = "unDepoy";
			}
		}catch(Exception e){
			msg = "false";
			log.error("流程名：【"+wfName+"】运行 失败!", e);
		}
		return msg;
	}
}
