package com.ligitalsoft.esb.service;

import java.util.Map;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.esb.EsbWorkFlow;

/**
 * ESB的流程管理接口
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-13 下午07:21:20
 *@Team 研发中心
 */

public interface IEsbWorkFlowService extends IBaseServices<EsbWorkFlow> {
	/**
	 * 工作流程测试
	 * 
	 * @author fangbin
	 * @param workflow
	 * @param Xml
	 * @return
	 */
	public String runWorkFlow(Long workflowId,String Xml);
	
	/**
	 * 解析xml
	 * @param workflowCode
	 * @param Xml
	 * @return 
	 * @author  hudaowan
	 * @date 2011-10-16 下午02:52:25
	 */
	public Map<String,Object>  readWorkflowXml(String Xml);
	

	
	
}
