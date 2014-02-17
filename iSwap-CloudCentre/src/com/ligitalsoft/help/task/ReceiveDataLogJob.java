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
import org.springframework.stereotype.Service;

import com.common.dbtool.FileDBTool;
import com.common.framework.help.SpringContextHolder;
import com.ligitalsoft.datasharexchange.service.IChangeItemService;
import com.ligitalsoft.datasharexchange.service.IReceTaskService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.ReceiveResult;

/**
 * 接收日志，数据用于监控
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-10-31上午10:17:04
 * @Team 研发中心
 */
@Service("receiveDataLog")
public class ReceiveDataLogJob implements Job {
	private final Log log = LogFactory.getLog(this.getClass());

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		IReceTaskService receTaskService = SpringContextHolder
				.getBean("receTaskService");
		IChangeItemService changeItemService = SpringContextHolder
				.getBean("changeItemService");
		FileDBTool tool = FileDBTool.init();
		try {
			tool.getMongoConn();
			List<Map<String, Object>> entityList = tool
					.findToFiledb(FileDBConstant.fileDBName,
							FileDBConstant.receiveDataInfoDB);
			for (Map<String, Object> map : entityList) {
				try {
					Map<String, Object> conditions = new HashMap<String, Object>();// 添加条件
					Integer count_val = Integer.valueOf(map.get("dataCount")
							.toString());
					Integer dataTotal = Integer.valueOf(map.get("dataTotal")
							.toString());
					ObjectId _id = (ObjectId) map.get("_id");
					String key = (String) map.get("itemCode");
					String[] deptUidItemId = key.split("#");
					List<ChangeItem> changeItems = changeItemService
							.findByProperty("itemCode", deptUidItemId[1]);
					ChangeItem changeItem = null;
					if (changeItems != null && changeItems.size() > 0) {
						changeItem = changeItems.get(0);
					}
					Date createDate = new Date();
					if (map.get("createDate") != null) {
						createDate = (Date) map.get("createDate");
					}
					ReceiveResult receiveResult = new ReceiveResult();
					if (changeItem != null) {
						receiveResult.setReceNum(dataTotal);
						receiveResult.setDataNum(count_val);
						receiveResult.setItemCode(deptUidItemId[1]);
						receiveResult.setItemName(changeItem.getItemName());
						receiveResult.setReceiveDeptId(deptUidItemId[0]);
						receiveResult.setCreateDate(new Date());
						receiveResult.setExchangeDate(createDate);
						receTaskService.saveOrUpdate(receiveResult);
						conditions.put("_id", _id);
						tool.deleteToFiledb(FileDBConstant.fileDBName,
								FileDBConstant.receiveDataInfoDB, conditions);
					}
					conditions.clear();
				} catch (Exception e) {
					continue;
				}
			}
		} catch (Exception e) {
			log.error("获取接受日志的信息失败！", e);
		} finally {
			tool.closeFileDB();
		}
	}
}
