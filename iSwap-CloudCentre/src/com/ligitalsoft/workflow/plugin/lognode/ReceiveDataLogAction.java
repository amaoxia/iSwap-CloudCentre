package com.ligitalsoft.workflow.plugin.lognode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.activity.ActivityExecution;

import com.common.dbtool.FileDBTool;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;

public class ReceiveDataLogAction extends PluginActionHandler {

	private static final long serialVersionUID = -3555728136070468777L;
	public String data_inputVar;
	public String keyName;

	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		log.info("开始接收日志记录.....");
		FileDBTool tool = FileDBTool.init();
		try {

			tool.getMongoConn();
			Map<String, Object> map = new HashMap<String, Object>();
			String alias=(String)context.getVariable("alias");
			if(null!=alias&&!StringUtils.isBlank(alias)){
				String itemcode=keyName+"#"+alias;
				map.put("itemCode", itemcode);
			}else{
				map.put("itemCode", keyName);
			}
			map.put("dataCount", context.getVariable(data_inputVar));
			map.put("dataTotal", context.getVariable(data_inputVar));
			map.put("createDate", new Date());
			
			if (null != map.get("dataTotal")) {
				tool.saveToFiledb(FileDBConstant.fileDBName,
						FileDBConstant.receiveDataInfoDB, map);
				context.setVariable("receiveDataLog",map);
			}
		} catch (Exception e) {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bo));
			log.error("接受日志节点失败！", e);
			throw new ActionException(e);
		} finally {
			tool.closeFileDB();
		}
	}
}
