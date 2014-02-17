/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jbpm.pvm.internal.builder;

import java.lang.reflect.Constructor;

import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.Continuation;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;

/**
 * 创建活动
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-12 上午09:34:31
 *@Team 研发中心
 */
public class ActivityBuilder extends CompositeBuilder {
  
  private static final Class<?>[] ACTIVITYBEHAVIOURBUILDER_PARAMTYPES = new Class<?>[]{ActivityBuilder.class};

  /** the enclosing composite */
  protected CompositeBuilder compositeBuilder;
  protected ActivityImpl activity;
  
  public ActivityBuilder(CompositeBuilder compositeBuilder, String activityName) {
    this.compositeBuilder = compositeBuilder;
    this.activity = compositeBuilder.createActivity();
    this.compositeElement = activity;
    this.activity.setName(activityName);
  }
  
  
  /**
   * 初始化
   * @return 
   * @author  2011-8-12 下午12:07:14
   * @author  hudaowan
   */
  public ActivityBuilder initial() {
    setProcessDefinitionInitial(activity);
    return this;
  }
  
  /**
   * 设置流程定义的初始化
   */
  protected void setProcessDefinitionInitial(ActivityImpl initial) {
    compositeBuilder.setProcessDefinitionInitial(initial);
  }

  public <T extends ActivityBehaviourBuilder> T startBehaviour(Class<T> activityBehaviourBuilderType) {
    return startBehaviour(null, activityBehaviourBuilderType);
  }

  /**
   * 开始运行活动的节点
   * @param <T>
   * @param activityName
   * @param activityBehaviourBuilderType
   * @return 
   * @author  2011-8-12 下午12:08:49
   * @author  hudaowan
   */
  public <T extends ActivityBehaviourBuilder> T startBehaviour(String activityName, Class<T> activityBehaviourBuilderType) {
    if (activityBehaviourBuilderType==null) {
      throw new RuntimeException("activityBuilderType is null");
    }
    try {
      Constructor<T> constructor = activityBehaviourBuilderType.getConstructor(ACTIVITYBEHAVIOURBUILDER_PARAMTYPES);
      T activityBuilder = constructor.newInstance(new Object[]{this});
      return activityBuilder;
    } catch (Exception e) {
      throw new RuntimeException("couldn't instantiate activity behaviour builder type "+activityBehaviourBuilderType.getName(), e);
    }
  }
  
  /**
   * 活动的节点运行结束
   */
  public CompositeBuilder endActivity() {
    return compositeBuilder;
  }
  
  /**
   * 开始流程
   */
  public FlowBuilder startFlow(String to) {
    UnresolvedFlow unresolvedFlow = new UnresolvedFlow();
    unresolvedFlow.transition = activity.createOutgoingTransition();
    unresolvedFlow.destinationName = to;
    addUnresolvedFlow(unresolvedFlow);
    return new FlowBuilder(this, unresolvedFlow.transition);
  }
  
  /**
   * 流程结束
   */
  public ProcessDefinitionImpl endProcess() {
    return compositeBuilder.endProcess();
  }
  
  /**
   * 流程的活动方向
   * @param to
   * @return 
   * @author  2011-8-12 下午02:03:01
   * @author  hudaowan
   */
  public ActivityBuilder transition(String to) {
    startFlow(to);
    return this;
  }

  /**
   * 流程活动方向
   * @param to
   * @param name
   * @return 
   * @author  2011-8-12 下午02:03:30
   * @author  hudaowan
   */
  public ActivityBuilder transition(String to, String name) {
    startFlow(to).name(name);
    return this;
  }

  /**
   * 并行执行
   * @return 
   * @author  2011-8-12 下午02:04:44
   * @author  hudaowan
   */
  public ActivityBuilder asyncExecute() {
    activity.setContinuation(Continuation.ASYNCHRONOUS);
    return this;
  }

  /***
   * 添加未解决的流程
   */
  protected void addUnresolvedFlow(UnresolvedFlow unresolvedFlow) {
    compositeBuilder.addUnresolvedFlow(unresolvedFlow);
  }
}
