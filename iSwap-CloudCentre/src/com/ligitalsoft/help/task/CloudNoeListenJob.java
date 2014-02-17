package com.ligitalsoft.help.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.common.dbtool.FileDBTool;
import com.common.framework.exception.ServiceException;
import com.common.framework.help.SpringContextHolder;
import com.ligitalsoft.cloudnode.service.ICloudnodeListenService;
import com.ligitalsoft.cloudnode.service.INodeTaskMsgService;
import com.ligitalsoft.model.cloudnode.CloudnodeListen;
import com.ligitalsoft.model.cloudnode.WorkFlow;

/**
 * MONGODB 监听
 * 
 * @author arcgismanager
 * 
 */
public class CloudNoeListenJob implements Job {

	private ICloudnodeListenService cloudnodeListenService;
	private INodeTaskMsgService nodeTaskMsgService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
		Map<String, Object> map = new HashMap<String, Object>();// 添加条件
		cloudnodeListenService = SpringContextHolder
				.getBean("cloudnodeListenService");
		List<CloudnodeListen> listens;
		try {
			listens = cloudnodeListenService.findByProperty("status", "1");
			for (CloudnodeListen cloudnodeListen : listens) {
				map.put(cloudnodeListen.getFiledName(),
						cloudnodeListen.getFiledStatus());
				List<Map<String, Object>> entityList = tool.findToFiledb(
						cloudnodeListen.getDbName(),
						cloudnodeListen.getCollectionName(), map);
				if (entityList != null && entityList.size() > 0) {
					/* 对应流程 */
					WorkFlow workFlow = cloudnodeListen.getWorkFlow();
					if (workFlow.getStatus().equals("1")) {
//						nodeTaskMsgService.updateStatus(nodeTaskMsg);
					}
				}
				map.clear();
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
