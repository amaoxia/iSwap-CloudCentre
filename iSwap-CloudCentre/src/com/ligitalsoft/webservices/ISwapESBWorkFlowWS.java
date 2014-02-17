package com.ligitalsoft.webservices;

/**
 *iSwapESB服务总线接口
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-10-17上午11:20:47
 *@Team 研发中心
 */
public interface ISwapESBWorkFlowWS {
	/**
	 * 根据流程的Id和enName查找流程Xml，并将流程的Xml 返回给flex编辑器
	 * @param id
	 * @param enName
	 * @return 
	 * @author  hudaowan
	 * @date 2011-9-3 上午11:44:14
	 */
	public String findWorkFlowXml(String id,String enName);
	
	/**
	 * 将flex编辑器的xml保存到服务器端
	 * @param id
	 * @param enName
	 * @param xml
	 * @return 
	 * @author  hudaowan
	 * @date 2011-9-3 上午11:46:40
	 */
	public String saveWorkFlowXml(String id,String enName,String xml);
	
}
