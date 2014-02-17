package com.ligitalsoft.workflow.plugin.lognode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jbpm.api.activity.ActivityExecution;

import com.common.dbtool.FileDBTool;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;

/**
 * 发送数据日志
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-9-5 下午09:38:48
 *@Team 研发中心
 */
public class SendDataLogAction extends PluginActionHandler {
	private static final long serialVersionUID = 681105820383425437L;
	public String data_inputVar;//实际发送数据
	public String datatotal_inputVar;//应发送数据
	public String keyName;
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		log.info("开始发送日志记录.....");
		FileDBTool tool = FileDBTool.init();
		try{
			tool.getMongoConn();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("itemCode", keyName);
			map.put("dataCount", context.getVariable(data_inputVar));
			map.put("dataTotal", context.getVariable(datatotal_inputVar));
			map.put("createDate", new Date());
			System.out.println("-----sendDatalog---------------"+context.getVariable(datatotal_inputVar)+"------"+context.getVariable(data_inputVar));
			tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.sendDataInfoDB, map);
		}catch(Exception e){
			 ByteArrayOutputStream bo = new ByteArrayOutputStream();
			 e.printStackTrace(new PrintStream(bo));
			 log.error("发送日志节点失败！",e);
			 throw new ActionException(e);
		}finally{
			tool.closeFileDB();
		}
	}
}
