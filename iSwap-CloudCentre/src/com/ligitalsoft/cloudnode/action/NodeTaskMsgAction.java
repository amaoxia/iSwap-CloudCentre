package com.ligitalsoft.cloudnode.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

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
import com.common.utils.common.StringUtils;
import com.common.utils.date.DateUtil;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.cloudnode.service.INodeTaskMsgService;
import com.ligitalsoft.cloudnode.service.IWorkFlowService;
import com.ligitalsoft.datasharexchange.service.IChangeItemService;
import com.ligitalsoft.model.cloudnode.NodeTaskMsg;
import com.ligitalsoft.model.cloudnode.WorkFlow;

/**
 * 任务调度管理
 * 
 * @Company 北京光码软件有限公司
 * @author fangbin
 * @version iSwap V6.0 数据交换平台
 * @date 2011-08-14
 * @Team 研发中心
 */
@Namespace("/cloudnode/nodetask")
@Action("task")
@Scope("prototype")
@Results({
		@Result(name = "listAction", location = "task!list.action", type = "redirectAction", params = {
				"page.index", "${page.index}", "taskName",
				"${taskName}", "workFlowName", "${workFlowName}", "type",
				"${type}" ,"status","${status}","statu","${statu}","deptId","${deptId}"}),
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
		@Result(name = "nextView", location = "parameter.ftl", type = "freemarker"),
		@Result(name = "parameter", location = "parameter.ftl", type = "freemarker") })
public class NodeTaskMsgAction extends FreemarkerBaseAction<NodeTaskMsg> {
	@Autowired
	private INodeTaskMsgService nodeTaskMsgService;
	@Autowired
	private IWorkFlowService workFlowService;
	@Autowired
	private IChangeItemService changeItemService;

	private WorkFlow workList;
	private String xtree;

	private String seconds; // 秒
	private String branch; // 分
	private String time; // 时
	private String day; // 天
	private String[] week; // 周
	private String[] month; // 月
	private String executeTime; // 执行时间
	private String type;

	private Long workFlowId;// 流程ID
	private String[] attributeKey;// Key
	private String[] attributeValue;// value
	private String attributeXML;// XML

	private Map<String, Object> map = new HashMap<String, Object>();
	private String argXMl;
	
	private String taskName;
	private Long deptId;
	private String workFlowName;
	private String status;
	private String statu;
	private Long appMsgId;// 应用ID
	private Long appItemId;//指标ID
	private Long changeItemId;//交换指标ID
	
	public String taskCustomize(){
		return "taskCustomize";
	}
	
	@Override
	protected IBaseServices<NodeTaskMsg> getEntityService() {
		return nodeTaskMsgService;
	}

	public WorkFlow getWorkList() {
		return workList;
	}

	public void setWorkList(WorkFlow workList) {
		this.workList = workList;
	}
	/**
	 * @author fangbin
	 */
	@Override
	public void onBeforeList() {
		if (changeItemId != null && changeItemId != 0) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("c.changeItem.id");
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(changeItemId + "");
			queryPara.setOp(Constants.OP_EQ_VALUE);
			queryParas.add(queryPara);
		}
		if (appMsgId != null && appMsgId != 0) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("c.changeItem.appItemExchangeConf.appMsg.id");
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(appMsgId + "");
			queryPara.setOp(Constants.OP_EQ_VALUE);
			queryParas.add(queryPara);
		}
		if (appItemId != null && appItemId != 0) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("c.changeItem.appItemExchangeConf.appItem.id");
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(appItemId + "");
			queryPara.setOp(Constants.OP_EQ_VALUE);
			queryParas.add(queryPara);
		}
		if(null!=deptId){
			QueryPara queryPara = new QueryPara();
			queryPara.setName("c.changeItem.sysDept.id");
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(deptId+"");
			queryPara.setOp(Constants.OP_EQ);
			queryParas.add(queryPara);
		}
		if (workFlowName != null && !StringUtils.isBlank(workFlowName)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("c.workFlowName");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(workFlowName);
			queryPara.setOp(Constants.OP_LIKE);
			queryParas.add(queryPara);
		}
		if (taskName != null && !StringUtils.isBlank(taskName)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("e.taskName");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(taskName);
			queryPara.setOp(Constants.OP_LIKE);
			queryParas.add(queryPara);
		}
		if (type != null && !StringUtils.isBlank(type)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("e.type");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(type);
			queryPara.setOp(Constants.OP_EQ);
			queryParas.add(queryPara);
		}
		if (statu != null && !StringUtils.isBlank(statu)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("e.status");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(statu);
			queryPara.setOp(Constants.OP_EQ);
			queryParas.add(queryPara);
		}
	}
	
	@Override
	public String list() {
        try {
            this.onBeforeList();
            this.setQueryAndsort();// 设置查询条件
            // 分页查询
            this.listDatas = nodeTaskMsgService.findNodeTaskMsgRightJoinWorkFlowList(queryParas, sortParas, page);
            this.onAfterList();
            return this.LIST;
        } catch (Exception e) {
            this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
            log.error(errorInfo, e);
            return this.ERROR;
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
	 * 初始值
	 */
	@Override
	public String add() {
		try{
			entityobj.setStatus("0");// 未启用
			entityobj.setCreateDate(DateUtil.getDateTime());
			entityobj.setHisDate(DateUtil.getDateTime());
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
			
			entityobj.setCron(cron);
			nodeTaskMsgService.saveOrUpdate(entityobj);
		} catch (Exception e) {
			return this.ERROR;
		}
		return RELOAD;

	}
	

	@Override
	public void onBeforeUpdateView() {
		NodeTaskMsg nodeTaskMsg;
		try {
			nodeTaskMsg = nodeTaskMsgService.findById(entityobj.getId());
			if (nodeTaskMsg.getWorkFlow() != null) {
				workFlowId=nodeTaskMsg.getWorkFlow().getId();
			}else{
				workFlowId=null;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 更新
	 * 
	 * @author fangbin
	 */
	@Override
	public String update() {
		try {
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
			entityobj.setCron(cron);
//			WorkFlow wf=new WorkFlow();
//			wf.setId(entityobj.getWorkFlow().getId());
//			entityobj.setWorkFlow(wf);
			nodeTaskMsgService.update(entityobj);
		} catch (Exception e) {
			this.errorInfo = "添加调度任务失败,请联系管理员!";
			log.error(this.errorInfo, e);
			return this.ERROR;
		}
		return RELOAD;

	}
	
	/**
	 * 获取表达式
	 * 6月、5周、4日、3时、 2分、1秒
	 * @return 
	 * @author  hudaowan
	 * @date 2011-10-27 下午08:44:00
	 */
	 public String getCron(String type,String datetime,String executeTime) {
		 String cron = null;
		 if("1".equals(type)){
			 cron = ELTool.init().sec(datetime);
		 }
		if("2".equals(type)){
			 cron = ELTool.init().cent(datetime);
	     }
		if("3".equals(type)){
			cron = ELTool.init().time(datetime);
		}
		if("4".equals(type)){
			cron = ELTool.init().today(datetime); 
		}
		if("5".equals(type)){
			cron = ELTool.init().week(datetime, executeTime); 
		}
		if("6".equals(type)){
			cron = ELTool.init().month(datetime, executeTime); 
		}	 
		 
		return cron;
	 }

	/**
	 * 批量删除
	 * 
	 * @author fangbin
	 * @return
	 */

	public String delMany() {
		try {
			for (int i = 0; i < ids.length; i++) {
				NodeTaskMsg nodeTaskMsg = nodeTaskMsgService.findById(ids[i]);
				if (nodeTaskMsg.getStatus().equals("0")) {
					nodeTaskMsgService.delete(nodeTaskMsg);
				}
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAction";
	}

	/**
	 * 启用
	 * 
	 * @author fangbin
	 * @return
	 */
	public String deploy() {
		NodeTaskMsg nodeTaskMsg;
		try {
			for(int i=0;i<ids.length;i++){
				nodeTaskMsg = nodeTaskMsgService.findById(ids[i]);
				nodeTaskMsg.setStatus(entityobj.getStatus());
				nodeTaskMsgService.update(nodeTaskMsg);
				nodeTaskMsgService.updateStatus(nodeTaskMsg);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAction";

	}

	/**
	 * 定义在deploy()前执行二次绑定
	 * 
	 * @throws Exception
	 */
	public void prepareDeploy() throws Exception {
		prepareModel();
	}

	/**
	 * 设置启动参数
	 * 
	 * @author fangbin
	 * @return
	 */

	public String setParameter() {
		return "parameter";
	}

	/**
	 * 定义在设置启动参数前执行二次绑定
	 * 
	 * @author fangbin
	 * @throws Exception
	 */
	public void prepareSetParameter() throws Exception {
		prepareModel();
	}

	/**
	 * 定义在UnDeploy()前执行二次绑定
	 * 
	 * @author fangbin
	 * @throws Exception
	 */
	public void prepareUnDeploy() throws Exception {
		prepareModel();
	}
	

	/**
	 * 生成xml
	 * 
	 * @author fangbin
	 * @param attributeKey
	 * @param attributeValue
	 * @return
	 */
	public String createXMl(String[] attributeKey, String[] attributeValue) {
		String argxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>";
		if (attributeKey != null && attributeKey.length > 0) {
			for (int i = 0; i < attributeKey.length; i++) {
				if (!StringUtils.isBlank(attributeKey[i])) {
					argxml += "<parameter key=\"" + attributeKey[i] + "\" >"
							+ attributeValue[i] + "</parameter>";
				}
			}
		}
		argxml += "</root>";
		return argxml;
	}

	public String getXtree() {
		return xtree;
	}

	public void setXtree(String xtree) {
		this.xtree = xtree;
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

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWorkFlowName() {
		return workFlowName;
	}

	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getAppMsgId() {
		return appMsgId;
	}

	public void setAppMsgId(Long appMsgId) {
		this.appMsgId = appMsgId;
	}

	public Long getAppItemId() {
		return appItemId;
	}

	public void setAppItemId(Long appItemId) {
		this.appItemId = appItemId;
	}

	public Long getChangeItemId() {
		return changeItemId;
	}

	public void setChangeItemId(Long changeItemId) {
		this.changeItemId = changeItemId;
	}

	public Long getWorkFlowId() {
		return workFlowId;
	}

	public void setWorkFlowId(Long workFlowId) {
		this.workFlowId = workFlowId;
	}

}
