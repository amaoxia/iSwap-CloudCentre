package com.ligitalsoft.datasharexchange.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

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
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.datasharexchange.service.IExchangeTransactService;
import com.ligitalsoft.model.changemanage.ExchangeSendTask;
import com.ligitalsoft.model.changemanage.ExchangeTransact;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.util.Costant;

/**
 * 
 * @author zhangx
 * 
 */
@Namespace("/exchange/transact")
@Action("transact")
@Scope("prototype")
@Results({
		@Result(name = "listAction", location = "transact!list.action", type = "redirectAction"),
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker") })
public class ExchangeTransactAction extends
		FreemarkerBaseAction<ExchangeTransact> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3932096332199068184L;
	private IExchangeTransactService exchangeTransactService;
	private ISysUserDeptService sysUserDeptService;
	private ISysDeptService sysDeptService;
	private Long taskId;// 任务ID

	@Override
	protected void onBeforeList() {
		SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
		Long deptid = sysUserDeptService.findByUserId(user.getId()).getDeptId();
		@SuppressWarnings("unused")
		SysDept dept = null;
		try {
			if (deptid != null && deptid != 0L) {
				dept = sysDeptService.findById(deptid);
				if (!dept.getDeptName().equals("信息管理中心")) {
					QueryPara para = new QueryPara();
					para.setName("e.sendTask.item.sysDept.id");
					para.setType(Constants.TYPE_LONG);
					para.setOp(Constants.OP_EQ);
					para.setValue(dept.getId().toString());
					queryParas.add(para);
				}
			}
		} catch (ServiceException e) {
			this.errorInfo = "查询异常,请联系管理员!";
		}
	}

	/**
	 * 添加信息
	 * 
	 * @return
	 */
	public String addTransact() {
		SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
		Long deptid = sysUserDeptService.findByUserId(user.getId()).getDeptId();
		try {
			SysDept dept = sysDeptService.findById(deptid);
			entityobj = new ExchangeTransact();
			entityobj.setDepartmentBySendDeptId(dept);// 催办部门
			entityobj.setSendUsername(user.getUserName());
			entityobj.setSendDate(new Date());
			String transactType = "";
			String content = "";
			String title = "";
		    transactType = URLDecoder.decode(getStringParameter("transactType"),"UTF-8");
			content =  URLDecoder.decode(getStringParameter("content"),"UTF-8");
			title =  URLDecoder.decode(getStringParameter("title"),"UTF-8");
			if (!StringUtils.isBlank(title)) {
				entityobj.setTitle(title);
			}
			if (!StringUtils.isBlank(transactType)) {
				entityobj.setTransactType(transactType);
			}
			if (!StringUtils.isBlank(content)) {
				entityobj.setContent(getStringParameter("content"));
			}

			ExchangeSendTask task = new ExchangeSendTask();
			task.setId(taskId);
			entityobj.setSendTask(task);
			exchangeTransactService.saveOrUpdate(entityobj);
			Struts2Utils.renderText("1", "encoding:GBK");
		} catch (ServiceException e) {
			this.errorInfo = "查询部门失败,请联系管理员!";
			log.error(errorInfo, e);
			Struts2Utils.renderText("0", "encoding:GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	@Override
	protected IBaseServices<ExchangeTransact> getEntityService() {
		return exchangeTransactService;
	}

	@Autowired
	public void setSysUserDeptService(ISysUserDeptService sysUserDeptService) {
		this.sysUserDeptService = sysUserDeptService;
	}

	@Autowired
	public void setSysDeptService(ISysDeptService sysDeptService) {
		this.sysDeptService = sysDeptService;
	}

	@Autowired
	public void setExchangeTransactService(
			IExchangeTransactService exchangeTransactService) {
		this.exchangeTransactService = exchangeTransactService;
	}
}
