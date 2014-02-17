package com.ligitalsoft.datasharexchange.service;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.changemanage.ExchangeSendTask;



public interface IListenerService extends IBaseServices<ExchangeSendTask> {

	/**
	 * 分析所有指标项的任务安排
	 */
	void analyseTask() ;
	
	/**
	 * 生成所有指标项任务
	 *
	 *@author shawl
	 *@date  Apr 1, 2009 4:15:34 PM
	 */
	public void buildAllTask();
	/**
	 * 检查任务是否生成，未生成进行生成任务
	 *
	 *@author shawl
	 *@date  Apr 1, 2009 4:48:50 PM
	 */
	public void checkTaskListener();
	
	
}
