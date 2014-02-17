package com.ligitalsoft.datasharexchange.service;

/**
 * 文件共享目录
 * 
 * @author arcgismanager
 * 
 */
public interface IShareFileService {
	/**
	 * 从交换成功目录移除文件到共享区目录
	 * 
	 * @author zhangx
	 */
	public void moveFileToShareDocument();

	/**
	 * 移动文件到发布目录 条件已经发布状态指
	 * 
	 * @author zhangx
	 */
	public void moveFileToPublishDocument();

	/**
	 * 初始化任务类
	 */
	public void InitJob();
}
