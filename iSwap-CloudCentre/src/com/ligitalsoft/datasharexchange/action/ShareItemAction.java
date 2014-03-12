package com.ligitalsoft.datasharexchange.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.dao.Constants;
import com.common.framework.dao.QueryPara;
import com.common.framework.dao.SortPara;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.ligitalsoft.datasharexchange.service.IChangeItemService;
import com.ligitalsoft.model.changemanage.ChangeItem;

@Scope("prototype")
@Namespace("/exchange/shareItem")
@Results({
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
		@Result(name = "listAction", location = "shareItem!list.action", type = "redirectAction", params = {
				"deptId", "${deptId}","deptName", "${deptName}"}) })
@Action("shareItem")
public class ShareItemAction extends FreemarkerBaseAction<ChangeItem> {

	private static final long serialVersionUID = -7093196059120131833L;
	private IChangeItemService changeItemService;
	private String deptId;//部门id
	private String deptName;// 部门名称

	@Override
    protected void onBeforeList() {
		if(getDeptId()!=null){
			QueryPara queryPara = new QueryPara();
            queryPara.setName("e.sysDept.id");
            queryPara.setOp(Constants.OP_EQ_VALUE);
            queryPara.setType(Constants.TYPE_LONG);
            queryPara.setValue(getDeptId());
            queryParas.add(queryPara);
		}
		QueryPara queryPara = new QueryPara();
        queryPara.setName("e.itemType");
        queryPara.setOp(Constants.OP_EQ_VALUE);
        queryPara.setType(Constants.TYPE_INTEGER);
        queryPara.setValue("3");
        queryParas.add(queryPara);
        
        SortPara para = new SortPara();
		para.setOrder("asc");
		para.setProperty("e.itemName");
		sortParas.add(para);// 初始以主键指标项名称排序
    }
	
	@Override
    protected void onBeforeAdd() {
    }
	
	/**
     * 删除指标
     */
    @Override
    @SuppressWarnings("static-access")
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
     * 保存具体的实体
     * @date 2010-12-8 下午01:25:54
     * @return
     */
    @Override
    @SuppressWarnings("static-access")
    public String add() {
        try {
            if (validData(entityobj)) {// 验证业务逻辑数据
                this.onBeforeAdd();
                changeItemService.save(entityobj, null, null);
                this.onAfterAdd();
            }
            return RELOAD;
        } catch (Exception e) {
            this.errorInfo = "添加数据失败，请稍候再试!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
    
    /**
     * 修改视图之前
     */
    @Override
    protected void onBeforeUpdateView() {
    }
    
    @Override
    protected void onBeforeView() {
    }
    
    /**
     * 修改保存具体的实体
     * @date 2010-12-8 下午01:25:54
     * @return
     */
    @SuppressWarnings("static-access")
    public String update() {
        try {
            if (validData(entityobj)) {
                this.onBeforeUpdate();
                changeItemService.update(entityobj, null, null);
                this.onAfterUpdate();
            }
            return RELOAD;
        } catch (Exception e) {
            this.errorInfo = "修改数据失败，请稍候再试!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
	
	@Override
	protected IBaseServices<ChangeItem> getEntityService() {
		return changeItemService;
	}

	@Autowired
	public void setChangeItemService(IChangeItemService changeItemService) {
		this.changeItemService = changeItemService;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
