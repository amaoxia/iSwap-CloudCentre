/*
 * @(#)CloudNodeInfoAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudcenter.action;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.codehaus.cargo.container.RemoteContainer;
import org.codehaus.cargo.container.configuration.RuntimeConfiguration;
import org.codehaus.cargo.container.deployable.Deployable;
import org.codehaus.cargo.container.deployer.Deployer;
import org.codehaus.cargo.container.property.GeneralPropertySet;
import org.codehaus.cargo.container.property.RemotePropertySet;
import org.codehaus.cargo.container.property.ServletPropertySet;
import org.codehaus.cargo.container.tomcat.Tomcat7xRemoteContainer;
import org.codehaus.cargo.container.tomcat.Tomcat7xRemoteDeployer;
import org.codehaus.cargo.container.tomcat.TomcatRuntimeConfiguration;
import org.codehaus.cargo.container.tomcat.TomcatWAR;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringConvertion;
import com.common.utils.common.StringUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudcenter.service.CloudNodeInfoService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.cloudcenter.CouldNodeDept;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.service.ISysDeptService;

/**
 * 云节点管理
 * 
 * @author lifh
 * @mail wslfh2005@163.com
 * @since 2011-6-16 上午10:00:39
 * @name com.ligitalsoft.cloudcenter.action.CloudNodeInfoAction.java
 * @version 1.0
 */
@Namespace("/cloudcenter/cloudNodeInfo")
@Results({
		@Result(name = StrutsAction.RELOAD, location = "cloudNodeInfo!list.action", type = "redirect", params = {
				"page.index", "${page.index}", "nodesName", "${nodesName}",
				"appId", "${appId}", "deptId", "${deptId}" }),
		@Result(name = "refresh", location = "../saveResult.ftl", type = "freemarker"),
		@Result(name = "deptTree", location = "deptTree.ftl", type = "freemarker") })
@Action("cloudNodeInfo")
public class CloudNodeInfoAction extends FreemarkerBaseAction<CloudNodeInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8685231515309700921L;

	private CloudNodeInfoService cloudNodeInfoService;
	private ISysDeptService sysDeptService;
	private AppMsgService appMsgService;
	private String deptIds;
	private Long[] appMsgIds;
	private Long appId = 0l;
	private String nodesName;
	private List<AppMsg> appMsgs;
	List<CloudNodeInfo> cloudNodeInfoList;
	private String ip;
	private String port;
	private Long deptId;

	@Override
	protected void onBeforeAddView() {
		super.onBeforeAddView();
		//appMsgs = appMsgService.findAllByProperty();
	}

	@Override
	protected void onBeforeList() {
		super.onBeforeList();

	}

	public void prepareNodeStatusMonitorView() throws Exception {
		prepareModel();
	}

	/**
	 * 得到部门树
	 * 
	 * @return
	 */
	public String getDeptTree() {
		JSONArray array = cloudNodeInfoService.getDeptTreeById(id);
		if (array != null) {
			Struts2Utils.renderJson(array, "encoding:GBK");
		}
		return null;
	}

	public String nodeStatusMonitorView() {
		this.onBeforeView();

		this.onAfterView();
		return "nodeStatusMonitorView";
	}

	@SuppressWarnings("static-access")
	public String nodeStatusMonitor() {
		try {
			this.onBeforeList();
			// 分页查询
			this.listDatas = this.cloudNodeInfoService.findAllByPage(appId,
					nodesName, page);

			List<CloudNodeInfo> nodes = new ArrayList<CloudNodeInfo>();
			for (CloudNodeInfo nodeInfo : listDatas) {
				nodeInfo.setRunStatus(testConn(nodeInfo.getAddress(),
						nodeInfo.getPort()));
				nodes.add(nodeInfo);
			}
			listDatas = nodes;
			this.onAfterList();
			return "nodeStatusMonitor";
		} catch (Exception e) {
			this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	@SuppressWarnings("static-access")
	public String list() {
		try {
			/*this.onBeforeList();
			// 分页查询
			this.listDatas = this.cloudNodeInfoService.findAllByPage(appId,
					nodesName, page);
			this.onAfterList();*/
			super.list();
			return this.LIST;
		} catch (Exception e) {
			this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	@Override
	protected void onAfterList() {
		super.onAfterList();
		for (CloudNodeInfo cloudNodeInfo : listDatas) {
			resetColudNodeInfo(cloudNodeInfo);
		}
		/*appMsgs = appMsgService.findAllByProperty();
		*/
	}

	@Override
	protected void onBeforeUpdateView() {
		super.onBeforeUpdateView();
		/*appMsgs = appMsgService.findAllByProperty();*/
		resetColudNodeInfo(entityobj);
	}

	@Override
	protected void onBeforeView() {
		super.onBeforeUpdateView();
		/*appMsgs = appMsgService.findAllByProperty();*/
		resetColudNodeInfo(entityobj);
	}

	private void resetColudNodeInfo(CloudNodeInfo entityobj) {
		/*String appMsgNames = "";
		String appMsgIds = "";
		for (AppCloudNode appCloudNode : entityobj.getAppCloudNode()) {
			String appName = appCloudNode.getAppMsg().getAppName();
			if (StringUtils.isBlank(appName)) {
				appName = "(空)";
			}
			appMsgNames = appMsgNames + "," + appName;
			appMsgIds = appMsgIds + "," + appCloudNode.getAppMsg().getId();
		}
*/
		String sysDeptNames = "";
		String sysDeptIds = "";
		for (CouldNodeDept nodeDept : entityobj.getNodeDept()) {
			sysDeptNames = sysDeptNames + ","
					+ nodeDept.getDept().getDeptName();
			sysDeptIds = sysDeptIds + "," + nodeDept.getDept().getId();
		}
		sysDeptNames = sysDeptNames.substring(1);
		sysDeptIds = sysDeptIds.substring(1);
		//appMsgNames = appMsgNames.substring(1);
		//appMsgIds = appMsgIds.substring(1);
		//entityobj.setAppMsgNames(appMsgNames);
		//entityobj.setAppMsgIds(appMsgIds);
		entityobj.setSysDeptIds(sysDeptIds);
		entityobj.setSysDeptNames(sysDeptNames);
	}

	/**
	 * 检查地址是ip地址是否唯一
	 * 
	 * @return
	 * @author zhangx
	 * @2010-12-30 下午08:10:24
	 */
	public String checkAddress() {
		String result = "";
		String id = getHttpServletRequest().getParameter("id");
		String address = getHttpServletRequest().getParameter("address").trim();
		try {
			entityobj = cloudNodeInfoService.findUniqueByProperty("address",
					address);
			if (entityobj == null) {
				result = "succ";
			} else {
				if (!StringUtils.isBlank(id)) {
					if (entityobj.getId().toString().equals(id)) {
						result = "succ";
					}
				}
			}
			Struts2Utils.renderText(result, "encoding:GBK");
		} catch (ServiceException e) {
			log.error("NodeInfoAction exception", e);
		}
		return null;
	}

	/**
	 * 节点注册 检查名称是否唯一
	 * 
	 * @return
	 * @author zhangx
	 * @2010-12-30 下午08:10:24
	 */
	public String checkNodesName() {
		String result = "";
		String id = getHttpServletRequest().getParameter("id");
		String nodesName = getHttpServletRequest().getParameter("nodesName")
				.trim();
		try {
			nodesName = new String(nodesName.getBytes("ISO-8859-1"), "UTF-8");
			CloudNodeInfo nodeInfo = cloudNodeInfoService.findUniqueByProperty(
					"nodesName", nodesName);
			if (nodeInfo == null) {
				result = "succ";
			} else {
				if (!StringUtils.isBlank(id)) {
					if (nodeInfo.getId().toString().equals(id)) {
						result = "succ";
					}
				}
			}
			Struts2Utils.renderText(result, "encoding:GBK");
		} catch (Exception e) {
			log.error("NodeInfoAction exception", e);
		}
		return null;
	}

	public String add() {
		super.add();
		entityobj.setCode(entityobj.getId()+"");
		super.update();
		return "refresh";
	}

	public String update() {
		super.update();
		return "refresh";
	}

	@Override
	protected void onBeforeUpdate() {
		super.onBeforeUpdate();
		cloudNodeInfoService.removeAppCloudNode(id);
		cloudNodeInfoService.removeCouldNodeDept(id);
		this.onBeforeAdd();
	}

	@Override
	protected void onBeforeAdd() {
		super.onBeforeAdd();
		/*for (Long appMsgId : appMsgIds) {
			AppCloudNode appCloudNode = new AppCloudNode();
			AppMsg appMsg;
			try {
				appMsg = appMsgService.findById(appMsgId);
				appCloudNode.setAppMsg(appMsg);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			appCloudNode.setCloudNodeInfo(entityobj);
			entityobj.getAppCloudNode().add(appCloudNode);
		}*/

		for (Long deptId : StringConvertion.convertionToLongs(deptIds, ",")) {
			CouldNodeDept couldNodeDept = new CouldNodeDept();
			couldNodeDept.setCouldNode(entityobj);
			SysDept dept;
			try {
				dept = sysDeptService.findById(deptId);
				couldNodeDept.setDept(dept);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			entityobj.getNodeDept().add(couldNodeDept);
		}

	}

	@SuppressWarnings("static-access")
	public String deploy() {
		/*
		 * InputStream in =
		 * this.getClass().getClassLoader().getResourceAsStream(
		 * "config/config.properties"); Properties prop = new Properties(); try
		 * { prop.load(in); } catch (IOException e) { throw new
		 * RuntimeException("得不到文件", e); }
		 * 
		 * try {
		 * DeployerWebServiceClient.getDeployerWebService(prop.getProperty(
		 * "deployerUrl")).deploy(node.getAddress()); } catch (Exception e) {
		 * throw new RuntimeException("部署失败，请查看网络！" + e); }
		 */
		try {
			CloudNodeInfo node = cloudNodeInfoService.findById(id);

			Deployer deploy = getDeployer(node.getAddress(), node.getPort());
			deploy.deploy(getDeployable());
			node.setStatus(1);
			cloudNodeInfoService.update(node);
		} catch (ServiceException e) {
			this.errorInfo = "部署节点失败!";
			log.error(this.errorInfo, e);
			return this.ERROR;
		}

		return StrutsAction.RELOAD;
	}

	private Deployer getDeployer(String ip, String port) {

		RuntimeConfiguration configuration = new TomcatRuntimeConfiguration();
		if (!StringUtils.isBlank(ip)) {
			configuration.setProperty(GeneralPropertySet.HOSTNAME,
					ip.toString());
		}
		if (!StringUtils.isBlank(port)) {
			configuration.setProperty(ServletPropertySet.PORT, port);
			// configuration.setProperty(GeneralPropertySet.RMI_PORT,
			// port.trim());
		}
		configuration.setProperty(RemotePropertySet.USERNAME, "tomcat");
		configuration.setProperty(RemotePropertySet.PASSWORD, "tomcat");
		RemoteContainer container = new Tomcat7xRemoteContainer(configuration);
		Deployer deploy = new Tomcat7xRemoteDeployer(container);
		return deploy;
	}

	private Deployable getDeployable() {
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("config/config.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (IOException e) {
			throw new RuntimeException("得不到文件", e);
		}

		Deployable deployable = new TomcatWAR(this.getClass().getClassLoader()
				.getResource(prop.get("webappname") + "").getPath());
		return deployable;
	}

	/**
	 * 连接测试
	 * 
	 * @return
	 * @author b
	 */
	public void connTest() {
		Socket server = null;
		try {
			server = new Socket();
			InetSocketAddress address = new InetSocketAddress(ip.trim(),
					Integer.parseInt(port.trim()));
			server.connect(address);

			Struts2Utils.renderText(Constant.STATUS_NORMAL, "encoding:GBK");
		} catch (UnknownHostException e) {
			Struts2Utils.renderText(Constant.STATUS_EXCEP, "encoding:GBK");
		} catch (IOException e) {
			Struts2Utils.renderText(Constant.STATUS_EXCEP, "encoding:GBK");
		} catch (NumberFormatException e) {
			Struts2Utils.renderText(Constant.STATUS_EXCEP, "encoding:GBK");
		} finally {
			try {
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String unDeploy() throws Exception {
		CloudNodeInfo node = cloudNodeInfoService.findById(id);
		/*
		 * InputStream in =
		 * this.getClass().getClassLoader().getResourceAsStream(
		 * "config/config.properties"); Properties prop = new Properties(); try
		 * { prop.load(in); } catch (IOException e) { throw new
		 * RuntimeException("得不到文件", e); } try {
		 * DeployerWebServiceClient.getDeployerWebService
		 * (prop.getProperty("deployerUrl")).undeploy(node.getAddress()); }
		 * catch (Exception e) { throw new RuntimeException("取消部署失败，请查看网络！" +
		 * e); }
		 */
		Deployer deploy = getDeployer(node.getAddress(), node.getPort());
		deploy.undeploy(getDeployable());
		node.setStatus(0);
		cloudNodeInfoService.update(node);
		return StrutsAction.RELOAD;
	}

	public String testConn(String ip, String port) {
		Socket server = null;
		try {
			server = new Socket();
			if (!StringUtils.isBlank(ip) && !StringUtils.isBlank(port)) {
				InetSocketAddress address = new InetSocketAddress(ip.trim(),
						Integer.parseInt(port.trim()));
				server.connect(address);
			}
			return Constant.STATUS_NORMAL;
		} catch (UnknownHostException e) {
			return Constant.STATUS_EXCEP;
		} catch (IOException e) {
			return Constant.STATUS_EXCEP;
		} catch (NumberFormatException e) {
			return Constant.STATUS_EXCEP;
		} finally {
			try {
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 机构树
	 * 
	 * @return
	 * @author lifh
	 */
	public String deptTree() {
		return "deptTree";
	}

	/**
	 * 部门树
	 * 
	 * @return
	 */

	public String deptTreeStr() {
		JSONArray array = sysDeptService.depTree();
		Struts2Utils.renderJson(array, "encoding:GBK");
		return null;
	}

	/**
	 * 通过部门id异步查询相关云节点
	 * 
	 * @author fangbin
	 * @return
	 */
	public String getNodeByDeptId() {
		cloudNodeInfoList = cloudNodeInfoService.findListNodeByDeptId(deptId);
		List<CloudNodeInfo> datas = new ArrayList<CloudNodeInfo>();
		for (CloudNodeInfo info : cloudNodeInfoList) {
			info.setAppCloudNode(null);
			info.setNodeDept(null);
			datas.add(info);
		}
		Struts2Utils.renderJson(datas, "encoding:GBK");
		return null;
	}

	@Override
	protected IBaseServices<CloudNodeInfo> getEntityService() {
		return cloudNodeInfoService;
	}

	@Autowired
	public void setCloudNodeInfoService(
			CloudNodeInfoService cloudNodeInfoService) {
		this.cloudNodeInfoService = cloudNodeInfoService;
	}

	public ISysDeptService getSysDeptService() {
		return sysDeptService;
	}

	@Autowired
	public void setSysDeptService(ISysDeptService sysDeptService) {
		this.sysDeptService = sysDeptService;
	}

	public List<AppMsg> getAppMsgs() {
		return appMsgs;
	}

	public void setAppMsgs(List<AppMsg> appMsgs) {
		this.appMsgs = appMsgs;
	}

	public AppMsgService getAppMsgService() {
		return appMsgService;
	}

	@Autowired
	public void setAppMsgService(AppMsgService appMsgService) {
		this.appMsgService = appMsgService;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public Long[] getAppMsgIds() {
		return appMsgIds;
	}

	public void setAppMsgIds(Long[] appMsgIds) {
		this.appMsgIds = appMsgIds;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getNodesName() {
		return nodesName;
	}

	public void setNodesName(String nodesName) {
		this.nodesName = nodesName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public List<CloudNodeInfo> getCloudNodeInfoList() {
		return cloudNodeInfoList;
	}

	public void setCloudNodeInfoList(List<CloudNodeInfo> cloudNodeInfoList) {
		this.cloudNodeInfoList = cloudNodeInfoList;
	}

}
