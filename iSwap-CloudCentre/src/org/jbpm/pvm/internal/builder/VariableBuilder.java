package org.jbpm.pvm.internal.builder;

import org.jbpm.pvm.internal.el.Expression;
import org.jbpm.pvm.internal.model.VariableDefinitionImpl;
import org.jbpm.pvm.internal.wire.Descriptor;


/**
 * 
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 上午11:51:56
 *@Team 研发中心
 */
public class VariableBuilder {
  
  protected CompositeBuilder compositeBuilder;
  protected VariableDefinitionImpl variableDefinition; 

  /**
   * 
   * 创建一个新的实例 VariableBuilder.  
   *  
   * @param compositeBuilder
   */
  public VariableBuilder(CompositeBuilder compositeBuilder) {
    this.compositeBuilder = compositeBuilder;
    variableDefinition = compositeBuilder.compositeElement.createVariableDefinition();
  }
  
  /**
   * 变量
   * @param name
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:52:27
   */
  public VariableBuilder name(String name) {
    variableDefinition.setName(name);
    return this;
  }

  public VariableBuilder type(String type) {
    variableDefinition.setTypeName(type);
    return this;
  }
  
  /**
   * 初始化值
   * @param initialValueDescriptor
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:53:26
   */
  public VariableBuilder initialValue(Descriptor initialValueDescriptor) {
    variableDefinition.setInitDescriptor(initialValueDescriptor);
    return this;
  }

  /**
   * 初始化值的表示
   * @param initialExpression
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:53:54
   */
  public VariableBuilder initialExpression(String initialExpression) {
    variableDefinition.setInitExpression(Expression.create(initialExpression, null));
    return this;
  }

  /**
   * 结束变量
   * @return 
   * @author  hudaowan
   * @date 2011-8-13 上午11:54:18
   */
  public CompositeBuilder endVariable() {
    return compositeBuilder;
  }
}
