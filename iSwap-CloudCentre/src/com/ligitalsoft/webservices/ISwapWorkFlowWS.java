package com.ligitalsoft.webservices;

import javax.jws.WebService;

/**
 *对流程的管理Ws，主要用于和Flex 流程编辑器通信
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-9-3 上午11:40:32
 *@Team 研发中心
 */
@WebService
public interface ISwapWorkFlowWS {

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
	
	/**
	 * 得到所有的数据源
	 * @return 
	 * @author  hudaowan
	 * @date 2011-9-14 下午02:23:12
	 */
	public String findDataSource(String deptId);
	
	/**
	 *  得到所有的mapping文件
	 * @author hudaowan
	 * @date 2011-10-29上午11:45:59
	 * @return
	 */
	public String findMapping(String deptId);
	
	/**
	 * 得到FTP的服务信息
	 * @author hudaowan
	 * @date 2011-12-19下午03:05:59
	 * @return
	 */
	public String findFTPServer();
	
}
