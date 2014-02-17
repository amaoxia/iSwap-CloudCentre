package com.ligitalsoft.workflow.plugin.basenode;

import org.jbpm.api.activity.ActivityExecution;

import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;

/**
 * 发送邮件
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-11-1下午06:52:22
 *@Team 研发中心
 */
public class SendMailAction  extends PluginActionHandler {
	private static final long serialVersionUID = 19513750987178179L;

	public String data_inputVar;
	public String receiving;
	public String subjectName;
	public String text;
	
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		
	}
	

}
