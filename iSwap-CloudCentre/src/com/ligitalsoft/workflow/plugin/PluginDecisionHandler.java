package com.ligitalsoft.workflow.plugin;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.internal.log.Jdk14LogFactory;
import org.jbpm.internal.log.Log;

import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.exception.NodeException;

/**
 * 用于做判断时候使用
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-8 下午02:27:25
 *@Team 研发中心
 */
public abstract class PluginDecisionHandler implements DecisionHandler {
	private static final long serialVersionUID = 7864048523823387387L;
	static {
	    Jdk14LogFactory.initializeJdk14Logging(); 
	  }

    protected final Log log = Log.getLog(getClass().getName());
    
	@Override
	public String decide(OpenExecution execution) throws NodeException {
		 String toTarget = this.doexecute(execution);
		return toTarget;
	}

	public abstract String doexecute(OpenExecution context) throws ActionException;
}












