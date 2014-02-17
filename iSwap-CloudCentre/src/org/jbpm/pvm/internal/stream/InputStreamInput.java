package org.jbpm.pvm.internal.stream;

import java.io.InputStream;

import org.jbpm.api.JbpmException;

/**
 * 以输入流的形式存在
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 下午04:42:30
 *@Team 研发中心
 */
public class InputStreamInput extends StreamInput {
  
  protected InputStream inputStream;
  
  public InputStreamInput(InputStream inputStream) {
    if (inputStream==null) {
      throw new JbpmException("inputStream is null");
    }
    this.name = "input-stream";
    this.inputStream = inputStream;
  }

  public InputStream openStream() {
    return inputStream;
  }
}
