package com.ligitalsoft.workflow.plugin.xmlnode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.activity.ActivityExecution;

import com.common.dbtool.FileDBTool;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.ObjectTool;

public class RouteAction  extends PluginActionHandler {
	private static final long serialVersionUID = -1125071824811614424L;
	
	public String dbName = "datareceivedb";
	public String tableName;
	public String keyName;
	public String status;
	public String filedbData_outVar;
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		ObjectTool obj_tool = ObjectTool.init();
		FileDBTool tool = FileDBTool.init();
		try{
			tool.getMongoConn();
			Map<String,Object> map = new HashMap<String,Object>();
			if(!StringUtils.isBlank(keyName)){
				map.put("name", keyName);
			}
			if(status != null){
				map.put("status", status);
			}
			List<Map<String,Object>> list_map = tool.findToFiledb(dbName,tableName,map);
			List<DataPackInfo> dpInfoList = new ArrayList<DataPackInfo>();
			int n = 0;
			for(Map<String,Object> data_map:list_map){
				DataPackInfo dpInfo = obj_tool.mapToDataPack(data_map);
				dpInfoList.add(dpInfo);
				n++; 
				Map<String,Object> src_map = data_map;
				src_map.put("_id", data_map.get("_id"));
				dpInfo.setStatus("route");
				Map<String,Object> tar_map = obj_tool.dataPackToMap(dpInfo);
				tool.updateToFiledb(dbName,tableName,src_map, tar_map);
				log.info("【第"+n+"个】数据包已经路由成功,数据总数【"+dpInfo.getRowDataList().size()+"条】");
				if(n>=10){
					break;
				}
			}
			tool.closeFileDB();
			this.putCacheInfo(filedbData_outVar, dpInfoList);
		}catch(Exception e){
			 ByteArrayOutputStream bo = new ByteArrayOutputStream();
			 e.printStackTrace(new PrintStream(bo));
			 log.error("路由数据失败！",e);
			 throw new ActionException(e);
		}
	}

}
