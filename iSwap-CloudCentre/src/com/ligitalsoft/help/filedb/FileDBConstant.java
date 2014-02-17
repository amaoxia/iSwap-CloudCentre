package com.ligitalsoft.help.filedb;

/**
 * 云存储的定义
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-11-20下午07:33:39
 *@Team 研发中心
 */
public class FileDBConstant {
	
	/**
	 * 使用云存储的数据库的名称
	 */
	public static final String fileDBName = "systemdb";

	/**
	 * 当服务器起来的时候将workflow对象加载到云存储,服务器将所有的流程加载，用于云端节点使用
	 */
    public static final String workflowDB = "workflowdb";
    
    /**
     * 当服务器起来的时候将DataSource对象加载到云存储,服务器将所有的DataSource加载，用于云端节点使用
     */
    public static final String dataSourceDB = "dataSourcedb";
    
    public static final String esbDataSourceDB = "esbDataSourceDB";
    
    /**
     *  当服务器起来的时候将NodeTaskMsg对象加载到云存储,服务器将所有的NodeTaskMsg加载，用于云端节点使用 -
     */
    public static final String jobInfoDB = "jobInfodb";
    
    /**
     * jmsInfo
     */
    public static final String jmsInfoDB = "jmsInfodb";
    
    /**
     * ftpinfo
     */
    public static final String ftpInfoDB = "ftpInfodb";
    
    /**
     * localhost
     */
    public static final String localhostDB = "localhostdb";
	/**
	 * mongo
	 */
	public static final String MONGODBLISTEN="mongodblisten";
	
    /**
     * 用于记录数据发送的过程中每次发送的数据总数，用于数据发送监控
     */
    public static final String sendDataInfoDB = "sendDataInfodb";
    
    /**
     * 用于记录数据接受的过程中每次接受的数据总数，用于数据接收监控
     */
    public static final String receiveDataInfoDB = "receiveDataInfodb";
    
    /**
     * -用于Mapping
     */
    public static final String mappingDB = "mappingdb";
    /**
     * excel数据入库日志记录
     * @author fangbin
     */
    public static final String excelInsertDBLog = "excelInsertDBLog";
    /**
     * ftpServerDB
     */
    public static final String ftpServerDB = "ftpServerDB";
	/**
	 * 数据格式日志
	 * @author fangbin
	 */
	public static final String FILTERDATALOG = "filterdatalog";
	 /**
     * 短信发送日志记录
     * @author fangbin
     */
    public static final String sendMessageLog = "sendMessageLog";
    
}
