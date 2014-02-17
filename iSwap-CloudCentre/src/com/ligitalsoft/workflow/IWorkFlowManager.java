package com.ligitalsoft.workflow;

import java.util.Map;

import com.ligitalsoft.workflow.exception.ProcessException;

/**
 *对工作流进行管理
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-7 上午10:53:13
 *@Team 研发中心
 */
public interface IWorkFlowManager {

	/**
	 * 初始化流程的引擎
	 * @return
	 * @throws ProcessException   
	 *boolean  
	 * @exception   
	 * @since  1.0.0
	 */
	public boolean initWorkFlowEngine() throws ProcessException;
	
	/**
	 * 批量部署工作流
	 * @param jpdlXmlString
	 * @return boolean
	 * @throws ProcessException     
	 * @exception   
	 * @since  1.0.0
	 */
	public boolean deployWsXmlArray(Map<String,String> map) throws ProcessException;
	
	/**
	 * 得到部署的工作流的信息
	 * @return
	 * @throws ProcessException
	 */
	public Map<String,String> getDeployWsXmlArray() throws ProcessException;
	
	/**
	 * 根据key值得到部署流程的ID
	 * @return
	 * @throws ProcessException
	 */
	public String  getDeployWsXml(String key) throws ProcessException;
	
	/**
	 * 单个部署工作流
	 * @param wsXml
	 * @return String
	 * @throws ProcessException   
	 * @exception   
	 * @since  1.0.0
	 */
	public String deployWsXmlString(String wsXml,String key) throws ProcessException;
	
	/**
	 *取消部署的工作流
	 * @return boolean
	 * @throws ProcessException   
	 * @exception   
	 * @since  1.0.0
	 */
	public boolean deleteDeployment(String key) throws ProcessException;
	
	/**
	 * 运行工作流
	 * @param key
	 * @param variables
	 * @return
	 * @throws ProcessException
	 */
	public Map<String,Object> runWorkFlow(String key,Map<String,Object> variables)throws ProcessException;

	
}
