package org.jbpm.pvm.internal.client;

import org.jbpm.api.Execution;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.pvm.internal.model.OpenProcessDefinition;


/**
 * 建立流程实例
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 下午12:07:59
 *@Team 研发中心
 */
public interface ClientProcessDefinition extends OpenProcessDefinition {

  /** 
   * 创建流程实例
   * creates a new process instances. The returned process instance 
   * is not started yet.  This way, 
   * {@link OpenExecution#setVariable(String, Object) variables can be set} 
   * before execution is started.  Invoke {@link ClientProcessInstance#start()} 
   * to start execution of the process. 
   */
  public ClientProcessInstance createProcessInstance();
  
  /** 
   * 创建流程实例
   * creates a new process instances with a given key. The returned process instance 
   * is not started yet.  This way, 
   * {@link OpenExecution#setVariable(String, Object) variables can be set} 
   * before execution is started.  Invoke {@link ClientProcessInstance#start()} 
   * to start execution of the process. 
   * @param key is a user provided reference that uniquely identifies this 
   * process instance in the scope of the process name. 
   */
   public ClientProcessInstance createProcessInstance(String key);
  
  /**
   * 创建流程实例 
   * creates a new process instances with a given key. The returned process instance 
   * is not started yet.  This way, 
   * {@link OpenExecution#setVariable(String, Object) variables can be set} 
   * before execution is started.  Invoke {@link ClientProcessInstance#start()} 
   * to start execution of the process. 
   * @param key is a user provided reference that uniquely identifies this 
   * process instance in the scope of the process name. key is allowed to be null.
   */
   public ClientProcessInstance createProcessInstance(String key, Execution superProcessExecution);

  /** 
   * 开始流程实例
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 下午12:11:43
   */
   public ClientProcessInstance startProcessInstance();
  
  /**
   *  creates the process instance with the given key and immediately start its 
   * execution.
   * @param key is a user provided reference that uniquely identifies this 
   * process instance in the scope of the process name. 
   */
   public ClientExecution startProcessInstance(String key);
}
