package com.ligitalsoft.esb.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.cachetool.CacheTool;
import com.common.framework.dao.Constants;
import com.common.framework.dao.QueryPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.jobtool.ELTool;
import com.common.jobtool.JobTool;
import com.common.utils.common.StringUtils;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.esb.service.IEsbTaskMsgService;
import com.ligitalsoft.esb.service.IEsbWorkFlowService;
import com.ligitalsoft.help.task.ISwapESBWorkFlowJob;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.esb.EsbTaskMsg;
import com.ligitalsoft.model.esb.EsbWorkFlow;


/**
 * 流程管理
 *  
 */
@Namespace("/iswapesb/esbtask")
@Results({ @Result(name = "listAction", location = "esbTaskAction!list.action", type = "redirect",params = {
		"page.index", "${page.index}", "taskName","${taskName}",  "type","${type}",  "status","${status}"  }),
        @Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker") })
@Action("esbTaskAction")
@Scope("prototype")
public class EsbTaskMsgAction  extends FreemarkerBaseAction<EsbTaskMsg> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IEsbTaskMsgService esbTaskMsgService;
	@Autowired
	private IEsbWorkFlowService esbWorkFlowService;
	@Autowired
	private AppMsgService appMsgService;
	private AppMsg appMsg;
	private EsbWorkFlow esbWorkFlow;
	private EsbTaskMsg esbTaskMsg;
	private List<EsbWorkFlow> esbWorkFlowList;
	private String seconds;   //秒
	private String branch;    //分
	private String time;      //时
	private String day;       //天
	private String[] week;      //周
	private String[] month;     //月
	private String executeTime;  //执行时间
	private String type;
	private String sign;
	private String tree;
	private String taskName;
	private String status;
	private Long appMsgId;// 应用ID
	private Long appItemId;//指标ID
	private Long changeItemId;//交换指标ID
	private String workFlowName;
	private Long workFlowId;// 流程ID
	
	  public String getTree() {
		return tree;
	}


	public void setTree(String tree) {
		this.tree = tree;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}

	
 	
	public String getType() {
		return type; 
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
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

	public EsbTaskMsg getEsbTaskMsg() {
		return esbTaskMsg;
	}

	public void setEsbTaskMsg(EsbTaskMsg esbTaskMsg) {
		this.esbTaskMsg = esbTaskMsg;
	}

	public List<EsbWorkFlow> getEsbWorkFlowList() {
		return esbWorkFlowList;
	}

	public void setEsbWorkFlowList(List<EsbWorkFlow> esbWorkFlowList) {
		this.esbWorkFlowList = esbWorkFlowList;
	}

	public AppMsg getAppMsg() {
		return appMsg;
	}

	public void setAppMsg(AppMsg appMsg) {
		this.appMsg = appMsg;
	}

	public EsbWorkFlow getEsbWorkFlow() {
		return esbWorkFlow;
	}

	public void setEsbWorkFlow(EsbWorkFlow esbWorkFlow) {
		this.esbWorkFlow = esbWorkFlow;
	}

	private List<AppMsg> appMsgList= new ArrayList<AppMsg>();

	public List<AppMsg> getAppMsgList() {
		return appMsgList;
	}

	public void setAppMsgList(List<AppMsg> appMsgList) {
		this.appMsgList = appMsgList;
	}

	@Override
	protected IBaseServices<EsbTaskMsg> getEntityService() {
		return esbTaskMsgService;
	}
	
	public String taskCustomize(){
		return "taskCustomize";
	}
	
	/**
	 * 
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
		if (workFlowName != null && !StringUtils.isBlank(workFlowName)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("c.workFlowName");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(workFlowName);
			queryPara.setOp(Constants.OP_LIKE);
			queryParas.add(queryPara);
		}
		if (!StringUtils.isBlank(taskName)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("e.taskName");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(taskName);
			queryPara.setOp(Constants.OP_LIKE);
			queryParas.add(queryPara);
		}
		if (!StringUtils.isBlank(status)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("e.status");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(status);
			queryPara.setOp(Constants.OP_EQ);
			queryParas.add(queryPara);
		}
		if (!StringUtils.isBlank(type)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("e.type");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(type);
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
            this.listDatas = esbTaskMsgService.findEsbTaskMsgRightJoinWorkFlowList(queryParas, sortParas, page);
            this.onAfterList();
            return this.LIST;
        } catch (Exception e) {
            this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
	
	/**
	 * 得到添加页面
	 * 获取应用
	 */
	protected void onBeforeAddView() { 
		super.onBeforeAddView();
		tree = this.esbTaskMsgService.workFlowXTree(null);
		esbWorkFlowList = esbWorkFlowService.findAll();	
		
	}
	
	/**
	 * 初始值
	 */
	public String add() {
	     try {
				esbTaskMsg.setStatus("0");    //未启用
				esbTaskMsg.setCreateDate(DateUtil.getDateTime());
				esbTaskMsg.setHisDate(DateUtil.getDateTime());
				//esbWorkFlow = this.esbWorkFlowService.findById(esbTaskMsg.getWorkFlow().getId());
				//esbTaskMsg.setWorkFlow(esbWorkFlow);
				String cron = null;
				String timeLoad = "";
				if("1".equals(esbTaskMsg.getType())){
					esbTaskMsg.setTimes(seconds);
					cron = this.getCron(esbTaskMsg.getType(), seconds, "");
				}
				if("2".equals(esbTaskMsg.getType())){
					esbTaskMsg.setTimes(branch);
					cron = this.getCron(esbTaskMsg.getType(), branch, "");
				}
				if("3".equals(esbTaskMsg.getType())){
					esbTaskMsg.setTimes(time);
					cron = this.getCron(esbTaskMsg.getType(), time, "");
				}
				if("4".equals(esbTaskMsg.getType())){
					esbTaskMsg.setTimes(day);
					cron = this.getCron(esbTaskMsg.getType(), day, "");
				}
				if("5".equals(esbTaskMsg.getType())){
					for(String one:week){
						 if (one == null)
			                  continue;
						 timeLoad = timeLoad+","+one;
					}
					String weeks = timeLoad.substring(1, timeLoad.length());
					cron = this.getCron(esbTaskMsg.getType(), weeks, executeTime);
					timeLoad = timeLoad+","+this.executeTime;
					esbTaskMsg.setTimes(timeLoad);
				}
				if("6".equals(esbTaskMsg.getType())){
					for(String one:month){
						 if (one == null)
			                  continue;
						 timeLoad = timeLoad+","+one;
					}
					String months = timeLoad.substring(1, timeLoad.length());
					cron = this.getCron(esbTaskMsg.getType(), months, executeTime);
					timeLoad = timeLoad+","+this.executeTime;
					esbTaskMsg.setTimes(timeLoad);
				}
				esbTaskMsg.setCron(cron);
				this.entityobj = esbTaskMsg;
				 super.add();
			} catch (Exception e) {
				e.printStackTrace();
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
	
	
	 public String delete() {
        super.delete();
        return "listAction";
    }
	 
	 /**
	  * 启用
	  * @return
	  */
	  public String deploy() {
		  JobTool jobTool = JobTool.init();
		  CacheTool tool = CacheTool.init();
		  Cache cache = tool.findCache("esbWorkfolwCache");//CacheConstant.esbWorkfolwMsgCache
		  try {
			  //批量启用
			  if("1".equals(this.sign)){
				  for(Long one:ids){
					    EsbTaskMsg esbTaskMsgLoad = new EsbTaskMsg();
						esbTaskMsgLoad = this.esbTaskMsgService.findById(one);
						esbTaskMsgLoad.setStatus("1");
						String jobName = esbTaskMsgLoad.getTaskName()+"#"+esbTaskMsgLoad.getWorkFlow().getWorkFlowName()+"#"+esbTaskMsgLoad.getWorkFlow().getWorkFlowCode();
						this.entityobj = esbTaskMsgLoad;
					    super.update();
						tool.putCacheInfo(cache, jobName, esbTaskMsgLoad.getMessage());
						jobTool.addJob(jobName, esbTaskMsgLoad.getCron(), ISwapESBWorkFlowJob.class);
				  }
			  }else{
				    EsbTaskMsg esbTaskMsgLoad = new EsbTaskMsg();
					esbTaskMsgLoad = this.esbTaskMsgService.findById(esbTaskMsg.getId());
					esbTaskMsgLoad.setStatus("1");
					String jobName = esbTaskMsgLoad.getTaskName()+"#"+esbTaskMsgLoad.getWorkFlow().getWorkFlowName()+"#"+esbTaskMsgLoad.getWorkFlow().getWorkFlowCode();
					this.entityobj = esbTaskMsgLoad;
				    super.update();
					tool.putCacheInfo(cache, jobName, esbTaskMsgLoad.getMessage());
					jobTool.addJob(jobName, esbTaskMsgLoad.getCron(), ISwapESBWorkFlowJob.class);
			  }
			} catch (Exception e) {
				log.error("启用失败",e);
			}
	        return "listAction";
	    }
	  
	  /**
	   * 禁用
	   * @return
	   */
	  public String unDeploy() {
		  JobTool jobTool = JobTool.init();
		  CacheTool tool = CacheTool.init();
		  Cache cache = tool.findCache("esbWorkfolwCache");
			try {
				//批量禁用
				  if("0".equals(this.sign)){
					  for(Long one:ids){
						    EsbTaskMsg esbTaskMsgLoad = new EsbTaskMsg();
							esbTaskMsgLoad = this.esbTaskMsgService.findById(one);
							esbTaskMsgLoad.setStatus("0");
							String jobName = esbTaskMsgLoad.getTaskName()+"#"+esbTaskMsgLoad.getWorkFlow().getWorkFlowName()+"#"+esbTaskMsgLoad.getWorkFlow().getWorkFlowCode();
							this.entityobj = esbTaskMsgLoad;
						    super.update();
							tool.deleteCacheInfo(cache, jobName);
							jobTool.deleteJob(jobName);
					  }
				  }else{
					    EsbTaskMsg esbTaskMsgLoad = new EsbTaskMsg();
						esbTaskMsgLoad = this.esbTaskMsgService.findById(esbTaskMsg.getId());
						esbTaskMsgLoad.setStatus("0");
						String jobName = esbTaskMsgLoad.getTaskName()+"#"+esbTaskMsgLoad.getWorkFlow().getWorkFlowName()+"#"+esbTaskMsgLoad.getWorkFlow().getWorkFlowCode();
						this.entityobj = esbTaskMsgLoad;
					    super.update();
						tool.deleteCacheInfo(cache, jobName);
						jobTool.deleteJob(jobName);
				  }
			} catch (ServiceException e) {
				e.printStackTrace();
			}
	        return "listAction";
	    }
	 
	 
	 
	 protected void onBeforeUpdateView() { 
		 try {
			 //esbWorkFlowList = esbWorkFlowService.findAll();	
			 //esbTaskMsg = this.esbTaskMsgService.findById(esbTaskMsg.getId());
			 //tree = this.esbTaskMsgService.workFlowXTree(esbTaskMsg.getWorkFlow().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 public String update() {
		 try {
			EsbTaskMsg esbTaskMsgLoad = new EsbTaskMsg();;
			esbTaskMsgLoad = this.esbTaskMsgService.findById(esbTaskMsg.getId());
			esbTaskMsgLoad.setCreateDate(DateUtil.getDateTime());
			esbTaskMsgLoad.setHisDate(DateUtil.getDateTime());
			esbWorkFlow = this.esbWorkFlowService.findById(esbTaskMsg.getWorkFlow().getId());
			esbTaskMsgLoad.setWorkFlow(esbWorkFlow);
			esbTaskMsgLoad.setType(type);
			esbTaskMsgLoad.setMessage(esbTaskMsg.getMessage());
			esbTaskMsgLoad.setTaskName(esbTaskMsg.getTaskName());
			String timeLoad = "";
			String cron = "";
			if("1".equals(type)){
				esbTaskMsgLoad.setTimes(seconds);
				cron = this.getCron(type, seconds, "");
			}
			if("2".equals(type)){
				esbTaskMsgLoad.setTimes(branch);
				cron = this.getCron(type, branch, "");
			}
			if("3".equals(type)){
				esbTaskMsgLoad.setTimes(time);
				cron = this.getCron(type, time, "");
			}
			if("4".equals(type)){
				esbTaskMsgLoad.setTimes(day);
				cron = this.getCron(type, day, "");
			}
			if("5".equals(type)){
				for(String one:week){
					 if (one == null)
		                  continue;
					 timeLoad = timeLoad+","+one;
				}
				String weeks = timeLoad.substring(1, timeLoad.length());
				cron = this.getCron(type, weeks, executeTime);
				timeLoad = timeLoad+","+this.executeTime;
				esbTaskMsgLoad.setTimes(timeLoad);
			}
			if("6".equals(type)){
				for(String one:month){
					 if (one == null)
		                  continue;
					 timeLoad = timeLoad+","+one;
				}
				String months = timeLoad.substring(1, timeLoad.length());
				cron = this.getCron(type, months, executeTime);
				timeLoad = timeLoad+","+this.executeTime;
				esbTaskMsgLoad.setTimes(timeLoad);
			}
			 esbTaskMsgLoad.setCron(cron);
		     this.entityobj = esbTaskMsgLoad;
		     super.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return RELOAD;
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

	public String getWorkFlowName() {
		return workFlowName;
	}

	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}

	public Long getWorkFlowId() {
		return workFlowId;
	}


	public void setWorkFlowId(Long workFlowId) {
		this.workFlowId = workFlowId;
	}
	 
}
	

