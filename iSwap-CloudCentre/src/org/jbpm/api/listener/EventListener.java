package org.jbpm.api.listener;

import java.io.Serializable;

import com.ligitalsoft.workflow.exception.NodeException;


/** 
 * listener to process execution events.
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-8 下午02:23:04
 *@Team 研发中心
 */
public interface EventListener extends Serializable {
  
  /**
   *  is invoked when an execution crosses the event on which this listener is registered
   * @param execution
   * @throws Exception
   */
  public void notify(EventListenerExecution execution) throws NodeException;

}
