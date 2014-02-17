package com.ligitalsoft.cloudnode.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.cloudnode.WorkFlow;

/**
 * 流程管理
 * 
 * @Company 北京光码软件有限公司
 * @author fangbin
 * @version iSwap V6.0 数据交换平台
 * @date 2011-06-14
 * @Team 研发中心
 */
@Repository
public class WorkFlowDao extends EntityHibernateDao<WorkFlow> {
	/**
	 * 通过应用ＩＤ查询工作流
	 * 
	 * @author fangbin
	 * @param AppMsgId
	 * @return
	 */
	public List<WorkFlow> getAllByAppMsg(Long AppMsgId) {
		String hql = " from WorkFlow wf where wf.appMsg.id=" + AppMsgId;
		List<WorkFlow> workFlowList = this.findListByHql(hql);
		return workFlowList;
	}

	/**
	 * 根据itemId查询流程
	 * 
	 * @param itemId
	 * @return
	 */
	public List<WorkFlow> findListByItemId(Long itemId) {
		String hql = "from WorkFlow e where e.itemId=" + itemId.toString();
		List<WorkFlow> workFlowList = this.findListByHql(hql);
		return workFlowList;
	}
	/**
	 * 查询部门下的所有流程
	 * 
	 * @author fangbin
	 * @param deptId 部门Id
	 * @return
	 */
	public List<WorkFlow> findByDeptId(Long deptId){
		String hql = "from WorkFlow e ";
		if(null!=deptId){
			hql+=" where e.sysDept.id=" + deptId;
		}
			
		List<WorkFlow> workFlowList = this.findListByHql(hql);
		return workFlowList;
	}
}
