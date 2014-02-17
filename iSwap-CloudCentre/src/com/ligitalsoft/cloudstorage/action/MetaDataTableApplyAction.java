/*
 * @(#)MetaDataTableApplyAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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
import com.common.utils.email.EmailConfig;
import com.common.utils.email.MailService;
import com.common.utils.email.MailServiceImpl;
import com.ligitalsoft.cloudstorage.service.IMetaDataAppMsgService;
import com.ligitalsoft.cloudstorage.service.IMetaDataService;
import com.ligitalsoft.cloudstorage.service.IMetaDataTableApplyService;
import com.ligitalsoft.cloudstorage.service.IMetaDataTableAuthService;
import com.ligitalsoft.model.cloudstorage.MetaData;
import com.ligitalsoft.model.cloudstorage.MetaDataAppMsg;
import com.ligitalsoft.model.cloudstorage.MetaDataTableApply;
import com.ligitalsoft.model.cloudstorage.MetaDataTableAuth;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserDept;
import com.ligitalsoft.model.system.SysUserRole;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;
import com.ligitalsoft.sysmanager.util.Costant;

/**
 * 数据申请授权表
 * 
 * @author zhangx
 * @since Aug 11, 2011 1:46:05 AM
 * @name com.ligitalsoft.cloudstorage.action.MetaDataTableApplyAction.java
 * @version 1.0
 */
@Scope("prototype")
@Namespace("/cloudstorage/apply")
@Action("apply")
@Results({
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
		@Result(name = "serachListAction", location = "apply!serachList.action", type = "redirectAction", params = {
				"page.index", "${page.index}" }),
		@Result(name = "listAction", location = "apply!list.action", type = "redirectAction"),
		@Result(name = "listMetaAuthAction", location = "apply!listMetaAuth.action", type = "redirectAction", params = {
				"type", "${type}" }),
		@Result(name = "listApplyAuthAction", location = "apply!listMetaAuth.action", type = "redirectAction", params = {
				"appId", "${appId}" }),
		@Result(name = "excel", type = "stream", params = {
				"contentType",// application/vnd.ms-excel;charset=ISO8859-1
				" application/octet-stream", "inputName", "excelStream",
				"contentDisposition", "attachment;filename=${fileName}",
				"bufferSize", "4096" }) })
public class MetaDataTableApplyAction extends
		FreemarkerBaseAction<MetaDataTableApply> {
	/**
     * 
     */
	private static final long serialVersionUID = 1056241259785278243L;
	private IMetaDataTableApplyService applyService;
	private String type;// 元数据类型
	private IMetaDataAppMsgService metaDataAppMsgService;// 元数据应用service
	private IMetaDataService metaDataService;// 元数据service
	private MetaData metaData;// 指标对象
	private IMetaDataTableAuthService metaDataTableAuthService;// 申请数据项授权_SERVICE
	private ISysDeptService deptService;// 部门
	private ISysUserDeptService sysUserDeptService;// 部门用户service
	private ISysUserRoleService sysUserRoleService;// 用户角色service
	private File upload;// 文件对象
	private String uploadFileName;// 文件名称
	private String uploadContentType;// 文件类型
	private InputStream excelStream;// excel文件输出流
	private String fileName;// 文件名称
	private Long itemId;// 元数据ID
	private Long appId;// 应用ID
	private String tids;// 字段ID集合
	private Long deptId;// 当前部门ID
	private Long dataAppId;// 元数据_应用ID
	private MetaDataAppMsg dataApp;
	private String targetName;// 指标名称
	private List<MetaDataTableAuth> metaAuthList = new ArrayList<MetaDataTableAuth>();// 字段授权list列表
	private List<Object[]> obj = new ArrayList<Object[]>();// 数据列表

	@Override
	protected void onBeforeAddView() {
		try {
			if (dataAppId != null && dataAppId != 0L) {
				dataApp = metaDataAppMsgService.findById(dataAppId);// 查询某个应用关联的指标
				metaData = dataApp.getMetaData();
			} else {
				metaData = metaDataService.findById(itemId);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
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
		SysDept sysDept;
		try {
			sysDept = deptService.findById(userDept.getDeptId());
			deptId = sysDept.getId();// 部门ID
			entityobj = applyService.findByItemDeptId(itemId, sysDept.getId(),
					appId);// 查找是否申请过
		} catch (ServiceException e) {
			this.errorInfo = "查找部门失败!";
			log.error(this.errorInfo, e);
		}// 当前用户所属部门

		return super.addView();
	}

	/**
	 * 原始库列表
	 * 
	 * @return
	 * @author zhangx
	 */
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
		this.onBeforeList();
		this.setQueryAndsort();// 设置查询条件
		// 分页查询
		try {
			this.listDatas = getEntityService().findAllByPage(
					"from MetaDataTableApply e", queryParas, sortParas, page);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		this.onAfterList();
		return "listMetaAuth";
	}

	/**
	 * 带有应用库列表
	 * 
	 * @return
	 * @author zhangx
	 */
	public String listApplyAuth() {
		StringBuffer hql = new StringBuffer();
		if (appId != null && appId != 0L) {
			QueryPara para = new QueryPara();
			para.setName("e.appMsg.id");
			para.setOp(Constants.OP_EQ);
			para.setType(Constants.TYPE_LONG);
			para.setValue(appId.toString());
			queryParas.add(para);
			hql.append("from MetaDataTableApply e");
		} else {
			hql.append("from MetaDataTableApply e where e.appMsg.id is not null");
		}
		if (!StringUtils.isBlank(targetName)) {
			hql.append(" and e.metaData.targetName like '%" + targetName + "%'");
		}
		this.onBeforeList();
		this.setQueryAndsort();// 设置查询条件
		// 分页查询
		try {
			this.listDatas = getEntityService().findAllByPage(hql.toString(),
					queryParas, sortParas, page);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		this.onAfterList();
		this.onBeforeList();
		this.setQueryAndsort();// 设置查询条件
		return "listApplyAuth";
	}

	@Override
	@SuppressWarnings("static-access")
	public String add() {
		if (!StringUtils.isBlank(uploadFileName)) {
			entityobj.setFiledApplyName(uploadFileName);
		}
		InputStream input = null;
		if (upload != null) {
			try {
				input = new BufferedInputStream(new FileInputStream(upload));
				byte[] bb = new byte[Integer.parseInt(upload.length() + "")];
				input.read(bb);
				entityobj.setFiledApplyFile(bb);
			} catch (IOException e) {
				this.errorInfo = "上传文件失败,请联系管理员!";
				log.error(this.errorInfo, e);
				return this.ERROR;
			}
		}
		try {
			SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
			if (user == null) {
				log.error("会话过期,请重新登录!");
				return ERROR;
			}
			if (entityobj.getSysDept() == null) {// 部门为null 添加操作
				SysUserDept userDept = sysUserDeptService.findByUserId(user
						.getId());
				SysDept sysDept = deptService.findById(userDept.getDeptId());
				entityobj.setSysDept(sysDept);
			}
			applyService.saveOrUpdate(itemId, appId, entityobj, tids);
		} catch (ServiceException e) {

		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				this.errorInfo = "关闭文件IO流异常!";
				log.error(this.errorInfo, e);
				return this.ERROR;
			}
		}
		return RELOAD;
	}

	/**
	 * 普通指标授权信息页面
	 * 
	 * @return
	 * @author zhangx
	 */
	public String addAuthView() {
		try {
			entityobj = applyService.findById(id);
			metaAuthList = metaDataTableAuthService.findByTableApplyId(id);
		} catch (ServiceException e) {
			this.errorInfo = "查询异常!";
			log.error(this.errorInfo, e);
		}
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
		applyService.addAuth(id, ids, noIds);
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

	/**
	 * 下载申请文件
	 * 
	 * @author zhangx
	 */
	public String downFile() {
		ServletOutputStream out = null;
		try {
			entityobj = getEntityService().findById(id);
			if (entityobj != null) {
				out = this.getHttpServletResponse().getOutputStream();
				if (!StringUtils.isBlank(entityobj.getFiledApplyName())) {
					// this.fileName = new
					// String(fileName.getBytes("ISO-8859-1"),"UTF-8");
					String fileName = new String(entityobj.getFiledApplyName()
							.getBytes("gb2312"), "ISO-8859-1");
					this.getHttpServletResponse().setContentType(
							"APPLICATION/OCTET-STREAM");
					this.getHttpServletResponse().setHeader(
							"Content-Disposition",
							"attachment; filename=" + fileName);
					// 得到路径变量
					out.write(entityobj.getFiledApplyFile());
					return null;
				}
			}
		} catch (Exception e) {
			this.errorInfo = "关闭文件IO流异常!";
			log.error(errorInfo, e);
			return ERROR;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				this.errorInfo = "下载失败,请联系管理员!";
				log.error(errorInfo, e);
				return ERROR;
			}
		}
		return null;
	}

	/**
	 * 数据查询列表
	 */
	@SuppressWarnings("static-access")
	public String serachList() {
		try {
			SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
			// System.out.println("藤原拓海");
			if (user == null) {
				log.error("会话过期,请重新登录!");
				return ERROR;
			}
			SysUserRole sysUserRole = sysUserRoleService.findByUserId(user
					.getId());
			SysUserDept userDept = sysUserDeptService
					.findByUserId(user.getId());
			// not 中心部门
			if (sysUserRole.getRoleId() > 2) {
				SysDept sysDept = deptService.findById(userDept.getDeptId());
				QueryPara que = new QueryPara();// 初始化查询参数
				que.setName("e.sysDept.id");
				que.setOp(Constants.OP_EQ);
				que.setType(Constants.TYPE_LONG);
				que.setValue(sysDept.getId().toString());
				queryParas.add(que);
			}
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
			// if (!StringUtils.isBlank(targetName)) {
			// QueryPara qp = new QueryPara();// 初始化查询参数
			// qp.setName("e.metaData.targetName");
			// qp.setOp(Constants.OP_LIKE);
			// qp.setType(Constants.TYPE_STRING);
			// qp.setValue(targetName);
			// queryParas.add(qp);
			// }
			this.setQueryAndsort();// 设置查询条件
			// 分页查询
			this.listDatas = getEntityService().findAllByPage(
					"from MetaDataTableApply e", queryParas, sortParas, page);
			return "serachList";
		} catch (Exception e) {
			this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	/**
	 * 数据查询列表
	 */
	@SuppressWarnings("static-access")
	public String serachAppList() {
		SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
		if (user == null) {
			log.error("会话过期,请重新登录!");
			return ERROR;
		}
		if (appId != null && appId != 0L) {
			QueryPara para = new QueryPara();
			para.setName("e.appMsg.id");
			para.setOp(Constants.OP_EQ);
			para.setType(Constants.TYPE_LONG);
			para.setValue(appId.toString());
			queryParas.add(para);
		}
		QueryPara queryPara = new QueryPara();// 初始化查询参数
		queryPara.setName("e.metaData.type");
		queryPara.setOp(Constants.OP_EQ);
		queryPara.setType(Constants.TYPE_STRING);
		queryPara.setValue("3");
		queryParas.add(queryPara);
		// if (!StringUtils.isBlank(targetName)) {
		// QueryPara qp = new QueryPara();// 初始化查询参数
		// qp.setName("e.metaData.targetName");
		// qp.setOp(Constants.OP_LIKE);
		// qp.setType(Constants.TYPE_STRING);
		// qp.setValue(targetName);
		// queryParas.add(qp);
		// }
		try {
			SysUserRole sysUserRole = sysUserRoleService.findByUserId(user
					.getId());
			// not 中心部门
			if (sysUserRole.getRoleId() > 2) {
				SysUserDept userDept = sysUserDeptService.findByUserId(user
						.getId());
				QueryPara que = new QueryPara();// 初始化查询参数
				que.setName("e.sysDept.id");
				que.setOp(Constants.OP_EQ);
				que.setType(Constants.TYPE_LONG);
				que.setValue(userDept.getDeptId().toString());
				queryParas.add(que);
			}
			this.setQueryAndsort();// 设置查询条件
			// 分页查询
			this.listDatas = getEntityService().findAllByPage(
					"from MetaDataTableApply e", queryParas, sortParas, page);
			return "serachAppList";
		} catch (ServiceException e) {
			this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	/**
	 * 数据列表
	 * 
	 * @return
	 * @author zhangx
	 */
	public String dataList() {
		try {
			obj = applyService.findDataList(id, page);
			metaAuthList = metaDataTableAuthService.findByFiledAuthApplyId(id,
					"1");// 字段申请通过
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "dataList";
	}

	/**
	 * 下载数据
	 * 
	 * @author zhangx
	 */
	@SuppressWarnings("static-access")
	public String downLoadData() {
		// FileInputStream fileInputStream = null;
		// ServletOutputStream out = null;
		WritableWorkbook book = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			entityobj = getEntityById(id);
			obj = applyService.findDataList(id);
			metaAuthList = metaDataTableAuthService.findByDataAuthStateApplyId(
					id, "1");// 数据字段授权通过
			// out = this.getHttpServletResponse().getOutputStream();
			// File excelFile = new File(entityobj.getMetaData().getTargetName()
			// + "_指标数据.xls");// 创建文件
			/**
			 * 定义与设置Sheet
			 */
			book = Workbook.createWorkbook(out);
			WritableSheet sheet = book.createSheet(" 第一页 ", 0);
			/**
			 * 定义单元格样式
			 */
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
			WritableCellFormat wcf = new WritableCellFormat(wf); // 单元格定义
			// wcf.setBackground(jxl.format.Colour.BLACK); // 设置单元格的背景颜色
			wcf.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式

			for (int i = 0; i < metaAuthList.size(); i++) {// 添加表头
				Label label = new Label(i, 0, metaAuthList.get(i)
						.getTableInfo().getName(), wcf);
				sheet.setColumnView(i, 30); // 设置列的宽度
				sheet.addCell(label);
			}
			for (int i = 1; i <= obj.size(); i++) {// 行数
				for (int j = 0; j < metaAuthList.size(); j++) {// 列数
					Object[] objects = obj.get(i - 1);
					String name = "";
					if (objects[j] != null) {
						name = objects[j].toString();
					} else {
						name = "";
					}
					Label label = new Label(j, i, name, wcf);// 添加数据
					sheet.addCell(label);
				}
			}
			book.write();
			book.close();
			fileName = java.net.URLEncoder.encode(entityobj.getMetaData()
					.getTargetName() + "指标数据.xls", "UTF-8");
			// this.fileName = new
			// String(fileName.getBytes("ISO-8859-1"),"UTF-8");
			// getHttpServletResponse().setHeader("Content-Disposition",
			// "attachment;fileName="
			// + java.net.URLEncoder.encode(fileName,"UTF-8"));
			excelStream = new ByteArrayInputStream(out.toByteArray());// 输出流转变成输入流
		} catch (ServiceException e) {
			this.errorInfo = "查询数据失败!";
			log.error(this.errorInfo, e);
		} catch (IOException e) {
			this.errorInfo = "写入文件失败!";
			log.error(this.errorInfo, e);
		} catch (RowsExceededException e) {
			this.errorInfo = "写入文件失败!";
			log.error(this.errorInfo, e);
		} catch (WriteException e) {
			this.errorInfo = "写入文件失败!";
			log.error(this.errorInfo, e);
		} finally {
			try {
				// if (fileInputStream != null) {
				// fileInputStream.close();
				// }
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				this.errorInfo = "关闭异常!";
				log.error(this.errorInfo, e);
				return this.ERROR;
			}
		}
		return "excel";
	}

	/**
	 * 生成File文件 供email发送文件
	 */
	public void downLoad() {
		WritableWorkbook book = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		OutputStream outputStream = null;
		try {
			entityobj = getEntityById(id);
			obj = applyService.findDataList(id);
			metaAuthList = metaDataTableAuthService.findByDataAuthStateApplyId(
					id, "1");
			/**
			 * 定义与设置Sheet
			 */
			book = Workbook.createWorkbook(out);
			WritableSheet sheet = book.createSheet(" 第一页 ", 0);
			/**
			 * 定义单元格样式
			 */
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLUE); // 定义格式 字体 下划线 斜体 粗体 颜色
			WritableCellFormat wcf = new WritableCellFormat(wf); // 单元格定义
			// wcf.setBackground(jxl.format.Colour.BLACK); // 设置单元格的背景颜色
			wcf.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式

			for (int i = 0; i < metaAuthList.size(); i++) {// 添加表头
				Label label = new Label(i, 0, metaAuthList.get(i)
						.getTableInfo().getName(), wcf);
				sheet.setColumnView(i, 30); // 设置列的宽度
				sheet.addCell(label);
			}
			for (int i = 1; i <= obj.size(); i++) {// 行数
				for (int j = 0; j < metaAuthList.size(); j++) {// 列数
					Object[] objects = obj.get(i - 1);
					String name = "";
					if (objects[j] != null) {
						name = objects[j].toString();
					} else {
						name = "";
					}
					Label label = new Label(j, i, name, wcf);// 添加数据
					sheet.addCell(label);
				}
			}
			book.write();
			book.close();
			fileName = entityobj.getMetaData().getTargetName() + "指标数据.xls";
			// fileName = java.net.URLEncoder.encode(entityobj.getMetaData()
			// .getTargetName() + "指标数据.xls", "UTF-8");
			//
			excelStream = new ByteArrayInputStream(out.toByteArray());//
			outputStream = new FileOutputStream(new File(fileName));
			byte[] bt = new byte[2048];
			int len = 0;
			while ((len = excelStream.read(bt, 0, bt.length)) != -1) {
				outputStream.write(bt, 0, len);
			}
			outputStream.flush();
		} catch (ServiceException e) {
			this.errorInfo = "查询数据失败!";
			log.error(this.errorInfo, e);
		} catch (IOException e) {
			this.errorInfo = "写入文件失败!";
			log.error(this.errorInfo, e);
		} catch (RowsExceededException e) {
			this.errorInfo = "写入文件失败!";
			log.error(this.errorInfo, e);
		} catch (WriteException e) {
			this.errorInfo = "写入文件失败!";
			log.error(this.errorInfo, e);
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
				if (excelStream != null) {
					excelStream.close();
				}
				if (out != null) {
					out.close();
				}

			} catch (IOException e) {
				this.errorInfo = "关闭异常!";
				log.error(this.errorInfo, e);
			}
		}
	}

	public String sendMail() {
		SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
		if (user == null) {
			log.error("会话过期,请重新登录!");
			return ERROR;
		}
		WritableWorkbook book = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		OutputStream outputStream = null;
		try {
			entityobj = getEntityById(id);
			obj = applyService.findDataList(id);
			metaAuthList = metaDataTableAuthService.findByDataAuthStateApplyId(
					id, "1");
			/**
			 * 定义与设置Sheet
			 */
			book = Workbook.createWorkbook(out);
			WritableSheet sheet = book.createSheet(" 第一页 ", 0);
			/**
			 * 定义单元格样式
			 */
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			// jxl.format.Colour.BLUE; // 定义格式 字体 下划线 斜体 粗体 颜色
			WritableCellFormat wcf = new WritableCellFormat(wf); // 单元格定义
			// wcf.setBackground(jxl.format.Colour.BLACK); // 设置单元格的背景颜色
			wcf.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式

			for (int i = 0; i < metaAuthList.size(); i++) {// 添加表头
				Label label = new Label(i, 0, metaAuthList.get(i)
						.getTableInfo().getName(), wcf);
				sheet.setColumnView(i, 30); // 设置列的宽度
				sheet.addCell(label);
			}
			for (int i = 1; i <= obj.size(); i++) {// 行数
				for (int j = 0; j < metaAuthList.size(); j++) {// 列数
					Object[] objects = obj.get(i - 1);
					String name = "";
					if (objects[j] != null) {
						name = objects[j].toString();
					} else {
						name = "";
					}
					Label label = new Label(j, i, name, wcf);// 添加数据
					sheet.addCell(label);
				}
			}
			book.write();
			book.close();
			fileName = entityobj.getMetaData().getTargetName() + "指标数据.xls";
			// fileName = java.net.URLEncoder.encode(entityobj.getMetaData()
			// .getTargetName() + "指标数据.xls", "UTF-8");
			//
			excelStream = new ByteArrayInputStream(out.toByteArray());//
			outputStream = new FileOutputStream(new File(fileName));
			byte[] bt = new byte[2048];
			int len = 0;
			while ((len = excelStream.read(bt, 0, bt.length)) != -1) {
				outputStream.write(bt, 0, len);
			}
			outputStream.flush();
		} catch (ServiceException e) {
			this.errorInfo = "查询数据失败!";
			log.error(this.errorInfo, e);
		} catch (IOException e) {
			this.errorInfo = "写入文件失败!";
			log.error(this.errorInfo, e);
		} catch (RowsExceededException e) {
			this.errorInfo = "写入文件失败!";
			log.error(this.errorInfo, e);
		} catch (WriteException e) {
			this.errorInfo = "写入文件失败!";
			log.error(this.errorInfo, e);
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
				if (excelStream != null) {
					excelStream.close();
				}
				if (out != null) {
					out.close();
				}

			} catch (IOException e) {
				this.errorInfo = "关闭异常!";
				log.error(this.errorInfo, e);
			}
		}
		// /////////////////////////////
		MailService mailService = new MailServiceImpl();
		String subject = entityobj.getMetaData().getTargetName() + "指标数据";
		String contents = entityobj.getMetaData().getTargetName() + "指标数据";
		if(!StringUtils.isBlank(user.getEmail())){
			String[] userEmailAddress = new String[] { user.getEmail().trim()};
			String[] multiPaths = new String[] { fileName };
			mailService.sendMultiPartEmail(subject, contents, userEmailAddress,
					EmailConfig.MAIL_HOSTUSERNAME, multiPaths);
		}
		return "serachListAction";
	}

	public static void main(String[] args) {
		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(new File(
					"E://test.xls"));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet(" 第一页 ", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			Label label = new Label(0, 0, " test ");
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			/*
			 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			 */
			jxl.write.Number number = new jxl.write.Number(1, 0, 555.12541);
			sheet.addCell(number);
			// 写入数据并关闭文件
			book.write();
			book.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	protected void onBeforeView() {
		super.onBeforeView();
		metaAuthList = metaDataTableAuthService.findByTableApplyId(id);
	}

	@Override
	public String update() {
		return super.update();
	}

	@Override
	protected IBaseServices<MetaDataTableApply> getEntityService() {
		return applyService;
	}

	@Autowired
	public void setApplyService(IMetaDataTableApplyService applyService) {
		this.applyService = applyService;
	}

	@Autowired
	public void setMetaDataService(IMetaDataService metaDataService) {
		this.metaDataService = metaDataService;
	}

	@Autowired
	public void setMetaDataAppMsgService(
			IMetaDataAppMsgService metaDataAppMsgService) {
		this.metaDataAppMsgService = metaDataAppMsgService;
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

	@Autowired
	public void setSysUserRoleService(ISysUserRoleService sysUserRoleService) {
		this.sysUserRoleService = sysUserRoleService;
	}

	public List<Object[]> getObj() {
		return obj;
	}

	public void setObj(List<Object[]> obj) {
		this.obj = obj;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
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

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public String getTids() {
		return tids;
	}

	public void setTids(String tids) {
		this.tids = tids;
	}

	public MetaDataAppMsg getDataApp() {
		return dataApp;
	}

	public void setDataApp(MetaDataAppMsg dataApp) {
		this.dataApp = dataApp;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public List<MetaDataTableAuth> getMetaAuthList() {
		return metaAuthList;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getDataAppId() {
		return dataAppId;
	}

	public void setDataAppId(Long dataAppId) {
		this.dataAppId = dataAppId;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}