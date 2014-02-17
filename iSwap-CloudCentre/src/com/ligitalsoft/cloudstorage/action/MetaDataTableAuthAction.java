/*
 * @(#)MetaDataTableAuthAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

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
import com.common.utils.common.StringUtils;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.cloudstorage.service.IMetaDataTableApplyService;
import com.ligitalsoft.cloudstorage.service.IMetaDataTableAuthService;
import com.ligitalsoft.model.cloudstorage.MetaData;
import com.ligitalsoft.model.cloudstorage.MetaDataTableApply;
import com.ligitalsoft.model.cloudstorage.MetaDataTableAuth;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserDept;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.util.Costant;

/**
 * 数据交换管理_数据授权_ACTION
 * 
 * @author zhangx
 * @since Aug 21, 2011 4:36:49 PM
 * @name com.ligitalsoft.cloudstorage.action.MetaDataTableAuthAction.java
 * @version 1.0
 */
@Scope("prototype")
@Namespace("/cloudstorage/dataAuth")
@Action("dataAuth")
@Results({ @Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker") })
public class MetaDataTableAuthAction extends
		FreemarkerBaseAction<MetaDataTableApply> {
	/**
     * 
     */
	private static final long serialVersionUID = 4063545598252627909L;
	private IMetaDataTableApplyService metaDataTableApplyService;
	private IMetaDataTableAuthService metaDataTableAuthService;
	private Long appId;// 应用ID
	private String type;// 元数据类型
	private ISysDeptService deptService;// 部门
	private ISysUserDeptService sysUserDeptService;// 部门用户service
	private Long itemId;// 指标ID
	private String tids;// 申请字段集合
	private String uploadFileName;// 文件名称
	private String uploadContentType;// 文件类型
	private File upload;// 文件对象
	private Long deptId;// 当前用户所属部门
	private MetaData metaData;// 指标对象
	private String nocheckIds;
	private String deptName;// 部门名称
	private String targetName;//指标名称
	private List<MetaDataTableAuth> metaAuthList = new ArrayList<MetaDataTableAuth>();// 字段授权list列表

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.framework.view.FreemarkerBaseAction#onBeforeList()
	 */

	/**
	 * 基础库申请列表
	 * 
	 * @return
	 * @author zhangx
	 */
	@SuppressWarnings("static-access")
	public String listMeta() {
		if (!StringUtils.isBlank(type)) {
			QueryPara queryPara = new QueryPara();// 初始化查询参数
			queryPara.setName("e.metaData.type");
			queryPara.setOp(Constants.OP_EQ);
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(type);
			queryParas.add(queryPara);
		} else {
			QueryPara queryPara = new QueryPara();// 初始化查询参数
			queryPara.setName("e.metaData.type");
			queryPara.setOp(Constants.OP_NE);
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue("3");
			queryParas.add(queryPara);
		}
		if (!StringUtils.isBlank(deptName)) {
			QueryPara queryPara = new QueryPara();// 初始化查询参数
			queryPara.setName("e.sysDept.deptName");
			queryPara.setOp(Constants.OP_LIKE);
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(deptName);
			queryParas.add(queryPara);
		}
		/**
		 * 字段授权通过的 才可以进行数据查看申请
		 */
		QueryPara queryPara = new QueryPara();// 初始化查询参数
		queryPara.setName("e.filedAuthState");//
		queryPara.setOp(Constants.OP_NE);
		queryPara.setType(Constants.TYPE_STRING);
		queryPara.setValue("0");
		queryParas.add(queryPara);

		try {
			this.onBeforeList();
			this.setQueryAndsort();// 设置查询条件
			// 分页查询
			this.listDatas = this.getAllObjectBypage();
			this.onAfterList();
			return "listMeta";
		} catch (Exception e) {
			this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	/**
	 * 应用申请列表
	 * 
	 * @return
	 * @author zhangx
	 */
	@SuppressWarnings("static-access")
	public String listApply() {
		StringBuffer hql = new StringBuffer();
		hql.append("from MetaDataTableApply e");
		if (appId != null && appId != 0L) {
			QueryPara queryPara = new QueryPara();// 初始化查询参数
			queryPara.setName("e.appMsg.id");
			queryPara.setOp(Constants.OP_EQ);
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(appId.toString());
			queryParas.add(queryPara);
		} else {
			QueryPara queryPara = new QueryPara();// 初始化查询参数
			queryPara.setName("e.metaData.type");
			queryPara.setOp(Constants.OP_EQ);
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue("3");// 分类库 应用集合
			queryParas.add(queryPara);
		}
		if (!StringUtils.isBlank(deptName)) {
			QueryPara queryPara = new QueryPara();// 初始化查询参数
			queryPara.setName("e.sysDept.deptName");
			queryPara.setOp(Constants.OP_LIKE);
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(deptName);
			queryParas.add(queryPara);
		}
		/**
		 * 字段授权通过的 才可以进行数据查看申请
		 */
		QueryPara queryPara = new QueryPara();// 初始化查询参数
		queryPara.setName("e.filedAuthState");//
		queryPara.setOp(Constants.OP_NE);
		queryPara.setType(Constants.TYPE_STRING);
		queryPara.setValue("0");
		queryParas.add(queryPara);
		try {
			this.onBeforeList();
			this.setQueryAndsort();// 设置查询条件
			// 分页查询
			listDatas = getEntityService().findAllByPage(hql.toString(),
					queryParas, sortParas, page);
			return "listApply";
		} catch (Exception e) {
			this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	/**
	 * 基础库申请列表
	 * 
	 * @return
	 * @author zhangx
	 */
	@SuppressWarnings("static-access")
	public String listMetaAuth() {
		if (!StringUtils.isBlank(type)) {
			QueryPara queryPara = new QueryPara();// 初始化查询参数
			queryPara.setName("e.metaData.type");
			queryPara.setOp(Constants.OP_EQ);
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(type);
			queryParas.add(queryPara);
		} else {
			QueryPara queryPara = new QueryPara();// 初始化查询参数
			queryPara.setName("e.metaData.type");
			queryPara.setOp(Constants.OP_NE);
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue("3");
			queryParas.add(queryPara);
		}
		if (!StringUtils.isBlank(deptName)) {
			QueryPara queryPara = new QueryPara();// 初始化查询参数
			queryPara.setName("e.sysDept.deptName");
			queryPara.setOp(Constants.OP_LIKE);
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(deptName);
			queryParas.add(queryPara);
		}
		/**
		 * 字段授权通过的 才可以进行数据查看申请
		 */
		QueryPara queryPara = new QueryPara();// 初始化查询参数
		queryPara.setName("e.dataApplyState");//
		queryPara.setOp(Constants.OP_NE);
		queryPara.setType(Constants.TYPE_STRING);
		queryPara.setValue("0");
		queryParas.add(queryPara);
		try {
			this.onBeforeList();
			this.setQueryAndsort();// 设置查询条件
			// 分页查询
			this.listDatas = this.getAllObjectBypage();
			this.onAfterList();
			return "listMetaAuth";
		} catch (Exception e) {
			this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	/**
	 * 基础库申请列表
	 * 
	 * @return
	 * @author zhangx
	 */
	@SuppressWarnings("static-access")
	public String listApplyAuth() {
		StringBuffer hql = new StringBuffer();
		hql.append("from MetaDataTableApply e");
		if (appId != null && appId != 0L) {
			QueryPara queryPara = new QueryPara();// 初始化查询参数
			queryPara.setName("e.appMsg.id");
			queryPara.setOp(Constants.OP_EQ);
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(appId.toString());
			queryParas.add(queryPara);
		} else {
			QueryPara queryPara = new QueryPara();// 初始化查询参数
			queryPara.setName("e.metaData.type");
			queryPara.setOp(Constants.OP_EQ);
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue("3");// 分类库 应用集合
			queryParas.add(queryPara);
		}
		if(!StringUtils.isBlank(targetName)){
			QueryPara queryPara = new QueryPara();// 初始化查询参数
			queryPara.setName("e.metaData.targetName");
			queryPara.setOp(Constants.OP_LIKE);
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(targetName);
			queryParas.add(queryPara);
		}
		/**
		 * 字段授权通过的 才可以进行数据查看申请
		 */
		QueryPara queryPara = new QueryPara();// 初始化查询参数
		queryPara.setName("e.dataApplyState");//
		queryPara.setOp(Constants.OP_NE);
		queryPara.setType(Constants.TYPE_STRING);
		queryPara.setValue("0");
		queryParas.add(queryPara);

		try {
			this.onBeforeList();
			this.setQueryAndsort();// 设置查询条件
			// 分页查询
			listDatas = getEntityService().findAllByPage(hql.toString(),
					queryParas, sortParas, page);
			this.onAfterList();
			return "listApplyAuth";
		} catch (Exception e) {
			this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	@Override
	public String addView() {
		SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
		if (user == null) {
			log.error("会话过期,请重新登录!");
			return ERROR;
		}
		SysUserDept userDept = sysUserDeptService.findByUserId(user.getId());
		try {
			SysDept sysDept = deptService.findById(userDept.getDeptId());
			deptId = sysDept.getId();
			entityobj = metaDataTableApplyService.findByItemDataState(itemId,
					deptId, appId, "1");// 当前部门是否申请申请过数据查看权限
			MetaDataTableApply dataApp = metaDataTableApplyService.findById(id);
			metaData = dataApp.getMetaData();
			this.getHttpServletRequest().setAttribute("dataApp", dataApp);
		} catch (ServiceException e) {
			this.errorInfo = "查找部门失败!";
			log.error(this.errorInfo, e);
		}// 当前用户所属部门

		return super.addView();
	}

	@Override
	@SuppressWarnings("static-access")
	public String add() {
		if (!StringUtils.isBlank(uploadFileName)) {
			entityobj.setDataApplyFileName(uploadFileName);
		}

		InputStream input=null;
		if (upload != null) {
			try {
					input = new BufferedInputStream(new FileInputStream(upload));  
//	            out = new BufferedOutputStream(new FileOutputStream(dst),  
//	                    BUFFER_SIZE);  
//				 input = new BufferedInputStream(
//						new FileInputStream(upload));
				byte[] bb = new byte[Integer.parseInt(upload.length()+"")];
				input.read(bb);
				entityobj.setDataApplyFile(bb);
			} catch (IOException e) {
				this.errorInfo="上传文件失败,请联系管理员!";
				log.error(this.errorInfo,e);
				return this.ERROR;
			}finally{
				try {
					if(input!=null){
						input.close();
					}
					input.close();
				} catch (IOException e) {
					this.errorInfo="关闭文件IO流异常!";
					log.error(errorInfo,e);
					return this.ERROR;
				}
			}
		}
		entityobj.setDataApplyDate(DateUtil.toDate(new Date()));
		metaDataTableApplyService.addDataApply(entityobj, tids, nocheckIds);
		return RELOAD;
	}

	/**
	 * 下载申请文件
	 * 
	 * @author zhangx
	 */
	public String downFile() {
		ServletOutputStream out=null;
		try {
			entityobj = getEntityService().findById(id);
			if (entityobj != null) {
				 out= this.getHttpServletResponse()
						.getOutputStream();
				String fileName = new String(entityobj.getDataApplyFileName()
						.getBytes("gb2312"), "iso8859-1");
				this.getHttpServletResponse().setContentType(
						"APPLICATION/OCTET-STREAM");
				this.getHttpServletResponse().setHeader("Content-Disposition",
						"attachment; filename=" + fileName);
				
				// 得到路径变量
				out.write(entityobj.getDataApplyFile());
				return null;
			}
		} catch (Exception e) {
			this.errorInfo = "下载失败,请联系管理员!";
			log.error(errorInfo,e);
			return ERROR;
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				this.errorInfo = "关闭文件IO流异常!";
				log.error(errorInfo,e);
				return ERROR;
			}
		}
		return null;
	}

	/**
	 * 普通指标授权信息页面
	 * 
	 * @return
	 * @author zhangx
	 */
	public String addAuthView() {
		metaAuthList = metaDataTableAuthService.findByDataAuthStateApplyId(id);// 已申请
		return "addAuthView";
	}

	/**
	 * 增加授权信息
	 * 
	 * @return
	 * @author zhangx
	 */
	public String addAuth() {
		String noIds = getStringParameter("noIds");
		metaDataTableApplyService.addDataAuth(id, ids, noIds);
		return StrutsAction.RELOAD;
	}

	/**
	 * 查看申请界面
	 * 
	 * @return
	 * @author zhangx
	 */
	public String authView() {
		try {
			entityobj = getEntityById();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "authView";
	}

	@Override
	protected void onBeforeView() {
		super.onBeforeView();
		metaAuthList = metaDataTableAuthService.findByDataAuthStateApplyId(id);
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getTids() {
		return tids;
	}

	public void setTids(String tids) {
		this.tids = tids;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public String getNocheckIds() {
		return nocheckIds;
	}

	public void setNocheckIds(String nocheckIds) {
		this.nocheckIds = nocheckIds;
	}

	public List<MetaDataTableAuth> getMetaAuthList() {
		return metaAuthList;
	}

	public void setMetaAuthList(List<MetaDataTableAuth> metaAuthList) {
		this.metaAuthList = metaAuthList;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	
	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	@Override
	protected IBaseServices<MetaDataTableApply> getEntityService() {
		return metaDataTableApplyService;
	}

	@Autowired
	public void setMetaDataTableApplyService(
			IMetaDataTableApplyService metaDataTableApplyService) {
		this.metaDataTableApplyService = metaDataTableApplyService;
	}

	@Autowired
	public void setMetaDataTableAuthService(
			IMetaDataTableAuthService metaDataTableAuthService) {
		this.metaDataTableAuthService = metaDataTableAuthService;
	}

	@Autowired
	public void setDeptService(ISysDeptService deptService) {
		this.deptService = deptService;
	}

	@Autowired
	public void setSysUserDeptService(ISysUserDeptService sysUserDeptService) {
		this.sysUserDeptService = sysUserDeptService;
	}

}
