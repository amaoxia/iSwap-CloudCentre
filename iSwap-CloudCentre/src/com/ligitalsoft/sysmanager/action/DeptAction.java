/*
 * @(#)DeptAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.action;

import java.io.File;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserDept;
import com.ligitalsoft.model.system.SysUserRole;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;
import com.ligitalsoft.sysmanager.util.Costant;
import com.ligitalsoft.sysmanager.util.FileUtil;

import com.common.framework.dao.QueryPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.web.struts2.Struts2Utils;

/**
 * 部门管理Action
 * 
 * @author zhangx
 * @since May 16, 2011 1:40:13 PM
 * @name com.ligitalsoft.sysmanager.action.DeptAction.java
 * @version 1.0
 */
@Namespace("/sysmanager/dept")
@Scope("prototype")
@Results({
		@Result(name = "main", location = "main.ftl", type = "freemarker"),
		@Result(name = StrutsAction.VIEW, location = "view.ftl", type = "freemarker"),
		@Result(name = StrutsAction.UPDATEVIEW, location = "dept!view.action", type = "redirect", params = {
				"id", "${id}", "flag", "${flag}" }) })
public class DeptAction extends FreemarkerBaseAction<SysDept> {

	/**
     * 
     */
	private static final long serialVersionUID = 2235482489898539332L;
	private ISysDeptService sysDeptService;
	private ISysUserDeptService sysUserDeptService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	private Long fatherId;
	private SysDept dept;
	private int flag; // 操作结果
	private File upload;// 文件对象
	private String uploadFileName;// 文件名称
	private String uploadContentType;// 文件类型
	
	/**
	 * 得到部门树
	 * 
	 * @return
	 * @author zhangx
	 */
	public String getDeptTree() {
		JSONArray deptTree = sysDeptService.depTree();
		Struts2Utils.renderJson(deptTree, "encoding:GBK");
		return null;
	}

	/**
	 * 根据部门Id得到下级部门列表,用于异步加载
	 * @return
	 * @author HuJun
	 */
	public String getChildDeptById() {
		JSONArray deptTree = new JSONArray();
		// 获得当前登陆用户
		SysUser user = (SysUser) getSession().get(Costant.SESSION_USER);
		if("administrator".equals(user.getUserName())){
			String id = getStringParameter("data");// 上级ID
			deptTree = sysDeptService.getChildDeptById(id!=null&&!"".equals(id)?new Long(id):null);
		}else{
			// 通过当前用户ID查询用户所属部门
			// 获得当前用户角色
			SysUserRole role = sysUserRoleService.findByUserId(user.getId());
			
			// 如果roleId大于2者为其它部门角色否则为信息中心人员
			if (role.getRoleId() > 2) {
					SysUserDept sysUserDept = sysUserDeptService.findByUserId(user
							.getId());
				deptTree = sysDeptService.getDeptTreeById(sysUserDept.getDeptId());
			}else{
				String id = getStringParameter("data");// 上级ID
				deptTree = sysDeptService.getChildDeptById(id!=null&&!"".equals(id)?new Long(id):null);
			}
		}
		Struts2Utils.renderJson(deptTree, "encoding:GBK");
		return null;
	}
	
	/**
	 * ztree
	 * 
	 * @return
	 */
	public String tree() {
		return null;
	}

	/**
	 * 部门主页面
	 * 
	 * @return
	 * @author zhangx
	 */
	@SuppressWarnings("static-access")
	public String main() {
		try {
		} catch (Exception e) {
			this.errorInfo = "实例化类异常,请联系管理员!";
			log.error(errorInfo, e);
			return this.ERROR;
		}
		return "main";
	}

	/**
	 * 单击节点创建 添加部门信息
	 */
	@SuppressWarnings("static-access")
	public String addDept() {
		entityobj = new SysDept();
		String parentId = getStringParameter("parentId");// 上级ID
		String name = getStringParameter("name");// 部门名称
		try {
			if (!StringUtils.isBlank(parentId) && !StringUtils.isBlank(name)) {
				id = Long.parseLong(parentId);
				SysDept dept = sysDeptService.findById(id);// 查询上级部门对象
				entityobj
						.setDeptName(java.net.URLDecoder.decode(name, "utf-8"));
				entityobj.setSysDept(dept);
				sysDeptService.insert(entityobj);
				Struts2Utils.renderJson(entityobj.getId(), "encoding:GBK");
				log.info("插入数据成功!");
			}
		} catch (Exception e) {
			Struts2Utils.renderJson(false, "encoding:GBK");
			this.errorInfo = "查询部门对象失败!";
			log.error(this.errorInfo, e);
			return this.ERROR;
		}
		return null;
	}

	/**
	 * 当前部门是有子类
	 * 
	 * @return
	 */
	public String isChildren() {
		if (id != null && id != 0L) {
			listDatas = sysDeptService.findByParentId(id);
			if (listDatas != null && listDatas.size() != 0) {
				Struts2Utils.renderText("1", "encoding:GBK");
				return null;
			}
		} else {
			Struts2Utils.renderText("0", "encoding:GBK");
			return null;
		}
		return null;
	}

	/**
	 * 更改部门名称
	 * 
	 * @author zhangx
	 */
	public String rename() {
		String rename = getStringParameter("rename");
		try {
			entityobj = sysDeptService.findById(id);
			if (entityobj != null) {
				entityobj.setDeptName(java.net.URLDecoder.decode(rename,
						"utf-8"));
				sysDeptService.saveOrUpdate(entityobj);
				log.info("[deptAction] :修改部门名称成功!");
			}
			Struts2Utils.renderJson(true, "encoding:GBK");
		} catch (Exception e) {
			Struts2Utils.renderJson(false, "encoding:GBK");
			this.errorInfo = "更新数据失败!";
			log.error(this.errorInfo, e);
		}
		return null;
	}

	/**
	 * 删除部门信息
	 */
	@Override
	public String delete() {
		try {
			if (id == null || id == 0) {
				Struts2Utils.renderJson(false, "encoding:GBK");
				return null;
			}
			List<SysUserDept> sysUserDepts = sysUserDeptService
					.findUserByDeptId(id);
			if (sysUserDepts != null && sysUserDepts.size() > 0) {
				Struts2Utils.renderJson(false, "encoding:GBK");
				return null;
			}
			sysDeptService.deleteById(id);
			Struts2Utils.renderJson(true, "encoding:GBK");
			return null;
		} catch (ServiceException e) {
			this.errorInfo = "删除机构信息失败!";
			log.error(this.errorInfo, e);
			Struts2Utils.renderJson(false, "encoding:GBK");
			return null;
		} finally {
			Struts2Utils.renderJson(false, "encoding:GBK");
		}
	}

	/**
	 * 部门树复制或剪切操作
	 * 
	 * @return
	 * @author zhangx
	 */
	public String copyOrCut() {
		try {
			entityobj = sysDeptService.findById(id);
			String copy = getStringParameter("copy");
			SysDept dept = new SysDept();
			if (!StringUtils.isBlank(copy)) {
				if (copy.equals("1")) {// 复制
					BeanUtils.copyProperties(dept, entityobj);
					dept.setId(null);
					dept.setChildrenDept(null);
					sysDeptService.insert(dept);
					Struts2Utils.renderJson(dept, "encoding:GBK");
					return null;
				} else {// 剪切
					entityobj.setSysDept(sysDeptService.findById(fatherId));
					sysDeptService.saveOrUpdate(entityobj);
					Struts2Utils.renderJson(true, "encoding:GBK");
				}
			}
		} catch (Exception e) {
			Struts2Utils.renderJson(false, "encoding:GBK");
			this.errorInfo = "修改数据失败!";
			log.error(this.errorInfo, e);
		}
		return null;
	}

	/**
	 * 排序
	 * 
	 * @return
	 */
	public String move() {
		String parentId = getStringParameter("parentId");
		SysDept sysDept = null;
		if (id != null) {
			try {
				entityobj = getEntityById();
				if (entityobj == null) {
					Struts2Utils.renderJson(false, "encoding:GBK");
					return null;
				}
				if (!StringUtils.isBlank(parentId)) {
					sysDept = getEntityById(Long.parseLong(parentId));
				}
				entityobj.setSysDept(sysDept);
				sysDeptService.update(entityobj);
				Struts2Utils.renderJson(true, "encoding:GBK");
				return null;
			} catch (ServiceException e) {
				Struts2Utils.renderJson(false, "encoding:GBK");
				this.errorInfo = "移动部门失败!";
				log.error(this.errorInfo, e);
			} finally {
				Struts2Utils.renderJson(false, "encoding:GBK");
			}
		}
		return null;
	}

	/**
	 * 部门排序
	 * 
	 * @return
	 */
	public String order() {
		String ids = getStringParameter("sids");
		try {
			if (!StringUtils.isBlank(ids)) {
				sysDeptService.setOrder(ids);
				Struts2Utils.renderJson(true, "encoding:GBK");
				System.out.println("SUCC");
				return null;
			}
		} catch (ServiceException e) {
			Struts2Utils.renderJson(false, "encoding:GBK");
			System.out.println("ERROR");
			return null;
		} finally {
			Struts2Utils.renderJson(false, "encoding:GBK");
			System.out.println("finally");
		}
		return null;
	}

	/**
	 * 部门信息
	 */
	@Override
	@SuppressWarnings("static-access")
	public String view() {
		try {
			if (id != null) {
				entityobj = sysDeptService.findById(id);
				if (entityobj == null) {
					entityobj = new SysDept();
				}
			}
		} catch (ServiceException e) {
			this.errorInfo = "查找机构信息失败!";
			log.error(this.errorInfo, e);
			return this.ERROR;
		}
		return StrutsAction.VIEW;
	}

	/**
	 * 保存或修改信息
	 * 
	 * @return
	 * @author zhangx
	 */
	@SuppressWarnings("static-access")
	public String saveOrUpdate() {
		StringBuffer buffer = new StringBuffer();
		StringBuffer filePath = new StringBuffer();
		StringBuffer logoPath = new StringBuffer();
		SysUser cUser = (SysUser) this.getSession().get(Costant.SESSION_USER);
		if (cUser == null) {
			this.errorInfo = "会话已过期,请重新登录!";
			return this.ERROR;
		}
		// ConfigAccess.init().findProp("deptLogo");//
		String dirPath = ServletActionContext.getServletContext().getRealPath(
				"/");
		buffer.append(dirPath);
		SysDept dept = null;
		try {
			if (id != null && id != 0) {
				dept = sysDeptService.findById(id);
				if (upload != null) {
					if (dept != null
							&& !StringUtils.isBlank(dept.getLogoPath())) {
						buffer.append("\\deptLogo\\");
						buffer.append(dept.getDeptUid());
						buffer.append(dept.getLogoPath());
						FileUtil.deleOnefile(buffer.toString());// 删除文件

					}
				}
			}
			if (upload != null) {// 文件对象
				String doc = uploadFileName.substring(
						uploadFileName.lastIndexOf(".") + 1,
						uploadFileName.length());
				filePath.append(dirPath);
				filePath.append("\\deptLogo\\");
				filePath.append(entityobj.getDeptUid());
				filePath.append(entityobj.getDeptName());
				filePath.append(".");
				filePath.append(doc);
				FileUtil.makeMulu(dirPath + "\\deptLogo");// 创建目录
				File dstFile = new File(filePath.toString());
				if (FileUtil.copy(upload, dstFile)) {
					if (upload != null) {
						logoPath.append("\\deptLogo");
						logoPath.append(entityobj.getDeptUid());
						logoPath.append(entityobj.getDeptName());
						logoPath.append("." + doc);
						entityobj.setLogoPath(logoPath.toString());// 设置文件相对路径
					}
				}
			}
			entityobj.setCreator(cUser.getUserName());// 创建者
			sysDeptService.saveOrUpdate(entityobj);
			flag = 1;
		} catch (ServiceException e) {
			flag = 0;
			this.errorInfo = "修改部门失败!";
			log.error(this.errorInfo, e);
			return this.ERROR;
		}
		return StrutsAction.UPDATEVIEW;
	}

	/**
	 * 用户部门标识符是否重复
	 * 
	 * @return
	 * @author zhangx
	 */
	public String checkDeptUid() {
		String deptUid = getStringParameter("deptUid").trim();
		String id = getStringParameter("id");
		String result = "";
		SysDept dept = null;
		getHttpServletResponse().setCharacterEncoding("GBK");
		try {
			if (!StringUtils.isBlank(deptUid)) {
				dept = sysDeptService.findUniqueByProperty("deptUid", deptUid);
			}
			if (dept == null) {
				result = "succ";
			} else {
				if (!StringUtils.isBlank(id)) {
					if (dept.getId().toString().equals(id)) {
						result = "succ";
					}
				}
			}
			Struts2Utils.renderText(result, "encoding:GBK");
		} catch (ServiceException e) {
			log.error("userService invoke exception ", e);
		}
		return null;
	}

	public void prepareSaveOrUpdate() throws Exception {
		if (id != null && id != 0) {
			entityobj = getEntityById();
		} else {
			entityobj = new SysDept();
		}
	}

	@Autowired
	public void setSysDeptService(ISysDeptService sysDeptService) {
		this.sysDeptService = sysDeptService;
	}

	@Autowired
	public void setSysUserDeptService(ISysUserDeptService sysUserDeptService) {
		this.sysUserDeptService = sysUserDeptService;
	}

	@Override
	protected IBaseServices<SysDept> getEntityService() {
		return sysDeptService;
	}

	// ////////////////////// 参数属性

	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	public SysDept getDept() {
		return dept;
	}

	public void setDept(SysDept dept) {
		this.dept = dept;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
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
}
