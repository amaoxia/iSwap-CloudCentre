package org.jbpm.pvm.internal.builder;

import org.jbpm.api.listener.EventListener;
import org.jbpm.pvm.internal.wire.Descriptor;
import org.jbpm.pvm.internal.wire.descriptor.StringDescriptor;


/**
 * 事件监听管理
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 上午11:27:48
 *@Team 研发中心
 */
public class EventBuilder extends ObservableBuilder {

  protected CompositeBuilder compositeBuilder;

  public EventBuilder(CompositeBuilder compositeBuilder, String eventName) {
    super(compositeBuilder.compositeElement, eventName);
    this.compositeBuilder = compositeBuilder;
  }
  
  /**
   * 监听
   * @param eventListener
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:23:34
   */
  public EventBuilder listener(EventListener eventListener) {
    addListener(eventListener);
    return this;
  }

  public EventBuilder listener(EventListener eventListener, boolean propagation) {
    addListener(eventListener, propagation);
    return this;
  }

  public EventBuilder listener(Descriptor descriptor) {
    addListener(descriptor);
    return this;
  }

  public EventBuilder listener(Descriptor descriptor, boolean propagation) {
    addListener(descriptor, propagation);
    return this;
  }

  public EventBuilder property(String name, String value) {
    StringDescriptor descriptor = new StringDescriptor(name, value);
    getEvent().addProperty(descriptor);
    return this;
  }
  
  /**
   * 开始另外操作者
   * @param exceptionType
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:24:00
   */
  public EventExceptionHandlerBuilder startExceptionHandler(Class<? extends Throwable> exceptionType) {
    return new EventExceptionHandlerBuilder(this, exceptionType);
  }
  
  /**
   * 结束监听
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:24:28
   */
  public CompositeBuilder endEvent() {
    return compositeBuilder;
  }
}
