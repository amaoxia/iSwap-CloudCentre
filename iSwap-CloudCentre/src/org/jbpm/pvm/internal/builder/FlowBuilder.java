package org.jbpm.pvm.internal.builder;

import org.jbpm.api.listener.EventListener;
import org.jbpm.api.model.Event;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.jbpm.pvm.internal.wire.Descriptor;
import org.jbpm.pvm.internal.wire.descriptor.StringDescriptor;


/**
 * 流程编译者
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 上午11:25:38
 *@Team 研发中心
 */
public class FlowBuilder extends ObservableBuilder {

  protected ActivityBuilder activityBuilder;
  protected TransitionImpl transition;
  
  public FlowBuilder(ActivityBuilder activityBuilder, TransitionImpl transition) {
    super(transition, Event.TAKE);
    this.activityBuilder = activityBuilder;
    this.transition = transition;
  }
  
  public FlowBuilder name(String name) {
    transition.setName(name);
    return this;
  }
  
  public FlowBuilder expr(String expression) {
    // transition.setExpression(expression);
    return this;
  }
  
  public FlowBuilder listener(EventListener eventListener) {
    addListener(eventListener);
    return this;
  }

  public FlowBuilder listener(EventListener eventListener, boolean propagation) {
    addListener(eventListener, propagation);
    return this;
  }

  public FlowBuilder listener(Descriptor descriptor) {
    addListener(descriptor);
    return this;
  }

  public FlowBuilder listener(Descriptor descriptor, boolean propagation) {
    addListener(descriptor, propagation);
    return this;
  }

  public FlowBuilder property(String name, String value) {
    StringDescriptor descriptor = new StringDescriptor(name, value);
    transition.addProperty(descriptor);
    return this;
  }

  /**
   * 结束流程
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:25:13
   */
  public ActivityBuilder endFlow() {
    return activityBuilder;
  }
}
