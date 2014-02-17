package com.ligitalsoft.cloudnode.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.tools.ant.util.DateUtils;
import org.bson.types.ObjectId;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.dbtool.FileDBTool;
import com.common.framework.dao.Constants;
import com.common.framework.dao.QueryPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.help.SpringContextHolder;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.date.DateUtil;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudcenter.service.AppCloudNodeService;
import com.ligitalsoft.cloudcenter.service.CloudNodeInfoService;
import com.ligitalsoft.cloudcenter.service.ICouldNodeDeptService;
import com.ligitalsoft.cloudnode.service.IWorkFlowService;
import com.ligitalsoft.datasharexchange.service.IChangeItemService;
import com.ligitalsoft.datasharexchange.service.IExcelToDbinfoService;
import com.ligitalsoft.datasharexchange.service.IReceTaskService;
import com.ligitalsoft.datasharexchange.service.ISendDataFomatResultService;
import com.ligitalsoft.datasharexchange.service.ISendMessageLogService;
import com.ligitalsoft.datasharexchange.service.ISendResultService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.ReceiveResult;
import com.ligitalsoft.model.changemanage.SendDataFomatResult;
import com.ligitalsoft.model.changemanage.SendMessageLog;
import com.ligitalsoft.model.changemanage.SendResult;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.cloudcenter.CouldNodeDept;
import com.ligitalsoft.model.cloudnode.WorkFlow;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;

/**
 * 流程管理
 * 
 * @Company 北京光码软件有限公司
 * @author fangbin
 * @version iSwap V6.0 数据交换平台
 * @date 2011-06-14
 * @Team 研发中心
 */
@Namespace("/cloudnode/workflow")
@Action("workflow")
@Scope("prototype")
@Results({
		@Result(name = "listAction", location = "workflow!list.action", type = "redirectAction", params = {
				"page.index", "${page.index}", "workFlowName",
				"${workFlowName}", "appMsgId", "${appMsgId}", "deptId",
				"${deptId}" }),
		@Result(name = "cloneView", location = "clone.ftl", type = "freemarker"),
		@Result(name = "testView", location = "test.ftl", type = "freemarker"),
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker") })
public class WorkFlowAction extends FreemarkerBaseAction<WorkFlow> {
	private static final long serialVersionUID = -2512294829402484298L;
	@Autowired
	private IWorkFlowService workFlowService;
	@Autowired
	private ISysDeptService sysDeptService;
	@Autowired
	private ISysUserDeptService sysUserDeptService;
	@Autowired
	private AppMsgService appMsgService;
	@Autowired
	private CloudNodeInfoService cloudNodeInfoService;
	@Autowired
	private ICouldNodeDeptService couldNodeDeptService;
	@Autowired
	private AppCloudNodeService appCloudNodeService;
	@Autowired
	private IChangeItemService changeItemService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
	private IExcelToDbinfoService excelToDbinfoService;

	private List<SysDept> sysdeptList = new ArrayList<SysDept>();
	private List<AppMsg> appMsgList = new ArrayList<AppMsg>();
	private List<CloudNodeInfo> cloudNodeInfoList = new ArrayList<CloudNodeInfo>();
	private List<SysDept> depts = new ArrayList<SysDept>();
	private List<ChangeItem> changeItems = new ArrayList<ChangeItem>();
	private String status;

	private Long workFlowId;// 流程ID
	private String[] attributeKey;// Key
	private String[] attributeValue;// value
	private String attributeXML;// XML
	private String workFlowName;// 流程名称
	private Long appMsgId;// 应用ID
	private Long appItemId;//指标ID
	private Long changeItemId;//交换指标ID
	private Long deptId;// 部门ID
	private Long roleId = 0L;
	private CouldNodeDept couldNodeDept;
	private String appItemTreeStr;

	public String getAppItemTreeStr() {
		return appItemTreeStr;
	}

	public void setAppItemTreeStr(String appItemTreeStr) {
		this.appItemTreeStr = appItemTreeStr;
	}

	@Override
	protected IBaseServices<WorkFlow> getEntityService() {
		return workFlowService;
	}

	public String wfCustomize(){
		return "wfCustomize";
	}
	
	/**
	 * @author fangbin
	 */
	@Override
	public void onBeforeList() {
		if (deptId != null && deptId != 0) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("changeItem.sysDept.id");
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(deptId + "");
			queryPara.setOp(Constants.OP_EQ_VALUE);
			queryParas.add(queryPara);
		}
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
			// try {
			// System.out.println(new
			// String(workFlowName.getBytes("ISO-8859-1"),"GBK"));
			// } catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			// }
			QueryPara queryPara = new QueryPara();
			queryPara.setName("workFlowName");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(workFlowName);
			queryPara.setOp(Constants.OP_LIKE);
			queryParas.add(queryPara);
		}
		appMsgList = appMsgService.findAllByProperty();
	}

	/**
	 * @author fangbin
	 */
	@Override
	public void onBeforeAddView() {
		/*if (null != deptId) {
			List<CouldNodeDept> depts = couldNodeDeptService
					.findDeptByDeptId(deptId);
			if (null != depts && depts.size() > 0) {
				 couldNodeDept = depts.get(0);
				appMsgList = appCloudNodeService
						.findListByCloudId(couldNodeDept.getCouldNode().getId());
				 changeItems = changeItemService.findListByDeptId(deptId);
			}
		}*/
	}

	/**
	 * 执行数据源添加之前，创建时间加入对象
	 * 
	 * @author fangbin
	 */
	@Override
	protected void onBeforeAdd() {
		Date date = new Date();
		Date nowdate = DateUtil.strToDate(DateUtils.format(date, "yyyy-MM-dd"));
		entityobj.setCreateDate(nowdate);
		entityobj.setStatus("0");
		/*String wftype = entityobj.getWfType();
		if ("0".equals(wftype)) {
			entityobj.setItemId("");
		}*/
	}
	
	@Override
	protected void onAfterAdd() {
		entityobj.setWorkFlowCode(entityobj.getId()+"");
		this.update();
	}

	/**
	 * @author fangbin
	 */
	@Override
	public void onBeforeUpdateView() {
		if (null != deptId) {
			List<CouldNodeDept> depts = couldNodeDeptService
					.findDeptByDeptId(deptId);
			if (null != depts && depts.size() > 0) {
				 couldNodeDept = depts.get(0);
				 appMsgList = appCloudNodeService
						.findListByCloudId(couldNodeDept.getCouldNode().getId());
				 changeItems = changeItemService.findListByDeptId(deptId);
			}
		}

	}

	@Override
	public void onBeforeUpdate() {
		String wftype = entityobj.getWfType();
		if ("0".equals(wftype)) {
			entityobj.setItemId("");
		}
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
		String id = getHttpServletRequest().getParameter("id");
		String workFlowCode = getStringParameter("workFlowCode").trim();
		getHttpServletResponse().setCharacterEncoding("GBK");
		try {
			WorkFlow workFlow = workFlowService.findUniqueByProperty(
					"workFlowCode", workFlowCode);
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

	/**
	 * 删除
	 * 
	 * @author fangbin
	 */
	@SuppressWarnings("static-access")
	@Override
	public String delete() {
		try {
			this.onBeforeDelete();
			this.getEntityService().deleteAllByIds(ids);
			this.onAfterDelete();
			return "listAction";
		} catch (Exception e) {
			this.errorInfo = "删除数据失败，有关联数据正在使用!";
			log.error(errorInfo, e);
			return this.ERROR;
		}
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
				WorkFlow workFlow = workFlowService.findById(ids[i]);
				if (workFlow.getStatus().equals("0")) {
					workFlowService.delete(workFlow);
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
	 * @author fangbin
	 * @return
	 */
	public String updateStatus() {
		try {
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					WorkFlow message = workFlowService.findById(ids[i]);
					message.setStatus(status);
					workFlowService.updateStatus(message);
					workFlowService.update(message);

				}
			}
			// if (id != null) {
			// WorkFlow message = workFlowService.findById(id);
			// message.setStatus(status);
			// workFlowService.update(message);
			// }
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAction";
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
	 * 
	 * @author fangbin
	 */
	public String clone() {
		Date date = new Date();
		Date nowdate = DateUtil.strToDate(DateUtils.format(date, "yyyy-MM-dd"));
		try {
			WorkFlow workFlow = workFlowService.findById(workFlowId);
			WorkFlow wf = new WorkFlow();
			wf.setWorkFlowName(entityobj.getWorkFlowName());
			wf.setWorkFlowCode(entityobj.getWorkFlowCode());
			wf.setAppMsg(workFlow.getAppMsg());
			wf.setCloudNodeInfo(workFlow.getCloudNodeInfo());
			wf.setCreateDate(nowdate);
			wf.setDataType(workFlow.getDataType());
			wf.setNotes(workFlow.getNotes());
			wf.setChangeItem(workFlow.getChangeItem());
			if (null != workFlow.getShowXml()) {
				String showXml = showXmlLoad(new String(workFlow.getShowXml()),
						entityobj.getWorkFlowCode());
				wf.setShowXml(showXml.getBytes());
			}
			wf.setStatus("0");
			wf.setSysDept(workFlow.getSysDept());
			wf.setWfType(workFlow.getWfType());
			String workFlowXml = showXmlLoad(
					new String(workFlow.getWorkFlowXml()),
					entityobj.getWorkFlowCode());
			wf.setWorkFlowXml(workFlowXml);
			workFlowService.insert(wf);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return RELOAD;
	}

	/**
	 * 解析xml重置流程的英文名称
	 * 
	 * @return
	 * @author hudaowan
	 * @date 2011-10-27 下午07:42:41
	 */
	public String showXmlLoad(String xml, String wfcode) {
		String wf_xml = "";
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			Attribute attr_id = root.attribute("name");
			root.remove(attr_id);
			root.addAttribute("name", wfcode);
			wf_xml = document.asXML();
		} catch (Exception e) {
			log.error("解析xml失败", e);
		}
		return wf_xml;
	}

	/**
	 * 查询应用关联的节点
	 * 
	 * @return
	 */
	public String getCloude() {
		cloudNodeInfoList = appCloudNodeService.findNodeInfoListByApp(id);
		List<CloudNodeInfo> datas = new ArrayList<CloudNodeInfo>();
		for (CloudNodeInfo info : cloudNodeInfoList) {
			info.setAppCloudNode(null);
			info.setNodeDept(null);
			datas.add(info);
		}
		Struts2Utils.renderJson(datas, "encoding:GBK");
		return null;
	}

	/**
	 * 查询节点下部门集合
	 * 
	 * @return
	 */
	public String getDept() {
		List<SysDept> depts = cloudNodeInfoService.findListDeptByNodeId(id);
		List<SysDept> data = new ArrayList<SysDept>();
		for (SysDept sysDept : depts) {
			sysDept.setChildrenDept(null);
			sysDept.setSysDept(null);
			data.add(sysDept);
		}
		Struts2Utils.renderJson(data, "encoding:GBK");
		return null;
	}

	/**
	 * 得到部门下的指标
	 * 
	 * @return
	 */
	public String getItem() {
		List<ChangeItem> changeItems = changeItemService.findListByDeptId(id);
		List<ChangeItem> data = new ArrayList<ChangeItem>();
		for (ChangeItem changeItem : changeItems) {
			changeItem.setSysDept(null);
			changeItem.setChangeItemCycle(null);
			changeItem.setDatSource(null);
			changeItem.setDocument(null);
			changeItem.setItemApps(null);
			changeItem.setTableDescs(null);
			changeItem.setTemplate(null);
			data.add(changeItem);
		}
		Struts2Utils.renderJson(data, "encoding:GBK");
		return null;
	}

	/**
	 * 定义在clone()前执行二次绑定.
	 * 
	 * @author fangbin
	 */
	public void prepareClone() throws Exception {
		prepareModel();
	}

	/**
	 * 流程测试页面
	 * 
	 * @author fangbin
	 * @return
	 */
	public String testView() {
		return "testView";
	}

	/**
	 * 定义在testView()前执行二次绑定.
	 * 
	 * @author fangbin
	 */
	public void prepareTestView() throws Exception {
		prepareModel();
	}

	/**
	 * 工作流程测试
	 * 
	 * @author fangbin
	 * @return
	 */
	public String workFlowTest() {
		String xml = "";
		if (!StringUtils.isBlank(attributeXML)) {
			xml = attributeXML;
		} else {
			xml = this.createXMl(attributeKey, attributeValue);
		}
		workFlowService.runWorkFlow(workFlowId, xml);

		return RELOAD;
	}

	/**
	 * 生成xml
	 * 
	 * @param attributeKey
	 * @param attributeValue
	 * @return
	 */
	public String createXMl(String[] attributeKey, String[] attributeValue) {
		String argxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>";
		for (int i = 0; i < attributeKey.length; i++) {
			if (!StringUtils.isBlank(attributeKey[i])) {
				argxml += "<parameter key=\"" + attributeKey[i] + "\" >"
						+ attributeValue[i] + "</parameter>";
			}
		}
		argxml += "</root>";
		return argxml;
	}

	/**
	 * 查询部门下的所有流程生成ZTree
	 * 
	 * @author fangbin
	 */
	public void getWorkFlowByDeptId() {
		JSONArray workFlowTree = workFlowService.workFlowZtreeByDeptId(deptId,
				workFlowId);
		Struts2Utils.renderJson(workFlowTree, "encoding:GBK");
	}

	public void synData() {
		receiveDataLog();
		this.fliteDataLog();
		senDataLog();
		SendMessageLog();
	}

	public void receiveDataLog() {
		Map<String, Object> conditions = new HashMap<String, Object>();// 添加条件
		IReceTaskService receTaskService = SpringContextHolder
				.getBean("receTaskService");
		IChangeItemService changeItemService = SpringContextHolder
				.getBean("changeItemService");
		FileDBTool tool = FileDBTool.init();
		try {
			tool.getMongoConn();
			List<Map<String, Object>> entityList = tool
					.findToFiledb(FileDBConstant.fileDBName,
							FileDBConstant.receiveDataInfoDB);
			for (Map<String, Object> map : entityList) {
				Integer count_val = Integer.valueOf(map.get("dataCount")
						.toString());
				Integer dataTotal = Integer.valueOf(map.get("dataTotal")
						.toString());
				ObjectId _id = (ObjectId) map.get("_id");
				String key = (String) map.get("itemCode");
				String[] deptUidItemId = key.split("#");
				List<ChangeItem> changeItems = changeItemService
						.findByProperty("itemCode", deptUidItemId[1]);
				ChangeItem changeItem = null;
				if (changeItems != null && changeItems.size() > 0) {
					changeItem = changeItems.get(0);
				}
				Date createDate = new Date();
				if (map.get("createDate") != null) {
					createDate = (Date) map.get("createDate");
				}
				ReceiveResult receiveResult = new ReceiveResult();
				if (changeItem != null) {
					receiveResult.setReceNum(dataTotal);// 待接收量
					receiveResult.setDataNum(count_val);// 实际接受数量
					receiveResult.setItemCode(deptUidItemId[1]);
					receiveResult.setItemName(changeItem.getItemName());// 设置指标名称
					receiveResult.setReceiveDeptId(deptUidItemId[0]);// 部门UID
					receiveResult.setCreateDate(new Date());
					receiveResult.setExchangeDate(createDate);
					// receiveResult.setReceiveDeptName(changeItem.getSysDept()
					// .getDeptName());// 部门名称
					receTaskService.saveOrUpdate(receiveResult);// 持久化操作
					conditions.put("_id", _id);// 添加删除条件
					tool.deleteToFiledb(FileDBConstant.fileDBName,
							FileDBConstant.receiveDataInfoDB, conditions);
				}
				conditions.clear();
			}
		} catch (Exception e) {
			log.error("获取接受日志的信息失败！", e);
		} finally {
			tool.closeFileDB();
		}
	}

	public void fliteDataLog() {
		ISendDataFomatResultService service = SpringContextHolder
				.getBean("sendDataFomatResultService");
		Map<String, Object> conditions = new HashMap<String, Object>();
		Map<String, Object> serach = new HashMap<String, Object>();// 添加条件
		FileDBTool tool = FileDBTool.init();
		try {
			tool.getMongoConn();
			List<Map<String, Object>> entityList = tool.findToFiledb(
					FileDBConstant.fileDBName, FileDBConstant.FILTERDATALOG);

			for (Map<String, Object> map : entityList) {
				String filename = (String) map.get("filename");
				serach.put("filename", filename);
				List<Map<String, Object>> list = tool.findToFiledb(
						FileDBConstant.fileDBName,
						FileDBConstant.excelInsertDBLog, serach);
				int e_count = 0;
				int s_count = 0;
				Object id = null;
				if (list != null && list.size() > 0) {
					Map<String, Object> excelInfo = list.get(0);
					e_count = Integer.valueOf(excelInfo.get("errCount") + "");
					s_count = Integer.valueOf(excelInfo.get("succCount") + "");
					id = excelInfo.get("_id");
				}
				Integer errCount = Integer.valueOf(map.get("errCount") + "")
						+ e_count;
				Integer succCount = Integer.valueOf(map.get("succCount") + "");
				if (s_count > 0) {
					succCount = s_count;
				}
				Integer total = Integer.valueOf(map.get("total") + "");
				String errlog = (String) map.get("errlog");
				String itemCode = (String) map.get("itemCode");

				Date createDate = new Date();
				if (null != map.get("createDate")) {
					createDate = (Date) map.get("createDate");
				}
				SendDataFomatResult result = new SendDataFomatResult();
				result.setErrCount(errCount);
				result.setSuccCount(succCount);
				result.setTotal(total);
				result.setFileName(filename);
				result.setErrlog(errlog);
				result.setCreateDate(createDate);
				result.setItemCode(itemCode);
				service.saveOrUpdate(result);
				if (id != null) {
					serach.clear();
					serach.put("_id", id);
					tool.deleteToFiledb(FileDBConstant.fileDBName,
							FileDBConstant.excelInsertDBLog, serach);
					serach.clear();
				}
				ObjectId _id = (ObjectId) map.get("_id");
				conditions.put("_id", _id);// 添加删除条件
				tool.deleteToFiledb(FileDBConstant.fileDBName,
						FileDBConstant.FILTERDATALOG, conditions);
				conditions.clear();
			}
		} catch (Exception e) {
			log.error("获取发送日志的信息失败！", e);
		} finally {
			tool.closeFileDB();
		}
	}

	public void senDataLog() {
		ISendResultService sendResultService = SpringContextHolder
				.getBean("sendResultService");
		IChangeItemService changeItemService = SpringContextHolder
				.getBean("changeItemService");
		Map<String, Object> conditions = new HashMap<String, Object>();
		FileDBTool tool = FileDBTool.init();
		try {
			tool.getMongoConn();
			List<Map<String, Object>> entityList = tool.findToFiledb(
					FileDBConstant.fileDBName, FileDBConstant.sendDataInfoDB);
			for (Map<String, Object> map : entityList) {
				Integer count_val = Integer.valueOf(map.get("dataCount") + "");
				Integer dataTotal = Integer.valueOf(map.get("dataTotal") + "");
				String itemCode = (String) map.get("itemCode");
				ObjectId _id = (ObjectId) map.get("_id");
				Date createDate = new Date();
				if (map.get("createDate") != null) {
					createDate = (Date) map.get("createDate");
				}
				List<ChangeItem> changeItems = changeItemService
						.findByProperty("itemCode", itemCode);
				ChangeItem changeItem = null;
				if (changeItems != null && changeItems.size() > 0) {
					changeItem = changeItems.get(0);
				}
				SendResult sendResult = new SendResult();
				if (changeItem != null) {
					sendResult.setDataNum(count_val);// 发送数量
					// sendResult.setItemId(Integer.parseInt(changeItem
					// .getId().toString()));// 设置itemId
					sendResult.setItemCode(itemCode);
					sendResult.setCreatedate(new Date());
					sendResult.setExchangeDate(createDate);// 交换时间
					sendResult.setItemName(changeItem.getItemName());// 设置指标名称
					sendResult.setDeptId(changeItem.getSysDept().getDeptUid());// 部门UID
					sendResult.setPayNum(dataTotal);
					// sendResult.setDeptName(changeItem.getSysDept()
					// .getDeptName());// 部门名称
					sendResultService.saveOrUpdate(sendResult);// 持久化操作
					conditions.put("_id", _id);// 添加删除条件
					tool.deleteToFiledb(FileDBConstant.fileDBName,
							FileDBConstant.sendDataInfoDB, conditions);
				}
				conditions.clear();
			}
		} catch (Exception e) {
			log.error("获取发送日志的信息失败！", e);
		} finally {
			tool.closeFileDB();
		}
	}

	public void SendMessageLog() {
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
		ISendMessageLogService sendMessageLogService = SpringContextHolder
				.getBean("sendMessageLogService");
		List<Map<String, Object>> entityList = tool.findToFiledb(
				FileDBConstant.fileDBName, "sendMessageLog");
		Map<String, Object> conditions = new HashMap<String, Object>();
		for (Map<String, Object> map : entityList) {
			ObjectId _id = (ObjectId) map.get("_id");
			String phone = (String) map.get("phone");
			String message = (String) map.get("message");
			Date createDate = new Date();
			if (map.get("createDate") != null) {
				createDate = (Date) map.get("createDate");
			}
			SendMessageLog messageLog = new SendMessageLog();
			messageLog.setPhone(phone);
			messageLog.setMessage(message);
			messageLog.setSendDate(createDate);
			try {
				sendMessageLogService.saveOrUpdate(messageLog);
				conditions.put("_id", _id);// 添加删除条件
				tool.deleteToFiledb(FileDBConstant.fileDBName,
						"sendMessageLog", conditions);
				conditions.clear();
			} catch (ServiceException e) {
				log.error("同步短信日志异常:#" + phone + "#", e);
			} finally {
				tool.closeFileDB();
			}
		}
	}

	public List<SysDept> getSysdeptList() {
		return sysdeptList;
	}

	public void setSysdeptList(List<SysDept> sysdeptList) {
		this.sysdeptList = sysdeptList;
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

	public List<CloudNodeInfo> getCloudNodeInfoList() {
		return cloudNodeInfoList;
	}

	public void setCloudNodeInfoList(List<CloudNodeInfo> cloudNodeInfoList) {
		this.cloudNodeInfoList = cloudNodeInfoList;
	}

	public Long getWorkFlowId() {
		return workFlowId;
	}

	public void setWorkFlowId(Long workFlowId) {
		this.workFlowId = workFlowId;
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

	public List<SysDept> getDepts() {
		return depts;
	}

	public List<ChangeItem> getChangeItems() {
		return changeItems;
	}

	public String getWorkFlowName() {
		return workFlowName;
	}

	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}

	public Long getAppMsgId() {
		return appMsgId;
	}

	public void setAppMsgId(Long appMsgId) {
		this.appMsgId = appMsgId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public CouldNodeDept getCouldNodeDept() {
		return couldNodeDept;
	}

	public void setCouldNodeDept(CouldNodeDept couldNodeDept) {
		this.couldNodeDept = couldNodeDept;
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
