package org.jbpm.pvm.internal.builder;

import org.jbpm.api.listener.EventListener;
import org.jbpm.pvm.internal.wire.Descriptor;


/**
 * 
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 上午11:28:14
 *@Team 研发中心
 */
public class CompositeExceptionHandlerBuilder extends ExceptionHandlerBuilder {

  protected CompositeBuilder compositeBuilder;

  public CompositeExceptionHandlerBuilder(CompositeBuilder compositeBuilder, Class<? extends Throwable> exceptionType) {
    this.compositeBuilder = compositeBuilder;
    exceptionHandler = compositeBuilder.compositeElement.createExceptionHandler();
    exceptionClass(exceptionType);
  }
  
  public CompositeExceptionHandlerBuilder exceptionClass(Class<? extends Throwable> exceptionType) {
    super.setExceptionClass(exceptionType);
    return this;
  }

  public CompositeExceptionHandlerBuilder listener(EventListener eventListener) {
    super.addListener(eventListener);
    return this;
  }

  public CompositeExceptionHandlerBuilder listener(Descriptor descriptor) {
    super.addListener(descriptor);
    return this;
  }

  public CompositeBuilder endExceptionHandler() {
    return compositeBuilder;
  }
}
