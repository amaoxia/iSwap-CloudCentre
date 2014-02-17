/*
 * @(#)TemplateAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.action;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;

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
import com.ligitalsoft.datasharexchange.service.IChangeItemTemplateService;
import com.ligitalsoft.model.changemanage.ChangeItemTemplate;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserDept;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.util.Costant;
import com.ligitalsoft.sysmanager.util.FileUtil;

/**
 * 交换指标文档ACTION
 * @author zhangx
 * @since Jun 28, 2011 9:23:29 AM
 * @name com.ligitalsoft.datasharexchange.action.TemplateAction.java
 * @version 1.0
 */
@Scope("prototype")
@Namespace("/exchange/template")
@Results( { @Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
        @Result(name = "listAction", location = "template!list.action", type = "redirectAction") })
public class TemplateAction extends FreemarkerBaseAction<ChangeItemTemplate> {

    /**
     * 
     */
    private static final long serialVersionUID = -8274931431633890560L;
    private IChangeItemTemplateService changeItemTemplateService;
    private ISysUserDeptService sysUserDeptService;
    private ISysDeptService sysDeptService;
    private File upload;// 上传文件对象
    private String uploadFileName;// 文件名称

    /**
     * list列表之前根据当前用户部门查询
     */
    @Override
    protected void onBeforeList() {
        SysUser user = (SysUser) getFromSession(Costant.SESSION_USER);
        if (user == null) {
            log.error("会话过去请重新登录!");
            return;
        }
        try {
            SysUserDept userDept = sysUserDeptService.findByUserId(user.getId());// 用户机构对象
            SysDept sysDept = sysDeptService.findById(userDept.getDeptId());
            // 是否机构中心
            if (sysDept != null && !sysDept.getDeptName().equals("信息管理中心")) {
                QueryPara queryPara = new QueryPara();
                queryPara.setName("e.changeItem.sysDept.id");
                queryPara.setOp(Constants.OP_EQ_VALUE);
                queryPara.setType(Constants.TYPE_LONG);
                queryPara.setValue(userDept.getDeptId().toString());
            } else {
                this.getHttpServletRequest().setAttribute("center", "1");// 1 部门中心
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }// 机构对象
    }

    /**
     * 上传模板
     * @return
     * @author zhangx
     */
    public String upload() {
        try {
            String path = ConfigAccess.init().findProp("itemTemplate");
            if (id != null) {
                entityobj = changeItemTemplateService.findById(id);
            }
            String doc = "";// 文件格式
            // String fileName="";//文件名称
            if (!StringUtils.isBlank(uploadFileName)) {
                doc = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1, uploadFileName.length());
                // fileName=uploadFileName.substring(0, uploadFileName.lastIndexOf("."));
            }
            if (StringUtils.isBlank(doc)) {
                this.errorInfo = "上传文件不符合,请重新上传!";
                log.error(doc);
                return StrutsAction.ERROR;
            }
            String itemDoc = entityobj.getChangeItem().getDataValue();// 得到指标指定文件类型
            if (!itemDoc.contains(doc)) {
                this.errorInfo = "上传文件格式不符合,请重新上传!";
                log.error(errorInfo);
                return StrutsAction.ERROR;
            }
            if (entityobj != null) {// 存在当前模板对象
                String templatePath = entityobj.getTemplatePath();// 模板物理路径
                if (!StringUtils.isBlank(templatePath)) {
                    FileUtil.deleOnefile(templatePath);// 删除单个文件
                }
            }
            String templateName = entityobj.getChangeItem().getItemName() + "_交换模板";// 文件名称格式 用指标名称命名
            String saveFileDoc = "/" + templateName + "." + doc;// 文件最终名称
            File file = new File(path + saveFileDoc);// 创建文件对象
            FileUtil.makeMulu(path);// 创建目录
            FileUtil.copy(upload, file);// 上传文件
            if (!StringUtils.isBlank(uploadFileName)) {
                entityobj.setTemplateName(templateName + "." + doc);// 模板名称
            }
            entityobj.setUploadState("1");// 模板状态
            entityobj.setState("1");//模板启用
            entityobj.setTemplatePath(path + saveFileDoc);// 模板地址
            SysUser sysUser = (SysUser) getFromSession(Costant.SESSION_USER);
            if (sysUser == null) {
                this.errorInfo = "会话过期,请重新登录!";
                log.error(this.errorInfo);
                return StrutsAction.ERROR;
            }
            entityobj.setCreator(sysUser.getUserName());// 创建人
            getEntityService().saveOrUpdate(entityobj);// 修改模板对象
        } catch (ServiceException e) {
            this.errorInfo = "上传指标模板失败!";
            log.error(this.errorInfo, e);
            return StrutsAction.ERROR;
        }

        return StrutsAction.RELOAD;
    }
    /**
     * 修改模板状态
     * @return
     * @author zhangx
     */
    public String updateStatus() {
        try {
            String status = getStringParameter("status");// 得到状态
            if (!StringUtils.isBlank(status)) {
                changeItemTemplateService.updateStatus(ids, status);// 设置模板状态
            }
        } catch (ServiceException e) {
            this.errorInfo = "修改模板状态异常!";
            log.error(this.errorInfo, e);
            return StrutsAction.ERROR;
        }
        return "listAction";
    }
    /**
     * 下载模板
     * @author zhangx
     */
    public void downTemplate() {
        try {
            entityobj = changeItemTemplateService.findById(id);
            if (entityobj != null) {
                ServletOutputStream out = this.getHttpServletResponse().getOutputStream();
                String dowName = new String(entityobj.getTemplateName().getBytes("gb2312"), "iso8859-1");
                this.getHttpServletResponse().setContentType("APPLICATION/OCTET-STREAM");
                this.getHttpServletResponse().setHeader("Content-Disposition", "attachment; filename=" + dowName);
                // 得到路径变量
                FileInputStream fileInputStream = new FileInputStream(entityobj.getTemplatePath());
                if (fileInputStream != null) {
                    byte[] by = new byte[2048];
                    while ((fileInputStream.read(by) != -1)) {
                        out.write(by);
                    }
                    fileInputStream.close();
                    out.close();
                    int downCount = 0;
                    if (entityobj.getDownCount() != null) {
                        downCount = entityobj.getDownCount();
                    }
                    downCount++;
                    entityobj.setDownCount(downCount);// 下载次数
                    getEntityService().saveOrUpdate(entityobj);// 修改当前模板对象
                }
            }
        } catch (Exception e) {
            this.errorInfo = "下载失败,请联系管理员!";
        }
    }
    @Override
    protected IBaseServices<ChangeItemTemplate> getEntityService() {
        return changeItemTemplateService;
    }

    // //// SERVICE
    @Autowired
    public void setChangeItemTemplateService(IChangeItemTemplateService changeItemTemplateService) {
        this.changeItemTemplateService = changeItemTemplateService;
    }

    @Autowired
    public void setSysUserDeptService(ISysUserDeptService sysUserDeptService) {
        this.sysUserDeptService = sysUserDeptService;
    }

    @Autowired
    public void setSysDeptService(ISysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
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

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
