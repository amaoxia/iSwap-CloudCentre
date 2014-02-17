package org.jbpm.pvm.internal.builder;

import org.jbpm.api.JbpmException;
import org.jbpm.api.activity.ActivityBehaviour;
import org.jbpm.pvm.internal.model.CompositeElementImpl;
import org.jbpm.pvm.internal.model.EventImpl;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.wire.Descriptor;
import org.jbpm.pvm.internal.wire.descriptor.ObjectDescriptor;
import org.jbpm.pvm.internal.wire.descriptor.StringDescriptor;

/**
 * 流程节点处理
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-12 上午09:36:08
 *@Team 研发中心
 */
public abstract class CompositeBuilder {

  protected CompositeElementImpl compositeElement;
  
  public abstract ProcessDefinitionImpl endProcess();
  protected abstract void addUnresolvedFlow(UnresolvedFlow unresolvedFlow);
  protected abstract void setProcessDefinitionInitial(ActivityImpl initial);

  /**
   * 创建一个新的活动
   * @return 
   * @author  2011-8-13 上午11:04:13
   * @author  hudaowan
   */
  protected ActivityImpl createActivity() {
    return compositeElement.createActivity();
  }

  /**
   * 创建一个新的事件
   * @param eventName
   * @return 
   * @author  2011-8-13 上午11:05:33
   * @author  hudaowan
   */
  public EventImpl createEvent(String eventName) {
    return compositeElement.createEvent(eventName);
  }
  
  /**
   * 流程的变量
   * @param name
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:14:33
   */
  public CompositeBuilder variable(String name) {
    return startVariable(name)
      .endVariable();
  }
  
  /**
   * 流程的变量
   * @param name
   * @param type
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:14:18
   */
  public CompositeBuilder variable(String name, String type) {
    return startVariable(name)
      .type(type)
      .endVariable();
  }
  
  /**
   * 开始定时器
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:14:07
   */
  public TimerBuilder startTimer() {
    return new TimerBuilder(this, null);
  }

  /**
   * 开始定时器
   * @param eventName
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:13:47
   */
  public TimerBuilder startTimer(String eventName) {
    return new TimerBuilder(this, eventName);
  }
  
  /**
   * 开始流程变量
   * @param name
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:13:30
   */
  public VariableBuilder startVariable(String name) {
    return new VariableBuilder(this)
      .name(name);
  }

  /**
   * 开始活动  
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:13:11
   */
  public ActivityBuilder startActivity() {
    return startActivity((String)null);
  }

  /**
   * 根据开始活动的名字，开始活动
   * @param activityName
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:15:34
   */
  public ActivityBuilder startActivity(String activityName) {
    return new ActivityBuilder(this, activityName);
  }

  /**
   * 开始活动
   * @param activityDescriptor
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:16:29
   */
  public ActivityBuilder startActivity(Descriptor activityDescriptor) {
    return startActivity(null, activityDescriptor);
  }

  /**
   * 开始活动
   * @param activityName
   * @param activityDescriptor
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:17:17
   */
  public ActivityBuilder startActivity(String activityName, Descriptor activityDescriptor) {
    if (activityDescriptor==null) {
      throw new RuntimeException("activityDescriptor is null");
    }
    ActivityBuilder activityBuilder = new ActivityBuilder(this, activityName);
    activityBuilder.activity.setActivityBehaviourDescriptor(activityDescriptor);
    return activityBuilder;
  }

  /**
   * 开始活动
   * @param activityBehaviour
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:17:36
   */
  public ActivityBuilder startActivity(ActivityBehaviour activityBehaviour) {
    return startActivity(null, activityBehaviour);
  }

  /**
   * 开始活动
   * @param activityName
   * @param activityBehaviour
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:17:52
   */
  public ActivityBuilder startActivity(String activityName, ActivityBehaviour activityBehaviour) {
    if (activityBehaviour==null) {
      throw new RuntimeException("activity is null");
    }
    ActivityBuilder activityBuilder = new ActivityBuilder(this, activityName);
    activityBuilder.activity.setActivityBehaviour(activityBehaviour);
    return activityBuilder;
  }

  /**
   * 开始活动
   * @param activityClass
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:18:07
   */
  public ActivityBuilder startActivity(Class<? extends ActivityBehaviour> activityClass) {
    return startActivity(null, new ObjectDescriptor(activityClass));
  }

  /**
   * 开始活动
   * @param activityName
   * @param activityClass
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:18:22
   */
  public ActivityBuilder startActivity(String activityName, Class<? extends ActivityBehaviour> activityClass) {
    return startActivity(activityName, new ObjectDescriptor(activityClass));
  }

  /**
   * 开始事件
   * @param eventName
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:18:54
   */
  public EventBuilder startEvent(String eventName) {
    return new EventBuilder(this, eventName);
  }
  
  /**
   * 开始另外操作者
   * @param exceptionType
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:19:24
   */
  public CompositeExceptionHandlerBuilder startExceptionHandler(Class<? extends Throwable> exceptionType) {
    return new CompositeExceptionHandlerBuilder(this, exceptionType);
  }
  
  public CompositeBuilder property(String name, String value) {
    return property(new StringDescriptor(name, value));
  }

  public CompositeBuilder property(Descriptor descriptor) {
    compositeElement.addProperty(descriptor);
    return this;
  }

  /**
   * 结束活动
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:22:09
   */
  public CompositeBuilder endActivity() {
    throw new JbpmException("calling endActivity on a processBuilder is invalid");
  }
  /**
   * 开始流程
   * @param to
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:22:33
   */
  public FlowBuilder startFlow(String to) {
    throw new JbpmException("calling startFlow on a processBuilder is invalid");
  }
}
