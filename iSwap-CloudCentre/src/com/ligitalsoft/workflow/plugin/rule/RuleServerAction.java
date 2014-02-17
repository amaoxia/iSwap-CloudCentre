package com.ligitalsoft.workflow.plugin.rule;

import org.jbpm.api.activity.ActivityExecution;

import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;

public class RuleServerAction extends PluginActionHandler {
	private static final long serialVersionUID = 252064412412668172L;
	public String ruleObject;//规则对象
	public String ruleText;//手动输入规则
	public String dataList_inputVar;//输入的数据
	public String outData_outVar;//输出数据
	
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		// TODO Auto-generated method stub

	}

}
