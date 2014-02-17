package com.ligitalsoft.esb.service;

import java.util.List;

import com.common.framework.dao.QueryPara;
import com.common.framework.dao.SortPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.cloudnode.NodeTaskMsg;
import com.ligitalsoft.model.esb.EsbTaskMsg;

public interface IEsbTaskMsgService extends IBaseServices<EsbTaskMsg> {
	
	/**
	 * tree
	 * 根据应用获取流程树
	 * @return 
	 * @author  hudaowan
	 * @date 2011-10-19 下午06:29:23
	 */
	public String workFlowXTree(Long checkId);

	public List<EsbTaskMsg> findEsbTaskMsgRightJoinWorkFlowList(List<QueryPara> queryParas,List<SortPara> sortParas,PageBean page) throws ServiceException ;
}
