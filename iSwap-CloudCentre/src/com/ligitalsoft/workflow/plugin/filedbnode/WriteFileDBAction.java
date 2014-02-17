package com.ligitalsoft.workflow.plugin.filedbnode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.activity.ActivityExecution;

import com.common.dbtool.FileDBTool;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.ObjectTool;

/**
 * 读取云存储的数据
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-9-21 下午02:22:26
 *@Team 研发中心
 */
public class WriteFileDBAction extends PluginActionHandler {

	private static final long serialVersionUID = 2347490652459341433L;
	
	public String data_inputVar;
	public String insertCount_outVar;//插入mongon数据库的数据条数
	public String dbName = "datasenddb";
	public String tableName;
	public String keyName;
	public String fromSource;
	
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		log.info("开始将数据写入云存储......");
		if(null!=this.getCacheInfo(data_inputVar)&&""!=this.getCacheInfo(data_inputVar)){
		ObjectTool obj_tool = ObjectTool.init();
		FileDBTool tool = FileDBTool.init();
		try{
			tool.getMongoConn();
			List<DataPackInfo> dpInfoList = (List<DataPackInfo>)this.getCacheInfo(data_inputVar);
			int n = 1;
			int i=0;
			if(null!=dpInfoList){
				for(DataPackInfo dpInfo:dpInfoList){
					dpInfo.setName(keyName);
					dpInfo.setStatus("send");
					if(null!=fromSource&&!StringUtils.isBlank(fromSource)){
						dpInfo.setAlias(fromSource);
					}
					Map<String,Object> map = obj_tool.dataPackToMap(dpInfo);
					tool.saveToFiledb(dbName, tableName,map);
					i+=dpInfo.getRowDataList().size();
					
					log.info("第【"+(n++)+"】个数据写入云存储成功,数据总数【"+dpInfo.getRowDataList().size()+"条】。");
				}
				if(!StringUtils.isBlank(insertCount_outVar)){
					context.setVariable(insertCount_outVar, i);
				}
			}
			tool.closeFileDB();
			log.info("成功的将数据写入云存储！");
		}catch(Exception e){
			 ByteArrayOutputStream bo = new ByteArrayOutputStream();
			 e.printStackTrace(new PrintStream(bo));
			 log.error("向云缓存写信息失败！",e);
			 throw new ActionException(e);
		}
		}else{
			 log.info("向云缓存写信息时没有获取到数据！");
		}
	}

}
