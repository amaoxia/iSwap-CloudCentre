package org.jbpm.pvm.internal.builder;

import java.util.Date;

import org.jbpm.api.listener.EventListener;
import org.jbpm.pvm.internal.model.TimerDefinitionImpl;
import org.jbpm.pvm.internal.wire.Descriptor;

/**
 * 对流程的定时任务管理
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 上午11:38:31
 *@Team 研发中心
 */
public class TimerBuilder extends ObservableBuilder {
  
  protected CompositeBuilder compositeBuilder;
  protected TimerDefinitionImpl timerDefinition;

  public TimerBuilder(CompositeBuilder compositeBuilder, String eventName) {
    super(compositeBuilder.compositeElement, eventName);
    this.compositeBuilder = compositeBuilder;
    
    timerDefinition = compositeBuilder.compositeElement.createTimerDefinition();
  }
  
  public TimerBuilder dueDate(String dueDateCalendarExpression) {
    timerDefinition.setDueDateDescription(dueDateCalendarExpression);
    return this;
  }
  
  /**
   * 适当的时间
   * @param dueDate
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:41:33
   */
  public TimerBuilder dueDate(Date dueDate) {
    timerDefinition.setDueDate(dueDate);
    return this;
  }
  
  /**
   * 重复
   * @param repeatCalendarExpression
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:41:01
   */
  public TimerBuilder repeat(String repeatCalendarExpression) {
    timerDefinition.setRepeat(repeatCalendarExpression);
    return this;
  }
  
  /**
   * 再试
   * @param retries
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:40:41
   */
  public TimerBuilder retries(int retries) {
    timerDefinition.setRetries(retries);
    return this;
  }

  public TimerBuilder signal(String signalName) {
    timerDefinition.setSignalName(signalName);
    return this;
  }
  
  /**
   * 决定
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:39:38
   */
  public TimerBuilder decision() {
    timerDefinition.setExclusive(true);
    return this;
  }
  
  public TimerBuilder listener(EventListener eventListener) {
    addListener(eventListener);
    return this;
  }

  public TimerBuilder listener(EventListener eventListener, boolean propagation) {
    addListener(eventListener, propagation);
    return this;
  }

  public TimerBuilder listener(Descriptor descriptor) {
    addListener(descriptor);
    return this;
  }

  public TimerBuilder listener(Descriptor descriptor, boolean propagation) {
    addListener(descriptor, propagation);
    return this;
  }

  /**
   * 结束定时任务
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:39:12
   */
  public CompositeBuilder endTimer() {
    return compositeBuilder;
  }
}
