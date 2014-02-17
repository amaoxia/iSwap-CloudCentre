package com.ligitalsoft.cloudnode.service;

import java.util.List;

import com.common.framework.dao.QueryPara;
import com.common.framework.dao.SortPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.cloudnode.NodeTaskMsg;

public interface INodeTaskMsgService extends IBaseServices<NodeTaskMsg> {
	/**
	 *将启动的任务添加到缓存中
	 */
	public void updateStatus(NodeTaskMsg entity);
	
	public List<NodeTaskMsg> findNodeTaskMsgRightJoinWorkFlowList(List<QueryPara> queryParas,List<SortPara> sortParas,PageBean page) throws ServiceException ;
}
