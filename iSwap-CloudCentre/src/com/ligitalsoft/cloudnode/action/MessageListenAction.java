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

import com.common.framework.dao.QueryPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.date.DateUtil;
import com.common.utils.tree.ztree.Node;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudnode.service.IMessageListenService;
import com.ligitalsoft.cloudnode.service.IWorkFlowService;
import com.ligitalsoft.iswapmq.service.IMQService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudnode.MessageListen;
import com.ligitalsoft.model.serverinput.JmsServerInfo;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;

/**
 * 消息监听管理
 * 
 * @Company 北京光码软件有限公司
 * @author fangbin
 * @version iSwap V6.0 数据交换平台
 * @date 2011-06-13
 * @Team 研发中心
 */
@Namespace("/cloudnode/messagelisten")
@Action("messagelisten")
@Scope("prototype")
@Results({
		@Result(name = "listAction", location = "messagelisten!list.action", type = "redirectAction" ,params = {"deptId","${deptId}"}),
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker") })
public class MessageListenAction extends FreemarkerBaseAction<MessageListen> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IMessageListenService messagelistenService;
	@Autowired
	private IWorkFlowService workFlowService;
	@Autowired
	private AppMsgService appMsgService;
	@Autowired
	private ISysUserDeptService sysUserDeptService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
    private IMQService  mqService;
	
	// private IMQService mqService;

	private String xtree;
	private String status;
	private Long deptId;
	private List<AppMsg> appMsgList = new ArrayList<AppMsg>();
	private List<JmsServerInfo> jmsServerList = new ArrayList<JmsServerInfo>();

	@Override
	protected IBaseServices<MessageListen> getEntityService() {
		return messagelistenService;
	}

	/**
	 * @author fangbin
	 */
	@Override
	public void onBeforeList() {
		if(null!=deptId){
		//通过当前用户ID查询用户所属部门
		QueryPara queryPara = new QueryPara();
		//queryPara.setName("workFlow.sysDept.id");
		queryPara.setName("sysDept.id");
		queryPara.setType("Long");
		queryPara.setValue(deptId+"");
		queryPara.setOp("=");
		queryParas.add(queryPara);
		}
		
		//获得所有应用
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

	/**
	 * 树页面
	 * 
	 * @return
	 */
	public String treeMain() {

		return "treeMain";
	}

	@Override
	public void onBeforeAddView() {
		// 应用 选项
//		appMsgList = appMsgService.findAll();
		 jmsServerList=mqService.findAll();
	}

	@Override
	public void onBeforeAdd() {
		//try {
			entityobj.setStatus("0");
			/*WorkFlow workFlow = workFlowService.findById(entityobj
					.getWorkFlow().getId());
			entityobj.setWorkFlow(workFlow);
			entityobj.setAppMsg(workFlow.getAppMsg());*/
			Date date = new Date();
			Date nowdate = DateUtil.strToDate(DateUtils.format(date,
					"yyyy-MM-dd"));
			entityobj.setCreateDate(nowdate);
		/*} catch (ServiceException e) {
			log.error("查找流程失败!", e);
		}*/
	}

	@Override
	public void onBeforeUpdateView() {
		MessageListen message;
		try {
			message = messagelistenService.findById(entityobj.getId());
			jmsServerList=mqService.findAll();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onBeforeUpdate(){
		//entityobj.setAppMsg(entityobj.getWorkFlow().getAppMsg());
	}
	/**
	 * 批量删除
	 * 
	 * @return
	 */

	public String delMany() {
		try {
			for (int i = 0; i < ids.length; i++) {
				MessageListen message = messagelistenService.findById(ids[i]);
				if (message.getStatus().equals("0")) {
					messagelistenService.delete(message);
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
		String path = getHttpServletRequest().getSession().getServletContext()
				.getRealPath("/");
		path = path.substring(0, 3) + "aa";
		try {
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					MessageListen message = messagelistenService
							.findById(ids[i]);
					message.setStatus(status);
					messagelistenService.update(message);
				}
			}
//			if (id != null) {
//				MessageListen message = messagelistenService.findById(id);
//				message.setStatus(status);
//				messagelistenService.update(message);
//			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAction";
	}
	
	public String getMQDataSourceJsonStr() {
		List<MessageListen> dataSourceList = messagelistenService.findMQDataSourcesByDept("1", deptId);
		if(dataSourceList==null||dataSourceList.size()<=0)return null;
		List<Node> nodes = new ArrayList<Node>();
		for(MessageListen dataSource : dataSourceList){
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

	public List<JmsServerInfo> getJmsServerList() {
		return jmsServerList;
	}

	public void setJmsServerList(List<JmsServerInfo> jmsServerList) {
		this.jmsServerList = jmsServerList;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
}
