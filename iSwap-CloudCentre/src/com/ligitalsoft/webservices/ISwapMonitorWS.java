package com.ligitalsoft.webservices;

import javax.jws.WebService;

@WebService
public interface ISwapMonitorWS {
	
	/**
	 * 获得所有应用
	 * 
	 * @author fangbin
	 * @return 
	 */
	public  String getAllApp() ;
	
	/**
	 * 获得所有云端节点
	 * 
	 * @author fangbin
	 * @param appId
	 * @return
	 */
	public String getCloudNode(String appId);
	
	/**
	 * 数据共享情况
	 * 
	 * @author fangbin
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String dataShare(String startDate,String endDate);
	
	/**
	 * 数据共享详情
	 * 
	 * @author fangbin
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String dataShareInfo(String deptId,String startDate,String endDate);
	
	/**
	 * 数据共享排名——部门
	 * @author fangbin
	 * @return
	 */
	public String deptDataShare();
	
	/**
	 * 数据共享排名——指标
	 * @author fangbin
	 * @param deptId
	 * @return
	 */
	public String targetDataShare(String deptId);
	/**
	 * 数据发送任务监控
	 * @author fangbin
	 * @param appId
	 * @return
	 */
	public String dataSendTask(String appId);
	/**
	 * 获得所有部门
	 *  @author fangbin
	 * @return
	 */
	public String getAllDept();
	
	/**
	 * 	 * 数据利用情况
	 * @author fangbin
	 * @param type 1部门视图，2应用视图
	 * @param type
	 * @param deptId
	 * @param receiveDeptId
	 * @param startDate
	 * @param endDate
	 * @param appId
	 * @return
	 */
	public String dataUse(String type,String deptId,String receiveDeptId,String startDate,String endDate,String appId);
	
	/**
	 * 数据接收详细
	 * @author fangbin
	 * @param targetId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	
	public String dataReceiveInfo(String targetId,String receiveDeptId,String startDate,String endDate);
	/**
	 * 数据利用排名——按应用
	 * @author fangbin
	 * @return
	 */
	public String dataUseRankByApp();
	/**
	 * 数据利用排名——按指标
	 * @param appId
	 * @return
	 */
	public String dataUseRankByTarget(String appId);
}
