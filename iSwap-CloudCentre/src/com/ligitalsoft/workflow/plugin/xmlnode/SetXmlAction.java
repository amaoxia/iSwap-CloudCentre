package com.ligitalsoft.workflow.plugin.xmlnode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.jbpm.api.activity.ActivityExecution;

import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;

/**
 * 用于设置XML的产量
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-11-27上午11:55:49
 *@Team 研发中心
 */
public class SetXmlAction  extends PluginActionHandler{
	private static final long serialVersionUID = 3868144225404044943L;

	public String msg;
	public String xml_outVar;
	
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		try{
			context.setVariable(xml_outVar,msg);
		}catch(Exception e){
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bo));
			log.error("设置XML的初始值节点失败!",e);
			throw new ActionException(e);
		}
	}
}
