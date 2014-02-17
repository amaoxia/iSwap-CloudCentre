package com.ligitalsoft.workflow.plugin.filedbnode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.activity.ActivityExecution;
import org.bson.types.ObjectId;

import com.common.dbtool.FileDBTool;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.ObjectTool;

public class UpdateFileDBAction  extends PluginActionHandler {

	private static final long serialVersionUID = 5057578333696891655L;

	public String dbName = "datareceivedb";
	public String data_inputVar;
	public String tableName;
	public String keyName;
	public String update_status;
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		log.info("开始更新云存储......");
		if(null!=getCacheInfo(data_inputVar)&&""!=getCacheInfo(data_inputVar)){
		List<DataPackInfo> dpInfoList = (List<DataPackInfo>)this.getCacheInfo(data_inputVar);
		if(null!=dpInfoList&&dpInfoList.size()>0){
		
		ObjectTool obj_tool = ObjectTool.init();
		FileDBTool tool = FileDBTool.init();
		try{
			tool.getMongoConn();
			int n = 0;
			for(DataPackInfo dpInfo:dpInfoList){
				n++; 
				Map<String,Object> src_map=new HashMap<String,Object>();
				src_map.put("_id", new ObjectId(dpInfo.getId()));
				dpInfo.setStatus(update_status);
				Map<String,Object> tar_map = obj_tool.dataPackToMap(dpInfo);
				tool.updateToFiledb(dbName,tableName,src_map, tar_map);
				log.info("【第"+n+"个】数据包状态修改成功,数据总数【"+dpInfo.getRowDataList().size()+"条】。");
			}
			tool.closeFileDB();
		}catch(Exception e){
			 ByteArrayOutputStream bo = new ByteArrayOutputStream();
			 e.printStackTrace(new PrintStream(bo));
			 log.error("修改云储存的信息失败！",e);
			 throw new ActionException(e);
		}
		}else{
			log.info("【修改云储存】节点未获得要修改的数据！");
		}

		}else{
			log.info("【修改云储存】节点未获得要修改的数据！");
		}
	}
}
