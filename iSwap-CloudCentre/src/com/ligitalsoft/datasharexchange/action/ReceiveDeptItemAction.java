package com.ligitalsoft.datasharexchange.action;


import net.sf.json.JSONArray;

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
import com.common.utils.common.StringUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.datasharexchange.service.ISendItemDeptService;
import com.ligitalsoft.model.changemanage.SendItemDept;

@Scope("prototype")
@Namespace("/exchange/receiveDeptItem")
@Results({
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
		@Result(name = "listAction", location = "receiveDeptItem!list.action", type = "redirectAction", params = {
				"deptid", "${deptid}","deptName", "${deptName}"}) })
@Action("receiveDeptItem")
// @InterceptorRefs({
// // @InterceptorRef("annotationWorkflow"), //excludeMethods
// @InterceptorRef(value = "token", params = { "includeMethods", "other" }),
// @InterceptorRef("defaultStack") })
public class ReceiveDeptItemAction extends FreemarkerBaseAction<SendItemDept> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7093196059120131833L;
	private ISendItemDeptService sendItemDeptService;
	/**
	 *
	 */
	private String tids;
	private String deptid;//部门id
	private String deptName;// 部门名称

	@Override
	public String add() {
		/*if (!StringUtils.isBlank(tids)) {
			sendItemDeptService.add(tids);
		}
		return StrutsAction.RELOAD;
		*/
		return "listAction";
	}

	@Override
	protected void onBeforeList() {
		 if(!StringUtils.isBlank(getDeptid())){
			 QueryPara queryPara = new QueryPara();
           queryPara.setName("e.sysDept.id");
           queryPara.setOp(Constants.OP_EQ_VALUE);
           queryPara.setType(Constants.TYPE_LONG);
           queryPara.setValue(getStringParameter("deptid"));
           queryParas.add(queryPara);
		}
		SortPara para = new SortPara();
		para.setOrder("asc");
		para.setProperty("e.changeItem.itemName");
		sortParas.add(para);// 初始以主键指标项名称排序
	}
	
	/*public String list() {
		List<ChangeItem> changeItemList = new ArrayList<ChangeItem>();
		if(!StringUtils.isBlank(getDeptid())) {
			
			SendItemDept sendItemDept = new SendItemDept();
			SysDept sysDept = new SysDept();
			sysDept.setId(new Long(getDeptid()));
			sendItemDept.setSysDept(sysDept);
			
			changeItemList = sendItemDeptService.getChangeItemListById(sendItemDept, page);
		}
		
		getHttpServletRequest().setAttribute("items", changeItemList);
		
		return LIST;
	}*/

	/**
	 * 删除实体数据
	 * 
	 * @author huwanshan
	 * @date 2010-12-8 下午01:34:01
	 * @return
	 */
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
	 * depExcludeTree
	 * 
	 * @return
	 */
	public String deptExcludeTree() {
		JSONArray deptTree = sendItemDeptService.deptExcludeTree();
		Struts2Utils.renderJson(deptTree, "encoding:GBK");
		return null;
	}

	@Override
	protected IBaseServices<SendItemDept> getEntityService() {
		return sendItemDeptService;
	}

	@Autowired
	public void setSendItemDeptService(ISendItemDeptService sendItemDeptService) {
		this.sendItemDeptService = sendItemDeptService;
	}

	public String getTids() {
		return tids;
	}

	public void setTids(String tids) {
		this.tids = tids;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
