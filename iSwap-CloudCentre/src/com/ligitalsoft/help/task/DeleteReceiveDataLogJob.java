package com.ligitalsoft.help.task;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.common.dbtool.FileDBTool;
import com.mongodb.DB;
import com.mongodb.Mongo;

/**
 * 删除mongoDB过程数据
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-10-31上午10:17:04
 * @Team 研发中心
 */
public class DeleteReceiveDataLogJob implements Job {
	private final Log log = LogFactory.getLog(this.getClass());

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		Map<String, Object> conditions = new HashMap<String, Object>();// 添加条件
		conditions.put("status", "receive");// 匹配当前时间以前的日志信息
		FileDBTool tool = FileDBTool.init();
		try {
			Mongo mongo = tool.getMongoConn();
			DB dbName = mongo.getDB("datareceivedb");
			Set<String> connNames = dbName.getCollectionNames();
			for (String connName : connNames) {
				try {
					tool.deleteToFiledb("datareceivedb", connName, conditions);
				} catch (Exception e) {
					log.error("获取接受日志的信息失败！", e);
					continue;
				}
			}
		} catch (Exception e) {
		} finally {
			tool.closeFileDB();
		}
	}
}
