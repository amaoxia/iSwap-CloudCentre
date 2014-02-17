package org.jbpm.api;

import java.util.Map;
import java.util.Set;


/**
 * manages runtime process executions
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-4 下午10:21:14
 *@Team 研发中心
 */
public interface ExecutionService {

  /** starts a new process instance for the ProcessDefinition with the given processDefinitionDbid.
   * @param processDefinitionId the {@link ProcessDefinition#getId() unique id} of the process definition. */
  public ProcessInstance startProcessInstanceById(String processDefinitionId);

  /** starts a new process instance for the ProcessDefinition with the given processDefinitionDbid. 
   * @param processDefinitionId the {@link ProcessDefinition#getId() unique id} of the process definition.
   * @param processInstanceKey is a user provided reference for the new process instance that must be unique over all 
   *    process definition versions with the same name. */
  public ProcessInstance startProcessInstanceById(String processDefinitionId, String processInstanceKey);

  /** starts a new process instance for the ProcessDefinition with the given processDefinitionDbid. 
   * @param processDefinitionId the {@link ProcessDefinition#getId() unique id} of the process definition.
   * @param variables are the initial values of the process variables that will be set before the execution starts. */
  public ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, ?> variables);

  /** starts a new process instance for the ProcessDefinition with the given processDefinitionDbid.
   * @param processDefinitionId the {@link ProcessDefinition#getId() unique id} of the process definition.
   * @param variables are the initial values of the process variables that will be set before the execution starts.
   * @param processInstanceKey is a user provided reference for the new process instance that must be unique over all 
   *    process versions with the same name. */
  public ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, ?> variables, String processInstanceKey);

  /** starts a new process instance in the latest version of the given process definition.
   * @param processDefinitionKey is the key of the process definition for which the latest version will be taken. */
  public  ProcessInstance startProcessInstanceByKey(String processDefinitionKey);

  /** starts a new process instance in the latest version of the given processDefinitionName.
   * @param processDefinitionKey is the key of the process definition
   *   for which the latest version will be taken.
   * @param processInstanceKey is a user provided reference for the new process instance
   *   that must be unique over all process versions with the same name. */
  public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String processInstanceKey);

  /** starts a new process instance in the latest version of the given processDefinitionName.
   * @param processDefinitionKey is the key of the process definition
   *   for which the latest version will be taken.
   * @param variables are the initial values of the process variables that
   *   will be set before the execution starts (read: before the initial
   *   activity is executed). */
  public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, ?> variables);

  /** starts a new process instance in the latest version of the given processDefinitionName.
   * @param processDefinitionKey is the key of the process definition for which the latest version will be taken.
   * @param variables are the initial values of the process variables that will be set before the execution starts.
   * @param processInstanceKey is a user provided reference for the new execution that must be unique over all
   *    process versions with the same name. */
  public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, ?> variables, String processInstanceKey);

  /** the path of execution that is uniquely defined by the execution id. */
  public Execution findExecutionById(String executionId);

  /** the process instance that is uniquely defined by the process execution id. */
  public ProcessInstance findProcessInstanceById(String processInstanceId);

  /** provides an external trigger to an execution. */
  public ProcessInstance signalExecutionById(String executionId);

  /** provides a named external trigger to an execution. */
  public ProcessInstance signalExecutionById(String executionId, String signalName);

  /** provides a named external trigger to an execution with parameters. */
  public ProcessInstance signalExecutionById(String executionId, String signalName, Map<String, ?> parameters);

  /** provides a external trigger to an execution with parameters. */
  public ProcessInstance signalExecutionById(String executionId, Map<String, ?> parameters);


  /** search for process instances with criteria.
   * be aware that this query only sees ongoing process instances.
   * refer to {@link HistoryService#createHistoryTaskQuery()} for
   * queries that include finished process instances. */
  public ProcessInstanceQuery createProcessInstanceQuery();

  /** creates or overwrites a variable value in the referenced execution */
  public void setVariable(String executionId, String name, Object value);

  /** creates or overwrites variable values in the referenced execution */
  public  void setVariables(String executionId, Map<String, ?> variables);
  
  /** creates a variable value in the referenced execution. optionally enables variable history tracking. */
  public void createVariable(String executionId, String name, Object value, boolean historyEnabled);
  
  /** creates variable values in the referenced execution. optionally enables variable history tracking. */
  public void createVariables(String executionId, Map<String, ?> variables, boolean historyEnabled);

  /** retrieves a variable */
  public Object getVariable(String executionId, String variableName);

  /** all the variables visible in the given execution scope */
  public Set<String> getVariableNames(String executionId);

  /** retrieves a map of variables */
  public Map<String, Object> getVariables(String executionId, Set<String> variableNames);
  
  /** end a process instance */
  public void endProcessInstance(String processInstanceId, String state);

  /** delete a process instance.  The history information will still be in the database. 
   * @throws JbpmException if the given processInstanceId doesn't exist*/
  public void deleteProcessInstance(String processInstanceId);

  /** delete a process instance, including the history information.
   *  @throws JbpmException if the given processInstanceId doesn't exist */
  public void deleteProcessInstanceCascade(String processInstanceId);
}
