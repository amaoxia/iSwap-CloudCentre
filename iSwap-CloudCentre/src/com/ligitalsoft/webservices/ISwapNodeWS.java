package com.ligitalsoft.webservices;

import javax.jws.WebService;

@WebService
public interface ISwapNodeWS {
	
	
	/**
	 * 用于测试数据源连接
	 * @param dsNamse
	 * @return
	 * @throws Exception 
	 * @author  hudaowan
	 * @date 2011-9-4 下午06:12:42
	 */
	public String testDataSource(String dsNamse);
	
	/**
	 * 部署工作流
	 * @param wfName
	 * @param wfXml
	 * @return
	 * @throws Exception 
	 * @author  hudaowan
	 * @date 2011-9-4 下午06:02:37
	 */
	public String deployWorkFlow(String wfName,String wfXml);
	
	/**
	 * 取消部署的流程
	 * @param wfName
	 * @return
	 * @throws Exception 
	 * @author  hudaowan
	 * @date 2011-9-7 下午04:15:02
	 */
	public String UnDepoyWorkFlow(String wfName);
	
	/**
	 * 运行工作流程
	 * @param wfName
	 * @param xmlAttr
	 * @return
	 * @throws Exception 
	 * @author  hudaowan
	 * @date 2011-9-4 下午06:06:31
	 */
	public String runWorkFlow(String wfName,String xmlAttr);
	
    /**
     * 运行流程的任务
     * @param taskName
     * @param corn
     * @return 
     * @author  hudaowan
     * @date 2011-9-7 下午10:08:18
     */
    public String runWorkFlowTask(String taskName,String corn);
    
    /**
     * 停止流程的任务
     * @param taskName
     * @return
     * @throws Exception 
     * @author  hudaowan
     * @date 2011-9-7 下午04:15:36
     */
    public String stopWorkFlowTask(String taskName);
    
    /**
     * 启动JMS的监听
     * @author hudaowan
     * @date 2011-10-29下午03:49:37
     * @param taskName
     * @param corn
     * @return
     */
    public String runJmsWfTask(String taskName,String corn);
    
    /**
     * 停止JMS的监听
     * @author hudaowan
     * @date 2011-10-29下午03:50:17
     * @param taskName
     * @return
     */
    public String stopJmsWfTask(String taskName);
    
    /**
     * 启动Ftp的监听
     * @author hudaowan
     * @date 2011-10-29下午03:50:34
     * @param taskName
     * @param corn
     * @return
     */
    public String runFtpWfTask(String taskName,String corn);
    
    /**
     * 停止ftp的监听
     * @author hudaowan
     * @date 2011-10-29下午03:51:03
     * @param taskName
     * @return
     */
    public String stopFtpWfTask(String taskName);
    
    /**
     * 启动本地目录的监听
     * @author hudaowan
     * @date 2011-10-29下午03:51:22
     * @param taskName
     * @param corn
     * @return
     */
    public String runLocalhostWfTask(String taskName,String corn);
    
    /**
     * 停止本地目录监听
     * @author hudaowan
     * @date 2011-10-29下午03:52:33
     * @param taskName
     * @return
     */
    public String stopLocalhostWfTask(String taskName);
    /**
     * 启动分布式存储的监听
     * @author hudaowan
     * @date 2011-10-29下午03:51:22
     * @param taskName
     * @param corn
     * @return
     */
    public String runCloudNodeListenWfTask(String taskName,String corn);

    /**
     * 停止分布式存储监听
     * @author hudaowan
     * @date 2011-10-29下午03:52:33
     * @param taskName
     * @return
     */
    public String stopCloudNodeListenWfTask(String taskName);
    
}
