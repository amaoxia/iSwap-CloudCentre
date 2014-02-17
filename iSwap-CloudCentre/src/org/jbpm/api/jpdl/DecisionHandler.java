package org.jbpm.api.jpdl;

import java.io.Serializable;

import org.jbpm.api.model.OpenExecution;

import com.ligitalsoft.workflow.exception.NodeException;


/**
 * 该节点用于判断
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-8 下午02:19:16
 *@Team 研发中心
 */
public interface DecisionHandler extends Serializable {

  /**
   * the name of the selected outgoing transition
   * @param execution
   * @return
   */
  public String decide(OpenExecution execution) throws NodeException;
}
