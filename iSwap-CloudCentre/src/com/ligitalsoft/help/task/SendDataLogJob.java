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
import com.ligitalsoft.datasharexchange.service.ISendResultService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.SendResult;

/**
 * 将发送数据从缓存中取出来，用于监控
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-10-31下午01:29:24
 * @Team 研发中心
 */
@Service("sendDataLog")
public class SendDataLogJob implements Job {
	private final Log log = LogFactory.getLog(this.getClass());

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		ISendResultService sendResultService = SpringContextHolder
				.getBean("sendResultService");
		IChangeItemService changeItemService = SpringContextHolder
				.getBean("changeItemService");
		Map<String, Object> conditions = new HashMap<String, Object>();
		FileDBTool tool = FileDBTool.init();
		try {
			tool.getMongoConn();
			List<Map<String, Object>> entityList = tool.findToFiledb(
					FileDBConstant.fileDBName, FileDBConstant.sendDataInfoDB);
			for (Map<String, Object> map : entityList) {
				try {
					Integer count_val = Integer.valueOf(map.get("dataCount")
							+ "");
					Integer dataTotal = Integer.valueOf(map.get("dataTotal")
							+ "");
					String itemCode = (String) map.get("itemCode");
					ObjectId _id = (ObjectId) map.get("_id");
					Date createDate = new Date();
					if (map.get("createDate") != null) {
						createDate = (Date) map.get("createDate");
					}
					List<ChangeItem> changeItems = changeItemService
							.findByProperty("itemCode", itemCode);
					ChangeItem changeItem = null;
					if (changeItems != null && changeItems.size() > 0) {
						changeItem = changeItems.get(0);
					}
					SendResult sendResult = new SendResult();
					if (changeItem != null) {
						sendResult.setDataNum(count_val);// 发送数量
						sendResult.setItemCode(itemCode);
						sendResult.setCreatedate(new Date());
						sendResult.setExchangeDate(createDate);// 交换时间
						sendResult.setItemName(changeItem.getItemName());// 设置指标名称
						sendResult.setDeptId(changeItem.getSysDept()
								.getDeptUid());// 部门UID
						sendResult.setPayNum(dataTotal);
						sendResultService.saveOrUpdate(sendResult);// 持久化操作
						conditions.put("_id", _id);// 添加删除条件
						tool.deleteToFiledb(FileDBConstant.fileDBName,
								FileDBConstant.sendDataInfoDB, conditions);
					}
					conditions.clear();
				} catch (Exception e) {
					log.error("获取发送日志的信息失败！", e);
					continue;
				}
			}
		} catch (Exception e) {
		} finally {
			tool.closeFileDB();
		}
	}
}
