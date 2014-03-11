package com.ligitalsoft.esb.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.tools.ant.util.DateUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hsqldb.lib.StringUtil;
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
import com.ligitalsoft.esb.service.IEsbWorkFlowService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.esb.EsbWorkFlow;
import com.ligitalsoft.workflow.IWorkFlowManager;


/**
 * 流程管理
 *  
 */
@Namespace("/iswapesb/workflow")
@Results({ @Result(name = "listAction", location = "esbworkflowAction!list.action", type = "redirect", params = {
		"page.index", "${page.index}", "workFlowName",
		"${workFlowName}", "status", "${status}"}),
        @Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
        @Result(name = "cloneView", location = "clone.ftl", type = "freemarker"),
        @Result(name = "workFlowTest", location = "../workFlowTestResult.ftl", type = "freemarker")})
@Action("esbworkflowAction")
@Scope("prototype")
public class EsbWorkFlowAction  extends FreemarkerBaseAction<EsbWorkFlow> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IEsbWorkFlowService esbWorkFlowService;
	@Autowired
	private AppMsgService appMsgService;
	private AppMsg appMsg;
	private EsbWorkFlow esbWorkFlow;
	
	private String[] attributeKey;//Key
	private String[] attributeValue;//value
	private String attributeXML;//XML
	@Autowired
	private IWorkFlowManager workFlowManager;
	
	private String workFlowName;
	private String status;
	private String sign;
	private Long appMsgId;// 应用ID
	private Long appItemId;//指标ID
	private Long changeItemId;//交换指标ID
	
	public String wfCustomize(){
		return "wfCustomize";
	}
	
	  public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}


	public String getWorkFlowName() {
		return workFlowName;
	}


	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}
	@Override
	public void onBeforeList(){
		if (appMsgId != null && appMsgId != 0) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("changeItem.appItemExchangeConf.appMsg.id");
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(appMsgId + "");
			queryPara.setOp(Constants.OP_EQ_VALUE);
			queryParas.add(queryPara);
		}
		if (appItemId != null && appItemId != 0) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("changeItem.appItemExchangeConf.appItem.id");
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(appItemId + "");
			queryPara.setOp(Constants.OP_EQ_VALUE);
			queryParas.add(queryPara);
		}
		if (changeItemId != null && changeItemId != 0) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("changeItem.id");
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(changeItemId + "");
			queryPara.setOp(Constants.OP_EQ_VALUE);
			queryParas.add(queryPara);
		}
		if (!StringUtils.isBlank(workFlowName)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("workFlowName");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(workFlowName);
			queryPara.setOp(Constants.OP_LIKE);
			queryParas.add(queryPara);
		}
		if (!StringUtils.isBlank(status)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("status");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(status);
			queryPara.setOp(Constants.OP_EQ);
			queryParas.add(queryPara);
		}
	}
	
	/**
     * 流程测试页面
     * @author fangbin
     * @return
     */
	@SuppressWarnings("static-access")
    public String testView(){
		try {
			 EsbWorkFlow esbWorkFlowLoad  = this.esbWorkFlowService.findById(esbWorkFlow.getId());
			 this.esbWorkFlow = esbWorkFlowLoad;
		} catch (ServiceException e) {
			this.errorInfo="查询数据失败,请联系管理员!";
			log.error(this.errorInfo,e);
			return this.ERROR;
		}
    	return "testView";
    }
    
    
	/**
	 * 得到添加页面
	 * 获取应用
	 */
	protected void onBeforeAddView() { 
		super.onBeforeView();
		 //appMsgList = appMsgService.findAllByProperty();
	}
	
	
	/**
	 * 初始值
	 */
	public String add() {
		 try {
			this.entityobj.setCreateDate(DateUtil.strToDate(DateUtils.format(new Date(), "yyyy-MM-dd")));
			this.entityobj.setStatus("0");//禁用
			//entityobj.setWorkFlowCode(getStringParameter("workFlowCode"));
			//appMsg = this.appMsgService.findById(entityobj.getAppMsg().getId());
			//entityobj.setAppMsg(appMsg);
//			this.entityobj = esbWorkFlow;
			super.add();
			entityobj.setWorkFlowCode(entityobj.getId()+"");
			esbWorkFlowService.update(entityobj);;
		 } catch (Exception e) {
				e.printStackTrace();
		 }
	     return RELOAD;
	}
	
	 
	 /**
	  * 部署
	  * @return
	  */
	  public String deploy() {
		  try {
			  //批量部署
			  if("1".equals(this.sign)){
				  for(Long one:ids){
					  EsbWorkFlow esbWorkFlowLoad  = this.esbWorkFlowService.findById(one);
						if(!StringUtils.isBlank(esbWorkFlowLoad.getWorkFlowXml())&&!StringUtils.isBlank(esbWorkFlowLoad.getWorkFlowCode())){
					    	workFlowManager.deployWsXmlString(esbWorkFlowLoad.getWorkFlowXml(), esbWorkFlowLoad.getWorkFlowCode());
					    	 esbWorkFlowLoad.setStatus("1");
					    	 this.entityobj = esbWorkFlowLoad;
							    super.update();
					    }   
				  }
			  }else{
				  EsbWorkFlow esbWorkFlowLoad  = this.esbWorkFlowService.findById(esbWorkFlow.getId());
					if(!StringUtils.isBlank(esbWorkFlowLoad.getWorkFlowXml())&&!StringUtils.isBlank(esbWorkFlowLoad.getWorkFlowCode())){
				    	workFlowManager.deployWsXmlString(esbWorkFlowLoad.getWorkFlowXml(), esbWorkFlowLoad.getWorkFlowCode());
				    	 esbWorkFlowLoad.setStatus("1");
				    	 this.entityobj = esbWorkFlowLoad;
						    super.update();
				    }  
			  }
			} catch (Exception e) {
				log.error("流程部署失败！",e);
			}
	        return "listAction";
	    }
	  
	  /**
	   * 取消部署
	   * @return
	   */
	  public String unDeploy() {
			try {
				 if("0".equals(this.sign)){
					 for(long one:ids){
						 EsbWorkFlow esbWorkFlowLoad  = this.esbWorkFlowService.findById(one);
						    if(!StringUtil.isEmpty(esbWorkFlowLoad.getWorkFlowCode())){
						    	workFlowManager.deleteDeployment(esbWorkFlowLoad.getWorkFlowCode());
						    	esbWorkFlowLoad.setStatus("0");
								this.entityobj = esbWorkFlowLoad;
							    super.update();
						    }
					 }
				 }else{
					 EsbWorkFlow esbWorkFlowLoad  = this.esbWorkFlowService.findById(esbWorkFlow.getId());
					    if(!StringUtil.isEmpty(esbWorkFlowLoad.getWorkFlowCode())){
					    	workFlowManager.deleteDeployment(esbWorkFlowLoad.getWorkFlowCode());
					    	esbWorkFlowLoad.setStatus("0");
							this.entityobj = esbWorkFlowLoad;
						    super.update();
					    }
				 }
				 
			}catch (Exception e) {
				log.error("取消部署失败！",e);
			}
	        return "listAction";
	    }
	 
	 protected void onBeforeUpdateView() { 
		 try {
			esbWorkFlow = this.esbWorkFlowService.findById(esbWorkFlow.getId());
			appMsgList = appMsgService.findAllByProperty();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	 }
	 
	 public String update() {
		 try {
			 EsbWorkFlow esbWorkFlowLoad  = this.esbWorkFlowService.findById(esbWorkFlow.getId());
			 esbWorkFlow.setWorkFlowCode(getStringParameter("workFlowCode"));
			 appMsg = this.appMsgService.findById(esbWorkFlow.getAppMsg().getId());
			 esbWorkFlowLoad.setWorkFlowName(esbWorkFlow.getWorkFlowName());
			 esbWorkFlowLoad.setWorkFlowCode(esbWorkFlow.getWorkFlowCode());
			 if(!"".equals(esbWorkFlowLoad.getShowXml())){
				 esbWorkFlowLoad.setShowXml(showXmlLoad(new String(esbWorkFlowLoad.getShowXml()),esbWorkFlow.getWorkFlowCode()).getBytes());
				 esbWorkFlowLoad.setWorkFlowXml(showXmlLoad(esbWorkFlowLoad.getWorkFlowXml(),esbWorkFlow.getWorkFlowCode()));
			 }
			 esbWorkFlowLoad.setAppMsg(appMsg);
			 this.entityobj = esbWorkFlowLoad;
		        super.update();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
        return RELOAD;
	 }
	 
		/**
		 * 克隆页面
		 * 
		 * @author fangbin
		 */
		public String cloneView() {
			return "cloneView";
		}

		/**
		 * 克隆
		 */
		public String clone() {
			Date date = new Date();
			Date nowdate = DateUtil.strToDate(DateUtils.format(date, "yyyy-MM-dd"));
			try {
				EsbWorkFlow workFlow = esbWorkFlowService.findById(esbWorkFlow.getId());
				EsbWorkFlow workFlowLoad = new EsbWorkFlow();
				workFlowLoad.setAppMsg(workFlow.getAppMsg());
				workFlowLoad.setSysUser(workFlow.getSysUser());
				workFlowLoad.setWorkFlowName(esbWorkFlow.getWorkFlowName());
				workFlowLoad.setWorkFlowCode(esbWorkFlow.getWorkFlowCode());
				workFlowLoad.setAppMsg(workFlow.getAppMsg());
				String showXml = showXmlLoad(new String(workFlow.getShowXml()),esbWorkFlow.getWorkFlowCode());
				workFlowLoad.setShowXml(showXml.getBytes());
				String workFlowXml = showXmlLoad(new String(workFlow.getWorkFlowXml()),esbWorkFlow.getWorkFlowCode());
				workFlowLoad.setWorkFlowXml(workFlowXml);
				workFlowLoad.setStatus("0");
				workFlowLoad.setCreateDate(nowdate);
				esbWorkFlowService.insert(workFlowLoad);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			return RELOAD;
		}
		
		/**
		 * 解析xml重置流程的英文名称
		 * @return 
		 * @author  hudaowan
		 * @date 2011-10-27 下午07:42:41
		 */
		public String showXmlLoad(String xml,String wfcode) {
			String wf_xml = "";
			try {
				Document document = DocumentHelper.parseText(xml);
				Element root = document.getRootElement(); 
				Attribute attr_id = root.attribute("name");
				root.remove(attr_id);
				root.addAttribute("name",wfcode);
				wf_xml = document.asXML();
			} catch (Exception e) {
				log.error("解析xml失败", e);
			}
			return wf_xml;
		}
	 
    /**
     * 工作流程测试
     * @author fangbin
     * @return
     */
    public String workFlowTest(){
    	try {
			EsbWorkFlow esbWorkFlowLoad  = this.esbWorkFlowService.findById(id);
			String xml="";
	    	if(!StringUtils.isBlank(attributeKey[0])){
	    		xml=this.createXMl(attributeKey, attributeValue);
	    	}else{
	    		xml=attributeXML;
	    	}
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	Map<String,Object> mapResult=new HashMap<String,Object>();
//	    	map = esbWorkFlowService.readWorkflowXml(xml);
	    	map.put("inputMessage", xml);
	    	mapResult = workFlowManager.runWorkFlow(esbWorkFlowLoad.getWorkFlowCode(), map);
			this.errorInfo =  (String)mapResult.get("msg");
		} catch (Exception e) {
			this.errorInfo = "流程测试失败！";
			log.error("测试失败！",e);
		}
    	return "workFlowTest";
    }
	    
	    /**
	     * 生成xml
	     * @param attributeKey
	     * @param attributeValue
	     * @return
	     */
	    public String createXMl(String[] attributeKey,String[] attributeValue){
	    	String argxml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>";
	    	for(int i =0;i<attributeKey.length;i++){
	    		if(!StringUtils.isBlank(attributeKey[i])){
	    			argxml+="<parameter key=\""+attributeKey[i]+"\" >"+attributeValue[i]+"</parameter>";
	    		}
	    	}
	    	argxml+="</root>";
	    	return argxml;
	    }
	    /**
		 * 检查code 是否唯一　
		 * 
		 * @return
		 * @author zhangx
		 * @2010-12-30 下午08:10:24
		 */
		public String checkCode() {
			String result = "";
			String id = getStringParameter("id");
			String workFlowCode = getStringParameter("workFlowCode");
			getHttpServletResponse().setCharacterEncoding("GBK");
			try {
				EsbWorkFlow workFlow = esbWorkFlowService.findUniqueByProperty("workFlowCode", workFlowCode);
				if (workFlow == null) {
					result = "succ";
				} else {
					if (!StringUtils.isBlank(id)) {
						if (workFlow.getId().toString().equals(id)) {
							result = "succ";
						}
					}
				}
				Struts2Utils.renderText(result, "encoding:GBK");
			} catch (ServiceException e) {
				log.error("workFlowAction exception", e);
			}
			return null;
		}
	    
	    public String[] getAttributeKey() {
			return attributeKey;
		}

		public void setAttributeKey(String[] attributeKey) {
			this.attributeKey = attributeKey;
		}

		public String[] getAttributeValue() {
			return attributeValue;
		}

		public void setAttributeValue(String[] attributeValue) {
			this.attributeValue = attributeValue;
		}

		public String getAttributeXML() {
			return attributeXML;
		}

		public void setAttributeXML(String attributeXML) {
			this.attributeXML = attributeXML;
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
		protected IBaseServices<EsbWorkFlow> getEntityService() {
			return esbWorkFlowService;
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
		
}
	

