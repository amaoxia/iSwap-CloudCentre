package org.jbpm.pvm.internal.stream;

import java.io.InputStream;

/**
 * 工作流文件的输入
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 下午04:38:06
 *@Team 研发中心
 */
public abstract class StreamInput {
  
  protected String name;

  public abstract InputStream openStream();
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public String toString() {
    return name;
  }
}
