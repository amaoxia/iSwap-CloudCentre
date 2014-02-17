/*
 * @(#)TaskAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.dao.Constants;
import com.common.framework.dao.QueryPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.datasharexchange.service.IChangeItemService;
import com.ligitalsoft.datasharexchange.service.IExchangeSendTaskService;
import com.ligitalsoft.datasharexchange.service.ITaskService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.ExchangeSendTask;
import com.ligitalsoft.model.changemanage.ExchangeTransact;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.util.Costant;

/**
 * 任务ACTION
 * 
 * @author daic
 * @since 2011-08-17 15:15:07
 * @name com.ligitalsoft.cloudstorage.action.TaskAction.java
 * @version 1.0
 */
@Scope("prototype")
@Namespace("/exchange/task")
@Results({ @Result(name = "listAction", location = "taskAction!list.action", type = "redirectAction") ,@Result(name = "succ", location = "../../common/succ.ftl", type = "freemarker") })
@Action("taskAction")

public class TaskAction extends FreemarkerBaseAction<ExchangeSendTask> {

	private static final long serialVersionUID = 5854980905054524517L;
	private IChangeItemService changeItemService;
	private IExchangeSendTaskService exchangeSendTaskService;
	private ITaskService taskService;
	private ISysDeptService sysDeptService;
	private AppMsgService appMsgService;
	private List<SysDept> sysDeptList;
	private String deptId;// 部门ID
	private String itemId;// 指标ID
	private String begin;// 任务开始时间
	private String end;// 任务结束时间
	private List<String[]> buildResult;
	private ISysUserDeptService sysUserDeptService;
	private List<Object[]> sendDeptList; // 部门列表
	private List<ExchangeSendTask> sendTaskList;// 发送任务列表
	private ExchangeTransact exchangeTransact;// 催办信息

	@Override
	public String list() {
		SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
		Long deptid = sysUserDeptService.findByUserId(user.getId()).getDeptId();
		SysDept dept = null;
		try {
			if (deptid != null && deptid != 0L) {
				dept = sysDeptService.findById(deptid);
				if (!dept.getDeptName().equals("信息管理中心")) {
					QueryPara para = new QueryPara();
					para.setName("e.item.sysDept.id");
					para.setType(Constants.TYPE_LONG);
					para.setOp(Constants.OP_EQ);
					para.setValue(dept.getId().toString());
					queryParas.add(para);
				}
			}
		} catch (ServiceException e) {
			return ERROR;
		}
		return super.list();

	}

	/**
	 * 生成任务
	 * 
	 * @return
	 */
	public String build() {
		List<ChangeItem> itemlist = new ArrayList<ChangeItem>();
		if (!deptId.equalsIgnoreCase("0") && itemId.equalsIgnoreCase("0")) {
			// 部门所有指标项
			itemlist = changeItemService.findListByDeptId(Long.valueOf(deptId));
		} else if (!deptId.equalsIgnoreCase("0")
				&& !itemId.equalsIgnoreCase("0")) {
			// 单个指标项
			try {
				itemlist.add(changeItemService.findById(Long.valueOf(itemId)));
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else {
			// 所有指标项
			itemlist = changeItemService.findAll();
		}

		buildResult = taskService.buildTask(itemlist, begin, end);

		return "succ";
	}

	@Override
	public String add() {
		return super.add();
	}

	@Override
	public String addView() {
		sysDeptList = sysDeptService.findAll();
		return super.addView();
	}

	@Override
	public String view() {
		SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
		Long deptid = sysUserDeptService.findByUserId(user.getId()).getDeptId();
		SysDept dept = null;
		try {
			dept = sysDeptService.findById(deptid);
			deptId = dept.getId().toString();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		String currentMonth = DateUtil.getCurrentDate("yyyy-MM");
		if (StringUtils.isBlank(begin))
			begin = currentMonth + "-01";
		if (StringUtils.isBlank(end))
			end = DateUtil.getCurrentDate("yyyy-MM-dd");
		// begin="2011-08-12 23:59:59";
		// end="2011-08-12 23:59:59";
		sendDeptList = exchangeSendTaskService.getSendDeptList(begin, end);
		List<AppMsg> appMsgs=new ArrayList<AppMsg>();
		appMsgs=appMsgService.findAll();
		getHttpServletRequest().setAttribute("app", appMsgs);
		return super.view();

	}

	/**
	 * ajax设置催办
	 * 
	 * @return
	 */
	public String cuiban() {
		try {
			ExchangeSendTask st = exchangeSendTaskService.findById(id);
			ChangeItem item = st.getItem();
			taskService.buildCuiban(item, st);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 添加催办信息
	 * 
	 * @return
	 */
	public String addExchangeTransact() {
		
		return "addTransactView";
	}

	/**
	 * 任务信息
	 * 
	 * @return
	 */
	public String taskView() {
		try {
			prepareModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "taskView";
	}

	@Autowired
	public void setExchangeSendTaskService(
			IExchangeSendTaskService exchangeSendTaskService) {
		this.exchangeSendTaskService = exchangeSendTaskService;
	}

	public List<Object[]> getSendDeptList() {
		return sendDeptList;
	}

	public void setSendDeptList(List<Object[]> sendDeptList) {
		this.sendDeptList = sendDeptList;
	}

	public List<ExchangeSendTask> getSendTaskList() {
		return sendTaskList;
	}

	public void setSendTaskList(List<ExchangeSendTask> sendTaskList) {
		this.sendTaskList = sendTaskList;
	}

	@Autowired
	public void setSysUserDeptService(ISysUserDeptService sysUserDeptService) {
		this.sysUserDeptService = sysUserDeptService;
	}

	@Autowired
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	public void setChangeItemService(IChangeItemService changeItemService) {
		this.changeItemService = changeItemService;
	}

	@Autowired
	public void setAppMsgService(AppMsgService appMsgService) {
		this.appMsgService = appMsgService;
	}

	@Override
	protected IBaseServices<ExchangeSendTask> getEntityService() {
		return exchangeSendTaskService;
	}

	public ExchangeTransact getExchangeTransact() {
		return exchangeTransact;
	}

	public void setExchangeTransact(ExchangeTransact exchangeTransact) {
		this.exchangeTransact = exchangeTransact;
	}

	public List<SysDept> getSysDeptList() {
		return sysDeptList;
	}

	public void setSysDeptList(List<SysDept> sysDeptList) {
		this.sysDeptList = sysDeptList;
	}

	@Autowired
	public void setSysDeptService(ISysDeptService sysDeptService) {
		this.sysDeptService = sysDeptService;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public List<String[]> getBuildResult() {
		return buildResult;
	}

	public void setBuildResult(List<String[]> buildResult) {
		this.buildResult = buildResult;
	}

}
