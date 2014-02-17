
package org.jbpm.pvm.internal.client;

import org.jbpm.api.model.OpenProcessInstance;


/**
 * 开始流程
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 下午12:09:41
 *@Team 研发中心
 */
public interface ClientProcessInstance extends ClientExecution, OpenProcessInstance {

  // start ////////////////////////////////////////////////////////////////////

  /** starts this process instance */
  public  void start();

}
