package com.ligitalsoft.help.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.common.dbtool.FileDBTool;
import com.common.framework.help.SpringContextHolder;
import com.ligitalsoft.datasharexchange.service.ISendDataFomatResultService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.changemanage.SendDataFomatResult;

public class SendDataFomatResultJob implements Job {
	private final Log log = LogFactory.getLog(this.getClass());

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		ISendDataFomatResultService service = SpringContextHolder
				.getBean("sendDataFomatResultService");
		FileDBTool tool = FileDBTool.init();
		try {
			tool.getMongoConn();
			List<Map<String, Object>> entityList = tool.findToFiledb(
					FileDBConstant.fileDBName, FileDBConstant.FILTERDATALOG);
			for (Map<String, Object> map : entityList) {
				try {
					Map<String, Object> conditions = new HashMap<String, Object>();
					Map<String, Object> serach = new HashMap<String, Object>();
					String filename = (String) map.get("filename");
					serach.put("filename", filename);
					List<Map<String, Object>> list = tool.findToFiledb(
							FileDBConstant.fileDBName,
							FileDBConstant.excelInsertDBLog, serach);
					int e_count = 0;
					int s_count = 0;
					Object id = null;
					if (list != null && list.size() > 0) {
						Map<String, Object> excelInfo = list.get(0);
						e_count = Integer.valueOf(excelInfo.get("errCount")
								+ "");
						s_count = Integer.valueOf(excelInfo.get("succCount")
								+ "");
						id = excelInfo.get("_id");
					}
					Integer errCount = Integer
							.valueOf(map.get("errCount") + "") + e_count;
					Integer succCount = Integer.valueOf(map.get("succCount")
							+ "");
					if (s_count > 0) {
						succCount = s_count;
					}
					Integer total = Integer.valueOf(map.get("total") + "");
					String errlog = (String) map.get("errlog");
					String itemCode = (String) map.get("itemCode");

					Date createDate = new Date();
					if (null != map.get("createDate")) {
						createDate = (Date) map.get("createDate");
					}
					SendDataFomatResult result = new SendDataFomatResult();
					result.setErrCount(errCount);
					result.setSuccCount(succCount);
					result.setTotal(total);
					result.setFileName(filename);
					result.setErrlog(errlog);
					result.setCreateDate(createDate);
					result.setItemCode(itemCode);
					service.saveOrUpdate(result);
					if (id != null) {
						serach.clear();
						serach.put("_id", id);
						tool.deleteToFiledb(FileDBConstant.fileDBName,
								FileDBConstant.excelInsertDBLog, serach);
						serach.clear();
					}
					ObjectId _id = (ObjectId) map.get("_id");
					conditions.put("_id", _id);// 添加删除条件
					tool.deleteToFiledb(FileDBConstant.fileDBName,
							FileDBConstant.FILTERDATALOG, conditions);
					conditions.clear();
				} catch (Exception e) {
					continue;
				}
			}
		} catch (Exception e) {
			log.error("获取发送日志的信息失败！", e);
		} finally {
			tool.closeFileDB();
		}
	}
}
