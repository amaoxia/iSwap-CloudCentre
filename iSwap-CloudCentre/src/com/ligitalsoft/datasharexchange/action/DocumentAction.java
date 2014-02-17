/*
 * @(#)DocumentAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.action;

import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.config.ConfigAccess;
import com.common.framework.dao.Constants;
import com.common.framework.dao.QueryPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.date.DateUtil;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.cloudnode.service.IWorkFlowService;
import com.ligitalsoft.datasharexchange.service.IChangeItemDocumentService;
import com.ligitalsoft.datasharexchange.service.IChangeItemService;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.ChangeItemDocument;
import com.ligitalsoft.model.cloudnode.WorkFlow;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserDept;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.util.Costant;
import com.ligitalsoft.sysmanager.util.FileUtil;

/**
 * 交换指标文档ACTION
 * 
 * @author zhangx
 * @since Jun 28, 2011 9:23:00 AM
 * @name com.ligitalsoft.datasharexchange.action.DocumentAction.java
 * @version 1.0
 */
@Scope("prototype")
@Namespace("/exchange/document")
@Results({
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
		@Result(name = "listAction", location = "document!list.action", type = "redirectAction"),
		@Result(name = "shareListAction", location = "document!shareList.action", type = "redirectAction") })
public class DocumentAction extends FreemarkerBaseAction<ChangeItemDocument> {

	/**
     * 
     */
	private static final long serialVersionUID = 2486684821481963488L;
	private IChangeItemDocumentService changeItemDocumentService;
	private IChangeItemService changeItemService;
	private ISysUserDeptService sysUserDeptService;
	private ISysDeptService sysDeptService;
	private IWorkFlowService workFlowService;
	private Long itemId;
	private File upload;// 上传文件对象
	private String uploadFileName;// 文件名称
	private List<ChangeItem> items = new ArrayList<ChangeItem>();
	private String status;// 指标发布状态
	private String deptName;

	/**
	 * 13261581489 list列表之前根据当前用户部门查询
	 */
	@Override
	protected void onBeforeList() {
		SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
		if (user == null) {
			log.error("会话超时,请重新登录!");
			return;
		}
		try {
			SysUserDept userDept = sysUserDeptService
					.findByUserId(user.getId());// 用户机构对象
			SysDept sysDept = sysDeptService.findById(userDept.getDeptId());
			// 是否机构中心
			System.out.println(sysDept.getDeptName());
			if (sysDept != null && !sysDept.getDeptName().equals("信息管理中心")) {
				QueryPara queryPara = new QueryPara();
				queryPara.setName("e.changeItem.sysDept.id");
				queryPara.setOp(Constants.OP_EQ_VALUE);
				queryPara.setType(Constants.TYPE_LONG);
				queryPara.setValue(userDept.getDeptId().toString());
			} else {
				this.getHttpServletRequest().setAttribute("center", "1");// 1
																			// 部门中心
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}// 机构对象
	}

	/**
	 * 上传模板
	 * 
	 * @return
	 * @author zhangx
	 */
	public String upload() {
		try {
			String path = ConfigAccess.init().findProp("itemDocument");
			// if(!StringUtils.isBlank(path)){
			// this.errorInfo="未找到文档保存路径!";
			// return StrutsAction.ERROR;
			// }
			String itemIds = getStringParameter("itemIds");
			String workId=getStringParameter("workId");
			String workName=getStringParameter("workName");
			ChangeItem changeItem = null;
			if (id != null) {
				entityobj = changeItemDocumentService.findById(id);
			}
			if (!StringUtils.isBlank(itemIds)) {
				String[] args = itemIds.split("_");
				changeItem = changeItemService
						.findById(Long.parseLong(args[0]));
			}
			String doc = "";// 文件格式
			// String fileName="";//文件名称
			if (!StringUtils.isBlank(uploadFileName)) {
				doc = uploadFileName.substring(
						uploadFileName.lastIndexOf(".") + 1,
						uploadFileName.length());
				// fileName=uploadFileName.substring(0,
				// uploadFileName.lastIndexOf("."));
			}
			if (StringUtils.isBlank(doc)) {
				this.errorInfo = "上传文件不符合,请重新上传!";
				log.error(doc);
				return StrutsAction.ERROR;
			}
			
			String itemDoc = "";
			String docNam = "";
			if (entityobj != null) {
				itemDoc = entityobj.getChangeItem().getDataValue();// 得到指标指定文件类型
				docNam = entityobj.getDocumentName();// 文件名称格式 deptId+指标名称
			} else {
				itemDoc = changeItem.getDataValue();// 文档类型
				docNam = changeItem.getSysDept().getId()
						+ changeItem.getItemCode()
						+ "_"
						+ new SimpleDateFormat("yyyyMMddHHssmmSSS")
								.format(new Date()) + "." + doc;// 文件名称格式
				// 用指标名称命名
			}
			if (!itemDoc.contains(doc)) {
				this.errorInfo = "上传文件格式不符合,请重新上传!";
				log.error(errorInfo);
				return StrutsAction.ERROR;
			}
			if (entityobj != null) {// 存在当前模板对象
				String templatePath = entityobj.getUploadPath();// 文档物理路径
				if (!StringUtils.isBlank(templatePath)) {
					FileUtil.deleOnefile(templatePath);// 删除单个文件
				}
			}
			String saveFileDoc = "/" + docNam;// 文件保存路径
			String pf = path + saveFileDoc;
			File file = new File(pf);// 创建文件对象
			FileUtil.makeMulu(path);// 创建目录
			FileUtil.copy(upload, file);// 上传文件
			if (entityobj == null) {
				entityobj = new ChangeItemDocument();
				entityobj.setChangeItem(changeItem);
			}
			if (!StringUtils.isBlank(uploadFileName)) {
				entityobj.setDocumentName(docNam);// 文档名称
			}
			if(!StringUtils.isBlank(workId)){
				entityobj.setWorkFlowId(Long.parseLong(workId));
			}
			if(!StringUtils.isBlank(workName)){
				entityobj.setWorkFlowName(workName);
			}
			entityobj.setUploadState("1");// 文档状态
			entityobj.setUploadPath(pf);// 文档地址
			SysUser sysUser = (SysUser) getFromSession(Costant.SESSION_USER);
			if (sysUser == null) {
				this.errorInfo = "会话过期,请重新登录!";
				log.error(this.errorInfo);
				return StrutsAction.ERROR;
			}
			// entityobj.setIpAddress(IPUtil.getRequestIpAddr(getHttpServletRequest()));
			entityobj.setUploadDate(DateUtil.toDate(new Date()));
			entityobj.setCreator(sysUser.getUserName());// 创建人
			getEntityService().saveOrUpdate(entityobj);// 修改文档对象
			return StrutsAction.RELOAD;
		} catch (ServiceException e) {
			this.errorInfo = "上传文档失败!";
			log.error(this.errorInfo, e);
			return StrutsAction.ERROR;
		}
	}

	public static void main(String[] args) {
		System.out.println((0.1 + 0.2 - 0.3));
		System.out.println(new SimpleDateFormat("yyyyMMddHHssmm")
				.format(new Date()));
	}

	/**
	 * 选择性上传界面
	 * 
	 * @return
	 * @author zhangx
	 */
	public String multUploadView() {
		SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
		try {
			if (user == null) {
				log.error("会话超时,请重新登录!");
				return StrutsAction.ERROR;
			}
			SysUserDept userDept = sysUserDeptService
					.findByUserId(user.getId());// 用户机构对象
			SysDept sysDept = sysDeptService.findById(userDept.getDeptId());
			if (sysDept != null) {
				if (sysDept.getDeptName().equals("信息管理中心")) {// 如果是管理中心显示全部指标
																// 0文档对象
					items = changeItemService.findListByDataType("0", null);
				} else {// 本部门
					items = changeItemService.findListByDataType("0",
							sysDept.getId());
				}
			}
			return "multUploadView";
		} catch (ServiceException e) {
			this.errorInfo = "查询部门对象失败,请联系管理员!";
			log.error(this.errorInfo, e);
			return StrutsAction.ERROR;
		}
	}

	/**
	 * 删除文档数据
	 */
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
	 * 指标列表 所有交换成功的文档指标 进行发布
	 * 
	 * @return
	 * @author zhangx
	 */
	public String shareList() {
		try {
			/**
			 * 查询所有指标文档类型
			 */
			QueryPara para = new QueryPara();
			para.setName("e.dataType");
			para.setValue("0");
			para.setType(Constants.TYPE_STRING);
			para.setOp(Constants.OP_EQ);
			queryParas.add(para);
			if (!StringUtils.isBlank(deptName)) {
				QueryPara pa = new QueryPara();
				pa.setName("e.sysDept.deptName");
				pa.setValue(deptName);
				pa.setType(Constants.TYPE_STRING);
				pa.setOp(Constants.OP_LIKE);
				queryParas.add(pa);
			}
			if (!StringUtils.isBlank(status)) {
				QueryPara pa = new QueryPara();
				pa.setName("e.shareState");
				pa.setValue(status);
				pa.setType(Constants.TYPE_STRING);
				pa.setOp(Constants.OP_EQ);
				queryParas.add(pa);
			} else {
				status = null;
			}
			this.onBeforeList();
			this.setQueryAndsort();// 设置查询条件
			// 分页查询
			this.listDatas = getEntityService().findAllByPage(
					"from  ChangeItem e", queryParas, sortParas, page);
			this.onAfterList();
			return "shareList";
		} catch (Exception e) {
			this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	/**
	 * 文档发布
	 * 
	 * @return
	 * @author zhangx
	 */
	public String updateStatus() {
		try {
			changeItemDocumentService.updateStatus(ids, status);
			return "shareListAction";
		} catch (ServiceException e) {
			this.errorInfo = "查询文档信息错误,请联系管理员!";
			log.error(this.errorInfo, e);
			return this.ERROR;
		} catch (URISyntaxException e) {
			this.errorInfo = "移动操作文件出错!";
			log.error(this.errorInfo, e);
			return this.ERROR;
		}
	}

	/**
	 * 根据指标得到指定流程
	 * 
	 * @return
	 */
	public String getWorkFlow() {
	 String temp=getStringParameter("temp");
	 if(!StringUtils.isBlank(temp)){
		 String[] args = temp.split("_");
			itemId=Long.parseLong(args[0]);
	 }
		List<WorkFlow> flows = workFlowService.findListByItemId(itemId);
		List<WorkFlow> data=new ArrayList<WorkFlow>();
		for (WorkFlow workFlow : flows) {
			workFlow.setAppMsg(null);
			workFlow.setSysDept(null);
			workFlow.setTaskList(null);
			workFlow.setCloudNodeInfo(null);
			data.add(workFlow);
		}
		Struts2Utils.renderJson(data, "encoding:GBK");
		return null;
	}

	
	@Override
	protected IBaseServices<ChangeItemDocument> getEntityService() {
		return changeItemDocumentService;
	}

	// service
	@Autowired
	public void setChangeItemDocumentService(
			IChangeItemDocumentService changeItemDocumentService) {
		this.changeItemDocumentService = changeItemDocumentService;
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
	public void setChangeItemService(IChangeItemService changeItemService) {
		this.changeItemService = changeItemService;
	}

	@Autowired
	public void setWorkFlowService(IWorkFlowService workFlowService) {
		this.workFlowService = workFlowService;
	}

	// ///property
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public List<ChangeItem> getItems() {
		return items;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
