package org.jbpm.pvm.internal.builder;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.internal.log.Log;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;

/**
 * 流程的运行管理
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 上午11:34:33
 *@Team 研发中心
 */
public class ProcessDefinitionBuilder extends CompositeBuilder {
  
  private static final Log log = Log.getLog(ProcessDefinitionBuilder.class.getName());
  
  protected ProcessDefinitionImpl processDefinition;
  protected List<UnresolvedFlow> unresolvedFlows = new ArrayList<UnresolvedFlow>();

  protected ProcessDefinitionBuilder(ProcessDefinitionImpl processDefinition) {
    this.processDefinition = processDefinition;
    this.compositeElement = processDefinition;
  }

  /**
   * 开始运行流程
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:35:10
   */
  public static ProcessDefinitionBuilder startProcess() {
    return startProcess(null);
  }

  /**
   * 开始运行流程
   * @param processDefinitionName
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:35:42
   */
  public static ProcessDefinitionBuilder startProcess(String processDefinitionName) {
    return startProcess(processDefinitionName, new ProcessDefinitionImpl());
  }

  /**
   *开始运行流程
   * @param processDefinitionName
   * @param processDefinition
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:36:02
   */
  public static ProcessDefinitionBuilder startProcess(String processDefinitionName, ProcessDefinitionImpl processDefinition) {
    processDefinition.setName(processDefinitionName);
    return new ProcessDefinitionBuilder(processDefinition);
  }
  
  /**
   * 结束流程
   */
  public ProcessDefinitionImpl endProcess() {
    verifyInitial();
    resolveFlows();
    return processDefinition;
  }

  protected void verifyInitial() {
    if (processDefinition.getInitial()==null) {
      errorNoInitial();
    }
  }
  /**
   * 流程的转向
   * @author  hudaowan
   * @date 2011-8-13 上午11:36:53
   */
  protected void resolveFlows() {
    for (UnresolvedFlow unresolvedFlow: unresolvedFlows) {
      ActivityImpl destination = processDefinition.findActivity(unresolvedFlow.destinationName);
      if (destination==null) {
        errorUnexistingFlowDestination(unresolvedFlow);
      }
      destination.addIncomingTransition(unresolvedFlow.transition);
    }
  }

  public ProcessDefinitionBuilder key(String key) {
    processDefinition.setKey(key);
    return this;
  }

  public ProcessDefinitionBuilder version(int version) {
    processDefinition.setVersion(version);
    return this;
  }

  public ProcessDefinitionBuilder description(String description) {
    processDefinition.setDescription(description);
    return this;
  }

  protected void addUnresolvedFlow(UnresolvedFlow unresolvedFlow) {
    unresolvedFlows.add(unresolvedFlow);
  }

  protected void setProcessDefinitionInitial(ActivityImpl initial) {
    if (processDefinition.getInitial()!=null) {
      errorMultipleInitials(initial);
    }
    processDefinition.setInitial(initial);
  }

  protected void errorMultipleInitials(ActivityImpl initial) {
    log.error("multiple initial activities: "+processDefinition.getInitial()+" and "+initial);
  }

  protected void errorNoInitial() {
    log.error("no initial activity");
  }

  protected void errorUnexistingFlowDestination(UnresolvedFlow unresolvedFlow) {
    String sourceActivityName = unresolvedFlow.transition.getSource().getName();
    log.error("unexisting transition destination: "+sourceActivityName+"-->"+unresolvedFlow.destinationName);
  }
}
