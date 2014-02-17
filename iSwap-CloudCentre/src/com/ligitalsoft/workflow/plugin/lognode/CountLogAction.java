package com.ligitalsoft.workflow.plugin.lognode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.hsqldb.lib.StringUtil;
import org.jbpm.api.activity.ActivityExecution;

import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;

/**
 * 计算日志的总数
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-12-13上午10:19:51
 *@Team 研发中心
 */
public class CountLogAction extends PluginActionHandler {

	private static final long serialVersionUID = -933068061884354846L;

	public String data_inputVar;  //输入参数
	public String data_outVar;    // 输出参数
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		log.info("开始数据累加.....");
		try {
			int count = 0;
			String input_data  =String.valueOf(context.getVariable(data_inputVar));
			String output_data  =(String)context.getVariable(data_outVar);
			if(!StringUtil.isEmpty(input_data)){
				 count = Integer.valueOf(input_data);
				if(!StringUtil.isEmpty(output_data)){
					count = count + Integer.valueOf(output_data);
				}
			}
			System.out.println("-------CountLongAction-----------------------"+count+"-----------");
			context.setVariable(data_outVar, String.valueOf(count));
			log.info("完成数据累加.....");
		} catch (Exception e) {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bo));
			log.error("统计日志总数节点出错!", e);
			throw new ActionException(e);
		}
	}

}
