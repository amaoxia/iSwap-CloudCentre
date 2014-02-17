
package org.jbpm.pvm.internal.builder;

import org.jbpm.pvm.internal.model.ActivityImpl;


/**
 * 活动行为的创建者
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-12 上午09:33:38
 *@Team 研发中心
 */
public class ActivityBehaviourBuilder {
  
  protected ActivityBuilder activityBuilder;
  protected ActivityImpl activity;

  public ActivityBehaviourBuilder(ActivityBuilder activityBuilder) {
    this.activityBuilder = activityBuilder;
    this.activity = activityBuilder.activity;
  }
  
  public ActivityBuilder endBehaviour() {
    return activityBuilder;
  }
}
