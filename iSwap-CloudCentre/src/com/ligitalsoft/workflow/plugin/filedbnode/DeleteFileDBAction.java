package com.ligitalsoft.workflow.plugin.filedbnode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.jbpm.api.activity.ActivityExecution;

import com.common.dbtool.FileDBTool;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.ObjectTool;

/**
 * 删除 filedb的数据
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-11-16下午05:41:53
 *@Team 研发中心
 */
public class DeleteFileDBAction extends PluginActionHandler{
	private static final long serialVersionUID = 1L;
	
	public String dbName = "datareceivedb";
	public String data_inputVar;
	public String tableName;
	public String keyName;
	public String status;
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		log.info("开始删除云存储......");
		List<DataPackInfo> dpInfoList = (List<DataPackInfo>)this.getCacheInfo(data_inputVar);
		if(null!=dpInfoList&&dpInfoList.size()>0){
		
		ObjectTool obj_tool = ObjectTool.init();
		FileDBTool tool = FileDBTool.init();
		try{
			tool.getMongoConn();
			int n = 0;
			for(DataPackInfo dpInfo:dpInfoList){
				n++; 
				Map<String,Object> src_map = new HashMap<String,Object>();
				src_map.put("_id", new ObjectId(dpInfo.getId()));
				System.out.println("---_id-----------------------"+dpInfo.getId()+"-----------");
				tool.deleteToFiledb(dbName, tableName, src_map);
				log.info("从云存储中成功删除【第"+n+"个】数据包,数据总数【"+dpInfo.getRowDataList().size()+"条】。");
			}
			tool.closeFileDB();
		}catch(Exception e){
			 ByteArrayOutputStream bo = new ByteArrayOutputStream();
			 e.printStackTrace(new PrintStream(bo));
			 log.error("删除云储存的信息失败！",e);
			 throw new ActionException(e);
		}
		}else{
			log.info("【删除云储存】节点未获得要删除的数据！");
		}
	}
}
