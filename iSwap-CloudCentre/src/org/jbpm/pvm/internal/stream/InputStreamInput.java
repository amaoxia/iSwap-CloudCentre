package org.jbpm.pvm.internal.stream;

import java.io.InputStream;

import org.jbpm.api.JbpmException;

/**
 * ������������ʽ����
 *@Company �к���Ԫ
 *@author   hudaowan
 *@version   iSwap V6.0 ���ݽ���ƽ̨  
 *@date   2011-8-13 ����04:42:30
 *@Team �з�����
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
