package com.ligitalsoft.help.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.dbtool.FileDBTool;
import com.common.framework.exception.ServiceException;
import com.common.framework.help.SpringContextHolder;
import com.ligitalsoft.datasharexchange.service.ISendMessageLogService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.changemanage.SendMessageLog;

/**
 * 短信内容监听
 * 
 * @author xinx
 * 
 */
public class SendMessageLogJob implements Job {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ISendMessageLogService sendMessageLogService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
		sendMessageLogService = SpringContextHolder
				.getBean("sendMessageLogService");
		List<Map<String, Object>> entityList = tool.findToFiledb(
				FileDBConstant.fileDBName, "sendMessageLog");
		try {
			for (Map<String, Object> map : entityList) {
				Map<String, Object> conditions = new HashMap<String, Object>();
				ObjectId _id = (ObjectId) map.get("_id");
				String phone = (String) map.get("phone");
				String message = (String) map.get("message");
				Date createDate = new Date();
				if (map.get("createDate") != null) {
					createDate = (Date) map.get("createDate");
				}
				SendMessageLog messageLog = new SendMessageLog();
				messageLog.setPhone(phone);
				messageLog.setMessage(message);
				messageLog.setSendDate(createDate);
				try {
					sendMessageLogService.saveOrUpdate(messageLog);
					conditions.put("_id", _id);// 添加删除条件
					tool.deleteToFiledb(FileDBConstant.fileDBName,
							"sendMessageLog", conditions);
					conditions.clear();
				} catch (ServiceException e) {
					log.error("同步短信日志异常:#" + phone + "#", e);
					continue;
				}
			}
		} catch (Exception e) {
		} finally {
			tool.closeFileDB();
		}
	}
}
