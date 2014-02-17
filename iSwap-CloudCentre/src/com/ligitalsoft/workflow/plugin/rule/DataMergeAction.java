package com.ligitalsoft.workflow.plugin.rule;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.api.activity.ActivityExecution;

import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.RowDataInfo;

/**
 * 数据合并节点
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version Fusion UCenter V1.0
 * @date 2012-2-28下午05:24:15
 * @Team 研发中心
 */
public class DataMergeAction extends PluginActionHandler {

	private static final long serialVersionUID = 7561707151210817563L;

	public String target_inputVar;// 目标输入
	public String src_inputVar;// 源的输入
	public String data_outVar;// 数据输出

	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		log.info("开始将数据合并......");
		List<DataPackInfo> srcList = new ArrayList<DataPackInfo>();
		List<DataPackInfo> targetList = new ArrayList<DataPackInfo>();
		if (null != this.getCacheInfo(src_inputVar)&&"" != this.getCacheInfo(src_inputVar)) {
			srcList = (List<DataPackInfo>) this.getCacheInfo(src_inputVar);
		}
		if (null != this.getCacheInfo(target_inputVar)&&"" != this.getCacheInfo(target_inputVar)) {
			targetList = (List<DataPackInfo>) this
					.getCacheInfo(target_inputVar);
		}
		if (targetList.size() > 0) {
			for (DataPackInfo dpi : srcList) {
				if(dpi.getRowDataList().size()>0){
					targetList.get(0).getRowDataList().addAll(dpi.getRowDataList());
				}
			}
		} else {
			targetList = srcList;
		}
		this.putCacheInfo(data_outVar, targetList);
	}

};
