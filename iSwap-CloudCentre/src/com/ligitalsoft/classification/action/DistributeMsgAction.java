package com.ligitalsoft.classification.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.ligitalsoft.classification.service.IDistributeMsgService;
import com.ligitalsoft.datasharexchange.service.IChangeItemService;
import com.ligitalsoft.model.classification.DistributeMsg;

@Namespace("/rule/distributemsg")
@Action("distributemsg")
@Scope("prototype")
@Results({
		@Result(name = "listAction", location = "distributemsg!list.action", type = "redirectAction"),
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker") })
public class DistributeMsgAction extends FreemarkerBaseAction<DistributeMsg> {

	/**
	 * 
	 */
	private String name;	
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private IDistributeMsgService distributeMsgService;
	@Autowired
	private IChangeItemService changeItemService;

	@Override
	protected IBaseServices<DistributeMsg> getEntityService() {

		return distributeMsgService;
	}
}
