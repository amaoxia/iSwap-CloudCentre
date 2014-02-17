package com.ligitalsoft.cloudnode.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.tools.ant.util.DateUtils;
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
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudnode.service.ILocalHostListenService;
import com.ligitalsoft.cloudnode.service.IWorkFlowService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudnode.LocalHostListen;
import com.ligitalsoft.model.cloudnode.WorkFlow;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;

/**
 * 本地目录监听
 * 
 * @Company 北京光码软件有限公司
 * @author fangbin
 * @version iSwap V6.0 数据交换平台
 * @date 2011-06-14
 * @Team 研发中心
 */
@Namespace("/cloudnode/localhostlisten")
@Action("localhostlisten")
@Scope("prototype")
@Results({
		@Result(name = "listAction", location = "localhostlisten!list.action", type = "redirectAction", params = {
				"page.index", "${page.index}", "listenName", "${listenName}",
				"appMsgId", "${appMsgId}", "deptId", "${deptId}" }),
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker") })
public class LocalHostListenAction extends
		FreemarkerBaseAction<LocalHostListen> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ILocalHostListenService localhostlistenService;
	@Autowired
	private IWorkFlowService workFlowService;
	@Autowired
	private AppMsgService appMsgService;
	@Autowired
	private ISysUserDeptService sysUserDeptService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;

	private String xtree;
	private String status;
	private List<AppMsg> appMsgList = new ArrayList<AppMsg>();

	private String listenName;
	private String appMsgId;
	private Long deptId;

	@Override
	protected IBaseServices<LocalHostListen> getEntityService() {
		return localhostlistenService;
	}

	/**
	 * @author fangbin
	 */
	@Override
	public void onBeforeList() {
		if (null != deptId) {
			// 通过当前用户ID查询用户所属部门
			QueryPara queryPara = new QueryPara();
			queryPara.setName("workFlow.sysDept.id");
			queryPara.setType("Long");
			queryPara.setValue(deptId.toString());
			queryPara.setOp("=");
			queryParas.add(queryPara);
		}

		if (listenName != null && !StringUtils.isBlank(listenName)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("listenName");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(listenName);
			queryPara.setOp(Constants.OP_LIKE);
			queryParas.add(queryPara);
		}

		if (appMsgId != null && !StringUtils.isBlank(appMsgId)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("appMsg.id");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(appMsgId);
			queryPara.setOp(Constants.OP_EQ);
			queryParas.add(queryPara);
		}
		// 获得所有应用
		appMsgList = appMsgService.findAll();
	}

	@Override
	public void onBeforeAddView() {
		// 流程树
		workFlowService.workFlowXTree(null);
		// 应用 选项
		// appMsgList = appMsgService.findAll();

	}

	@Override
	public void onBeforeAdd() {
		try {
			entityobj.setStatus("0");
			WorkFlow workFlow = workFlowService.findById(entityobj
					.getWorkFlow().getId());
			entityobj.setWorkFlow(workFlow);
			entityobj.setAppMsg(workFlow.getAppMsg());
			Date date = new Date();
			Date nowdate = DateUtil.strToDate(DateUtils.format(date,
					"yyyy-MM-dd"));
			entityobj.setCreateDate(nowdate);
		} catch (ServiceException e) {
			log.error("查找流程失败!", e);
		}
	}

	/**
	 * 流程树
	 * 
	 * @return
	 */
	public String getWorkFlowTree() {
		JSONArray array = workFlowService.workFlowXTree(null);
		if (array != null) {
			if (array != null) {
				Struts2Utils.renderJson(array, "encoding:GBK");
			}
		}
		return null;
	}

	@Override
	public void onBeforeUpdateView() {
		LocalHostListen localhostlisten;
		try {
			localhostlisten = localhostlistenService
					.findById(entityobj.getId());
			// 应用 选项
			appMsgList = appMsgService.findAll();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBeforeUpdate() {
		entityobj.setAppMsg(entityobj.getWorkFlow().getAppMsg());
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */

	public String delMany() {
		try {
			for (int i = 0; i < ids.length; i++) {
				LocalHostListen localhostlisten = localhostlistenService
						.findById(ids[i]);
				if (localhostlisten.getStatus().equals("0")) {
					localhostlistenService.delete(localhostlisten);
				}
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAction";
	}

	/**
	 * 更改状态
	 * 
	 * @return
	 */
	public String updateStatus() {
		try {
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					LocalHostListen localhostlisten = localhostlistenService
							.findById(ids[i]);
					localhostlisten.setStatus(status);
					localhostlistenService.update(localhostlisten);
					localhostlistenService.updateStatus(localhostlisten);
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAction";
	}

	public String getXtree() {
		return xtree;
	}

	public void setXtree(String xtree) {
		this.xtree = xtree;
	}

	public List<AppMsg> getAppMsgList() {
		return appMsgList;
	}

	public void setAppMsgList(List<AppMsg> appMsgList) {
		this.appMsgList = appMsgList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getListenName() {
		return listenName;
	}

	public void setListenName(String listenName) {
		this.listenName = listenName;
	}

	public String getAppMsgId() {
		return appMsgId;
	}

	public void setAppMsgId(String appMsgId) {
		this.appMsgId = appMsgId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

}
