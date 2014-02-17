package com.ligitalsoft.help.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.common.dbtool.FileDBTool;
import com.common.framework.exception.ServiceException;
import com.common.framework.help.SpringContextHolder;
import com.ligitalsoft.datasharexchange.service.IExcelToDbinfoService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.changemanage.ExcelToDbinfo;
import com.mongodb.BasicDBObject;

/**
 * excel文档数据入库信息
 * 
 * @author arcgismanager
 * 
 */
public class ExcelToDbJob implements Job {

	private IExcelToDbinfoService excelToDbinfoService;

	//
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
		Map<String, Object> map = new HashMap<String, Object>();//添加条件
		Date startDate = new Date();
		map.put("createDate", new BasicDBObject("$lte", startDate));// 匹配当前时间以前的日志信息
		List<Map<String, Object>> entityList = tool
				.findToFiledb(FileDBConstant.fileDBName,
						FileDBConstant.excelInsertDBLog, map);
		excelToDbinfoService = SpringContextHolder
				.getBean("excelToDbinfoService");
		map.clear();// 清空
		for (Map<String, Object> obj : entityList) {
			ObjectId _id = (ObjectId) obj.get("_id");
			String errorFileName = (String) obj.get("filename");
			Integer total = Integer.valueOf(obj.get("total").toString());
			Integer succCount = Integer
					.valueOf(obj.get("succCount").toString());
			Integer errTotal = Integer.valueOf(obj.get("errCount").toString());
			Date createDate=new Date();
			if(obj.get("createDate")!=null){
				createDate = (Date)obj.get("createDate");
			}
			ExcelToDbinfo dbinfo = new ExcelToDbinfo();
			dbinfo.setErrorFileName(errorFileName);
			dbinfo.setErrTotal(errTotal);
			dbinfo.setSuccCount(succCount);
			dbinfo.setCreateDate(createDate);
			dbinfo.setTotal(total);
			try {
				map.put("_id", _id);// 删除条件
				excelToDbinfoService.saveOrUpdate(dbinfo);
				tool.deleteToFiledb(FileDBConstant.fileDBName,
						FileDBConstant.excelInsertDBLog, map);

			} catch (ServiceException e) {
				System.out.println("添加数据失败" + e.getMessage());
				continue;
			}finally{
				tool.closeFileDB();
			}
		}
	}
}
