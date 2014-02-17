package com.ligitalsoft.iswapmq.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.iswapmq.service.IMQService;
import com.ligitalsoft.model.serverinput.JmsServerInfo;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.service.ISysDeptService;

@Namespace("/iswapmq/external/jms")
@Results({ @Result(name = StrutsAction.RELOAD, location = "jmsInfoAction!list.action", type = "redirect"),
        @Result(name = "saveMQResult", location = "../../../common/succ.ftl", type = "freemarker") })
@Action("jmsInfoAction")
public class JmsServiceAction extends FreemarkerBaseAction<JmsServerInfo> {

    private static final long serialVersionUID = -7624219775565273471L;

    @Autowired
    private ISysDeptService   sysDeptService;

    private List<SysDept>     listDepts        = new ArrayList<SysDept>();

    @Autowired
    private IMQService        mqService;

    @SuppressWarnings("static-access")
    @Override
    public String list() {
        try {
            this.onBeforeList();
            this.setQueryAndsort();// 设置查询条件
            // 分页查询
            this.listDatas = this.getAllObjectBypage();
            this.listDepts = sysDeptService.findAll();
            this.onAfterList();
            return this.LIST;
        }
        catch (Exception e) {
            this.errorInfo = "系统繁忙，浏览信息失败，请稍候再试！";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }

    /*
     * 查出所有部门
     * @see com.common.framework.view.FreemarkerBaseAction#addView()
     */
    @Override
    public String addView() {

        listDepts = sysDeptService.findAll();
        return StrutsAction.ADDVIEW;

    }
    @Override
    protected void onBeforeView() {
    	listDepts = sysDeptService.findAll();
    	super.onBeforeView();
    }

    @SuppressWarnings("static-access")
    @Override
    public String add() {
        try {
            if (validData(entityobj)) {// 验证业务逻辑数据
                this.onBeforeAdd();
                getEntityService().insert(entityobj);
                this.onAfterAdd();
            }
            return "saveMQResult";
        }
        catch (Exception e) {
            this.errorInfo = "添加数据失败，请稍候再试!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
    /**
     * 修改保存具体的实体
     * @author huwanshan
     * @date 2010-12-8 下午01:25:54
     * @return
     */
    @SuppressWarnings("static-access")
    public String update() {
        try {
            if (validData(entityobj)) {
                this.onBeforeUpdate();
                this.getEntityService().update(entityobj);
                this.onAfterUpdate();
            }
            return "saveMQResult";
        } catch (Exception e) {
            this.errorInfo = "修改数据失败，请稍候再试!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
    
    @SuppressWarnings("static-access")
    @Override
    public String updateView() {
        try {
            entityobj = this.mqService.findById(id);
            this.listDepts = sysDeptService.findAll();
        }
        catch (ServiceException e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return this.UPDATEVIEW;
    }

    /**
     * 用户登录 检查登录名称是否存在
     * @return
     * @author
     * @2010-12-30 下午08:16:30
     * @throws Exception 
     */
    public String checkName() throws Exception {
        String jmsServerName = getHttpServletRequest().getParameter("jmsServerName").trim();
        String id = getHttpServletRequest().getParameter("id");
        String result = "";
        //getHttpServletResponse().setCharacterEncoding("GBK");
        try {
            //JmsServerInfo info = mqService.findUniqueByProperty("jmsServerName", jmsServerName);
            JmsServerInfo info = mqService.findUniqueByProperty("jmsServerName", new String(jmsServerName.getBytes("ISO-8859-1"),"UTF-8"));
            if (info == null) {
                result = "succ";
            }
            else {
                if (!StringUtils.isBlank(id)) {
                    if (info.getId().toString().equals(id)) {
                        result = "succ";
                    }
                }
            }
            Struts2Utils.renderText(result, "encoding:GBK");
        }
        catch (ServiceException e) {
            log.error("ftpService invoke exception ", e);
        }
        return null;
    }
    /**
     * 删除
     */
    @Override
    @SuppressWarnings("static-access")
    public String delete() {
        try {
            this.onBeforeDelete();
            this.getEntityService().deleteAllByIds(ids);
            this.onAfterDelete();
            return StrutsAction.RELOAD;
        } catch (Exception e) {
            this.errorInfo = "删除数据失败!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
    public void prepareTestJMS() throws Exception {
		prepareModel();
	}
    /**
     * 测试JMS消息
     * @author fangbin
     * @return
     */
    public String testJMS(){
    	JmsServerInfo jmsserver=new JmsServerInfo();
    	try {
			jmsserver=mqService.findById(entityobj.getId());
			boolean flag=mqService.testJMS(jmsserver);
			Struts2Utils.renderText(flag+"", "encoding:GBK");
		} catch (ServiceException e) {
			e.printStackTrace();
			log.error("连接测试失败!",e);
		}
    	
    	return null;
    }
    @Override
    protected IBaseServices<JmsServerInfo> getEntityService() {
        return mqService;
    }

    public ISysDeptService getSysDeptService() {
        return sysDeptService;
    }

    public void setSysDeptService(ISysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    public List<SysDept> getListDepts() {
        return listDepts;
    }

    public void setListDepts(List<SysDept> listDepts) {
        this.listDepts = listDepts;
    }

    public IMQService getJmsservice() {
        return mqService;
    }

    public void setJmsservice(IMQService jmsservice) {
        this.mqService = jmsservice;
    }

}
