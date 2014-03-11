package com.ligitalsoft.cloudnode.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

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
import com.common.utils.tree.ztree.Node;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudnode.service.IFtpListenService;
import com.ligitalsoft.cloudnode.service.IWorkFlowService;
import com.ligitalsoft.iswapmq.service.IFtpService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudnode.FtpListen;
import com.ligitalsoft.model.serverinput.FtpServerInfo;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;

/**
 * 远程目录监听
 * 
 * @Company 北京光码软件有限公司
 * @author fangbin
 * @version iSwap V6.0 数据交换平台
 * @date 2011-06-14
 * @Team 研发中心
 */
@Namespace("/cloudnode/ftplisten")
@Action("ftplisten")
@Scope("prototype")
@Results({
		@Result(name = "listAction", location = "ftplisten!list.action", type = "redirectAction", params = {
				"page.index", "${page.index}", "listenName", "${listenName}",
				"appMsgId", "${appMsgId}","deptId","${deptId}" }),
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker") })
public class FtpListenAction extends FreemarkerBaseAction<FtpListen> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -594948210679647798L;
	@Autowired
	private IFtpListenService ftpListenService;
	@Autowired
	private IWorkFlowService workFlowService;
	@Autowired
	private AppMsgService appMsgService;
	@Autowired
	private ISysUserDeptService sysUserDeptService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
	private IFtpService ftpService;

	private String xtree;
	private String status;
	private List<AppMsg> appMsgList = new ArrayList<AppMsg>();
	private List<FtpServerInfo> ftpServerList = new ArrayList<FtpServerInfo>();

	private String listenName;
	private String appMsgId;
	/* 部门ID */
	private Long deptId;

	@Override
	protected IBaseServices<FtpListen> getEntityService() {
		return ftpListenService;
	}

	/**
	 * 
	 * @author fangbin
	 */
	@Override
	public void onBeforeList() {

		if (null != deptId) {
			// 通过当前用户ID查询用户所属部门
			QueryPara queryPara = new QueryPara();
			//queryPara.setName("workFlow.sysDept.id");
			queryPara.setName("sysDept.id");
			queryPara.setType("Long");
			queryPara.setValue(deptId+"");
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
		/*if (appMsgId != null && !StringUtils.isBlank(appMsgId)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("appMsg.id");
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(appMsgId);
			queryPara.setOp(Constants.OP_EQ);
			queryParas.add(queryPara);
		}*/
		// 获得所有应用
		//appMsgList = appMsgService.findAll();
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
	public void onBeforeAddView() {
		// 应用 选项
		// appMsgList = appMsgService.findAll();
		ftpServerList = ftpService.findAll();
	}

	@Override
	public void onBeforeUpdate() {
		//entityobj.setAppMsg(entityobj.getWorkFlow().getAppMsg());
	}

	@Override
	public void onBeforeAdd() {
		//try {
		entityobj.setStatus("0");
		//WorkFlow workFlow = workFlowService.findById(entityobj.getWorkFlow().getId());
		//entityobj.setWorkFlow(workFlow);
		//entityobj.setAppMsg(workFlow.getAppMsg());
		Date date = new Date();
		Date nowdate = DateUtil.strToDate(DateUtils.format(date, "yyyy-MM-dd"));
		entityobj.setCreateDate(new java.sql.Date(nowdate.getTime()));
		//} catch (ServiceException e) {
			//log.error("查找流程失败!", e);
		//}
	}

	@Override
	public void onBeforeUpdateView() {
		FtpListen ftp;
		try {
			ftp = ftpListenService.findById(entityobj.getId());
			// 流程树
			//workFlowService.workFlowXTree(ftp.getWorkFlow().getId());
			// 应用 选项
			// appMsgList = appMsgService.findAll();
			ftpServerList = ftpService.findAll();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */

	public String delMany() {
		try {
			for (int i = 0; i < ids.length; i++) {
				FtpListen ftp = ftpListenService.findById(ids[i]);
				if (ftp.getStatus().equals("0")) {
					ftpListenService.delete(ftp);
				}
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAction";
	}

	/**
	 * 发布
	 * 
	 * @return
	 */
	public String updateStatus() {
		try {
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					FtpListen ftp = ftpListenService.findById(ids[i]);
					ftp.setStatus(status);
					ftpListenService.updateStatus(ftp);
					ftpListenService.update(ftp);
				}
			}
		} catch (ServiceException e) {
			log.error("", e);
		}
		return "listAction";
	}
	
	public String getFtpDataSourceJsonStr() {
		List<FtpListen> dataSourceList = ftpListenService.findFtpDataSourcesByDept("1", deptId);
		if(dataSourceList==null||dataSourceList.size()<=0)return null;
		List<Node> nodes = new ArrayList<Node>();
		for(FtpListen dataSource : dataSourceList){
			Node node = new Node();
			node.setId(dataSource.getId()+"");
			node.setName(dataSource.getListenName());
			nodes.add(node);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {

				if (name.equals("name") || name.equals("id")) {
					return false;
				} else {
					return true;
				}
			}
		});
		String dsJsonStr = JSONArray.fromObject(nodes, jsonConfig).toString();
		Struts2Utils.renderText(dsJsonStr, "encoding:GBK");
		return null;
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

	public List<FtpServerInfo> getFtpServerList() {
		return ftpServerList;
	}

	public void setFtpServerList(List<FtpServerInfo> ftpServerList) {
		this.ftpServerList = ftpServerList;
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
