package com.ligitalsoft.workflow.plugin.filedbnode;

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

/**
 * 读取云存储的数据
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-9-21 下午02:20:09
 *@Team 研发中心
 */
public class ReadFileDBAction extends PluginActionHandler {
	private static final long serialVersionUID = 5994801051944890910L;
	public String dbName = "datareceivedb";
	public String tableName;
	public String keyName;
	public String status;
	//public String update_status;
	public String filedbData_outVar;
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		log.info("开始查询云存储......");
		ObjectTool obj_tool = ObjectTool.init();
		FileDBTool tool = FileDBTool.init();
		try{
			tool.getMongoConn();
			Map<String,Object> map = new HashMap<String,Object>();
			if(!StringUtils.isBlank(keyName)){
				map.put("name", keyName);
			}
			if(!StringUtils.isBlank(status)){
				map.put("status", status);
			}
			List<Map<String,Object>> list_map = tool.findManyToFiledb(dbName,tableName,map);
			List<DataPackInfo> dpInfoList = new ArrayList<DataPackInfo>();
			int n = 0;
			for(Map<String,Object> data_map:list_map){
				DataPackInfo dpInfo = obj_tool.mapToDataPack(data_map);
				System.out.println("-------------------------------_id--:"+dpInfo.getId()+"----");
				context.setVariable("alias", dpInfo.getAlias());
				dpInfoList.add(dpInfo);
				n++; 
//				Map<String,Object> src_map = data_map;
//				src_map.put("_id", data_map.get("_id"));
//				dpInfo.setStatus(update_status);
//				Map<String,Object> tar_map = obj_tool.dataPackToMap(dpInfo);
//				tool.updateToFiledb(dbName,tableName,src_map, tar_map);
				log.info("从云存储中成功读取【第"+n+"个】数据包,数据总数【"+dpInfo.getRowDataList().size()+"条】。");
				if(n>=2){
					break;
				}
			}
			
			tool.closeFileDB();
			list_map.clear();
			this.putCacheInfo(filedbData_outVar, dpInfoList);
		}catch(Exception e){
			 ByteArrayOutputStream bo = new ByteArrayOutputStream();
			 e.printStackTrace(new PrintStream(bo));
			 log.error("读取云储存的信息失败！",e);
			 throw new ActionException(e);
		}
	}

}
