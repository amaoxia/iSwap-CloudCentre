package com.ligitalsoft.webservices;

import com.common.framework.help.SpringContextHolder;

/**
 * 用于和Flex进行通信，取数据使用
 *@Company 中海纪元
 *@author   hudaowan
 *@date   2012-9-3 下午8:08:21
 *@Team 研发中心
 */
public class WebFlexServer {
	/**
	 * 得到流程的xml
	 *@author hudaowan
	 *@date   2012-9-4 上午9:29:30
	 *@param id
	 *@param enName
	 *@param type
	 *@return   
	 *        String  
	 *@exception
	 */
	public String findWfXml(String id,String enName,String type){
		String xml = "";   
		if("0".equals(type)){
			ISwapWorkFlowWS iSwapWorkFlow =  SpringContextHolder.getBean("iSwapWorkFlow");
			xml = iSwapWorkFlow.findWorkFlowXml(id, enName);
		}else {
			ISwapESBWorkFlowWS iSwapESBWorkFlow = SpringContextHolder.getBean("iSwapESBWorkFlow");
			xml = iSwapESBWorkFlow.findWorkFlowXml(id, enName);
		}
		return xml;
	}
	

	/**
	 * 将flex编辑器的xml保存到服务器端
	 * @param id
	 * @param enName
	 * @param xml
	 * @return 
	 * @author  hudaowan
	 * @date 2011-9-3 上午11:46:40
	 */
	public String saveWorkFlowXml(String id,String enName,String xml,String type){
		String msg = "true";
		if("0".equals(type)){
			ISwapWorkFlowWS iSwapWorkFlow =  SpringContextHolder.getBean("iSwapWorkFlow");
			msg = iSwapWorkFlow.saveWorkFlowXml(id, enName, xml);
		}else {
			ISwapESBWorkFlowWS iSwapESBWorkFlow = SpringContextHolder.getBean("iSwapESBWorkFlow");
			msg = iSwapESBWorkFlow.saveWorkFlowXml(id, enName, xml);
		}
	 return msg;
	}
	
	/**
	 * 得到所有的数据源
	 * @return 
	 * @author  hudaowan
	 * @date 2011-9-14 下午02:23:12
	 */
	public String findDataSource(String deptId){
		 String msg = "";
		 ISwapWorkFlowWS iSwapWorkFlow =  SpringContextHolder.getBean("iSwapWorkFlow");
		 msg = iSwapWorkFlow.findDataSource(deptId);
		 return msg;
	}
	
	/**
	 *  得到所有的mapping文件
	 * @author hudaowan
	 * @date 2011-10-29上午11:45:59
	 * @return
	 */
	public String findMapping(String deptId){
		String msg = "";
		ISwapWorkFlowWS iSwapWorkFlow =  SpringContextHolder.getBean("iSwapWorkFlow");
		msg = iSwapWorkFlow.findMapping(deptId);
	  return msg;
	}
	
	/**
	 * 得到FTP的服务信息
	 * @author hudaowan
	 * @date 2011-12-19下午03:05:59
	 * @return
	 */
	public String findFTPServer(){
		String msg = "";
	    ISwapWorkFlowWS iSwapWorkFlow =  SpringContextHolder.getBean("iSwapWorkFlow");
		msg = iSwapWorkFlow.findFTPServer();
	 return msg;
	}
	

}
