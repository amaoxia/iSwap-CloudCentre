package org.jbpm.pvm.internal.stream;

import java.io.InputStream;

/**
 * �������ļ�������
 *@Company �к���Ԫ
 *@author   hudaowan
 *@version   iSwap V6.0 ���ݽ���ƽ̨  
 *@date   2011-8-13 ����04:38:06
 *@Team �з�����
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
