package com.ligitalsoft.workflow.plugin.lognode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import org.jbpm.api.activity.ActivityExecution;

import com.common.dbtool.FileDBTool;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;

public class RecordLogAction extends PluginActionHandler {
	private static final long serialVersionUID = 252064412412668172L;
	public String data_inputVar;//输入数据
	public String tableName;//表名
	public String subjectName;//指标名
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		log.info("开始将记录日志信息......");
	    String dbName = "systemdb";//
		FileDBTool tool = FileDBTool.init();
		Map<String,Object>map=	(Map<String,Object>)context.getVariable(data_inputVar);
		if(null!=map){
		try{
			tool.getMongoConn();
			map.put("itemCode", subjectName);
			tool.saveToFiledb(dbName, tableName, map);
			log.info("成功将记录日志信息......");
		}catch(Exception e){
			 ByteArrayOutputStream bo = new ByteArrayOutputStream();
			 e.printStackTrace(new PrintStream(bo));
			 log.error("记录日志写入云存储失败！",e);
		}finally{
			tool.closeFileDB();
		}
		}
	}
	
}
