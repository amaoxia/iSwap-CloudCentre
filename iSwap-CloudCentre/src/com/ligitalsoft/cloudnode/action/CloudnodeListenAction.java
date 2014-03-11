package com.ligitalsoft.cloudnode.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

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
import com.common.jobtool.ELTool;
import com.common.utils.tree.ztree.Node;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.cloudnode.service.ICloudnodeListenService;
import com.ligitalsoft.cloudnode.service.IWorkFlowService;
import com.ligitalsoft.model.cloudnode.CloudnodeListen;

/**
 * 云存储监听
 * 
 * @author arcgismanager
 * 
 */
@Namespace("/cloudnode/cloudnodeListen")
@Action("cloudnodeListen")
@Scope("prototype")
@Results({
		@Result(name = "listAction", location = "cloudnodeListen!list.action", type = "redirectAction", params = {
				"page.index", "${page.index}", "deptId", "${deptId}" }),
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker") })
public class CloudnodeListenAction extends
		FreemarkerBaseAction<CloudnodeListen> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6657972800429850501L;
	private ICloudnodeListenService cloudnodeListenService;
	private IWorkFlowService workFlowService;
	private String status;
	private Long deptId;
	private String seconds; // 秒
	private String branch; // 分
	private String time; // 时
	private String day; // 天
	private String[] week; // 周
	private String[] month; // 月
	private String executeTime; // 执行时间
	private String type;

	@Override
	protected void onBeforeList() {
		super.onBeforeList();
		if (null != deptId) {
			QueryPara para = new QueryPara();
			//para.setName("workFlow.sysDept.id");
			para.setName("sysDept.id");
			para.setType(Constants.TYPE_LONG);
			para.setOp(Constants.OP_EQ);
			para.setValue(deptId + "");
			queryParas.add(para);
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

	/**
	 * 更改状态
	 * 
	 * @return
	 */
	public String updateStatus() {
		try {
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					CloudnodeListen cloudnodeListen = cloudnodeListenService
							.findById(ids[i]);
					cloudnodeListen.setStatus(status);
					cloudnodeListenService.update(cloudnodeListen);
					cloudnodeListenService.updateStatus(cloudnodeListen);
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAction";
	}

	@Override
	public void onBeforeAdd() {
		if (entityobj == null) {
			entityobj = new CloudnodeListen();
		}
		entityobj.setStatus("0");// 未启用
		/*entityobj.setHisDate(DateUtil.getDateTime());
		entityobj.setType(type);
		String timeLoad = "";
		String cron = "";
		if ("1".equals(type)) {
			entityobj.setTimes(seconds);
			cron = this.getCron(type, seconds, "");
		}
		if ("2".equals(type)) {
			entityobj.setTimes(branch);
			cron = this.getCron(type, branch, "");
		}
		if ("3".equals(type)) {
			entityobj.setTimes(time);
			cron = this.getCron(type, time, "");
		}
		if ("4".equals(type)) {
			entityobj.setTimes(day);
			cron = this.getCron(type, day, "");
		}
		if ("5".equals(type)) {
			for (String one : week) {
				if (one == null)
					continue;
				timeLoad = timeLoad + "," + one;
			}
			String weeks = timeLoad.substring(1, timeLoad.length());
			cron = this.getCron(type, weeks, executeTime);
			timeLoad = timeLoad + "," + this.executeTime;
			entityobj.setTimes(timeLoad);
		}
		if ("6".equals(type)) {
			for (String one : month) {
				if (one == null)
					continue;
				timeLoad = timeLoad + "," + one;
			}
			String months = timeLoad.substring(1, timeLoad.length());
			cron = this.getCron(type, months, executeTime);
			timeLoad = timeLoad + "," + this.executeTime;
			entityobj.setTimes(timeLoad);
		}
		entityobj.setCron(cron);*/
	}

	@Override
	protected void onBeforeUpdate() {
		try {
			/*entityobj.setType(type);
			String timeLoad = "";
			String cron = "";
			if ("1".equals(type)) {
				entityobj.setTimes(seconds);
				cron = this.getCron(type, seconds, "");
			}
			if ("2".equals(type)) {
				entityobj.setTimes(branch);
				cron = this.getCron(type, branch, "");
			}
			if ("3".equals(type)) {
				entityobj.setTimes(time);
				cron = this.getCron(type, time, "");
			}
			if ("4".equals(type)) {
				entityobj.setTimes(day);
				cron = this.getCron(type, day, "");
			}
			if ("5".equals(type)) {
				for (String one : week) {
					if (one == null)
						continue;
					timeLoad = timeLoad + "," + one;
				}
				String weeks = timeLoad.substring(1, timeLoad.length());
				cron = this.getCron(type, weeks, executeTime);
				timeLoad = timeLoad + "," + this.executeTime;
				entityobj.setTimes(timeLoad);
			}
			if ("6".equals(type)) {
				for (String one : month) {
					if (one == null)
						continue;
					timeLoad = timeLoad + "," + one;
				}
				String months = timeLoad.substring(1, timeLoad.length());
				cron = this.getCron(type, months, executeTime);
				timeLoad = timeLoad + "," + this.executeTime;
				entityobj.setTimes(timeLoad);
			}
			entityobj.setCron(cron);*/
		} catch (Exception e) {
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
				CloudnodeListen cloudnodeListen = cloudnodeListenService
						.findById(ids[i]);
				if (cloudnodeListen.getStatus().equals("0")) {
					cloudnodeListenService.delete(cloudnodeListen);
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAction";
	}

	/**
	 * 获取表达式 6月、5周、4日、3时、 2分、1秒
	 * 
	 * @return
	 * @author hudaowan
	 * @date 2011-10-27 下午08:44:00
	 */
	public String getCron(String type, String datetime, String executeTime) {
		String cron = null;
		if ("1".equals(type)) {
			cron = ELTool.init().sec(datetime);
		}
		if ("2".equals(type)) {
			cron = ELTool.init().cent(datetime);
		}
		if ("3".equals(type)) {
			cron = ELTool.init().time(datetime);
		}
		if ("4".equals(type)) {
			cron = ELTool.init().today(datetime);
		}
		if ("5".equals(type)) {
			cron = ELTool.init().week(datetime, executeTime);
		}
		if ("6".equals(type)) {
			cron = ELTool.init().month(datetime, executeTime);
		}

		return cron;
	}
	
	public String getMongoDataSourceJsonStr() {
		List<CloudnodeListen> dataSourceList = cloudnodeListenService.findMongoDataSourcesByDept("1", deptId);
		if(dataSourceList==null||dataSourceList.size()<=0)return null;
		List<Node> nodes = new ArrayList<Node>();
		for(CloudnodeListen dataSource : dataSourceList){
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

	@Autowired
	public void setWorkFlowService(IWorkFlowService workFlowService) {
		this.workFlowService = workFlowService;
	}

	public String getSeconds() {
		return seconds;
	}

	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String[] getWeek() {
		return week;
	}

	public void setWeek(String[] week) {
		this.week = week;
	}

	public String[] getMonth() {
		return month;
	}

	public void setMonth(String[] month) {
		this.month = month;
	}

	public String getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ICloudnodeListenService getCloudnodeListenService() {
		return cloudnodeListenService;
	}

	@Autowired
	public void setCloudnodeListenService(
			ICloudnodeListenService cloudnodeListenService) {
		this.cloudnodeListenService = cloudnodeListenService;
	}

	@Override
	protected IBaseServices<CloudnodeListen> getEntityService() {
		return cloudnodeListenService;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
}
