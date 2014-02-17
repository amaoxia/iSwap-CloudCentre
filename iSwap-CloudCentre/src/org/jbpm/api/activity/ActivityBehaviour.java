package org.jbpm.api.activity;

import java.io.Serializable;

import com.ligitalsoft.workflow.exception.NodeException;
/**
 * Custom节点，要执行需要实现的接口，在Custom节点中执行行为
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-4 下午10:10:19
 *@Team 研发中心
 */
public interface ActivityBehaviour extends Serializable {
  
  /**
   * Custom节点操作
   * @param execution
   * @throws Exception   
   *void  
   * @exception   
   * @since  1.0.0
   */
  public  void execute(ActivityExecution execution) throws NodeException;
}
