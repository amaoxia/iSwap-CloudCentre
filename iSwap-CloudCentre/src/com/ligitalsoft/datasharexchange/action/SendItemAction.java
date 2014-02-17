package com.ligitalsoft.datasharexchange.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.dao.Constants;
import com.common.framework.dao.QueryPara;
import com.common.framework.dao.SortPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.datasharexchange.service.IChangeItemService;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.ChangeItemCycle;

@Scope("prototype")
@Namespace("/exchange/sendItem")
@Results({
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
		@Result(name = "listAction", location = "sendItem!list.action", type = "redirectAction", params = {
				"deptId", "${deptId}","deptName", "${deptName}"}) })
@Action("sendItem")
public class SendItemAction extends FreemarkerBaseAction<ChangeItem> {

	private static final long serialVersionUID = 4291635193286682099L;
	private IChangeItemService changeItemService;
	private Long deptId;
	private String deptName;
	private ChangeItem changeItem;
	private ChangeItemCycle cycle;// 指标交换规则

    @Override
    protected void onBeforeList() {
		if(getDeptId()!=null){
			QueryPara queryPara = new QueryPara();
            queryPara.setName("e.sysDept.id");
            queryPara.setOp(Constants.OP_EQ_VALUE);
            queryPara.setType(Constants.TYPE_LONG);
            queryPara.setValue(getDeptId()+"");
            queryParas.add(queryPara);
		}
		QueryPara queryPara = new QueryPara();
        queryPara.setName("e.itemType");
        queryPara.setOp(Constants.OP_EQ_VALUE);
        queryPara.setType(Constants.TYPE_INTEGER);
        queryPara.setValue("1");//发送指标
        queryParas.add(queryPara);
        
        SortPara para = new SortPara();
		para.setOrder("asc");
		para.setProperty("e.itemName");
		sortParas.add(para);// 初始以主键指标项名称排序
    }
    
    public String exchangeItemConf() throws ServiceException {
    	setChangeItem(changeItemService.findById(id));
		return "tableConf";
	}
    

	@Override
    protected void onBeforeAdd() {
    	setRule();
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
                changeItemService.save(entityobj, null, cycle);
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
     * 修改保存具体的实体
     * @date 2010-12-8 下午01:25:54
     * @return
     */
    @SuppressWarnings("static-access")
    public String update() {
        try {
            if (validData(entityobj)) {
                this.onBeforeUpdate();
                setRule();
                changeItemService.update(entityobj, null, cycle);
                this.onAfterUpdate();
            }
            return RELOAD;
        } catch (Exception e) {
            this.errorInfo = "修改数据失败，请稍候再试!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
	
    public void setRule() {
        if (!StringUtils.isBlank(cycle.getExchangeCycleValue())) {
            StringBuffer exchangeDateRule = new StringBuffer();
            if (cycle.getExchangeCycleValue().equals("0")) {// 周交换
                String w_sel = getStringParameter("w_sel");
                String w_day = getStringParameter("w_day");
                exchangeDateRule.append(w_sel);
                exchangeDateRule.append(",");
                exchangeDateRule.append(w_day);
            } else if (cycle.getExchangeCycleValue().equals("1")) {// 月交换
                String m_sel = getStringParameter("m_sel");
                String m_day = getStringParameter("m_day");
                exchangeDateRule.append(m_sel);
                exchangeDateRule.append(",");
                exchangeDateRule.append(m_day);
            } else if (cycle.getExchangeCycleValue().equals("2")) {// 季交换
                String j_sel = getStringParameter("j_sel");
                String j_month = getStringParameter("j_month");
                String j_day = getStringParameter("j_day");
                exchangeDateRule.append(j_sel);
                exchangeDateRule.append(",");
                exchangeDateRule.append(j_month);
                exchangeDateRule.append(",");
                exchangeDateRule.append(j_day);
            } else if (cycle.getExchangeCycleValue().equals("3")) {// 交换
                String y_sel = getStringParameter("y_sel");
                String y_month = getStringParameter("y_month");
                String y_day = getStringParameter("y_day");
                exchangeDateRule.append(y_sel);
                exchangeDateRule.append(",");
                exchangeDateRule.append(y_month);
                exchangeDateRule.append(",");
                exchangeDateRule.append(y_day);
            }
            cycle.setExchangeDateRule(exchangeDateRule.toString());
        }
        if (!StringUtils.isBlank(cycle.getUseDefaultRule()) && cycle.getUseDefaultRule().equals("0")) {// 不采用系统默认规则
            String greenNotify = getStringParameter("greenNotify");
            String greenNotifyDay = getStringParameter("greenNotifyDay");
            String yellowNotify = getStringParameter("yellowNotify");
            String yellowNotifyDay = getStringParameter("yellowNotifyDay");
            String redNotify = getStringParameter("redNotify");
            String redNotifyDay = getStringParameter("redNotifyDay");
            cycle.setRuleGreenNotify(greenNotify + "," + greenNotifyDay);
            cycle.setRuleYellowNotify(yellowNotify + "," + yellowNotifyDay);
            cycle.setRuleRedNotify(redNotify + "," + redNotifyDay);
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

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public ChangeItemCycle getCycle() {
		return cycle;
	}

	public void setCycle(ChangeItemCycle cycle) {
		this.cycle = cycle;
	}

	public ChangeItem getChangeItem() {
		return changeItem;
	}

	public void setChangeItem(ChangeItem changeItem) {
		this.changeItem = changeItem;
	}
}
