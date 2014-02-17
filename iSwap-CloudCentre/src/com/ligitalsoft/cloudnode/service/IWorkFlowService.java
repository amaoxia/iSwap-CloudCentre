package com.ligitalsoft.cloudnode.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudnode.WorkFlow;

public interface IWorkFlowService extends IBaseServices<WorkFlow> {

	/**
	 * 业务流程树(xtree radio)
	 * 
	 * @author fangbin
	 * @return
	 */
	public JSONArray workFlowXTree(Long checkId);

	/**
	 * 工作流程测试
	 * 
	 * @author fangbin
	 * @param workflow
	 * @param Xml
	 * @return
	 */
	public String runWorkFlow(Long workflowId, String Xml);

	/**
	 * 根据指标查询流程
	 * 
	 * @param itemId
	 * @return
	 */
	public List<WorkFlow> findListByItemId(Long itemId);
	/**
	 * 查询部门下的所有流程
	 * 
	 * @author fangbin
	 * @param deptId 部门Id
	 * @return
	 */
	public List<WorkFlow> findByDeptId(Long deptId);
	/**
	 * 查询部门下的所有流程生成ZTree
	 * 
	 * @author fangbin
	 * @param deptId //部门id
	 * @param workFlowId　//被选中的流程id
	 * @return
	 */
	public JSONArray workFlowZtreeByDeptId(Long deptId,Long workFlowId);
	/**
	 * 更改发布状态 时修改缓存中的部署的流程信息
	 * @param entity
	 * @author hudaowan
	 * @date 2011-9-4 下午05:32:50
	 */
	public void updateStatus(WorkFlow entity) ;
}
